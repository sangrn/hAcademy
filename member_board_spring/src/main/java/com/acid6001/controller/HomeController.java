package com.acid6001.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import service.BoardServiceImpl;
import vo.Criteria;

@Controller
public class HomeController {

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("list", new BoardServiceImpl().list(new Criteria(1, 8, 1)));
		return "common/index";
	}
}
