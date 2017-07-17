<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.blog.com/blog/tag/core" prefix="readfile" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>首页</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport" content="width=device-width, initial-scale=1" />
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/bootstrap/css/bootstrap.css" />
	   <link rel="stylesheet" href="${pageContext.request.contextPath }/css/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/style.css" />

	<script type="text/javascript" src="${pageContext.request.contextPath }/bootstrap/jqurey/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/index.js"></script>
    <script src="${pageContext.request.contextPath }/js/modernizr.js"></script>
 	<script type="text/javascript">
 		window.onload=function(){
 			$.post("${pageContext.request.contextPath}/index");
 		};
 	
 	</script>
 	<style type="text/css">
 		#jubmp:hover{
 			color: red;
 		}
 		#jubm font:hover{
 			color: red;
 		}
 	</style>
  </head>	
 <body>
 	<!--引入头  -->
 	<c:import url="Heard.jsp"></c:import>
    <div class="widewrapper main">
        <div class="container">
            <div class="row">
                <div class="col-md-8 blog-main">
                	<c:if test="${ empty page.list}">
                		<p>未找到数据...</p>
                	</c:if>
                	<c:if test="${!empty page.list }">
	                	<c:forEach items="${page.list }" var="page">
	                		 <div class="row">
		                        <div class="col-md-6 col-sm-6" style="width: 680px;" >
		                            <article class=" blog-teaser">
		                            	<div style="margin-top: 4%;margin-left: 3%">
		                            		<a href="${pageContext.request.contextPath }/findBlog/${page.id}" id="jubm"><font size="6" color="#7B8690">${page.title }</font></a>
		                            	</div>
		                            	<hr style="width: 100%">
		                                <div class="body" style="margin-top: 20px;">
		                                   ${page.context }
		                                    
		                                </div>
		            					<div class="handle">
		            						<ul>
		            							<li><span class="glyphicon glyphicon-comment" aria-hidden="true"><p style="margin-top: -19px;margin-left: 20px">${page.reviewnum }</p></span></li>
		            							<li><span class="glyphicon glyphicon-eye-open" aria-hidden="true"><p style="margin-top: -18px;margin-left: 20px">${page.readnum }</p></span></li>
		            							<li><span class="glyphicon glyphicon-calendar" aria-hidden="true"><p style="margin-top: -18px;margin-left: 20px"><fmt:formatDate value="${page.publishtime }" pattern="yyyy-MM-dd" /></p></span></li>
		            							<li><span class="glyphicon glyphicon-user" aria-hidden="true"><p style="margin-top: -18px;margin-left: 20px">${page.auth.nickname }</p></span></li>
		            							<li><span class="glyphicon glyphicon-menu-hamburger" aria-hidden="true"><p style="margin-top: -20px;margin-left: 20px">${page.cate}</p></span></li>
		            						</ul>
		            					</div>
		                                <div class="clearfix" style="margin-top: 30px;">
		                                    <a href="${pageContext.request.contextPath }/findBlog/${page.id}" class="btn btn-clean-one" target="_blank">阅读全文</a>
		                                </div>
		                            </article>
		                        </div>
	                       </div>
	                	</c:forEach>
                	</c:if>
                </div>
                <aside class="col-md-4 blog-aside">
                    
                     <div class="aside-widget">
                        <header>
                            <h3>关于博主</h3>
                        </header>
                        <div class="body">
                          <c:if test="${empty page.list }">
                          	  <div class="headImg" style="background-image: url('./img/tx.jpg');">
                           	  </div>
	                            <p style="margin-left: 46%;color: gray;margin-top: 10px;">${auth.nickname }</p>
	                            <hr>
	                            <p style="margin-left: 36%">QQ:${auth.qq }</p>
	                            <p style="margin-left: 26%">E-mail:${auth.email }</p>
                          </c:if>
                           <c:if test="${!empty page.list }">
                          	  <div class="headImg" style="background-image: url('./img/tx.jpg');background-position:-4px -8px;background-repeat:no-repeat;">
                            	
                           	 </div>
	                            <p style="margin-left: 46%;color: gray;margin-top: 10px;">${page.list[0].auth.nickname }</p>
	                            <hr>
	                            <p style="margin-left: 36%">QQ:${page.list[0].auth.qq }</p>
	                            <p style="margin-left: 26%">E-mail:${page.list[0].auth.email }</p>
                          </c:if>
                        </div>
                    </div>
                    
                    <div class="aside-widget">
                        <header>
                            <h3>推荐阅读</h3>
                        </header>
                        <div class="body">
                            <ul class="clean-list">
                            	<c:forEach items="${ recommends}" var="recommend">
                            		<li><a href="${pageContext.request.contextPath }/findBlog/${recommend.id}">${recommend.title }</a><p style="margin-left: 40%;margin-top: -22px;">${recommend.readnum }人阅读</p></li>
                            	</c:forEach>
                            </ul>
                        </div>
                    </div>
                
                    <div class="aside-widget">
                        <header>
                            <h3>友情连接</h3>
                        </header>
                        <div class="body">
                            <ul class="clean-list">
                                <li><a href="http://www.lkhhp.cn/Shop">怀品的个人商城</a></li>
                                <li><a href="http://www.murongx.com">小周娃的网站</a></li>
                                <li><a href="http://blog.cyanstream.com">玩双截棍的熊猫的博客</a></li>
                                
                            </ul>
                        </div>
                    </div>
                </aside>
            </div>
        </div>
    </div>
    <footer>
    	 <div class="widewrapper footer" style="background-color: white;">
            <div class="container">
                <div class="row">
                    <div class="col-md-4 footer-widget partPage" style="width: 100%;margin-left: 18%">
                    	<c:if test="${ !empty page.list }">
                    		<ul>
	                    		<li><p style="margin-top: 5px;color: black;"><b>共${page.currentrow }条记录,${page.currentpage }页</b></p></li>
	                    		<li><button class="navigation"  id="homepage" ><a href="${pageContext.request.contextPath }/index" >首页</a></button></li>
	                    		<li><button class="navigation"  id="uppage" ><a href="${pageContext.request.contextPath }/index/${page.uppage}">上一页</a></button></li>
	                    		<li><button class="navigation"  id="nextpage" ><a href="${pageContext.request.contextPath }/index/${page.nextpage}">下一页</a></button></li>
	                    		<li><button class="navigation"  id="lastpage" ><a href="${pageContext.request.contextPath }/index/${page.lastpage}">尾页</a></button></li>
	                    		<li><p style="margin-top: 5px;color: black;"><b>${page.thispage }/${page.currentpage }</b></p></li>
                    		</ul>
                    	</c:if>
                    </div>
                   
                </div>
            </div>
        </div>
        <c:import url="Footer.jsp"></c:import>
    </footer>

</body>
	
</html>
