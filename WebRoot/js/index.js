$(function(){
	$(".navigation").mouseover(function(){
		$(this).css("background-color","black");
		$(this).css("color","white");
	});
	
	$(".navigation").mouseout(function(){
		$(this).css("background-color","white");
		$(this).css("color","#808080");
	;
	});
});