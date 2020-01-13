package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controller.UserController;
import domain.Student;

public class DataControll extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private JTextField snoField;
	private JTextField snameField;
	private JTextField ssexField;
	private JTextField sclassField;
	UserController controller;
	DataControll dataControll = this;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					DataControll frame = new DataControll();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public DataControll() {
		setTitle("数据操作");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel wayLabel = new JLabel("查询方式");
		wayLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("宋体", Font.PLAIN, 20));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"查询所有人", "按班级查询"}));
		
		JLabel classLabel = new JLabel("输入所要查询班级");
		classLabel.setHorizontalAlignment(SwingConstants.CENTER);
		classLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		
		textField = new JTextField();
		textField.setFont(new Font("宋体", Font.PLAIN, 20));
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		table = new JTable();
		table.setRowHeight(25);
		table.setFont(new Font("宋体", Font.PLAIN, 20));
		String[] rowTabel = {"学号","姓名","性别","班级"};
		Object[][] object = new Object[][] {};
		DefaultTableModel dtm = new DefaultTableModel(object, rowTabel);
		table.setModel(dtm);
		scrollPane.setViewportView(table);
		// 获取表格模型
		final TableModel tableModel = table.getModel();
		// 在 表格模型上 添加 数据改变监听器
        tableModel.addTableModelListener(new TableModelListener() {
        	@Override
            public void tableChanged(TableModelEvent e) {
                // 获取 第一个 和 最后一个 被改变的行（只改变了一行，则两者相同）
                int firstRow = e.getFirstRow();
                int lastRow = e.getLastRow();

                // 获取被改变的列
                int column = e.getColumn();

                // 事件的类型，可能的值有:
                //     TableModelEvent.INSERT   新行或新列的添加
                //     TableModelEvent.UPDATE   现有数据的更改
                //     TableModelEvent.DELETE   有行或列被移除
                int type = e.getType();

                // 针对 现有数据的更改 更新其他单元格数据
                if (type == TableModelEvent.UPDATE) {
                	// 遍历每一个修改的行，单个学科分数更改后同时更新总分数
                    for (int row = firstRow; row <= lastRow; row++) {
                    	String changedSno = tableModel.getValueAt(row, 0).toString();
                    	String changedSname = tableModel.getValueAt(row, 1).toString();
                    	String changedSsex = tableModel.getValueAt(row, 2).toString();
                    	String changedSclass = tableModel.getValueAt(row, 3).toString();
                    	controller.update(changedSno, changedSname, changedSsex, changedSclass);
                    }
                }
        	}
        });
                
        	
        //-------
		JButton button = new JButton("查询");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller = new UserController();
				if (comboBox.getSelectedItem().equals("查询所有人")) {
//					System.out.println(comboBox.getSelectedItem());
					ResultSet rset = controller.select();
					try {
						dtm.setRowCount(0);
						while(rset.next()) {
							String sno = rset.getString(1);
							String sname = rset.getString(2);
							String ssex = rset.getString(3);
							String sclass = rset.getString(4);
							String[] row = {sno,sname,ssex,sclass};
							dtm.addRow(row);
//							System.out.println(Arrays.toString(row));
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else if (comboBox.getSelectedItem().equals("按班级查询")) {
					ResultSet rset = controller.select(textField.getText());
					try {
						dtm.setRowCount(0);
						while(rset.next()) {
							String sno = rset.getString(1);
							String sname = rset.getString(2);
							String ssex = rset.getString(3);
							String sclass = rset.getString(4);
							String[] row = {sno,sname,ssex,sclass};
							dtm.addRow(row);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		button.setFont(new Font("宋体", Font.PLAIN, 20));
		
		JLabel snoLabel = new JLabel("学号：");
		snoLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		
		snoField = new JTextField();
		snoField.setFont(new Font("宋体", Font.PLAIN, 20));
		snoField.setColumns(10);
		
		JLabel snameLabel = new JLabel("姓名：");
		snameLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		
		snameField = new JTextField();
		snameField.setFont(new Font("宋体", Font.PLAIN, 20));
		snameField.setColumns(10);
		
		JLabel ssexLabel = new JLabel("性别：");
		ssexLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		
		ssexField = new JTextField();
		ssexField.setFont(new Font("宋体", Font.PLAIN, 20));
		ssexField.setColumns(10);
		
		JLabel sclassLabel = new JLabel("班级：");
		sclassLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		
		sclassField = new JTextField();
		sclassField.setFont(new Font("宋体", Font.PLAIN, 20));
		sclassField.setColumns(10);
		
		JButton insertButton = new JButton("确认添加");
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller = new UserController();
				int count = controller.insert(snoField.getText(), snameField.getText(), ssexField.getText(), sclassField.getText());
				if(count > 0) {
	    			JOptionPane.showMessageDialog(DataControll.this, "添加成功", "提示", JOptionPane.INFORMATION_MESSAGE);
	    			snoField.setText("");
	    			snameField.setText("");
	    			ssexField.setText("");
	    			sclassField.setText("");
				}else {
	    			JOptionPane.showMessageDialog(DataControll.this, "添加失败", "提示", JOptionPane.ERROR_MESSAGE);
	    			
	    		}
			}
		});
		insertButton.setFont(new Font("宋体", Font.PLAIN, 20));
		
		JButton deleteButton = new JButton("删除选中行");
		deleteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				Student student = new Student(table.getValueAt(selectedRow, 0).toString(), table.getValueAt(selectedRow, 1).toString(), table.getValueAt(selectedRow, 2).toString(), table.getValueAt(selectedRow, 3).toString());
				controller.delete(student.getSno(), student.getSname(), student.getSsex(), student.getSclass());
				dtm.removeRow(selectedRow);
				
			}
		});
		deleteButton.setFont(new Font("宋体", Font.PLAIN, 20));
		
		//原table位置
		JLabel lblNewLabel = new JLabel("双击选中单元格修改数据。");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		
		JButton exitButton = new JButton("退出");
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dataControll.dispose();
				new PhotoMainView().setVisible(true);
			}
		});
		exitButton.setFont(new Font("宋体", Font.PLAIN, 20));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(35)
							.addComponent(wayLabel)
							.addGap(41)
							.addComponent(classLabel, GroupLayout.PREFERRED_SIZE, 392, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(snoLabel)
							.addGap(11)
							.addComponent(snoField, GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 468, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(10)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(snameLabel)
											.addGap(10)
											.addComponent(snameField, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(ssexLabel)
											.addGap(10)
											.addComponent(ssexField, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(sclassLabel)
											.addGap(10)
											.addComponent(sclassField, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(70)
											.addComponent(insertButton, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
											.addGap(61))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(70)
											.addComponent(deleteButton, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
											.addGap(61))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED))))
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
									.addGap(80)
									.addComponent(exitButton, GroupLayout.PREFERRED_SIZE, 72, Short.MAX_VALUE)
									.addGap(61)))))
					.addGap(9))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(13)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(wayLabel)
								.addComponent(classLabel))
							.addGap(7)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(3)
									.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(3)
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(button)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(45)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(snoField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(snoLabel))))
					.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 456, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(3)
									.addComponent(snameLabel))
								.addComponent(snameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(10)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(3)
									.addComponent(ssexLabel))
								.addComponent(ssexField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(10)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(3)
									.addComponent(sclassLabel))
								.addComponent(sclassField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(insertButton)
							.addGap(18)
							.addComponent(deleteButton)
							.addGap(18)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(exitButton, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
