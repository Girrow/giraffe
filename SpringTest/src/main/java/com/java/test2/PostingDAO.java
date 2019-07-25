package com.java.test2;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
@Service
public class PostingDAO {
	public Map map = new HashMap();
//	private Map map = new HashMap();
	public void insert(Posting posting) {
		map.put(posting.getTableNo(), posting.getContent());
	}
	
	public void update(Posting posting) {
		map.put(posting.getTableNo(), posting.getContent());
	}
	
	public void delete(Posting posting) {
		map.remove(posting.getTableNo());
	}
	
}
