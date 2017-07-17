$(function(){

	$("#login").click(function(){
		layer.open({
			type:2,
			title:'用户登录',
			content:'Login.jsp'
		});
	
	});
	
	$("#loginOut").click(function(){
	
		layer.confirm('注销登陆？', {
		
		btn: ['确定','取消'], 
		btn1:function(index){
			$.post("/Blog/LoginOut",function(data){
				if(data==1){
					layer.closeAll();
					window.location.href="/Blog/Backstage";
				}
			
			});
		},
		
		closeBtn: 1
		
		});
	});
	
	$(".style").mouseover(function(){
		$(this).css("background-color","#5A98DE");
		$(this).css("color","white");
	});
	$(".style").mouseout(function(){
		$(this).css("background-color","white");
		$(this).css("color","black");
	});
	
	$("#homepage").click(function(){
		$.post("/Blog/Backstage");
	
	});
	
	$("#uppage").click(function(){
		
		var uppage=$(this).val();
		$.post("/Blog/Backstage/"+uppage);
	
	});
	
	$("#nextpage").click(function(){
		
		var nextpage=$(this).val();
		$.post("/Blog/Backstage/"+nextpage);
	
	});
	
	$("#lastpage").click(function(){
		
		var lastpage=$(this).val();
		$.post("/Blog/Backstage/"+lastpage);
	
	});
	
});

function setTop(blog_id){
		layer.confirm('推荐阅读？', {
		
		btn: ['确定','取消'], 
		btn1:function(index){
			$.post("/Blog/recommend/"+blog_id,function(data){
				if(data==1){
					layer.alert('设置成功',{
						icon:1,
						time:2000,
						skin:'layer-ext-moon'
					
					},function(){
						window.location.href="/Blog/Backstage";
					});
					
				}else if(data==0){
					layer.alert('设置失败',{
						icon:2,
						time:2000,
						skin:'layer-ext-moon'
					
					},function(){
						window.location.href="/Blog/Backstage";
						});
				
				}
			
			});
		},
		
		closeBtn: 1
		
		});
	
	}
	
	function publishBlog(blog_id){
	
		layer.confirm('确认发布？', {
		
		btn: ['确定','取消'], 
		btn1:function(index){
			$.post("/Blog/Blogpublish/"+blog_id,function(data){
				if(data==1){
					layer.alert('已发布',{
						icon:1,
						time:2000,
						skin:'layer-ext-moon'
					
					},function(){
						
						window.location.href="/Blog/Backstage";
					});
				}else if(data==0){
					layer.alert('发布失败',{
						icon:2,
						time:3000,
						skin:'layer-ext-monn'
					
					});
					
				}
			
			});
		},
		
		closeBtn: 1
		
		});
	
	
	}
	
	function del(blog_id){
	
		layer.confirm('确定删除？', {
		
		btn: ['确定','取消'], 
		btn1:function(index){
			$.post("/Blog/delteblog/"+blog_id,function(data){
				if(data==1){
					layer.alert('已删除',{
						icon:1,
						time:3000,
						skin:'layer-ext-moon'
					});
					window.location.href="/Blog/Backstage";
				}
			
			});
		},
		
		closeBtn: 1
		
		});
		
	}
	
	function show(allpage){
			var thispage=$("#skip").val();
			if(thispage>allpage){
				layer.msg('无效页码',function(){});
			
			}else if(thispage<=0){
			
				layer.msg('无效页码',function(){});
			}else{
			
				$.post("/Blog/Backstage/"+thispage);
			
			}
		
		}
	
		function datadel(){
			var obj = document.getElementsByName("blog_ids");
				var blogs=[];
				for(i in obj){
					if(obj[i].checked){
						blogs.push(obj[i].value);
					
					}	
				}
				if(blogs.length==0){
					layer.msg('未选择',function(){});
				}
				if(blogs.length!=0){
					$.ajax({
							url: "datadel",
							dataType: "html",  
			   				contentType: "application/json",  
			    			type: "post",
			   				data:JSON.stringify(blogs)
						
						});
					}
		}