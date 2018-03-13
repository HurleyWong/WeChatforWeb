<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ov.User"%>
<%@page import="ov.Account"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="ueditor.parse.js"></script>
<title>公众号内容</title>
<link rel="stylesheet" type="text/css" href="css/like_style.css"/>
<style>
	@import url("css/account_content.css");
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
	request.setCharacterEncoding("utf-8");
	String article_review=new String(request.getParameter("article_review"));
	System.out.println(article_review);
%>

<div id="container">
		<div id="mainContent">
			<div id="list">
				<div id="list_hd">
					<div id="actor">
						<img src=<%=headImage_path%>>
					</div>
					<div id="info_icon">
						<a href="#" id="wechat_add"></a>
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
						<a href="OfficialAccountsServer?id=<%=user.getId() %>" title="阅读">
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
				
				<div class="apply_account" onclick="window.location.href='ApplyAccountsServlet?id=<%=user.getId() %>';return false">		<!-- 申请公众号 -->
					<div class="apply_account_avatar">		<!-- 申请公众号入口图标 -->
						<!-- <img src="images/headImage.png"></a> -->
					</div>
					<div class="apply_account_info">		<!-- 申请公众号入口信息 -->
						<h3>
							<span>
								
							</span>
						</h3>
					</div>
				</div>
				
				<div class="dividing_line">
					<hr>		<!-- 分割线 -->
				</div>
				
				<div class="list_min_account">		<!-- 公众号列表 -->
<%
	int count=0;
	List<Account> list=(List<Account>)request.getAttribute("list");
	if(list!=null){
		count=list.size();
		for(int i=0;i<count;i++){
			Account account=list.get(i);
			System.out.println(" "+account.getAccount_name());
%>	
					<div class="list_min_account_avatar">		<!-- 公众号图标 -->
						<img src="images/headImage.png"></a>
					</div>
					<div class="list_min_account_date">		<!-- 公众号推送文章日期 -->
						<p>11.11</p>
					</div>
					<div class="list_min_account_info">		<!-- 公众号具体信息 -->
						<h3>
							<span>
								<%=account.getAccount_name() %><!-- 公众号名称 -->
							</span>
						</h3>
						<p>
							<span>
								<%=account.getC_id() %><!-- 公众号标题 -->
							</span>
						</p>
					</div>
					<div class="dividing_line">
						<hr>		<!-- 分割线 -->
					</div>
<%
		}
	}
%>	
				</div>
			</div>
			
			<div id="account_content">
				<div id="content_hd">
					<div id="content_title">
						<h3 class="article_title">评论成功</h3>
						<div class="article_list">
							<span class="article_tag"></span>
							<em class="article_date"></em>
							<em class="article_author"></em>
						</div>
					</div>
				</div>
				<hr>
				<div id="protocol_main">
					<p><%=user.getNickName() %>：</p>
					<p><%=article_review %></p>
					<article class="htmleaf-container">
						<div id="content_container">
						</div>
					</article>
				</div>
			</div>
		</div>
	</div>
</body>
</html>







