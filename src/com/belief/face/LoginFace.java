package com.belief.face;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.belief.service.AdminService;

public class LoginFace implements ActionListener {

	private static final int DEFAULT_COLUS = 1;

	private static final int DEFAULT_HEIGHT = 250;
	private static final int DEFAULT_ROWS = 4;

	// 设置窗体默认位置和大小
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_X = 520;
	private static final int DEFAULT_Y = 250;

	public static void main(String[] args) {
		/*
		 * 所有的Swing组件必须由事件分派线程(event dispatch thread)进行配置，
		 * 线程将鼠标点击和按钮控制转移到用户接口组件。下面的代码片段是事件分派线程中的执行代码。 现在，只需要将其看成是启动一个Swing程序的神器代码。
		 */
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				new LoginFace();
			}
		});
	}
	// 两个文本框，一个账号，一个密码
	JTextField Account = new JTextField(10);

	JButton ButtonExit = new JButton("退出");
	ButtonGroup ButtonGroupRole = new ButtonGroup();
	// 三个按钮，登录，重置，退出
	JButton ButtonLogin = new JButton("登录");

	JButton ButtonReset = new JButton("重置");
	/**
	 * Author:KING-ERIC Date:2018-3-31 23:00
	 */

	// 定义登陆界面元素************************

	// 定义主窗体，顶层容器
	JFrame Frame = new JFrame("MailServer-信仰");
	// 四个面板容器(JPanel中间容器)，Swing组件不能直接添加到顶层容器中，它必须添加到一个与Swing顶层容器相关联的内容面板（Content
	// Pane）上
	JPanel JPanel1 = new JPanel();
	JPanel JPanel2 = new JPanel();

	JPanel JPanel3 = new JPanel();
	JPanel JPanel4 = new JPanel();
	JMenu Menu = new JMenu("选项");
	// 一个菜单项
	JMenuBar MenuBar = new JMenuBar();

	JMenuItem MenuItem1 = new JMenuItem("退出");
	JMenuItem MenuItem2 = new JMenuItem("关于");
	JLabel passLabel = new JLabel("密    码：");
	JPasswordField passWord = new JPasswordField(10);
	// 两个单选框，一个用户，一个管理员，共同作为一个 ButtonGroup，去掉用户单选框
	// JRadioButton RadioButtonUser=new JRadioButton("用户",true);
	JRadioButton RadioButtonAdmin = new JRadioButton("管理员", true);
	JLabel roleLabel = new JLabel("角    色：");

	// 三个文本标签(JLabel可以显示文本、图像或同时显示二者)，用户名：，密码：，角色：
	JLabel userLabel = new JLabel("用户名：");

	public LoginFace() {
		super();

		// 将第一行内容：用户名和Account输入框置入中间容器Panel1
		JPanel1.add(userLabel);
		JPanel1.add(Account);

		// 将第二行内容：密码和passWord输入框置入中间容器Panel2
		JPanel2.add(passLabel);
		JPanel2.add(passWord);

		// 将第三行内容：RadioButtonUser和RadioButtonAdmin绑定同一个ButtonGroupRole
		// ButtonGroupRole.add(RadioButtonUser);
		ButtonGroupRole.add(RadioButtonAdmin);

		// 将第三行内容：角色和RadioButtonUser和RadioButtonAdmin置入中间容器Panel3
		JPanel3.add(roleLabel);
		// JPanel3.add(RadioButtonUser);
		JPanel3.add(RadioButtonAdmin);

		// 将第四行内容：三个Button置入中间容器Panel4
		JPanel4.add(ButtonLogin);
		JPanel4.add(ButtonReset);
		JPanel4.add(ButtonExit);

		// 将所有中间容器加入顶层容器JFrame
		Frame.add(JPanel1);
		Frame.add(JPanel2);
		Frame.add(JPanel3);
		Frame.add(JPanel4);

		// 加入菜单项
		Menu.add(MenuItem1);
		Menu.add(MenuItem2);
		MenuBar.add(Menu);
		Frame.setJMenuBar(MenuBar);

		// 设置布局管理器为网格布局，GridLayout(int rows, int cols) :创建具有指定行数和列数的网格布局。Rows为行数，Cols为列数
		Frame.setLayout(new GridLayout(DEFAULT_ROWS, DEFAULT_COLUS));

		// 设置窗体大小，长300px，宽250px，初始位置250，150
		Frame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		Frame.setLocation(DEFAULT_X, DEFAULT_Y);
		// Frame.pack();

		// 设置关闭窗口时，退出程序，定义一个用户关闭这个框架时的响应动作
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 显示窗体，不设置此项时窗体会一闪而过，为了框架显示，需要调用框架的setVisible方法
		Frame.setVisible(true);

		// 设置窗体可以自由改变大小
		Frame.setResizable(true);

		// 设置五个按钮的事件监听
		ButtonLogin.addActionListener(this);
		ButtonReset.addActionListener(this);
		ButtonExit.addActionListener(this);
		MenuItem1.addActionListener(this);
		MenuItem2.addActionListener(this);
	}

	public void about() {
		String Content = "*    Author:KING-ERIC\n* Date:2018-3-31 23:00\n    版权所有,翻版必究！";
		JOptionPane.showMessageDialog(null, Content, "关于我们", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getActionCommand().equals("登录")) {
			login();
		} else if (arg0.getActionCommand().equals("重置")) {
			clear();
		} else if (arg0.getActionCommand().equals("退出")) {
			System.exit(0);
		} else if (arg0.getActionCommand().equals("关于")) {
			about();
		}
	}

	public void clear() {
		Account.setText("");
		passWord.setText("");
	}

	public void login() {
		String account = Account.getText();
		String password = String.valueOf(passWord.getPassword());
		if (account == null || account.equals("")) {
			JOptionPane.showMessageDialog(null, "请输入用户名！", "提示消息", JOptionPane.WARNING_MESSAGE);
		} else if (password == null || password.equals("")) {
			JOptionPane.showMessageDialog(null, "请输入密码！", "提示消息", JOptionPane.WARNING_MESSAGE);
		} else {
			AdminService loginservice = new AdminService();
			if (loginservice.Login(account, password)) {
				JOptionPane.showMessageDialog(null, "登陆成功！", "提示消息", JOptionPane.INFORMATION_MESSAGE);

				// 清空输入框
				this.clear();

				// 关闭当前界面(Frame)
				Frame.dispose();

				// 创建一个新界面
				@SuppressWarnings("unused")
				IndexFace indexface = new IndexFace();
			} else {
				JOptionPane.showMessageDialog(null, "用户名或密码错误！", "提示消息", JOptionPane.INFORMATION_MESSAGE);
				// 清空输入框
				this.clear();
			}
		}
	}
}
