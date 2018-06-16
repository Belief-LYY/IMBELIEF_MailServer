package com.belief.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Base64;
import java.util.Calendar;

import javax.net.ssl.SSLSocketFactory;

/**
 * 
 * @author KING-ERIC
 * 
 */
public class SmtpSend extends TransportModel {
	// 静态内部类，在该内部类中创建单例对象，再将该单例对象通过getInstance()方法返回给外部使用
	private static class InternalClass {
		private final static SmtpSend smtpSend = new SmtpSend();
	}

	// 公有静态成员方法，返回唯一实例
	public static synchronized SmtpSend getSmtpSend() {
		return InternalClass.smtpSend;
	}

	// 公有静态成员方法，返回唯一实例
	public static synchronized SmtpSend getSmtpSend(String Host, int Port) {
		InternalClass.smtpSend.SmtpHost = Host;
		InternalClass.smtpSend.SmtpPort = Port;
		return InternalClass.smtpSend;
	}

	// 测试Main函数
	public static void main(String[] args) {
		SmtpSend aSmtpSend = SmtpSend.getSmtpSend();
		aSmtpSend.setSmtpHost("127.0.0.1");
		aSmtpSend.setSmtpPort(25);
		aSmtpSend.setUsername("296293760@imbelief.com.cn");
		aSmtpSend.setPassword("vzujaubksmhvbhc");
		aSmtpSend.Login();

		aSmtpSend.setEmailAddr("296293760@imbelief.com.cn");
		aSmtpSend.setReceivers("891079011@qq.com", "296293760@imbelief.com.cn");
		aSmtpSend.setSubject("Just a Test");
		aSmtpSend.setContent("This email from command line. Cross the GFW, we can reach every corner in the world !");
		aSmtpSend.SendMail();

		aSmtpSend.Quit();
	}

	// 内容
	private String Content = null;
	// 发件人地址
	private String EmailAddr = null;// 296293760@qq.com

	// 收件人地址
	private String[] Receivers = null;// "891079011@qq.com"

	// 邮件服务器域名地址
	private String SmtpHost = null;// "smtp.qq.com"

	// 邮件服务器端口，正常是25，腾讯是587
	private int SmtpPort;

	// 主题
	private String Subject = null;

	// 私有构造函数
	private SmtpSend() {

	}

	public String getContent() {
		return Content;
	}

	public String getEmailAddr() {
		return EmailAddr;
	}

	public String[] getReceivers() {
		return Receivers;
	}

