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

	<script type="text/javascript" src="${pageContext.request.contextPath }/bootstrap/jqurey/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/layer/layer.js"></script>
	<style type="text/css">
		.login_box{
			list-style-type: none;
			margin: 10px;
		}
	</style>
  </head>
  
  <body>
   	<div class="container">
   		<ul class="login_box">
   			<li>用户名:<input type="text" name="username" id="name" /></li>
   			<li>密&nbsp;&nbsp;&nbsp;码:<input type="password" name="password" id="password"/></li>
   		</ul>
   		<div style="margin-left: 25%">
   			<button id="agree" >确定</button>
   			<button id="cansole" style="margin-left: 25%">取消</button>
   		</div>
   		<script type="text/javascript">
   			$(function(){
   				$("#cansole").click(function(){
   					var name=parent.layer.getFrameIndex(window.name);
   					parent.layer.close(name);   				
   				});
   				
   				$("#agree").click(function(){
   					var name=document.getElementById("name").value;
   					var password=document.getElementById("password").value;
   					if(name==""||password==""){
   						layer.msg("用户名或密码不能为空",function(){
   						});
   					}else if(name!=""&&password!=""){
   					
   						 $.ajax({
            			type: "post",
             			url: "Login",
             			data: "name="+name+"&password="+password,
            			success: function(data){
            			 if(data==0){
            			 	layer.msg("用户名或密码不正确",function(){
   							});
            			 }else if(data==1){
            			 	var name=parent.layer.getFrameIndex(window.name);
   							parent.layer.close(name);
   							parent.window.location.href="/Blog/Backstage";
   							$.ajax({
   								type: "post",
             					url: "Backstage"
   							}); 
   							
            			 };
            			 
                        }
                         
                         });
   					
   					}
   				});
   				
   			
   			});
   		</script>
   	</div>
  </body>
</html>
