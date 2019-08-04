package kr.gudi.web.dao;

import java.util.HashMap;

public interface TestDaoInterface {

	public HashMap<String, Object> getData();
	public int delData(HashMap<String, Object> param);
	public int setData(HashMap<String, Object> param);	
	public void clear();
}
