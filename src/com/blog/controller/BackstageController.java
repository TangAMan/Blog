package com.blog.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import redis.clients.jedis.Jedis;

import com.blog.po.Auth;
import com.blog.po.Blogger;
import com.blog.po.BloggerExtension;
import com.blog.service.BackstageService;
import com.blog.service.BlogService;
import com.blog.util.MD5Utils;
import com.blog.util.jedisFactory;
import com.blog.vo.InsertVo;
import com.fasionshop.utils.Page;
import com.google.gson.Gson;

@Controller
public class BackstageController {

	@Resource(name="backstageService")
	private BackstageService backstageService;
	
	@RequestMapping("/Login")
	public  void Login(String name,String password ,HttpServletResponse response,HttpServletRequest request) throws IOException{
		Auth user = new Auth();
		user.setUsername(name);
		user.setPassword(MD5Utils.md5(password));
		Auth auth = backstageService.findAuth(user);
		if(auth!=null){
			response.getWriter().print(1);
			request.getSession().setAttribute("auth", auth);
		}else{
			response.getWriter().print(0);
		}
		
	}
	@RequestMapping("/LoginOut")
	public void LoginOut(HttpServletRequest request,HttpServletResponse response) throws IOException{
		if(request.getSession(false)!=null&&request.getSession().getAttribute("auth")!=null){
			request.getSession().invalidate();
			response.getWriter().print(1);
		}

		
	}
	@RequestMapping("/Backstage")
	public String AllBlog(Model model){
		
	/*	List<BloggerExtension> list=backstageService.findAllBlog();*/
		Page<BloggerExtension> page =backstageService.findAllBlog(1,10);
		Jedis jedis=jedisFactory.getFactory().getPool().getResource();
		for(BloggerExtension bloggerExtension:page.getList()){
			bloggerExtension.setReadnum(jedis.hget("blog", bloggerExtension.getId()));
		}
		jedisFactory.getFactory().close(jedis);
		model.addAttribute("page",page);
		return "Backstage";
	}
	
	@RequestMapping("/Backstage/{thispage}")
	public String PartBlog(@PathVariable String thispage,Model model){
		
	/*	List<BloggerExtension> list=backstageService.findAllBlog();*/
		int thispag=Integer.parseInt(thispage);
		Page<BloggerExtension> page =backstageService.findAllBlog(thispag,10);
		Jedis jedis=jedisFactory.getFactory().getPool().getResource();
		for(BloggerExtension bloggerExtension:page.getList()){
			bloggerExtension.setReadnum(jedis.hget("blog", bloggerExtension.getId()));
		}
		jedisFactory.getFactory().close(jedis);
		model.addAttribute("page",page);
		return "Backstage";
	}
	
	
	
	@RequestMapping("/edit/{blog_id}")
	public String edit(@PathVariable String blog_id,Model model){
		BloggerExtension blogger=backstageService.findBlogById(blog_id);
		model.addAttribute("blog", blogger);
		return "edit";
	}
	
	@RequestMapping("/edit")
	public String Edit(){
		return "edit";
	}
	
	@RequestMapping("/Update/{blog_id}")
	public String Update(InsertVo iVo,@PathVariable String blog_id) throws Exception{
		backstageService.updateById(iVo,blog_id);
		return "redirect:/Backstage";
	}
	
	@RequestMapping("/delteblog/{blog_id}")
	public void Delete(@PathVariable String blog_id,HttpServletResponse response) throws Exception{
		backstageService.deleteBlogById(blog_id);
		
			response.getWriter().print(1);
		
	}
	@RequestMapping("/Blogpublish/{blog_id}")
	public void publishBlog(@PathVariable String blog_id,HttpServletResponse response){
		try{
			backstageService.publishBlogStateById(blog_id);
			response.getWriter().print(1);
		}catch(Exception e){
			try {
				response.getWriter().print(0);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	@RequestMapping("/recommend/{blog_id}")
	public void Recommend(@PathVariable String blog_id,HttpServletResponse response){
		
		try{
			backstageService.recommendBlogById(blog_id);
			response.getWriter().print(1);
		}catch(Exception e){
			e.printStackTrace();
			try {
				response.getWriter().print(0);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	//批量删除
	@RequestMapping("/datadel")
	public void datadel(@RequestBody String[] blogs){
		for(int i=0;i<blogs.length;i++){
			backstageService.deleteBlogById(blogs[i]);	
		}
	}
	
}
