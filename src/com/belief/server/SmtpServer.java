package com.belief.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Date;

import com.belief.connection.Connection;
import com.belief.listener.SEmailListener;
import com.belief.listener.SLoginListener;

public class SmtpServer {
	// 静态内部类，在该内部类中创建单例对象，再将该单例对象通过getInstance()方法返回给外部使用
	private static class InternalClass {
		private final static SmtpServer smtpServer = new SmtpServer();
	}

	private static ServerSocket aServerSocket;

	// 公有静态成员方法，返回唯一实例
	public static SmtpServer getSmtpServer() {
		return InternalClass.smtpServer;
	}

	public static void main(String[] args) {
		SmtpServer aSmtpServer = new SmtpServer();
		aSmtpServer.StartServer();
	}

	// 私有构造函数
	private SmtpServer() {
		try {
			aServerSocket = new ServerSocket(25);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void StartServer() {
		System.out.println("---SMTP服务器启动---" + new Date().toString());
		new Thread() {
			@SuppressWarnings("unused")
			@Override
			public void run() {
				while (true) {
					Connection conn = null;
					try {
						conn = new Connection(aServerSocket.accept());
						conn.AddMsgListener(new SEmailListener(conn));
						conn.AddMsgListener(new SLoginListener(conn));
						conn.start();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						if (conn != null)
							conn.DisConnect();
					}
				}
			}
		}.start();
	}
}
