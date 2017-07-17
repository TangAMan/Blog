package com.blog.exception;

public class BlogerException extends Exception {

	private String msg;
	public BlogerException(String msg){
		super(msg);
		this.msg=msg;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
