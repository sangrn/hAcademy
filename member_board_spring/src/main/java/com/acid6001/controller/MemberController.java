package com.acid6001.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import service.BoardServiceImpl;
import service.MemberService;
import service.MemberServiceImpl;
import vo.Criteria;
import vo.Member;

@Controller
public class MemberController {
	MemberService service = new MemberServiceImpl();

	@GetMapping("/login")
	public String loginForm(Model model) {
		return "member/login";
	}

	@PostMapping("/login")
	public String login(Model model, String id, String pwd, HttpServletRequest req, HttpServletResponse resp) {
		String msg = "";
		String redirectUrl = "login";

		if (service.login(id, pwd)) {
			HttpSession session = req.getSession();
			session.setAttribute("member", service.findBy(id));
			msg = "성공";

			// 아이디 저장
			Cookie cookie = new Cookie("savedId", id);
			cookie.setMaxAge(req.getParameter("savedId") == null ? 0 : 60 * 60 * 24 * 365); // 1년
			resp.addCookie(cookie);

			redirectUrl = "/";

		} else {
			msg = "로그인 실패";
		}
		model.addAttribute("msg", msg);
		return "redirect:/" + redirectUrl;

	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	@GetMapping("idValid")
	public @ResponseBody Integer idValid(String id) {
		return service.findBy(id) == null ? 1 : 0;
	}

	@GetMapping("joing")
	protected String joing() throws ServletException, IOException {
		return "member/join";
	}

	@PostMapping("join")
	protected String doPost(Member member) throws ServletException, IOException {
		service.join(member);
		return "redirect:/login";
	}

}
