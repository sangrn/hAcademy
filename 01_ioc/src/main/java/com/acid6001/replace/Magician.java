package com.acid6001.replace;

import lombok.Setter;

@Setter

public class Magician {
	private String magicWord;
	private MagicBox magicBox;

	void magic() {
		System.out.println("상자 안에는 무엇이 있을까요");
		System.out.println("주문 : " + magicWord);
		System.out.println("내용 : " + magicBox.getContent());
	}
}
