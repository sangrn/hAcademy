package com.acid6001.aop5;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Ex {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("aop5.xml");
		ctx.getBean("myBean1", MyBean.class).run();
		System.out.println("=====================================");
		ctx.getBean("myBean2", MyBean.class).run();
	
		ctx.close();
	}
}
