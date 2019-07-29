package com.java.Model;

import java.util.List;
import java.util.Map;

import com.java.Bean.PostingBean;

public interface PostingDAO {
	public abstract void insertPost(PostingBean post);
	public abstract void deletePost(int index);
	public abstract void updatePost(PostingBean post,int index);
	public abstract List<Map> selectAllOfPost();
	public abstract boolean checkWriter(PostingBean post , String name);
}
