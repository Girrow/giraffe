package kr.gudi.web.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.gudi.web.service.CrawlingServiceInterface;
import net.sf.json.JSONObject;

@Controller
public class CrawlingController {

	@Autowired
	CrawlingServiceInterface csi;
	
	@RequestMapping("/getHtmlData")
	public void getHtmlData(HttpServletRequest req, HttpServletResponse response) {
		HashMap<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("page", req.getParameter("page"));
		try {
			JSONObject jsonObject = JSONObject.fromObject(csi.getHtmlData(paramsMap));
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().write(jsonObject.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/")
	public String firstPage(Model model) {
		return "20190729";
	}
	
}
