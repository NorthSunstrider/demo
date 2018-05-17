package com.apestore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apestore.entity.User;
import com.apestore.mapper.UserMapper;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserMapper userMapper;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	private User saveUser(@RequestParam("username") String username, @RequestParam("password") String password) {
		User user = new User(username, password);
		System.out.println("save user:" + user);

		return user;
	}

	@RequestMapping(value = "/create/model_attribute", method = RequestMethod.POST)
	@ResponseBody
	private User saveUser(@ModelAttribute("user") User user) {
		// userMapper.saveUser(user);
		return user;
	}

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	@ResponseBody
	private User selectUserById() {
		User user = this.userMapper.getUser(1l);
		return user;
	}

}
