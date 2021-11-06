package com.acid6001.mapper;

import java.util.List;

import com.acid6001.domain.AttachVo;

public interface AttachMapper {
	void insert(AttachVo vo);
	void delete(String uuid);
	List<AttachVo> findByBno(Long bno);
	AttachVo findBy(String uuid);
	void deleteAll(Long bno); // 게시글 하나에 있는거 일괄삭제
	
	List<AttachVo> getOldFiles();
}
