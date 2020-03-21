package whsdu;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

public class MyIo {
	
	TreeNode hu_root = new TreeNode();
	String str_hu = new String();
	String str_coding = new String();
	String str_decoding = new String();
	StringBuilder codeBuilder = new StringBuilder();
	Map<Character,String> encodeMap = new HashMap<Character, String>();
	
	//初始化
	public void myInitialization(String str_huff){
		str_hu = str_huff;
		newTxt("codefile");
		newTxt("huffmanfile");
		newTxt("textfile");
        //生成哈夫曼树，得到树的根节点tree
		str_coding = new String();
        constructTree(str_hu);
        //得到每个字符的编码，储存到encodeMap中
        findPath(hu_root, encodeMap, new StringBuilder());
        findPath(hu_root, encodeMap, new StringBuilder());
        //建立编译表
        myTranslate();
        myIO("huffmanfile", codeBuilder.toString());     
	}
	
	//编码
	public void myCoding() {
		//根据encodeMap，将输入文字进行编译，结果储存在文件中
		str_coding = new String();
		StringBuilder sb = new StringBuilder();
        for(int i=0;i<str_hu.length();i++){
            String tmp = encodeMap.get(str_hu.charAt(i));
            sb.append(tmp);
        }
        str_coding = sb.toString();
        //存储到txt文件中
		myIO("codefile", str_coding);
		
	}
	
	//译码		根据encodeMap和编译完成的str_coding，进行译码
	public void myDecoding() {
		String str = str_coding;
		if(str.length() != 0) {
			str_decoding = new String();
			StringBuilder decodeStr = new StringBuilder();
	        //
	        while(str.length()>0){
	            for(Map.Entry<Character,String> e: encodeMap.entrySet()){
	                String charEncodeStr = e.getValue();
	                if(str.startsWith(charEncodeStr)){
	                    decodeStr.append(e.getKey());
	                    str = str.substring(charEncodeStr.length());
	                    break;
	                }
	            }
	        }
	        str_decoding = decodeStr.toString();
	        myIO("textfile", str_decoding);
		}
		else {
			JOptionPane.showMessageDialog(null, "您还没有编码！");
		}
	}
	
	//将str_hu写入文件filename,覆盖式
	public void myIO(String filename,String str_hu) {
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter("src/"+filename+".txt"));
			bw.write(str_hu);
			bw.newLine();
		    bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "打开失败");
		}
	}
	
	//判断文件是否存在
	public void newTxt(String filename) {
		File f = new File("src/"+filename+".txt");
	    if (!f.exists()){
	        try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "文件已存在，创建失败");
			}
	    }
	    else {
	    	//JOptionPane.showMessageDialog(null, "文件已存在！");
	    }
	}//end of method
	
	//得到字符的编码
    private void findPath(TreeNode root, Map<Character,String> res, StringBuilder path) {
        //为叶子节点
    	if (root.left == null && root.right == null) {
            path.append(root.val);
            res.put(root.ch,path.substring(1));
            path.deleteCharAt(path.length() - 1);
            return;
        }
        path.append(root.val);
        if (root.left != null) findPath(root.left, res, path);
        if (root.right != null) findPath(root.right, res, path);
        path.deleteCharAt(path.length() - 1);
    }
	
    //建立二叉树
    public void constructTree(String s) {
        //计算字符的权值,存储到Map中
        Map<Character, Integer> dataMap = new HashMap<Character, Integer>();
        char[] s_char = s.toCharArray();
        for(char c:s_char) {
        	if (dataMap.containsKey(c)) {
                int count = dataMap.get(c);
                dataMap.put(c, count + 1);
            } else {
                dataMap.put(c, 1);
            }
        }
        
        //树节点链表nodeList
        LinkedList<TreeNode> nodeList = new LinkedList<TreeNode>();
        //遍历Map
        for (Map.Entry<Character, Integer> entry : dataMap.entrySet()) {
            Character ch = entry.getKey();
            int freq = entry.getValue();
            int val = 0;
            TreeNode tmp = new TreeNode(ch,val,freq,null,null);
            nodeList.add(tmp);
        }

        //
        Collections.sort(nodeList, new Comparator<TreeNode>() {
            public int compare(TreeNode t1, TreeNode t2) {
                return t1.freq-t2.freq;
            }
        });

        if(nodeList.size()==1){
            TreeNode t = nodeList.get(0);
            hu_root = new TreeNode(null,0,nodeList.get(0).freq,t,null);
        }

        TreeNode root = null;
        while(nodeList.size()>0){
            TreeNode t1 = nodeList.removeFirst();
            TreeNode t2 = nodeList.removeFirst();
            t1.val = 0;
            t2.val = 1;
            if(nodeList.size()==0){
                root = new TreeNode(null,0,t1.freq+t2.freq,t1,t2);
            }else {
				//
                TreeNode tmp = new TreeNode(null,0,t1.freq+t2.freq,t1,t2);
				
				//
                if(tmp.freq>nodeList.getLast().freq){
                    nodeList.addLast(tmp);
                }else {
                    for(int i=0;i<nodeList.size();i++){
                        int tmpFreq = tmp.freq;
                        if(tmpFreq<= nodeList.get(i).freq){
                            nodeList.add(i,tmp);
                            break;
                        }
                    }
                }
            }
        }
        hu_root = root;
    }
	
    //建立编译表
    public void myTranslate() {
    	codeBuilder = new StringBuilder();
    	for (Entry<Character, String> entry : encodeMap.entrySet()) {
    		codeBuilder.append(entry.getKey());
    		codeBuilder.append(":");
    		codeBuilder.append(entry.getValue());
    		codeBuilder.append("\n");
        }
	}
    
    //查看
    public String myLookOver(HuffmanEnum huff) {
		StringBuilder all = new StringBuilder();
		switch(huff) {
		case 哈夫曼树:return codeBuilder.toString();
		case 编码:
			if (str_coding.length() != 0) {
				return str_coding;
			}else{
				return "请您编码后再查看！";
			}
		case 译码:
			if (str_decoding.length() != 0) {
				return str_decoding;
			}else{
				return "请您译码后再查看！";
			}
		case 全部:
			all.append(codeBuilder.toString());
			all.append(str_coding);
			all.append(str_decoding);
			return all.toString();
		}
		return all.toString();
	}
    
	//内部类
    private class TreeNode {
    	Character ch;
    	int val;
    	int freq;
    	TreeNode left;
    	TreeNode right;
        public TreeNode() { 
        	
        }
        public TreeNode(Character ch, int val, int freq, TreeNode left, TreeNode right) {
            this.ch = ch;
            this.val = val;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }
    }//内部类	
}//end of class
