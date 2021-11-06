package com.acid6001.mapper;

import static org.junit.Assert.assertNotNull;

import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.acid6001.domain.BoardVo;
import com.acid6001.domain.Criteria;
import com.acid6001.domain.ReplyCriteria;
import com.acid6001.domain.ReplyVo;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {
	@Setter
	@Autowired
	private ReplyMapper mapper;
	
	@Test
	public void testExist() {
		assertNotNull(mapper);
	}

	@Test
	public void testInsert() {
		IntStream.range(0, 20).forEach(i-> {
			ReplyVo vo = new ReplyVo();
			
			// 게시물의 번호
			
			vo.setBno(77832L);
			vo.setReply("댓글 테스트"+ i);
			vo.setReplyer("댓글러" + i);
			
			mapper.insert(vo);
		});
	}
	@Test
	public void testRead() {
		log.info(mapper.read(1L));
	}
	
	@Test
	public void testUpdate() {
		ReplyVo vo = new ReplyVo();
		vo.setReply("수정된 댓글");
		vo.setReplyer("수정자");
		vo.setRno(3L);
		mapper.update(vo);
	
	}
	@Test
	public void testRemove() {
		log.info(mapper.delete(2L));
	}
	
	@Test
	public void testGetList() {
		ReplyCriteria criteria = new ReplyCriteria();
//		criteria.setLastRno(11L);
		mapper.getList(77832L, criteria).forEach(log::info);
	}
}
