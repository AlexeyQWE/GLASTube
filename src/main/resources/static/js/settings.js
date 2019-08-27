function newLogin() {
    console.log(document.getElementById("user").innerText);
    if (document.getElementById("newLogin").value.trim() === ""){
        alert("Please enter new login!");
    } else {
        $.get("/newLogin?newLogin=" + document.getElementById("newLogin").value + "&login=" + document.getElementById("user").innerText,
        function(data) {
            if (data != "") {
                location.href = "/login";
            } else alert("This login already exist!");
        });
    }
}
function newPassword() {
    console.log(document.getElementById("user").innerText);
    if (document.getElementById("oldPassword").value.trim() === ""){
        alert("Please enter current password!");
    } else if (document.getElementById("newPassword").value.trim() === "") {
        alert("Please enter new password!");
    } else {
        $.get("/newPassword?newPassword=" + document.getElementById("newPassword").value + "&Password=" + document.getElementById("oldPassword").value
                + "&login=" + document.getElementById("user").innerText,
        function(data) {
            if (data != "") {
                location.href = "/login";
            } else alert("Incorrect password!");
        });
    }
}