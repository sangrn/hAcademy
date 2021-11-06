package com.acid6001.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardController {
	// @Autowired
	// private BoardService boardService;

	@RequestMapping("/board/list")
	public String getList() {
		return "board/list";
	}

	@RequestMapping("/board/detail")
	public String detail(Model model, Long bno) {
		model.addAttribute("value", bno);
		return "board/detail";
	}

	@RequestMapping("/board/detail/{bno}")
	public String detail2(Model model, @PathVariable Long bno) {
		model.addAttribute("value", bno);
		return "board/detail";
	}

}
