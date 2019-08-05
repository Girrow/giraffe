package com.java.ha.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.java.ha.service.PostingServiceI;
import com.java.ha.service.UserServiceI;
import com.java.ha.vo.PostingVO;
import com.java.ha.vo.UserVO;

import net.sf.json.JSONObject;

@Controller
public class MainController {
	
	
	@Autowired
	PostingServiceI postingService;
	
	@Autowired
	UserServiceI userService;
	
	
	@RequestMapping("/")
	public String main() {
		return "20190729";
	}
	
	@RequestMapping(value="/",method = RequestMethod.POST)
	public void selectPost(HttpServletResponse res) {
		/*이 부분 수정*/
		List<Map<String,String>> viewMap = postingService.selectAllOfPost();
		res.setCharacterEncoding("UTF-8");
		res.setContentType("application/json");
		try {
			if(viewMap.size()==0) {
				System.out.println("현재값 비어있음");
				res.getWriter().write("11");
			}else {
				Map<String,List> result = new HashMap<String,List>();
				result.put("result",viewMap);
				JSONObject json = JSONObject.fromObject(result);
				res.getWriter().write(json.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/insert",method = RequestMethod.POST)
	public void insertPost(PostingVO post,HttpServletResponse res) {
		System.out.println("들어온 값 ="+post);
		postingService.insertPost(post);
		try {
			res.getWriter().write("success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/update",method = RequestMethod.POST)
	public void updatePost(PostingVO post,HttpServletRequest req,HttpServletResponse res){
//		String postNo=req.getParameter("postNo");
//		String content=req.getParameter("content");
//		String writer=req.getParameter("writer");
		String index=Integer.toString((Integer.parseInt(req.getParameter("index"))+1));
//		req.getParameter("writer");
//		PostingVO post = new PostingVO();
//		post.setPostNo(postNo);
//		post.setContent(content);
//		post.setWriter(writer);
		System.out.println("Writer =" +post.getWriter());
		postingService.updatePost(post, index);
		
		try {
			res.getWriter().write("success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value="/delete",method = RequestMethod.POST)
	public void deletePost(HttpServletRequest req,HttpServletResponse res) {
		String index=Integer.toString((Integer.parseInt(req.getParameter("index"))+1));
		postingService.deletePost(index);
		try {
			res.getWriter().write("삭-제");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public void addUser(HttpServletRequest req,HttpServletResponse res) {
//		String switchFlag=req.getParameter("switchFlag");
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		System.out.printf("[%s,%s]",username,password);
		
		UserVO user=new UserVO();
		user.setUsername(username);
		user.setPassword(password);
		userService.insertUser(user);
//		userDAO.setUser(user);
	}
	
	@RequestMapping(value="/loginCk",method = RequestMethod.POST)
	public void checkUser(HttpServletRequest req,HttpServletResponse res) {
//		System.out.println(userDAO.getUserList().toString());
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		System.out.printf("[%s,%s]",username,password);
		
		UserVO user=new UserVO();
		user.setUsername(username);
		user.setPassword(password);
//		System.out.println("select = "+userService.selectUser(user));
		try {
			if(true) {
				JSONObject json = JSONObject.fromObject(userService.selectUser(user));
				res.setContentType("application/json; charset=utf-8");
				res.getWriter().write(json.toString());
			}else {
				res.getWriter().write("fail");
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
