package com.belief.model;

import java.io.Serializable;

//联合主键实体类必须实现序列化接口
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;// 序列化的序列号
	private String mail_address;
	private String friend_address;
	private String remark_name;

	public String getMail_address() {
		return mail_address;
	}

	public void setMail_address(String mail_address) {
		this.mail_address = mail_address;
	}

	public String getFriend_address() {
		return friend_address;
	}

	public void setFriend_address(String friend_address) {
		this.friend_address = friend_address;
	}

	public String getRemark_name() {
		return remark_name;
	}

	public void setRemark_name(String remark_name) {
		this.remark_name = remark_name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((friend_address == null) ? 0 : friend_address.hashCode());
		result = prime * result + ((mail_address == null) ? 0 : mail_address.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (friend_address == null) {
			if (other.friend_address != null)
				return false;
		} else if (!friend_address.equals(other.friend_address))
			return false;
		if (mail_address == null) {
			if (other.mail_address != null)
				return false;
		} else if (!mail_address.equals(other.mail_address))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Address [mail_address=" + mail_address + ", friend_address=" + friend_address + ", remark_name="
				+ remark_name + "]";
	}

}
