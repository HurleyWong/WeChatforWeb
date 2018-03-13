<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="ov.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
    <style>
     @import url("css/creatgroup.css");
</style>
</head>
<%
	System.out.println(request.getParameter("user"));
	ArrayList<Myfriend> list = (ArrayList<Myfriend>)request.getAttribute("myfriends");
	
%>
<body>
    <div id="container">
        <div class="head_list">
            <h1 class="title">请填写群聊信息</h1>
        </div>
        <form class="frm1"  action="CreatGroupServlet" onsubmit="return check()">
        
       		 <div  id="frm_group1" class="frm_group">
                <input type="text"  id="zhzh" onkeyup="showCanUse()"  class="frm_input_id" placeholder="请输入群聊账号" maxlength="9" required=""  name="id" ><label id="ashowcanuse"></label>
            </div>
            
            <div class="frm_group">
                <input type="text"     class="frm_input_na" placeholder="请输入群聊名称"  maxlength="12" required="" id="name" name="name" >
            </div>
            
           <div class ="List">
	           <div class = "contact_title">
	          		 &nbsp;<input type="checkbox" onclick="quanxuan(this)" />全选
	                 <lable class="hylb">好友列表 </lable>
	            </div>
	            <div class="list_blow">
	            
	            <%
	            	int i =0;
	            	for(i = 0;i<list.size();i++){
	            		
	            %>
		       		<div class="list_min_chat">
		            <input type="checkbox" class="checkbox1" name="jszzdm" value="<%=list.get(i).getId()%>">
					<div class="list_min_chat_avatar">
						
							<img src="<%=list.get(i).getHeadImage()%>">
						</div>
						<div class="list_min_chat_info">
						<h3 class="name">
						<span class="name_text"><%=list.get(i).getNickName()%></span>
						</h3>				
						</div>
					</div>
	            
	            <%
	            	}
	            %>
	        
            	</div>
            <input type="text" name="user_id" value="userid" hidden="hidden">
            <button  type="submit" class="frm_buttom1" style="margin-top:50px" >确定</button>


            </p>
        </form>
    </div>
    	<script type="text/javascript">
    	
    	function quanxuan(a)
    	{
    	    //找到下面所有的复选框
    	    var ck =document.getElementsByClassName("checkbox1");
    	    if(a.checked){
    	    	 for(var i=0;i<ck.length;i++)
    	    	 {
    	    		 ck[i].checked= true;
    	    	 }
    	    }else{
    	    	 for(var i=0;i<ck.length;i++)
    	    	 {
    	    		 ck[i].checked= false;
    	    	 }
    	    }
    	}
    	
		function conUse(){
			if(document.getElementById("ashowcanuse").innerHTML == "不可用"){
				alert("账号不可用");
				document.getElementById("zhzh").focus();
			}
		}
		function showCanUse()
		{
			var xmlhttp;
			var content = document.getElementById("zhzh");
			var str = content.value;
			console.log("====keyword=="+str);
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
			var url ="Register2Servlet?str2="+str;
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
		
		function check(){
			if(document.getElementById("ashowcanuse").innerHTML == "不可用"){
				alert("账号不可用");
				return false;
			}
		}
    	
<%
	if(request.getParameter("error")!=null){
		if(request.getParameter("error").equals("1")){
%>
			alert("群账号不可用");
<%
		}
		if(request.getParameter("error").equals("2")){
%>
			alert("系统异常，请重试!");
<%
		}
	}
%>

	</script>
</body>
</html>