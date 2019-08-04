package com.java.ha.service;

import java.util.HashMap;

import com.java.ha.vo.UserVO;

public interface UserServiceI {
	public abstract HashMap<String,Object> insertUser(UserVO user);
	
	public abstract HashMap<String,Object> selectUser(UserVO user);
}
