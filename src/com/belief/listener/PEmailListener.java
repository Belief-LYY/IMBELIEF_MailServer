package com.belief.listener;

import java.util.ArrayList;
import java.util.List;

import com.belief.Impl.mailDaoImpl;
import com.belief.connection.Connection;
import com.belief.connection.Connection.MsgListener;
import com.belief.model.Mail;
import com.belief.model.MessageType;

public class PEmailListener implements MsgListener {
	private Connection conn;
	private List<Mail> Mails = new ArrayList<Mail>();

	public PEmailListener(Connection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public void onReceive(String aMessage) {
		if (conn.State == 2) {
			if (MessageType.MSG_PATTERN_QUIT.matcher(aMessage).matches()) {
				for (Mail aMail : Mails) {
					new mailDaoImpl().delete(aMail);
				}
				conn.DisConnect();
			} else if (aMessage.equalsIgnoreCase(MessageType.MSG_TYPE_STAT)) {
				// 返回邮箱统计信息
				conn.SendClient(MessageType.MSG_TYPE_OK);
			} else if (aMessage.equalsIgnoreCase(MessageType.MSG_TYPE_LIST)) {
				List<Mail> UserMails = new mailDaoImpl().QueryByMailAddress(conn.aUser.getMail_address());
				conn.SendClient(MessageType.MSG_TYPE_OK);
				for (Mail aMail : UserMails) {
					conn.SendClient(aMail.getMail_id() + " " + aMail.getMail_subject());
				}
			} else if (aMessage.equalsIgnoreCase(MessageType.MSG_TYPE_RSET)) {
				Mails.removeAll(Mails);
				conn.SendClient(MessageType.MSG_TYPE_OK);
			} else if (MessageType.MSG_PATTERN_RETR.matcher(aMessage).matches()) {
				conn.SendClient(MessageType.MSG_TYPE_OK + " "
						+ new mailDaoImpl().QueryByMailId(aMessage.split(" ")[1]).toString());
			} else if (MessageType.MSG_PATTERN_UIDL.matcher(aMessage).matches()) {
				conn.SendClient(MessageType.MSG_TYPE_OK + " "
						+ new mailDaoImpl().QueryByMailId(aMessage.split(" ")[1]).getMail_subject());
			} else if (MessageType.MSG_PATTERN_DELE.matcher(aMessage).matches()) {
				Mails.add(new mailDaoImpl().QueryByMailId(aMessage.split(" ")[1]));
				conn.SendClient(MessageType.MSG_TYPE_OK);
			} else {
				conn.SendClient(MessageType.MSG_TYPE_502+2);
			}
		}
	}
}
