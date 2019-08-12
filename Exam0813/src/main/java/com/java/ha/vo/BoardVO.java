package com.java.ha.vo;


public class BoardVO {
	private String boardNo;
	private String content;
	private String writer;
	public String getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(String boardNo) {
		this.boardNo = boardNo;
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
		return "BoardVO [boardNo=" + boardNo + ", content=" + content + ", writer=" + writer + "]";
	}
}
