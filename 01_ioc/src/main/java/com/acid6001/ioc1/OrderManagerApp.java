package com.acid6001.ioc1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class OrderManagerApp {
	public static void main(String[] args) {
		// OrderManager orderManager = new OrderManager();
		// orderManager.order();

		// DL

		ApplicationContext ctx = new ClassPathXmlApplicationContext("ioc1.xml");
		OrderManager orderManager = ctx.getBean("orderManager", OrderManager.class);
//		OrderManager orderManager1 = ctx.getBean("orderManager", OrderManager.class);
//		OrderManager orderManager2 = ctx.getBean("orderManager", OrderManager.class);
//		OrderManager orderManager3 = ctx.getBean("orderManager", OrderManager.class);

//		System.out.println(orderManager);
//		System.out.println(orderManager1);
//		System.out.println(orderManager2);
//		System.out.println(orderManager3);

//		OrderManager orderManager = (OrderManager) ctx.getBean("orderManager");
		orderManager.order();
	}
}
