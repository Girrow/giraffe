package com.java.test;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.java.test2.Posting;
import com.java.test2.PostingDAO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	PostingDAO postingDAO;
	@Autowired
	Posting a;
	
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		return "home";
	}
	
	@RequestMapping(value="/exam", method = RequestMethod.GET)
	public String examToHome(Model model) {

		return "20190725";
	}
	
	@RequestMapping(value="/exam2", method = RequestMethod.GET)
	public String examToHome2(HttpServletRequest req,Model model) {
		String tableNo=req.getParameter("tableNo");
		String content=req.getParameter("content");
		a.setTableNo(Integer.parseInt(tableNo));
		a.setContent(content);
		postingDAO.insert(a);
		System.out.println("LIst ="+postingDAO.map);
		return "20190725";
	}
	
	
}
