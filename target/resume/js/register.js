$(function () {
    $("#register").click(saveUser);
    $("#userID").blur(function(){
        var userID = $("#userID").val();
        if(userID == null || userID == undefined || userID == "")
            return false;
        $.ajax({
            url : "common/checkUserID",
            data : {"userID":userID},
            type : "get",
            dataType : "JSON",
            success : function(result) {
                if (result.state == 1) {
                    alert("此ID已被注册，请重新输入！");
                    $("#userID").val("");
                    $("#userID").focus();
                }
            }
        });
    });

    $("#email").blur(function(){
        var email = $("#email").val();
        if(email == null || email == undefined || email == "")
            return false;
        $.ajax({
            url : "common/checkEmail",
            data : {"email":email},
            type : "get",
            dataType : "JSON",
            success : function(result) {
                if (result.state == 1) {
                    alert("此邮箱已被注册，请重新输入！");
                    $("#email").val("");
                    $("#email").focus();
                }
            }
        });
    });
});

function saveUser() {
    var userName=$("#userName").val();
    var phone=$("#phone").val();
    var email=$("#email").val();
    var sex=$("input[type='radio']:checked").val();
    var userID=$("#userID").val();
    var password=$("#password").val();
    var re_password=$("#re_password").val();

    if(password != re_password){
        alert("密码不一致！");
        return false;
    }
    //正则匹配邮箱
    var myReg=/^[a-zA-Z0-9_-]+@([a-zA-Z0-9]+\.)+(com|cn|net|org)$/;
    if (!myReg.test(email)) {
        alert("邮箱格式不正确！");
        $("#email").focus();
        return false;
    }

    $.ajax({
        url : "user/register",
        data : {"userName":userName,"phone":phone,"email":email,"sex":sex,"userID":userID,"password":password},
        type : "post",
        dataType : "JSON",
        success : function(result) {
            if (result.state == 0) {
                alert(result.message);
                $(":input").val("");
            } else {
                alert(result.message);
            }
        }
    });

}