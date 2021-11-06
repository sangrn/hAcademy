package com.acid6001.mapper;

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
public class BoardMapperTests {
	@Setter
	@Autowired
	private BoardMapper mapper;

	@Test
	public void testGetList() {
		mapper.getList().forEach(log::info);
		// mapper.getList();
	}

	@Test
	public void testGetListPaging() {
		Criteria cri = new Criteria();
		cri.setType("TCW");
		cri.setKeyword("엄준식");
		mapper.getListWithPaging(cri).forEach(log::info);
	}

	@Test
	public void testInsert() {
		BoardVo board = new BoardVo();
		board.setTitle("무신사 패션에디터 엄준식");
		board.setContent("음오아예~ ");
		board.setWriter("엄준식");
		mapper.insert(board); // 프록시 객체

	}

	@Test
	public void testInsertSelectKey() {
		BoardVo board = new BoardVo();
		board.setTitle("엄준식 - 셀렉트키");
		board.setContent("음오아예~ ");
		board.setWriter("엄준식");
		log.info("before :: " + board);
		mapper.insertSelectKey(board); // 프록시 객체
		log.info("after :: " + board);
	}

	@Test
	public void testRead() {
		log.info(mapper.read(5L));
	}

	@Test
	public void testUpdate() {
		BoardVo board = new BoardVo();
		board.setTitle("손인욱바보");
		board.setContent("장지환이씀");
		board.setWriter("김찬호");
		board.setBno(4L);
		log.info(mapper.update(board));
		log.info(mapper.read(4L));
	}

	@Test
	public void testDelete() {
		log.info(mapper.read(3L));
		log.info(mapper.delete(3L));
		log.info(mapper.read(3L));

	}
	
	@Test
	public void testGetTotalCount() {
		Criteria cri = new Criteria();
		cri.setType("TWC");
		cri.setKeyword("엄");
		log.info(mapper.getTotalCount(cri));
		
		
	}
}
