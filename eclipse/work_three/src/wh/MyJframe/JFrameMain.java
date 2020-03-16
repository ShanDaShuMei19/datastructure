package wh.MyJframe;

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

public class JFrameMain {

	
	String filename = new String();
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameMain window = new JFrameMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JFrameMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(Color.WHITE);
		frame.setForeground(Color.WHITE);
		frame.setFont(new Font("华文新魏", Font.PLAIN, 20));
		frame.setTitle("\u5355\u8BCD\u68C0\u7D22\u7EDF\u8BA1");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(JFrameMain.class.getResource("/com/sun/javafx/scene/control/skin/caspian/images/capslock-icon.png")));
		frame.setBounds(100, 100, 736, 507);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("宋体", Font.PLAIN, 19));
		textField.setBounds(169, 57, 163, 24);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("新建");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 19));
		
		btnNewButton.setBounds(378, 56, 113, 27);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("输入文件名");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 19));
		lblNewLabel.setBounds(14, 55, 113, 24);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("输入查询文字");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 19));
		lblNewLabel_1.setBounds(14, 92, 128, 24);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("查询");
		
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 19));
		btnNewButton_1.setBounds(378, 91, 113, 27);
		frame.getContentPane().add(btnNewButton_1);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("宋体", Font.PLAIN, 19));
		textField_1.setBounds(169, 94, 163, 24);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(14, 129, 293, 318);
		frame.getContentPane().add(textPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(328, 131, 321, 316);
		frame.getContentPane().add(textArea);
		
		JButton btnNewButton_2 = new JButton("保存");
		
		btnNewButton_2.setFont(new Font("宋体", Font.PLAIN, 19));
		btnNewButton_2.setBounds(505, 56, 113, 27);
		frame.getContentPane().add(btnNewButton_2);
//查询
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str_query = textField_1.getText();
				if(filename.length() != 0) {
					if(str_query.length() != 0) {
						String setTxt = MyIo.queryTxt("src/" + filename  + ".txt", str_query);
						textArea.setText(setTxt);
					}
					else {
						JOptionPane.showMessageDialog(null, "请输入查询文字");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "您还没有打开文件");
				}		
			}
		});
//新建		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				filename = textField.getText();
				if(filename.length() != 0) {
					MyIo.newTxt("src/" + filename  + ".txt");
				}
				else {
					JOptionPane.showMessageDialog(null, "请输入文件名");
				}
			}
		});
//保存		
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(filename.length() != 0) {
					String li = textPane.getText();
					MyIo.saveTxt("src/" + filename  + ".txt",li);
				}
				else {
					JOptionPane.showMessageDialog(null, "请先建立一个文件！");
				}
			}
		});//end of button
		
	}//end of initialize
}//end of class
