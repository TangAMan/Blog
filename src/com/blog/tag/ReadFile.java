package com.blog.tag;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.blog.exception.BlogerException;
/**
 * 
 * @author admin
 *自定义标签，输出文件
 */
public class ReadFile extends SimpleTagSupport {

	private String src;

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}
	@Override
	public void doTag() throws JspException, IOException {
		PageContext context =(PageContext) getJspContext();
		if(src==null){
			try {
				throw new BlogerException("找不到路径");
			} catch (BlogerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String path=context.getServletContext().getRealPath(src);
		BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(new FileInputStream(path),"utf-8"));
		String line=null;
		while((line=bufferedReader.readLine())!=null){
			context.getOut().write(line, 0, line.length());
		}
		bufferedReader.close();
		
		/*BufferedReader bReader = new BufferedReader(new InputStreamReader(inputStream));
		String line=null;
		while((line=bReader.readLine())!=null){
			context.getOut().write(line, 0, line.length());
		}
		inputStream.close();
		bReader.close();*/
	}
}
