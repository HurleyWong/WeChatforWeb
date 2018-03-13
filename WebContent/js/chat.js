add();
function add()
{
	var div = document.getElementById('Message');	
	div.scrollTop = div.scrollHeight;
}

function goChat1(id){
	window.location.href="ChatServlet?g_id="+id;
}
function goChat2(id){
	window.location.href="ChatServlet?p_id="+id;
}

function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + " " + date.getHours() + seperator2 + date.getMinutes()
            + seperator2 + date.getSeconds();
    return date.getHours()+":"+date.getMinutes();
}

function isSend(){
	if(event.ctrlKey&&event.keyCode==13){
		sendTextMessage1();
	} 
}
function getimg_path(){
	return 'images/headImage.png';
}