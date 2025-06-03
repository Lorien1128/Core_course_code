let orderedDishes = [];
let rid, uid;

function initPage() {
    let diner = true;
    rid = getQueryVariable("rid");
    uid = getQueryVariable("uid");
    if (uid === null) {
        diner = false;
        document.getElementById("title").innerHTML = "商家端"
        document.getElementById("order-nav").style.display = "";
    }
    $.ajax({
        url: "restaurant-servlet",
        type: "post",
        dataType: "json",
        data: {"action": "getNameAndRate", "rid": rid},
        success: function (data) {
            document.getElementById("restaurant-name").innerHTML = data["name"];
            layui.use(['rate'], function(){
                const rate = layui.rate;
                rate.render({
                    elem: '#restaurant-rate'
                    ,value: parseFloat(data["rate"])
                    ,readonly: true
                });
            });
            layui.form.render();
            document.getElementById("restaurant-img").
                setAttribute("src", "./img/restaurant/" + rid + ".jpg");
        }
    });
    $.ajax({
        url: "restaurant-servlet",
        type: "post",
        dataType: "json",
        data: {"action": "initDishes", "rid": rid},
        success: function (data) {
            setDishList(data);
        }
    });
    initFixBar(diner);
}

function setDishList(dishes) {
    let listHtml = ""
    for (let i in dishes) {
        const dish = dishes[i];
        console.log(dish["price"]);
        let materialHtml = "";
        for (let j in dish["material"]) {
            const material = dish["material"][j];
            materialHtml = materialHtml + "<a material-id='" + material["mid"] + "' href=\"javascript:void(0)\" onclick=\"queryLocation(this)\">" + material["name"] + "</a>\n" +
                "                            " + material["weight"] + "g；";
        }
        listHtml = listHtml + "<div class=\"layui-row\" style=\"margin-top: 20px; margin-bottom: 20px\">\n" +
            "            <div class=\"layui-collapse layui-col-md10\">\n" +
            "                <div class=\"layui-colla-item\" style=\"padding: 10px\">\n" +
            "                    <div class=\"layui-colla-title\">\n" +
            "                        <div class=\"layui-row\">\n" +
            "                            <div class=\"layui-col-md3\" style=\"font-size: large; margin-left: 10px\">\n" +
            "                                " + dish["name"] + "\n" +
            "                            </div>\n" +
            "                            <div class=\"layui-col-md3\" style=\"margin-left: -20px\">\n" +
            "                            <span class=\"layui-badge layui-bg-green\">\n" +
            "                                <span>" + dish["style"] + "</span>\n" +
            "                                <i class=\"layui-icon layui-icon-note\"></i>\n" +
            "                            </span>\n" +
            "                                <span class=\"layui-badge layui-bg-green\">\n" +
            "                                <span>" + dish["meat"] + "</span>\n" +
            "                                <i class=\"layui-icon layui-icon-note\"></i>\n" +
            "                            </span>\n" +
            "                            </div>\n" +
            "                            <div class=\"layui-col-md3\" style=\"font-size: medium; font-family: '仓耳渔阳体 W04',serif; color: gray\">\n" +
            "                                月售" + dish["sales"] + "份\n" +
            "                            </div>\n" +
            "                            <div class=\"layui-col-md3\" style=\"color: red\">\n" +
            "                                <i class=\"layui-icon layui-icon-rmb\">\n" +
            "                                    <span style=\"font-family: '仓耳渔阳体 W04',serif; font-size: x-large\">&nbsp;" + parseFloat(dish["price"]).toFixed(2) + "</span>\n" +
            "                                </i>\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                    <div class=\"layui-colla-content\">\n" +
            "                        <p style='position: relative; top: 5px'>\n" + materialHtml +
            "                        </p>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "            </div>";
        if (uid != null) {
            listHtml = listHtml + "<div class=\"layui-col-md2\">\n" +
                "                <div id=\"diner-show\">\n" +
                "                    <div class=\"layui-btn\" style=\"margin-left: 15px; margin-top: 12px;  width: 40px; border-radius: 20px; padding: 0\"\n" +
                "                         dish-id='" + dish.did + "' dish-name='" + dish.name + "' dish-price='" + dish.price + "' onclick=\"addToList(this)\">\n" +
                "                        <i class=\"layui-icon layui-icon-add-1\"\n" +
                "                           style=\"color: white; font-size: x-large; \"></i>\n" +
                "                    </div>\n" +
                "                    <div style=\"position: relative; right: 16px; bottom: 3px; display: inline-block\">\n" +
                "                    <span id=\"amount" + dish.did + "\" class=\"layui-bg-red\" style=\"width: 20px; height: 20px; border-radius: 10px; padding: 1px; display: none; font-size: small\">\n" +
                "                        <span style=\"position: relative; left: 5px; display: table; margin-right: 10px\">1</span>\n" +
                "                    </span>\n" +
                "                    </div>\n" +
                "                    <div id=\"sub" + dish.did + "\" class=\"layui-btn layui-btn-primary layui-border-green\" style=\"position: relative; top: 8px; right: 14px; width: 24px; height: 24px; padding: 0; border-radius: 11px; border: 2px solid; display: none\"\n" +
                "                         onclick=\"removeFromList(this)\">\n" +
                "                        <i class=\"layui-icon layui-icon-subtraction\" style=\"position: relative; bottom: 8px; font-size: large\"></i>\n" +
                "                    </div>\n" +
                "                </div></div></div>";
        }
        else {
            listHtml = listHtml + "<div class=\"layui-col-md2\">" + "<div id=\"restaurant-show\" style=\"margin-top: 18px\">\n" +
                "                    <div dish-id=\"" + dish.did + "\" class=\"layui-btn layui-btn-sm\" style=\"display: inline-block; margin-left: 16px\" onclick=\"updateDish(this)\">\n" +
                "                        修改\n" +
                "                    </div>\n" +
                "                    <div dish-id=\"" + dish.did + "\" class=\"layui-btn layui-btn-sm\" style=\"display: inline-block\" onclick=\"delDish(this)\">\n" +
                "                        删除\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>";
        }
    }
    document.getElementById("dish-list").innerHTML = listHtml;
    layui.element.init();
}

