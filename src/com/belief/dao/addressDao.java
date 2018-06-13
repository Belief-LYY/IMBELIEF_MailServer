package com.belief.dao;

import java.util.ArrayList;

import com.belief.model.Address;

//业务层抽象接口
public interface addressDao {
	void save(Address address);

	void delete(Address address);

	void update(Address address);

	Address QueryByOne(String mail_address, String friend_address);

	ArrayList<Address> QueryByMail(String mail_address);
}
