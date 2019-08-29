$(document).ready(function() {
            $.get("/all_video", function(data) {
                console.log(data.length);
                var str1 = "";
                var str2 = "";
                var str3 = "";
                    for (var i = data.length - 1; i >= 0; i--) {
                        console.log(i);
                        if (i == 3 || i == 0) {
                            str1 += "<div class=\"card-img\">";
                            str1 += "<video src=" + data[i].path + " controls width=\"400\" height=\"300\"></video>";
                            str1 += "<div class=\"card-footer\"><a href=\"/watch?id=" + (data[i].id) + "\" class=\"card-link\">" + data[i].name + "</a>";
                            str1 += "</div></div>";
                            $("#videoList1").html(str1);
                        } else if (i == 1 || i == 4) {
                            str2 += "<div class=\"card-img\">";
                            str2 += "<video src=" + data[i].path + " controls width=\"400\" height=\"300\"></video>";

                            str2 += "<div class=\"card-footer\"><a href=\"/watch?id=" + (data[i].id) + "\" class=\"card-link\">" + data[i].name + "</a>";
                            str2 += "</div></div>";
                            $("#videoList2").html(str2);
                        } else if (i == 5 || i == 2) {
                            str3 += "<div class=\"card-img\">";
                            str3 += "<video src=" + data[i].path + " controls width=\"400\" height=\"300\"></video>";
                            str3 += "<div class=\"card-footer\"><a href=\"/watch?id=" + (data[i].id) + "\" class=\"card-link\">" + data[i].name + "</a>";
                            str3 += "</div></div>";
                            $("#videoList3").html(str3);
                        }
                        console.log(str1);
                        console.log(str2);
                        console.log(str3);
                    }
            });
        });