function initFixBar(diner) {
    let icon = "layui-icon-cart-simple";
    let func = showList;
    if (!diner) {
        icon = "layui-icon-add-1";
        func = addDish;
    }
    layui.use(['util', 'layer'], function() {
        const util = layui.util;
        util.fixbar({
            bar1: "<i class='layui-icon " + icon + "' style='font-size: xx-large; margin: 5px'></i>"
            , css: {right: 100, bottom: 100, border:{radiusX : 14}}
            , bgcolor: 'green'
            , click: function () {
                func();
            }
        });
    });
}

function queryLocation(html) {
    const mid = html.getAttribute("material-id");
    $.ajax({
        url: "restaurant-servlet",
        type: "post",
        dataType: "json",
        data: {"action": "getLocation", "mid": mid},
        success: function (data) {
            layer.msg("原产地：" + data["origin"], {icon: 7});
        }
    });
}

function addToList(html) {
    const id = html.getAttribute("dish-id");
    const name = html.getAttribute("dish-name");
    const price = html.getAttribute("dish-price");
    let find = Boolean(false);
    for (let i in orderedDishes) {
        const dish = orderedDishes[i];
        if (dish.id === id) {
            dish.amount = dish.amount + 1;
            find = true;
        }
    }
    if (!find) {
        const dish = {
            id: id,
            name: name,
            amount: 1,
            price: price
        }
        orderedDishes.push(dish);
    }
    const redDot = document.getElementById("amount" + id);
    const num = redDot.getElementsByTagName('span')[0];
    if (redDot.style.display === "none") {
        redDot.style.display = "table";
        num.innerHTML = "1";
    }
    else {
        num.innerHTML = (parseInt(num.innerHTML) + 1).toString();
    }
    const subBtn = document.getElementById("sub" + id);
    if (subBtn.style.display === "none") {
        subBtn.style.display = "inline-block";
    }
}

function removeFromList(html) {
    const id = /[0-9]+/.exec(html.id)[0];
    for (let i in orderedDishes) {
        const dish = orderedDishes[i];
        if (dish.id === id) {
            dish.amount = dish.amount - 1;
            const redDot = document.getElementById("amount" + id);
            const num = redDot.getElementsByTagName('span')[0];
            num.innerHTML = (parseInt(num.innerHTML) - 1).toString();
            if (dish.amount === 0) {
                delete orderedDishes[i];
                if (orderedDishes[0] === undefined) {
                    orderedDishes = [];
                }
                html.style.display = "none";
                redDot.style.display = "none";
            }
        }
    }
}

function showList() {
    if (orderedDishes.length === 0) {
        const layer = layui.layer;
        layer.msg("您还未选择菜品！", {icon:7});
        return;
    }
    let content = "";
    if (orderedDishes.length > 0) {
        content = "<table class=\"layui-table\" lay-even=\"\" lay-skin=\"nob\" style='text-align: center'>\n" +
            "    <thead>\n" +
            "    <tr>\n" +
            "        <th style='width: 200px'>菜品</th>\n" +
            "        <th style='width: 150px'>数量</th>\n" +
            "    </tr>\n" +
            "    </thead>\n" +
            "    <tbody>";
        for (let i in orderedDishes) {
            const name = orderedDishes[i].name;
            const amount = orderedDishes[i].amount;
            content = content + "    <tr>\n" +
                "        <td>" + name + "</td>\n" +
                "        <td>" + amount + "</td>\n" +
                "    </tr>"
        }
        content = content + "    </tbody>\n" +
            "</table>";
    }
    const layer = layui.layer;
    layer.open({
        type: 1
        , title: '订单'
        , offset: 'auto'
        , id: 'list'
        , content: content
        , btn: ['关闭', '去付款']
        , btnAlign: 'c'
        , shade: 0
        , yes: function () {
            layer.closeAll();
        }
        , btn2: function () {
            pay();
        }
    });
}

function pay() {
    window.localStorage.removeItem("payList");
    window.localStorage.setItem("payList", JSON.stringify(orderedDishes));
    window.open("pay.html?rid=" + rid + "&uid=" + uid, '_blank');
}

function delDish(html) {
    const did = html.getAttribute("dish-id");
    $.ajax({
        url: "restaurant-servlet",
        type: "post",
        dataType: "json",
        data: {"action": "delDish", "did": did, "rid": rid},
        success: function (data) {
            if (data["success"] === true) {
                layer.msg("删除成功！", {icon: 1});
                setTimeout(() => location.reload(),1000);
            }
        }
    });
}

function updateDish(html) {
    const did = html.getAttribute("dish-id");
    window.open("dish.html?rid=" + rid + "&did=" + did, '_blank');
}

function addDish() {
    window.open("dish.html?rid=" + rid, '_blank');
}

function showOrders() {
    window.open("order.html?rid=" + rid, '_blank');
}
