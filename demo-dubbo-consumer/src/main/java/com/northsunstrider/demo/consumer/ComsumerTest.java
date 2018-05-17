package com.northsunstrider.demo.consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.northsunstrider.demo.service.DemoService;

/**
 * @Description: TODO
 * @author: North
 * @date: 2018年5月15日 上午9:45:33
 */
public class ComsumerTest {

	/**
	 * @Description TODO
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			threadTest();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Description 单次调用dubbo服务
	 */
	public static void singleCall() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "dubbo-demo-consumer.xml" });
		context.start();
		DemoService demoService = (DemoService) context.getBean("demoService"); // 获取远程服务代理
		String hello = demoService.sayHello("world"); // 执行远程方法
		System.out.println(hello); // 显示调用结果
	}

	/**
	 * @Description 使用同一个demoService循环调用dubbo服务<br/>
	 *              demoService实例为dubbo返回的代理类
	 *              com.alibaba.dubbo.common.bytecode.proxy0@1ff8b8f
	 *              通过让当前线程睡眠，我们发现
	 *              在执行demoService.sayHello()方法时候才会调用Impl类的sayHello()方法
	 *              说明我之前理解：既从context获得的demoService是一个具体实现类的，当执行demoService的sayHello()方法时，
	 *              是在consumer本地执行了Impl类的方法。 是错误的。
	 *              可以理解为从context获取的demoService更像是一个句柄，可以沟通consumer和provider的句柄
	 *              在需要时，既执行实际方法如sayHello()时再通过该句柄联系provider,再执行provider的impl方法
	 * @throws InterruptedException
	 */
	public static void loopCall() throws InterruptedException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "dubbo-demo-consumer.xml" });
		context.start();
		DemoService demoService = (DemoService) context.getBean("demoService"); // 获取远程服务代理
		while (true) {
			Thread.currentThread().sleep(2000);
			String hello = demoService.sayHello("world"); // 执行远程方法
			System.out.println("loopCall:" + hello + "--" + demoService); // 显示调用结果
		}
	}

	/**
	 * @Description 循环调用dubbo服务，每次都使用新的demoService实例
	 *              虽然demoService的声明是在循环中进行的，但context实际上返回的都是同一个代理对象
	 * @throws InterruptedException
	 */
	public static void loopCallWithNewServiceInstance() throws InterruptedException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "dubbo-demo-consumer.xml" });
		context.start();
		while (true) {
			DemoService demoService = (DemoService) context.getBean("demoService"); // 获取远程服务代理
			Thread.currentThread().sleep(2000);
			String hello = demoService.sayHello("world"); // 执行远程方法
			System.out.println("newServiceLoopCall" + hello + "--" + demoService); // 显示调用结果
		}

	}

	/**
	 * @Description 多线程环境下调用dubbo服务<br/>
	 *              在有并发压力的情况下，provider会多开线程来提供实现类
	 *              而单线程调用dubbo服务，provider始终是同一个线程来提供服务
	 */
	public static void threadTest() {
		try {
			Thread loopCall = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						loopCall();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			loopCall.start();
			loopCallWithNewServiceInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
