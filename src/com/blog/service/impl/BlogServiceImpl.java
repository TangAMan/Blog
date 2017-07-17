package com.blog.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.blog.exception.BlogerException;
import com.blog.mapper.BlogMapper;
import com.blog.po.Auth;
import com.blog.po.Blogger;
import com.blog.po.BloggerExtension;
import com.blog.po.ReView;
import com.blog.po.TagExtension;
import com.blog.service.BlogService;
import com.blog.util.InsertUtil;
import com.blog.util.jedisFactory;
import com.blog.vo.InsertVo;
import com.blog.vo.QueryVo;
import com.fasionshop.utils.Page;

public class BlogServiceImpl implements BlogService {

	@Autowired
	@Qualifier("blogMapper")
	private BlogMapper blogMapper;
	
	
	public void save(InsertVo iVo) {
		// TODO Auto-generated method stub
		//查找标签
		//存博客
		blogMapper.insertBlog(iVo.getBloggerExtension());
		InsertUtil insertUtil = new InsertUtil(blogMapper, iVo);
		insertUtil.InsertTag();
		insertUtil.InsertBlogTags();
		
	}

	/**
	 * 普通物理分页
	 */
	public Page PartPage(int thisPage, int pageRow) {
		// TODO Auto-generated method stub
		Page<BloggerExtension> page = new Page<BloggerExtension>();
		//当前页
		page.setThispage(thisPage);
		//首页
		page.setHomepage(1);
		//总条数
		int allRow=blogMapper.getBlogAllRow();
		page.setCurrentrow(allRow);
		//总页数
		int allPage=allRow/pageRow+(allRow%pageRow==0?0:1);
		page.setCurrentpage(allPage);
		//下页
		page.setNextpage(thisPage==allPage?allPage:thisPage+1);
		//上页
		page.setUppage(thisPage==1?1:thisPage-1);
		page.setLastpage(allPage);
		//分页查询
		QueryVo queryVo = new QueryVo();
		queryVo.setThispage((thisPage-1)*pageRow);
		queryVo.setPagerow(pageRow);
		List<BloggerExtension> blogs=blogMapper.getPartPage(queryVo);
		page.setList(blogs);
		return page;
	}


	public BloggerExtension findBlogById(String blog_id) {
		// TODO Auto-generated method stub
		return blogMapper.findBlogById(blog_id);
	}


	public List<ReView> getAllReviewsById(String blog_id) {
		// TODO Auto-generated method stub
		return blogMapper.getAllReviewsById(blog_id);
	}

	/**
	 * ajax物理分页
	 */
	@Override
	public Page<BloggerExtension> getPage(int thispage,int pageRow) {
		// TODO Auto-generated method stub
		
			/**
			 * 这个方法中已经把需要的数据封装好，在页面使用时，直接调用
			 */
				Page<BloggerExtension> page = new Page<BloggerExtension>();
				//当前页
				page.setThispage(thispage);
				//首页
				page.setHomepage(1);
				//总条数
				int allRow=blogMapper.getBlogAllRow();
				page.setCurrentrow(allRow);
				//总页数
				int allPage=allRow/pageRow+(allRow%pageRow==0?0:1);
				page.setCurrentpage(allPage);
				//下页
				page.setNextpage(thispage==allPage?allPage:thispage+1);
				//上页
				page.setUppage(thispage==1?1:thispage-1);
				page.setLastpage(allPage);
				//分页查询
				QueryVo queryVo = new QueryVo();
				queryVo.setThispage((thispage-1)*pageRow);
				queryVo.setPagerow(pageRow);
				List<BloggerExtension> blogs=blogMapper.getPartPage(queryVo);
				page.setList(blogs);
				return page;
	}

	/**
	 * 根据博客id查找评论
	 */
	@Override
	public int getReviewByBlogId(String id) {
		// TODO Auto-generated method stub
		return blogMapper.getReviewNumByBlogId(id);
	}

	@Override
	public void insertReviewById(ReView reView) {
		// TODO Auto-generated method stub
		blogMapper.insertReview(reView);
	}

	@Override
	public List<BloggerExtension> RecommendBlog() {
		// TODO Auto-generated method stub
		return blogMapper.RecommendBlog();
	}

	@Override
	public Auth getAuth() {
		// TODO Auto-generated method stub
		return blogMapper.getAuth();
	}


	
}



