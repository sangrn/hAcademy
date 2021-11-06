package com.acid6001.aop6;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class MyAdvice {
	public void simple(JoinPoint jp, int value) {
		if (value > 5000) {
			System.out.println("advice access!~!");
		}
	}

	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("pre arround");
		Object obj = pjp.proceed();
		System.out.println("suf arround");
		return obj;
	}
}
