<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-1.8.0.js"></script>
<script type="text/javascript">
$.ajax({
    type: "get",  
    async: false,  
    url: "http://api.m.baidu.com/?type=hot&from=1009669a&dt=jsonp",  
    dataType: "jsonp",
    jsonpCallback:"window.baidu.hot"   //这里为后台返回的动态函数
});
var baidu = {
	hot : function(data){
		var hottag = "";
		$.each(data.hot,function(key,value){
			hottag += "<a href='"+value.url+"'>"+value.word+"</a>";
		});
		$("#hotinfo").html(hottag);
	}	
}
</script>
</head>
<body>
<form action="http://m.baidu.com/s" method="get">
	<input type="text" name="word" maxlength="64" size="26">
	<input type="hidden" name="from" value="1009669a">
	<input type="submit" value="百度一下" />
</form>
<div id="hotinfo"></div>
</body>
</html>