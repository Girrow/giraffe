package com.java.dao;

import java.util.HashMap;

public interface TestDaoI {
	public abstract HashMap<String,Object> getData();
	public abstract int setData(HashMap<String,Object> param);
	public abstract int delData(HashMap<String,Object> param);
	public abstract void clear();
}
