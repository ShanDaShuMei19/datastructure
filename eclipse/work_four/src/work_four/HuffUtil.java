package work_four;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class HuffUtil{
	
	TreeNode hu_root = new TreeNode();
    //建立二叉树
    public TreeNode constructTree(String s) {
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
        
        //遍历dataMap,初始化二叉树节点，并将所有初始化后的节点放到nodeList中，并进行排序
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
		//对存放节点的链表进行排序，方便后续进行组合
        //
        Collections.sort(nodeList, new Comparator<TreeNode>() {
            public int compare(TreeNode t1, TreeNode t2) {
                return t1.freq-t2.freq;
            }
        });

        //size==1,代表字符串只包含一种类型的字母
        if(nodeList.size()==1){
            TreeNode t = nodeList.get(0);
            //hu_root = new TreeNode(null,0,nodeList.get(0).freq,t,null);
            return new TreeNode(null,0,nodeList.get(0).freq,t,null);
        }

        //利用排序好的节点建立二叉树，root为初始化根节点
        TreeNode root = null;
        while(nodeList.size()>0){
			//因为nodeList在前面已经排好序，所以直接取出前两个节点，他们的和肯定为最小
            TreeNode t1 = nodeList.removeFirst();
            TreeNode t2 = nodeList.removeFirst();
			//左子树的val赋值为0，右子树的val赋值为1
            t1.val = 0;
            t2.val = 1;
			//将取出的两个节点进行合并
            if(nodeList.size()==0){
				//此时代表所有节点合并完毕，返回结果
                root = new TreeNode(null,0,t1.freq+t2.freq,t1,t2);
            }else {
				//此时代表还有可以合并的节点
                TreeNode tmp = new TreeNode(null,0,t1.freq+t2.freq,t1,t2);
				
                //t1、t2合并后，需要将得到的新节点加入到原链表中，继续与其他节点合并，
				//此时需要保证原链表的有序性，需要进行排序
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
		//返回建立好的二叉树根节点
        //hu_root =  
        return root;
    }
	
    //对已经建立好的二叉树进行遍历，得到每个字符的编码
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
	//编码方法，返回Object[]，大小为2,Objec[0]为编码后的字符串，Object[1]为编码对应的码表
    public Object[] encode(String s){
    	
        Object[] res= new Object[2];
        //Map
        Map<Character,String> encodeMap = new HashMap<Character, String>();
        //生成哈夫曼树，得到树的根节点tree
        TreeNode tree = constructTree(s);
        //得到每个
        findPath(tree, encodeMap, new StringBuilder());
        findPath(tree, encodeMap, new StringBuilder());
        
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<s.length();i++){
            String tmp = encodeMap.get(s.charAt(i));
            sb.append(tmp);
        }
        res[0]=sb.toString();
        res[1] = encodeMap;
        return res;

    }
		
	//译码 (Decoding)，根据decodeMap将str_Decoding译码，返回
	public String decode(String str_Decoding,Map<Character,String> decodeMap){
		//
        StringBuilder decodeStr = new StringBuilder();
        //
        while(str_Decoding.length()>0){
            for(Map.Entry<Character,String> e: decodeMap.entrySet()){
                String charEncodeStr = e.getValue();
                if(str_Decoding.startsWith(charEncodeStr)){
                    decodeStr.append(e.getKey());
                    str_Decoding = str_Decoding.substring(charEncodeStr.length());
                    break;
                }
            }
        }
        return decodeStr.toString();
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
    }
	
}