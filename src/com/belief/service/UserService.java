package com.belief.service;

import com.belief.Impl.userDaoImpl;
import com.belief.model.User;

public class UserService extends userDaoImpl {
	public boolean Login(String email, String password) {
		User aUser = QueryByEmail(email);
		if (aUser != null && aUser.getUser_password() != null && aUser.getUser_password().equals(password))
			return true;
		return false;
	}

	public boolean Register(String email, String password) {
		if (QueryByEmail(email) == null) {
			save(new User(email, password));
			return true;
		}
		return false;
	}
}
