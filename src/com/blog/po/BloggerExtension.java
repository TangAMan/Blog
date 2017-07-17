package com.blog.po;

import java.util.List;

/**
 * Blogger的扩展类
 * @author admin
 *
 */
public class BloggerExtension extends Blogger {

	private String src;
	private String aid;
	//博客的标签
	private List<TagExtension> tags;
	private Tag tag;
	private Auth auth;
	//评论数量
	private int reviewnum;
	//阅读量
	private String readnum;

	

	public String getReadnum() {
		return readnum;
	}

	public void setReadnum(String readnum) {
		this.readnum = readnum;
	}

	public Auth getAuth() {
		return auth;
	}

	public void setAuth(Auth auth) {
		this.auth = auth;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public List<TagExtension> getTags() {
		return tags;
	}

	public void setTags(List<TagExtension> tags) {
		this.tags = tags;
	}

	public int getReviewnum() {
		return reviewnum;
	}

	public void setReviewnum(int reviewnum) {
		this.reviewnum = reviewnum;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	
}
