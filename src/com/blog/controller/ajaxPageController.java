package com.blog.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blog.mapper.BlogMapper;
import com.blog.po.BloggerExtension;
import com.blog.service.BlogService;
import com.fasionshop.utils.Page;
import com.google.gson.Gson;

@Controller
@SuppressWarnings("all")
public class ajaxPageController {

	@Resource(name="blogService")
	private BlogService blogService;
	@RequestMapping("/page")
	/**
	 * 这里获取到页面传过来的参数，参数传递到service层
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void page(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String thispage=request.getParameter("pram");
		int thispag=Integer.parseInt(thispage);
		Page<BloggerExtension> data=blogService.getPage(thispag,3);
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//转为json数据，页面不用解析直接使用
		response.getWriter().write(new Gson().toJson(data));

		
		
		
	}
}
