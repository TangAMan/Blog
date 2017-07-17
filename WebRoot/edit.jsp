<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 <script type="text/javascript" src="./bootstrap/jqurey/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/wangeditor/js/wangEditor.min.js"></script> 
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/wangeditor/css/wangEditor.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/PublishBlog.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/bootstrap/css/bootstrap.min.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/publishblog.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/layer/layer.js"></script>
  </head>
  
  <body>
  <!--引入头  -->	  			
  	<div><c:import url="Heard.jsp"></c:import></div>
  			
  	 <div class="container" style="margin-left: 10.5%">
  	 	<form action="${pageContext.request.contextPath }/Update/${blog.id}" method="post" onsubmit="return Blogsubmit()">
			标题:<input type="text" name="bloggerExtension.title" id="title" class="title" style="margin-top: 2%;width: 86.5%" value="${blog.title }" />
				 					
				 <div style="width: 90%;margin-top: 3%" >
					 <textarea id="textarea1" name="bloggerExtension.context" style="height:400px;max-height:500px;">
					   	 ${blog.context }
					   	 		
					 </textarea>
				</div>
				
				文章标签:<input type="text" id="tags" name="tagExtension.tags" style="margin-top: 2%;width: 84%" value="${blog.tag.tags }"><br>
				
				<div style="margin-top: 2%">
				文章分类:<input type="radio" name="bloggerExtension.cate" value="java" <c:if test="${blog.cate=='java' }">
						checked='checked'
						</c:if> />java
					<input type="radio" name="bloggerExtension.cate"  value="c#" <c:if test="${blog.cate=='c#' }" >
						checked='checked'
						</c:if>/>c#
					<input type="radio" name="bloggerExtension.cate" value=".net" <c:if test="${blog.cate=='.net' }">
						checked='checked'
						</c:if> />.net
					<input type="radio" name="bloggerExtension.cate" value="c++"  <c:if test="${blog.cate =='c++'}">
						checked='checked'
					</c:if> />c++
				</div>
				<br>
				<div class="buttons clearfix">
                  <button  class="btn btn-xlarge btn-clean-one"  style="width: 91%" >Submit</button>
         		</div>
		</form>
  	 </div>
  	 	
			  	
  	<!--引入尾  -->	
  	<div style="margin-top: 3%"><c:import url="Footer.jsp"></c:import></div>	
  </body>
  
</html>
