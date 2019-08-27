function register() {
    if (document.getElementById("login").value.trim() === ""){
        alert("Please enter login!");
    } else if (document.getElementById("password").value.trim() === ""){
        alert("Please enter password!");
    } else {
        $.get("/register?login=" + document.getElementById("login").value
                    + "&password=" + document.getElementById("password").value,
        function(data) {
            console.log(data);
            if (data != "") {
                location.href = "/";
            } else alert("This login already exist!");
        });
    }
}