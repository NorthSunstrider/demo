package com.northsunstrider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.northsunstrider.entity.User;
import com.northsunstrider.service.UserDataService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserDataService userDataService;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	private User saveUser(@RequestParam("username") String username, @RequestParam("password") String password) {
		User user = new User(username, password);
		userDataService.saveUser(user);
		return user;
	}

	@RequestMapping(value = "/create/model_attribute", method = RequestMethod.POST)
	@ResponseBody
	private User saveUser(@ModelAttribute("user") User user) {
		userDataService.saveUser(user);
		return user;
	}

}
