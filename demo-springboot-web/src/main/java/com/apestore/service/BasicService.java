package com.apestore.service;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class BasicService {

	private SqlSessionFactory sqlSessionFactory;

	public BasicService() {
		super();
		String resource = "conf.xml";
		InputStream is = BasicService.class.getClassLoader().getResourceAsStream(resource);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
	}

	SqlSession openSession() {
		return sqlSessionFactory.openSession();
	}

}
