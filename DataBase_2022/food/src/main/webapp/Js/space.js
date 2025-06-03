let role;
let id;
const kinds = ["火锅", "中餐", "早点", "快餐"];

function initPage() {
    if (getQueryVariable("uid") != null) {
        role = "diner";
        id = parseInt(getQueryVariable("uid"));
        $.ajax({
            url: "space-servlet",
            type: "post",
            dataType: "json",
            data: {"action": "initPage", "uid": id},
            success: function (data) {
                document.getElementById("name")
                    .setAttribute("value", data["name"]);
                document.getElementById("password")
                    .setAttribute("value", data["password"]);
                document.getElementById("phone")
                    .setAttribute("value", data["phone"]);
                document.getElementById("pos-x")
                    .setAttribute("value", data["x"]);
                document.getElementById("pos-y")
                    .setAttribute("value", data["y"]);
                document.getElementById("account")
                    .setAttribute("value",data["account"]);
                document.getElementById("vip")
                    .setAttribute("value", data["vip"] === false ? "否" : "是");
                document.getElementById("diner-info").style.display = "";
            }
        });
    }
    else if (getQueryVariable("rid") != null) {
        role = "restaurant";
        id = parseInt(getQueryVariable("rid"));
        document.getElementById("upload-img").src = "./img/restaurant/" + id + ".jpg";
        $.ajax({
            url: "space-servlet",
            type: "post",
            dataType: "json",
            data: {"action": "initPage", "rid": id},
            success: function (data) {
                document.getElementById("name")
                    .setAttribute("value", data["name"]);
                document.getElementById("password")
                    .setAttribute("value", data["password"]);
                document.getElementById("phone")
                    .setAttribute("value", data["phone"]);
                document.getElementById("pos-x")
                    .setAttribute("value", data["x"]);
                document.getElementById("pos-y")
                    .setAttribute("value", data["y"]);
                $("#kind").val(data["kind"]);
                document.getElementById("restaurant-info").style.display = "";
                layui.use(['rate'], function(){
                    const rate = layui.rate;
                    rate.render({
                        elem: '#rate'
                        ,value: parseFloat(data["rate"])
                        ,readonly: true
                    });
                });
            }
        });
    }
    else {
        role = "deliverer";
        id = getQueryVariable("tid");
        $.ajax({
            url: "space-servlet",
            type: "post",
            dataType: "json",
            data: {"action": "initPage", "tid": id},
            success: function (data) {
                document.getElementById("name")
                    .setAttribute("value", data["name"]);
                document.getElementById("password")
                    .setAttribute("value", data["password"]);
                document.getElementById("phone")
                    .setAttribute("value", data["phone"]);
                document.getElementById("pos-x")
                    .setAttribute("value", data["x"]);
                document.getElementById("pos-y")
                    .setAttribute("value", data["y"]);
                document.getElementById("salary")
                    .setAttribute("value", data["salary"]);
                document.getElementById("onWork").innerHTML =
                    data["state"] === "on" ? "点击下班" : "点击上班";
                document.getElementById("deliverer-info").style.display = "";
            }
        });
    }
}

function beVIP() {
    const preState = document.getElementById("vip").getAttribute("value");
    if (preState === "是") {
        document.getElementById("vip").setAttribute("value", "否");
        document.getElementById("vip-btn").innerHTML = "充值VIP";
    }
    else {
        document.getElementById("vip").setAttribute("value", "是");
        document.getElementById("vip-btn").innerHTML = "取消VIP";
    }
    $.ajax({
        url: "space-servlet",
        type: "post",
        dataType: "json",
        data: {"action": "changeVIP", "uid": id},
        success: function (data) {
            console.log(data);
            if (data["success"] === true) {
                if (preState === "是") {
                    layer.msg("取消成功", {icon:1});
                }
                else {
                    layer.msg("充值成功", {icon:1});
                }
            }
            else {
                layer.msg("余额不足！", {icon:5});
            }
        }
    });
}

function openWindow() {
    const $ = layui.jquery, layer = layui.layer;
    console.log("debug")
    const othis = $(this);
    const type = othis.data('type')
    layer.open({
        type: 1
        , title: '充值'
        , offset: type
        , id: 'cz' + type
        , content: '<div style="padding: 20px 100px; text-align: center">' +
                    '<div class="layui-btn-container">\n' +
            '    <button type="button" class="layui-btn" onclick="addToAccount(100)" style="margin: 10px">' +
            '<span style="font-family: \'仓耳渔阳体 W04\', serif">100 元</span></button>\n' +
            '    <button type="button" class="layui-btn" onclick="addToAccount(50)" style="margin: 10px">' +
            '<span style="font-family: \'仓耳渔阳体 W04\', serif">5 0 元</span></button>\n' +
            '</div>\n' +
            '<div class="layui-btn-container">\n' +
            '    <button type="button" class="layui-btn" onclick="addToAccount(20)" style="margin: 10px">' +
            '<span style="font-family: \'仓耳渔阳体 W04\', serif">2 0 元</span></button>\n' +
            '    <button type="button" class="layui-btn" onclick="addToAccount(10)" style="margin: 10px">' +
            '<span style="font-family: \'仓耳渔阳体 W04\', serif">1 0 元</span></button>\n' +
            '</div></div>'
        , btn: '关闭'
        , btnAlign: 'c'
        , shade: 0
        , yes: function () {
            layer.closeAll();
        }
    });
}

