package wh.MyJframe;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class MyIo {
	
	public static int total;
	public static void newTxt(String filename) {
		File f = new File(filename);
	    if (!f.exists()){
	        try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "文件已存在，创建失败！");
			}
	    }
	    else {
	    	JOptionPane.showMessageDialog(null, "文件已存在！");
	    }
	}
	
	public static void saveTxt(String filename,String ss) {
			BufferedWriter bw;
			try {
				bw = new BufferedWriter(new FileWriter(filename,true));
				bw.write(ss);
				bw.newLine();
			    bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "打开失败");
			}
	}//end of method
	
	public static String queryTxt(String filename,String ss) {
		total = 0;
		int line_num = 0;
		StringBuffer textArea = new StringBuffer();
		BufferedReader br;
		 try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				line_num += 1;
				MyIo.queryLine(line_num,line,textArea,ss);
			}
			br.close();
		 } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
		 JOptionPane.showMessageDialog(null, "打开失败");
		} catch (IOException e) {
			// TODO Auto-generated catch block
		JOptionPane.showMessageDialog(null, "读取失败");
		}	
		return "总共出现" + total + "次\n" + textArea.toString();
	}//end of method
	
	public static void queryLine(int line_num,String my_txt,StringBuffer textArea,String stest) {
		int total_line = 0;
		StringBuffer textArea_line = new StringBuffer();
		
		char[] s = my_txt.toCharArray();
		char[] ss = stest.toCharArray();
		if(s.length<ss.length)return;
		
		for(int i = 0;s.length - i >= ss.length;) {
			int j;
			for(j = 0;j < ss.length;) {
				if(s[i] == ss[j]) {
					i+=1;
					j+=1;
				}
				else {
					i = i-j+1;
					break;
				}	
			}//寻找
			if(j == ss.length) {
				//di i-j+1 lie
				total += 1;
				total_line += 1;
				textArea_line.append("\t第"+(i-j+1)+"列\n");
			}//
		}//
		
		if(total_line != 0) {
			textArea.append("第"+ line_num +"行总共出现"+ total_line + "次：\n");
			textArea.append(textArea_line);
		}
	}//end of method
}//end of class 
