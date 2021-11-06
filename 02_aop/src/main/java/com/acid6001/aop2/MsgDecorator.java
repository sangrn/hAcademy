package com.acid6001.aop2;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MsgDecorator implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation arg0) throws Throwable {
		System.out.println("전처리");
		Object obj = arg0.proceed(); // 원래 메서드의 실행
		System.out.println("후처리");
		return null;
	}
}
