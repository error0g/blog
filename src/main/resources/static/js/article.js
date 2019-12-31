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

    // 获取文章信息
    var articleId = getQueryVariable("articleId");
    $.ajax({
        type: "get",
        url: "/article/" + articleId,
        dataType: "json",
        success: function (json) {
            $("#articleTitle").html(json.title);
            $("#articleCreateBy").html(json.createDate);
            $("#articleContent").text(json.content);
            MarkDown();
        }
    });
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