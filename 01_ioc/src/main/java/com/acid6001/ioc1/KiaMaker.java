package com.acid6001.ioc1;

public class KiaMaker implements CarMaker {
	@Override
	public Car sell(Money money) {
		System.out.println("금액 : " + money.getAmount());
		return new Car("K5");
	}

}
