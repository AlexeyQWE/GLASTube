

function searchVideo(){
//    var text = document.getElementsByClassName("form-inline").value;
    var text = document.getElementById("textSearch").value;
    console.log(text);
    if (text.trim() === ""){
        alert("Search field must not be empty");
    } else {
        $.get("/resultSearch?text=" + text, function(data){
            var str = "";
            for(var i = 0; i < data.length; i++) {
                console.log(data[i]);
                str += data[i].author + "[" + data[i].id + "]:" + data[i].name + "<br>";
            }
            $(".temp").html(str);
            document.getElementsByClassName("temp").value = "";
        });
    }
}