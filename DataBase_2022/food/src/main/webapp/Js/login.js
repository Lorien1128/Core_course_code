let action = "login";

function changeLoginMode() {
   if (action === "login") {
       document.getElementById("login-title").innerHTML = "用户注册";
       document.getElementById("login-user")
           .setAttribute("placeholder", "请设置您的账号");
       document.getElementById("login-pass")
           .setAttribute("placeholder", "请设置您的密码");
       document.getElementById("login-phone").style = null;
       document.getElementById("login-btn").innerHTML = "注册";
       document.getElementById("login-change-mode").innerHTML = "已有账号？点此登录";
       action = "register";
   }
   else {
       document.getElementById("login-title").innerHTML = "用户登录";
       document.getElementById("login-user")
           .setAttribute("placeholder", "请输入您的账号");
       document.getElementById("login-pass")
           .setAttribute("placeholder", "请输入您的密码");
       document.getElementById("login-phone").style.display = "none";
       document.getElementById("login-btn").innerHTML = "登录";
       document.getElementById("login-change-mode").innerHTML = "没有账号？点此注册";
       action = "login";
   }
}

function login() {
    const layer = layui.layer;
    const params = $.param({'action': action}) + '&' + $('#login-form').serialize();
    const role = $('#role-select option:selected').val();
    // console.log(role);
    $.ajax({
        url: "login-servlet",
        type: "post",
        dataType: "json",
        data: params,
        success: function (data) {
            if (action === "login") {
                if (data["id"] === -1) {
                    layer.msg("未注册！", {icon: 2});
                }
                else if (data["id"] === -2) {
                    layer.msg("密码错误！", {icon: 2});
                }
                else {
                    const id = data["id"];
                    if (role === "diner") {
                        window.open("diner.html?uid=" + id, '_blank');
                    }
                    else if (role === "restaurant") {
                        window.open("restaurant.html?rid=" + id, '_blank');
                    }
                    else {
                        window.open("order.html?tid=" + id, '_blank');
                    }
                }
            }
            else {
                if (data["success"] === true) {
                    layer.msg("注册成功！", {icon: 1});
                    setTimeout(() => location.reload(),1000);
                }
                else {
                    layer.msg("注册失败，账号重复！", {icon: 2});
                }
            }
        }
    });
}