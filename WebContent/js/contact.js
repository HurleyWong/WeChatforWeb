function showHint(str)
{
	var xmlhttp;
	var content = document.getElementById("search_input");
	str = content.value;
	console.log("====keyword=="+str);
	if(content.value==""){
		clearContent();
		return ;
	}
	if (str.length==0)
	  {
		clearContent();
	  document.getElementById("search_input").innerHTML="";
	  return;
	  }
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
	    	var json = eval("("+result+")");
	    	toShow(json);
	    }
	  }
	var url ="ShowHintServlet?keyword="+str;
	console.log("====url=="+url);
	xmlhttp.open("GET",url,true);
	xmlhttp.send();
}

function toShow(contents){
	console.log("toshow");
	clearContent();
	
	var size = contents.length;
	for(var i=0;i<size;i++){
		var nextNode = contents[i];
		var tr = document.createElement("tr");
		var td = document.createElement("td");
		
		
		var str2 = "<tr><td>"+""+"</td></tr>"
		td.onmouseover = function(){
			this.className = 'mouseOver';
			document.getElementById("search_input").value=this.innerHTML; 
		};
		
		td.onmouseout = function(){
			this.className = 'mouseOut';
		};
		console.log(nextNode);
		
		td.onclick = function(){
			console.log("点击td ");
			console.log(td.innerText);
//			document.getElementById("search_input").value=nextNode;
		};
		
		var text = document.createTextNode(nextNode);
		td.appendChild(text);
		tr.appendChild(td);
		document.getElementById("mytable").appendChild(tr);
	
	}
}

function clearContent(){

	
	var con = document.getElementById("mytable");
	var size = con.childNodes.length;
	for(var i = size -1;i>=0;i--){
		con.removeChild(con.childNodes[i]);
	}
}
function isContains(str, substr) {
    return str.indexOf(substr) >= 0;
}
function toshowperson(){
	console.log("toshowsomeone");
	var myf = document.getElementsByClassName('list_min_chat');
	console.log("length = " + myf.length);
	for (var i = 0; i < myf.length; i++) {
	console.log(":"+myf[i].innerHTML);
	if (isContains(myf[i].innerHTML,document.getElementById("search_input").value)) {
		console.log("有");
		myf[i].onclick();
	}else{
		console.log("没有");
	}

	}	
}
function onblur1(){
	clearContent();
/*	if(document.getElementById("search_input").value!=null){
		toshowperson();
	}*/
}