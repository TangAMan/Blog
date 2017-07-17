package com.blog.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.blog.util.jedisFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class BlogHandlerInterceptor implements HandlerInterceptor {

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		// TODO Auto-generated method stub
		String url = request.getRequestURL().substring(request.getRequestURL().lastIndexOf("/")+1);
		
		Jedis jedis=jedisFactory.getFactory().getPool().getResource();
		if(jedis.hget("blog", url)==null){
			jedis.hset("blog", url, "1");
		}else{
			jedis.hincrBy("blog", url, 1);
		}
		jedisFactory.getFactory().close(jedis);
		return true;
	}

}
