package com.java.ha.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.ha.dao.CommonDaoI;
import com.java.ha.util.HttpUtil;

@Service
public class BoardService implements BoardServiceI {
	
	
	@Autowired
	CommonDaoI cdi;
	
	@Override
	public List<HashMap<String,Object>> callData(String key, HttpServletRequest req, HttpServletResponse res) {
		List<HashMap<String,Object>> resultList = new ArrayList<HashMap<String,Object>>();
		HashMap<String,Object> statusMap = new HashMap<String,Object>();
		System.out.println("callData : " + key);
		HashMap<String, Object> paramMap = HttpUtil.getParams(req);
		if((boolean) paramMap.get("status")) {
			if("select".equals(key)) {
				System.out.println("select");
				
			}else if("insert".equals(key)) {
				int resultN =insertPost(req);
				if(resultN>0) {
					System.out.println("삽입성공");
					statusMap.put("status", "insertSuccess");
					resultList.add(statusMap);
				}else {
					System.out.println("삽입실패");
				}
				
				
			}else if("update".equals(key)) {
				System.out.println("update");
				
				
			}else if("delete".equals(key)) {
				System.out.println("delete");
				
				
			}else {
				System.out.println("여기 오지마세요");	
			}
			
			
		}else {
			System.out.println("status False");
		}
		return resultList;
	}
	
	public int insertPost(HttpServletRequest req){
		HashMap<String,Object> paramMap = new HashMap<String,Object>();
		HashMap<String,Object> params = new HashMap<String,Object>();
		String writer=req.getParameter("writer");
		String content=req.getParameter("content");
		
		paramMap.put("queryType", "insert");
		paramMap.put("queryTarget", "post.insert");
		params.put("writer",writer);
		params.put("content", content);
		paramMap.put("params", params);
		int result =(int)cdi.commonDB(paramMap).get("result");
		return result;
	}
}
