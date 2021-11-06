package com.acid6001.ioc4;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AudiEx {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("ioc4.xml");
		System.out.println(ctx.getBean("audi"));
		
		ctx.close();
	}
}
