package com.java.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Info {
	
	public Info() {
		setSql();
	}
	
	// 데이터 베이스 접속 정보 4가지
	String driver = "org.mariadb.jdbc.Driver";
	
	List<Map<String, String>> sqlList = new ArrayList<Map<String, String>>();
	
	public InfoBean step1() {
		String url = "jdbc:mysql://:53306/hole";
		String user = "m0";
		String password = "m0";
		return new InfoBean(driver, url, user, password);
	}
	
	public InfoBean step2() {
		String url = "jdbc:mysql://gdj16.gudi.kr:53306/201907";
		String user = "m15";
		String password = "m15";
		return new InfoBean(driver, url, user, password);
	}
	
	public boolean setSql() {
		Map<String, String> sqlMap = new HashMap<String, String>();
		sqlMap.put("원본", "select no_id, operation_id, attended_flag, date_format(TIME, '%Y-%m-%d') AS time from c_operation_detailes");
		sqlMap.put("대상", "insert into m0 values (?, ?, ?, ?)");
		sqlList.add(sqlMap);
		return true;
	}

	public Map<String, String> getSql(int index){
		return sqlList.get(index);
	}
}
