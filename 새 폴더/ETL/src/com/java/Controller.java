package com.java;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class Controller {
	Info dbInfo = new Info();
	DBConnection 원본 = new DBConnection(dbInfo.step1());
	DBConnection 수정본 = new DBConnection(dbInfo.step2());
	/*
	public Map<String, String> getSql(int index){
		return sqlList.get(index);
	}
	*/
	public void run() {
		for(int i=0;i<dbInfo.sqlList.size();i++) {
			String sql=dbInfo.gelSql(i).get("원본");
			String sql2=dbInfo.gelSql(i).get("대상");
			//현재 시간값 받는데 1000빵 해야됨
			long startTime = System.currentTimeMillis();
			
			//1)원본 가져오기
			List<List> resultList=step1(sql);
			System.out.println("원본 데이터 건수 : " + resultList.size());
			
			//2)대상 테이블에 넣기
			int resultCnt = step2(resultList, sql2);
			System.out.println("대상 데이터 건수 : " + resultCnt);
			long endTime = System.currentTimeMillis();
			
			//3) 작업 결과 출력
			
			System.out.println("ETL 종료 : " + ((endTime - startTime) / 1000) + "(초)");
		}
	}
	
	
	public List<List> step1(String sql) {
		List<List> resultList = new ArrayList();
		try {
			ResultSet rs=원본.getData(sql);
			//메타 데이터를 받는 변수
			ResultSetMetaData rsmd = rs.getMetaData();
			int col_cnt=rsmd.getColumnCount();
			List params =null;
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
		}finally {
			원본.close();
		}
		return resultList;
	}
	
	public int step2(List<List> resultList,String sql){
		int resultCnt = 0;
		try {
			for(int i = 0; i < resultList.size(); i++) {
				if(수정본.conn != null) {
					resultCnt += 수정본.setData(sql, resultList.get(i));
				} else {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			수정본.close();
		}
		
		return resultCnt;
		
	}
}
