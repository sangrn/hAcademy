package com.acid6001.aop1;

import java.lang.reflect.Proxy;

public class HelloWorldEx {
	public static void main(String[] args) {
		// 원본 객체
		HelloWorld helloWorld = new HelloWorldImpl();
		System.out.println("============ 원본 ============");
		helloWorld.sayHello("장지환");
		// System.out.println(helloWorld);

		// 프록시 객체
		HelloWorld helloWorld2 = (HelloWorld) Proxy.newProxyInstance(HelloWorld.class.getClassLoader(),
				new Class[] { HelloWorld.class }, new HelloWorldHandler(helloWorld));
		System.out.println("============ 프록시 ============");
		helloWorld2.sayHello("지건 딱대");
		// System.out.println(helloWorld2.toString());

		// 육하원칙
		// 5W1H
		// when what where

		// joinPoint : where advice 지정될 수 있는 모든 지점
		// pointCut : advice 가 지정된 지점 
		// advice : what + when
		// -- 

	}
}
