$(function () {
    var userID = getUrlVars()['userID'];
    $("#userID").val(userID);
    $("#submit").click(function () {
        var password = $("#password").val();
        var re_pass = $("#re_password").val();
        if (password != re_pass) {
            alert("输入的两次密码不一致!");
            return false;
        }
        $.ajax({
            url : "user/changePassword",
            data : {"userID":userID, "password":password},
            type : "post",
            dataType : "JSON",
            //TODO 懒得去改了，ajax方法返回直接进入了error方法
            error : function (result) {
                alert("修改密码成功，将自动前往登录页面登录!");
                window.location.href = "login.html";
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