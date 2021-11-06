package com.acid6001.aop7;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Setter;

@Component
public class MyBean {
	@Autowired
	@Setter // setter injection 을 이용하여 bean 을 생성했다.
	
	// @Autowired
	private MyDependency dependency;

	public void run() {
		dependency.hello(6000);
		dependency.hello(4000);
		dependency.goodbye(6000);
		dependency.goodbye(4000);
	}
}
