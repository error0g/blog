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

    var articleId = getQueryVariable("articleId");

    // 获取文章信息
    $.ajax({
        type: "get",
        url: "/article/" + articleId,
        dataType: "json",
        success: function (json) {
            $("#articleTitle").html(json.title);
            $("#articleCreateBy").html(json.createDate);
            $("#articleContent").html(json.content);
            $("#articleKind").html(json.kindName);
        }
    });
}