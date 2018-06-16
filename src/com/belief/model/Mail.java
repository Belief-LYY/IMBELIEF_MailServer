package com.belief.model;

import java.util.Date;

//实体类
public class Mail {
	private String content_encoding;
	private String mail_address;
	private String mail_content;
	private String mail_id;
	private String mail_receive;
	private String mail_subject;
	private String protocol_version;
	private Date send_time;
	private String state_read;
	private String state_send;
	private Date timing;

	public Mail() {
		super();
		this.mail_id = new Double(Math.random() * 3).toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mail other = (Mail) obj;
		if (mail_address == null) {
			if (other.mail_address != null)
				return false;
		} else if (!mail_address.equals(other.mail_address))
			return false;
		if (mail_id == null) {
			if (other.mail_id != null)
				return false;
		} else if (!mail_id.equals(other.mail_id))
			return false;
		if (mail_receive == null) {
			if (other.mail_receive != null)
				return false;
		} else if (!mail_receive.equals(other.mail_receive))
			return false;
		return true;
	}

	public String getContent_encoding() {
		return content_encoding;
	}

	public String getMail_address() {
		return mail_address;
	}

	public String getMail_content() {
		return mail_content;
	}

	public String getMail_id() {
		return mail_id;
	}

	public String getMail_receive() {
		return mail_receive;
	}

	public String getMail_subject() {
		return mail_subject;
	}

	public String getProtocol_version() {
		return protocol_version;
	}

	public Date getSend_time() {
		return send_time;
	}

	public String getState_read() {
		return state_read;
	}

	public String getState_send() {
		return state_send;
	}

	public Date getTiming() {
		return timing;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mail_address == null) ? 0 : mail_address.hashCode());
		result = prime * result + ((mail_id == null) ? 0 : mail_id.hashCode());
		result = prime * result + ((mail_receive == null) ? 0 : mail_receive.hashCode());
		return result;
	}

	public void setContent_encoding(String content_encoding) {
		this.content_encoding = content_encoding;
	}

	public void setMail_address(String mail_address) {
		this.mail_address = mail_address;
	}

	public void setMail_content(String mail_content) {
		this.mail_content = mail_content;
	}

	public void setMail_id(String mail_id) {
		this.mail_id = mail_id;
	}

	public void setMail_receive(String mail_receive) {
		this.mail_receive = mail_receive;
	}

	public void setMail_subject(String mail_subject) {
		this.mail_subject = mail_subject;
	}

	public void setProtocol_version(String protocol_version) {
		this.protocol_version = protocol_version;
	}

	public void setSend_time(Date send_time) {
		this.send_time = send_time;
	}

	public void setState_read(String state_read) {
		this.state_read = state_read;
	}

	public void setState_send(String state_send) {
		this.state_send = state_send;
	}

	public void setTiming(Date timing) {
		this.timing = timing;
	}

	@Override
	public String toString() {
		return "Mail [mail_id=" + mail_id + ", mail_address=" + mail_address + ", mail_receive=" + mail_receive
				+ ", send_time=" + send_time + ", mail_subject=" + mail_subject + ", mail_content=" + mail_content
				+ ", state_send=" + state_send + ", state_read=" + state_read + ", content_encoding=" + content_encoding
				+ ", protocol_version=" + protocol_version + ", timing=" + timing + "]";
	}

}
