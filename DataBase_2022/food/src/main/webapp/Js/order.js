let tid;

function initPage() {
    const uid = getQueryVariable("uid");
    const rid = getQueryVariable("rid");
    tid = getQueryVariable("tid");
    const mode = getQueryVariable("mode");
    if (uid != null) {
        $.ajax({
            url: "order-servlet",
            type: "post",
            dataType: "json",
            data: {"action": "initDinerOrder", "uid": uid},
            success: function (data) {
                const content = getUrContent(data);
                document.getElementById("ur-order-list").innerHTML += content;
                document.getElementById("ur-order-list").style.display = "";
            }
        });
    }
    else if (rid != null) {
        $.ajax({
            url: "order-servlet",
            type: "post",
            dataType: "json",
            data: {"action": "initRestaurantOrder", "rid": rid},
            success: function (data) {
                const content = getUrContent(data);
                document.getElementById("ur-name").innerHTML = "用户名";
                document.getElementById("ur-order-list").innerHTML += content;
                document.getElementById("ur-order-list").style.display = "";
            }
        });
    }
    else {
        if (mode === null) {
            $.ajax({
                url: "order-servlet",
                type: "post",
                dataType: "json",
                data: {"action": "initNewDelivererOrder", "tid": tid},
                success: function (data) {
                    const content = getDelivererNew(data);
                    document.getElementById("new-order-list").innerHTML += content;
                    document.getElementById("new-order-list").style.display = "";
                    document.getElementById("all-list").style.display = "";
                    document.getElementById("title").innerHTML = "待配送订单";
                }
            });
        }
        else {
            $.ajax({
                url: "order-servlet",
                type: "post",
                dataType: "json",
                data: {"action": "initAllDelivererOrder", "tid": tid},
                success: function (data) {
                    const content = getDelivererAll(data);
                    document.getElementById("all-order-list").innerHTML += content;
                    document.getElementById("all-order-list").style.display = "";
                }
            });
        }
    }
}

function getUrContent(data) {
    let content = "";
    for (let i = 0; i < data.length; i++) {
        const order = data[i];
        const name = order["name"], time = order["time"], totalPrice = order["totalPrice"].toFixed(2),
            state = order["state"], oid = order["oid"];
        const color = state === "配送中" ? "red" : "black";
        content += "    <div class=\"layui-row\">\n" +
            "        <div class=\"layui-col-md3\">" + name + "</div>\n" +
            "        <div class=\"layui-col-md3\">" + time + "</div>\n" +
            "        <div class=\"layui-col-md2\">￥" + totalPrice + "</div>\n" +
            "        <div class=\"layui-col-md2\" style='color: " + color + "'>" + state + "</div>\n" +
            "        <div class=\"layui-col-md2\">\n" +
            "            <a order-id='" + oid + "' href=\"javascript:void(0)\" onclick=\"showDetails(this)\">点此查看</a>\n" +
            "        </div>\n" +
            "    </div>";
        if (i < data.length - 1) {
            content += "<hr style=\"margin: 10px 15px;\"/>";
        }
    }
    return content;
}

function getDelivererNew(data) {
    let content = "";
    for (let i = 0; i < data.length; i++) {
        const order = data[i];
        const restaurant = order["restaurant"], diner = order["diner"], time = order["time"],
            dx = order["dx"].toFixed(0), dy = order["dy"].toFixed(0),
            rx = order["rx"].toFixed(0), ry = order["ry"].toFixed(0),
            deliverFee = order["deliverFee"].toFixed(2), oid = order["oid"];
        content += "    <div class=\"layui-row\">\n" +
            "        <div class=\"layui-col-md2\">" + restaurant + "</div>\n" +
            "        <div class=\"layui-col-md1\">(" + rx + ", " + ry + ")</div>\n" +
            "        <div class=\"layui-col-md2\">" + diner + "</div>\n" +
            "        <div class=\"layui-col-md1\">(" + dx + ", " + dy + ")</div>\n" +
            "        <div class=\"layui-col-md2\">" + time + "</div>\n" +
            "        <div class=\"layui-col-md1\">￥" + deliverFee + "</div>\n" +
            "        <div class=\"layui-col-md2\">\n" +
            "            <a order-id='" + oid + "' href=\"javascript:void(0)\" onclick=\"showDetails(this)\">点此查看</a>\n" +
            "        </div>\n" +
            "        <div class=\"layui-col-md1\">\n" +
            "            <button order-id='" + oid + "' class=\"layui-btn layui-btn-xs\" onclick=\"finishOrder(this)\">\n" +
            "                <span style=\"font-family: '仓耳渔阳体 W04', serif\">完成订单</span>\n" +
            "            </button>\n" +
            "        </div>\n" +
            "    </div>";
        if (i < data.length - 1) {
            content += "<hr style=\"margin: 10px 15px;\"/>";
        }
    }
    return content;
}

function getDelivererAll(data) {
    let content = "";
    for (let i = 0; i < data.length; i++) {
        const order = data[i];
        const restaurant = order["restaurant"], diner = order["diner"], time = order["time"], state = order["state"],
            deliverFee = order["deliverFee"].toFixed(2), oid = order["oid"];
        const color = state === "配送中" ? "red" : "black";
        content += "    <div class=\"layui-row\">\n" +
            "        <div class=\"layui-col-md2\">" + restaurant + "</div>\n" +
            "        <div class=\"layui-col-md2\">" + diner + "</div>\n" +
            "        <div class=\"layui-col-md3\">" + time + "</div>\n" +
            "        <div class=\"layui-col-md1\">￥" + deliverFee + "</div>\n" +
            "        <div class=\"layui-col-md2\" style='color: " + color + "'>" + state + "</div>\n" +
            "        <div class=\"layui-col-md2\">\n" +
            "            <a order-id='" + oid + "' href=\"javascript:void(0)\" onclick=\"showDetails(this)\">点此查看</a>\n" +
            "        </div>\n" +
            "    </div>";
        if (i < data.length - 1) {
            content += "<hr style=\"margin: 10px 15px;\"/>";
        }
    }
    return content;
}

function showDetails(html) {
    const oid = html.getAttribute("order-id");
    const url = "pay.html?oid=" + oid;
    window.open(url, '_blank');
}

function finishOrder(html) {
    const oid = html.getAttribute("order-id");
    $.ajax({
        url: "order-servlet",
        type: "post",
        dataType: "json",
        data: {"action": "finishOrder", "oid": oid},
        success: function (data) {
            if (data["success"] === true) {
                layer.msg("订单已完成！", {icon: 1});
                setTimeout(() => location.reload(),1000);
            }
        }
    });
}

function jump() {
    window.open("order.html?tid=" + tid + "&mode=all", '_blank');
}