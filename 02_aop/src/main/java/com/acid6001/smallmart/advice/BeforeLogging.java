package com.acid6001.smallmart.advice;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class BeforeLogging implements MethodBeforeAdvice {

	@Override
	public void before(Method arg0, Object[] arg1, Object arg2) throws Throwable {
		String name = arg1[0].toString();
		System.out.println(arg0.getName() + " :: " + arg1[0] + " :: 사전");
	
	}

}