	private String GetResponse() {
		String aString = null;
		try {
			aString = aInputStream.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("R: " + aString);
		return aString;
	}

	public String getSmtpHost() {
		return SmtpHost;
	}

	public int getSmtpPort() {
		return SmtpPort;
	}

	public String getSubject() {
		return Subject;
	}

	private boolean isOK() {
		String Response = null;
		try {
			Response = GetResponse();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (Response != null && (Response.startsWith("2") || Response.startsWith("3"))) {
			// 211 系统状态或系统帮助响应
			// 214 帮助信息
			// 220 <domain> 服务就绪
			// 221 <domain> 服务关闭传输信道
			// 250 要求的邮件操作完成
			// 251 用户非本地，将转发向<forward-path>
			// 354 开始邮件输入，以<CRLF>.<CRLF>结束
			return true;
		}
		try {
			// throw new Exception("发送消息出错...");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 421 <domain> 服务未就绪，关闭传输信道（当必须关闭时，此应答可以作为对任何命令的响应）
		// 450 要求的邮件操作未完成，邮箱不可用（例如，邮箱忙）
		// 451 放弃要求的操作；处理过程中出错
		// 452 系统存储不足，要求的操作未执行
		// 500 格式错误，命令不可识别（此错误也包括命令行过长）
		// 501 参数格式错误
		// 502 命令不可实现
		// 503 错误的命令序列
		// 504 命令参数不可实现
		// 550 要求的邮件操作未完成，邮箱不可用（例如，邮箱未找到，或不可访问）Connection frequency limited
		// 551 用户非本地，请尝试<forward-path>
		// 552 过量的存储分配，要求的操作未执行
		// 553 邮箱名不可用，要求的操作未执行（例如邮箱格式错误）
		// 554 操作失败
		return false;
	}

	public synchronized boolean Login() {
		if (SmtpHost == null || SmtpPort == 0 || Username == null || Password == null) {
			try {
				throw new Exception("数据未初始化...");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return isConnected;
			}
		}
		if (!isConnected) {
			try {
				if (SmtpPort == 25) {
					iSocket = new Socket(SmtpHost, SmtpPort);
				} else
					iSocket = SSLSocketFactory.getDefault().createSocket(SmtpHost, SmtpPort);
				aOutputStream = new BufferedWriter(new OutputStreamWriter(iSocket.getOutputStream()));
				aInputStream = new BufferedReader(new InputStreamReader(iSocket.getInputStream()));

				isOK();

				// HELO - 打开(确认发送)
				System.out.println("<------- HELLO ------->");
				Send("HELO " + SmtpHost + CRLF);

				// STARTTLS
				System.out.println("<------- STARTTLS ------->");
				Send("STARTTLS " + SmtpHost + CRLF);

				// 账户验证
				System.out.println("<------- 请求登陆 ------->");
				Send("AUTH LOGIN" + CRLF);

				System.out.println("<------- 用户名 ------->");
				Send(new String(Base64.getEncoder().encode(Username.getBytes())) + CRLF);

				System.out.println("<------- 登录授权码 ------->");
				Send(new String(Base64.getEncoder().encode(Password.getBytes())) + CRLF);

			} catch (UnknownHostException e) {
				e.printStackTrace();
				return isConnected;
			} catch (IOException e) {
				e.printStackTrace();
				return isConnected;
			} catch (Exception e) {
				e.printStackTrace();
				return isConnected;
			}
			isConnected = true;
		}
		return isConnected;
	}

	public synchronized boolean Quit() {
		System.out.println("<------- 退出 ------->");
		Send("QUIT" + CRLF);
		isConnected = false;
		SmtpHost = null;
		SmtpPort = 0;
		Username = null;
		Password = null;
		if (aInputStream != null) {
			try {
				aInputStream.close();
			} catch (IOException e) {
			}
		}
		if (aOutputStream != null) {
			try {
				aOutputStream.close();
			} catch (IOException e) {
			}
		}
		if (iSocket != null) {
			try {
				iSocket.close();
			} catch (IOException e) {
			}
		}
		return isConnected;
	}

	private boolean Send(String Command) {
		try {
			aOutputStream.write(Command);
			// flush冲一下
			aOutputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.print("S: " + Command);
		return isOK();
	}

	// 邮件发送方法
	public synchronized boolean SendMail() {
		boolean Reasult = false;
		if (!isConnected || Subject == null || EmailAddr == null || Receivers == null || Content == null) {
			try {
				throw new Exception("数据未初始化...");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return Reasult;
			}
		}
		try {
			// 设置发件人
			System.out.println("<------- 设置发件人 ------->");
			Send("MAIL FROM:<" + EmailAddr + ">" + CRLF);

			// 设置收件人
			System.out.println("<------- 设置收件人 ------->");
			for (String Receiver : Receivers) {
				Send("RCPT TO:<" + Receiver + ">" + CRLF);
			}

			// 请求发送数据
			System.out.println("<------- 请求发送信件 ------->");
			Send("DATA" + CRLF);

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("From:<" + EmailAddr + ">" + CRLF);
			for (String Receiver : Receivers) {
				stringBuilder.append("To:<" + Receiver + ">" + CRLF);
			}
			stringBuilder.append("Subject:" + Subject + CRLF);
			stringBuilder.append("Date:" + Calendar.getInstance().getTime().toString() + CRLF);
			stringBuilder.append("Content-Type:text/plain;charset=\"GB2312\"" + CRLF);
			stringBuilder.append(CRLF);
			stringBuilder.append(Content);
			stringBuilder.append(CRLF + "." + CRLF);
			System.out.println("<------- 发送信件 ------->");
			Reasult = Send(stringBuilder.toString());

			System.out.println("=========================>");
			Subject = null;
			EmailAddr = null;
			for (int i = 0; i < Receivers.length; i++) {
				Receivers[i] = null;
			}
			Receivers = null;
			Content = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Reasult;
	}

	public void setContent(String content) {
		Content = content;
	}

	public void setEmailAddr(String emailAddr) {
		EmailAddr = emailAddr;
	}

	public void setReceivers(String... receivers) {
		Receivers = receivers;
	}

	public void setSmtpHost(String smtpHost) {
		SmtpHost = smtpHost;
	}

	public void setSmtpPort(int smtpPort) {
		SmtpPort = smtpPort;
	}

	public void setSubject(String subject) {
		Subject = subject;
	}
}
