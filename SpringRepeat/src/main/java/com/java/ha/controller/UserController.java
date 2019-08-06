package com.java.ha.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@RequestMapping("/")
	public String view() {
		return "User";
	}
	
	//데이터 처리를 위해 만든 url
	@RequestMapping("/insert")
	public void insert() {
		System.out.println("insert");
	}
	
	@RequestMapping("/select")
	public void select() {
		System.out.println("select");
	}
	
	@RequestMapping("/update")
	public void update() {
		System.out.println("update");
	}
	
	@RequestMapping("/delete")
	public void delete() {
		System.out.println("delete");
	}
}
