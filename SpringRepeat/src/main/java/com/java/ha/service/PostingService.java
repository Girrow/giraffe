package com.java.ha.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.ha.dao.PostingDAOI;
import com.java.ha.vo.PostingVO;

@Service
public class PostingService implements PostingServiceI {

	@Autowired
	PostingDAOI postingDAO;
	
	
	@Override
	public void insertPost(PostingVO post) {
		postingDAO.insertData(post);
	}

	@Override
	public void deletePost(String index) {
		HashMap<String,String> resultMap = new HashMap<String,String>();
		resultMap.put("index",index);
		postingDAO.delData(resultMap);
	}

	@Override
	public void updatePost(PostingVO post, String index) {
		HashMap<String,String> resultMap = new HashMap<String,String>();
		resultMap.put("content", post.getContent());
		resultMap.put("index",index);
		System.out.println("updatePost="+resultMap);
		postingDAO.updateData(resultMap);
	}

	@Override
	public List<Map<String,String>> selectAllOfPost() {
		System.out.println("한번 찍어보기");
		System.out.println(postingDAO.getData());
		return postingDAO.getData();
	}

	@Override
	public boolean checkWriter(PostingVO post, String name) {
		// TODO Auto-generated method stub
		return false;
	}

}
