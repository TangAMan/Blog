package com.blog.mapper;

import java.util.List;

import com.blog.po.Auth;
import com.blog.po.Blogger;
import com.blog.po.BloggerExtension;
import com.blog.po.Tag;
import com.blog.po.TagExtension;
import com.blog.vo.QueryVo;

public interface BackstageMapper {

	/**
	 * 查找博主
	 * @param user
	 * @return
	 */
	Auth findAuth(Auth user);

	/**
	 * 博客总条数
	 * @return
	 */
	int findAllBlogNum();
	/**
	 * 查找所有博客
	 * @param vo 
	 * @return
	 */
	List<BloggerExtension> findAllBlog(QueryVo vo);
	/**
	 * 根据博客id查找博客
	 * @param blog_id
	 * @return 指定id查到的博客
	 */
	BloggerExtension findBlogById(String blog_id);
	/**
	 * 根据博客id更新信息
	 * @param bloggerExtension
	 */
	void updateBlogByid(BloggerExtension bloggerExtension);
	/**
	 * 更新标签(多)
	 * @param tagExtension
	 */
	void updateTagsById(Tag tag);
	/**
	 * 更新每篇博客对应的标签
	 * @param tExtension
	 */
	void updateBlogTags(TagExtension tExtension);
	/**
	 * 根据博客id删除博客
	 * @param blog_id
	 */
	void deleteBlogById(String blog_id);
	/**
	 * 根据博客id更新博客id
	 * @param blog_id
	 */
	void updateBlogStateById(String blog_id);
	/**
	 * 把博客设置为推荐阅读
	 * @param blog_id
	 */
	void recommendBlogById(String blog_id);
	

}
