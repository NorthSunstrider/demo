package com.northsunstrider.aop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.northsunstrider.aop.service.ProductService;
import com.northsunstrider.aop.tool.CurrentSetHolder;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	private String insert(@RequestParam("user") String user) {
		CurrentSetHolder.set(user);
		System.out.println(user);
		productService.insert();
		return "request received";
	}

	@RequestMapping(method = RequestMethod.DELETE)
	@ResponseBody
	private String delete() {
		CurrentSetHolder.set("admin");
		productService.delete();
		return "request received";
	}
}
