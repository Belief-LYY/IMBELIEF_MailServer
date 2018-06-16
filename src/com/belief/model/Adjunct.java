package com.belief.model;

//实体类
public class Adjunct {
	private String adjunct_id;
	private double adjunct_size;
	private String adjunct_type;
	private String adjunct_url;
	private String mail_id;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Adjunct other = (Adjunct) obj;
		if (adjunct_id == null) {
			if (other.adjunct_id != null)
				return false;
		} else if (!adjunct_id.equals(other.adjunct_id))
			return false;
		if (adjunct_type == null) {
			if (other.adjunct_type != null)
				return false;
		} else if (!adjunct_type.equals(other.adjunct_type))
			return false;
		if (adjunct_url == null) {
			if (other.adjunct_url != null)
				return false;
		} else if (!adjunct_url.equals(other.adjunct_url))
			return false;
		if (mail_id == null) {
			if (other.mail_id != null)
				return false;
		} else if (!mail_id.equals(other.mail_id))
			return false;
		return true;
	}

	public String getAdjunct_id() {
		return adjunct_id;
	}

	public double getAdjunct_size() {
		return adjunct_size;
	}

	public String getAdjunct_type() {
		return adjunct_type;
	}

	public String getAdjunct_url() {
		return adjunct_url;
	}

	public String getMail_id() {
		return mail_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adjunct_id == null) ? 0 : adjunct_id.hashCode());
		result = prime * result + ((adjunct_type == null) ? 0 : adjunct_type.hashCode());
		result = prime * result + ((adjunct_url == null) ? 0 : adjunct_url.hashCode());
		result = prime * result + ((mail_id == null) ? 0 : mail_id.hashCode());
		return result;
	}

	public void setAdjunct_id(String adjunct_id) {
		this.adjunct_id = adjunct_id;
	}

	public void setAdjunct_size(double adjunct_size) {
		this.adjunct_size = adjunct_size;
	}

	public void setAdjunct_type(String adjunct_type) {
		this.adjunct_type = adjunct_type;
	}

	public void setAdjunct_url(String adjunct_url) {
		this.adjunct_url = adjunct_url;
	}

	public void setMail_id(String mail_id) {
		this.mail_id = mail_id;
	}

	@Override
	public String toString() {
		return "Adjunct [mail_id=" + mail_id + ", adjunct_id=" + adjunct_id + ", adjunct_type=" + adjunct_type
				+ ", adjunct_size=" + adjunct_size + ", adjunct_url=" + adjunct_url + "]";
	}

}
