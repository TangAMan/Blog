<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	<meta name="viewport" content="width=device-width, initial-scale=1" />
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/bootstrap/css/bootstrap.css" />
	   <link rel="stylesheet" href="${pageContext.request.contextPath }/css/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/style.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath }/bootstrap/jqurey/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/bootstrap/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath }/js/modernizr.js"></script>
 	<script type="text/javascript">
 	</script>

  </head>
  <body>
  	<footer>
        <div class="widewrapper copyright">
                Copyright 2015 More Info <a href="${pageContext.request.contextPath }" target="_blank" title="Blog">Blog</a> - Collect from <a href="${pageContext.request.contextPath }" title="Blog" target="_blank">Blog</a>
        </div>
    </footer>
  
  </body>
</html>
