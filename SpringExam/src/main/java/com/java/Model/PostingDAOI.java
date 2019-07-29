package com.java.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.java.Bean.PostingBean;

@Repository
public class PostingDAOI implements PostingDAO{
	private List<Map> list = new ArrayList<Map>();

	@Override
	public void insertPost(PostingBean post) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("postNo",post.getPostNo());
		map.put("content",post.getContent());
		map.put("writer",post.getWriter());
		list.add(map);
	}

	@Override
	public void deletePost(int index) {
		list.remove(index);
	}
	
	@Override
	public void updatePost(PostingBean post, int index) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("postNo",post.getPostNo());
		map.put("content",post.getContent());
		map.put("writer",post.getWriter());
		//map.put(post.getPostNo(), post.getContent());
		list.set(index,map);
	}

	@Override
	public List<Map> selectAllOfPost() {
		return this.list;
	}

	@Override
	public boolean checkWriter(PostingBean post, String name) {
		if(post.getWriter()==name) {
			return true;
		}else {
			return false;
		}
	}
}
