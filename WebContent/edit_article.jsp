<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ov.User"%>
<%@ page import="ov.Account"%>
<%@ page import="java.io.*"%>    
<%
	String path=request.getContextPath();
	String bashPath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公众号编辑界面</title>

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
	
	Account account=(Account)request.getAttribute("account1");
	
	System.out.println("公众号："+account);
	String account_id=account.getId();
	System.out.println("公众号id:"+account_id);
	/*
	session.setAttribute("account_id",account.getId());
	System.out.println(account.getId());
	*/
	//Account account_id=(Account)request.getAttribute("a_id");
	//String account_id=(String)request.getAttribute("a_id");
	//System.out.println(""+account_id);
	
	//session.setAttribute("account_id",account.getId());
	//System.out.println(account.getId());
%>


<form action="ArticleServlet?account_id=<%=account_id %>" method="post">
<h3>微信公众号编辑页面</h3>
标题：<input type="text" name="title"><br/>
作者：<input type="text" name="author"><br/>

<script id="editor" type="text/plain" style="width:1024px;height:350px;"></script>
<input type="submit" value="保存并发送">
</form>


<!-- 实例化编辑器 -->
<script type="text/javascript">
	var ue = UE.getEditor('editor',{
		toolbars:[
			['fullscreen','source','undo','redo',"|",'fontfamily','|','blockquote',
				'horizontal','|','link','unlink','emotion','simpleupload',
				'insertimage','music'],
			['bold','italic','underline','strikethrough','autotypeset',
				'|','forecolor','backcolor','|','justifyleft',
				'justifycenter','justifyright','justifyjustify','insertorderedlist',
				'insertunorderedlist','|','preview','cleardoc']
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
</body>
</html>











