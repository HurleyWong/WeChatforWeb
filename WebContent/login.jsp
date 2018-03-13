<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page import="java.net.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Login Page</title>
	<style type="text/css">
		@import url("css/register.css");
	</style>
</head>
<body>
	<div id="container">
		<div id="head">
			<h1>网页版微信登录界面</h1>
		</div>
<%
   String str1 ="";
   String str2="";
   Cookie cookie = null;
   Cookie[] cookies = null;
   // 获取cookies的数据,是一个数组
   cookies = request.getCookies();
   if( cookies != null ){
      for (int i = 0; i < cookies.length; i++){
         cookie = cookies[i];
         if(cookie.getName().equals("myusername")){
        	 str1=cookie.getValue();
         }
 		 if(cookie.getName().equals("mypassword")){
 			 str2=cookie.getValue();
         }
      }
  }else{
     System.out.println("没有找到Cookie");
  }
%>
		<form class="frm1"  method="post" action="LoginServlet" onsubmit="return check()">
		
            <div class="frm_group">
                <input type="text"     class="frm_input" placeholder="请输入账号或用户名" required="" id="username" name="username" value="<%=str1%>">
            </div>
            <div class="frm_group">
                <input type="password" class="frm_input" placeholder="请输入密码"   required="" id="password1" name="password" value="<%=str2%>">
            </div>
			<div class="frm_group_left">
                <div class="frm_group_checkbox">
                    <label>
                        <input type="checkbox" id="checkbox_a" name="jzmm" value="1"><i></i>三天免登录
                    </label>
                </div>
            </div>
            <button type="submit" class="frm_buttom1" >登录</button>

            <p class=""><small>没有账户？</small><a href="register.jsp">去注册</a>
            </p>
        </form>
	</div>
	<script type="text/javascript">
	<%
	if(request.getParameter("error")!=null){
		if(request.getParameter("error").equals("1")){
%>
			alert("请检查账号或用户名是否输入正确！");
<%
		}
		if(request.getParameter("error").equals("2")){
%>
			alert("系统异常，请重试!");
<%
		}
	}
%>
		function check(){
			return true;
		}
	</script>
</body>
</html>