<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page import="ov.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
    <title>Java后端WebSocket的Tomcat实现</title>
    <style>
	@import url("css/chat.css");
</style>
</head>
<body style="overflow:-Scroll;overflow-y:hidden">
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
					<div class="list_min_chat" ondblclick="goChat1(<%=list2.get(i).getId()%>)" onclick="show1('<%=list2.get(i).getId()%>','<%out.print(list2.get(i).getName());%>','<%if(user.getId().equals(list2.get(i).getC_id())){out.print("1");}else{out.print("0");}%>')">     
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
					<div class="list_min_chat" ondblclick="goChat2(<%=list.get(i).getId()%>)" onclick="show2('<%=list.get(i).getId() %>','<%=list.get(i).getNickName()%>')">
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
						<p><%
	           					if(request.getAttribute("m_g_m")!=null){
	           						MyG_Message mgm1 = (MyG_Message)request.getAttribute("m_g_m");
	           						out.println("<a id ='mmm_title'  href='GroupInformationServlet?info_type=1&g_id="+mgm1.getId()+"'>"+mgm1.getName()+"</a>");
	           						out.println("<input type='text' name='' id='chat_type' value='1' hidden='hidden'> ");
	           						out.println("<input type='text' name='' id='chat_obj' value='"+mgm1.getId()+"' hidden='hidden'> ");
	           					}else{
	           						ArrayList<P_Message> mpm1 = (ArrayList<P_Message>) request.getAttribute("m_p_m");
	           						out.println("<a id ='mmm_title'  href='#'> "+mpm1.get(0).getNickName()+"</a>");
	           						out.println("<input type='text' name='' id='chat_type' value='0' hidden='hidden'> ");
	           						out.println("<input type='text' name='' id='chat_obj' value='"+mpm1.get(1).getId()+"' hidden='hidden'> ");
	           					}
	           				%></p>
					</div>
				</div>
				<div class="Message" id="Message" >
				<%
	           		if(request.getAttribute("m_g_m")!=null){
	           			MyG_Message mgm = (MyG_Message)request.getAttribute("m_g_m");
	           			for(i =0;i<mgm.getList().size();i++){
	           				if(mgm.getList().get(i).getId().equals(user.getId())){

				%>	
								<div class="m_contact">
								<div class="m_contact_time">
								<p><%
								java.util.Date date1 = new Date();
								String d1 = date1.getDate()+"";
								String d2 = mgm.getList().get(i).getC_date().getDate()+"";
								if(d1.equals(d2)){
									out.print(mgm.getList().get(i).getC_date().getHours()+":"+mgm.getList().get(i).getC_date().getMinutes());
								}else{
									out.print(mgm.getList().get(i).getC_date().getDate()+":"+mgm.getList().get(i).getC_date().getHours()+":"+mgm.getList().get(i).getC_date().getMinutes());
								}
								%>
								</p>
								</div>
								<div class="m_actor">
									 <img src="<%=mgm.getList().get(i).getHeadImage()%>"> 
								</div>
								<div class="m_message">
								<p><%=mgm.getList().get(i).getContent() %><br/></p>
								</div>
								</div>
				
							<%
	           				}else{
							%>
			
								<div class="f_contact">
								<div class="m_contact_time">
								<p><%
								java.util.Date date1 = new Date();
								String d1 = date1.getDate()+"";
								String d2 = mgm.getList().get(i).getC_date().getDate()+"";
								System.out.print("now day:"+d1);
								System.out.print(" day:"+mgm.getList().get(i).getC_date().getDate());
								if(d1.equals(d2)){
									out.print(mgm.getList().get(i).getC_date().getHours()+":"+mgm.getList().get(i).getC_date().getMinutes());
								}else{
									out.print(mgm.getList().get(i).getC_date().getDate()+":"+mgm.getList().get(i).getC_date().getHours()+":"+mgm.getList().get(i).getC_date().getMinutes());
								}
								%></p>
								</div>
								<div class="f_actor">
								<img src="<%=mgm.getList().get(i).getHeadImage() %>"> 
								</div>
								<div class="f_message">
								<p><%=mgm.getList().get(i).getContent() %></p>
								</div>
								</div>
				<%
           			}}}else{

           				ArrayList<P_Message> mpm = (ArrayList<P_Message>) request.getAttribute("m_p_m");
	           			for(i =0;i<mpm.size();i++){
	           				if(mpm.get(i).getId().equals(user.getId())){
				%>	
								<div class="m_contact">
								<div class="m_contact_time">
								<p><%
								java.util.Date date1 = new Date();
								String d1 = date1.getDate()+"";
								String d2 = mpm.get(i).getC_date().getDate()+"";
								if(d1.equals(d2)){
									out.print(mpm.get(i).getC_date().getHours()+":"+mpm.get(i).getC_date().getMinutes());
								}else{
									out.print(mpm.get(i).getC_date().getDate()+":"+mpm.get(i).getC_date().getHours()+":"+mpm.get(i).getC_date().getMinutes());
								}
								%>
								</p>
								</div>
								<div class="m_actor">
									 <img src="<%=mpm.get(i).getHeadImage()%>"> 
								</div>
								<div class="m_message">
								<p><%=mpm.get(i).getContent() %><br/></p>
								</div>
								</div>
				
							<%
	           				}else{
							%>
			
								<div class="f_contact">
								<div class="m_contact_time">
								<p><%
								java.util.Date date1 = new Date();
								String d1 = date1.getDate()+"";
								String d2 = mpm.get(i).getC_date().getDate()+"";
								System.out.print("now day:"+d1);
								System.out.print(" day:"+mpm.get(i).getC_date().getDate());
								if(d1.equals(d2)){
									out.print(mpm.get(i).getC_date().getHours()+":"+mpm.get(i).getC_date().getMinutes());
								}else{
									out.print(mpm.get(i).getC_date().getDate()+":"+mpm.get(i).getC_date().getHours()+":"+mpm.get(i).getC_date().getMinutes());
								}
								%></p>
								</div>
								<div class="f_actor">
								<img src="<%=mpm.get(i).getHeadImage() %>"> 
								</div>
								<div class="f_message">
								<p><%=mpm.get(i).getContent() %></p>
								</div>
								</div>
				<%
           			}}}
				%>
					<!-- <div class="m_contact">
					<div class="m_contact_time">
					<p>12:56</p>
					</div>
					<div class="m_actor">
						 <img src="images/headImage.png"> 
					</div>
					<div class="m_message">
					<p>444mia<br/></p>
					</div>
					</div>
					
					<div class="f_contact">
					<div class="m_contact_time">
					<p>12:56</p>
					</div>
					<div class="f_actor">
					<img src="images/headImage.png"> 
					</div>
					<div class="f_message">
					<p>555</p>
					</div>
					</div> -->
					
					</div>
				<div class="chatArea_blow">
				<%
					if(request.getAttribute("m_p_m")!=null){
						
				%>
					<div id="chatArea_tool">						
						<a class="wechat_face" ng-click="showEmojiPanel($event)" href="javascript:;" title="表情">
						</a>
						<a class="wechat_screencut" mm-action-track="" track-type="['click']" ng-click="screenShot()" href="javascript:;" title="截屏">
						</a>
						<a id="wechat_file" mm-action-track="" track-type="['click']" onclick="popWindows()"  href="javascript:;" title="文件和图片">
						</a>
					</div>			       
				<%
					}else{
				%>
					<div id="chatArea_tool">						
						<a class="wechat_face" ng-click="showEmojiPanel($event)" href="javascript:;" title="表情">
						</a>
						<a class="wechat_screencut" mm-action-track="" track-type="['click']" ng-click="screenShot()" href="javascript:;" title="截屏">
						</a>
						<a id="wechat_file" mm-action-track="" track-type="['click']" ng-click="sendClick($event)"  href="javascript:;" title="文件和图片">
						</a>
					</div>		
				<%
					}
				%>
				<div id="chatArea_input_blank" contenteditable="true" onkeydown="isSend()"></div>
				<div id="chatArea_action">
					<span>按下Ctrl+Enter发送</span>
					<a class="btn btn_send" href="javascript:sendTextMessage1();" ng-click="sendTextMessage1()">发送</a>
				</div> 
				</div>
						
				</div>

					</div>
					
				</div>
				
				<audio src="music/b.mp3" controls="controls" preload id="music1" hidden></audio>
				
	<script type="text/javascript">
		function popWindows(){
			
		}
	</script>
				
	<script type="text/javascript" src="js/chat.js"></script>
	
	<script type="text/javascript" src="js/letgochat.js"></script>
	
	<script type="text/javascript" src="js/contact.js"></script>
	
	
</body>
</html>