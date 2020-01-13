package view;

import java.awt.BorderLayout;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.UserController;
import domain.Admin;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainView extends JFrame {
	MainView mainView = this;
	private JPanel contentPane;
	UserController userController;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MainView frame = new MainView();
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
	
	public MainView() {
		setTitle("信息管理系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu settingMenu = new JMenu("设置");
		settingMenu.setIcon(new ImageIcon(MainView.class.getResource("/icon/设置.png")));
		menuBar.add(settingMenu);
		
		JMenuItem changeItem = new JMenuItem("修改密码");
		changeItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel changePanel = new JPanel();
				JPasswordField newPassword = new JPasswordField(5);
				JPasswordField confrimPassword = new JPasswordField(5);
				changePanel.add(new JLabel("新密码："));
				changePanel.add(newPassword);
				changePanel.add(Box.createHorizontalStrut(15));
				changePanel.add(new JLabel("确认密码："));
				changePanel.add(confrimPassword);
				int result = JOptionPane.showConfirmDialog(null, changePanel,
				        "修改密码", JOptionPane.OK_CANCEL_OPTION);
			    if (result == JOptionPane.OK_OPTION) {
			    	String password = String.valueOf(newPassword.getPassword());
//			    	System.out.println(LoginView.account);
			    	if(LoginView.currentAccount.equals("系统管理员")) {
			    		Admin.password = password;
			    	}else if (LoginView.currentAccount.equals("用户")) {
			    		userController = new UserController();
			    		int count = userController.updatePassword(LoginView.account, password);
			    		if(count > 0) {
			    			JOptionPane.showMessageDialog(MainView.this, "修改成功", "提示", JOptionPane.INFORMATION_MESSAGE);
			    		}else {
			    			JOptionPane.showMessageDialog(MainView.this, "修改失败", "提示", JOptionPane.ERROR_MESSAGE);
			    			
			    		}
			    	}
			    	System.out.println(Admin.password);
			    }
			}
		});
		
		changeItem.setIcon(new ImageIcon(MainView.class.getResource("/icon/修改密码.png")));
		settingMenu.add(changeItem);
		
		JMenuItem menuItem = new JMenuItem("增加账户");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(LoginView.currentAccount.equals("用户")) {
					JOptionPane.showMessageDialog(MainView.this, "非管理员不能添加用户", "提示", JOptionPane.INFORMATION_MESSAGE);
				}else {
					JPanel insertPanel = new JPanel();
					JTextField insertAccount = new JTextField(5);
					JPasswordField insertPassword = new JPasswordField(5);
					insertPanel.add(new JLabel("账户："));
					insertPanel.add(insertAccount);
					insertPanel.add(Box.createHorizontalStrut(15));
					insertPanel.add(new JLabel("密码："));
					insertPanel.add(insertPassword);
					int result = JOptionPane.showConfirmDialog(null, insertPanel,
					        "增加账户", JOptionPane.OK_CANCEL_OPTION);
				    if (result == JOptionPane.OK_OPTION) {
				    	String password = String.valueOf(insertPassword.getPassword());
				    	System.out.println(insertAccount.getText()+" "+ password);
				    	userController = new UserController();
				    	int count = userController.insert(insertAccount.getText(), password);
				    	if(count > 0) {
				    		JOptionPane.showMessageDialog(MainView.this, "添加成功", "提示", JOptionPane.INFORMATION_MESSAGE);
				    	}else {
				    		JOptionPane.showMessageDialog(MainView.this, "添加失败", "提示", JOptionPane.ERROR_MESSAGE);
				    	}
				    }
				}
			}
		});
		menuItem.setIcon(new ImageIcon(MainView.class.getResource("/icon/账户.png")));
		settingMenu.add(menuItem);
		
		JMenuItem exitItem = new JMenuItem("退出账户");
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainView.dispose();
				new LoginView().setVisible(true);
				
			}
		});
		exitItem.setIcon(new ImageIcon(MainView.class.getResource("/icon/退出.png")));
		settingMenu.add(exitItem);
		
		JMenu dataMenu = new JMenu("数据操作");
		dataMenu.setIcon(new ImageIcon(MainView.class.getResource("/icon/数据.png")));
		menuBar.add(dataMenu);
		
		JMenuItem updateItem = new JMenuItem("增删改查");
		updateItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainView.dispose();
				new DataControll().setVisible(true);
			}
		});
		updateItem.setIcon(new ImageIcon(MainView.class.getResource("/icon/修改.png")));
		dataMenu.add(updateItem);
		
		JButton btnNewButton = new JButton("关闭");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		
		menuBar.add(btnNewButton);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGap(0, 974, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGap(0, 513, Short.MAX_VALUE)
		);
		contentPane.setLayout(gl_contentPane);
	}
}
