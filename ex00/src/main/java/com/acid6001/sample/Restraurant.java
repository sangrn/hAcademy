package com.acid6001.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class Restraurant { // >> 인스턴스 생성
	@Autowired
	private Chef chef; // 포함 관계 >> 인스턴스 생성
	
}
