package com.acid6001.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acid6001.domain.Criteria;
import com.acid6001.domain.ReplyCriteria;
import com.acid6001.domain.ReplyVo;
import com.acid6001.mapper.BoardMapper;
import com.acid6001.mapper.ReplyMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ReplyServiceImpl implements ReplyService {
	private ReplyMapper mapper;
	private BoardMapper boardMapper;

	@Override
	@Transactional
	public void register(ReplyVo vo) {
		// 작업 1
		boardMapper.upddateReplyCnt(vo.getBno(), 1);
		// 작업 2
		mapper.insert(vo);
	}

	@Override
	public ReplyVo get(Long rno) {
		return mapper.read(rno);
	}

	@Override
	public boolean modify(ReplyVo vo) {
		return mapper.update(vo) > 0;
	}

	@Transactional
	@Override
	public boolean remove(Long rno) {
		boardMapper.upddateReplyCnt(get(rno).getBno(), -1);
		return mapper.delete(rno) > 0;
	}

	@Override
	public List<ReplyVo> getList(ReplyCriteria cri, Long bno) {
		return mapper.getList(bno, cri);
	}

}
