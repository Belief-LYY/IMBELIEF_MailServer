package com.belief.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory sessionFactory;
	private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();

	// 使用静态代码块初始化Hibernate
	static {
		try {
			// 1 读取配置文件
			Configuration cfg = new Configuration().configure();
			// 2 根据配置 创建Factory
			sessionFactory = cfg.buildSessionFactory();
			cfg = null;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void closeSession() throws HibernateException {
		// 从线程局部变量threadLocal中获取之前存入的Session实例
		Session session = (Session) threadLocal.get();
		threadLocal.set(null);
		if (session != null) {
			session.close();
		}
	}

	// 获得ThreadLocal对象管理的Session实例
	public static Session getSession() throws HibernateException {
		Session session = (Session) threadLocal.get();
		if (session == null || !session.isOpen()) {
			if (sessionFactory == null) {
				rebuildSessionFactory();
			}
			// 3 通过sessionFactory获得操作数据库的session对象
			session = (sessionFactory != null) ? sessionFactory.openSession() : null;
			threadLocal.set(session);
		}
		return session;
	}

	// 获得SessionFactory实例
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	// 重建SessionFactory
	public static void rebuildSessionFactory() {
		try {
			Configuration cfg = new Configuration().configure();
			sessionFactory = cfg.buildSessionFactory();
			cfg = null;
		} catch (Exception ex) {
			System.out.println("Error Creating SessionFactory");
			ex.printStackTrace();
		}
	}

	public static void shutDown() {
		getSessionFactory().close();
	}

}
