package com.java.ha.service;

import java.net.http.HttpRequest;
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
public class LoginService implements LoginServiceI {

	@Autowired
	CommonDaoI cdi;
	@Override
	public List<HashMap<String, Object>> callData(String key, HttpServletRequest req, HttpServletResponse res) {
		List<HashMap<String,Object>> resultList = new ArrayList<HashMap<String,Object>>();
		System.out.println("callData : " + key);
		HashMap<String, Object> paramMap = HttpUtil.getParams(req);
		HashMap<String,Object> params = new HashMap<String,Object>();
		if((boolean) paramMap.get("status")) {
			String flag ="";
			flag=req.getParameter("switchFlag");
			if("select".equals(key)) {
				System.out.println("select");
				
			}else if("add".equals(key) && "on".equals(flag)) {
				long result =checkIdDuplicate(req,paramMap,params);
				if(result > 0) {
					System.out.println("로그인 성공");
					params.put("status", "loginSuccess");
				}else {
					System.out.println("아이디 , 비밀번호 오류 또는 존재하지 않는 아이디입니다.");
					params.put("status", "loginFail");
				}
				
			}else if("add".equals(key) && flag == null){
				if(checkId(req,paramMap,params)>0) {
					params.put("status", "err");
					System.out.println("이미 존재하는 아이디");
				}else {
					params =insertId(req,paramMap,params);
				}
			}
			resultList.add(params);
		}else {
			System.out.println("status False");
		}
		return resultList;
	}
	
	public long checkIdDuplicate(HttpServletRequest req,HashMap<String, Object> paramMap,HashMap<String,Object> params) {
		String id =req.getParameter("id");
		String pw =req.getParameter("pw");
		paramMap.put("queryType", "selectOne");
		paramMap.put("queryTarget", "login.selectOne_user");
		params.put("id",id);
		params.put("pw", pw);
		paramMap.put("params", params);
		HashMap<String,Object> resultMap =(HashMap<String,Object>)cdi.commonDB(paramMap).get("result");
		return (long)resultMap.get("count");
	}
	
	public int checkId(HttpServletRequest req,HashMap<String, Object> paramMap,HashMap<String,Object> params) {
		
		String id =req.getParameter("id");
		String pw =req.getParameter("pw");
		paramMap.put("queryType", "selectList");
		paramMap.put("queryTarget", "login.select_user");
		params.put("id",id);
		params.put("pw", pw);
		paramMap.put("params", params);
		List<HashMap> resultList =(List<HashMap>)cdi.commonDB(paramMap).get("result");
		params.put("pw", null);
		return resultList.size();
	}
	
	public HashMap<String,Object> insertId(HttpServletRequest req,HashMap<String, Object> paramMap,HashMap<String,Object> params) {
		HashMap<String,Object> insertParams = new HashMap<String,Object>();
		
		System.out.println("check");
		String id =req.getParameter("id");
		String pw =req.getParameter("pw");
		System.out.println("id : "+id+", pw : "+pw);
		
		paramMap.put("queryType", "insert");
		paramMap.put("queryTarget", "login.insert_user");
		params.put("id",id);
		params.put("pw", pw);
		paramMap.put("params", params);
		int result =(int)cdi.commonDB(paramMap).get("result");
		System.out.println("result ="+result);
		insertParams.put("status", "addSuccess");
		insertParams.put("id", id);
		return insertParams;
	}

}
