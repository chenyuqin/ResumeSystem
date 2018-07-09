$(function () {
    $("#submit").click(function () {
        var password = $("#password").val();
        var newpassword = $("#newpassword").val();
        var repassword = $("#repassword").val();
        var userID = localStorage.getItem("userID");
        if (newpassword != repassword) {

            alert("两次密码不一致,请重新输入!");
            $("#re_password").focus();
            return false;
        }
        $.ajax({
            url: "user/chg_passwd",
            data: {"userID": userID, "password": password, "newpassword": newpassword},
            type: "post",
            dataType: "JSON",
            success: function (result) {
                alert(result.message);
                location.reload();
            }
        });

    });

});