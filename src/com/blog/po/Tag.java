package com.blog.po;

import java.io.Serializable;

public class Tag implements Serializable {

	private String id;
	private String blog_id;
	public String getBlog_id() {
		return blog_id;
	}
	public void setBlog_id(String blog_id) {
		this.blog_id = blog_id;
	}
	private String tags;
	public String getId() {
		return id;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	

	
	
}
