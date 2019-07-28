package com.java.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

public class mainP {
	public static void main(String[] args) {
		List<Map> list=new ArrayList<Map>();
		JSONObject a = new JSONObject();
//		a.put("a", "1234");
		System.out.println("a="+a);
		Map<String,String> map = new HashMap<String,String>();
		map.put("a", "1111");
		map.put("b", "2222");
		list.add(map);
		Map<String,List> listMap = new HashMap<String,List>();
		listMap.put("result", list);
		System.out.println();
		a= JSONObject.fromObject(listMap);
//		System.out.println();
	}
}
