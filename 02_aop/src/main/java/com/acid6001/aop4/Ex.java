package com.acid6001.aop4;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
//import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class Ex {
	public static void main(String[] args) {
		One one = new One();

		System.out.println("=================== Advicor 프록시 결과 ===================");
		ProxyFactory factory = new ProxyFactory();

		AspectJExpressionPointcut pc = new AspectJExpressionPointcut();
		pc.setExpression("execution (* second(..))");

		// AspectJExpressionPointcutAdvisor advisor = new
		// AspectJExpressionPointcutAdvisor();
		// advisor.setAdvice(new LoggingAdv());
		// advisor.setExpression("execution (* second(..)");

		Advisor advisor = new DefaultPointcutAdvisor(pc, new LoggingAdv());

		factory.addAdvisor(advisor);
		factory.setTarget(one);
		One oneP1 = (One) factory.getProxy();

		oneP1.first();
		oneP1.second();

	}
}
