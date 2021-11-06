package com.acid6001.collection;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.Data;

@Data

public class CollectionEx {
	private Properties properties;
	private Set<?> set;
	private List<String> list;
	private Map<String, Integer> map;

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("collection.xml");

		CollectionEx ex = ctx.getBean("ex", CollectionEx.class);
		System.out.println("====== props =====");
		ex.properties.forEach((k, v) -> System.out.println(k + "::" + v));

		System.out.println("===== set ======");
		ex.set.forEach(System.out::println);

		System.out.println("===== list ======");
		ex.list.forEach(System.out::println);

		System.out.println("===== map ======");
		ex.map.forEach((k, v) -> System.out.println(k + "::" + v));

		ctx.close();
	}
}
