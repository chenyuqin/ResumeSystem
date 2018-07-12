$(function () {
    $.ajax({
        url : "resume/resumeList",
        data : {"status":1},
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
                        {field:'userID', title: 'ID', width:70, align: 'center'}
                        ,{field:'userName', title: '用户名', width:110, align: 'center'}
                        ,{field:'s_sex', title: '性别', width:65, align: 'center'}
                        ,{field:'phone', title: '电话',width:140, align: 'center'}
                        ,{field:'email', title: '邮箱',width:185, align: 'center'} //minWidth：局部定义当前单元格的最小宽度，layui 2.2.1 新增
                        ,{field:'experience', title: '工龄', width:60, align: 'center'}
                        ,{field:'position', title: '求职意向', width:150, align: 'center'}
                        ,{field:'ondutytime', title: '到岗时间', width:110, align: 'center'}
                        ,{field:'deliver_time', title: '投递时间', width:190, sort: true, align: 'center'}
                        ,{title: '状态', align: 'center', templet: '#view'}
                        ,{align:'center', toolbar: '#barDemo'}
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
