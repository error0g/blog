/*文章编辑*/

function getQueryVariable(variable) {
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i = 0; i < vars.length; i++) {
        var pair = vars[i].split("=");
        if (pair[0] == variable) {
            return pair[1];
        }
    }
    return (false);
}
var articleId = getQueryVariable("id");
$(function () {
    var editor;
    //渲染分类
    $.ajax({
        type: "POST",
        url: "/admin/Article/sort",
        contentType: "application/json;charset=utf-8",
        success: function (result) {
            for (var i = 0; i < result.data.length; i++) {
                $("#sort").append("<option kindId='" + result.data[i].id + "'>" + result.data[i].name + "</option>")
            }
        },
        //请求失败，包含具体的错误信息
        error: function (e) {
            console.log(e.status);
            console.log(e.responseText);
        }
    });

    //获取url的参数
    //文章编辑根据articleId获取数据
    $.ajax({
        type: "get",
        url: "/article/" + articleId,
        dataType: "json",
        success: function (result) {
            $("#title").val(result.data.title);
            $(".content").val(result.data.content);
            $("#sort option[kindId=" + result.data.kindId + "]").prop("selected", true);
            $("#top option[value=" + result.data.top + "]").prop("selected", true);
            editor = editormd("editor", {
                height: 470,
                path: "../../editor/lib/",
                imageUpload: true,
                imageFormats: ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
                imageUploadURL: "uploadfile",
                saveHTMLToTextarea: true,
            });
            $(".saveArticle").html("修改文章")
            $(".saveArticle").attr("onclick","updateArticle();");
        },
        error: function () {
            editor = editormd("editor", {
                height: 470,
                path: "../../editor/lib/",
                imageUpload: true,
                imageFormats: ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
                imageUploadURL: "uploadfile",
                saveHTMLToTextarea: true,
            });
        }
    });
});

function addArticle() {
    var title = $("#title").val();
    var content = $(".content ").val();
    var kindId = $("#sort option:selected").attr("kindId");
    var top = $("#top").val();
    var article = {
        title: title,
        article: article,
        content: content,
        kindId: kindId,
        top:top=="true"?true:false,
    };

    $.ajax({
        type: "POST",
        url: "/admin/write",
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(article),
        success: function (result) {
            if (result.code = 200) {
                swal(
                    '成功!',
                    '已添加文章。',
                    'success'
                ).then(function () {
                    location.reload();
                });
            } else {
                swal(
                    '失败!',
                    '系统错误。',
                    'error'
                );
            }
        },
        //请求失败，包含具体的错误信息
        error: function (e) {
            console.log(e.status);
            console.log(e.responseText);
            location.reload();
        }
    });
}

    function updateArticle() {
        var id =articleId;
        var title = $("#title").val();
        var content = $(".content ").val();
        var kindId = $("#sort option:selected").attr("kindId");
        var top = $("#top").val();
        var article = {
            id:id,
            title: title,
            article: article,
            content: content,
            kindId: kindId,
            top:top=="true"?true:false,
        };

        $.ajax({
            type: "POST",
            url: "/admin/aticle/update",
            dataType: "json",
            contentType: "application/json;charset=utf-8",
            data: JSON.stringify(article),
            success: function (result) {
                if (result.code = 200) {
                    console.log(result)
                    swal(
                        '成功!',
                        '已修改文章。',
                        'success'
                    ).then(function () {
                        location.reload();
                    });
                } else {
                    swal(
                        '失败!',
                        '系统错误。',
                        'error'
                    );
                }
            },
            //请求失败，包含具体的错误信息
            error: function (e) {
                console.log(e.status);
                console.log(e.responseText);
                location.reload();
            }
        });}

