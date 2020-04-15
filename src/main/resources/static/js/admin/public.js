/*后台公有方法*/

$(function(){
/*注销用户*/
$("#quit").click(function () {
    $.ajax({
        type: "POST",
        url: "/quit",
        success: function () {
            window.location.reload();
        },
        error: function (e) {
            console.log(e.status);
            console.log(e.responseText);
        }
    });
});
    /*一言骚话*/
    fetch('https://v1.hitokoto.cn')
        .then(function (res){
            return res.json();
        })
        .then(function (data) {
            var hitokoto = document.getElementById('text-network');
            hitokoto.innerText = data.hitokoto;
        })
        .catch(function (err) {
            console.error(err);
        });

    /*修改密码-show*/
    var SetPassword=false;
    $(".btn-setpassword").click(function () {
        if(SetPassword==false)
        {
            $(".btn-setpassword").html("收起");
            $(".set-pws").show(500);
            SetPassword=true;
        }else {
            $(".btn-setpassword").html("修改密码");
            $(".set-pws").hide(500);
            SetPassword=false;
        }

    });

    /*修改密码*/
    $("#setPsw").click(function () {
        var username=$("#user").text();
        var password= $("#password").val();
        var user={username:username,password:password};
        $.ajax({
            type: "PUT",
            url:"/admin/modifyPwd",
            dataType: "json",
            contentType: " application/json",
            data: JSON.stringify(user),
            success:function (result) {
                if(result.code==200)
                {
                    swal('成功!', '修改成功', 'success')
                }
                else{
                    swal('失败!', '系统错误', 'error')
                }
            }
        });
    });

    $.ajax({
            type: "POST",
            url: "/admin/sys/Info",
            success: function (result) {
                $("#user").html(result.data.user);
                $("#lastTime").html(result.data.lastTime);
            }
        }
    );
});

