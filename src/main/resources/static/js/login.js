$(function() {
    $(".login").click(function () {
        var username = $("#username").val();
        var password = $("#password").val();
        $.ajax({
            type: "POST",
            url: "/login",
            data: {"username": username, "password": password},
            success: function (result) {
                if(result==true)
                {
                    window.location='./admin/index';
                }
                else {
                    $('#myModal').modal('show')
                }
                console.log(result);
            },
            //请求失败，包含具体的错误信息
            error: function (e) {
                console.log(e.status);
                console.log(e.responseText);
            }
        });
    });
});