function addToAccount(money) {
    $.ajax({
        url: "space-servlet",
        type: "post",
        dataType: "json",
        data: {"action": "addToAccount", "uid": id, "money": money},
        success: function (data) {
            if (data["success"] === true) {
                const pre = parseInt(document.getElementById("account").getAttribute("value"));
                const aft = pre + parseInt(money);
                document.getElementById("account").setAttribute("value", aft.toString());
                const layer = layui.layer;
                layer.msg('充值成功！', {icon: 1, time: 1000});
            }
        }
    });
}

function save() {
    const params = $.param({'action': "save"}) + '&' + $.param({'role': role}) + '&' + $.param({'id': id})
            + '&' + $('#personal-info-form').serialize();
    const layer = layui.layer;
    layer.open({
        type: 1
        , title: '请再次确认'
        , offset: 'auto'
        , id: 'save'
        , content: "<h3 style='text-align: center'>确认保存吗？</h3>"
        , btn: ['取消', '确认保存']
        , btnAlign: 'c'
        , shade: 0
        , yes: function () {
            layer.closeAll();
        }
        , btn2: function () {
            $.ajax({
                url: "space-servlet",
                type: "post",
                dataType: "json",
                data: params,
                success: function (data) {
                    if (data["success"] === true) {
                        layer.msg("保存成功", {icon:1});
                        setTimeout(() => location.reload(), 1000);
                    }
                }
            });
        }
    });

}

function withDraw() {
    const layer = layui.layer;
    layer.open({
        type: 1
        , title: '请再次确认'
        , offset: 'auto'
        , id: 'withdraw'
        , content: "<h3 style='text-align: center'>确认注销吗？</h3>"
        , btn: ['取消', '确认注销']
        , btnAlign: 'c'
        , shade: 0
        , yes: function () {
            layer.closeAll();
        }
        , btn2: function () {
            $.ajax({
                url: "space-servlet",
                type: "post",
                dataType: "json",
                data: {"action": "withdraw", "role": role, "id": id},
                success: function (data) {
                    if (data["success"] === true) {
                        layer.msg("注销成功", {icon:1});
                        setTimeout(() => window.location.replace("login.html"), 1500);
                    }
                }
            });
        }
    });
}

layui.use(['upload', 'element', 'layer'], function () {
    const $ = layui.jquery
        , upload = layui.upload
        , element = layui.element
        , layer = layui.layer;

    const uploadInst = upload.render({
        elem: '#upload-btn'
        , url: 'upload-servlet'
        , auto: false
        , bindAction: '#img-submit-btn'
        , choose: function (obj) {
            this.files = obj.pushFile();
            obj.preview(function (index, file, result) {
                const format = file.name.substr(file.name.lastIndexOf('.'));
                obj.resetFile(index, file, getQueryVariable("rid") + format);
                $('#img-submit-btn').click();
                document.getElementById("upload-msg").style.display = "";
                $('#upload-img').attr('src', result);
            });
        }
        , before: function () {
            element.progress('upload-progress', '0%'); //进度条复位
            layer.msg('上传中', {icon: 16, time: 0});
        }
        , done: function (res, index) {
            if (res["code"] === -1) {
                layer.msg('文件错误');
            }
            else {
                delete this.files[index];
            }
            $('#upload-text').html(''); //置空上传失败的状态
        }
        , error: function () {
            //演示失败状态，并实现重传
            const uploadText = $('#upload-text');
            document.getElementById("upload-msg").style.display = "none";
            uploadText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
            uploadText.find('.demo-reload').on('click', function () {
                uploadInst.upload();
            });
        }
        //进度条
        , progress: function (n) {
            element.progress('upload-progress', n + '%');
            if (n === 100) {
                layer.msg('上传完毕', {icon: 1});
            }
        }
    });
});

function changeWorkState() {
    let msg;
    if (document.getElementById("onWork").innerHTML === "点击下班") {
        document.getElementById("onWork").innerHTML = "点击上班";
        msg = "下班成功";
    }
    else {
        document.getElementById("onWork").innerHTML = "点击下班";
        msg = "上班成功";
    }
    $.ajax({
        url: "space-servlet",
        type: "post",
        dataType: "json",
        data: {"action": "changeWorkState", "tid": id},
        success: function (data) {
            if (data["success"] === true) {
                layer.msg(msg, {icon:1});
            }
        }
    });
}