package com.belief.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Date;

import com.belief.connection.Connection;
import com.belief.listener.PEmailListener;
import com.belief.listener.PLoginListener;

public class POP3Server {
	// 静态内部类，在该内部类中创建单例对象，再将该单例对象通过getInstance()方法返回给外部使用
	private static class InternalClass {
		private final static POP3Server aPOP3Server = new POP3Server();
	}

	private static ServerSocket aServerSocket;

	// 公有静态成员方法，返回唯一实例
	public static synchronized POP3Server getPOP3Server() {
		return InternalClass.aPOP3Server;
	}

	public static void main(String[] args) {
		POP3Server aPOP3Server = new POP3Server();
		aPOP3Server.StartServer();
	}

	// 私有构造函数
	private POP3Server() {
		try {
			aServerSocket = new ServerSocket(110);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized void StartServer() {
		System.out.println("---POP3服务器启动---" + new Date().toString());
		new Thread() {
			@SuppressWarnings("unused")
			@Override
			public void run() {
				while (true) {
					Connection conn = null;
					try {
						conn = new Connection(aServerSocket.accept());
						conn.AddMsgListener(new PLoginListener(conn));
						conn.AddMsgListener(new PEmailListener(conn));
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
