package com.acid6001.smallmart5;

import org.springframework.stereotype.Component;

@Component
public class SmallMartImpl implements SmallMart {

	public void getProduct(String msg) {
		System.out.println(msg);
	}

	public void getProduct2(String msg) {
		System.out.println(msg);
	}
}
