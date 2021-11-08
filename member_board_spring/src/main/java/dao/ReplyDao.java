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
import vo.Reply;

public class ReplyDao {
	public void insert(Reply reply) {
		Connection conn = DBConn.getConnection();
		try {
			// 글 작성
			PreparedStatement pstmt = conn.prepareStatement(
					"INSERT INTO TBL_REPLY(RNO, TITLE, CONTENT, ID, BNO) VALUES (SEQ_REPLY.NEXTVAL,?,?,?,?)");
			int idx = 1;
			pstmt.setString(idx++, reply.getTitle()); // 1 
			pstmt.setString(idx++, reply.getContent()); // 2 
			pstmt.setString(idx++, reply.getId()); // 3 
			pstmt.setLong(idx++, reply.getBno()); // 4

			// select : executeQuery, insert update delete : executeUpdate
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Reply> list(Long bno) {
		Connection conn = DBConn.getConnection();
		List<Reply> list = new ArrayList<>();
		try {
			String sql = "SELECT \r\n" + "	RNO, TITLE, CONTENT, TO_CHAR(REGDATE, 'YY/MM/DD') AS REGDATE, ID\r\n"
					+ "FROM TBL_REPLY WHERE RNO > 0 AND BNO = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, bno);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int idx = 1;
				Long rno = rs.getLong(idx++);
				String title = rs.getString(idx++);
				String content = rs.getString(idx++);
				String regDate = rs.getString(idx++);
				String id = rs.getString(idx++);

				Reply reply = new Reply(rno, title, content, regDate, id, bno);
				list.add(reply);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public Reply select(Long rno) {
		Connection conn = DBConn.getConnection();
		Reply reply = null;
		try {
			String sql = "SELECT \r\n" + "	RNO, TITLE, CONTENT, TO_CHAR(REGDATE, 'YY/MM/DD') AS REGDATE, ID, BNO\r\n"
					+ "FROM TBL_REPLY WHERE RNO = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, rno);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int idx = 1;
				rno = rs.getLong(idx++);
				String title = rs.getString(idx++);
				String content = rs.getString(idx++);
				String regDate = rs.getString(idx++);
				String id = rs.getString(idx++);
				Long bno = rs.getLong(idx++);

				reply = new Reply(rno, title, content, regDate, id, bno);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reply;
	}

	public void delete(Long rno) {
		Connection conn = DBConn.getConnection();
		try {
			// 글 작성
			PreparedStatement pstmt = conn.prepareStatement("DELETE TBL_REPLY WHERE RNO = ?");
			int idx = 1;
			pstmt.setLong(idx++, rno); // 3

			// select : executeQuery, insert update delete : executeUpdate
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}