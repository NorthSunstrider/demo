package com.apestore.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apestore.entity.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Controller
@EnableAutoConfiguration
@RequestMapping("/user")
@Api("swagger的注释	UserController")
public class UserController {

	
	@ApiOperation(value = "保存用户", notes = "notes 保存用户")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	private User saveUser(@RequestParam("username") String username, @RequestParam("password") String password) {
		User user = new User(username, 1);
		System.out.println("save user:" + user);

		return user;
	}

	@RequestMapping(value = "/create/model_attribute", method = RequestMethod.POST)
	@ResponseBody
	private User saveUser(@ModelAttribute("user") User user) {
		// userDataService.saveUser(user);
		return user;
	}
}
