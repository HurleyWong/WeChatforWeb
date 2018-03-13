<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ov.User" %>
<%@ page import="ov.Account" %>
<%@ page import="java.io.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>注册公众号</title>
	<style type="text/css">
		@import url("css/register.css");
	</style>
</head>
<body>
<%
	User user=(User)session.getAttribute("User");
	//User user = (User)request.getAttribute("user");
	System.out.println(" "+ user.toString());
	String headImage_path = "C:\\Users\\jack\\Desktop\\head_image\\";
	String id = user.getId()+"\\";
	headImage_path+=id;	
	headImage_path += "head.png";
	System.out.println("path=="+headImage_path);


%>


<%
	request.setAttribute("pics",1);
%>
<div id="container">
	<div id="head">
		<h1>请填写公众号信息</h1>
	</div>
	<form class="frm1" action="RegisterAccountServlet" onsubmit="">
		<div class="frm_group">
			<input type="text" class="frm_input" placeholder="请输入公众号账号" required="" id="id" name="id">
		</div>
		<div class="frm_group">
			<input type="text" class="frm_input" placeholder="请输入公众号名称" required="" id="account_name" name="account_name" onblur="isHave()">
		</div>
		<div class="frm_group">
			<input type="text" class="frm_input" placeholder="请输入公众号功能介绍" required="" id="introduction" name="introduction">
		</div>
		<button type="submit" class="frm_buttom1">注册</button>
	</form>
</div>
<script type="text/javascript">
<%
	if(request.getParameter("error")!=null){
		if(request.getParameter("error").equals("1")){
%>
			alert("账号不可用");
<%
		}
		if(request.getParameter("error").equals("2")){
%>
			alert("系统异常，请重试！");
<%
		}
	}
%>

</script>
</body>
</html>









