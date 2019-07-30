package com.java;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class Controller {
	
	Info 디비정보 = new Info();
	DBConnection 원본 = new DBConnection(디비정보.step1());
	DBConnection 대상 = new DBConnection(디비정보.step2());
	
	public void run() {
		for(int i = 0; i < 디비정보.sqlList.size(); i++) {
			String sql = 디비정보.getSql(i).get("원본");
			String sql2 = 디비정보.getSql(i).get("대상");
			
			long startTime = System.currentTimeMillis();
			// 1) 원본 가져오기
			List<List<Object>> resultList = step1(sql);
			System.out.println("원본 데이터 건수 : " + resultList.size());
			
			// 2) 대상 테이블에 데이터 넣기
			int resultCnt = step2(resultList, sql2);
			System.out.println("대상 데이터 건수 : " + resultCnt);
			long endTime = System.currentTimeMillis();
			
			// 3) 작업 결과 출력
			System.out.println("ETL 종료 : " + ((endTime - startTime) / 1000) + "(초)");
		}
	}
	
	public List<List<Object>> step1(String sql) {
		List<List<Object>> resultList = new ArrayList();
		try {
			ResultSet rs = 원본.getData(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int col_cnt = rsmd.getColumnCount();
			List<Object> params = null;
			while(rs.next()) {
				params = new ArrayList<Object>();
				for(int i = 1; i <= col_cnt; i++) {
					String col_nm = rsmd.getColumnName(i);
					Object value = rs.getObject(col_nm);
					params.add(value);
				}
				resultList.add(params);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			원본.close();
		}		
		return resultList;
	}
	
	public int step2(List<List<Object>> resultList, String sql) {
		int resultCnt = 0;
		try {
			for(int i = 0; i < resultList.size(); i++) {
				if(대상.conn != null) {
					resultCnt += 대상.setData(sql, resultList.get(i));
				} else {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			대상.close();
		}
		
		return resultCnt;
	}
	
}
