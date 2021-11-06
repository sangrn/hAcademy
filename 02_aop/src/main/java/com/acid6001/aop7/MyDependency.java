package com.acid6001.aop7;

import org.springframework.stereotype.Component;

@Component
public class MyDependency {
	public void hello(int value) {
		System.out.println("hello :: " + value);
	}
	public void goodbye(int value) {
		System.out.println("goodbye :: " + value);
	}
}
