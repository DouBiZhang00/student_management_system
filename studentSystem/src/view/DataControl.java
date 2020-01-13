package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import domain.Student;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DataControl extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DataControl frame = new DataControl("数据操作");
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
	public DataControl() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		String[] columnNames = {"学号", "姓名", "性别", "班级"};
		Student rowDatas[] = {};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		final DefaultTableModel dtm = new DefaultTableModel(null,columnNames);//操作表格中的数据类
		final JTable table = new JTable(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"\u5B66\u53F7", "\u59D3\u540D", "\u6027\u522B", "\u73ED\u7EA7"
			}
		));//表格
		 // 设置表格内容颜色
        table.setForeground(Color.BLACK);                   // 字体颜色
        table.setFont(new Font(null, Font.PLAIN, 14));      // 字体样式
//        table.setSelectionForeground(Color.DARK_GRAY);      // 选中后字体颜色
        table.setSelectionBackground(Color.LIGHT_GRAY);     // 选中后字体背景
        table.setGridColor(Color.GRAY);                     // 网格颜色
        
     // 设置表头
        table.getTableHeader().setFont(new Font(null, Font.BOLD, 14));  // 设置表头名称字体样式
        table.getTableHeader().setForeground(Color.RED);                // 设置表头名称字体颜色
        table.getTableHeader().setResizingAllowed(false);               // 设置不允许手动改变列宽
        table.getTableHeader().setReorderingAllowed(false);             // 设置不允许拖动重新排序各列
        
        table.setRowHeight(30);// 设置行高
        
        // 设置滚动面板视口大小（超过该大小的行数据，需要拖动滚动条才能看到）
        table.setPreferredScrollableViewportSize(new Dimension(400, 300));
     // 把 表格 放到 滚动面板 中（表头将自动添加到滚动面板顶部）
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 100, 480, 450);
        contentPane.add(scrollPane);
        
        JButton btnNewButton = new JButton("New button");
        btnNewButton.setBounds(149, 23, 93, 23);
        contentPane.add(btnNewButton);
        
        final TableModel tableModel = table.getModel();// 获取 表格模型
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
                    	Object snoObj = tableModel.getValueAt(row, 0);
                    	Object snameObj = tableModel.getValueAt(row, 1);
                        Object ssexObj = tableModel.getValueAt(row, 2);
                        Object sclassObj = tableModel.getValueAt(row, 3);
                    }
                }
            }
        });
	}
	public DataControl(String title) {
			
			this();
			this.setTitle(title);
		}
	}
