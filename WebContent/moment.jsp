<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="ov.User,ov.Moment,ov.Like,ov.Review"%>
<%@ page import="java.io.* ,java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>

<style>
	@import url("css/friend.css");
</style>
<script src="js/moment.js"></script>
</head>
<body>
	<div id="container">
		<div id="mainContent">
			<div id="list">
				<div id="list_hd">
					<div id="actor">
						<img src="images/headImage.png">
					</div>
					<div id="info_icon">
				        <div id="wechat_add" >
				             <ul id="m1" class="active">
					            <li><a href="#" target="mainFrame">修改资料</a></li>
					            <li><a href="moment.jsp?user" target="_blank">朋友圈</a></li>
				             </ul>
				        </div>				      
					</div>
					<div id=info>
						<p><%=session.getAttribute("user_nickname") %></p>
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
						<a href="#" title="阅读">
							<i id="wechat_public">
							</i>
						</a>
					</div>
					<div id="list_tab_friends" class="list_tab_friends" >
						<a href="ContactServlet?id=ddd" title="通讯录">
							<i id="wechat_friends">
							</i>
						</a>
					</div>
				</div>
				
				
				
			</div>
			<div id="chatArea">
				<div id="chatArea_hd">
					<div id="chatArea_title">
						<a id="wechat_down"></a>
					</div>
					<div id="but">
					    <a href="mymoment_list" target="_self"><input type="button" value="我的朋友圈"></a>
					    <a href="moment_add.jsp" target="_self"><input type="button" value="发表朋友圈"></a>
				    </div>
				</div>
				<div id="allFriend">
				<br/>
				<div id = "temp">
				    <input type = "text" id = "tep" size = "10" >
				</div>
				<div id="review">
                        <input type="text" id="review1" size="40"><input type="button" value="发送" onclick="send()">
                </div>
<%              
                int count = 0;	
	            List<Moment> list = (List<Moment>)request.getAttribute("list");
	            List<User> userList = (List<User>)request.getAttribute("userList");
	            List<List<Like>> likeList = (List<List<Like>>)request.getAttribute("likeList");
	            List<List<Review>> reviewList = (List<List<Review>>)request.getAttribute("reviewList");
	            if (list != null) {
		        count = list.size();
		
		        for(int i = 0; i < count; i++) {
			         Moment moment = list.get(i); 
			         User user = userList.get(i);
			         List<Like> lList = likeList.get(i);
			         List<Review> rList = reviewList.get(i);
			         
			         
			         
%>
                <table border="0" id="content-table" cellpadding="0" cellspacing="0">
                <tr>
                   <td><img src="images/4.jpg"> &nbsp<%=user.getNickName() %></td>          
                </tr>
                <tr>
                      <td><%=moment.getContent() %></td>
                </tr>
                <tr>
                    <td>
                    <input type="button" value="评论" onclick="revi(<%=moment.getId() %>)">
                    <a href = "like_save?mo_id=<%=moment.getId() %>&mark=0"><input type="button" value="点赞"></a>
                    <a href = "hide_save?c_id=<%=moment.getC_id() %>"><input type="button" value="屏蔽"></a>
                    </td>
                </tr>
                <tr>
      <%
                  String str = "";
                  String str1 = "";
                  for(int j=0;j<lList.size();j++){
                       str = lList.get(j).getC_id()+" ";
                       str1 = str1+str ;
                  }
       %> 
               <td> <%=str1 %>等<%=lList.size() %>人已赞！ </td>      
                </tr>
                <tr>
                <td>
                <%             
                   for(int k=0;k<rList.size();k++){
                       String areview = rList.get(k).getR_id()+":"+rList.get(k).getContent()+"<br/>"; 
                %>
                <%=areview %> 
                <%
                   }
                %> 
                </td>
                </tr>
                </table>           
                <br/>
<%  
               }
               } 
%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>