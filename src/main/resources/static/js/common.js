$(function (){
    $(document).ready(function (){
        $.ajax({
            url: "/users/get_by_id",
            type: "POST",
            dataType: "JSON",
            success: function (json){
                if (json.state == 200){
                    let a = document.getElementById("login-user");
                    a.innerText = "HI "+json.data.username;
                    a.href = "userdata.html";

                }else {
                    alert("加载用户信息失败");
                }
            },
            error: function (xhr){
                alert("加载用户信息时时遇到未知错误");
            }
        });
    })
})
