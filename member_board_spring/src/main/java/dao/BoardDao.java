package dao;

import java.sql.Connection; 
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.DBConn;
import vo.Attach;
import vo.Board;
import vo.Criteria;
import vo.Member;

public class BoardDao {
	// CRUD
	public Long insert(Board board) {
		Connection conn = DBConn.getConnection();
		Long bno = null;
		try {
			conn.setAutoCommit(false);

			// 글 번호를 먼저 발급
			ResultSet rs = conn.prepareStatement("SELECT SEQ_BOARD.NEXTVAL FROM DUAL").executeQuery();
			rs.next();
			bno = rs.getLong(1);

			// 글 작성
			PreparedStatement pstmt = conn.prepareStatement(
					"INSERT INTO TBL_BOARD(BNO, TITLE, CONTENT, ID, CATEGORY) VALUES (?, ?, ?, ?, ?)");
			int idx = 1;
			pstmt.setLong(idx++, bno);
			pstmt.setString(idx++, board.getTitle()); // 1
			pstmt.setString(idx++, board.getContent()); // 2
			pstmt.setString(idx++, board.getId()); // 3
			pstmt.setLong(idx++, board.getCategory()); // 4

			// select : executeQuery, insert update delete : executeUpdate
			pstmt.executeUpdate();
			conn.commit();
			conn.setAutoCommit(true);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bno;
	}

	public Board read(Long bno) {
		Connection conn = DBConn.getConnection();
		Board board = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(
					"SELECT BNO, TITLE, CONTENT, REGDATE, ID, CATEGORY\r\n" + "FROM TBL_BOARD\r\n" + "WHERE BNO = ?");
			pstmt.setLong(1, bno);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				int idx = 1;
				board = new Board(rs.getLong(idx++), rs.getString(idx++), rs.getString(idx++), rs.getDate(idx++),
						rs.getString(idx++), rs.getLong(idx++), null, null);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return board;
	}

	public List<Board> list() {
		Connection conn = DBConn.getConnection();
		List<Board> list = new ArrayList<>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT \r\n" + "	BNO, TITLE, REGDATE, ID, CATEGORY \r\n"
					+ "FROM TBL_BOARD\r\n" + "WHERE BNO > 0\r\n" + "ORDER BY 1 DESC");
			while (rs.next()) {
				int idx = 1;
				Long bno = rs.getLong(idx++);
				String title = rs.getString(idx++);
				Date regDate = rs.getDate(idx++);
				String id = rs.getString(idx++);
				Long category = rs.getLong(idx++);

				Board board = new Board(bno, title, regDate, id, category);
				list.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Board> list(Criteria cri) {
		Connection conn = DBConn.getConnection();
		List<Board> list = new ArrayList<>();
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("WITH B AS (\r\n");
			sql.append("	SELECT \r\n");
			sql.append("		ROWNUM RN, TB.*\r\n");
			sql.append("	FROM TBL_BOARD TB \r\n");
			sql.append("	WHERE BNO > 0\r\n");
			sql.append("	AND CATEGORY = ? \r\n");
			sql.append("	AND ROWNUM <= ? * ?\r\n");
			sql.append("	ORDER BY BNO DESC\r\n");
			sql.append(")\r\n");
			sql.append("SELECT BNO, TITLE, REGDATE, ID, CATEGORY, ");
			sql.append("(SELECT COUNT(*) FROM TBL_REPLY R WHERE R.BNO = B.BNO) REPLYCNT,");
			sql.append(" CONTENT FROM B\r\n");
			sql.append("WHERE RN > (? - 1) * ?");

			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			int idx = 1;
			pstmt.setInt(idx++, cri.getCategory());
			pstmt.setInt(idx++, cri.getPageNum());
			pstmt.setInt(idx++, cri.getAmount());
			pstmt.setInt(idx++, cri.getPageNum());
			pstmt.setInt(idx++, cri.getAmount());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				idx = 1;
				Long bno = rs.getLong(idx++);
				String title = rs.getString(idx++);
				Date regDate = rs.getDate(idx++);
				String id = rs.getString(idx++);
				Long category = rs.getLong(idx++);

				Board board = new Board(bno, title, regDate, id, category);
				board.setReplyCnt(rs.getInt(idx++));
				board.setContent(rs.getString(idx++));
				list.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public void update(Board board) {
		Connection conn = DBConn.getConnection();
		try {
			// 글 작성
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE TBL_BOARD SET\r\n" + "	TITLE = ?,\r\n" + "	CONTENT = ?\r\n" + "WHERE BNO = ?");
			int idx = 1;
			pstmt.setString(idx++, board.getTitle()); // 1
			pstmt.setString(idx++, board.getContent()); // 2
			pstmt.setLong(idx++, board.getBno()); // 3

			// select : executeQuery, insert update delete : executeUpdate
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Long bno) {
		Connection conn = DBConn.getConnection();
		try {
			// 글 작성
			PreparedStatement pstmt = conn.prepareStatement("DELETE TBL_BOARD WHERE BNO = ?");
			int idx = 1;
			pstmt.setLong(idx++, bno); // 3

			// select : executeQuery, insert update delete : executeUpdate
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 첨부 파일

	public void writeAttach(Attach attach) {
		Connection conn = DBConn.getConnection();
		try {
			// 글 작성
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO TBL_ATTACH VALUES (?, ?, ?, ?)");
			int idx = 1;
			pstmt.setString(idx++, attach.getUuid()); // 3
			pstmt.setString(idx++, attach.getOrigin()); // 3
			pstmt.setLong(idx++, attach.getBno()); // 3
			pstmt.setString(idx++, attach.getPath()); // 4

			// select : executeQuery, insert update delete : executeUpdate
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Attach> readAttach(Long bno) {
		Connection conn = DBConn.getConnection();
		List<Attach> list = new ArrayList<>();
		try {
			PreparedStatement pstmt = conn.prepareStatement("SELECT UUID, ORIGIN, PATH FROM TBL_ATTACH WHERE BNO = ?");
			pstmt.setLong(1, bno);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int idx = 1;
				String uuid = rs.getString(idx++);
				String origin = rs.getString(idx++);
				String path = rs.getString(idx++);

				Attach attach = new Attach(uuid, origin, bno, path);
				list.add(attach);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Attach> readAttachByPath(String path) {
		Connection conn = DBConn.getConnection();
		List<Attach> list = new ArrayList<>();
		try {
			PreparedStatement pstmt = conn.prepareStatement("SELECT UUID, ORIGIN, PATH FROM TBL_ATTACH WHERE PATH = ?");
			pstmt.setString(1, path);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int idx = 1;
				String uuid = rs.getString(idx++);
				String origin = rs.getString(idx++);

				Attach attach = new Attach(uuid, origin, null, path);
				list.add(attach);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public String findOriginBy(String uuid) {
		Connection conn = DBConn.getConnection();
		String origin = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement("SELECT ORIGIN FROM TBL_ATTACH WHERE UUID = ?");
			pstmt.setString(1, uuid);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				origin = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return origin;
	}

	public static void main(String[] args) {
		BoardDao dao = new BoardDao();

		// 글 작성 테스트
//		Long bno = dao.insert(new Board(null, "dao main에서 작성된 글 제목", 
//				"dao main에서 작성된 글 내용", null, "babamba", 1L));
//		System.out.println(bno);

		// 단일 조회 테스트

		// 목록 조회 테스트
//		dao.list().forEach(System.out::println);

//		dao.update(new Board(2L, "수정글", "수정내용"));
//		System.out.println(dao.read(2L));

		// 페이지 테스트
		List<Board> list = dao.list(new Criteria());
		for (Board m : list) {
			System.out.println(m);
		}
//		System.out.println(dao.getCount());
	}

	public int getCount(Criteria cri) {
		Connection conn = DBConn.getConnection();
		int count = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(*) FROM TBL_BOARD WHERE CATEGORY = ?");
			pstmt.setInt(1, cri.getCategory());
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
}