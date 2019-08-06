package kr.gudi.web.util;

import java.util.ArrayList;
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
		
//		return checkParams(paramMap);
		return paramMap;
	}
	
	public static void makeJsonView(HttpServletResponse res, HashMap<String, Object> resultMap) {
		try {
			res.setCharacterEncoding("UTF-8");
			res.setContentType("application/json; charset=UTF-8");
			
			JSONObject jObject = JSONObject.fromObject(checkParams(resultMap));
			res.getWriter().write(jObject.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static HashMap<String,Object> checkParams(HashMap<String,Object> params){
		int totBlank=0;
		int totNull=0;
		HashMap<String,Object> errMap = new HashMap<String,Object>();
		HashMap<String,Object> resultparams = new HashMap<String,Object>();
		for(String key : params.keySet()) {
			if(params.get(key)=="") {
				resultparams.put(key, "empty");
				System.out.println(key+"값이 비어있습니다.");
				errMap.put(key,"비어있는 값");
				totBlank++;
			}else if(params.get(key)==null) {
				resultparams.put(key, "null");
				System.out.println(key+"값이 Null입니다.");
				errMap.put(key,"null값");
				totNull++;
			}else {
				resultparams.put(key, params.get(key));
			}
		}
		errMap.put("blank 값",totBlank);
		errMap.put("Null 값",totNull);
		resultparams.put("err", errMap);
		return resultparams;
	}

}
