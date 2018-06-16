package com.belief.face;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import com.belief.Impl.userDaoImpl;
import com.belief.model.User;
import com.belief.server.POP3Server;
import com.belief.server.SmtpServer;

public class IndexFace implements ActionListener {

	private static final int DEFAULT_HEIGHT = 500;

	// 设置窗体默认位置和大小
	private static final int DEFAULT_WIDTH = 400;
	private static final int DEFAULT_X = 250;
	private static final int DEFAULT_Y = 100;
	public static void main(String[] args) {
		/*
		 * 所有的Swing组件必须由事件分派线程(event dispatch thread)进行配置，
		 * 线程将鼠标点击和按钮控制转移到用户接口组件。下面的代码片段是事件分派线程中的执行代码。 现在，只需要将其看成是启动一个Swing程序的神器代码。
		 */
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				new IndexFace();
			}
		});
	}
	JButton ButtonAdd = new JButton(" 添加 ");
	JButton ButtonChange = new JButton(" 修改 ");
	JButton ButtonClear = new JButton(" 清空 ");
	JButton ButtonDelete = new JButton(" 删除 ");
	JButton ButtonGPOP3 = new JButton(" 启动POP3协议 ");
	JButton ButtonGSMTP = new JButton(" 启动SMTP协议 ");

	// 四个按钮，登录，重置，退出
	JButton ButtonRefesh = new JButton(" 刷新IP ");
	JButton ButtonRefreshTable = new JButton(" 刷新表单 ");
	JButton ButtonRun = new JButton(" 运行服务器 ");
	JButton ButtonSearch = new JButton(" 查询 ");

	/**
	 * Author:KING-ERIC Date:2018-3-31 23:00
	 */

	// 定义界面元素************************

	// 定义主窗体，顶层容器
	JFrame Frame = new JFrame("MailServer-信仰");
	// 七个文本标签(JLabel可以显示文本、图像或同时显示二者)
	JLabel InfoLabel1 = new JLabel("欢迎使用邮件服务器，当前版本 Version 5.0");

	JLabel InfoLabel2 = new JLabel("当前主机名：KING-ERIC        服务器当前IP地址：127.0.0.1");
	JLabel InfoLabel3 = new JLabel("SMTP协议状态：  协议已停止");

	JLabel InfoLabel4 = new JLabel("POP3协议状态：  协议已停止");
	JLabel JLabel1 = new JLabel("邮   箱");
	JLabel JLabel2 = new JLabel("密   码");
	JLabel JLabel3 = new JLabel("姓   名");

	JLabel JLabel4 = new JLabel("请输入要查询的用户：");
	// 十九个面板容器(JPanel中间容器)，Swing组件不能直接添加到顶层容器中，它必须添加到一个与Swing顶层容器相关联的内容面板（Content
	// Pane）上
	JPanel JPanel1 = new JPanel();
	JPanel JPanel10 = new JPanel();
	JPanel JPanel11 = new JPanel();
	JPanel JPanel12 = new JPanel();
	JPanel JPanel13 = new JPanel();

	JPanel JPanel14 = new JPanel();
	JPanel JPanel15 = new JPanel();
	JPanel JPanel16 = new JPanel();
	JPanel JPanel17 = new JPanel();
	JPanel JPanel18 = new JPanel();
	JPanel JPanel19 = new JPanel();
	JPanel JPanel2 = new JPanel();

	JPanel JPanel20 = new JPanel();
	JPanel JPanel21 = new JPanel();
	JPanel JPanel22 = new JPanel();
	JPanel JPanel23 = new JPanel();
	JPanel JPanel24 = new JPanel();
	JPanel JPanel25 = new JPanel();
	JPanel JPanel26 = new JPanel();
	JPanel JPanel27 = new JPanel();
	JPanel JPanel28 = new JPanel();
	JPanel JPanel29 = new JPanel();
	JPanel JPanel3 = new JPanel();
	JPanel JPanel4 = new JPanel();
	JPanel JPanel5 = new JPanel();
	JPanel JPanel6 = new JPanel();
	JPanel JPanel7 = new JPanel();
	JPanel JPanel8 = new JPanel();
	JPanel JPanel9 = new JPanel();
	JTextField JTextField1 = new JTextField(10);
	JTextField JTextField2 = new JTextField(10);
	JTextField JTextField3 = new JTextField(10);
	JTextField JTextField4 = new JTextField(10);
	JTextField JTextField5 = new JTextField(10);
	JTextField JTextField6 = new JTextField(10);
	JTextField JTextField7 = new JTextField(10);
	JTextPane JTextPane1 = new JTextPane();
	JTextPane JTextPane2 = new JTextPane();
	JTextPane JTextPane3 = new JTextPane();
	JTextPane JTextPane4 = new JTextPane();
	JTextPane JTextPane5 = new JTextPane();

	JTextPane JTextPane6 = new JTextPane();
	JMenu Menu1 = new JMenu(" 选项 ");
	JMenu Menu2 = new JMenu(" 帮助 ");
	// 一个菜单项
	JMenuBar MenuBar1 = new JMenuBar();
	JMenuItem MenuItem1 = new JMenuItem("退出");
	JMenuItem MenuItem2 = new JMenuItem("关于");

	JMenuItem MenuItem3 = new JMenuItem("帮助");

	// 两个自动水平和/或垂直滚动的容器类
	JScrollPane ScrollPane1 = new JScrollPane();
	JScrollPane ScrollPane2 = new JScrollPane();
	// 来一个JTabbedPane容器
	JTabbedPane TabbedPane = new JTabbedPane();
	// 两个JTable
	JTable Table1 = null;

	JTable Table2 = null;

	public IndexFace() {
		super();

		// 加入菜单项
		Menu1.add(MenuItem1);
		Menu1.add(MenuItem2);
		Menu2.add(MenuItem3);
		MenuBar1.add(Menu1);
		MenuBar1.add(Menu2);
		Frame.setJMenuBar(MenuBar1);

		// 设置窗体大小，宽400px，高500px，初始位置250，100
		Frame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		Frame.setLocation(DEFAULT_X, DEFAULT_Y);
		// Frame.pack();

		Frame.getContentPane().add(TabbedPane, BorderLayout.CENTER);

		// 四个标签页，每页算一个JPanel
		TabbedPane.addTab(" 控制面板 ", JPanel1);
		TabbedPane.addTab(" 用户管理 ", JPanel2);
		TabbedPane.addTab(" 日志管理 ", JPanel3);
		TabbedPane.addTab(" 系统设置 ", JPanel4);

		// 第一页放三个JPanel
		JPanel1.add(JPanel5);
		JPanel1.add(JPanel6);
		JPanel1.add(JPanel7);
		JPanel1.setLayout(new GridLayout(3, 1));
		JPanel5.setBorder(BorderFactory.createTitledBorder(null, "IP地址", 1, 0, null, null));
		JPanel6.setBorder(BorderFactory.createTitledBorder(null, "STMP协议", 1, 0, null, null));
		JPanel7.setBorder(BorderFactory.createTitledBorder(null, "POP3协议", 1, 0, null, null));

		// 第二页放两个JPanel
		JPanel2.add(JPanel8);
		JPanel2.add(JPanel9);
		JPanel2.setLayout(new GridLayout(2, 1));
		JPanel8.setBorder(BorderFactory.createTitledBorder(null, "用户列表", 1, 0, null, null));
		JPanel9.setBorder(BorderFactory.createTitledBorder(null, "搜索用户", 1, 0, null, null));
		JPanel10.setBorder(BorderFactory.createTitledBorder(null, "操作", 1, 0, null, null));

		// 第三页放两个JPanel
		JPanel3.add(JPanel11);
		JPanel3.add(JPanel12);
		JPanel3.setLayout(new GridLayout(2, 1));
		JPanel11.setBorder(BorderFactory.createTitledBorder(null, "系统日志列表", 1, 0, null, null));
		JPanel12.setBorder(BorderFactory.createTitledBorder(null, "检索日志", 1, 0, null, null));

		// 第四页放三个JPanel
		JPanel4.add(JPanel13);
		JPanel4.add(JPanel14);
		JPanel4.add(JPanel15);
		JPanel4.setLayout(new GridLayout(3, 1));
		JPanel13.setBorder(BorderFactory.createTitledBorder(null, "辅助工具", 1, 0, null, null));
		JPanel14.setBorder(BorderFactory.createTitledBorder(null, "扩展功能", 1, 0, null, null));
		JPanel15.setBorder(BorderFactory.createTitledBorder(null, "用户推送", 1, 0, null, null));

		// 第一页**********************************************************************************
		// 第一页第一个JPanel
		JPanel5.add(InfoLabel1);
		JPanel5.add(InfoLabel2);
		JPanel16.add(ButtonRefesh);
		JPanel16.add(new JLabel("                                    "));
		JPanel16.add(ButtonRun);
		JPanel5.add(JPanel16);
		JPanel5.setLayout(new GridLayout(3, 1));

		// 第一页第二个JPanel
		JPanel6.add(InfoLabel3);
		JPanel6.add(ButtonGSMTP);

		// 第一页第三个JPanel
		JPanel7.add(InfoLabel4);
		JPanel7.add(ButtonGPOP3);

		// 第二页第一个JPanel****************

		// 左边的JPanel
		JPanel8.add(JPanel10);
		JPanel10.setLayout(new GridLayout(5, 1));
		JPanel10.add(JPanel17);
		JPanel10.add(JPanel18);
		JPanel10.add(JPanel19);
		JPanel10.add(JPanel20);
		JPanel10.add(JPanel21);

		JPanel17.add(JLabel1);
		JPanel17.add(JTextField1);

		JPanel18.add(JLabel2);
		JPanel18.add(JTextField2);

		JPanel19.add(JLabel3);
		JPanel19.add(JTextField3);

		JPanel20.add(ButtonAdd);
		JPanel20.add(ButtonChange);

		JPanel21.add(ButtonDelete);
		JPanel21.add(ButtonClear);

		// 右边的JTable
		JPanel8.setLayout(new GridLayout(1, 2));
		refeshtable();

		// 第二页第二个JPanel****************
		JPanel9.setLayout(new GridLayout(4, 1));
		JPanel9.add(JPanel22);
		JPanel9.add(JPanel23);
		JPanel9.add(JPanel24);
		JPanel9.add(JPanel25);
		JPanel23.add(JLabel4);
		JPanel23.add(JTextField4);
		JPanel24.add(ButtonSearch);
		JPanel24.add(new JLabel("                          "));
		JPanel24.add(ButtonRefreshTable);

		// 第三页JPanel11、12.......
		JPanel11.add(new JLabel("此功能尚未开通！"));
		JPanel12.add(new JLabel("此功能尚未开通！"));
		JPanel13.add(new JLabel("此功能尚未开通！"));
		JPanel14.add(new JLabel("此功能尚未开通！"));
		JPanel15.add(new JLabel("此功能尚未开通！"));

		// 设置关闭窗口时，退出程序，定义一个用户关闭这个框架时的响应动作
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 显示窗体，不设置此项时窗体会一闪而过，为了框架显示，需要调用框架的setVisible方法
		Frame.setVisible(true);

		// 设置窗体可以自由改变大小
		Frame.setResizable(true);

		// 设置五个按钮的事件监听
		MenuItem1.addActionListener(this);
		MenuItem2.addActionListener(this);
		MenuItem3.addActionListener(this);
		ButtonRefesh.addActionListener(this);
		ButtonRun.addActionListener(this);
		ButtonGSMTP.addActionListener(this);
		ButtonGPOP3.addActionListener(this);
		ButtonAdd.addActionListener(this);
		ButtonDelete.addActionListener(this);
		ButtonChange.addActionListener(this);
		ButtonClear.addActionListener(this);
		ButtonSearch.addActionListener(this);
		ButtonRefreshTable.addActionListener(this);

		try {
			InetAddress localhost = InetAddress.getLocalHost();
			if (localhost != null)
				InfoLabel2.setText(
						"当前主机名：" + localhost.getHostName() + "        服务器当前IP地址：" + localhost.getHostAddress());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void about() {
		String Content = "*    Author:KING-ERIC\n* Date:2018-3-31 23:00\n    版权所有,翻版必究！";
		JOptionPane.showMessageDialog(null, Content, "关于我们", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getActionCommand().equals("帮助")) {
			help();
		} else if (arg0.getActionCommand().equals("退出")) {
			System.exit(0);
		} else if (arg0.getActionCommand().equals("关于")) {
			about();
		} else if (arg0.getActionCommand().equals(" 刷新IP ")) {
			refeship();
		} else if (arg0.getActionCommand().equals(" 运行服务器 ")) {
			runserver();
		} else if (arg0.getActionCommand().equals(" 启动SMTP协议 ")) {
			startsmtp();
		} else if (arg0.getActionCommand().equals(" 启动POP3协议 ")) {
			startpop3();
		} else if (arg0.getActionCommand().equals(" 添加 ")) {
			add();
		} else if (arg0.getActionCommand().equals(" 删除 ")) {
			delete();
		} else if (arg0.getActionCommand().equals(" 修改 ")) {
			modify();
		} else if (arg0.getActionCommand().equals(" 清空 ")) {
			clear();
		} else if (arg0.getActionCommand().equals(" 查询 ")) {
			query();
		} else if (arg0.getActionCommand().equals(" 刷新表单 ")) {
			refeshtable();
		}
	}

	public void add() {
		String email = JTextField1.getText();
		String password = JTextField2.getText();
		String name = JTextField3.getText();
		if (email == null || email.equals("")) {
			JOptionPane.showMessageDialog(null, "请输入用户邮箱！", "提示消息", JOptionPane.WARNING_MESSAGE);
		} else if (password == null || password.equals("")) {
			JOptionPane.showMessageDialog(null, "请输入密码！", "提示消息", JOptionPane.WARNING_MESSAGE);
		} else if (name == null || name.equals("")) {
			JOptionPane.showMessageDialog(null, "请输入姓名！", "提示消息", JOptionPane.WARNING_MESSAGE);
		} else {
			User iuser = new userDaoImpl().QueryByEmail(email);
			if (iuser == null) {
				User user = new User();
				user.setMail_address(email);
				user.setNick_name(name);
				user.setUser_password(password);
				new userDaoImpl().save(user);
				JOptionPane.showMessageDialog(null, "添加成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
				refeshtable();
			} else {
				JOptionPane.showMessageDialog(null, "用户已经存在，添加失败！", "提示", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	public void clear() {
		JTextField1.setText("");
		JTextField2.setText("");
		JTextField3.setText("");
	}

	public void delete() {
		String email = JTextField1.getText();
		if (email == null || email.equals("")) {
			JOptionPane.showMessageDialog(null, "请输入用户邮箱！", "提示消息", JOptionPane.WARNING_MESSAGE);
		} else {
			User iuser = new userDaoImpl().QueryByEmail(email);
			if (iuser != null) {
				User user = new User();
				user.setMail_address(email);
				new userDaoImpl().delete(user);
				JOptionPane.showMessageDialog(null, "删除成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
				refeshtable();
			} else {
				JOptionPane.showMessageDialog(null, "用户不存在，删除失败！", "提示", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	public void help() {
		String Content = "https://blog.csdn.net/lyy296293760";
		JOptionPane.showMessageDialog(null, Content, "帮助", JOptionPane.INFORMATION_MESSAGE);
	}

	public void modify() {
		String email = JTextField1.getText();
		String password = JTextField2.getText();
		String name = JTextField3.getText();
		if (email == null || email.equals("")) {
			JOptionPane.showMessageDialog(null, "请输入用户邮箱！", "提示消息", JOptionPane.WARNING_MESSAGE);
		} else if (password == null || password.equals("")) {
			JOptionPane.showMessageDialog(null, "请输入密码！", "提示消息", JOptionPane.WARNING_MESSAGE);
		} else if (name == null || name.equals("")) {
			JOptionPane.showMessageDialog(null, "请输入姓名！", "提示消息", JOptionPane.WARNING_MESSAGE);
		} else {
			User iuser = new userDaoImpl().QueryByEmail(email);
			if (iuser != null) {
				User user = new User();
				user.setMail_address(email);
				user.setNick_name(name);
				user.setUser_password(password);
				new userDaoImpl().update(user);
				JOptionPane.showMessageDialog(null, "修改成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
				refeshtable();
			} else {
				JOptionPane.showMessageDialog(null, "用户不存在，请检查用户邮箱！", "提示", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	public void query() {
		String email = JTextField4.getText();
		User iuser = new userDaoImpl().QueryByEmail(email);
		if (iuser != null)
			JOptionPane.showMessageDialog(null, iuser, "用户信息", JOptionPane.INFORMATION_MESSAGE);
		else
			JOptionPane.showMessageDialog(null, "请输入要查询的用户邮箱！", "提示", JOptionPane.INFORMATION_MESSAGE);
	}

	public void refeship() {
		try {
			InetAddress localhost = InetAddress.getLocalHost();
			if (localhost != null)
				InfoLabel2.setText(
						"当前主机名：" + localhost.getHostName() + "        服务器当前IP地址：" + localhost.getHostAddress());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void refeshtable() {
		this.clear();
		ArrayList<User> Users = new userDaoImpl().QueryAllUser();
		Object[][] Data = new Object[Users.size()][3];
		for (int i = 0; i < Users.size(); i++) {
			Data[i][0] = Users.get(i).getNick_name();
			Data[i][1] = Users.get(i).getMail_address();
			Data[i][2] = Users.get(i).getUser_password();
		}
		Table1 = new JTable(Data, new String[] { "姓名", "邮箱", "密码" });
		// 给table加上一个鼠标事件监听器对象
		Table1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(MouseEvent e) {// 仅当鼠标单击时响应
				// 得到选中的行列的索引值
				int r = Table1.getSelectedRow();
				// int c= Table1.getSelectedColumn();
				// 得到选中的单元格的值，表格中都是字符串
				JTextField1.setText(Table1.getValueAt(r, 1).toString());
				JTextField2.setText(Table1.getValueAt(r, 2).toString());
				JTextField3.setText(Table1.getValueAt(r, 0).toString());
				// Object value= Table1.getValueAt(r, c);
				// String info=r+"行"+c+"列值 : "+value.toString();
				// javax.swing.JOptionPane.showMessageDialog(null,info);
			}
		});
		JPanel8.remove(ScrollPane1);
		ScrollPane1 = new JScrollPane(Table1);
		JPanel8.add(ScrollPane1);
	}

	public void runserver() {
		String Content = "https://blog.csdn.net/lyy296293760";
		POP3Server.getPOP3Server().StartServer();
		SmtpServer.getSmtpServer().StartServer();
		InfoLabel3.setText("SMTP协议状态：  协议已开启");
		InfoLabel4.setText("POP3协议状态：  协议已开启");
		JOptionPane.showMessageDialog(null, Content, "帮助", JOptionPane.INFORMATION_MESSAGE);
	}

	public void startpop3() {
		String Content = "https://blog.csdn.net/lyy296293760";
		POP3Server.getPOP3Server().StartServer();
		InfoLabel4.setText("POP3协议状态：  协议已开启");
		JOptionPane.showMessageDialog(null, Content, "帮助", JOptionPane.INFORMATION_MESSAGE);
	}

	public void startsmtp() {
		String Content = "https://blog.csdn.net/lyy296293760";
		SmtpServer.getSmtpServer().StartServer();
		InfoLabel3.setText("SMTP协议状态：  协议已开启");
		JOptionPane.showMessageDialog(null, Content, "帮助", JOptionPane.INFORMATION_MESSAGE);
	}
}
