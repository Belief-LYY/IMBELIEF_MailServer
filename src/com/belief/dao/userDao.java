package com.belief.dao;

import java.util.ArrayList;

import com.belief.model.User;

//业务层抽象接口
public interface userDao {
	void save(User user);

	void delete(User user);

	void update(User user);

	User QueryByEmail(String email);

	ArrayList<User> QueryAllUser();

}
