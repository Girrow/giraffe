package com.java;

public class InfoBean {
	
	String driver;
	String url;
	String user;
	String password;
	
	public InfoBean(String driver, String url, String user, String password) {
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.password = password;
	}
	
	public String getDriver() {
		return driver;
	}
	public String getUrl() {
		return url;
	}
	public String getUser() {
		return user;
	}
	public String getPassword() {
		return password;
	}
	@Override
	public String toString() {
		return "InfoBean [driver=" + driver + ", url=" + url + ", user=" + user + ", password=" + password + "]";
	}
}
