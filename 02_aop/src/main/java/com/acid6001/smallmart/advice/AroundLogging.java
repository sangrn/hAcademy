package com.acid6001.smallmart.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class AroundLogging implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation arg0) throws Throwable {
		System.out.println(arg0.getMethod() + " :: " + arg0.getArguments()[0] + " :: around 사전");
		Object obj = arg0.proceed();
		System.out.println(arg0.getMethod() + " :: " + arg0.getArguments()[0] + " :: around 사후");
		return obj;
	}

}
