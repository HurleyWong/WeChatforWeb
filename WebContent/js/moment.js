function revi(id) {
	var mychar= document.getElementById("review");
    mychar.style.display = "block";
    var mych= document.getElementById("tep");
    mych.value = id ;
}

function send() {
	var mychar= document.getElementById("review1");
    var content  = mychar.value;
    var ch_id = document.getElementById("tep");
    var id = ch_id.value;
    window.location.href = "/Web_ClassDesign/review_save?m_id=" +id +"&content="+content +"&mark=0";
    var mych= document.getElementById("review");
    mych.style.display = "none";
}