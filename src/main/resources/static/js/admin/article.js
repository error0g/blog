$(function () {

    /*init*/
    var QueryNum=sessionStorage.getItem("pageNum");
    var pageNum=QueryNum==null?1:parseInt(QueryNum);

    $('#article-table').bootstrapTable({
        url: '/admin/articlePage',
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
                var pageSize = $('#article-table').bootstrapTable('getOptions').pageSize;
                var pageNumber = $('#article-table').bootstrapTable('getOptions').pageNumber;
                return pageSize * (pageNumber - 1) + index + 1;
            }
        }, {
            field: 'title',
            title: '标题'
        },
            {
                field: 'createDate',
                title: '创建时间'
            },
            {
                field: 'traffic',
                title: '点击量'
            },
            {
                field: 'kindName',
                title: '分类'
            },
            {
                field: 'id',
                align: 'center',
                width: 250,
                valign: 'middle',
                title: '操作',
                formatter:function actionFormatter(value, row, index) {
                    var id = value;
                    var result =    "<button type=\"button\" class=\"btn btn-success \"  onclick=\"editArticle('" + id + "')\">编辑</button>\n" +
                        " <button type=\"button\" class=\"btn btn-danger btn-primary\"  onclick=\"delArticle('" + id + "')\">删除</button>\n" ;
                    return result;
                }
            },
        ],
    });

    $("#remove").on("click", function(){
        swal({
            title: '确定删除吗？',
            text: '你将无法恢复它！',
            type: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '确定删除！',
        }).then(function(result){
            if (result.value) {
                var rows = $("#article-table").bootstrapTable('getSelections');
                if (rows.length == 0)
                {
                    swal(
                        '已取消！',
                        '请选择需要删除的文章:(',
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
                            url:"/admin/Articles/del",
                            type:"POST",
                            dataType: 'json',
                            data:{strlist:ids.toString()},
                            success:function (result) {
                                if(result.code==200)
                                {
                                    $("#article-table").bootstrapTable('refresh');
                                    swal('删除!', '你的文章已经被删除。', 'success');
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
                    '你的文章是安全的:)',
                    'error'
                );
            }
        })
    });
});



function editArticle(id) {
    window.location.href="/admin/edit.html?id="+id;
}

function delArticle(id) {
    swal({
        title: '确定删除吗？',
        text: '你将无法恢复它！',
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: '确定删除！',
    }).then(function(result){
        console.log(result);
        if (result.value) {
            $.ajax(
                {
                    url:"/admin/Article/del",
                    type:"POST",
                    data:{id:id},
                    success:function (result) {
                        if(result.code==200)
                        {
                            $("#article-table").bootstrapTable('refresh');
                            swal('删除!', '你的文章已经被删除。', 'success');
                        }
                        else {
                            swal('错误!', '系统错误。', 'error');
                        }

                    }
                }
            );
        }
        else if(result.dismiss=="cancel"){
            swal(
                '已取消！',
                '你的文章是安全的:)',
                'error'
            );
        }
    })
}