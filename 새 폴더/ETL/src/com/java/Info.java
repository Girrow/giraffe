package com.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Info {
	public Info() {
		setSql();
	}
	String driver="org.mariadb.jdbc.Driver";
	List<Map<String,String>> sqlList=new ArrayList<Map<String, String>>();
//	꼭 값을 arrayList로 선정을 해주어야 하는가?  new ArrayList<Map<String, String>>();
	
	public InfoBean step1() {
		String url="jdbc:mysql://:53306/hole";
		String user="m15";
		String password="m15";
		return new InfoBean(driver,url,user,password);
	}
	
	public InfoBean step2() {
		String url="jdbc:mysql://:53306/201907";	//보낼 값
		String user="m15";
		String password="m15";
		return new InfoBean(driver,url,user,password);
	}
	
	public void setSql() {
		Map<String, String> sqlMap = new HashMap<String, String>();
		sqlMap.put("원본", "select no_id, operation_id, attended_flag, date_format(TIME, '%Y-%m-%d') AS time from c_operation_detailes");
		sqlMap.put("대상", "insert into m15 values (?, ?, ?, ?)");
		sqlList.add(sqlMap);
	}
	
	public Map<String,String> gelSql(int index){
		return sqlList.get(index);
	}
}
