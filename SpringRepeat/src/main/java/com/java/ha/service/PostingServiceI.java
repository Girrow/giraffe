package com.java.ha.service;

import java.util.List;
import java.util.Map;

import com.java.ha.vo.PostingVO;

public interface PostingServiceI {
	public abstract void insertPost(PostingVO post);
	public abstract void deletePost(String index);
	public abstract void updatePost(PostingVO post, String index);
	public abstract List<Map<String,String>> selectAllOfPost();
	public abstract boolean checkWriter(PostingVO post , String name);
}
