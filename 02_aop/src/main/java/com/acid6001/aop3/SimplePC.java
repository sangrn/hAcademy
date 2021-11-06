package com.acid6001.aop3;

import java.lang.reflect.Method;

import org.springframework.aop.support.StaticMethodMatcherPointcut;

public class SimplePC extends StaticMethodMatcherPointcut {

	@Override
	public boolean matches(Method arg0, Class<?> arg1) {
		return arg0.getName().equals("first") && arg1 == One.class;
	}

}
