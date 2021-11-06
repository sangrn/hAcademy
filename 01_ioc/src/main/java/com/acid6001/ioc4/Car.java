package com.acid6001.ioc4;

import org.springframework.stereotype.Component;

import lombok.Setter;
import lombok.ToString;

@Component
@Setter
@ToString
public class Car {
	private String name = "abcd";
}
