
/*后台登陆请求*/
$(function () {
    $(".login").click(function () {
        var username = $("#username").val();
        var password = $("#password").val();
        $.ajax({
            type: "POST",
            url: "/login",
            data: {"username": username, "password": password},
            success: function (result) {
                if (result.code==200) {
                    window.location = '/admin/index.html';
                } else {
                    swal(
                         '密码错误！',
                        '请检查账号或密码是否有错误!',
                        'error'
                    )
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