package com.acid6001.service;

import java.util.List;

import com.acid6001.domain.AttachVo;
import com.acid6001.domain.BoardVo;
import com.acid6001.domain.Criteria;

public interface BoardService {
	void register(BoardVo boardVo);
	BoardVo get(Long bno);
	boolean modify(BoardVo board);
	boolean remove(Long bno);
	List<BoardVo> getList(Criteria cri);
	int getTotal(Criteria cri);
	
	List<AttachVo> getAttachs(Long bno);
}
