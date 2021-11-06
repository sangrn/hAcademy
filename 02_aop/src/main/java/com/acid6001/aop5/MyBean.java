package com.acid6001.aop5;

import lombok.Setter;

public class MyBean {
	@Setter // setter injection 을 이용하여 bean 을 생성했다.
	private MyDependency dependency;

	public void run() {
		dependency.hello();
		dependency.goodbye();
	}
}
