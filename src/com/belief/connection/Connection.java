package com.belief.connection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.belief.model.MessageType;
import com.belief.model.User;

public class Connection extends Thread {
	// 监听器的接口定义
	public static interface MsgListener {
		// 监听到消息之后的响应方法
		public void onReceive(String aMessage);
	}

	// Logger
	private static final Logger aLogger = LogManager.getLogger("MailServer");
	// 在线连接/用户集合
	public static List<Connection> conns = new ArrayList<Connection>();
	// 数据输入流
	public BufferedReader aInputStream = null;
	// 数据输出流
	public BufferedWriter aOutputStream = null;
	// Connection对象保存连接信息
	private Socket aSocket = null;
	// 连接的用户
	public User aUser = null;
	// 对方的IP地址
	public String IP;
	// 是否已连接
	private Boolean IsConnected = false;
	// 多个监听器形成一个监听器集合，监听不同种类的消息
	private List<MsgListener> Listeners = new ArrayList<MsgListener>();

	// 对方的端口
	public int Port;

	// 状态机状态
	public int State;

	// 构造方法
	public Connection(Socket aSocket) {
		super();
		try {
			this.aSocket = aSocket;
			aOutputStream = new BufferedWriter(new OutputStreamWriter(this.aSocket.getOutputStream()));
			aInputStream = new BufferedReader(new InputStreamReader(this.aSocket.getInputStream()));
			IsConnected = true;
			State = 0;
			SendClient(MessageType.MSG_TYPE_220);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection(String IP, int Port) {
		super();
		this.IP = IP;
		this.Port = Port;
		try {
			this.aSocket = new Socket(IP, Port);
			aOutputStream = new BufferedWriter(new OutputStreamWriter(this.aSocket.getOutputStream()));
			aInputStream = new BufferedReader(new InputStreamReader(this.aSocket.getInputStream()));
			IsConnected = true;
			State = 0;
			SendClient(MessageType.MSG_TYPE_220);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 往监听器集合里添加监听器
	public void AddMsgListener(MsgListener Listener) {
		Listeners.add(Listener);
	}

	public void DisConnect() {
		try {
			if (IsConnected)
				SendClient(MessageType.MSG_TYPE_221);
			IsConnected = false;
			IP = null;
			Port = 0;
			State = 0;
			aUser = null;
			if (aOutputStream != null)
				aOutputStream.close();
			if (aInputStream != null)
				aInputStream.close();
			if (aSocket != null)
				aSocket.close();
			conns.remove(this);
		} catch (Exception e) {
			e.printStackTrace();
			DisConnect();
		}
	}

	public String RecvClient() {
		try {
			if (IsConnected) {
				return aInputStream.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
			IsConnected = false;
			DisConnect();
		}
		return null;
	}

	// 移除监听器集合里的监听器
	public void RemoveMsgListener(MsgListener Listener) {
		Listeners.remove(Listener);
	}

	@Override
	public void run() {
		while (IsConnected) {
			String aMessage = RecvClient();
			System.out.println("R: " + aMessage);
			aLogger.info("R: " + aMessage);
			if (aMessage == null) {
				DisConnect();
			} else if (MessageType.MSG_PATTERN_BLANK.matcher(aMessage).matches()) {
				// Do Nothing
			} else {
				for (MsgListener Listener : Listeners) {
					Listener.onReceive(aMessage);
				}
			}
		}
	}

	public boolean SendClient(String aMessage) {
		try {
			if (IsConnected) {
				System.out.println("S: " + aMessage);
				aLogger.info("S: " + aMessage);
				aOutputStream.write(aMessage);
				aOutputStream.flush();
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
			IsConnected = false;
			DisConnect();
		}
		return false;
	}
}
