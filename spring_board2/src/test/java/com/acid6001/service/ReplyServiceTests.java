package com.acid6001.service;

import static org.junit.Assert.assertNotNull;

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
public class ReplyServiceTests {
	@Setter
	@Autowired
	private ReplyService service;

	@Test
	public void testClass() {
		log.info(service);
		log.info(service.getClass().getSimpleName());
	}
	
	@Test
	public void testExist() {
		assertNotNull(service);
	}

	@Test
	public void testGetList() {
		service.getList(new ReplyCriteria(), 77829L).forEach(log::info);
//		assertNotNull(service.getList(new ReplyCriteria(), 77829L));
	}

	@Test
	public void testregister() {
		ReplyVo replyVo = new ReplyVo();
		replyVo.setReply("아아아아아");
		replyVo.setReplyer("이거뭐야도대체");
		replyVo.setBno(77829L);
		service.register(replyVo);
	}

	@Test
	public void testGet() {
		log.info(service.get(10L));
	}

	@Test
	public void testmodify() {
		ReplyVo replyVo = new ReplyVo();
		replyVo.setReply("서비스 테스트 수정글 제목");
		replyVo.setReplyer("서비스 테스터");
		replyVo.setRno(77832L);
		service.modify(replyVo);

	}

	@Test
	public void testremove() {
		log.info(service.remove(9L));

	}

}
