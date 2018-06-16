package com.belief.dao;

import java.util.ArrayList;

import com.belief.model.User;

//业务层抽象接口
public interface userDao {
	void delete(User user);

	ArrayList<User> QueryAllUser();

	User QueryByEmail(String email);

	void save(User user);

	void update(User user);

}
