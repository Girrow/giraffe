package kr.gudi.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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

	@RequestMapping("/print")
	public void print(HttpServletResponse response) {
		try {
			HashMap<String, Object> resultMap = new HashMap<String, Object>();
			String addr = "https://www.kbl.or.kr/stats/team_rank.asp";
			List<HashMap<String, Object>> dataList = new ArrayList<HashMap<String, Object>>();
			HashMap<String, Object> rowMap = null;
			
			Document doc = Jsoup.connect(addr).get();
			Elements trs = doc.select(".tbltype_record tbody tr");
			for (Element trline : trs) {
				rowMap = new HashMap<String, Object>();
				Elements tds = trline.select("td");
				int i = 1;
				for(Element tdline : tds) {
//					System.out.println(tdline.text());
					rowMap.put("col" + i, tdline.text());
					i++;
				}
				dataList.add(rowMap);
			}
			resultMap.put("targetSize", trs.size());
			resultMap.put("resultSize", dataList.size());
			resultMap.put("rows", dataList);
			
			JSONObject jsonObject = JSONObject.fromObject(resultMap);
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().write(jsonObject.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
