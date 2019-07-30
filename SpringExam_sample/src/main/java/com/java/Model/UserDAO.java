package com.java.Model;

import java.util.List;

import com.java.Bean.UserBean;

public interface UserDAO {
	public abstract void setUser(UserBean user);
	public abstract List getUserList();
}
