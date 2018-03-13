<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="util.StringUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style type="text/css">
	#container{
		width:300px;
		min-height:300px;
		margin:100px auto;
	}
	body{
		font-size:14px;
		background: url(images/wx_background.jpg) no-repeat;
		background-size:cover;
	}
</style>
</head>
<body>
	<%
 		String str1 = (String)request.getAttribute("str1");
		String url1 = (String)request.getAttribute("url1");
		String str2 = (String)request.getAttribute("str2");
		String url2 = (String)request.getAttribute("url2"); 
	%>
	<div id="container">
		<div>
	        <img width= "30" src="images/success.jpg">
	        <span class = "atitle"><%=str1%></span>
	        
		     <br/>
		 </div>
	     <p><span id="count">1</span>秒后跳向<%=str2 %></p>
	     <a href="<%=url1%>">返回</a>
	</div>
	<script type="text/javascript">
    function back(){
        window.history.back();
    }
    var count=document.getElementById("count").innerHTML;
    function count1(){
        count--;
        document.getElementById("count").innerHTML=count;
        if(count==0){
            window.clearInterval(timer);
            location.assign("<%=url2%>");
        }
    }
    var timer=setInterval("count1()",1000);
</script>
</body>
</html>