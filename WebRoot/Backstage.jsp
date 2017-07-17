<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>后台管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="./hui/static/h-ui/css/H-ui.min.css" />
	<link rel="stylesheet" type="text/css" href="./hui/static/h-ui.admin/css/H-ui.admin.css" />
	<link rel="stylesheet" type="text/css" href="./hui/lib/Hui-iconfont/1.0.8/iconfont.css" />
	<link rel="stylesheet" type="text/css" href="./hui/static/h-ui.admin/skin/default/skin.css" id="skin" />
	<link rel="stylesheet" type="text/css" href="./hui/static/h-ui.admin/css/style.css" />
	<link rel="stylesheet" type="text/css" href="./bootstrap/css/bootstrap.css" />

<title></title>
<style type="text/css">

#publish:hover{
	color: blue;
	cursor: pointer;
}
#edit:hover{
	color: blue;
	cursor: pointer;
}
#delete:hover{
	color: blue;
	cursor: pointer;
}
#top:hover{
	color: blue;
	cursor: pointer;
}
.style{
	width:50px;
	height:25px;
	border-radius:5px;
	
}
</style>
</head>
<body>



<nav class="breadcrumb">

	<c:if test="${sessionScope.auth==null }">
		<i class="Hui-iconfont">&#xe67f;</i> 后台 <span class="c-gray en">&gt;</span> 管理员登录 <a class="btn btn-success radius r" id="login"  style="line-height:1.6em;margin-top:0px"  title="登录" ><i class="Hui-iconfont">登录</i></a>
		
	</c:if>
<c:if test="${sessionScope.auth!=null }">
	<i class="Hui-iconfont">&#xe67f;</i> 后台 <span class="c-gray en">&gt;</span> 文章管理 
	<a class="btn btn-success radius r" id="loginOut" style="line-height:1.6em;margin-top:0px"  title="注销" ><i class="Hui-iconfont">&#xe6a6;</i>注销</a>
	<a class="btn btn-success radius r"  style="line-height:1.6em;margin-top:0px;margin-right: 15px;"  title="新博客" href="${pageConetxt.request.contextPath }/Blog/PublishBlog" ><i class="Hui-iconfont">&#xe600;</i>新博客</a>
</c:if>

</nav>

<c:if test="${sessionScope.auth==null }">
	<div class="container">
		<div style="margin-left: 42%;margin-top: 20%">
			<span class="glyphicon glyphicon-remove-circle" aria-hidden="true" style="height: 50px;"></span><font size="5px">未登录...</font>
		</div>
	</div>
</c:if>
<c:if test="${sessionScope.auth!=null }">
	<c:if test="${ empty page.list }">
		<p>未找到数据...</p>
	</c:if>
	<c:if test="${!empty page.list }">
		<div class="page-container" >
			<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> <a class="btn btn-primary radius" data-title="新博客" href="${pageContext.request.contextPath }/PublishBlog"><i class="Hui-iconfont">&#xe600;</i> 新博客</a></span> <span class="r">共有数据：<strong>${page.currentrow }</strong> 条</span> </div>
				<div class="mt-20">
					<table class="table table-border table-bordered table-bg table-hover table-sort">
						<thead>
							<tr class="text-c">
								<th width="25"><input type="checkbox" name="" value=""></th>
								<th width="80">ID</th>
								<th>标题</th>
								<th width="80">分类</th>
								<th width="80">作者</th>
								<th width="120">更新时间</th>
								<th width="75">浏览次数</th>
								<th width="60">发布状态</th>
								<th width="120">操作</th>
							</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.list }" var="page" varStatus="pageindex">
							<tr class="text-c">
								<td><input type="checkbox" value="${page.id }" name="blog_ids"></td>
								<td>${pageindex.index+1 }</td>
								<c:if test="${page.state==0 }">
									<td class="text-l"><a style="cursor:pointer;pointer-events:none"  class="text-primary" href="${pageContext.request.contextPath }/findBlog/${page.id}" title="查看">${page.title }</a></td>
								</c:if>
								<c:if test="${page.state==1 }">
									<td class="text-l"><a style="cursor:pointer" class="text-primary" href="${pageContext.request.contextPath }/findBlog/${page.id}" title="查看">${page.title }</a></td>
								</c:if>
								<td>${page.cate }</td>
								<td>${page.auth.nickname }</td>
								<td><fmt:formatDate value="${page.publishtime }" pattern="yyyy-MM-dd hh:mm:ss"/></td>
								<td>${page.readnum }</td>
								<c:if test="${page.state==1 }">
									<td class="td-status"><span class="label label-success radius">已发布</span></td>
									<td>
										<i class="Hui-iconfont " ><a  href="${pageContext.request.contextPath }/edit/${page.id}"  id="edit" >&#xe6df;</a></i>
										<i class="Hui-iconfont " ><a   onclick="del('${page.id}')" id="delete" >&#xe6e2;</a></i>
										<i class="Hui-iconfont " ><a  onclick="setTop('${page.id}')" id="top" >&#xe6c1;</a></i>
									</td>
								</c:if>
								<c:if test="${page.state==0 }">
									<td class="td-status"><span class="label label-success radius">未发布</span></td>
									<td>
										<i class="Hui-iconfont "  ><a onclick="publishBlog('${page.id}')" id="publish" >&#xe603;</a></i>
										<i class="Hui-iconfont " ><a  href="${pageContext.request.contextPath }/edit/${page.id}" id="edit" >&#xe6df;</a></i>
										<i class="Hui-iconfont "><a   onclick="del('${page.id}')" id="delete" >&#xe6e2;</a></i>
									</td>
								</c:if>
							</tr>
						</c:forEach>
					</tbody>
				</table>
		</div>
		<div>
			<div>共${page.currentrow}条数据,${page.currentpage }页,${page.thispage+1 }/${page.currentpage }</div>
				<div style="margin-left: 70%">
					<button style="background-color: white;border: 1px solid gray" id="homepage" class="style">首页</button>
					<button style="background-color: white;border: 1px solid gray" id="uppage" value="${page.uppage }" class="style">上一页</button>
					<button style="background-color: white;border: 1px solid gray" id="nextpage" value="${page.nextpage }" class="style">下一页</button>
					<button style="background-color: white;border: 1px solid gray" id="lastpage" value="${page.lastpage }" class="style">尾页</button>
				    <input type="text" id="skip" style="width: 20px;height: 25px;"/>
					<button id="Goto" style="width:25px;height:25px;background-color: white;" onclick="show('${page.currentpage}')" >GO</button>
				</div>
			</div>	
		</div>
	</c:if>
</c:if>	
	<footer>
		<div style="margin-top: 38%;position:static;">
	        <c:import url="Footer.jsp" ></c:import>
	    </div>
	    
	 </footer>

<script type="text/javascript" src="./bootstrap/jqurey/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="./layer/layer.js"></script>
<script type="text/javascript" src="./hui/static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="./hui/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="./hui/lib/My97DatePicker/4.8/WdatePicker.js"></script> 
<script type="text/javascript" src="./hui/lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="./hui/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript" src="./js/backstage.js"></script>
</body>
</html>
