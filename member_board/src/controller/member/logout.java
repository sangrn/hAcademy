package controller.member;

import java.io.IOException; 

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberService;
import service.MemberServiceImpl;
import vo.Member;

@WebServlet("/logout")
public class logout extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 세션 해제
		req.getSession().invalidate();
		resp.sendRedirect("index.html");
	}

}