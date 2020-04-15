$(function () {


    /*init*/
    var QueryNum=sessionStorage.getItem("pageNum");
    var pageNum=QueryNum==null?1:parseInt(QueryNum);
    $('#class-table').bootstrapTable({
        url: '/admin/classPage',
        method: 'post',
        onlyInfoPagination:true,
        sidePagination:"server", //服务器分页
        contentType:'application/x-www-form-urlencoded; charset=UTF-8',
        datatype: 'json',
        pagination:true,  //页脚分页按钮
        onlyInfoPagination:false,// 页脚分页数据
        pageSize: 5, //每页数量
        pageNumber: pageNum, //默认第几页
        dataField: "list", //数据字典
        singleSelect: false, //复选
        queryParams:function(params){ // 请求服务器数据时发送的参数，可以在这里添加额外的  // 查询参数，返回false则终止请求
            return {
                pageNum:(params.offset / params.limit)+1
            }},
        columns: [
            {
                checkbox: true
            },{
                title: '#',
                formatter: function (value, row, index) {
                    var pageSize = $('#class-table').bootstrapTable('getOptions').pageSize;
                    var pageNumber = $('#class-table').bootstrapTable('getOptions').pageNumber;
                    return pageSize * (pageNumber - 1) + index + 1;
                }
            }, {
                field: 'name',
                title: '分类名称'
            },
            {
                field: 'number',
                title: '文章所属数量'
            },
            {
                field: 'createDate',
                title: '创建时间'
            }

        ],
    });

    /*添加分类*/
    $("#addClass").click(function () {
        swal({
            title: '输入分类名称',
            input: 'text',
            confirmButtonText: '提交',
            confirmButtonColor: '#4cd964',
            allowOutsideClick: false
        }).then(function(res) {
            if(res) {
                if(res.value==''&&res.value==null)
                {
                    swal({
                        type: 'warning',
                        title: '请宁添加名称啊 亲~',
                        confirmButtonText: '确定',
                        confirmButtonColor: '#4cd964'
                    });
                }
                else {
                    $.ajax({
                        type:"post",
                        url:"/admin/Article/sort/add",
                        data:{"sortName":res.value},
                        success:function (result) {
                            if(result.code==200)
                            {
                                $("#class-table").bootstrapTable('refresh');
                                swal({
                                    type: 'success',
                                    title: '添加成功',
                                    confirmButtonText: '确定',
                                    confirmButtonColor: '#4cd964'
                                });
                            }
                            else {
                                swal({
                                    type: 'error',
                                    title: '添加失败',
                                    confirmButtonText: '确定',
                                    confirmButtonColor: '#4cd964'
                                });
                            }
                        },
                        error:function () {
                            swal({
                                type: 'error',
                                title: '系统错误',
                                confirmButtonText: '确定',
                                confirmButtonColor: '#4cd964'
                            });
                        }

                    });
                }
            }
        });

    });
    $("#remove").on("click", function(){
        swal({
            title: '确定删除吗？',
            text: '此分类下的所有内容将被删除, 你确认要删除这些分类吗?！',
            type: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '确定删除！',
        }).then(function(result){
            if (result.value) {
                var rows = $("#class-table").bootstrapTable('getSelections');
                if (rows.length == 0)
                {
                    swal(
                        '已取消！',
                        '请选择需要删除的分类:(',
                        'error'
                    );
                }
                else{
                    var ids = new Array();
                    $(rows).each(function() {// 通过获得别选中的来进行遍历
                        ids.push(this.id);// cid为获得到的整条数据中的一列
                    });
                    $.ajax(
                        {
                            url:"/admin/Articles/sort/del",
                            type:"POST",
                            dataType: 'json',
                            data:{strlist:ids.toString()},
                            success:function (result) {
                                if(result.code==200)
                                {
                                    $("#class-table").bootstrapTable('refresh');
                                    swal('删除!', '你的文章和分类已经被删除。', 'success');
                                }
                                else {
                                    swal('错误!', '系统错误。', 'error');
                                }

                            }
                        }
                    );
                }
            }
            else if(result.dismiss=="cancel"){
                swal(
                    '已取消！',
                    '你的文章和分类是安全的:)',
                    'error'
                );
            }
        })
    });
});
