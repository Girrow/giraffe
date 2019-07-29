package com.java.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.java.Bean.UserBean;

@Repository
public class UserDAOI implements UserDAO {
	List<Map> userList = new ArrayList<Map>();
	
	@Override
	public void setUser(UserBean user) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("username",user.getUsername());
		map.put("password",user.getPassword());
		userList.add(map);
	}

	@Override
	public List getUserList(){
		return this.userList;
	}

}
