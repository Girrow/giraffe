package com.java.ha.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.java.ha.vo.PostingVO;

@Repository
public class PostingDAO implements PostingDAOI {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<Map<String,String>>  getData() {
		return sqlSession.selectList("sql.getData");
	}

	@Override
	public void delData(HashMap<String,String> post) {
		sqlSession.delete("sql.delete",post);
	}

	@Override
	public int insertData(PostingVO post) {
		sqlSession.insert("sql.insert",post);
		return 0;
	}

	@Override
	public void clear() {
		sqlSession.update("sql.truncate");
	}

	@Override
	public void updateData(HashMap<String,String> post) {
		sqlSession.update("sql.update",post);
	}

}
