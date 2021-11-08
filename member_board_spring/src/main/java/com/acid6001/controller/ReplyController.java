package com.acid6001.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import service.ReplyService;
import service.ReplyServiceImpl;
import vo.Reply;

@RestController
@RequestMapping("reply")
public class ReplyController {
	private ReplyService service = new ReplyServiceImpl();

	// 댓글 단일 조회
	@GetMapping("/")
	public Reply get(Long rno) {
		return service.get(rno);
	}

	@PostMapping("/")
	public void write(@RequestBody Reply reply) {
		System.out.println(reply);
		service.write(reply);
	}

	// 댓글 삭제
	@DeleteMapping
	public void delete(Long rno) {
		service.remove(rno);
	}

	@GetMapping("list")
	public List<Reply> getList(Long bno) {
		return service.list(bno);
	}
}
