$(function () {
    $.ajax({
        url : "user/userList",
        data : {},
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
                        {field:'userID', title: 'User ID', width:80, align: 'center'}
                        ,{field:'userName', title: '用户名', width:120, align: 'center'}
                        ,{field:'s_sex', title: '性别', width:80, align: 'center'}
                        ,{field:'phone', title: '电话',width:150, align: 'center'}
                        ,{field:'email', title: '邮箱',width:180, align: 'center'} //minWidth：局部定义当前单元格的最小宽度，layui 2.2.1 新增
                        ,{field:'description', title: '个人简介', align: 'center'}
                        ,{field:'create_time', title: '创建时间', sort: true, align: 'center'}
                        ,{field:'activeStatus', title: '是否激活', width:100, align: 'center'}
                        ,{field:'isDeliver', title: '是否投递简历', width:120, align: 'center'}
                    ]]
                    ,even: true
                    ,page: true
                });

            });
        }
    });

});