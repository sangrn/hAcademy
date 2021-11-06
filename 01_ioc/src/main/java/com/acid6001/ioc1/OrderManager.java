package com.acid6001.ioc1;

import lombok.Setter;

public class OrderManager {
	@Setter
	private CarMaker maker;

	// public OrderManager() {
	// this.maker = new KiaMaker();
	// }

	public void order() {
		Car car = maker.sell(new Money(8000));
		System.out.println("차량 이름 : " + car.getName());
	}
}
