package com.java.ha.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.java.ha.vo.PostingVO;

public interface PostingDAOI {
	public List<Map<String,String>> getData();
	public void delData(HashMap<String,String> post);
	public int insertData(PostingVO post);	
	public void updateData(HashMap<String,String> post);
	public void clear();
}
