package com.java.test;

public class Bean {
	int no_id; 
	int operation_id; 
	int attended_flag;
	String time;
	
	public int getNo_id() {
		return no_id;
	}
	public void setNo_id(int no_id) {
		this.no_id = no_id;
	}
	public int getOperation_id() {
		return operation_id;
	}
	public void setOperation_id(int operation_id) {
		this.operation_id = operation_id;
	}
	public int getAttended_flag() {
		return attended_flag;
	}
	public void setAttended_flag(int attended_flag) {
		this.attended_flag = attended_flag;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "Bean [no_id=" + no_id + ", operation_id=" + operation_id + ", attended_flag=" + attended_flag
				+ ", time=" + time + "]";
	}
		
}
