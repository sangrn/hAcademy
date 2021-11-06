package com.acid6001.smallmart4;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SmallMartEx {
	public static void main(String[] args) {
		// ApplicationContext
		// getProduct2 xml aop

		// proxyFactoryBean
		// Advice PointCut
		// Advisor

		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("mart2.xml");
		SmallMart mart = ctx.getBean("smallMart", SmallMart.class);

		mart.getProduct("식빵");
		System.out.println("====================");
		mart.getProduct2("커피");

		ctx.close();
	}
}
