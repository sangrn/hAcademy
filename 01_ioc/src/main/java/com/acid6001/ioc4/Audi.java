package com.acid6001.ioc4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Component
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Audi {
	@Autowired
	private Car car;
}
