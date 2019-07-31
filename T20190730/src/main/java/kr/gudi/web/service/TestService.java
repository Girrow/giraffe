package kr.gudi.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.gudi.web.dao.CommonDaoInterface;
import kr.gudi.web.dao.TestDaoInterface;

@Service
public class TestService implements TestServiceInterface {

	@Autowired
	TestDaoInterface tdi;
	
	final String ADDR = "http://gdu.co.kr/process/process_010100.html";
	
	@Autowired
	CommonDaoInterface cdi;
	
	@Override
	public HashMap<String, Object> getData() {
//		return tdi.getData();
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("queryType", "selectOne");
		paramMap.put("queryTarget", "sql.getData");
		paramMap.put("params", null);
		return cdi.commonDB(paramMap);
	}
	
	@Override
	public HashMap<String, Object> setData() {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		List<HashMap<String, Object>> paramList = getHTMLParser();
		int tot = 0;
		int del = 0;
		//tdi.clear();
		for(int i = 0; i < paramList.size(); i++) {
			del += tdi.delData(paramList.get(i));
			tot += tdi.setData(paramList.get(i));
		}
		resultMap.put("TotalCount", paramList.size());
		resultMap.put("InsertCount", tot);
		resultMap.put("DeleteCount", del);
		System.out.println(resultMap.toString());
		return resultMap;
	}
	
	public List<HashMap<String, Object>> getHTMLParser() {
		List<HashMap<String, Object>> paramList = new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> row;
		try {
			Document doc = Jsoup.connect(ADDR).get();
			Elements newsHeadlines = doc.select(".process-list .first_li");
			for (Element headline : newsHeadlines) {				
				String tit = headline.select(".tit").text();
				String date = headline.select(".date").text();
				String clearfix = headline.select(".clearfix").text();
				row = new HashMap<String, Object>();
				row.put("tit", tit);
				row.put("date", date);
				row.put("clearfix", clearfix);
				paramList.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paramList;
	}

}
