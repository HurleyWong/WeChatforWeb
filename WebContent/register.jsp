<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" csontent="text/html; charset=ISO-8859-1">
<title>register Page</title>
	<style type="text/css">
		@import url("css/register.css");
	</style>
</head>
<body>
<%
	request.setAttribute("pics", 1);
%>
	<div id="container">
		<div id="head">
			<h1>请填写信息</h1>
		</div>
		<form class="frm1"  action="RegisterServlet" onsubmit="return check()">
			<div id="frm_group1">
                <input id="zhzh" type="text"  class="frm_input1" maxlength="9" placeholder="请输入账号" required=""  name="id" onkeyup="showCanUse()" ><label id="ashowcanuse"></label>
            </div>
            <div class="frm_group">
                <input type="text"     class="frm_input" maxlength="9" placeholder="请输入用户名" required="" id="username" name="username" onblur="isHave()">
            </div>
            <div class="frm_group">
                <input type="text" class="frm_input" placeholder="请输入手机号" maxlength="11" required="" id="phonenumber" name ="phonenumber">
            </div>
            <div class="frm_group">
                <input type="password" class="frm_input" placeholder="请输入密码"   maxlength="12" required="" id="password1" name="password">
            </div>
            <div class="frm_group">
                <input type="password" class="frm_input" placeholder="请再次输入密码" maxlength="12" required="" id="password2" name ="password">
            </div>
            <div class="frm_group_left">
                <div class="frm_group_checkbox">
                    <label>
                        <input type="checkbox" id="checkbox_a" value="100"><i></i> 我同意注册协议
                    </label>
                </div>
            </div>
            <button type="submit" class="frm_buttom1" >注 册</button>

            <p class=""><small>已经有账户了？</small><a href="login.jsp">点此登录</a>
            </p>
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
			alert("系统异常，请重试!");
<%
		}
	}
%>

		function conUse(){
			if(document.getElementById("ashowcanuse").innerHTML == "不可用"){
				alert("账号不可用");
				document.getElementById("zhzh").focus();
			}
		}
		
		function check(){
			if(document.getElementById("ashowcanuse").innerHTML == "不可用"){
				alert("账号不可用");
				return false;
			}
			var p1=document.getElementById("password1").value;
			var p2=document.getElementById("password2").value;
			var b =document.getElementById("checkbox_a").check
			if (p1 != p2) {
				alert("两次输入的密码不一致");
				return false;
			}
			
			if(!document.getElementById("checkbox_a").checked){
				alert("请同意注册协议");
				return false;
			}
		}
		function showCanUse()
		{
			var xmlhttp;
			var content = document.getElementById("zhzh");
			var str = content.value;
			if(content.value==""){
				setNone()
				return ;
			}
			if (window.XMLHttpRequest)
			  {// code for IE7+, Firefox, Chrome, Opera, Safari
			  xmlhttp=new XMLHttpRequest();
			  }
			else
			  {// code for IE6, IE5
			  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
			  }
			xmlhttp.onreadystatechange=function()
			  {
			  if (xmlhttp.readyState==4 && xmlhttp.status==200)
			    {
			    	var result=xmlhttp.responseText;
			    	console.log(result);
			    	if(result=='1'){
			    		setCuse();
			    	}else{
			    		setNuse();
			    	}
			    }
			  }
			var url ="Register2Servlet?str="+str;
			console.log("====url=="+url);
			xmlhttp.open("GET",url,true);
			xmlhttp.send();
		}
		function setCuse(){
			document.getElementById("ashowcanuse").innerHTML ="可用";
			document.getElementById("ashowcanuse").style="color:green";
		}
		function setNuse(){
			document.getElementById("ashowcanuse").innerHTML ="不可用";
			document.getElementById("ashowcanuse").style="color:red";
		}
		function setNone(){
			document.getElementById("ashowcanuse").innerHTML ="";
		}
	</script>
</body>
</html>