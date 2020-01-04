/*后台控制中心*/
$(function(){
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
                    if(result=="true")
                    {
                        alert("修改成功");
                    }
            }
        });
    });
});
