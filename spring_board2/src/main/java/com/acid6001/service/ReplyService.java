package com.acid6001.service;

import java.util.List; 

import com.acid6001.domain.ReplyCriteria;
import com.acid6001.domain.ReplyVo;

public interface ReplyService {
	void register(ReplyVo replyVo);

	ReplyVo get(Long rno);

	boolean modify(ReplyVo vo);

	boolean remove(Long rno);

	List<ReplyVo> getList(ReplyCriteria cri, Long bno);
}
