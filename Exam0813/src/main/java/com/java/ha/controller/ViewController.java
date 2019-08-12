package com.java.ha.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
	
	@RequestMapping("/")
	public String firstView(HttpServletResponse response) {
		return "redirect:/login";
	}
	
	@RequestMapping("/board")
	public String view() {
		return "board/board";
	}
	
	@RequestMapping("/login")
	public String loginView() {
		return "login/login";
	}
}
