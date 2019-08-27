

function searchVideo(){
//    var text = document.getElementsByClassName("form-inline").value;
    var text = document.getElementById("textSearch").value;
    console.log(text);
    $.get("/resultSearch?text=" + text, function(data){


    });
}