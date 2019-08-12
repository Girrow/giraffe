package com.java.ha.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginServiceI {
	public List<HashMap<String,Object>> callData(String key, HttpServletRequest req, HttpServletResponse res);
}
