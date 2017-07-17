package com.blog.service;

import java.util.List;

import com.blog.exception.BlogerException;
import com.blog.po.Auth;
import com.blog.po.Blogger;
import com.blog.po.BloggerExtension;
import com.blog.po.ReView;
import com.blog.po.Tag;
import com.blog.vo.InsertVo;
import com.blog.vo.QueryVo;
import com.fasionshop.utils.Page;

public interface BlogService {


	/**
	 * 插入信息
	 * @param iVo 封装了文章，标签，中间表信息
	 */
	public void save(InsertVo iVo);

	/**
	 * 分页查询所有
	 * @param parseInt
	 * @param i
	 * @return
	 */
	public Page PartPage(int parseInt, int i);

	/**
	 * 根据id查找用户
	 * @param blog_id 页面提交过来的数据
	 * @return 查找到的博客
	 */
	public BloggerExtension findBlogById(String blog_id);
	
	/**
	 * 根据id查找评论
	 * @param blog_id
	 * @return
	 */
	public List<ReView> getAllReviewsById(String blog_id);

	/**
	 * ajax分页
	 * @param thispage
	 * @param i
	 * @return
	 */
	public Page<BloggerExtension> getPage(int thispage, int i);

	/**
	 * 根据博客id查找评论量
	 * @param id
	 * @return 返回查询到评论量
	 */
	public int getReviewByBlogId(String id);
	/**
	 * 为指定博客插入评论
	 * @param reView
	 */
	public void insertReviewById(ReView reView);
	/**
	 * 查找所有推荐阅读
	 * @return
	 */
	public List<BloggerExtension> RecommendBlog();
	/***
	 * 查询博主
	 * @return
	 */
	public Auth getAuth();

	
	

	
}
