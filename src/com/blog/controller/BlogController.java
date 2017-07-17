package com.blog.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.blog.exception.BlogerException;
import com.blog.po.Auth;
import com.blog.po.Blogger;
import com.blog.po.BloggerExtension;
import com.blog.po.ReView;
import com.blog.po.Tag;
import com.blog.service.BlogService;
import com.blog.util.GetUuid;
import com.blog.util.jedisFactory;
import com.blog.vo.InsertVo;
import com.blog.vo.QueryVo;
import com.fasionshop.utils.Page;
/**
 * 博客controller
 * @author admin
 *
 */
@Controller
public class BlogController {

	@Autowired
	@Qualifier("blogService")
private BlogService blogService;
	
	
	/**
	 * 博客发布
	 * @param iVo包装类型
	 * @param request
	 * @return
	 * @throws Exception
	 */
@RequestMapping(value="/PublishBlog",method=RequestMethod.POST)
public String PublishBlog(InsertVo iVo,HttpServletRequest request) throws Exception{
	
		String title=iVo.getBloggerExtension().getTitle();
		String path=request.getSession().getServletContext().getRealPath("/WEB-INF/blog/");
		String insertPath="/WEB-INF/blog/";
		String context=iVo.getBloggerExtension().getContext();
		/*byte[] bys = context.getBytes();*/
		File file = new File(path);
		if(!file.exists()){
			file.mkdirs();
		}
		/*FileOutputStream fileOutputStream = new FileOutputStream(new File(path,title+".html"));*/
		 PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path+"/"+title+".html"),"utf-8")));
		/*fileOutputStream.write(bys, 0, bys.length);
		fileOutputStream.close();*/
		 out.write(context, 0, context.length());
		 out.flush();
		 out.close();
		//存储博客
		BloggerExtension bloggerExtension=  iVo.getBloggerExtension();
		bloggerExtension.setSrc(insertPath);
		bloggerExtension.setAid("a189c1f7-a3eb-43b9-86d0-1c020ce09675");
		String str=iVo.getBloggerExtension().getContext();
		/*if(str!=null){
			str=str.length()>120?str.substring(0,120)+"....":str+"....";
		}*/
		str=str==null?"":(str.length()>120?str.substring(0,120)+"...":str+"...");
		bloggerExtension.setContext(str);
		blogService.save(iVo);
		
		return "redirect:Backstage";
	}
	/**
	 * 分页controller
	 * @param model
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping("/index/{thispage}")
	@SuppressWarnings("all")
public String Index(Model model, @PathVariable String thispage) throws Exception{
			/*String path=request.getSession().getServletContext().getRealPath("/WEB-INF/blog/test.html");
		
			model.addAttribute("path", path);*/
			int thipage=Integer.parseInt(thispage);
			Page<BloggerExtension> page=blogService.PartPage(thipage,4);
			//每条博客评论数，遍历所有，根据博客id查询,获得每条博客点击量
			Jedis jedis =jedisFactory.getFactory().getPool().getResource();
			for(BloggerExtension bloggerExtension:page.getList()){
				bloggerExtension.setReviewnum(blogService.getReviewByBlogId(bloggerExtension.getId()));
				bloggerExtension.setReadnum(jedis.hget("blog", bloggerExtension.getId()));
				
			}
			
			List<BloggerExtension> recommends=blogService.RecommendBlog();
			for(BloggerExtension bloggerExtension:recommends){
				bloggerExtension.setReadnum(jedis.hget("blog", bloggerExtension.getId()));	
			}
			jedisFactory.getFactory().close(jedis);
			model.addAttribute("recommends",recommends);
			model.addAttribute("page", page);
			return "index";
		
	}
/**
 * 第一次进入	
 * @param model
 * @return
 * @throws Exception
 */
@RequestMapping("/index")
@SuppressWarnings("all")
public String index(Model model) throws Exception{

			Page<BloggerExtension> page=blogService.PartPage(1,4);
			//每条博客评论数，遍历所有，根据博客id查询,获得每条博客点击量
			Jedis jedis =jedisFactory.getFactory().getPool().getResource();
			for(BloggerExtension bloggerExtension:page.getList()){
				bloggerExtension.setReviewnum(blogService.getReviewByBlogId(bloggerExtension.getId()));
				bloggerExtension.setReadnum(jedis.hget("blog", bloggerExtension.getId()));
				
			}
			
			List<BloggerExtension> recommends=blogService.RecommendBlog();
			for(BloggerExtension bloggerExtension:recommends){
				bloggerExtension.setReadnum(jedis.hget("blog", bloggerExtension.getId()));	
			}
			jedisFactory.getFactory().close(jedis);
			Auth auth=blogService.getAuth();
			model.addAttribute("recommends",recommends);
			model.addAttribute("page", page);
			model.addAttribute("auth", auth);
			return "index";
		
	}
	
	
	
	@RequestMapping(value="/findBlog/{blog_id}",method=RequestMethod.GET)
	public String findBlog(Model model,@PathVariable String blog_id){
		
		BloggerExtension bloggerExtension=blogService.findBlogById(blog_id);
		Jedis jedis=jedisFactory.getFactory().getPool().getResource();
		bloggerExtension.setReadnum(jedis.hget("blog", blog_id));
		bloggerExtension.setReviewnum(blogService.getReviewByBlogId(blog_id));
		model.addAttribute("blog",bloggerExtension );
		//查找评论内容
		List<ReView> reviews =blogService.getAllReviewsById(blog_id);
		model.addAttribute("reviews", reviews);
		return "Templte";
	}
/**
 * 根据博客id插入评论
 * @throws IOException 
 * 
 */
	@RequestMapping("/submitReview")
	public  void submitReview(ReView reView,HttpServletResponse response) throws IOException{
		blogService.insertReviewById(reView);
		response.getWriter().write("{state:1}");
		
	}

	@RequestMapping("/PublishBlog")
	public String publishBlog(){
		return "PublishBlog";
		
	}
}