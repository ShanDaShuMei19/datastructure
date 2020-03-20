package work_four;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

public class MyIo {
	
	//从终端读入n个字符，建立哈夫曼树
	public static void myInitialization() {
		newTxt("codefile");
		newTxt("huffmanfile");
		newTxt("textfile");
	}
	
	//利用已建好的哈夫曼树，对字符进行编码，然后将正文编码结果存入文件codefile中；
	public static void myCoding() {
		
	}
	
	//利用已建好的哈夫曼树将文件codefile中的代码进行译码，结果存入文件textfile中。
	public static void myDecoding() {
		
	}
	
	//查看
	public void myLookOver(HuffmanEnum huff) {
		StringBuffer strbuff = new StringBuffer();
		switch(huff) {
		case 哈夫曼树:break;
		case 编码:break;
		case 译码:break;
		case 全部:break;
		}
	}
	
	
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
	}
}
