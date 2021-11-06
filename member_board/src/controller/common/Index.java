package controller.common;

import java.io.IOException; 

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardServiceImpl;
import vo.Criteria;

@WebServlet("/index.html")
public class Index extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("list", new BoardServiceImpl().list(new Criteria(1, 8, 1))); 
		req.getRequestDispatcher("WEB-INF/jsp/common/index.jsp").forward(req, resp);
	}
}