<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="ov.User"%>
<%@ page import="java.io.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<%
	request.setCharacterEncoding("utf-8");
%>
<style>
	@import url("css/wechat.css");
</style>
</head>
<body style="overflow:-Scroll;overflow-y:hidden">
<%
	User user = (User)request.getAttribute("user");
	System.out.println(" "+ user.toString());
	
	session.setAttribute("user_id", user.getId());
    session.setAttribute("my_image", user.getHeadImage());
    session.setAttribute("user_nickname", user.getNickName());
%>
	<div id="container">
		<div id="mainContent">
			<div id="list">
				<div id="list_hd">
					<div id="actor">
						<img src="<%if(user.getHeadImage()!=null){out.print(user.getHeadImage());}else{out.print("images/headImage.png");}%>">
					</div>
					<div id="info_icon">
						<div id="wechat_add" >
				         <div id="my_menu">
				          <ul>
					            <li><a href="#" target="mainFrame">修改资料</a></li>
					            <li><a href="moment_list" >朋友圈</a></li>
					            <li><a href="#" target="mainFrame">添加好友/群</a></li>
					            <li><a href="friend.jsp?user" target="_blank">创建群聊</a></li>
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
					<a id="wechat_search"></a>
					<input id="search_input" type="text" placeholder="搜索">
				</div>
				<div id="list_tab">
					<div class="list_tab_chat" id="list_tab_chat">
						<a href="#" title="聊天">
							<i id="wechat_chat">
							</i>
						</a>	
					</div>
					<div id="list_tab_public" class="list_tab_public">
						<a href="OfficialAccountsServlet?id=<%=user.getId() %>" title="阅读">
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
				<div>
				</div>	
			</div>
			<div id="chatArea">
				<div id="chatArea_hd">
					<div id="chatArea_title">
						<a id="wechat_down"></a>
					</div>
				</div>
				<div class="chatArea_main_i">
				<i id="chatArea_main_image" >
				</i>
				</div>
				<div id="chatArea_main">
					<p>暂时没有新消息</p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>