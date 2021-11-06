package com.acid6001.ioc3;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AudiEx {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("ioc3.xml");
		
		Audi audi = ctx.getBean("audi", Audi.class);
		System.out.println(audi);
		
		String str = ctx.getBean("str", String.class);
		System.out.println(str);
		
		Car c = ctx.getBean("car1", Car.class);
		System.out.println(c);
		
		int i = ctx.getBean("iii", Integer.class);
		System.out.println(i);

		Car c1 = ctx.getBean("car2", Car.class);
		System.out.println(c1);
		
		ctx.close();
	}
}
