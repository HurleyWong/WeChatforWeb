<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="ov.User"%>
<%@ page import="java.io.*"%>
<%
	String path=request.getContextPath();
	;String bashPath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style>
	@import url("css/friend.css");
</style>

<!-- 配置文件 -->
<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.min.js"></script>
<link rel=stylesheet href="ueditor/themes/default/css/ueditor.css">
<script type="text/javascript" charset="uft-8" scr="ueditor/lang/zh-cn/zh-cn.js"></script>
<style type="text/css">
	div{
		width="100%";
	}
</style>

</head>
<body>

	<div id="container">
		<div id="mainContent">
			<div id="list">
				<div id="list_hd">
					<div id="actor">
						<img src=>
					</div>
					<div id="info_icon">
				        <div id="wechat_add" >
				             <ul id="m1" class="active">
					            <li><a href="#" target="mainFrame">修改资料</a></li>
					            <li><a href="moment.jsp" target="_blank">朋友圈</a></li>
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
						<a href="ContactServlet?id=" title="通讯录">
							<i id="wechat_friends">
							</i>
						</a>
					</div>
				</div>
				
				
				
			</div>
			<div id="chatArea">
				<!-- 
				<form name="frm" action="/Web_ClassDesign/moment_save">
				<table border="0" id="content-table" cellpadding="0" cellspacing="0">
				    <tr>
						<td><span>编辑朋友圈</span></td>
						<td>
							<textarea name="note" cols="50" rows="16"></textarea>
						</td>
					</tr>
					<tr>
					    <td><a href="#" ><input type="button" value="上传图片"></a></td>
					</tr>
					<tr>
					    <td><span>选择可见</span></td>
					    <td>       
					        <select name="list">
					         <option value="all myfriend">all myfriend</option>
					         <option value="only myself">only myself</option>
					        </select>
					    </td>
					</tr>
					<tr>
					    <td><input type="submit" value="发表"></td>
					</tr>
				</table>
				</form>
				 -->
				<form name="frm" action="/Web_ClassDesign/moment_save">
				
				 <h3>编辑朋友圈</h3></br>
				 <script id="editor" type="text/plain" style="width:720px;height:350px;"></script>
				<table border="0" id="content-table" cellpadding="0" cellspacing="0">

		    			<tr>
					    <td><span>选择可见</span></td>
					    <td>       
					        <select name="list">
					         <option value="all myfriend">all myfriend</option>
					         <option value="only myself">only myself</option>
					        </select>
					    </td>
					</tr>
					<tr>
					    <td><input type="submit" value="发表"></td>
					</tr>
				</table>
				</form>		
			<!-- 实例化编辑器 -->
<script type="text/javascript">
	var ue = UE.getEditor('editor',{
		toolbars:[
			['fullscreen','source','undo','redo','|','emotion','simpleupload','insertimage'],
			['autotypeset','preview','cleardoc']
		]
	});
	
	function getContent() {
        var arr = [];
        arr.push("使用editor.getContent()方法可以获得编辑器的内容");
        arr.push("内容为：");
        arr.push(UE.getEditor('editor').getContent());
        alert(arr.join("\n"));
    }
	
</script>
				
			</div>

		</div>
	</div>
</body>
</html>