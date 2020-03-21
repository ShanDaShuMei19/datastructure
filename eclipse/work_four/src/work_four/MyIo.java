package work_four;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

public class MyIo {

	private HuffUtil huffUtil = new HuffUtil();
	static Object[] res= new Object[2];
	static String decoding = new String();
	static Map<Character,String> encodeMap;
	static String str;
	static StringBuilder codeBuilder;
	//从终端读入n个字符str_hu，建立哈夫曼树，返回ArrayList
	public void myInitialization(String str_hu) {
		newTxt("codefile");
		newTxt("huffmanfile");
		newTxt("textfile");
		//每次初始化，重新生成哈夫曼树
		huffUtil = new HuffUtil();
		//编译
		res = huffUtil.encode(str_hu);
		encodeMap = (Map<Character, String>) res[1];
		str = (String) res[0];
		decoding = huffUtil.decode(str, encodeMap);
		
		codeBuilder = new StringBuilder();
		for (Entry<Character, String> entry : encodeMap.entrySet()) {
			codeBuilder.append(entry.getKey());
			codeBuilder.append(":");
			codeBuilder.append(entry.getValue());
			codeBuilder.append("\n");
        }
		
		MyIo.myIO("huffmanfile", codeBuilder.toString());		
	}
	
	//利用已建好的哈夫曼树，对字符进行编码，然后将正文编码结果存入文件codefile中；
	//根据huffmancodes,将str_hu编译为译码，存入codefile中
	public static void myCoding() {
		//返回编码集 和 编译结果
		MyIo.myIO("textfile", decoding);
		
	}
	
	//利用已建好的哈夫曼树将文件codefile中的代码进行译码，结果存入文件textfile中。
	//将codefile中的译码  译 为string，存入textfile文件中（根据huffmancodes）
	public static void myDecoding() {
		//返回原文字
		//return decoding;
		MyIo.myIO("codefile", str);
	}
	
	//查看
	//根据查看类型，返回对应txt文件中的字符串
	public String myLookOver(HuffmanEnum huff) {
		StringBuilder all = new StringBuilder();
		switch(huff) {
		case 哈夫曼树:return codeBuilder.toString();
		case 编码:return str;
		case 译码:return decoding;
		case 全部:
			all.append(codeBuilder.toString());
			all.append(str);
			all.append(decoding);
			return all.toString();
		}
		return all.toString();
	}
	
	//将str_hu写入文件filename,覆盖式
	public static void myIO(String filename,String str_hu) {
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter("src/"+filename+".txt",true));
			bw.write(str_hu);
			bw.newLine();
		    bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "打开失败");
		}
	}
	
	//判断文件是否存在
	public static void newTxt(String filename) {
		File f = new File("src/"+filename+".txt");
	    if (!f.exists()){
	        try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "文件已存在，创建失败！");
			}
	    }
	    else {
	    	//JOptionPane.showMessageDialog(null, "文件已存在！");
	    }
	}//end of method
	
}//end of class
