<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style type="text/css">
    *{
        margin:0px;
        padding: 0px;
    }
    body{
        font-size:14px;
        background: url(images/wx_background.jpg) no-repeat;
        background-size:cover;
    }
    #container{
        width: 60%;
        height: 500px;
        border: 1px solid #C0C0C0;
        margin:80px auto;
    }
    #d_top{
        background-color: rgb(46,50,56);
        width: 100%;
        height: 50px;
        font-size: 18px;
        font-weight: 900;
        color:  #FFFFFF;

    }
    .radio1{
        margin-top: 20px;
        margin-left: 50px;
    }
    .radio2{
        margin-top: 20px;
         margin-left: 50px;
    }
    .tocontact{
    	text-decoration: none;
    	color:#fff;
    	float:right;
    	margin-top: 10px;
    	margin-right:20px;
    }
    #d_center{
        width: 100%;
        height: 70px;
        border-bottom: 1px solid #C0D0D7;
    }
    .search_input1{
        width: 50%;
        background-color: #FFF;
        background-image: none;
        border: 1px solid #e5e6e7;
        border-radius: 1px;
        color: inherit;
        padding: 6px 12px;
        -webkit-transition: border-color .15s ease-in-out 0s,box-shadow .15s ease-in-out 0s;
        transition: border-color .15s ease-in-out 0s,box-shadow .15s ease-in-out 0s;
        font-size: 14px;
    }
    .btn_1{
        width: 80px;
         height: 30px;
         background-color: #1ab394;
         border-color: #1ab394;
         color: #FFF;
         margin-bottom: 20px;
         margin-left: 20px; 

    }

    .frm_group{
        margin-left: 100px;
        margin-top: 25px;
        width:100%;
    }
</style>
</head>
<body>
    <div id="container">
        <div id="d_top">
            <label style="margin-left: 50px;">查找</label><input class="radio1" type="radio" name="radio1" value="p_id">人<input class="radio2" type="radio" name="radio1" value="p_id">群
            <input class="radio2" type="radio" name="radio1" value="p_id">公众号
            <a class="tocontact" href="ContactServlet?id=<%=session.getAttribute("userid")%>">返回</a>
        </div>
        <div id="d_center">
            <div class="frm_group">
                <input type="text"     class="search_input1" placeholder="请输入" required="" id="id" name="id" ><input type="button" value="查找" class="btn_1">
            </div>
        </div>
        <div id="d_bottom">
        
        </div>
    </div>
</body>
</html>