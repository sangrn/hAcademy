package com.acid6001.smallmart2;

public class SmallMartImpl implements SmallMart {

	public String getProduct(String msg) throws Exception {
		System.out.println(msg);
		if (msg == null)
			throw new Exception("예외발생!");
		return null;
	}

	public String getProduct2(String msg) throws Exception {
		System.out.println(msg);
		if (msg == null)
			throw new Exception("예외발생!");
		return null;
	}
}
