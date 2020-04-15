
var flag=true;
var pageNum=1;
var defaultcart;
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
                var top="";
                if(result.list[i].top==true)
                {
                    top="<span style='color: red'>[置顶]</span>"
                }
                $(".post").append("<div id=post-card-"+result.list[i].id+" class='post-card'></div>");

                $("#post-card-"+result.list[i].id).html("" +
                    " <span class=\"post-time\">"+result.list[i].createDate+"</span>" +
                    "<a href='/article.html?articleId="+result.list[i].id+"'>"+
                    "<h3 class=\"post-title\">"+result.list[i].title+top+"</h3>\n" +
                    "</a>"+
                    "<div class='post-content'>"+result.list[i].content+"</div>"
                );
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
$(function () {

/*init*/
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
    $.get("config.java", function(result){
        $(".aboutme pre code").html(result);
    });
});

function aboutMe() {

    if(flag)
    {
        $("#about-me").html("<i class=\"fa fa-reply\"></i>Home");
        $(".col").hide();
        $(".aboutme").show();
        flag=false;
    }
    else{
        $("#about-me").html("<i class=\"fa fa-paper-plane-o\"></i>About Me");
        $(".aboutme").hide();
        $(".col").show();
        flag=true;
    }

}
