package com.acid6001.aop2;

public final class HelloWorldImpl implements HelloWorld {

	// @Around
	public void sayHello(String msg) {
		System.out.println("안녕 안녕 :: " + msg);

	}
}
