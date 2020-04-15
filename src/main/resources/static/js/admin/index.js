/*后台控制中心*/
$(function(){

    $.ajax({
        type: "POST",
        url:"/admin/sys/Info",
        success:function (result) {
            $("#articleNum").html(result.data.articleNum+"篇");
            $("#sortNum").html(result.data.sortNum+"种");
            for(var i=0;i<result.data.article.length;i++)
            {
                //前五条文章列表
                $("#articleTop").append("<a href='/article.html?articleId="+result.data.article[i].id+"' class=\"list-group-item\">"+result.data.article[i].title+"\n" + "</a>")
            }
        }
    });

});
