package com.java.ha.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.ha.service.BoardServiceI;
import com.java.ha.service.HarinService;
import com.java.ha.service.LoginServiceI;

@Controller
public class DataController{
	
	@Autowired
	BoardServiceI bsi;
	
	@Autowired
	HarinService hsi;
	
	@Autowired
	LoginServiceI lsi;

	// 데이터 처리 하기 위하여 만든 URL
	/*
	@RequestMapping("/board/{key}")
	public String board(@PathVariable String key, HttpServletRequest req, HttpServletResponse res, Model model) {
		
		model.addAttribute("resultss", bsi.callData(key, req, res));
		return "board/board";
	}
	*/
	
	@RequestMapping("/login/{key}")
	public String login(@PathVariable String key, HttpServletRequest req, HttpServletResponse res, ModelMap model) {
		List<HashMap<String,Object>> returnList = lsi.callData(key, req, res);
		model.addAttribute("result",returnList);

		return "/login/login";
	}
	
	@RequestMapping("/board/{key}")
	public String board(@PathVariable String key, HttpServletRequest req, HttpServletResponse res, Model model) {
		model.addAttribute("result", hsi.callData(key,req,res));
		System.out.println();
		model.addAttribute("postList",hsi.getList(req,res).get(0).get("result"));
		return "board/board";
	}
	
}
