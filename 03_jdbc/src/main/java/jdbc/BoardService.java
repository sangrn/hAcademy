package jdbc;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;

@Service
public class BoardService {
	@Autowired
	@Setter
	private BoardDao dao;

	// 현재 시간 조회
	public String getTime() {
		return dao.getTime();
	}

	public List<Map<String, Object>> getList() {
		return dao.getList();
	}

	public void write(Board board) {
		dao.insert(board);
	}

	public void modify(Board board2) {
		// TODO Auto-generated method stub
		dao.update(board2);
	}

	public Board findBy(Long bno) {
		return dao.findBy(bno);
	}

	public List<Board> getListBoard() {
		return dao.getListBoard();
	}
}
