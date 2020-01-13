package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.UserController;
import domain.Admin;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class LoginView extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	static String currentAccount;
	private JTextField accountField;
	private JComboBox comboBox;
	LoginView loginView;
	static String account;
	private UserController userController = new UserController();
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					loginView = new LoginView();
//					loginView.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public LoginView() {
		loginView = this;
		setTitle("登录");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel titleLabel = new JLabel("学生管理系统登录界面");
		titleLabel.setFont(new Font("华文行楷", Font.ITALIC, 20));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel accountLabel = new JLabel("账户：");
		accountLabel.setFont(new Font("宋体", Font.PLAIN, 15));
		
		JLabel passwordLabel = new JLabel("密码：");
		passwordLabel.setFont(new Font("宋体", Font.PLAIN, 15));
		
		accountField = new JTextField();
		accountField.setFont(new Font("宋体", Font.PLAIN, 15));
		accountField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("宋体", Font.PLAIN, 15));
		
		comboBox = new JComboBox();

		comboBox.setModel(new DefaultComboBoxModel(new String[] {"系统管理员", "用户"}));
		comboBox.setFont(new Font("宋体", Font.PLAIN, 15));
		
		JButton okButton = new JButton("确认");
		okButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String username = accountField.getText();;
				String password = String.valueOf(passwordField.getPassword());
				int count =  userController.login(username, password);
				if((currentAccount = comboBox.getSelectedItem().toString()).equals("系统管理员"));
				System.out.println(comboBox.getSelectedItem().toString());
				if (accountField.getText().toString().isEmpty()) {
					JOptionPane.showMessageDialog(LoginView.this, "账号不能为空", "提示", JOptionPane.INFORMATION_MESSAGE);
				}else if (String.valueOf(passwordField.getPassword()).isBlank()) {
					JOptionPane.showMessageDialog(LoginView.this, "密码不能为空", "提示", JOptionPane.INFORMATION_MESSAGE);
				}else if (comboBox.getSelectedItem().toString().equals("系统管理员")&&(!accountField.getText().toString().equals(Admin.account)||!String.valueOf(passwordField.getPassword()).equals(Admin.password))) {
					JOptionPane.showMessageDialog(LoginView.this, "管理员账户或密码错误（或为空）", "提示", JOptionPane.INFORMATION_MESSAGE);
				}else if (comboBox.getSelectedItem().toString().equals("系统管理员")&&(accountField.getText().toString().equals(Admin.account))&&(String.valueOf(passwordField.getPassword()).equals(Admin.password))){
					JOptionPane.showMessageDialog(LoginView.this, "管理员账户登陆成功", "提示", JOptionPane.INFORMATION_MESSAGE);
					account = accountField.getText();
					loginView.dispose();
					new PhotoMainView().setVisible(true);
				}else if (currentAccount.equals("用户")&&(count > 0)) {
					JOptionPane.showMessageDialog(LoginView.this, "用户账户登陆成功", "提示", JOptionPane.INFORMATION_MESSAGE);
					account = accountField.getText();
					loginView.dispose();
					new PhotoMainView().setVisible(true);
				}else {
					System.out.println(count + " " + account);
					JOptionPane.showMessageDialog(LoginView.this, "用户账户或密码错误", "提示", JOptionPane.INFORMATION_MESSAGE);
					
				}
			}
		});
		
		JButton cancelButton = new JButton("退出");
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(111)
							.addComponent(titleLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(30))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(68)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(accountLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(accountField, GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(passwordLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(168)
							.addComponent(comboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(68)))
					.addGap(83))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(152)
					.addComponent(okButton, GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(cancelButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(140))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(28)
					.addComponent(titleLabel)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(accountLabel)
						.addComponent(accountField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordLabel)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18, 18, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(okButton)
						.addComponent(cancelButton))
					.addGap(34))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
