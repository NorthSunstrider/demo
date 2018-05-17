package com.northsunstrider.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class JDBCTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	
	//使用java jdbc连接并操作数据库
	public static void test(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/test_db";
			String username="north";
			String password="114477";
			Connection con=DriverManager.getConnection(url, username, password);
			Statement stmt=con.createStatement();
			ResultSet rs= stmt.executeQuery("select * from user");
			while(rs.next()){
				String name=rs.getString("username");
				System.out.println(name);
			}
		}catch(Exception e){
			System.out.println("can't find jdbc driver class,load error");
			e.printStackTrace();
		}
		
		
	}

}
