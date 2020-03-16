package wh.MyJframe;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class MyIo {
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
				bw = new BufferedWriter(new FileWriter(filename));
				bw.write(ss);
				bw.newLine();
			    bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "打开失败");
			}
	}
}
