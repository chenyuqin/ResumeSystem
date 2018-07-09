$(function () {
    var userID = getUrlVars()["userID"];
    var userName = getUrlVars()["userName"];
    if (userID != null && userID != undefined && userName != null && userName != undefined) {
        localStorage.setItem("userID", userID);
        localStorage.setItem("userName", userName);
    }
    var userName = localStorage.getItem("userName");
    if (userName == null) {
        alert("请先登录!");
        window.location.href = "login.html";
    } else {
        $("#currentUser").html(decodeURI(userName));
    }

    $("#logout").click(function () {
        var userID = localStorage.getItem("userID");
        localStorage.setItem("userName", null);
        localStorage.setItem("userID", null);
        $.ajax({
            url: "user/logout",
            data: {"userID": userID},
            type: "get",
            dataType: "JSON",
            success: function (result) {
                if (result.state == 0) {
                    window.location.href = "login.html";
                }
            }
        });
    });
});

//获取URL中的参数
function getUrlVars() {
    var vars = [],
        hash;
    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
    for (var i = 0; i < hashes.length; i++) {
        hash = hashes[i].split('=');
        vars.push(hash[0]);
        vars[hash[0]] = hash[1];
    }
    return vars;
}
