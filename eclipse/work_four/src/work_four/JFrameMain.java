package work_four;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Dimension;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class JFrameMain {

	private JFrame frame;

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
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(JFrameMain.class.getResource("/com/sun/javafx/scene/control/skin/caspian/images/capslock-icon.png")));
		frame.setBounds(100, 100, 717, 515);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setMinimumSize(new Dimension(300, 300));
		textPane.setBounds(14, 184, 315, 271);
		frame.getContentPane().add(textPane);
		
		JLabel lblNewLabel = new JLabel("文字输入区");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 19));
		lblNewLabel.setBounds(14, 153, 168, 18);
		frame.getContentPane().add(lblNewLabel);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(370, 74, 315, 381);
		frame.getContentPane().add(textArea);
		
		JButton btnNewButton_1 = new JButton("查看");
		
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 19));
		btnNewButton_1.setBounds(524, 34, 147, 27);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("初始化");
		
		btnNewButton_1_1.setFont(new Font("宋体", Font.PLAIN, 19));
		btnNewButton_1_1.setBounds(14, 25, 147, 27);
		frame.getContentPane().add(btnNewButton_1_1);
		
		JButton btnNewButton_1_2 = new JButton("编码");
		
		btnNewButton_1_2.setFont(new Font("宋体", Font.PLAIN, 19));
		btnNewButton_1_2.setBounds(14, 71, 147, 27);
		frame.getContentPane().add(btnNewButton_1_2);
		
		JButton btnNewButton_1_3 = new JButton("译码");
		
		btnNewButton_1_3.setFont(new Font("宋体", Font.PLAIN, 19));
		btnNewButton_1_3.setBounds(14, 113, 147, 27);
		frame.getContentPane().add(btnNewButton_1_3);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(HuffmanEnum.values()));
		comboBox.setBounds(370, 37, 124, 24);
		frame.getContentPane().add(comboBox);
//comboBox	
//textArea 显示区
//textPane 输入区		
//初始化	
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MyIo.myInitialization();
			}
		});//end of button
//编码		
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
//译码
		btnNewButton_1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
//查看
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
	}//end of initialize
}//end of class
