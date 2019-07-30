package com.java;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Test {
	public static void main(String[] args) {
		System.out.println("FTP > Test.main()");
		String strCmd = "sh /root/get.sh";
		String[] callCmd = {"/bin/bash","-c",strCmd};
		Map<Integer,String> map =exec(callCmd);
		System.out.println(map);
		
	}
	
	
	public static Map<Integer, String> exec(String[] cmd){
		Map<Integer,String> map = new HashMap<Integer,String>();
		ProcessBuilder pb = new ProcessBuilder(cmd);
		pb.redirectErrorStream(true);
		Process proc=null;
		try {
			proc = pb.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		
		
		BufferedReader br = null;
		try {
			if(proc != null){
				br=new BufferedReader(new InputStreamReader(proc.getInputStream()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		String line;
		StringBuilder sb = new StringBuilder();
		try {
			if(br !=null) {
				while((line = br.readLine()) !=null) {	//한줄씩 내용 읽어오기
					sb.append(line).append("\n");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		try {
			if(proc !=null) {
				proc.waitFor();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		if(proc != null) {
			map.put(0,String.valueOf(proc.exitValue()));
		}
		
		
		try {
			map.put(1, sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
		
	}
}
