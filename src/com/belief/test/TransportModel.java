package com.belief.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.Socket;

public class TransportModel {
	// 换行符
	protected final static String CRLF = "\r\n";
	// 用户名
	protected String Username = null;
	// 密码：授权码
	protected String Password = null;
	// iSocket套接字
	protected Socket iSocket = null;
	// 输入流
	protected BufferedReader aInputStream = null;
	// 输出流
	protected BufferedWriter aOutputStream = null;
	// 是否已登陆
	protected boolean isConnected = false;

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}
}
