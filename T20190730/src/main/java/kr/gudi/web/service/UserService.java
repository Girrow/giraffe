package kr.gudi.web.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import kr.gudi.web.util.HttpUtil;

@Service
public class UserService implements UserServiceInterface {

	@Override
	public void callData(String key, HttpServletRequest req, HttpServletResponse res) {
		System.out.println("callData : " + key);
		HashMap<String, Object> paramMap = HttpUtil.getPatams(req);
		HttpUtil.makeJsonView(res, paramMap);
	}

}
