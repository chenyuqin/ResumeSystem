$(function () {
    var userID = getUrlVars()["userID"];
    $.ajax({
        url: "resume/preview",
        data: {"userID": userID},
        type: "get",
        dataType: "JSON",
        success: function (result) {
            if (result.state == 1) {
                return false;
            } else {
                //TODO 懒得找方法了，一个一个写
                $("*[name='userName']").val(result.data.userName);
                $("*[name='sex']").val(result.data.s_sex);
                $("*[name='birthday']").val(result.data.birthday);
                $("*[name='experience']").val(result.data.experience);
                $("*[name='phone']").val(result.data.phone);
                $("*[name='education']").val(result.data.education);
                $("*[name='email']").val(result.data.email);
                $("*[name='nativePlace']").val(result.data.nativePlace);
                $("*[name='startTime']").val(result.data.startTime);
                $("*[name='endTime']").val(result.data.endTime);
                $("*[name='school']").val(result.data.school);
                $("*[name='major']").val(result.data.major);
                $("*[name='description']").val(result.data.description);
                $("*[name='position']").val(result.data.position);
                $("*[name='salary']").val(result.data.salary);
                $("*[name='ondutytime']").val(result.data.ondutytime);
                $("*[name='workstyle']").val(result.data.w_workstyle);
                for (var i = 0; i < result.data.workexperiences.length; i++) {
                    $("#project").append(
                        '    <div class="layui-form-item">' +
                        '        <tr>' +
                        '        <td>' +
                        '        <label class="layui-form-label" style="color:green">' +
                        '          项&nbsp;&nbsp;目&nbsp;&nbsp;名' +
                        '        </label>' +
                        '        <div class="layui-input-block " style="width: 22.5%" >' +
                        '            <input type="text" name="project" class="layui-input" value="' + result.data.workexperiences[i].projectName + '" disabled="disabled">' +
                        '        </div>' +
                        '      </td>' +
                        '    </tr>' +
                        '</div>' +
                        '<div class="layui-form-item">' +
                        '      <tr>' +
                        '        <td>' +
                        '        <label class="layui-form-label">' +
                        '          项目描述' +
                        '        </label>' +
                        '        <div class="layui-input-block " style="width: 60.5%" >' +
                        '            <textarea name="projectDesc"  class="layui-textarea" disabled="disabled">' + result.data.workexperiences[i].projectDesc +
                        '            </textarea>' +
                        '        </div>' +
                        '      </td>' +
                        '    </tr>' +
                        '</div>' +
                        '<div class="layui-form-item">' +
                        '      <tr>' +
                        '        <td>' +
                        '        <label class="layui-form-label">' +
                        '          责任描述' +
                        '        </label>' +
                        '        <div class="layui-input-block " style="width: 60.5%" >' +
                        '            <textarea name="roleDesc"  class="layui-textarea" disabled="disabled">' + result.data.workexperiences[i].roleDesc +
                        '            </textarea>' +
                        '        </div>' +
                        '      </td>' +
                        '    </tr>' +
                        '</div>');
                }

                for (var i = 0; i < result.data.skills.length; i++) {
                    var percent;
                    if (result.data.skills[i].value == "一般")
                        percent = 25;
                    else if (result.data.skills[i].value == "良好")
                        percent = 50;
                    else if (result.data.skills[i].value == "熟练")
                        percent = 75;
                    else
                        percent = 100;
                    $("#skill").append(
                        '<div class="layui-form-item">' +
                        '   <tr>' +
                        '       <td>' +
                        '           <label class="layui-form-label">' + result.data.skills[i].name +
                        '           </label>' +
                        '           <div class="layui-input-block" style="width: 60.5%;margin-top:10px" >' +
                        '               <div class="layui-progress layui-progress-big" lay-showPercent="yes">' +
                        '                   <div class="layui-progress-bar" lay-percent="' + percent + '%" style="width: ' + percent + '%;">' +
                        '                       <span class="layui-progress-text">' + percent + '%</span>' +
                        '                   </div>' +
                        '               </div>' +
                        '           </div>' +
                        '       </td>' +
                        '   </tr>' +
                        '</div>');
                }
                $("*[name='selfAppraisal']").val(result.data.selfAppraisal);
                if (result.data.status == 0) {

                } else if (result.data.status == 1) {
                    $("#Approve").css("background-color", "gray");
                    $("#Approve").attr("disabled", "true");
                    $("#Reject").css("background-color", "gray");
                    $("#Reject").attr("disabled", "true");
                    $("#footer").append(
                        '<span style="color: green;font-size: 14px;">&nbsp;*该简历已[通过]，无法再次操作!</span>'
                    );
                } else if (result.data.status == 2) {
                    $("#Approve").css("background-color", "gray");
                    $("#Approve").attr("disabled", "true");
                    $("#Reject").css("background-color", "gray");
                    $("#Reject").attr("disabled", "true");
                    $("#footer").append(
                        '<span style="color: red;font-size: 14px;">&nbsp;*该简历已[拒绝]，无法再次操作!</span>'
                    );
                }
            }
        }
    });

    $("#Approve").click(function () {
        $.ajax({
            url: "resume/operatorStatus",
            data: {"userID": userID, "status": 1},
            type: "post",
            dataType: "JSON",
            success: function (result) {
                alert(result.message);
                window.location.reload();
                parent.location.reload();
            }
        });
    });

    $("#Reject").click(function () {
        $.ajax({
            url: "resume/operatorStatus",
            data: {"userID": userID, "status": 2},
            type: "post",
            dataType: "JSON",
            success: function (result) {
                alert(result.message);
                window.location.reload();
                parent.location.reload();
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