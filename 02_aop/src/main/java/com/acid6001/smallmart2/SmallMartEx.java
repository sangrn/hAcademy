package com.acid6001.smallmart2;

import java.lang.reflect.Method;

import org.springframework.aop.Advisor;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import com.acid6001.aop3.LoggingAdv;
import com.acid6001.aop3.SimplePC;
import com.acid6001.smallmart.advice.AfterLogging;
import com.acid6001.smallmart.advice.AroundLogging;
import com.acid6001.smallmart.advice.BeforeLogging;
import com.acid6001.smallmart.advice.ThrowsLogging;

public class SmallMartEx {
	public static void main(String[] args) {
		SmallMart mart = new SmallMartImpl();

		// 프록시 객체
		ProxyFactory factory = new ProxyFactory();
		factory.addAdvisor(new DefaultPointcutAdvisor(new StaticMethodMatcherPointcut() {

			@Override
			public boolean matches(Method arg0, Class<?> arg1) {
				// TODO Auto-generated method stub
				return arg0.getName().endsWith("2");
			}
		}, new MethodBeforeAdvice() {

			@Override
			public void before(Method arg0, Object[] arg1, Object arg2) throws Throwable {
				// TODO Auto-generated method stub
				System.out.println("?");
			}
		}));

		factory.setTarget(mart);

		SmallMart proxy = (SmallMart) factory.getProxy();

		try {
			// mart.getProduct("커피");
			proxy.getProduct("커피");

			proxy.getProduct2("커피2");
			// mart.getProduct(null);
			// proxy.getProduct(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
