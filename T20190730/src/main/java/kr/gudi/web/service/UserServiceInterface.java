package kr.gudi.web.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserServiceInterface {

	public HashMap<String, Object> callData(String key, HttpServletRequest req, HttpServletResponse res);
}
