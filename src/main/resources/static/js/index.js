$(function () {

/*init*/
        var pageNum=1;
        getPage(pageNum);
        $(".previous").hide();

   /*上一页*/
    $(".previous a").click(function () {
        pageNum--;
        if(pageNum<=0)
            pageNum=1;
        getPage(pageNum);
    });
    /*下一页*/
      $(".next a").click(function () {
        pageNum++;
        getPage(pageNum);
    });

    /*分页获取*/
    function getPage(pageNum) {
            $.ajax({
                type: "GET",
                url: "/page?pageNum="+pageNum,
                success: function (result) {
                    $(".post").html("");
                    $(".next a").show();
                    for(var i=0;i<result.pageSize;i++)
                    {
                        $(".post").append("<div id=post-card"+i+" class='post-card'></div>");
                        $("#post-card"+i).html(" <a href='/article.html?articleId="+result.list[i].id+"'>"+"<h3 class=\"post-title\">"+result.list[i].title+"</h3>\n" + "</a>");
                    }
                    if(result.pageSize<3)
                    {
                        $(".next a").hide();
                        $(".post").append("<h1>后面没有内容啦。</h1>");
                    }
                    $("#pageNum").html("第"+pageNum+"页")
                    if(pageNum>1)
                    {
                        $(".previous").show();
                    }
                    else $(".previous").hide();
                },
                //请求失败，包含具体的错误信息
                error: function (e) {
                    console.log(e.status);
                    console.log(e.responseText);
                }
            });
        }
});