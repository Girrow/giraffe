package kr.gudi.test.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import kr.gudi.test.bean.성적표;
import kr.gudi.test.bean.학생;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class 학생정보가져오기 {
	
	// 학생 정보 정적 변수 생성
	public static List<학생> 학생정보목록 = new ArrayList<학생>();
	
	public static boolean Run(String addr) {
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
		        
		        // 문자열로 받은 값을 JSON 데이터형으로 변환하기
		        JSONObject jo = JSONObject.fromObject(response.toString());
		        // JSON 데이터 중 키값이 'students'의 값을 배열로 담기
		        JSONArray students = jo.getJSONArray("students");
		        // 배열에 담겨 있는 학생의 정보 가져오기 (반복문)
		        for(int i = 0; i < students.size(); i++) {
		        	// 각 행 학생 정보 객체로 담기
		        	JSONObject row = students.getJSONObject(i);
		        	// 학생 정보 중 성적표 객체로 담기
		        	JSONObject point = row.getJSONObject("point");		        	
		        	// 학생 Bean 객체 생성
		        	학생 학생정보 = new 학생();
		        	// 성적표 Bean 객체 생성
		        	성적표 성적 = new 성적표();
		        	// 학생 Bean 객체에 국어 점수 담기
		        	성적.set국어(point.getInt("Korean"));
		        	// 학생 Bean 객체에 영어 점수 담기
		        	성적.set영어(point.getInt("English"));
		        	// 학생 Bean 객체에 수학 점수 담기
		        	성적.set수학(point.getInt("Math"));
		        	// 학생 Bean 객체에 과학 점수 담기
		        	성적.set과학(point.getInt("Science"));
		        	
		        	// 학생정보 Bean 객체에 학생 번호 담기
		        	학생정보.set번호(row.getInt("no"));
		        	// 학생정보 Bean 객체에 학생 이름 담기
		        	학생정보.set이름(row.getString("nm"));
		        	// 학생정보 Bean 객체에 학생 성적 객체 담기
		        	학생정보.set성적(성적);
		        	
		        	System.out.println(학생정보);
		        	// 정적 객체 변수에 각 행 학생정보 추가하기
		        	학생정보가져오기.학생정보목록.add(학생정보);
		        }
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
