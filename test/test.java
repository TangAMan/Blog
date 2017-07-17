import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.blog.mapper.BlogMapper;
import com.blog.po.BloggerExtension;
import com.blog.po.Tag;
import com.blog.service.BlogService;
import com.blog.service.impl.BlogServiceImpl;
import com.blog.util.GetUuid;
import com.blog.util.MD5Utils;
import com.blog.vo.InsertVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/applicationConfig-dao.xml")
public class test {

	@Autowired
	@Qualifier("blogMapper")
	private BlogMapper blogMapper;
	@Test
	public void test(){
		
	
		BloggerExtension bloggerExtension = new BloggerExtension();
		
		bloggerExtension.setTitle("test");
		bloggerExtension.setContext("测试");
		bloggerExtension.setCate("java");
		bloggerExtension.setSrc("src");
		bloggerExtension.setState(0);
		InsertVo iVo = new InsertVo();
		
		blogMapper.insertBlog(bloggerExtension);
		
		
	}
	@Test
	public void md5(){
		System.out.println(MD5Utils.md5("123"));
	}
}
