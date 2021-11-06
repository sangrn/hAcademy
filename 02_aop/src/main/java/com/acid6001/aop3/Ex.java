package com.acid6001.aop3;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class Ex {
	public static void main(String[] args) {
		One one = new One();
		Two two = new Two();

		// 타겟의 메서드 호출
		System.out.println("=================== 타겟결과 ===================");
		one.first();
		one.second();
		two.first();
		two.second();

		System.out.println("=================== 프록시 결과 ===================");
		ProxyFactory factory = new ProxyFactory();
		factory.addAdvice(new LoggingAdv());
		factory.setTarget(one);
		One oneP = (One) factory.getProxy();

		factory = new ProxyFactory();
		factory.addAdvice(new LoggingAdv());
		factory.setTarget(two);
		Two twoP = (Two) factory.getProxy();

		oneP.first();
		oneP.second();

		twoP.first();
		twoP.second();

		//
		System.out.println("=================== advice 프록시 결과 ===================");

		factory = new ProxyFactory();
		Advisor advisor = new DefaultPointcutAdvisor(new SimplePC(), new LoggingAdv());
		factory.addAdvisor(advisor);
		factory.setTarget(one);
		One oneP1 = (One) factory.getProxy();

		factory = new ProxyFactory();
		advisor = new DefaultPointcutAdvisor(new SimplePC(), new LoggingAdv());
		factory.addAdvisor(advisor);
		factory.setTarget(two);
		Two twoP1 = (Two) factory.getProxy();

		oneP1.first();
		oneP1.second();

		twoP1.first();
		twoP1.second();

	}
}
