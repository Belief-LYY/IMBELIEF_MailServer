package com.belief.dao;

import java.util.ArrayList;

import com.belief.model.Mail;

//业务层抽象接口
public interface mailDao {
	void delete(Mail mail);

	ArrayList<Mail> QueryByMailAddress(String mail_address);

	Mail QueryByMailId(String mail_id);

	ArrayList<Mail> QueryByMailReceive(String mail_receive);

	ArrayList<Mail> QueryByMailReceive(String mail_receive, String state_read);

	void save(Mail mail);

	void update(Mail mail);

}
