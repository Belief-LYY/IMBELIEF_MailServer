package com.belief.dao;

import java.util.ArrayList;

import com.belief.model.Address;

//业务层抽象接口
public interface addressDao {
	void delete(Address address);

	ArrayList<Address> QueryByMail(String mail_address);

	Address QueryByOne(String mail_address, String friend_address);

	void save(Address address);

	void update(Address address);
}
