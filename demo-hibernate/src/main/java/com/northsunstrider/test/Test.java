package com.northsunstrider.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.northsunstrider.entity.PasswordInfo;
import com.northsunstrider.util.HibernateUtil;

public class Test {

	public static void main(String[] args) {
		// testSave();
		testQuery();
	}

	// 应用hibernate向数据库中插入一条数据
	public static void testSave() {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			// 得到工厂
			sessionFactory = HibernateUtil.getSessionFactory();
			// 得到session
			session = sessionFactory.openSession();
			// 开启事务
			tx = session.beginTransaction();

			// 创建对象并保存
			PasswordInfo pi = new PasswordInfo("111", "1111", "111", "1111");
			session.save(pi);
			// 提交事务
			tx.commit();
		} catch (Exception e) {
			// 事务回滚
			tx.rollback();
		} finally {
			// 关闭资源
			session.close();
			sessionFactory.close();
		}
	}

	// 应用hibernate从数据库中查询数据
	public static void testQuery() {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			// 得到工厂
			sessionFactory = HibernateUtil.getSessionFactory();
			// 得到session
			session = sessionFactory.openSession();
			// 开启事务
			tx = session.beginTransaction();

			// 查询记录
			PasswordInfo p = session.get(PasswordInfo.class, 1l);
			System.out.println(p.getUsername());
			// 提交事务
			tx.commit();
		} catch (Exception e) {
			// 事务回滚
			tx.rollback();
		} finally {
			// 关闭资源
			session.close();
			sessionFactory.close();
		}
	}
}
