$(function () {

    // $(window).unload(function (evt) {
    //     if (typeof evt == 'undefined') {
    //         evt = window.event;
    //     }
    //     if (evt) {
    //         var n = window.event.screenX - window.screenLeft;
    //         var b = n > document.documentElement.scrollWidth-20;
    //
    //         if(b && window.event.clientY < 0 || window.event.altKey){
    //             // 这个可以排除刷新 关闭的时候触发
    //             var userID = localStorage.getItem("userID");
    //             localStorage.setItem("userName", null);
    //             localStorage.setItem("userID", null);
    //             $.ajax({
    //                 url: "user/logout",
    //                 data: {"userID": userID},
    //                 type: "get",
    //                 dataType: "JSON"
    //             });
    //         }
    //     }
    // });

    window.onbeforeunload  =  function()
    {
        if((event.clientX>document.body.clientWidth&&event.clientY<0)||event.altKey)
        {
            alert("关闭触发");
        }
        else
        {
            alert("刷新触发");
        }
    };

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
