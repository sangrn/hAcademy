package controller.member;

import java.io.IOException; 
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.MemberService;
import service.MemberServiceImpl;
import vo.Member;

/**
 * 
 * 자주 발생되는 에러 라이브러리 탐색 실패 TOMCAT 외부 라이브러리 추가 빌드
 * 
 * preference -> server -> runtime envirements 톰캣 설치 경로 프로젝트 설정 -> targeted
 * runtime 체크 여부 -> 추가 빌드
 * 
 * 빌드 : 컴파일
 * 
 * 포트 충돌 8080
 * 
 * cmd netstat -ao 사용하려는 포트와 일치하는 pid 조회
 * 
 * taskkill -pid 포트번호의 pid -f
 * 
 * 500 error
 * 
 * 404 error 1. 서버가 정상 구동 > url 2. 서버가 비정상 구동
 * 
 * console의 오류로그
 * 
 */

@WebServlet("/loginOld")
public class LoginOld extends HttpServlet {
	List<Member> members = new ArrayList<Member>();
	{
		members.add(new Member("javaman", "1234"));
		members.add(new Member("babamba", "1234"));
		members.add(new Member("hong", "1234"));
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 로그인 폼 화면 : forwarding
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String id = req.getParameter("id");
		String pwd = req.getParameter("pwd");
		String msg = "";
		String redirectUrl = "login";

		// 어떤 타입의 변수로 두가지 경우를 처리할 수 있을까 >> boolean

		// 1. 아이디가 없다.
		if (findBy(id) == null) {
			msg = "아이디 없음";
		}
		// 2. 아이디는 있는데 비밀번호가 맞지않다.
		else if (findBy(id, pwd) == null) {
			msg = "비밀번호 불일치";
		}
		// 3. 둘다 맞다
		else {
			HttpSession session = req.getSession();
			session.setAttribute("id", id);
			//고객번호 session set 필요
			session.setAttribute("mem_num", id);
			msg = "성공";
			redirectUrl = "index.jsp";
		}

		resp.sendRedirect(redirectUrl + "?msg=" + URLEncoder.encode(msg, "utf-8"));

		// 세션 생성

//		resp.setContentType("text/html");
//		resp.setCharacterEncoding("utf-8");
//		resp.getWriter()
//			.append("<h1>method type : " + req.getMethod() + "</h1>")
//			.append("<h2>아이디 : " + id + "</h2>")
//			.append("<h2>비밀번호 : " + pwd + "</h2>")
//			.append("<a href='index.jsp'>메인으로</a>");
	}

	private Member findBy(String id) {
		Member member = null;
		for (Member m : members) {
			if (m.getId().equals(id)) {
				member = m;
				break;
			}
		}
		return member;
	}

	private Member findBy(String id, String pwd) {
		Member member = null;
		for (Member m : members) {
			if (m.getId().equals(id) && m.getPwd().equals(pwd)) {
				member = m;
				break;
			}
		}
		return member;
	}
}