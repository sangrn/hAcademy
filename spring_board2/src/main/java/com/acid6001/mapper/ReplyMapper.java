package com.acid6001.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.acid6001.domain.Criteria;
import com.acid6001.domain.ReplyCriteria;
import com.acid6001.domain.ReplyVo;

public interface ReplyMapper {
	int insert(ReplyVo vo);

	ReplyVo read(Long rno);

	int update(ReplyVo vo);

	int delete(Long rno);

	List<ReplyVo> getList(@Param("bno") Long bno, @Param("cri") Criteria cri);
}
