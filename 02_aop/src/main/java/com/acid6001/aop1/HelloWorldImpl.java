package com.acid6001.aop1;

public class HelloWorldImpl implements HelloWorld {

	@Override
	public void sayHello(String msg) {
		System.out.println("안녕 안녕 :: " + msg);

	}

}
