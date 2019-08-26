$(document).ready(function() {
        $.get("/all_comments", function(data){
            var str = "";
            for(var i = data.length - 1; i >= 0; i--) {
                console.log(data[i]);
                str += data[i].id_user + "[" + data[i].date + "]:" + data[i].text + "<br>";
            }
            $(".comments").html(str);
        });
    });

    function addComment(){
        var text = document.getElementById("comment").value;
        if (text.trim() === ""){
            alert("Заполните поле для ввода");
        } else {
            $.get("/add_comment?id_user=123&text=" + text,function(data){
                $.get("/all_comments", function(data){
                    var str = "";
                    for(var i = data.length - 1; i >= 0; i--) {
                        console.log(data[i]);
                        str += data[i].id_user + "[" + data[i].date + "]:" + data[i].text + "<br>";
                    }
                    $(".comments").html(str);
                });
                console.log(data);
            });
        }
        //console.log(text);
        //$.post();

    }