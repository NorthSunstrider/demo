package com.apestore.app;

import javax.servlet.http.HttpServlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.apestore.controller.UserController;

//使用main函数运行项目时，访问路径不包含项目名
//既tomcat下url=http://localhost:8080/springboot-web-demo/user/create
//main函数运行url=http://localhost:8080/user/create
public class Application extends HttpServlet {

	public static void main(String[] args) {
		SpringApplication.run(UserController.class, args);
	}
}
