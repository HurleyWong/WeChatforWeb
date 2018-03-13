function sendTextMessage1(){
	if(document.getElementById("mmm_title").innerHTML==""){
		alert("当前还未选择联系人");
		return;
	}
/*	alert(document.getElementById("chatArea_input_blank").innerHTML);*/
	var str = document.getElementById("chatArea_input_blank").innerHTML;
	var textarea = document.getElementById("Message");
	var myhtml = "<div class='m_contact'><div class='m_contact_time'><p>"+getNowFormatDate()+"</p></div><div class='m_actor'><img src='images/headImage.png'></div><div class='m_message'><p>"+str+"</p></div></div>"
	textarea.innerHTML+=myhtml;
	add();
	document.getElementById("chatArea_input_blank").innerHTML="";
	if(document.getElementById("chat_type").value!=null){
		if(document.getElementById("chat_obj").value!=null){
			var chat_obj = document.getElementById("chat_obj").value;
			var chat_type = document.getElementById("chat_type").value;
			var con_message =str;
		    websocket.send(chat_obj+','+chat_type+','+con_message);
		}
	}
}
function playMusic(){
	
	 var audio = document.getElementById('music1'); 
	 if(audio!==null){             
	    //检测播放是否已暂停.audio.paused 在播放器播放时返回false.
	     // alert(audio.paused);
	  if(audio.paused)                     {                 
	      audio.play();//audio.play();// 这个就是播放  
	  }else{
		  audio.play();// 这个就是暂停
	  }
	 } 
}
function receiveTextMessage1(str){
//	var strs= new Array(); 
//	str = str.replace("[","");
//	str = str.replace("]","");
//	strs=str.split(",");
//	for(var i=0;i<strs.length;i++){
//		console.log("第"+i+"个 = "+strs[i]);
//	}
//	var for_obj =strs[0];
//	var for_name = strs[1];
//	var img_path =strs[2];
//	var message =strs[3];
//	var message = strs.replace('"',"");
//	console.log("for_obj: " +strs[0]+"  for_name: "+strs[1]+"  img_path: "+strs[2]+"message: "+strs[3]);
//	console.log("title:"+document.getElementById("mmm_title").innerHTML+"name:"+strs[1]);
//	if(document.getElementById("mmm_title").innerHTML==for_name){
//		var textarea = document.getElementById("Message");
//		var myhtml = "<div class='f_contact'><div class='m_contact_time'><p>"+getNowFormatDate()+"</p></div><div class='f_actor'><img src='"+getimg_path()+"'></div><div class='f_message'><p>"+str+"</p></div></div>"
//		textarea.innerHTML+=myhtml;
//		add();
//	}else{
//		console.log("还未选择联系人!");
//		var textarea = document.getElementById("Message");
//		var myhtml = "<div class='f_contact'><div class='m_contact_time'><p>"+getNowFormatDate()+"</p></div><div class='f_actor'><img src="+getimg_path()+"></div><div class='f_message'><p>"+str+"</p></div></div>"
//		textarea.innerHTML+=myhtml;
//		add();
//	}
/*	alert(document.getElementById("chatArea_input_blank").innerHTML);*/
	playMusic();
	var textarea = document.getElementById("Message");
	var myhtml = "<div class='f_contact'><div class='m_contact_time'><p>"+getNowFormatDate()+"</p></div><div class='f_actor'><img src="+getimg_path()+"></div><div class='f_message'><p>"+str+"</p></div></div>"
	textarea.innerHTML+=myhtml;
	add();
	
}
window.onload = function () {
	
	function setLogArea(str){
		console.log(str);
	}
	
	if ('WebSocket' in window) {
	    websocket = new WebSocket("ws://192.168.43.64:8080/Web_ClassDesign/websocket");
	/*    alert("new websocket");*/
	    console.log('new websocket');
	  /*  setLogArea('new websocket');*/
	}
	else {
	    alert('当前浏览器 Not support websocket');
	}
	//连接发生错误的回调方法
	 websocket.onerror = function () {
		 console.log('WebSocket连接发生错误');
//		 setLogArea('WebSocket连接发生错误');
	 };

	 //连接成功建立的回调方法
	 websocket.onopen = function () {
		 console.log('WebSocket连接成功');
//		 setLogArea('WebSocket连接成功');
	 };

	 //接收到消息的回调方法
	 websocket.onmessage = function (event) {
	 	console.log(event)
	 	receiveTextMessage1(event.data);
//	 	setLogArea(event.data);
	 };

	 //连接关闭的回调方法
	 websocket.onclose = function () {
		 console.log("WebSocket连接关闭");
//		 setLogArea('WebSocket连接关闭');
	 };

	 //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
	 window.onbeforeunload = function () {
	     closeWebSocket();
	 };
}