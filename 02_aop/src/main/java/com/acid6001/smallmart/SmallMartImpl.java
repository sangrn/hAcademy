package com.acid6001.smallmart;

public class SmallMartImpl implements SmallMart {

	public String getProduct(String msg) throws Exception {
		System.out.println(msg);
		if (msg == null)
			throw new Exception("μμΈλ°μ!");
		return null;
	}

}
