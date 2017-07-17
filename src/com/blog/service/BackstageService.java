package com.blog.service;

import com.blog.po.Auth;
import com.blog.po.Blogger;
import com.blog.po.BloggerExtension;
import com.blog.vo.InsertVo;
import com.fasionshop.utils.Page;

public interface BackstageService {

	/**
	 * 查找博主
	 * @param user
	 * @return
	 */
	Auth findAuth(Auth user);

	/**
	 * 查找所有博客
	 * @param rowpage 
	 * @param thispage 
	 * @return
	 */
	Page<BloggerExtension> findAllBlog(int thispage, int rowpage);
	/**
	 * 根据博客id查找博客
	 * @param blog_id
	 * @return 根据id查到的博客
	 */
	BloggerExtension findBlogById(String blog_id);
	/**
	 * 根据博客id更新信息
	 * @param iVo 封装前台提交过来的数据
	 * @param blog_id 博客id
	 */
	void updateById(InsertVo iVo, String blog_id);
	/**
	 * 根据博客id删除博客
	 * @param blog_id
	 */
	void deleteBlogById(String blog_id);
	/**
	 * 根据博客id更新博客状态
	 * @param blog_id
	 * @throws Exception 
	 */
	void publishBlogStateById(String blog_id) throws Exception;
	/**
	 * 把博客设置为推荐阅读
	 * @param blog_id
	 */
	void recommendBlogById(String blog_id) throws Exception;

	
}
