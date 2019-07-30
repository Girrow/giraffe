package com.java.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TestDao implements TestDaoI{

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public HashMap<String, Object> getData() {
		
		return sqlSession.selectOne("sql.getData");
	}
	
	@Override
	public int setData( HashMap<String, Object> param) {
		
		return sqlSession.insert("sql.insert",param);
	}

	@Override
	public void clear() {
		sqlSession.delete("sql.truncate");
		
	}

	@Override
	public int delData(HashMap<String, Object> param) {
		
		return sqlSession.delete("sql.delete",param);
	}

}
