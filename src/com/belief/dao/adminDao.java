package com.belief.dao;

import com.belief.model.Admin;

//业务层抽象接口
public interface adminDao {
	void save(Admin admin);

	void delete(Admin admin);

	void update(Admin admin);

	Admin QueryByAccount(String account);
}
