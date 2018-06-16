package com.belief.test;

import org.junit.Test;

import com.belief.Impl.mailDaoImpl;

//仅供测试
public class TestMail {

	// 测试增
	@Test
	public void testQueryByMailAddress() {
		mailDaoImpl impl = new mailDaoImpl();
		impl.QueryByMailAddress("296293760@imbelief.com.cn");
	}

	@Test
	public void testQueryByMailReceive() {
		mailDaoImpl impl = new mailDaoImpl();
		impl.QueryByMailReceive("891079011@qq.com");
	}

	@Test
	public void testQueryByMailReceives() {
		mailDaoImpl impl = new mailDaoImpl();
		impl.QueryByMailReceive("891079011@qq.com", "未读");
	}
}
