package com.tronsis.test.service;

import java.sql.Connection;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tronsis.test.entity.User;
import com.tronsis.test.entity.api.IUserOperation;

public class UserService extends BasicService {

	public User getUser(Long id) {
		SqlSession sqlSession = null;
		try {
			sqlSession = openSession();
			String statement = "com.tronsis.test.entity.mapping.userMapper.getUser";// 映射sql的标识字符串
			// 执行查询返回一个唯一user对象的sql
			User user = sqlSession.selectOne(statement, id);
			return user;
		} catch (Exception e) {
			throw e;
		} finally {
			sqlSession.close();
		}
	}

	public User searchUser(String name, int age) {
		SqlSession sqlSession = null;
		try {
			sqlSession = openSession();
			IUserOperation userOperation = sqlSession.getMapper(IUserOperation.class);
			User user = userOperation.searchUser(name, age);
			return user;
		} catch (Exception e) {
			throw e;
		} finally {
			sqlSession.close();
		}
	}

	public User selectUserById(Long id) {
		SqlSession sqlSession = null;
		try {
			sqlSession = openSession();
			IUserOperation userOperation = sqlSession.getMapper(IUserOperation.class);
			User user = userOperation.selectUserById(1l);
			return user;
		} catch (Exception e) {
			throw e;
		} finally {
			sqlSession.close();
		}
	}

	public List<User> listUser() {
		SqlSession sqlSession = null;
		try {
			sqlSession = openSession();
			IUserOperation userOperation = sqlSession.getMapper(IUserOperation.class);
			List<User> userlist = userOperation.listUser();
			return userlist;
		} catch (Exception e) {
			throw e;
		} finally {
			sqlSession.close();
		}
	}

	public int transactionTest() {
		SqlSession sqlSession = null;
		Connection conn = null;
		try {
			sqlSession = openSession();
			conn = sqlSession.getConnection();
			conn.setAutoCommit(false);
			IUserOperation userOperation = sqlSession.getMapper(IUserOperation.class);
			userOperation.updateUser("111", "user1");
			int a = 1 / 0;
			userOperation.updateUser("222", "user2");
			conn.commit();
			return 1;
		} catch (Exception e) {
			try {
				conn.rollback();
				throw e;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} finally {
			sqlSession.close();
		}
		return 0;
	}

}
