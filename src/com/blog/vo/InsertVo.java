package com.blog.vo;

import com.blog.po.Blogger;
import com.blog.po.BloggerExtension;
import com.blog.po.TagExtension;

/**
 * 包装插入类
 * @author admin
 *
 */
public class InsertVo {
	private TagExtension tagExtension;
	private BloggerExtension bloggerExtension;
	public BloggerExtension getBloggerExtension() {
		return bloggerExtension;
	}
	public void setBloggerExtension(BloggerExtension bloggerExtension) {
		this.bloggerExtension = bloggerExtension;
	}
	public TagExtension getTagExtension() {
		return tagExtension;
	}
	public void setTagExtension(TagExtension tagExtension) {
		this.tagExtension = tagExtension;
	}
	
	
	
	
	
}
