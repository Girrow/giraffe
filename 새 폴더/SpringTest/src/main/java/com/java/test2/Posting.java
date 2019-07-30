package com.java.test2;

import org.springframework.stereotype.Service;

@Service
public class Posting {
	private String content;
	public int tableNo;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getTableNo() {
		return tableNo;
	}
	public void setTableNo(int tableNo) {
		this.tableNo = tableNo;
	}

}
