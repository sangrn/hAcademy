package com.acid6001.value1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component("ex")
public class SimpleValueEx {
	@Value("#{person.name}")
	private String name;
	@Value("#{person.age}")
	private int age;

	@Value("#{person}")
	private Person person;

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("value1.xml");
		SimpleValueEx ex = ctx.getBean("ex", SimpleValueEx.class);
		System.out.println(ex);

		ctx.close();
	}
}
