const dishes = [];
let rid;
let uid;
let tid;

function initPage() {
    const oid = getQueryVariable("oid")
    if (oid != null) {
        $.ajax({
            url: "pay-servlet",
            type: "post",
            dataType: "json",
            data: {"action": "getOrderMessage", "oid": oid},
            success: function (data) {
                showBasicInfo(data);
                document.getElementById("submit-time").innerHTML = data["time"];
                document.getElementById("deliver-state").innerHTML = data["state"];
                document.getElementById("deliver-state").style.color =
                    data["state"] === "配送中" ? "red" : "black";
                initDishList(data["dishes"]);
                if (data["coupon"] === true) {
                    document.getElementById("coupon").innerHTML =
                        "满" + data["sill"] + "减" + data["discount"];
                }
                else {
                    document.getElementById("coupon").innerHTML = "未使用优惠券";
                }
                document.getElementById("total-price").innerHTML = data["price"].toFixed(2);
                document.getElementById("title").innerHTML = "订单信息";
                document.getElementById("pay-btn").style.display = "none";
                document.getElementById("pay-coupon").style.display = "none";
                document.getElementById("query-msg").style.display = "";
            }
        });
    }
    else {
        rid =getQueryVariable("rid");
        uid = getQueryVariable("uid");
        const orderList = JSON.parse(window.localStorage.getItem("payList"));
        let listContent = "";
        let total = 0;
        for (let i in orderList){
            const dish = orderList[i];
            const name = dish.name;
            const amount = dish.amount;
            const price = parseFloat(dish.price) * amount;
            total = total + price;
            dishes.push({did: dish.id, amount: dish.amount});
            listContent = listContent + "            <div class=\"layui-row\" style=\"margin-top: 10px\">\n" +
                "                <div class=\"layui-col-md5 layui-col-md-offset1\">\n" +
                "                    <span style=\"font-size: large\">" + name + "</span>\n" +
                "                </div>\n" +
                "                <div class=\"layui-col-md3\">\n" +
                "                    <span style=\"color: darkgray; position: relative; top: 1px;\">" + amount + "份</span>\n" +
                "                </div>\n" +
                "                <div class=\"layui-col-md3\">\n" +
                "                    <span style=\"font-size: small\">￥</span>\n" +
                "                    <span>" + price.toFixed(2) + "</span>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <hr style=\"margin: 5px\"/>";
        }
        document.getElementById("dish-list").innerHTML = listContent;
        document.getElementById("total-mode").innerHTML = "应付款（不含优惠金额）";
        document.getElementById("coupon-display").style.display = "none";
        $.ajax({
            url: "pay-servlet",
            type: "post",
            dataType: "json",
            data: {"action": "getOrderMessageBeforePay", "rid": rid, "uid": uid},
            success: function (data) {
                showBasicInfo(data);
                tid = data["tid"];
                total = total + parseFloat(data["deliverFee"]);
                document.getElementById("total-price").innerHTML = total.toFixed(2);
                const coupons = data["coupons"];
                let content = "";
                for (let i = 0; i < coupons.length; i++) {
                    const coupon = coupons[i];
                    content += "<option value='" + coupon["cid"] + "'>满" + coupon["sill"] + "减" + coupon["discount"] + "</option>";
                }
                document.getElementById("coupon-select").innerHTML += content;
                layui.form.render();
            }
        });
    }
}

function showBasicInfo(data) {
    document.getElementById("diner-name").innerHTML = data["diner_name"];
    document.getElementById("diner-phone").innerHTML = data["diner-phone"];
    document.getElementById("diner-pos").innerHTML =
        data["dx"].toFixed(2) + ", " + data["dy"].toFixed(2);
    document.getElementById("restaurant-name").innerHTML = data["restaurant-name"];
    document.getElementById("restaurant-phone").innerHTML = data["restaurant-phone"];
    document.getElementById("restaurant-pos").innerHTML =
        data["rx"].toFixed(2) + ", " + data["ry"].toFixed(2);
    document.getElementById("deliverer-name").innerHTML = data["deliverer-name"];
    document.getElementById("deliverer-phone").innerHTML = data["deliverer-phone"];
    document.getElementById("deliverFee").innerHTML = data["deliverFee"].toFixed(2);
}

function commitOrder() {
    const params = $.param({'action': "commitOrder"}) + '&' + $.param({'uid': uid}) + '&' +
        $.param({'rid': rid}) + '&' + $.param({'tid': tid}) + '&' +
        $.param({'dishes': JSON.stringify(dishes)}) + '&' +
        $.param({'cid': $('#coupon-select option:selected').val()});
    $.ajax({
        url: "pay-servlet",
        type: "post",
        dataType: "json",
        data: params,
        success: function (data) {
            if (data["success"] === true) {
                layer.msg("付款成功！", {icon: 1});
                setTimeout(() => window.open("order.html?uid=" + uid),1000);
            }
        }
    });
}

function initDishList(orderList) {
    let listContent = "";
    for (let i in orderList){
        const dish = orderList[i];
        const name = dish["name"];
        const amount = dish["amount"];
        const price = parseFloat(dish["price"]);
        listContent = listContent + "            <div class=\"layui-row\" style=\"margin-top: 10px\">\n" +
            "                <div class=\"layui-col-md5 layui-col-md-offset1\">\n" +
            "                    <span style=\"font-size: large\">" + name + "</span>\n" +
            "                </div>\n" +
            "                <div class=\"layui-col-md3\">\n" +
            "                    <span style=\"color: darkgray; position: relative; top: 1px;\">" + amount + "份</span>\n" +
            "                </div>\n" +
            "                <div class=\"layui-col-md3\">\n" +
            "                    <span style=\"font-size: small\">￥</span>\n" +
            "                    <span>" + price.toFixed(2) + "</span>\n" +
            "                </div>\n" +
            "            </div>\n" +
            "            <hr style=\"margin: 5px\"/>";
    }
    document.getElementById("dish-list").innerHTML = listContent;
}
