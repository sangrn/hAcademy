package com.acid6001.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.acid6001.domain.BoardVo;
import com.acid6001.domain.Criteria;

public interface BoardMapper {
	// @Select("SELECT * FROM TBL_BOARD WHERE BNO > 0")
	List<BoardVo> getList();

	List<BoardVo> getListWithPaging(Criteria cri);

	void insert(BoardVo board);

	void insertSelectKey(BoardVo board);

	BoardVo read(Long bno);

	int update(BoardVo boardVo);

	int delete(Long bno);

	int getTotalCount(Criteria cri);
	
	void upddateReplyCnt(@Param("bno") Long bno, @Param("amount") int amount);

}
