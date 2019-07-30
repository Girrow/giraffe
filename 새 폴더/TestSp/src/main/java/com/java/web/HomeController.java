package com.java.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

//	view의 value도 중복되어서는 안됨.
	@Autowired
	HomeService hs;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {

//		HomeService a = new HomeService();
		Map<String, Object> map = hs.getData();
		System.out.println(map);
		return "home";
	}

	@RequestMapping(value = "/J", method = RequestMethod.GET)
	public String jquery() {
		return "JQuery";
	}

}
