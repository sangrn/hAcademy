package com.acid6001.smallmart;

import org.springframework.aop.framework.ProxyFactory;

import com.acid6001.smallmart.advice.AfterLogging;
import com.acid6001.smallmart.advice.AroundLogging;
import com.acid6001.smallmart.advice.BeforeLogging;
import com.acid6001.smallmart.advice.ThrowsLogging;

public class SmallMartEx {
	public static void main(String[] args) {
		SmallMart mart = new SmallMartImpl();

		// 프록시 객체
		ProxyFactory factory = new ProxyFactory();
		factory.addAdvice(new AroundLogging());
		factory.addAdvice(new BeforeLogging());
		factory.addAdvice(new BeforeLogging());
		factory.addAdvice(new AfterLogging());
		factory.addAdvice(new AroundLogging());
		factory.addAdvice(new ThrowsLogging());

		factory.setTarget(mart);

		SmallMart proxy = (SmallMart) factory.getProxy();

		try {
			// mart.getProduct("커피");
//			proxy.getProduct("커피");
			// mart.getProduct(null);
			 proxy.getProduct(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
