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

import kr.gudi.web.bean.CrawlingBean;
import kr.gudi.web.dao.CommonDaoInterface;

@Service
public class CrawlingService implements CrawlingServiceInterface {

	@Autowired
	CommonDaoInterface cdi;

	@Override
	public HashMap<String, Object> getHtmlData(HashMap<String, Object> paramsMap) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		List<HashMap<String, CrawlingBean>> crawlingList = getList(paramsMap);
		List<HashMap<String, Object>> paramList = null;
		for (int r = 0; r < crawlingList.size(); r++) {
			HashMap<String, CrawlingBean> map = crawlingList.get(r);
			HashMap<String, Object> stateMap = new HashMap<String, Object>();
			// 화면 데이터를 기준으로 테이블에 삽입 처리
			paramList = getHTMLParser(map.get("step2"));
			System.out.println("세번째까지 값 받음");
			int tot = 0;
			int del = 0;
			HashMap<String, Object> paramMap1 = null;
			HashMap<String, Object> paramMap2 = null;
			HashMap<String,Object> paramMap3 = null;
			for (int i = 0; i < paramList.size(); i++) {
				paramMap1 = new HashMap<String, Object>();
				paramMap2 = new HashMap<String, Object>();
				paramMap3 = new HashMap<String,Object>();

				paramMap1.put("queryType", map.get("step1").getQueryType());
				paramMap1.put("queryTarget", map.get("step1").getQueryTarget());
				paramMap1.put("params", paramList.get(i));

				paramMap2.put("queryType", map.get("step2").getQueryType());
				paramMap2.put("queryTarget", map.get("step2").getQueryTarget());
				paramMap2.put("params", paramList.get(i));
				
				paramMap3.put("queryType", map.get("step3").getQueryType());
				paramMap3.put("queryTarget", map.get("step3").getQueryTarget());
				paramMap3.put("params",null);
				
				
				del += (int) cdi.commonDB(paramMap1).get("result");
				tot += (int) cdi.commonDB(paramMap2).get("result");
				if(r==0) {
					resultMap.put("firstResult", cdi.commonDB(paramMap3).get("result"));
				}else {
					resultMap.put("secondResult", cdi.commonDB(paramMap3).get("result"));
				}
			}
//			stateMap.put("TotalCount", paramList.size());
//			stateMap.put("InsertCount", tot);
//			stateMap.put("DeleteCount", del);
//			resultMap.put("crawling" + (r + 1),stateMap);
		}

		System.out.println(resultMap.toString());
		return resultMap;
	}

	public List<HashMap<String, Object>> getHTMLParser(CrawlingBean cb) {
		List<HashMap<String, Object>> paramList = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> row;
		try {
			Document doc = Jsoup.connect(cb.getUrl()).get();
			Elements newsHeadlines = doc.select(cb.getTarget());
			for (Element headline : newsHeadlines) {
				row = new HashMap<String, Object>();

				for (String key : cb.getColumns().keySet()) {
					String tit = headline.select(cb.getColumns().get(key).toString()).text();
					row.put(key, tit);
				}

				paramList.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paramList;
	}

	public List<HashMap<String, CrawlingBean>> getList(HashMap<String, Object> paramsMap) {
		List<HashMap<String, CrawlingBean>> crawlingList = new ArrayList<HashMap<String, CrawlingBean>>();
		HashMap<String, CrawlingBean> crawlingMap = null;
		HashMap<String, Object> columns = null;
		HashMap<String, CrawlingBean> crawlingMap2 = null;
		HashMap<String, Object> columns2 = null;
		/*************************************************************************
		 * queryTarget : "namespace.id" < 1 url :
		 * "http://gdu.co.kr/process/process_010100.html" < 1 target : ".process-list
		 * .first_li" < 1 columns : ".tit" ".date" ".clearfix" < N
		 *************************************************************************/
		// 처리 대상 내용 정의
//		if("1".equals(paramsMap.get("page"))) {
		crawlingMap = new HashMap<String, CrawlingBean>();
		columns = new HashMap<String, Object>();
		columns.put("tit", ".tit");
		columns.put("date", ".date");
		columns.put("clearfix", ".clearfix");
		crawlingMap.put("step1", new CrawlingBean("delete", "crawling.first_delete", "", "", columns));
		crawlingMap.put("step2", new CrawlingBean("insert", "crawling.first_insert",
				"http://gdu.co.kr/process/process_010100.html?page=1&plist=&find_field=&find_word=&find_state=&find_ordby=&conf=&mode=",
				".process-list .first_li", columns));
		crawlingMap.put("step3", new CrawlingBean("selectList", "crawling.first_select", "", "", columns));
		crawlingList.add(crawlingMap);

		crawlingMap2 = new HashMap<String, CrawlingBean>();
		columns2 = new HashMap<String, Object>();
		columns2.put("tit", ".tit");
		columns2.put("date", ".date");
		columns2.put("clearfix", ".clearfix");
		crawlingMap2.put("step1", new CrawlingBean("delete", "crawling2.second_delete", "", "", columns2));
		crawlingMap2.put("step2", new CrawlingBean("insert", "crawling2.second_insert",
				"http://gdu.co.kr/process/process_010100.html?page=2&plist=&find_field=&find_word=&find_state=&find_ordby=&conf=&mode=",
				".process-list .first_li", columns2));
		crawlingMap2.put("step3", new CrawlingBean("selectList", "crawling2.second_select", "", "", columns));
		crawlingList.add(crawlingMap2);
		System.out.println("두번째까지 값 받음");
//
//		} else if("2".equals(paramsMap.get("page"))) {
//			crawlingMap = new HashMap<String, CrawlingBean>();
//			columns = new HashMap<String, Object>();
//			columns.put("tit", ".tit");
//			columns.put("date", ".date");
//			columns.put("clearfix", ".clearfix");
//			crawlingMap.put("step1", new CrawlingBean("delete", "crawling.first_delete", "", "", columns));
//			crawlingMap.put("step2", new CrawlingBean("insert", "crawling.first_insert", "http://gdu.co.kr/process/process_010100.html?page=2&plist=&find_field=&find_word=&find_state=&find_ordby=&conf=&mode=", ".process-list .first_li", columns));
//			crawlingList.add(crawlingMap);
//		}
		System.out.println("크롤링 리스트 : " + crawlingList.size());
		return crawlingList;
	}

}
