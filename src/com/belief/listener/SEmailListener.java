package com.belief.listener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;

import com.belief.Impl.mailDaoImpl;
import com.belief.connection.Connection;
import com.belief.model.Mail;
import com.belief.model.MessageType;
import com.belief.connection.Connection.MsgListener;

public class SEmailListener implements MsgListener {
	private Connection conn;
	// Message Buffer
	private List<Mail> Mails = new ArrayList<Mail>();
	private String MailFrom;
	private StringBuffer Content = new StringBuffer("");

	public SEmailListener(Connection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public void onReceive(String aMessage) {
		if (conn.State == 4) {
			if (MessageType.MSG_PATTERN_MAIL_FROM.matcher(aMessage).matches()) {
				conn.SendClient(MessageType.MSG_TYPE_250);
				Matcher aMatcher = MessageType.MSG_PATTERN_MAIL_ADDRESS.matcher(aMessage);
				if (aMatcher.find())
					MailFrom = aMatcher.group();
				if (conn.aUser.getMail_address().equals(MailFrom))
					conn.State++;
				else
					conn.SendClient(MessageType.MSG_TYPE_504);
			} else if (MessageType.MSG_PATTERN_QUIT.matcher(aMessage).matches()) {
				conn.DisConnect();
			} else {
				conn.SendClient(MessageType.MSG_TYPE_502);
			}
		} else if (conn.State == 5) {
			if (MessageType.MSG_PATTERN_RCPT_TO.matcher(aMessage).matches()) {
				conn.SendClient(MessageType.MSG_TYPE_250);
				Mail aMail = new Mail();
				aMail.setMail_address(MailFrom);
				Matcher aMatcher = MessageType.MSG_PATTERN_MAIL_ADDRESS.matcher(aMessage);
				if (aMatcher.find())
					aMail.setMail_receive(aMatcher.group());
				Mails.add(aMail);
				conn.State++;
			} else if (MessageType.MSG_PATTERN_QUIT.matcher(aMessage).matches()) {
				conn.DisConnect();
			} else if (aMessage.equalsIgnoreCase(MessageType.MSG_TYPE_RSET)) {
				conn.SendClient(MessageType.MSG_TYPE_250);
				Mails = null;
				conn.State = 4;
			} else {
				conn.SendClient(MessageType.MSG_TYPE_502);
			}
		} else if (conn.State == 6) {
			if (MessageType.MSG_PATTERN_RCPT_TO.matcher(aMessage).matches()) {
				conn.SendClient(MessageType.MSG_TYPE_250);
				Mail aMail = new Mail();
				aMail.setMail_address(MailFrom);
				Matcher aMatcher = MessageType.MSG_PATTERN_MAIL_ADDRESS.matcher(aMessage);
				if (aMatcher.find())
					aMail.setMail_receive(aMatcher.group());
				Mails.add(aMail);
			} else if (MessageType.MSG_PATTERN_QUIT.matcher(aMessage).matches()) {
				conn.DisConnect();
			} else if (aMessage.equalsIgnoreCase(MessageType.MSG_TYPE_RSET)) {
				conn.SendClient(MessageType.MSG_TYPE_250);
				Mails = null;
				conn.State = 4;
			} else if (aMessage.equalsIgnoreCase(MessageType.MSG_TYPE_DATA)) {
				conn.SendClient(MessageType.MSG_TYPE_354);
				conn.State++;
			} else {
				conn.SendClient(MessageType.MSG_TYPE_502);
			}
		} else if (conn.State == 7) {
			if (aMessage.equals(MessageType.MSG_TYPE_DATA_END)) {
				for (int i = 0; i < Mails.size(); i++) {
					mailDaoImpl amailDaoImpl = new mailDaoImpl();
					Mails.get(i).setMail_content(Content.toString());
					Mails.get(i).setSend_time(new Date());
					Mails.get(i).setState_send(MessageType.MSG_TYPE_ST_SUCCESS);
					Mails.get(i).setState_read(MessageType.MSG_TYPE_ST_UNREAD);
					Matcher aMatcher = MessageType.MSG_PATTERN_SUBJECT.matcher(Content.toString());
					if (aMatcher.find())
						Mails.get(i).setMail_subject(aMatcher.group().substring(8));
					aMatcher = MessageType.MSG_PATTERN_CHARSET.matcher(Content.toString());
					if (aMatcher.find()) {
						String Content_encoding = aMatcher.group();
						Mails.get(i).setContent_encoding(Content_encoding.substring(Content_encoding.indexOf("\"") + 1,
								Content_encoding.lastIndexOf("\"")));
					}

					amailDaoImpl.save(Mails.get(i));
				}
				conn.State = 4;
				conn.SendClient(MessageType.MSG_TYPE_250);
			} else {
				Content.append(aMessage + MessageType.MSG_TYPE_CRLF);
			}
		}
	}
}
