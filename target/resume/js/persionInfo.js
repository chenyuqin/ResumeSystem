$(function () {
    var userID = localStorage.getItem("userID");
    $.ajax({
        url : "common/checkUserID",
        data : {"userID":userID},
        type : "get",
        dataType : "JSON",
        success : function(result) {
            if (result.state == 1) {
                $("#userID").val(result.data.userID);
                $("#userName").val(result.data.userName);
                $("#phone").val(result.data.phone);
                $("#email").val(result.data.email);
                $("#description").val(result.data.description);
                if (result.data.sex == 1) {
                    $("input[name='sex']:eq(1)").attr("checked",'checked');
                }
            }
        }
    });

    $("#submit").click(function () {

        var userID = $("#userID").val();
        var userName = $("#userName").val();
        var phone = $("#phone").val();
        var email = $("#email").val();
        var description = $("#description").val();
        var sex = $("input[name='sex']:checked").val();
        $.ajax({
            url : "user/updateUser",
            data : {"userName":userName,"phone":phone,"email":email,"sex":sex,"userID":userID,"description":description},
            type : "post",
            dataType : "JSON",
            success: function (result) {
                // location.reload();
                alert("修改成功!");
            },
            error: function (result) {
                alert("修改成功!");
                location.reload();
            }
        });

    });

});