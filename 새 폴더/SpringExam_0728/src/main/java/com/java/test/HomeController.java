package com.java.test;

import java.util.HashMap;
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
				post.setTableNo(tableNo);
				post.setContent(content);
				
				postingDAO.insert(post);
				System.out.println("insert");
				break;
				
			case "update":
				tableNo=req.getParameter("tableNo");
				content=req.getParameter("content");
				int index=Integer.parseInt(req.getParameter("index"));
				post.setTableNo(tableNo);
				post.setContent(content);
				
				postingDAO.update(post,index);
				System.out.println("update");
				break;
				
			case "delete":
				tableNo=req.getParameter("tableNo");
				post.setTableNo(tableNo);
				System.out.println("들어온 테이블 Index ="+tableNo);
				postingDAO.delete(post);
				System.out.println("delete");
				break;
				
			case "selects":
				List<Map> viewMap = postingDAO.selectAll();
				System.out.println("select");
				res.setCharacterEncoding("UTF-8");
				res.setContentType("application/json");
				try {
					if(viewMap.size()==0) {
						System.out.println("비어있음");
						res.getWriter().print("1234");
					}else{
						Map<String,List> result = new HashMap<String,List>();
						result.put("result", viewMap);
						JSONObject json = JSONObject.fromObject(result);
						res.getWriter().write(json.toString());
					}
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
