window.onload=function(){
		
    var editor = new wangEditor('textarea1');
    // 上传图片（举例）
    editor.config.uploadImgUrl = "${pageContext.request.contextPath}/WEB-INF/blog/";
    // 配置自定义参数（举例）
    editor.config.uploadParams = {
        token: 'abcdefg',
        user: 'wangfupeng1988'
    };
    editor.config.uploadImgFileName = 'myFileName';
    // 设置 headers（举例）
    editor.config.uploadHeaders = {
        'Accept' : 'text/x-json',
        'Content-Type':'multipart/form-data'
	};
	editor.create();
	

	
	};
	
	function Blogsubmit(){
		var title=$("#title").val();
		var tags=$("#tags").val();
		var tag=$("#tag").val();
		if(title==""||tags==""||tag==""){
			
			layer.msg('标题标签或分类不能为空',function(){});
			return false;
		}else{
			return true;
		}
		
	}
	