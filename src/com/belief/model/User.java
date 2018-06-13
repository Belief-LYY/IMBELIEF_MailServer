package com.belief.model;

//实体类
public class User {
	private String nick_name;
	private String mail_address;
	private String user_password;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String mail_address, String user_password) {
		super();
		this.mail_address = mail_address;
		this.user_password = user_password;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public String getMail_address() {
		return mail_address;
	}

	public void setMail_address(String mail_address) {
		this.mail_address = mail_address;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		User other = (User) obj;
		if (mail_address == null) {
			if (other.mail_address != null)
				return false;
		} else if (!mail_address.equals(other.mail_address))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [nick_name=" + nick_name + ", mail_address=" + mail_address + ", user_password=" + user_password
				+ "]";
	}

}
