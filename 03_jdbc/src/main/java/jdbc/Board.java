package jdbc;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component @Data
public class Board {
	private Long bno;
	private String title;
	private String content;
	private String regDate;
	private String id;
	private Integer category;
	
	
}
