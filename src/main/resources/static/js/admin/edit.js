/*文章编辑*/

$(function(){
    /*新文章发布*/
    $("#saveArticle").click(function () {
        var title=$("#title").val();
         var content =editor.getMarkdown();
        var kindId=$("#sort option:selected").attr("kindId");
        var top=$("#top").val();
        var article={
            title:title,
            article:article,
            content:content,
            kindId:kindId,
            top:top,
        };

        $.ajax({
            type: "POST",
            url: "./write",
            dataType: "json",
            contentType: "application/json;charset=utf-8",
            data: JSON.stringify(article),
            success: function (result) {
                if(result==true)
                {
                   alert("yes");
                }
                location.reload();
            },
            //请求失败，包含具体的错误信息
            error: function (e) {
                console.log(e.status);
                console.log(e.responseText);
                location.reload();
            }
        });
    });
});
