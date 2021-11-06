package com.acid6001.ioc2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class OrderManagerApp {
	public static void main(String[] args) {
		// OrderManager orderManager = new OrderManager();
		// orderManager.order();

		// DL

		ApplicationContext ctx = new ClassPathXmlApplicationContext("ioc2.xml");
		OrderManager orderManager = ctx.getBean("orderManager", OrderManager.class);
		orderManager.order();
	}
}
