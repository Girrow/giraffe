package com.java.test;

import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.java.test2.Posting;
import com.java.test2.PostingDAO;

import net.sf.json.JSONObject;

/**
 * Handles requests for the application home page.
 */


@Controller
public class HomeController {
	
	@Autowired
	PostingDAO postingDAO;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		return "home";
	}
	
	@RequestMapping(value = "/J", method = RequestMethod.GET)
	public String home2() {

		return "JQuery";
	}
	
	@RequestMapping(value="/exam", method = RequestMethod.GET)
	public String examToHome(Model model) {

		return "20190725";
	}
	
	@RequestMapping(value="/exam2", method = RequestMethod.GET)
	public void examToHome2(HttpServletRequest req, HttpServletResponse res){
		String method =req.getParameter("method");
		String tableNo="";
		String content="";
		Posting post=new Posting();
		
		switch(method) {
			case "insert":
				tableNo=req.getParameter("tableNo");
				content=req.getParameter("content");
				post.setTableNo(Integer.parseInt(tableNo));
				post.setContent(content);
				
				postingDAO.insert(post);
				System.out.println("insert");
				break;
				
			case "update":
				tableNo=req.getParameter("tableNo");
				content=req.getParameter("content");
				post.setTableNo(Integer.parseInt(tableNo));
				post.setContent(content);
				
				postingDAO.update(post);
				System.out.println("update");
				break;
				
			case "delete":
				tableNo=req.getParameter("tableNo");
				post.setTableNo(Integer.parseInt(tableNo));
				System.out.println("들어온 테이블 No ="+tableNo);
				postingDAO.delete(post);
				System.out.println("delete");
				break;
				
			case "selects":
				List<Map> viewMap = postingDAO.selectAll();
				System.out.println("select");
				System.out.println("List = "+viewMap.toString());
				/*일단 한번 해보는 곳 시작*/
				String json = "{\"my_key\": \"my_value\"}";
//				JSONObject json = JSONObject.fromObject(viewMap);
				res.setCharacterEncoding("UTF-8");
				res.setContentType("application/json");
				try {
//					PrintWriter pw = ;
					res.getWriter().write(json.toString());
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			default:
				break;
		}
		
		/*초기화*/
		tableNo="";
		content="";
		System.out.println("List 2 : "+postingDAO.selectAll().toString());
		
//		return "20190725";
	}
}





/*
@RequestMapping("/getJson")
public void getJson() {
	학생정보가져오기 학생 = new 학생정보가져오기();
	String addr="http://gudi.kr/gdj16/web/data.json";
	if(학생.Run(addr)) {
		System.out.println("Ok");
	}else{
		System.out.println("No");
	}
}
*/