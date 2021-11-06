package com.acid6001.controller;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
@Log4j

// Test Controller
public class BoardControllerTests {
	@Setter
	@Autowired
	private WebApplicationContext ctx;
	private MockMvc mvc;

	@Before
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}

	@Test
	public void testExist() {
		assertNotNull(ctx);
		assertNotNull(mvc);
		log.info(ctx);
		log.info(mvc);
	}
	
	@Test
	public void testList() throws Exception {
		ModelMap map = mvc.perform(MockMvcRequestBuilders.get("/board/list")
				.param("pageNum", "2")
				.param("amount", "5")
				)
		.andReturn()
		.getModelAndView()
		.getModelMap();
		
		List<?>list = (List<?>) map.get("list");
		list.forEach(log::info);
	}
	@Test
	public void testRegister() throws Exception {
		ModelAndView mav = mvc.perform(MockMvcRequestBuilders.post("/board/register")
					.param("title", "ralo다이아간다")
					.param("content", "싱글벙글 찬호촌")
					.param("writer", "paka"))
				.andReturn()
				.getModelAndView();
		
		log.info(mav.getViewName());
	}
	
	@Test
	public void testGet() throws Exception {
		ModelMap map = mvc.perform(MockMvcRequestBuilders.get("/board/get").param("bno", "7"))
		.andReturn()
		.getModelAndView()
		.getModelMap();
		
		log.info(map.get("board"));
	}
	
	@Test
	public void testModify() throws Exception {
		ModelAndView mav = mvc.perform(
				MockMvcRequestBuilders.post("/board/modify")
					.param("title", "ralo다이아간다")
					.param("content", "봉춤맨")
					.param("writer", "ralo")
					.param("bno", "12")
					)
				.andReturn()
				.getModelAndView();
		
		log.info(mav.getViewName());
				
	}
	
	@Test
	public void testRemove() throws Exception {
		ModelAndView mav = mvc.perform(
				MockMvcRequestBuilders.post("/board/remove")
					.param("bno", "12")
					)
				.andReturn()
				.getModelAndView();
		
		log.info(mav.getViewName());
	}
}
