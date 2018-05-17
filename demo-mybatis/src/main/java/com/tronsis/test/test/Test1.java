package com.tronsis.test.test;

import java.io.IOException;
import java.util.List;

import com.tronsis.test.entity.User;
import com.tronsis.test.service.UserService;

public class Test1 {
	public static void main(String[] args) throws IOException {
		// // mybatis的配置文件
		// String resource = "conf.xml";
		// // 使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
		// InputStream is = Test1.class.getClassLoader().getResourceAsStream(
		// resource);
		// // 构建sqlSession的工厂
		// SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder()
		// .build(is);
		// // 使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）
		// // Reader reader = Resources.getResourceAsReader(resource);
		// // 构建sqlSession的工厂
		// // SqlSessionFactory sessionFactory = new
		// // SqlSessionFactoryBuilder().build(reader);
		// // 创建能执行映射文件中sql的sqlSession
		// SqlSession session = sessionFactory.openSession();
		// /**
		// * 映射sql的标识字符串，
		// * me.gacl.mapping.userMapper是userMapper.xml文件中mapper标签的namespace属性的值，
		// * getUser是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
		// */
		// String statement =
		// "com.tronsis.test.entity.mapping.userMapper.getUser";// 映射sql的标识字符串
		// // 执行查询返回一个唯一user对象的sql
		// User user = session.selectOne(statement, 1l);
		// System.out.println(user);

		// testSearchUser();
		// listUser();
		// testUser();
		transactionTest();
	}

	public static void testUser() {
		UserService userService = new UserService();
		// User user = userService.getUser(1l);
		User user = userService.selectUserById(1l);
		System.out.println(user.getName());
	}

	public static void testSearchUser() {
		UserService userService = new UserService();
		User user = userService.searchUser("aa", 11);
		System.out.println(user.getName());
	}

	public static void listUser() {
		UserService userService = new UserService();
		System.out.println(System.currentTimeMillis());
		List<User> userList = userService.listUser();
		System.out.println(userList.size());
		System.out.println(System.currentTimeMillis());
	}

	//在mybatis中加入事物管理，通过java.sql.connection
	public static void transactionTest() {
		UserService userService = new UserService();
		int result = userService.transactionTest();
		System.out.println(result);
	}
}
