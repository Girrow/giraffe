package com.java.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class 학생정보가져오기 {
	
	// 학생 정보 정적 변수 생성
//	public static List<학생> 학생정보목록 = new ArrayList<학생>();
	
	public boolean Run(String addr) {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 학생정보 받아오기 시작.");
		try {
			// URL 정보 만들기
			URL url = new URL(addr);
			// URL 정보를 이용하여 접속하기
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			// 접속 시 요청 정보 정의하기 : GET 방식
			http.setRequestMethod("GET");
			// 응답 코드가 정상일때 제어문 실행 : 200(정상)
			if(http.getResponseCode() == 200) {
				// 응답 받은 결과값 입력 스트림 객체에 담기
				InputStream is = http.getInputStream();
				// 입력 스트림 객체의 값을 임시(버퍼 공간)저장 하기 
		        BufferedReader br = new BufferedReader(new InputStreamReader(is));
		        // 문자열 임시(버퍼 공간) 변수 생성
		        StringBuffer response = new StringBuffer();
		        // 입력 스트림에서 받아온 내용을 한줄씩 담기 위하여 사용할 문자열 변수 생성
		        String line = "";
		        // 각 행별로 문자열 추출하기 (반복문)
		        while ((line = br.readLine()) != null) {
		        	// 각 행의 문자열 값을 문자열 임시(버퍼 공간) 변수에 추가하기
		            response.append(line);
		            // 다음 행 처리를 하기 위하여 문자열 임시(버퍼 공간) 변수에 추가하기
		            response.append('\r');
		        }
		        // 입력 스트림 객체 종료하기
		        br.close();
		        System.out.println(response.toString());
		        /*
		         *JSON 형태를 받을 수 있는 라이브러리를 이용하기 
		         * */
		        String strJson = response.toString();
		        JSONObject jObj = JSONObject.fromObject(strJson);
		        JSONArray students=jObj.getJSONArray("students");
		        System.out.println("행의 수 : " + students.size());
		        
		        Map<String,Object> cols = null;
		        Map<String,Object> cols2 = null;
		        List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		        for(int i=0;i<students.size();i++) {
		        	JSONObject row = students.getJSONObject(i);
		        	cols = new HashMap<String,Object>();
		        	cols.put("no", row.getString("no"));
		        	cols.put("nm", row.getString("nm"));
		        	/**/
		        	//cols.put("point", row.getJSONObject("point"));
		        	JSONObject rows = row.getJSONObject("point");
		        	cols2 = new HashMap<String,Object>();
		        	cols2.put("Korean",rows.getString("Korean"));
		        	cols2.put("Math",rows.getString("Math"));
		        	cols2.put("English",rows.getString("English"));
		        	cols2.put("Science",rows.getString("Science"));
		        	/*
		        	 * cols2 = new HashMap
		        	 * cols2.put("Korean",row.getJSONObject("point").getString("Korean"))
		        	 * */
		        	cols.put("point",cols2);
		        	resultList.add(cols);
		        }
		        
		        /*
		        System.out.println("드라이버 :"+Class.forName("org.mariadb.jdbc.Driver"));
		        String urlTo="jdbc:mysql://gdj16.gudi.kr:53306/201907";
		        String user="m15";
		        String password="m15";
		        
		        String sql="insert into m15(no,nm,english,science,korean,math) values(?,?,?,?,?,?)";
		        
		        Connection conn = DriverManager.getConnection(urlTo,user,password);
		        PreparedStatement pstmt=conn.prepareStatement(sql);
		        for(int i=0;i<6;i++) {
		        	pstmt.setObject((i+1), 5);
		        };
		        pstmt.executeUpdate();
		        */
		        
		        
		        /*
		        Controller ctl = new Controller();
				ctl.run();
				*/
				
//		        System.out.println(resultList.get(0).get("no"));
		        
		        
		        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 학생정보 받아오기 종료.");
			} else {
				System.out.println("학생 정보를 받아 오지 못하였습니다.");
				return false;
			}
			// URL 접속 종료
			http.disconnect();
			
		} catch (Exception e) {
			// Try 내용 실행 중 오류 발생 시 오류 내용 Console 창에 출력하기
			e.printStackTrace();
			System.out.println("학생 정보를 받아 오지 못하였습니다.");
			return false;
		}
		
		return true;
	}

}

