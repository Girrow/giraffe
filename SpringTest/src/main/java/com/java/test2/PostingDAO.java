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
		Map map = new HashMap();
		map.put("number",posting.getTableNo());
		map.put("content",posting.getContent());
		list.add(map);
	}
	
	public void update(Posting posting) {
		Map map = new HashMap();
		map.put("number",posting.getTableNo());
		map.put("content",posting.getContent());
		map.put(posting.getTableNo(), posting.getContent());
		list.set(posting.getTableNo(),map);
	}
	
	public void delete(Posting posting) {
		
		list.remove((posting.getTableNo()-1));
	}
	
	public List<Map> selectAll() {
		return this.list;
	}
}
