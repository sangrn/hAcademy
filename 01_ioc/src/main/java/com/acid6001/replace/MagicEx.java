package com.acid6001.replace;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.Setter;

@Setter
public class MagicEx {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("replace.xml");
		Magician magician = ctx.getBean("magician", Magician.class);
		magician.magic();
	}
}
