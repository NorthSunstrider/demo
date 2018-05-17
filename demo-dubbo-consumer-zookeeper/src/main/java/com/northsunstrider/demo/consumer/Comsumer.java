package com.northsunstrider.demo.consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.northsunstrider.demo.service.DemoService;

/**
 * @Description: TODO
 * @author: North
 * @date: 2018年5月8日 下午5:53:07
 */
public class Comsumer {
	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "springmvc.xml" });
		context.start();
		DemoService demoService = (DemoService) context.getBean("demoService"); // 获取远程服务代理
		while (true) {
			String hello = demoService.sayHello("world"); // 执行远程方法
			System.out.println(hello); // 显示调用结果
		}
	}
}
