package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberServiceImpl;

@WebServlet("/idValid")
public class IdValid extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		int result = new MemberServiceImpl().findBy(id) == null ? 1 : 0;
		resp.getWriter().print(result); 

		// 1521 9090
	}
}