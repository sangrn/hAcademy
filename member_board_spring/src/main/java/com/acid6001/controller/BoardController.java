package com.acid6001.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import service.BoardService;
import service.BoardServiceImpl;
import util.CommonConst;
import util.MyFileRenamePolicy;
import vo.Attach;
import vo.Board;
import vo.Criteria;
import vo.Member;
import vo.PageDTO;

@Controller
@RequestMapping("/board")
public class BoardController {
	BoardService service = new BoardServiceImpl();

	@GetMapping("list")
	public void list(Model model, Criteria cri) {

		model.addAttribute("list", service.list(cri));
		model.addAttribute("page", new PageDTO(service.getCount(cri), cri));

	}

	@GetMapping("detail")
	public void detail(Model model, Long bno) {
		model.addAttribute("board", new BoardServiceImpl().read(bno));
	}
	
	
	// 글쓰기 폼
	@GetMapping("write")
	protected void writeForm(){

	}

	// 글쓰기 후 프로세스
	@PostMapping("wirte")
	protected String write(HttpServletRequest req, HttpSession session) throws IOException{
		String saveDirectory = CommonConst.UPLOAD_PATH;
		String path = getPath();

		File uploadPath = new File(saveDirectory + File.separator + path);
		if (!uploadPath.exists()) {
			uploadPath.mkdirs();
		}

		int maxPostSize = 10 * 1024 * 1024;
		String encoding = "utf-8";
		FileRenamePolicy policy = new MyFileRenamePolicy();
		MultipartRequest multi = new MultipartRequest(req, uploadPath.getAbsolutePath(), maxPostSize, encoding, policy);

		Enumeration<String> files = multi.getFileNames();
		List<Attach> attachs = new ArrayList<>();
		while (files.hasMoreElements()) {
			String file = files.nextElement();
			String uuid = multi.getFilesystemName(file);
			if (uuid == null)
				continue;
			String origin = multi.getOriginalFileName(file);

			Attach attach = new Attach(uuid, origin, null, path);
			attachs.add(attach);
		}

		String title = multi.getParameter("title"); //
		String content = multi.getParameter("content"); //
		
		
		String id = ((Member) session.getAttribute("member")).getId();

		Board board = new Board(title, content, id, 1L);
		board.setAttachs(attachs);

		new BoardServiceImpl().write(board);

		return "redirect:/board/list";
	}

	private String getPath() {
		return new SimpleDateFormat("yyMMdd").format(new Date());
	}
}
