package com.belief.Impl;

import com.belief.dao.adminDao;
import com.belief.model.Admin;

//业务层(DAO层)的实现类，继承了业务层公共父类，并实现了业务层抽象接口
public class adminDaoImpl extends ImplFather implements adminDao {

	@Override
	public void delete(Admin admin) {
		session.delete(admin);
		cleanUp();
	}

	@Override
	public Admin QueryByAccount(String account) {
		Admin admin = (Admin) session.get(Admin.class, account);
		cleanUp();
		return admin;
	}

	@Override
	public void save(Admin admin) {
		session.save(admin);
		cleanUp();
	}

	@Override
	public void update(Admin admin) {
		session.update(admin);
		cleanUp();
	}

}
