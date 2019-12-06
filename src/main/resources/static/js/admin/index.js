$(function(){

/*修改密码*/
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
});
