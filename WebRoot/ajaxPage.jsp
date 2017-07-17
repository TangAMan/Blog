<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ajaxPage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script type="text/javascript" src="./bootstrap/jqurey/jquery-1.7.1.min.js"></script><!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
	
		var da;
		$(function(){
			//首次调用
			ajaxRequest(1);
			//点击事件触发时调用
				bindFunction();
			});
			

		function ajaxRequest(thispage){
				
					$.post("${pageContext.request.contextPath}/page",{pram:thispage},function(data){
				 
				 var html="<ul>";
				 for(var i=0;i<data.list.length;i++){
				 	html+="<li>"+data.list[i].title+"</li>";
				 }
				 html+="</ul>";
				$("#content").html(html);
				$("#current")[0].value=data.thispage;
				$("#allpage")[0].value=data.currentpage;
				da=data;
				},"json");
				
		};
		
		//绑定事件
				function bindFunction(){
				
					$("#nextpage").click(function(){
					//调用从后台返回过来的数据，直接需要的数据拿到就可以访问
						ajaxRequest(da.nextpage);
					
					});
					
					$("#uppage").click(function(){
						ajaxRequest(da.uppage);
					
					});
					
					$("#first").click(function(){
						ajaxRequest(1);
					
					});
					$("#endpage").click(function(){
						ajaxRequest(da.currentpage);
					
					});
				
				};	
			
		
	</script>
  </head>
  
  <body>
    <div id="content">
    
    </div>
    <div id="end">
    			<input type="button"  id="current" value="" > 
    			<input type='button' id='first' value='first' />
				<input type='button' id='uppage' value='uppage' />
				<input type='button' id='nextpage' value='nextpage' />
				<input type='button' id='endpage' value='endpage' />
				<input type="button" id="allpage"  value="" > 
    
    </div>
  </body>
</html>
