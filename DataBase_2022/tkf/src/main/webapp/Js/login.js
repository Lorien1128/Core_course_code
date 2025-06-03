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
    console.log("111");
    const params = $.param({'action': action}) + '&' + $('#login-form').serialize();
    $.ajax({
        url: "login-servlet",
        type: "post",
        dataType: "json",
        data: params,
        success: function (data) {
            console.log("get");
            if (data["succeed"] === "true") {
                alert("登录成功");
            }
            else {
                alert("登录失败");
            }
        }
    });
}