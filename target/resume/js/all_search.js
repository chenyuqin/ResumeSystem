$(function () {

    $("#search").click(function () {
        var keyWord = $("#keyWord").val();
        $.ajax({
            url : "search/all",
            data : {"keyWord":keyWord},
            type : "get",
            dataType : "JSON",
            success : function(result) {

                layui.use('table', function(){
                    var table = layui.table;

                    table.render({
                        elem: '#test'
                        ,data: result.data
                        ,cellMinWidth: 50 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
                        ,cols: [[
                            {field:'userID', title: 'ID', width:80, align: 'center'}
                            ,{field:'userName', title: '用户名', width:115, align: 'center'}
                            ,{field:'sex', title: '性别', width:70, align: 'center',templet:'#sex_view'}
                            ,{field:'phone', title: '电话',width:130, align: 'center'}
                            ,{field:'email', title: '邮箱',width:170, align: 'center'} //minWidth：局部定义当前单元格的最小宽度，layui 2.2.1 新增
                            ,{field:'education', title: '学历', width:100, align: 'center'}
                            ,{field:'experience', title: '工龄', width:100, sort: true, align: 'center'}
                            ,{field:'selfAppraisal', title: '个人简介', align: 'center'}
                            ,{field:'status', title: '状态', width:80, align: 'center', templet:'#view'}
                            ,{align:'center', toolbar: '#barDemo', width:80}
                        ]]
                        ,even: true
                        ,page: true
                    });

                    //监听工具条
                    table.on('tool(demo)', function (obj) {
                        var data = obj.data;
                        if (obj.event === 'detail') {
                            var userID = data.userID;
                            // $("#detail").attr("onclick", "WeAdminShow('查看简历','preview.html?userID=" + userID + "')");
                            WeAdminShow("查看简历", "preview.html?userID=" + userID);
                        }
                    });

                });
            }
        });

    });
});
