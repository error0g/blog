

$(function(){
/*注销用户*/
$("#quit").click(function () {
    $.ajax({
        type: "POST",
        url: "/quit",
        success: function (code) {
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
        })

});