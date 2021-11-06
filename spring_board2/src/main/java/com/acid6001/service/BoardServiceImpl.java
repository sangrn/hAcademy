package com.acid6001.service;

import java.util.List; 

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acid6001.domain.AttachVo;
import com.acid6001.domain.BoardVo;
import com.acid6001.domain.Criteria;
import com.acid6001.mapper.AttachMapper;
import com.acid6001.mapper.BoardMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {

	private BoardMapper boardMapper;
	private AttachMapper attachMapper;

	@Override @Transactional
	public void register(BoardVo boardVo) {
		boardMapper.insertSelectKey(boardVo);
		
		boardVo.getAttachs().forEach(attach-> {
			attach.setBno(boardVo.getBno());
			attachMapper.insert(attach);
		});
	}

	@Override
	public BoardVo get(Long bno) {
		return boardMapper.read(bno);
	}

	@Override @Transactional
	public boolean modify(BoardVo boardVo) {
		boolean result = boardMapper.update(boardVo) > 0;
		
		attachMapper.deleteAll(boardVo.getBno());
		if(result) {
			boardVo.getAttachs().forEach(vo-> {
				vo.setBno(boardVo.getBno());
				attachMapper.insert(vo);
			});
		}
		return result;
	}

	@Override @Transactional
	public boolean remove(Long bno) {
		attachMapper.deleteAll(bno);
		return boardMapper.delete(bno) > 0;
	}

	@Override
	public List<BoardVo> getList(Criteria cri) {
		return boardMapper.getListWithPaging(cri);
	}

	@Override
	public int getTotal(Criteria cri) {
		return boardMapper.getTotalCount(cri);
	}

	@Override
	public List<AttachVo> getAttachs(Long bno) {
		return attachMapper.findByBno(bno);
	}
	

}
