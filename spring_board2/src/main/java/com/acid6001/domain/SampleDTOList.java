package com.acid6001.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Data;

@Data
public class SampleDTOList {
	@Autowired 
	private List<SampleDTO>list;
}
