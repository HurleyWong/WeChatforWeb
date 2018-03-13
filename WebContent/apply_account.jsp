<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ov.User"%>
<%@page import="ov.Account"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>申请公众号</title>
<%
	request.setCharacterEncoding("utf-8");
%>
<style>
	@import url("css/apply_account.css");
</style>
</head>
<body>
<%
	User user = (User)request.getAttribute("user");
	//System.out.println(" "+ user.toString());
	String headImage_path = "C:\\Users\\jack\\Desktop\\head_image\\";
	String id = user.getId()+"\\";
	headImage_path+=id;	
	headImage_path += "head.png";
	System.out.println("path=="+headImage_path);


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
				
				<div class="apply_account">		<!-- 申请公众号 -->
					<div class="apply_account_avatar">		<!-- 申请公众号入口图标 -->
						<img src="images/headImage.png"></a>
					</div>
					<div class="apply_account_info">		<!-- 申请公众号入口信息 -->
						<h3>
							<span>
								申请公众号
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
						<p></p>
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
			
			<div id="protocol">
				<div id="protocol_hd">
					<div id="chatArea_title"><h3>微信公众平台服务协议</h3>
						<!-- <a id="wechat_down"></a>  -->
					</div>
				</div>
				<div id="protocol_main">
					欢迎你使用微信公众平台！<br><br>
					一、【协议的范围】<br>
					1.1  本协议是你与腾讯之间关于你使用微信公众平台服务所订立的协议。<br>
					1.2  本服务是腾讯向用户提供的信息发布、客户服务、企业管理以及与此相关的互联网技术服务。<br><br>
					二、【帐号注册与认证】<br>
					2.1  你在使用本服务前需要注册一个微信公众帐号。<br>
					2.2 用户在注册微信公众帐号时需要选择帐号类型，且选择后将无法更改。<br><br>
					三、【用户个人信息保护】<br>
					3.1  保护用户个人信息是腾讯的一项基本原则，腾讯将会采取合理的措施保护用户的个人信息。<br>
					3.2  未经你的同意，腾讯不会向腾讯以外的任何公司、组织和个人披露你的个人信息。<br><br>
					四、【用户行为规范】<br>
					4.1 【信息内容规范】<br>
					4.2 【平台使用规范】<br><br>
					五、【帐号管理】<br>
					5.1 微信公众帐号的所有权归腾讯公司所有。<br>
					5.2 微信公众帐号密码由你自行设定。<br>
					5.3 如果你的微信公众帐号被盗、密码遗忘或因其他原因导致无法正常登录，你可以按照腾讯的申诉途径进行<br>
					申诉。腾讯并不承诺你一定能通过申诉找回帐号。<br><br>
					六、【风险及免责】<br>
					6.1  你理解并同意：在使用本服务时，须自行承担如下腾讯不可掌控的风险内容，包括但不限于：<br>
					6.1.1 由于受到计算机病毒或其他恶意程序、黑客攻击等不可抗拒因素引起的信息丢失泄漏等损失和风险；<br>
					6.1.2 用户或腾讯的电脑软件、硬件和通信线路出现故障导致的服务终端、数据丢失及其他损失和风险；<br>
					6.2.3 用户发布的内容被他人转发、分享，因此等传播可能带来的风险和责任；<br>
					6.2.4 其他腾讯无法控制或合理预见的情形。<br><br>
					七、【知识产权声明】<br>
					7.1  腾讯在本服务中所使用的商业标识，其著作权或商标权归腾讯所有。<br>
					7.2  任何未经腾讯书面同意及权利人许可的非法获取行为，均属违法侵权行为。<br><br>
					阅读完整服务协议请点击
					<a href="https://mp.weixin.qq.com/cgi-bin/announce?action=getannouncement&key=1503979103&version=1&lang=zh_CN&platform=2">
						《微信公众平台服务协议》
					</a>
				</div>
				
				<div id="chatArea_tool">
					<a id="wechat_face"></a>
					<a id="wechat_screencut"></a>
					<a id="wechat_file"></a>
				</div>
				<div id="chatArea_input_blank"></div>
				<div id="protocol_submit">
					<a href="register_account.jsp?id=<%=user.getId()%>">我同意并遵守《微信公众平台服务协议》</a>
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>