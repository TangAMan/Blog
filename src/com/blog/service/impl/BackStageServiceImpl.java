package com.blog.service.impl;

import javax.annotation.Resource;

import com.blog.mapper.BackstageMapper;
import com.blog.po.Auth;
import com.blog.po.Blogger;
import com.blog.po.BloggerExtension;
import com.blog.po.Tag;
import com.blog.po.TagExtension;
import com.blog.service.BackstageService;
import com.blog.vo.InsertVo;
import com.blog.vo.QueryVo;
import com.fasionshop.utils.Page;

public class BackStageServiceImpl implements BackstageService {

	@Resource(name="backstageMapper")
	private BackstageMapper backstageMapper;
	@Override
	public Auth findAuth(Auth user) {
		// TODO Auto-generated method stub
		return backstageMapper.findAuth(user);
	}
	@Override
	public Page<BloggerExtension> findAllBlog(int thispage, int pagerow) {
		// TODO Auto-generated method stub
		Page<BloggerExtension> page = new Page<BloggerExtension>();
		//首页
		page.setHomepage(thispage);
		//上一页
		page.setUppage(thispage==1?1:thispage+1);
		//总页数
		int allNum=backstageMapper.findAllBlogNum();
		int allPage= allNum/pagerow+(allNum%pagerow==0?0:1);
		page.setCurrentrow(allNum);
		page.setCurrentpage(allPage);
		page.setNextpage(thispage==allPage?allPage:thispage-1);
		page.setLastpage(allPage);
		QueryVo vo = new QueryVo();
		vo.setThispage((thispage-1)*pagerow);
		vo.setPagerow(pagerow);
		page.setList(backstageMapper.findAllBlog(vo));
		return page;
	}
	@Override
	public BloggerExtension findBlogById(String blog_id) {
		// TODO Auto-generated method stub
		return backstageMapper.findBlogById(blog_id);
	}
	@Override
	public void updateById(InsertVo iVo, String blog_id) {
		// TODO Auto-generated method stub
		BloggerExtension bloggerExtension=iVo.getBloggerExtension();
		bloggerExtension.setId(blog_id);
		backstageMapper.updateBlogByid(bloggerExtension);
		Tag tag =iVo.getTagExtension();
		tag.setBlog_id(blog_id);
		backstageMapper.updateTagsById(tag);
		String tags=iVo.getTagExtension().getTags();
		String [] tgs =tags.split(",");
		for(int i=0;i<tgs.length;i++){
			TagExtension tExtension =new TagExtension();
			tExtension.setTagname(tgs[i]);
			tExtension.setBlog_id(blog_id);
			backstageMapper.updateBlogTags(tExtension);
		}
	}
	@Override
	public void deleteBlogById(String blog_id) {
		// TODO Auto-generated method stub
		backstageMapper.deleteBlogById(blog_id);
	}
	@Override
	public void publishBlogStateById(String blog_id) throws Exception {
		// TODO Auto-generated method stub
		backstageMapper.updateBlogStateById(blog_id);
	}
	@Override
	public void recommendBlogById(String blog_id) throws Exception {
		// TODO Auto-generated method stub
		backstageMapper.recommendBlogById(blog_id);
	}
	
	

}
