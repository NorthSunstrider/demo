package com.apestore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apestore.entity.User;
import com.apestore.mapper.UserMapper;

@Controller
@EnableAutoConfiguration
@RequestMapping("/user2")
public class UserController2 {

	@Autowired
	private UserMapper userMapper;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	private User saveUser(@RequestParam("username") String username, @RequestParam("password") String password) {
		System.out.println("This is controller 2 ==");
		User user = new User(username, 1);
		System.out.println("save user:" + user);

		return user;
	}

//	@RequestMapping(value = "/create/model_attribute", method = RequestMethod.POST)
//	@ResponseBody
//	private User saveUser(@ModelAttribute("user") User user) {
//		userDataService.saveUser(user);
//		return user;
//	}
	
	@RequestMapping(value = "/select", method = RequestMethod.GET)
	@ResponseBody
	private User selectUser(@RequestParam("id") Long id) {
		System.out.println("This is controller 2 ==");
		User user=this.userMapper.selectUserById(1L);
		System.out.println("select user:" + user);

		return user;
	}
}
