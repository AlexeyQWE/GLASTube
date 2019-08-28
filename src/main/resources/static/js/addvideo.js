$(document).ready(function() {

});

function tmp() {

//$("#submitButton").click(function(event) {
        var text = document.getElementById("filename").value;
        console.log("fvgbhjnk="+text);
        if (text.trim() === ""){
            alert("Заполните поле для ввода");
        } else {
            // Stop default form Submit.
            event.preventDefault();

            // Call Ajax Submit.

            ajaxSubmitForm();
        }
//    });

}

function ajaxSubmitForm() {

    // Get form
    var form = $('#fileUploadForm')[0];

    var data = new FormData(form);

    var token = $("meta[name='_csrf']").attr("content");

    var uri_request = "/rest/uploadMultiFiles?login=" + document.getElementById("user").innerText;

    console.log(uri_request);

    $("#submitButton").prop("disabled", true);

    console.log(data);

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: uri_request,
        headers: {"X-CSRF-TOKEN": token},
        data: data,

        // prevent jQuery from automatically transforming the data into a query string
        processData: false,
        contentType: false,
        cache: false,
        timeout: 1000000,
        success: function(data, textStatus, jqXHR) {

            $("#result").html(data);
            console.log("SUCCESS : ", data);
            $("#submitButton").prop("disabled", false);
            $('#fileUploadForm')[0].reset();
        },
        error: function(jqXHR, textStatus, errorThrown) {

            $("#result").html(jqXHR.responseText);
            console.log("ERROR : ", jqXHR.responseText);
            $("#submitButton").prop("disabled", false);

        }
    });

}