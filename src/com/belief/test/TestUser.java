package com.belief.test;

import org.junit.Test;

import com.belief.Impl.userDaoImpl;
import com.belief.model.User;

//仅供测试
public class TestUser {

	// 测试删
	@Test
	public void testdelete() {
		userDaoImpl impl = new userDaoImpl();
		User iuser = new User();
		iuser.setMail_address("15514652@imbelief.com.cn");
		impl.delete(iuser);
	}

	// 测试查
	@Test
	public void testQuery() {
		userDaoImpl impl = new userDaoImpl();
		System.out.println(impl.QueryByEmail("795138462@imbelief.com.cn"));
	}

	// 测试增
	@Test
	public void testSave() {
		userDaoImpl impl = new userDaoImpl();
		User iuser = new User();
		iuser.setMail_address("15514652@imbelief.com.cn");
		iuser.setNick_name("佚阳");
		iuser.setUser_password("LYY1115*");
		impl.save(iuser);
	}

	// 测试改
	@Test
	public void testUpdate() {
		userDaoImpl impl = new userDaoImpl();
		User iuser = new User();
		iuser.setMail_address("15514652@imbelief.com.cn");
		iuser.setNick_name("阳阳阳阳");
		iuser.setUser_password("LYY1145*");
		impl.update(iuser);
	}
}
