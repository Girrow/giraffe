package com.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class DBConnection {
	
	public DBConnection(InfoBean ib) {
		if(classCheck(ib.getDriver())) {
			if(getConn(ib.getUrl(), ib.getUser(), ib.getPassword())) {
				System.out.println("접속 완료");
			} else {
				System.out.println("접속 오류");
			}
		} else {
			System.out.println("접속 불가");
		}
	}
	
	// 데이터베이스에서 이용할 변수
	public Connection conn = null;
	PreparedStatement pstmt = null;	
	ResultSet rs = null;
	String sql = "select 1";
	List<Map<String, Object>> resultList = null;
	
	// 클래스 유무 확인
	public boolean classCheck(String driver) {
		try {
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}	

	public boolean getConn(String url, String user, String password) {
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean sqlCheck() {
		try {
			pstmt = conn.prepareStatement(sql);
			return pstmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public ResultSet getData(String sql) {
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public int setData(String sql, List<Object> params) {
		try {
			pstmt = conn.prepareStatement(sql);
			
			for(int i = 0; i < params.size(); i++) {
				pstmt.setObject((i + 1), params.get(i));
			}
			
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public boolean close() {
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
