package com.acid6001.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.acid6001.domain.AttachVo;
import com.acid6001.domain.BoardVo;
import com.acid6001.domain.Criteria;
import com.acid6001.domain.PageDTO;
import com.acid6001.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("board/*")
@AllArgsConstructor
public class BoardController {
	private BoardService service;
	private UploadController uploadcontroller;

	@GetMapping("list")
	public void list(Model model, Criteria cri) {
		log.info("board.list");
		model.addAttribute("list", service.getList(cri));
		model.addAttribute("page", new PageDTO(service.getTotal(cri), cri));

	}

	@GetMapping("register")
	public void register() {

	}

	@PostMapping("register")
	public String register(BoardVo boardVo, RedirectAttributes rttr) {
		log.info("register ::" + boardVo);
		service.register(boardVo);
		log.info("register ::" + boardVo);
		rttr.addFlashAttribute("result", boardVo.getBno());
		return "redirect:/board/list";
	}

	@GetMapping({ "get", "modify" })
	public void get(@RequestParam("bno") Long bno, Model model, @ModelAttribute("cri") Criteria cri) {
		log.info("get");
		model.addAttribute("board", service.get(bno));
	}

	@PostMapping("modify")
	public String modify(BoardVo boardVo, RedirectAttributes rttr, Criteria cri) {
		log.info("modify ::" + boardVo);
		service.modify(boardVo);
		if (service.modify(boardVo)) {
			rttr.addFlashAttribute("result", "success");
		}
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		return "redirect:/board/list";
	}

	@PostMapping("remove")
	public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr, Criteria cri) {
		log.info("remove ::" + bno);
		List<AttachVo> list = service.getAttachs(bno);
		if (service.remove(bno)) {
			list.forEach(vo-> {
				uploadcontroller.deleteFile(vo.getFullPath(), vo.isImage());
			});
			rttr.addFlashAttribute("result", "success");
		}
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		return "redirect:/board/list";
	}
	
	@GetMapping("getAttachs/{bno}")
	public @ResponseBody List<AttachVo> getAttachs(@PathVariable Long bno) {
		return service.getAttachs(bno);
	}

}
