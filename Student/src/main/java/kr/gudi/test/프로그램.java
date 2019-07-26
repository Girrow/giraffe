package kr.gudi.test;

import kr.gudi.test.biz.I성적관리;
import kr.gudi.test.biz.성적관리;
import kr.gudi.test.util.학생정보가져오기;

public class 프로그램 
{
	// 학생자료 URL 주소
	protected static String url = "http://gudi.kr/gdj16/web/data.json";
    public static void main( String[] args ) {
        System.out.println("Java Programming Start!!");
        if(학생정보가져오기.Run(url)) {
        	I성적관리 문제 = new 성적관리();
        	System.out.println(">> 1번 문제 : 전체 학생 대상으로 평균 출력하기");
        	문제.평균();
        	
        	System.out.println(">> 2번 문제 : 전체 학생 대상으로 성적순 출력하기");
        	문제.성적순();
        	
        	System.out.println(">> 3번 문제 : 전체 학생 대상으로 우수 학생 출력하기");
        	문제.우수학생();
        }
        System.out.println("Java Programming End!!");
    }
}
