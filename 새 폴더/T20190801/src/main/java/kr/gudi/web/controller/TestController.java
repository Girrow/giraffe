package kr.gudi.web.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.gudi.web.service.TestServiceInterface;
import net.sf.json.JSONObject;

@Controller
public class TestController {
	
	@Autowired
	TestServiceInterface tsi;
	
	@RequestMapping("/getData")
	public void getData(HttpServletResponse response) {
//		tsi.getData();
		try {
			JSONObject jsonObject = JSONObject.fromObject(tsi.getData());
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().write(jsonObject.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/getData2")
	public String getData2(Model model) {
		HashMap<String, Object> resultMap = tsi.getData();
		model.addAttribute("data", resultMap);
		return "json";
	}
	
	@RequestMapping("/setData")
	public String setData(Model model) {
		model.addAttribute("data", tsi.setData());
		return "json";
	}

}
