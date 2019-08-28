$(document).ready(function() {
    $.get("/all_comments?videoname=" + document.getElementById("videoname").innerText, function(data){
        var str = "";
        for(var i = data.length - 1; i >= 0; i--) {
            console.log(data[i]);
            str += data[i].user + "[" + data[i].date + "]:" + data[i].text + "<br>";
        }
        $(".comments").html(str);
    });
});

function addComment(){
    var videoname = document.getElementById("videoname").innerText;
    var login = document.getElementById("user").innerText;
    var text = document.getElementById("comment").value;
    if (text.trim() === ""){
        alert("Enter your commentary!");
    }else if (login === ".O."){
        alert("Please sing in!");
    }else {
        $.get("/add_comment?videoname=" + videoname + "&user=" + login + "&text=" + text,function(data){
            $.get("/all_comments?videoname=" + videoname, function(data){
                var str = "";
                for(var i = data.length - 1; i >= 0; i--) {
                    console.log(data[i]);
                    str += data[i].user + "[" + data[i].date + "]:" + data[i].text + "<br>";
                }
                $(".comments").html(str);
                document.getElementById("comment").value = "";
            });
            console.log(data);
        });
    }
    //console.log(text);
    //$.post();

}