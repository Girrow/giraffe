package com.java.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.service.TestServiceI;

import net.sf.json.JSONObject;

@Controller
public class TestController {

	@Autowired
	TestServiceI tsi;

	@RequestMapping("/getData")
	public void getData(HttpServletResponse res) {
//		tsi.getData();
		try {
			JSONObject jsonObject = JSONObject.fromObject(tsi.getData());
			res.setContentType("application/json;charset=UTF-8");
			res.getWriter().write(jsonObject.toString());

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@RequestMapping("/getData2")
	public String getData2(Model model) {
		HashMap<String, Object> resultMap = tsi.getData();
		model.addAttribute("data", resultMap);
		return "json";
	}
	
	@RequestMapping("/setData")
	public String setData(Model model) {
//		HashMap<String, Object> resultMap = 
		model.addAttribute("data", tsi.setData());
		System.out.println(tsi.setData());
		return "json";
	}
}
