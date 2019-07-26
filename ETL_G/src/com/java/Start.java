package com.java;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class Start {
	
	public void main(String[] args) {
		/***********************************************
		 * ETL 프로그램 개발    (원본)  ----->  (대상)
		 * 1) 가져올 데이터베이스 연결 (원본) : hole
		 * 2) 입력할 데이터베이스 연결 (대상) : 201907
		 * 3) 대상 테이블 필요 (c_operation_detailes) --> (m)
		 ***********************************************/
		Info 디비정보 = new Info();
		DBConnection 원본 = new DBConnection(디비정보.step1());
		DBConnection 대상 = new DBConnection(디비정보.step2());
		
		long startTime = 0, endTime = 0;
		
		if(원본.conn != null) {
			System.out.println("SQL 대기중....");
			String sql = "select no_id, operation_id, attended_flag, date_format(TIME, '%Y-%m-%d') AS time from c_operation_detailes";
			String sql2 = "insert into m0 values (?, ?, ?, ?)";
			ResultSet rs = 원본.getData(sql);
			int resultCnt = 0;
			try {
				ResultSetMetaData rsmd = rs.getMetaData();
				int col_cnt = rsmd.getColumnCount();
				List<Object> params = null;
				startTime = System.currentTimeMillis();
				while(rs.next()) {
					params = new ArrayList<Object>();
					for(int i = 1; i <= col_cnt; i++) {
						String col_nm = rsmd.getColumnName(i);
						Object value = rs.getObject(col_nm);
						params.add(value);
					}
					if(대상.conn != null) {
						resultCnt += 대상.setData(sql2, params);
					} else {
						break;
					}
				}
				endTime = System.currentTimeMillis();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				long totTime = ((endTime - startTime) / 1000);
				System.out.println("SQL 종료 : " + resultCnt + ", 경과시간(초) : " + totTime);
				원본.close();
				대상.close();
			}
		} else {
			System.out.println("데이터베이스 접속 중 오류 발생");
		}		
		
	}

}
