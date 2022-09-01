package com.northsunstrider.demo.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description: TODO
 * @author: North
 * @date: 2018年5月7日 下午12:31:00
 */
public class Provider {

	/**
	 * @Description TODO
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "springmvc.xml" });
		context.start();
		System.out.println("provider start");
		System.in.read();
	}

}
