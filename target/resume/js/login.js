$(function () {
    $("#login").click(function () {
        var userID = $("#userID").val();
        var password = $("#password").val();
        var rememberMe = $("#rememberMe").is(":checked");

        if (userID == null || password == null || userID == "" || password == "") {
            alert("用户ID或者密码不能为空!");
            return false;
        }
        $.ajax({
            url: "user/login",
            data: {"userID": userID, "password": password, "rememberMe": rememberMe},
            type: "post",
            dataType: "JSON",
            success: function (result) {
                if (result.state == 0) {
                    alert(result.message);
                    localStorage.setItem('userID', userID);
                    localStorage.setItem('userName', result.data.userName);
                    window.location.href = "index.html";
                    return false;
                } else if (result.state == 5) {
                    alert(result.message);
                    localStorage.setItem('userID', userID);
                    localStorage.setItem('userName', result.data.userName);
                    window.location.href = "admin/index.html";
                } else {
                    alert(result.message);
                }
            }
        });
    });

    $("#forget").click(function () {
        var email = $("#forget_email").val();
        if (email == null || email == undefined || email == "") {
            alert("邮箱不能为空！");
            return false;
        }
        //正则匹配邮箱
        var myReg = /^[a-zA-Z0-9_-]+@([a-zA-Z0-9]+\.)+(com|cn|net|org)$/;
        if (!myReg.test(email)) {
            alert("邮箱格式不正确！");
            $("#email").focus();
            return false;
        }

        $.post("common/forgetPassword", {"email": email}, function (result) {
            if (result.state == 0) {
                $('#myModal').modal('hide');
                alert(result.message);
            } else {
                $('#myModal').modal('hide');
                alert(result.message);
            }
        });
    });
});



