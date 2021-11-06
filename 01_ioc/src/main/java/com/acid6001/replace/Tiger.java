package com.acid6001.replace;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.springframework.beans.factory.support.MethodReplacer;

public class Tiger implements MethodReplacer {

	@Override
	public Object reimplement(Object arg0, Method arg1, Object[] arg2) throws Throwable {
		System.out.println(arg0.getClass().getCanonicalName());
		System.out.println(arg1.getName());
		System.out.println(Arrays.toString(arg2));

		return "호랑이";

	}
}
