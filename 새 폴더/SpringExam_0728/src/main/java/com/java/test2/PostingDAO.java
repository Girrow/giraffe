package com.java.test2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class PostingDAO {
	private List<Map> list = new ArrayList<Map>();
	public void insert(Posting posting) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("number",posting.getTableNo());
		map.put("content",posting.getContent());
		list.add(map);
	}
	
	public void update(Posting posting,int index) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("number",posting.getTableNo());
		map.put("content",posting.getContent());
		map.put(posting.getTableNo(), posting.getContent());
		list.set(index,map);
	}
	
	public void delete(Posting posting) {
		
		list.remove((Integer.parseInt(posting.getTableNo())-1));
	}
	
	public List<Map> selectAll() {
		return this.list;
	}
}
