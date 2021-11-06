package com.acid6001.service;

import static org.junit.Assert.assertNotNull; 

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.acid6001.domain.BoardVo;
import com.acid6001.domain.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {
	@Setter
	@Autowired
	private BoardService service;
	
	@Test
	public void testExist() {
		assertNotNull(service);
	}
	
	@Test
	public void testGetList() {
		service.getList(new Criteria()).forEach(log::info);
	}
	
	@Test
	public void testregister() {
		BoardVo boardVo = new BoardVo();
		boardVo.setTitle("서비스 테스트 엄!");
		boardVo.setContent("서비스 테스트 준!");
		boardVo.setWriter("서비스 테스트 식!");
		service.register(boardVo);
	}
	
	@Test
	public void testGet() {
		log.info(service.get(10L));
	}
	
	@Test
	public void testmodify() {
		BoardVo boardVo = new BoardVo();
		boardVo.setTitle("엄!");
		boardVo.setContent("준!");
		boardVo.setWriter("식!");
		boardVo.setBno(10L);
		service.modify(boardVo);
	}
	
	@Test
	public void testremove() {
		log.info(service.get(9L));
		log.info(service.remove(9L));
		log.info(service.get(9L));
		
	}
	
	@Test
	public void testgetTotal() {
		log.info(service.getTotal(new Criteria()));
	}
}
