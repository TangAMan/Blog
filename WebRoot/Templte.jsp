<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib  uri="http://www.blog.com/blog/tag/core" prefix="readfile" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    <base href="<%=basePath%>">
    <title>文章正文</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta name="viewport" content="width=device-width, initial-scale=1" />
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/bootstrap/css/bootstrap.css" />
	   <link rel="stylesheet" href="${pageContext.request.contextPath }/css/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/style.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath }/bootstrap/jqurey/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/layer/layer.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/Templte.js"></script>
    <script src="${pageContext.request.contextPath }/js/modernizr.js"></script>
 	<script type="text/javascript">
 	</script>
  </head>
  
  <body>
  		<c:import url="Heard.jsp"></c:import>
  		 <div class="widewrapper main">
        <div class="container">
            <div class="row">
                <div class="col-md-8 blog-main">
                    <article class="blog-post">
                      
                        <div class="body">
                            <h2>${blog.title }</h2>
                            <div class="meta">
                                <i class="fa fa-user"></i> ${blog.auth.nickname } <i class="fa fa-calendar"></i><fmt:formatDate value="${blog.publishtime }" pattern="yyyy-MM-dd" /><i class="fa fa-comments"></i><span class="data"><a href="#comments">${blog.readnum }</a></span>
                            </div>

                            <div class="container" style="border: 1px solid #DDDDDD;width: 700px;"  ><readfile:readFile src="${blog.src }${blog.title }.html"/></div>

                        </div>
                    </article>

                    <aside class="comments" id="comments">
                        
						<input type="hidden" id="blog_id" value="${blog.id }" />
                        <h2><i class="fa fa-comments"></i> ${blog.reviewnum }&nbsp;评论</h2>
							<c:forEach items="${reviews }" var="review">
								<article class="comment">
		                            ${review.critic }<br>
		                            ${review.content }<br>
		                            <fmt:formatDate value="${review.reviewtime }" pattern="yyyy-MM-dd HH:mm" />
		                            
		                             
		                            <hr>
						</c:forEach>
                        
                    </aside>

                    <aside class="create-comment" id="create-comment">   

                        <h2><i class="fa fa-pencil"></i> 攥写评论</h2>

                       
                            <div class="row">
                                <div class="col-md-6">
              						 <input type="text" name="critic" id="comment-name" placeholder="Your Name" class="form-control input-lg">    
                                </div>
                            </div>


                            <textarea rows="10" name="content" id="comment-body" onkeydown="countChar('comment-body','cahrNum')"  onkeyup="countChar('comment-body','cahrNum')"  placeholder="Your Message" class="form-control input-lg"></textarea>
							  可以输入   <spna id="cahrNum">140</spna>
                            <div class="buttons clearfix">
                                <button type="submit" id="submit" class="btn btn-xlarge btn-clean-one">Submit</button>
                            </div>
                    </aside>
                </div>
                <aside class="col-md-4 blog-aside">
                
                	 <div class="aside-widget">
                        <header>
                            <h3>关于博主</h3>
                        </header>
                        <div class="body">
                          
                          	  <div class="headImg" style="background-image: url('./img/tx.jpg');">
                           	  </div>
	                            <p style="margin-left: 46%;color: gray;margin-top: 10px;">${auth.nickname }</p>
	                            <hr>
	                            <p style="margin-left: 36%">QQ:750625513</p>
	                            <p style="margin-left: 26%">E-mail:750625513@qq.com</p>
                         
                        </div>
                    </div>
                	
                	
                	
                    <div class="aside-widget">
                        <header>
                            <h3>Tags</h3>
                        </header>
                        <div class="body clearfix">
                            <ul class="tags">
                               	<c:forEach items="${blog.tags }" var="tag">
                               		 <li><a >${tag.tagname }</a></li>
                               	</c:forEach>
                                                      
                            </ul>
                        </div>
                    </div>
                </aside>
            </div>
        </div>
    </div>

 
       <c:import url="Footer.jsp"></c:import>
  </body>
</html>
