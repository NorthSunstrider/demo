package com.northsunstrider.demo.service.impl;

import com.northsunstrider.demo.service.DemoService;

/**
 * @Description: TODO
 * @author: North
 * @date: 2018年5月5日 下午5:17:10
 */
public class DemoServiceImpl implements DemoService {

	@Override
	public String sayHello(String name) {
		System.out.println("here is demoServiceImpl" + Thread.currentThread().getName());
		return "Hello " + name;
	}

}
