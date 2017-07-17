package com.blog.exception;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class ExceptionResolver implements HandlerExceptionResolver {

	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		// TODO Auto-generated method stub
		
		ex.printStackTrace();
		String message=null;
		BlogerException blogerException=null;
		if(ex instanceof BlogerException){
			blogerException=(BlogerException) ex;
		}else{
			blogerException = new BlogerException("未知错误");
		}
		message=blogerException.getMessage();
		try {
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView();
	}

}
