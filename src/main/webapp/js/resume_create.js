var projectCount = 0;

$(function () {

    var userID = localStorage.getItem("userID");
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
                if (result.data.s_sex == '女') {
                    $("input[type='radio']").eq(1).attr("checked", true);
                }
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
                if (result.data.w_workstyle == '实习') {
                    $("*[name='workstyle']").val(0);
                } else {
                    $("*[name='workstyle']").val(1);
                }

                for (var i = 0; i < result.data.workexperiences.length; i++) {
                    $("#d1").append(
                        '<div><div class="layui-form-item" >' +
                        '<div class="layui-form-label" style="color:green">项&nbsp;&nbsp;目&nbsp;&nbsp;名</div>' +
                        '<div class="layui-input-block" style="width:40%">' +
                        '<input class="layui-input" type="text" name="projectName" placeholder="" autocomplete="off" value="' + result.data.workexperiences[i].projectName + '">' +
                        '</div></div>' +

                        '<div class="layui-form-item" style="width:80%">' +

                        '<div class="layui-form-label ">项目描述</div>' +
                        '<div class="layui-input-block">' +
                        '<textarea class="layui-textarea" placeholder="" name="projectDesc" type="textarea">' + result.data.workexperiences[i].projectDesc + '</textarea>' +
                        '</div>' +
                        '</div>' +

                        '<div class="layui-form-item" style="width:80%">' +
                        '<div class="layui-form-label ">责任描述</div>' +
                        '<div class="layui-input-block">' +
                        '<textarea class="layui-textarea" placeholder="" name="roleDesc" >' + result.data.workexperiences[i].roleDesc + '</textarea>' +
                        '</div>' +
                        '</div>' + '<br><br></div>');
                }

                for (var i = 0; i < result.data.skills.length; i++) {
                    $("#d2").append('<div><div class="layui-form-item" >' +
                        '<div class="layui-form-label">技&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;能</div>' +
                        '<div class="layui-input-block" style="width:40%">' +
                        '<input class="layui-input" type="text" name="name" placeholder="" autocomplete="off" value="' + result.data.skills[i].name + '">' +
                        '</div></div>' +

                        '<div class="layui-form-item" >' +
                        '<div class="layui-form-label">掌握程度</div>' +
                        '<div class="layui-input-block" style="width:40%">' +
                        '<input class="layui-input" type="text" name="value" placeholder="" value="' + result.data.skills[i].value + '">' +
                        '</div>' +

                        '</div>' + '<br></div>'
                    );
                }
                $("*[name='selfAppraisal']").val(result.data.selfAppraisal);

            }
        }
    });

    $("#submit").click(function () {
        var userID = localStorage.getItem("userID");
        $.ajax({
            url: "resume/create",
            data: {"params": $("form").serialize(), "userID": userID},
            type: "get",
            dataType: "JSON",
            success: function (result) {
                alert("简历创建/修改成功!");
            }
        });
    });

    $("#bt11").click(function () {
        projectCount++;
        $("#d1").append('<div><div class="layui-form-item" >' +
            '<div class="layui-form-label" style="color:green">项&nbsp;&nbsp;目&nbsp;&nbsp;名</div>' +
            '<div class="layui-input-block" style="width:40%">' +
            '<input class="layui-input" type="text" name="projectName" placeholder="请输入项目名称" autocomplete="off">' +
            '</div></div>' +

            '<div class="layui-form-item" style="width:80%">' +

            '<div class="layui-form-label ">项目描述</div>' +
            '<div class="layui-input-block">' +
            '<textarea class="layui-textarea" placeholder="请描述项目的基本信息和内容" name="projectDesc" type="textarea"></textarea>' +
            '</div>' +
            '</div>' +

            '<div class="layui-form-item" style="width:80%">' +
            '<div class="layui-form-label ">责任描述</div>' +
            '<div class="layui-input-block">' +
            '<textarea class="layui-textarea" placeholder="请描述自己在项目中所扮演的角色和承担的责任" name="roleDesc" ></textarea>' +
            '</div>' +

            '</div>' + '<br><br></div>'
        );
    });


    $("#bt12").click(function () {
        $("#d1").children("div:last-child").remove();

    });

    $("#bt21").click(function () {
        $("#d2").append('<div><div class="layui-form-item" >' +
            '<div class="layui-form-label">技&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;能</div>' +
            '<div class="layui-input-block" style="width:40%">' +
            '<input class="layui-input" type="text" name="name" placeholder="请输入技能名称" autocomplete="off">' +
            '</div></div>' +

            '<div class="layui-form-item" >' +
            '<div class="layui-form-label">掌握程度</div>' +
            '<div class="layui-input-block" style="width:40%">' +
            '<input class="layui-input" type="text" name="value" placeholder="一般 / 良好 / 熟练 / 精通">' +
            '</div>' +

            '</div>' + '<br></div>'
        );
    });

    $("#bt22").click(function () {
        $("#d2").children("div:last-child").remove();

    });
});