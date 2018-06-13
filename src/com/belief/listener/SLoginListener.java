package com.belief.listener;

import org.apache.commons.codec.binary.Base64;

import com.belief.connection.Connection;
import com.belief.connection.Connection.MsgListener;
import com.belief.model.MessageType;
import com.belief.model.User;
import com.belief.service.UserService;

public class SLoginListener implements MsgListener {
	private Connection conn;
	// Message Buffer
	private String Usermail = null;
	private String Password = null;

	public SLoginListener(Connection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public void onReceive(String aMessage) {
		if (conn.State == 0) {
			if (MessageType.MSG_PATTERN_HELO.matcher(aMessage).matches()) {
				conn.SendClient(MessageType.MSG_TYPE_250);
				conn.State++;
			} else if (MessageType.MSG_PATTERN_QUIT.matcher(aMessage).matches()) {
				conn.DisConnect();
			} else {
				conn.SendClient(MessageType.MSG_TYPE_502);
			}
		} else if (conn.State == 1) {
			if (aMessage.equalsIgnoreCase(MessageType.MSG_TYPE_AUTH_LOGIN)) {
				conn.SendClient(MessageType.MSG_TYPE_334);
				conn.State++;
			} else if (MessageType.MSG_PATTERN_QUIT.matcher(aMessage).matches()) {
				conn.DisConnect();
			} else {
				conn.SendClient(MessageType.MSG_TYPE_502);
			}
		} else if (conn.State == 2) {
			if (MessageType.MSG_PATTERN_QUIT.matcher(aMessage).matches()) {
				conn.DisConnect();
			} else {
				// Byte构造字符串不能用toString()要用new String()
				Usermail = new String(Base64.decodeBase64(aMessage)).split("\\@")[0] + MessageType.MSG_TYPE_DOMAIN;
				conn.SendClient(MessageType.MSG_TYPE_334);
				conn.State++;
			}
		} else if (conn.State == 3) {
			if (MessageType.MSG_PATTERN_QUIT.matcher(aMessage).matches()) {
				conn.DisConnect();
			} else {
				Password = new String(Base64.decodeBase64(aMessage));
				if (new UserService().Login(Usermail, Password)) {
					conn.SendClient(MessageType.MSG_TYPE_235);
					conn.aUser = new User(Usermail, Password);
					Connection.conns.add(conn);
					conn.State++;
				} else {
					conn.SendClient(MessageType.MSG_TYPE_535);
					conn.State = 1;
				}
			}
		}
	}
}