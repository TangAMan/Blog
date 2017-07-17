package com.blog.po;

import java.io.Serializable;
import java.sql.Timestamp;

public class ReView implements Serializable {

	private String id;
	private String  blog_id;
	//评论人
	private String critic;
	private String content;
	private Timestamp reviewtime;
	public int  getReviewnum() {
		return reviewnum;
	}
	public void setReviewnum(int  reviewnum) {
		this.reviewnum = reviewnum;
	}
	private int reviewnum;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBlog_id() {
		return blog_id;
	}
	public void setBlog_id(String blog_id) {
		this.blog_id = blog_id;
	}
	public String getCritic() {
		return critic;
	}
	public void setCritic(String critic) {
		this.critic = critic;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getReviewtime() {
		return reviewtime;
	}
	public void setReviewtime(Timestamp reviewtime) {
		this.reviewtime = reviewtime;
	}
	
	
}
