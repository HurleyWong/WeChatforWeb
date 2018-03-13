<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="ov.*"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>contact Paged</title>
<%
	request.setCharacterEncoding("utf-8");
%>
<style>
	@import url("css/contact.css");
</style>
</head>
<body  style="overflow:-Scroll;overflow-y:hidden">
<%
	User user = (User)request.getAttribute("user");
	
	ArrayList<Myfriend> list =(ArrayList<Myfriend>)request.getAttribute("myfriends");
	
	ArrayList<MyGroup> list2 =(ArrayList<MyGroup>)request.getAttribute("mygroups");
	
	session.setAttribute("userid", user.getId());
	
	System.out.println("MyFriends:");
	for(Myfriend o:list){
		System.out.println(o.toString());
	}
	System.out.println("MyGroup:");
	for(MyGroup o2:list2){
		System.out.println(o2.toString());
	}
%>
	<div id="container">
		<div id="mainContent">
		<div id = "list">
			<div id = "list_top" >
				<div id="list_hd">
					<div id="actor">
						<img src="<%=user.getHeadImage()%>">
					</div>
					<div id="info_icon">
						<div id="wechat_add" >
				         <div id="my_menu">
				          <ul>
					            <li><a href="#" target="mainFrame">修改资料</a></li>
					            <li><a href="friend.jsp?user" target="_blank">朋友圈</a></li>
					            <li><a href="addporg.jsp" >添加好友/群</a></li>
					            <li><a href="ContactServlet?type=create&id=<%=user.getId()%>">创建群聊</a></li>
					            <li><a href="#" target="mainFrame">意见反馈</a></li>
					            <li><a href="friend.jsp?user" target="_blank">退出</a></li>
					        </ul>
					        </div>	
				        </div>		
					</div>
					<div id=info>
						<p><%=user.getNickName() %></p>
					</div>
				</div>
				<div id="list_search_bar">
					<a id="wechat_search" onclick="toshowperson()"></a>
					<input id="search_input" type="text" placeholder="搜索" onkeyup="showHint(this.value)" onblur="onblur1()" onfocus="showHint(this.value)" onkeypress="if(event.keyCode==13) {toshowperson();return false;}">
					<table id="mytable">

					</table>
				</div>
				<div id="list_tab">
					<div class="list_tab_chat" id="list_tab_chat">
						<a href="WeChatServlet?id=<%=user.getId() %>" title="聊天">
							<i id="wechat_chat">
							</i>
						</a>	
					</div>
					<div id="list_tab_public" class="list_tab_public">
						<a href="#" title="阅读">
							<i id="wechat_public">
							</i>
						</a>
					</div>
					<div id="list_tab_friends" class="list_tab_friends" >
						<a href="ContactServlet?id=<%=user.getId() %>" title="通讯录">
							<i id="wechat_friends">
							</i>
						</a>
					</div>
				</div>
				</div>
		    <div id = "list_blow"  border:0px solid;">
               
						<div class = "contact_title">
                        <h4 >群组</h4>
            </div>	
				
				
				<%
					int i = 0;
				
					for(i=0;i<list2.size();i++){
						System.out.println("id------user="+user.getId()+"--c_id="+list2.get(i).getC_id());
				%>
					<div id="<%=list2.get(i).getId()%>" class="list_min_chat" ondblclick="goChat1(<%=list2.get(i).getId()%>)" onclick="show1('<%=list2.get(i).getId()%>','<%out.print(list2.get(i).getName());%>','<%if(user.getId().equals(list2.get(i).getC_id())){out.print("1");}else{out.print("0");}%>')">     
						<div class="list_min_chat_avatar">
							<img src="images/headImage.png"></a>
						</div>
						<div class="list_min_chat_ext">
							<p><%=list2.get(i).getN_m_date()%></p>
							<a class="wechat_no-remind"></a>
						</div>
						<div class="list_min_chat_info">
							<h3>
								<span>
									<%=list2.get(i).getName()%>
								</span>
							</h3>
							<p>
								<span>
									<%=list2.get(i).getN_message()%>
								</span>
							</p>
						</div>
					</div>
				<%
					}
				%>
				<div class = "contact_title">
                        <h4 >好友</h4>
            	</div>
           		<%
					for(i=0;i<list.size();i++){
				%>
					<div  id="<%=list.get(i).getId()%>" class="list_min_chat" ondblclick="goChat2(<%=list.get(i).getId()%>)" onclick="show2('<%=list.get(i).getHeadImage() %>','<%=list.get(i).getId() %>','<%=list.get(i).getNickName()%>')">
						<div class="list_min_chat_avatar" >
							<img src="<%=list.get(i).getHeadImage()%>" ></a>
						</div>
						<div class="list_min_chat_ext">
							<p><%=list.get(i).getN_m_date()%></p>
							<a class="wechat_no-remind"></a>
						</div>
						<div class="list_min_chat_info">
							<h3>
								<span>
									<%=list.get(i).getNickName()%>
								</span>
							</h3>
							<p>
								<span>
									<%=list.get(i).getN_message()%>
								</span>
							</p>
						</div>
					</div>
				<%
					}
				%>
				
        </div>
            
            
			</div>
			<div id="chatArea">
				<div id="chatArea_hd">
					<div id="chatArea_title">
						<p>详细信息</p>
					</div>
				</div>
				<div id="chatArea_main">
				<div id="main_image">
					<img id="show_pic" src="images/headImage.png">
				</div>
				<div class="charArea_message">
				<p id="t_name" style="color:#000000"></p>
				</div>
				<div class="charArea_blow_button">
				<div id="bbb1" style ="display:none"><input type="button" id="btn1" class="frm_buttom1" value="发送信息"  onclick="btclick1()"></div>
				<div id="bbb2" style ="display:none"><input type="button" id="btn2" class="frm_buttom2" value="解散群聊"  onclick="btclick2()"> </div>
				</div>
				</div>

				<!-- <div id="chatArea_tool">	聊天工具栏						
					<a class="wechat_face" ng-click="showEmojiPanel($event)" href="javascript:;" title="表情"></a>
					<a class="wechat_screencut" mm-action-track="" track-type="['click']" ng-click="screenShot()" href="javascript:;" title="截屏"></a>
					<a id="wechat_file" mm-action-track="" track-type="['click']" ng-click="sendClick($event)"  href="javascript:;" title="文件和图片"></a>
				</div>
								                 
				<div id="chatArea_input_blank" contenteditable="true"></div>
				<div id="chatArea_action">
					<span>按下Ctrl+Enter换行</span>
					<a class="btn btn_send" href="javascript:;" ng-click="sendTextMessage()">发送</a>
				</div> -->
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
	function goChat1(id){
		window.location.href="ChatServlet?g_id="+id;
	}
	function goChat2(id){
		window.location.href="ChatServlet?p_id="+id;
	}
	function show1(g_id,name,a){
		var str1;
		console.log(a);
		if(a=='1'){
	/* 		document.getElementById("btn2").value='解散群聊'; */
			str1='解散群聊';
		}else{
/* 			document.getElementById("btn2").value='退出群聊'; */
			str1='退出群聊';
		}
		
		
		document.getElementById('bbb1').innerHTML = "<input type='button' class='frm_buttom1' value='发送消息' onclick='btclick1("+g_id+")'>";
		document.getElementById('bbb2').innerHTML = "<input type='button' class='frm_buttom2' value='"+str1+"' onclick='btclick2("+g_id+")'>";
		document.getElementById('show_pic').src = "images/headImage.png";
		
		document.getElementById("bbb1").style.display='block';
		document.getElementById("bbb2").style.display='block';
		document.getElementById("t_name").innerHTML = name;

	}
	function show2(srcpath,id,name){
		console.log(id);
		console.log(name);
		
		document.getElementById('bbb1').innerHTML = "<input type='button' class='frm_buttom1' value='发送消息' onclick='btclick3("+id+")'>";
		document.getElementById('bbb2').innerHTML = "<input type='button' class='frm_buttom2' value='删除好友' onclick='btclick4("+id+")'>";
	
		document.getElementById('show_pic').src = srcpath;
		document.getElementById("bbb1").style.display='block';
		document.getElementById("bbb2").style.display='block';
		document.getElementById("t_name").innerHTML = name;
		
	}
	function btclick1(g_id){
		console.log("点击btn1");
		window.location.href="ChatServlet?g_id="+g_id;
	}
	
	function btclick2(id){
		console.log("点击btn2");
		document.getElementById(id).style.display="none";
		document.getElementById('show_pic').src = "images/headImage.png";
		document.getElementById("bbb1").style.display='none';
		document.getElementById("bbb2").style.display='none';
		document.getElementById("t_name").innerHTML='';
		var xmlhttp;
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
			    }
		  }
		var url ="DeleteG_PServlet?g_id="+id;
		console.log("====url=="+url);
		xmlhttp.open("GET",url,true);
		xmlhttp.send(); 
		
	}
	function btclick3(g_id){
		console.log("点击btn3");
		window.location.href="ChatServlet?p_id="+g_id; 
	}
	function btclick4(id){
		console.log("点击btn4");
/* 		window.location.href="DeleteG_PServlet?p_id="+id; */
		document.getElementById(id).style.display="none";
		document.getElementById('show_pic').src = "images/headImage.png";
		document.getElementById("bbb1").style.display='none';
		document.getElementById("bbb2").style.display='none';
		document.getElementById("t_name").innerHTML='';
		var xmlhttp;
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
		    }
		  }
		var url ="DeleteG_PServlet?p_id="+id;
		console.log("====url=="+url);
		xmlhttp.open("GET",url,true);
		xmlhttp.send(); 
	}
	
/* 	function openWin(){
		window.open ("addporg.jsp", "newwindow", "height=500, width=800, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");
	} */
	
	
	</script>
	
	<script type="text/javascript" src="js/contact.js"></script>
	
</body>
</html>