package com.northsunstrider.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTransaction {

	public static void main(String[] args) {
		test();
	}

	/**在jdbc中加入事物控制
	 * 用户1往用户2账户转账200
	 * 两条更新语句，1先扣钱，2再加钱
	 * 普通情况第一条更新成功后，程序出错，抛出异常，第二条语句就不会执行了，这样就造成问题了
	 * 加入事物后，第二条报错，事物回滚，将1的钱又退回去了
	 */
	public static void test() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/test_db";
			String username = "north";
			String password = "114477";
			con = DriverManager.getConnection(url, username, password);
			con.setAutoCommit(false);

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from  bank_user order by id asc");
			while (rs.next()) {
				String name = rs.getString("username");
				int temp = rs.getInt("balance");
				System.out.println(name + ":" + temp);
			}

			// 第一条更新语句
			stmt.executeUpdate("update bank_user set balance=0 where id=1");

			// 除0引发异常
			int a = 1 / 0;

			// 第二条更新语句
			stmt.executeUpdate("update bank_user set balance=200 where id=2");

			con.commit();
		} catch (Exception e) {
			// 捕获异常后，事物回滚
			if (con != null)
				try {
					con.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			e.printStackTrace();
		}

	}
}
