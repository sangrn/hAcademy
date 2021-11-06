package com.acid6001.smallmart.advice;

import org.springframework.aop.ThrowsAdvice;

public class ThrowsLogging implements ThrowsAdvice {
	public void afterThrowing(Throwable throwable) {
		System.out.println("예외발생 로깅");
	}
}
