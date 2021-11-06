package com.acid6001.aop2;

import org.springframework.aop.framework.ProxyFactory;

public class HelloWorldEx {
	public static void main(String[] args) {
		// 타겟
		HelloWorld target = new HelloWorldImpl();

		// 프록시 factory
		ProxyFactory pf = new ProxyFactory();
		// 어드바이스 추가
		pf.addAdvice(new MsgDecorator());
		// 타겟 추가
		pf.setTarget(target);

		// 프록시 객체
		HelloWorld proxy = (HelloWorld) pf.getProxy();

		target.sayHello("타겟 실행");

		proxy.sayHello("프록시 실행");

		System.out.println(target);
		System.out.println(proxy);
	}
}
