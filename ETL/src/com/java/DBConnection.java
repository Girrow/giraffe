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

	public Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = ""; // 사용하지는 않을 듯
	List<Map<String, Object>> resultList = null;
	
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

//	sqlCheck 만들필요가 있나?

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
			pstmt=conn.prepareStatement(sql);
			
			for(int i=0;i<params.size();i++) {
				pstmt.setObject((i+1), params.get(i));
				//임의적으로 추가해봄
				System.out.println("##\n"+params.get(i)+"\n##");
			}
			return pstmt.executeUpdate();
			/*
			 * 이해가 잘 안가는 부분
			 * params.get(i)가 의미하는 것을 보고싶음
			 * 잘 끝나면 1을 보내서//
			 * */
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public boolean close() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
