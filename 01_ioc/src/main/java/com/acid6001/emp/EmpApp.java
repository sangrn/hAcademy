package com.acid6001.emp;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EmpApp {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("emp.xml");
		Emp e = ctx.getBean("emp", Emp.class);
		e.work();
	}
}
