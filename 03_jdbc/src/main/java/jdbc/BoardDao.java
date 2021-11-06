package jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import lombok.Setter;

@Service
public class BoardDao {
	@Autowired
	@Setter
	private JdbcTemplate template;

	public String getTime() {
		return template.queryForObject("SELECT SYSDATE FROM DUAL", String.class);
	}

	public List<Map<String, Object>> getList() {
		return template.queryForList("SELECT * FROM TBL_BOARD WHERE ROWNUM <= 10 ORDER BY BNO DESC");
	}

	public void insert(Board board) {
		Object[] objects = { board.getTitle(), board.getContent() };
		template.update("INSERT INTO TBL_BOARD(BNO, TITLE, CONTENT) VALUES (SEQ_BOARD.NEXTVAL, ?, ?)", objects);
	}

	public void update(Board board2) {
		// TODO Auto-generated method stub
		template.update("UPDATE TBL_BOARD SET TITLE = ? WHERE BNO =?", board2.getTitle(), board2.getBno());
	}

	public Board findBy(Long bno) {
		return template.queryForObject("SELECT * FROM TBL_BOARD WHERE BNO=?", new BoardMapper(), bno);
	}
	
	public List<Board> getListBoard() {
		return template.query("SELECT * FROM TBL_BOARD WHERE ROWNUM <= 10 ORDER BY BNO DESC", new BoardMapper());
	}
	

	private class BoardMapper implements RowMapper<Board> {
		@Override
		public Board mapRow(ResultSet arg0, int arg1) throws SQLException {
			Board board = new Board();
			board.setBno(arg0.getLong("bno"));
			board.setTitle(arg0.getString("title"));
			board.setContent(arg0.getString("content"));
			board.setRegDate(arg0.getString("regdate"));
			board.setId(arg0.getString("id"));
			board.setCategory(arg0.getInt("category"));
			return board;
		}

	}
}
