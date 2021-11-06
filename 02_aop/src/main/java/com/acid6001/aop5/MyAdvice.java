package com.acid6001.aop5;

import java.lang.reflect.Method; 

import org.springframework.aop.MethodBeforeAdvice;

public class MyAdvice implements MethodBeforeAdvice {
	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		System.out.println("before advice !!!");

	}

}
