package com.belief.listener;

import com.belief.connection.Connection;
import com.belief.connection.Connection.MsgListener;
import com.belief.model.MessageType;
import com.belief.model.User;
import com.belief.service.UserService;

public class PLoginListener implements MsgListener {
	private Connection conn;
	// Message Buffer
	private String Usermail = null;
	private String Password = null;

	public PLoginListener(Connection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public void onReceive(String aMessage) {
		if (conn.State == 0) {
			if (MessageType.MSG_PATTERN_QUIT.matcher(aMessage).matches()) {
				conn.DisConnect();
			} else if (MessageType.MSG_PATTERN_USER.matcher(aMessage).matches()) {
				Usermail = aMessage.split(" ")[1] + MessageType.MSG_TYPE_DOMAIN;
				conn.SendClient(MessageType.MSG_TYPE_OK);
				conn.State++;
			} else {
				conn.SendClient(MessageType.MSG_TYPE_502);
			}
		} else if (conn.State == 1) {
			if (MessageType.MSG_PATTERN_QUIT.matcher(aMessage).matches()) {
				conn.DisConnect();
			} else if (MessageType.MSG_PATTERN_PASS.matcher(aMessage).matches()) {
				Password = aMessage.split(" ")[1];
				if (new UserService().Login(Usermail, Password)) {
					conn.SendClient(MessageType.MSG_TYPE_235);
					conn.aUser = new User(Usermail, Password);
					Connection.conns.add(conn);
					conn.State++;
				} else {
					conn.SendClient(MessageType.MSG_TYPE_535);
					conn.State = 0;
				}
			} else {
				conn.SendClient(MessageType.MSG_TYPE_502);
			}
		}
	}
}