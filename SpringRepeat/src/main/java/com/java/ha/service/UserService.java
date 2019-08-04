package com.java.ha.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.ha.dao.CommonDaoInterface;
import com.java.ha.vo.UserVO;

@Service
public class UserService implements UserServiceI {
	@Autowired
	CommonDaoInterface cdi;
	@Override
	public HashMap<String, Object> insertUser(UserVO user) {
		HashMap<String,Object> paramMap = new HashMap<String,Object>();
		HashMap<String,Object> params = new HashMap<String,Object>();
		paramMap.put("queryType","insert");
		paramMap.put("queryTarget","useUser.m15_insert");
		params.put("username", user.getUsername());
		params.put("password",user.getPassword());
		paramMap.put("params",params);
		return cdi.commonDB(paramMap);
		
	}
	@Override
	public HashMap<String, Object> selectUser(UserVO user) {
		HashMap<String,Object> paramMap = new HashMap<String,Object>();
		HashMap<String,Object> params = new HashMap<String,Object>();
		paramMap.put("queryType","selectOne");
		paramMap.put("queryTarget","useUser.m15_idSelect");
		params.put("username", user.getUsername());
		params.put("password",user.getPassword());
		paramMap.put("params",params);
		return cdi.commonDB(paramMap);
	}

}
