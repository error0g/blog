/*获取文章*/

window.onload = function () {
//获取url的参数
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

    /*获取文章信息*/
    var articleId = getQueryVariable("articleId");
    $.ajax({
        type: "get",
        url: "/article/" + articleId,
        dataType: "json",
        success: function (result) {
            $("#articleTitle").html(result.data.title);
            $("#articleCreateBy").html(result.data.createDate);
            $("#articleContent").text(result.data.content);
            $("#articleKind").html(result.data.kindName)
            $("#articlTraffic").html(result.data.traffic);
            MarkDown();
        }
    });
    /*markdown 渲染插件*/
    function MarkDown() {   
        editormd.markdownToHTML("article-body", {
            htmlDecode: "style,script,iframe",
            emoji: true,
            taskList: true,
            tex: true,
            flowChart: true,
            sequenceDiagram: true
        });
    }
}