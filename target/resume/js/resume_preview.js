$(function () {
    var userID = localStorage.getItem("userID");
    $.ajax({
        url: "resume/preview",
        data: {"userID": userID},
        type: "get",
        dataType: "JSON",
        success: function (result) {
            if (result.state == 1) {
                alert("尚未创建简历，请先创建！");
                window.location.href = "resume_create.html";
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
                    $("#deliver").css("background-color", "gray");
                    $("#deliver").attr("disabled", "true");
                    $("#footer").append(
                        '<span style="color: black;font-size: 15px;">*该简历已经投递，短时间内无法再次投递!</span>'
                    );
                } else if (result.data.status == 1) {
                    $("#deliver").css("background-color", "gray");
                    $("#deliver").attr("disabled", "true");
                    $("#footer").append(
                        '<span style="color: green;font-size: 15px;">*该简历已通过，请到邮箱中确认面试时间!</span>'
                    );
                } else if (result.data.status == 2) {
                    $("#deliver").css("background-color", "gray");
                    $("#deliver").attr("disabled", "true");
                    $("#footer").append(
                        '<span style="color: red;font-size: 15px;">*该简历已被拒绝，短时间内无法再次投递!</span>'
                    );
                }
            }
        }
    });

    $("#deliver").click(function () {
        $.ajax({
            url: "resume/deliver",
            data: {"userID": userID},
            type: "post",
            dataType: "JSON",
            success: function (result) {
                alert(result.message);
                window.location.reload();
            }
        });
    });
});