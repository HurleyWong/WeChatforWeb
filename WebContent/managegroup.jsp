<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page import="ov.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Manage Group</title>
    <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
    @import url("css/managegroup.css");
   
</style>
<style type="text/css">
	@import url("css/managegroup1.css");
	  @import url("css/bootstrap.min.css");
</style>
</head>
<%

	User user = (User)request.getAttribute("user");
	GroupInfo g_info = (GroupInfo)request.getAttribute("group_info");
	ArrayList<Myfriend> list = (ArrayList<Myfriend>)request.getAttribute("myfriends");
	
%>
<body>
 <div id="container">
 	<div id="d_top">
 	    <ul class="nav nav-tabs">
			<li id="cygl" class="active"  onclick="change1()"><a href="#">成员管理</a></li>
			<li id="wjgl" class="" onclick="change2()"><a href="#">文件管理</a></li>
		</ul>
 	</div>
 	<div class="d_blow">
	<div id="people" >
	<input id="group_c_id" name ="group_c_id" type="text" value="<%=g_info.getGroup().getC_id() %>" hidden="hidden">
	
	<div class="blow_button1">

	<input type="checkbox" class="blow_button_people" onclick="quanxuan(this)" />
	<span class="quanxuanTEXT">全选</span>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
		<form class="frm1"  action="AddG_PServlet">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
				</button>
				<input type="text" name ="add_group_id" hidden="hidden" value="<%=g_info.getGroup().getId()%>">
				<h4 class="modal-title" id="myModalLabel">
					添加好友/群
				</h4>
			</div>
			<div class="modal-body">

				<%
	            	int i =0;
	            	for(i = 0;i<list.size();i++){
	            		
	            %>
		       		<div class="list_min_chat">
		            <input type="checkbox" class="delete" name="jszzdm_add" value="<%=list.get(i).getId()%>">
					<div class="list_min_chat_avatar3">
						
							<img src="<%=list.get(i).getHeadImage()%>"></a>
						</div>
						<div class="list_min_chat_info">
						<h3 style="color:#000000">
						<span style="color:#000000" ><%=list.get(i).getNickName()%></span>
						</h3>				
						</div>
					</div>
	            
	            <%
	            	}
	            %>
	            
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">
					关闭
				</button>
				<button type="submit" class="btn btn-primary" onclick="addperson()">
					提交更改
				</button>
			</div>
			</form>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->
	</div>
			 <form class="frm1"  action="DeleteG_PServlet" onsubmit="return check()">
			 <input id="user_id" name ="user_id" type="text" value="<%=user.getId() %>" hidden="hidden">
	<input id="group_id" name ="group_id" type ="text" value="<%=g_info.getGroup().getId() %>" hidden="hidden">
		<input type="submit" class="blow_button_delect" value="确认删除">
	<input type="button" class="blow_button_add" value="邀请好友" data-toggle="modal" data-target="#myModal">
	<div id="blow_persons">
		 
			<%
				for(i=0;i<g_info.getList().size();i++){
			%>
				<div class="list_min_chat">
					<input type="checkbox" class="delect" name="jszzdm" value="<%=g_info.getList().get(i).getId()%>">
					<div class="list_min_chat_avatar">
					
						<img src="<%=g_info.getList().get(i).getHeadImage()%>">
					</div>
					<div class="list_min_chat_info">
					<h3 class="name">
					<span class="name_text"><%=g_info.getList().get(i).getNickName()%></span>
					</h3>
					</div>
				</div>	
				
			<%
				}
			%>
				</div>
				</form>
				</div>	
	<div id="file">
	<form action="DoFileUploadServlet?g_id=<%=g_info.getGroup().getId() %>" method="post" enctype="multipart/form-data">
	<div class="blow_button2">
	<input type="file" class="blow_button_send" name="chooseFile" value="文件浏览"/>
	<input type="submit" class="blow_button_download" value="文件上传">
	</div>
	<div class="file_body">
				<table class="FileTable">
  <tr>
    <th></th>
    <th>文件名    </th>
    <th>上传者    </th>
    <th>上传时间    </th>
  </tr>
 
 <%
 	ArrayList<Mfile> filelist = (ArrayList<Mfile>)g_info.getList2();
 	for( i =0;i<filelist.size();i++){
 %>
   <tr>
	<td style="width:30px;height:30px;"><!-- <img src="images/File_image.png"> --></td>
	<td><a href="DownLoadServlet?fileName=<%=filelist.get(i).getName()%>&g_id=<%=g_info.getGroup().getId() %>"><%=filelist.get(i).getName() %>	</a></td>
	<td><%=filelist.get(i).getC_name()%></td>
	<td><%=filelist.get(i).getC_date() %></td>
	</tr>
 <%
 	}
 %>

  </table>
					<!-- <div class="file_image">
					
						<img src="images/File_image.png">
					</div>
					<div class="file_name">
					<h3 class="file_name_h3">
					<span class="file_name_text">猫和老鼠</span>
					</h3>
					</div> -->
				</div>	
	</form>
	</div>
	</div>
	</div>
     <script type="text/javascript">	
     	function check(){
     		var  flag =0;
     		var ck =document.getElementsByClassName("delect");
     		 for(var i=0;i<ck.length;i++)
     	    {
     	        if(ck[i].checked)//判断全选按钮的状态是不是选中的
     	        {
     	        	if(ck[i].value==document.getElementById("user_id").value){
     	        		alert("您不能删除自己");
     	        		return false;
     	        	}
     	           flag = 1;
     	        }
     	    }
     		if(flag == 0){
     			alert("您还未选中好友!");
     			return false;
     		} 
     		var c_id = document.getElementById("group_c_id").value;
     		var user_id = document.getElementById("user_id").value;
     		if(c_id != user_id){
     			alert("您没有此权限");
     			return false;
     		}else{
     			if(confirm("您确定要删除好友吗？")){
     				console.log("确定删除好友");
     				return true;
     			}else{
     				return false;
     			}
     		}
     	}

		function change1() {
			document.getElementById('wjgl').className="";
			document.getElementById('cygl').className="active";
			document.getElementById('people').style.display="block";
			document.getElementById('file').style.display="none";
			
		}
		function change2() {
			document.getElementById('cygl').className="";
			document.getElementById('wjgl').className="active";
			document.getElementById('people').style.display="none";
			document.getElementById('file').style.display="block";
		}
		function quanxuan(a)
    	{
    	    
    	    var ck =document.getElementsByClassName("delect");
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
     	<%
    	if(request.getParameter("flag")!=null){
    		if("1".equals(request.getParameter("flag"))){
    			out.println("change2();");
    		}
    	}else{
    		out.println("change1();");
    	}
     	%>
		 $(function () { $('#myModal').modal('hide')});
	</script>
</body>
</html>