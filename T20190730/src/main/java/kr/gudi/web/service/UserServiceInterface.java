package kr.gudi.web.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserServiceInterface {

	public void callData(String key, HttpServletRequest req, HttpServletResponse res);
}
