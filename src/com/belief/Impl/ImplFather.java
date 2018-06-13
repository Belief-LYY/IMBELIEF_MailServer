package com.belief.Impl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.belief.util.HibernateUtil;

//提取业务层(DAO层)的公共部分，作为业务层实现类的公共父类
public class ImplFather {

	protected Session session = HibernateUtil.getSession();
	protected Transaction tx = session.beginTransaction();

	protected void cleanUp() {
		try {
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			HibernateUtil.closeSession();
		}
	}
}
