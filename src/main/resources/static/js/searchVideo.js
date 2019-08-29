

function searchVideo(){
//    var text = document.getElementsByClassName("form-inline").value;
    var text = document.getElementById("textSearch").value;
    console.log(text);
    if (text.trim() === ""){
        alert("Search field must not be empty");
    } else {
        console.log("kekekekekek");
        location.href = "/resultSearch?text=" + text;
//        $.get("/resultSearch?text=" + text);
    }
}