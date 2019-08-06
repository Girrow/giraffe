package kr.gudi.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.gudi.web.service.BoardServiceInterface;
import kr.gudi.web.service.UserServiceInterface;

@Controller
public class DataController {
	
	@Autowired
	UserServiceInterface usi;
	
	@Autowired
	BoardServiceInterface bsi;
	
	// 데이터 처리 하기 위하여 만든 URL
	@RequestMapping("/User/{key}")
	public void user(@PathVariable String key, HttpServletRequest req, HttpServletResponse res) {
		usi.callData(key, req, res);
	}
	
	// 데이터 처리 하기 위하여 만든 URL
	@RequestMapping("/Board/{key}")
	public void board(@PathVariable String key, HttpServletRequest req) {
		bsi.callData(key);
	}
	
}
