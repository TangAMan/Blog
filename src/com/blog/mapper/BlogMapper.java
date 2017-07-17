package com.blog.mapper;

import java.util.List;

import com.blog.exception.BlogerException;
import com.blog.po.Auth;
import com.blog.po.Blogger;
import com.blog.po.BloggerExtension;
import com.blog.po.ReView;
import com.blog.po.Tag;
import com.blog.po.TagExtension;
import com.blog.vo.InsertVo;
import com.blog.vo.QueryVo;

public interface BlogMapper {

	/**
	 * 查找所有标签
	 */
	
	public List<Tag> getTag();
	/**
	 * 插入博客
	 * @param bloggerExtension 博客包装类
	 */
	public void insertBlog(BloggerExtension bloggerExtension); 
	/**
	 * 插入标签
	 * @param tag
	 */
	public void insertTag(Tag tag);
	/**
	 * 插入博客标签中间信息
	 * @param tagExtension
	 */
	public void insertBlogTags(TagExtension tagExtension);
	/**
	 * 总共多少条博客
	 * @return 查询条数
	 */
	public int getBlogAllRow();
	/**
	 * 分页查询
	 * @param queryVo 封装了查询信息
	 * @return
	 */
	public List<BloggerExtension> getPartPage(QueryVo queryVo);
	/**
	 * 根据id查找用户
	 * @param blog_id 页面提交的数据
	 * @return 查询到的博客
	 */
	public BloggerExtension findBlogById(String blog_id);
	/**
	 * 根据博客id查找评论
	 * @param blog_id
	 * @return
	 */
	public List<ReView> getAllReviewsById(String blog_id);
	/**
	 * 根据博客id查找博客评论
	 * @param id
	 * @return
	 */
	public int getReviewNumByBlogId(String id);
	/**
	 * 插入评论
	 * @param reView
	 */
	public void insertReview(ReView reView);
	/**
	 * 查找所有推荐阅读
	 * @return
	 */
	public List<BloggerExtension> RecommendBlog();
	/**
	 * 查找博客
	 * @return
	 */
	public Auth getAuth();
	
	
}
