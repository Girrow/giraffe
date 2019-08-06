package kr.gudi.web.util;

import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class HttpUtil {
	
	public static HashMap<String, Object> getPatams(HttpServletRequest req){
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		
		Enumeration<String> paramNames = req.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String name = (String) paramNames.nextElement();
			String value = req.getParameter(name);
			System.out.println(name + " : " + value);
			paramMap.put(name, value);
		}
		
		return paramMap;
	}
	
	public static void makeJsonView(HttpServletResponse res, HashMap<String, Object> resultMap) {
		try {
			res.setCharacterEncoding("UTF-8");
			res.setContentType("application/json; charset=UTF-8");
			JSONObject jObject = JSONObject.fromObject(resultMap);
			res.getWriter().write(jObject.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
