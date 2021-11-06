package com.acid6001.aop7;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Ex {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("aop7.xml");
		ctx.getBean("myBean", MyBean.class).run();
		ctx.close();

	}
}
