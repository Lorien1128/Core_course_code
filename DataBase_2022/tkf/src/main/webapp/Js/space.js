function initPage() {
    //TODO: query info and write in
    layui.use(['rate'], function(){
        const rate = layui.rate;
        rate.render({
            elem: '#rate'
            ,value: 3.5
            ,readonly: true
        });
    });
}

function beVIP() {
    if (document.getElementById("vip").getAttribute("value") === "是") {
        document.getElementById("vip").setAttribute("value", "否");
        document.getElementById("vip-btn").innerHTML = "充值VIP";
        //TODO
    }
    else {
        document.getElementById("vip").setAttribute("value", "是");
        document.getElementById("vip-btn").innerHTML = "取消VIP";
        //TODO
    }
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
        , id: 'cz' + type //防止重复弹出
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
    //TODO
    const pre = parseInt(document.getElementById("account").getAttribute("value"));
    const aft = pre + parseInt(money);
    document.getElementById("account").setAttribute("value", aft.toString());
    const layer = layui.layer;
    layer.open({
        type: 1
        , title: '充值'
        , offset: 'auto'
        , id: 'succeed'
        , content: '<div style="padding: 20px 100px; text-align: center">充值成功！</div>'
        , btn: '确定'
        , btnAlign: 'c'
        , shade: 0
        , yes: function () {
            layer.closeAll();
        }
    })
}

function save() {

}

layui.use(['upload', 'element', 'layer'], function () {
    const $ = layui.jquery
        , upload = layui.upload
        , element = layui.element
        , layer = layui.layer;

    //常规使用 - 普通图片上传
    const uploadInst = upload.render({
        elem: '#upload-btn'
        ,url: 'upload-servlet/post' //此处用的是第三方的 http 请求演示，实际使用时改成您自己的上传接口即可。
        , before: function (obj) {
            //预读本地文件示例，不支持ie8
            obj.preview(function (index, file, result) {
                $('#upload-img').attr('src', result); //图片链接（base64）
            });

            element.progress('upload-progress', '0%'); //进度条复位
            layer.msg('上传中', {icon: 16, time: 0});
        }
        , done: function (res) {
            //TODO
            $('#upload-text').html(''); //置空上传失败的状态
        }
        , error: function () {
            //演示失败状态，并实现重传
            const uploadText = $('#upload-text');
            uploadText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
            uploadText.find('.demo-reload').on('click', function () {
                uploadInst.upload();
            });
        }
        //进度条
        , progress: function (n) {
            element.progress('upload-progress', n + '%'); //可配合 layui 进度条元素使用
            if (n === 100) {
                layer.msg('上传完毕', {icon: 1});
            }
        }
    });
});