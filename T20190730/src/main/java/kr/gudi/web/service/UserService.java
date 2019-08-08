package kr.gudi.web.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import kr.gudi.web.util.HttpUtil;

@Service
public class UserService implements UserServiceInterface {
	
	@Override
	public HashMap<String, Object> callData(String key, HttpServletRequest req, HttpServletResponse res) {
		System.out.println("callData : " + key);
		HashMap<String, Object> paramMap = HttpUtil.getPatams(req);
		if((boolean) paramMap.get("status")) {
			// 디비
			System.out.println("우린 죽는 것이다!");
		} 		
		return paramMap;
	}

}
