package GUI;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class MyJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyJFrame frame = new MyJFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MyJFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 785, 557);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("城市数量");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 19));
		lblNewLabel.setBounds(14, 38, 97, 22);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(93, 38, 73, 24);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("路径数");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 19));
		lblNewLabel_1.setBounds(227, 37, 72, 23);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(297, 39, 73, 24);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		table = new JTable();
		table.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		table.setFont(new Font("宋体", Font.PLAIN, 19));
		table.setRowHeight(20);
		
		
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"\u5750\u6807i", "\u5750\u6807j", "\u6743\u503Cw"},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"\u5750\u6807i", "\u5750\u6807j", "\u6743\u503Cw"
			}
		));
		table.setBounds(14, 109, 485, 388);
		contentPane.add(table);
		
		JLabel lblNewLabel_2 = new JLabel("起点");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 19));
		lblNewLabel_2.setBounds(14, 74, 97, 22);
		contentPane.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(93, 72, 73, 24);
		contentPane.add(textField_2);
		
		JLabel lblNewLabel_3 = new JLabel("终点");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 19));
		lblNewLabel_3.setBounds(227, 73, 97, 22);
		contentPane.add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(297, 73, 73, 24);
		contentPane.add(textField_3);
		
		JButton btnNewButton = new JButton("查询");
		
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 19));
		btnNewButton.setBounds(413, 72, 113, 27);
		contentPane.add(btnNewButton);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(513, 117, 240, 380);
		contentPane.add(textPane);
			
		textField.setText("0");		//点数
		textField_1.setText("0"); 	//边数
		textField_2.setText("1");	//起点
		textField_3.setText("0");	//终点
		
		JButton btnNewButton_1 = new JButton("测试数据");
		
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 19));
		btnNewButton_1.setBounds(413, 38, 113, 27);
		contentPane.add(btnNewButton_1);
//测试数据		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("7");		//点数
				textField_1.setText("15"); 	//边数
				textField_2.setText("1");	//起点
				textField_3.setText("0");	//终点
				table.setModel(new DefaultTableModel(
						new Object[][] {
							{"\u5750\u6807i", "\u5750\u6807j", "\u6743\u503Cw"},
							{"1", "2", "2546"},
							{"2", "1", "2546"},
							{"2", "5", "544"},
							{"5", "2", "544"},
							{"2", "3", "5614"},
							{"3", "2", "5614"},
							{"7", "5", "985"},
							{"5", "6", "487"},
							{"6", "3", "6542"},
							{"3", "4", "55"},
							{"4", "5", "841"},
							{"6", "7", "5145"},
							{"7", "6", "51"},
							{"1", "3", "489"},
							{"4", "7", "4599"},
							{null, null, null},
							{null, null, null},
							{null, null, null},
						},
						new String[] {
							"\u5750\u6807i", "\u5750\u6807j", "\u6743\u503Cw"
						}
					));
			}
		});
//查询		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//路径数,定点数
				int num,v_num,qidian,zhongdian;
				String num_str = textField_1.getText();
				String vum_str = textField.getText();
				String qidian_str = textField_2.getText();
				String zhongdian_str = textField_3.getText();
				if(num_str.length()!=0 && vum_str.length()!=0 && qidian_str.length()!=0) {
					num = Integer.valueOf(num_str);
					v_num = Integer.valueOf(vum_str);
					qidian = Integer.valueOf(qidian_str);
					zhongdian = Integer.valueOf(zhongdian_str);
					
					//顶点
					String[] v_str = new String[v_num];
					for(int i = 1;i <= v_num;i++) {
						v_str[i-1] = "v" + i;	
					}
					
					//权值，邻接矩阵
					int[][] weight = new int[v_num][v_num];
					for(int i = 0;i < v_num;i++) {
						for(int j = 0;j < v_num;j++) {
							weight[i][j] = -1;	
						}		
					}
					for(int i = 0;i < v_num;i++) {
						weight[i][i] = 0;		
					}

					//读入权值
					for(int i = 1;i <= num;i+=1) {
						int x = myRead(i, 0, table);
						int y = myRead(i, 1, table);
						int z = myRead(i, 2, table);
						weight[x-1][y-1] = z;
					}

					if(zhongdian == 0) {
						textPane.setText(MyUtil.myDijkstra1(v_num,weight, v_str, qidian-1));
					}
					else {
						textPane.setText(MyUtil.myDijkstra2(v_num,weight, v_str, qidian-1,zhongdian-1));
					}				
				}
				else {
					JOptionPane.showMessageDialog(null, "请输入顶点数和边数");
				}			
			}
		});//end of button
		
	}//end of method

	public static int myRead(int x,int y,JTable table) {
		if(table.getValueAt(x, y) == null) {
			JOptionPane.showMessageDialog(null, "请输入完整数据：" + x+1 + "  " + y+1);
			return 0;
		}
		else{
			return Integer.valueOf(table.getValueAt(x, y).toString());
		}
	}//end of method
}//end of class
