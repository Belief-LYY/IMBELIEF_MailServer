package com.belief.service;

import com.belief.Impl.adminDaoImpl;
import com.belief.model.Admin;

public class AdminService extends adminDaoImpl {
	public boolean Login(String account, String password) {
		Admin admin = QueryByAccount(account);
		if (admin != null && admin.getAccount() != null && admin.getAccount().equals(account)
				&& admin.getPassword() != null && admin.getPassword().equals(password))
			return true;
		return false;
	}
}
