$(function(){
				
//点击添加
$("#submit").click(function(){
		var name=document.getElementById("comment-name").value;
		var review=document.getElementById("comment-body").value;
		var re =new RegExp("[`~!@#$^&*()=|{}':;',\\[\\].《》<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]");
		if(re.test(review)){
			layer.msg("不能含有特殊字符",function(){
				
			});
			
		}else if(name==""||review==""){
			layer.msg("名称或内容不能为空",function(){
				
			});
		}else if(name!=""&&review!=""){
			var reviews=document.getElementById("comments");
			reviews.innerHTML+=" <p>"+name+"&nbsp;"+review+"</p><p>"+getTime()+"</p><hr></div> ";
			document.getElementById("comment-name").value='';
			document.getElementById("comment-body").value='';
			var blog_id=$("#blog_id").val();
			$.post("/Blog/submitReview",{critic:name,content:review,blog_id:blog_id},function(data){
				var json=eval("("+data+")");
				if(json.state=="1"){
					layer.alert('评论成功', {
						  icon: 1,
						  skin: 'layer-ext-moon'
						});
				}else{
					
					layer.alert('评论失败', {
						  icon: 2,
						  skin: 'layer-ext-moon'
						});
				}
				
			});
		}
		
});

});

//统计字数
function countChar(textareaName,spanName){
	var contentLength=140 - document.getElementById(textareaName).value.length;
	if(contentLength>=0){
		
		document.getElementById(spanName).innerHTML =contentLength ;
	}else{
		document.getElementById(spanName).innerHTML =0 ;
	}
	
	 
}

//获取时间
function getTime(){
		        var now=new Date();
		        var y=now.getFullYear();
		        var m=now.getMonth()+1;
		        m=(m<10)?'0'+m:m;
		        var d=now.getDate();
		        var h=now.getHours();
		        h=(h<10)?'0'+h:h;
		        var mi=now.getMinutes();
		        mi=(mi<10)?'0'+mi:mi;
		        return y+'-'+m+'-'+d+" "+h+':'+mi;
    	};
	
//删除添加的评论	
function del(obj){
					obj.parentNode.parentNode.removeChild(obj.parentNode);
				}
		
		