package com.java.ha.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.ha.service.BoardServiceI;
import com.java.ha.service.LoginServiceI;
import com.java.ha.vo.UserVO;

@Controller
public class DataController{
	
	@Autowired
	BoardServiceI bsi;
	
	@Autowired
	LoginServiceI lsi;

	private HashMap<String,UserVO> userMap = new HashMap<String,UserVO>();
	
	// 데이터 처리 하기 위하여 만든 URL
	@RequestMapping("/board/{key}")
	public String board(@PathVariable String key, HttpServletRequest req, HttpServletResponse res, Model model) {
		model.addAttribute("result", bsi.callData(key, req, res));
		return "board/board";
	}
	
	@RequestMapping("/login/{key}")
	public String login(@PathVariable String key, HttpServletRequest req, HttpServletResponse res, ModelMap model) {
		
		model.addAttribute("result",lsi.callData(key, req, res));
		
		return "/login/login";
	}
	
}
