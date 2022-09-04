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

/**
 * spring4以后官方推荐使用构造器方式注入<br/>
 * 保证注入的组件不可变且不为空<br/>
 * 保证使用构造器注入的类呈完全初始化的状态
 */
@Controller
@RequestMapping("/user2")
public class UserController2 {

    private final UserDataService userDataService;

    @Autowired
    public UserController2(UserDataService userDataService) {
        this.userDataService = userDataService;
    }

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
