package com.java.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.java.Bean.PostingBean;
import com.java.Bean.UserBean;
import com.java.Model.PostingDAO;
import com.java.Model.UserDAO;

import net.sf.json.JSONObject;

@Controller
public class PostingController {
	
	@Autowired
	PostingDAO postingDAO;
	@Autowired
	UserDAO userDAO;
	
	@RequestMapping("/")
	public String main() {
		return "20190729";
	}
	
	@RequestMapping(value="/",method = RequestMethod.POST)
	public void selectPost(HttpServletRequest req,HttpServletResponse res) {
		List<Map> viewMap = postingDAO.selectAllOfPost();
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
	public void insertPost(HttpServletRequest req,HttpServletResponse res) {
		String postNo=req.getParameter("postNo");
		String content=req.getParameter("content");
		String writer=req.getParameter("writer");
		
		PostingBean post = new PostingBean();
		post.setPostNo(postNo);
		post.setContent(content);
		post.setWriter(writer);
		postingDAO.insertPost(post);
		
		try {
			res.getWriter().write("success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping(value="/update",method = RequestMethod.POST)
	public void updatePost(HttpServletRequest req,HttpServletResponse res){
		String postNo=req.getParameter("postNo");
		String content=req.getParameter("content");
		String writer=req.getParameter("writer");
		int index=Integer.parseInt(req.getParameter("index"));
		
		PostingBean post = new PostingBean();
		post.setPostNo(postNo);
		post.setContent(content);
		post.setWriter(writer);
		postingDAO.updatePost(post, index);
		
		try {
			res.getWriter().write("success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value="/delete",method = RequestMethod.POST)
	public void deletePost(HttpServletRequest req,HttpServletResponse res) {
		int index=Integer.parseInt(req.getParameter("index"));
		postingDAO.deletePost(index);
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
		
		UserBean user=new UserBean();
		user.setUsername(username);
		user.setPassword(password);
		userDAO.setUser(user);
	}
	
	@RequestMapping(value="/loginCk",method = RequestMethod.POST)
	public void checkUser(HttpServletRequest req,HttpServletResponse res) {
		System.out.println(userDAO.getUserList().toString());
	}
}
