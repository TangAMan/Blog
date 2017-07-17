package com.blog.util;

import com.blog.mapper.BlogMapper;
import com.blog.po.TagExtension;
import com.blog.vo.InsertVo;

public class InsertUtil {

	private BlogMapper blogMapper;
	private InsertVo iVo;
	private TagExtension tagExtension;
	public InsertUtil (BlogMapper blogMapper,InsertVo iVo){
		this.blogMapper=blogMapper;
		this.iVo=iVo;
	}
	
	public void InsertTag(){
		tagExtension =new TagExtension();
		tagExtension.setBlog_id(iVo.getBloggerExtension().getId());
		tagExtension.setTags(iVo.getTagExtension().getTags());
		blogMapper.insertTag(tagExtension);
	}
	
	public void InsertBlogTags(){
			String str=iVo.getTagExtension().getTags().toLowerCase();
			String[] tags = str.split(",");
			for(int i=0;i<tags.length;i++){
				tagExtension.setBlog_id(iVo.getBloggerExtension().getId());
				tagExtension.setTagname(tags[i]);
				blogMapper.insertBlogTags(tagExtension);
			}
		}
		
}
