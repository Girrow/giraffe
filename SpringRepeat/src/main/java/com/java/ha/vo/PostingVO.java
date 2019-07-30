package com.java.ha.vo;

public class PostingVO {
	private String postNo;
	private String content;
	private String writer;
	
	public String getPostNo() {
		return postNo;
	}
	public void setPostNo(String postNo) {
		this.postNo = postNo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	@Override
	public String toString() {
		return "PostingVO [postNo=" + postNo + ", content=" + content + ", writer=" + writer + "]";
	}
}
