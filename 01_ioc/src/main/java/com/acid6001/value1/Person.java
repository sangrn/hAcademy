package com.acid6001.value1;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.ToString;

@Getter
@Component
@ToString
public class Person {
	private String name = "엄준식";
	private int age = 15;
}
