let searchWhat = "restaurant";
let searchMode = "distance";
let uid = getQueryVariable("uid");

function initPage() {
    searchWhat = "restaurant";
    searchMode = "distance";
    search();
}

function search() {
    const params =
        $.param({'what': searchWhat}) + '&' +
        $.param({'mode': searchMode}) + '&' +
        $('#search-form').serialize();
    $.ajax({
        url: "diner-servlet",
        type: "post",
        dataType: "json",
        data: params,
        success: function (data) {
            if (searchWhat === "restaurant") {
                setRestaurantContent(data);
            }
            else {
                setDishContent(data);
            }
        }
    });
}

function setRestaurantContent(data) {
    let content = "";
    for (let row = 0; row < (data.length + 1) / 2; row++) {
        content += "<div class=\"layui-row\" style=\"margin-right: 300px; margin-top: 30px; margin-left: 100px\">";
        for (let col = 0; col <= 1; col++) {
            const index = row * 2 + col;
            if (index === data.length) {
                break;
            }
            const item = data[index];
            const rid = item["rid"], name = item["name"],
                kind = item["kind"], dist = item["dist"].toFixed(0);
            content += "<div class=\"layui-col-md5\" style=\"margin-left: " + (40 + 10 * col) + "px\">\n" +
                "            <a href='restaurant.html?rid=" + rid + "&uid=" + uid + "' target='_blank'>\n" +
                "                <div class=\"layui-panel\" style=\"padding-top: 12px; padding-bottom: -10px; padding-left: 10px; border: 1px #3ec487 solid; border-radius: 10px\">\n" +
                "                    <div class=\"layui-row\">\n" +
                "                        <div class=\"layui-col-md3\">\n" +
                "                            <img src='img/restaurant/" + rid + ".jpg' style=\"height: 55px; width: 60px; border-radius: 10px; margin-top: 4px\" alt=\"\">\n" +
                "                        </div>\n" +
                "                        <div class=\"layui-col-md9\">\n" +
                "                            <div class=\"layui-row\">\n" +
                "                                <div class=\"layui-col-md8\">\n" +
                "                                    <p style=\"font-size: large;\">" + name + "</p>\n" +
                "                                </div>\n" +
                "                                <div class=\"layui-col-md4\">\n" +
                "                                    <span class=\"layui-badge layui-bg-green\">\n" +
                "                                        <span>" + kind + "</span>\n" +
                "                                        <i class=\"layui-icon layui-icon-note\"></i>\n" +
                "                                    </span>\n" +
                "                                </div>\n" +
                "                            </div>\n" +
                "                            <div class=\"layui-row\">\n" +
                "                                <div class=\"layui-col-md7\" id='rate" + index + "' style=\"position: relative; bottom: 3px\"></div>\n" +
                "                                <div class=\"layui-col-md5\" style=\"position: relative; bottom: 2px; text-align: center\">\n" +
                "                                    <p style=\"font-size: smaller; padding-top: 15px; color: gray; font-family: '仓耳渔阳体 W03',serif\">距您" + dist + " km</p>\n" +
                "                                </div>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </a>\n" +
                "        </div>\n";
        }
        content += "</div>\n";
    }
    document.getElementById("content").innerHTML = content;
    for (let i = 0; i < data.length; i++) {
        const name = '#rate%'.replace("%", i.toString());
        layui.use(['rate'], function(){
            const rate = layui.rate;
            rate.render({
                elem: name
                ,value: parseFloat(data[i]["rate"])
                ,readonly: true
            });
        });
    }
    layui.form.render();
}

function setDishContent(data) {
    let content = "";
    for (let i = 0; i < data.length; i++) {
        const item = data[i];
        const rid = item["rid"], restaurant = item["restaurant"], name = item["name"], style = item["style"],
            meat = item["meat"], sales = item["sales"], price = item["price"];
        content += "    <div style=\"margin-left: 100px; margin-right: 300px; margin-top: 35px\">\n" +
            "        <a href='restaurant.html?rid=" + rid + "&uid=" + uid + "' target='_blank'>\n" +
            "            <div class=\"layui-panel\" style=\"padding: 10px 5px; border-radius: 15px\">\n" +
            "                <div class=\"layui-row\">\n" +
            "                    <div class=\"layui-col-md3\" style=\"margin-left: 20px; position: relative; top: 5px\">\n" +
            "                        <i class=\"layui-icon layui-icon-home\" style=\"color: green; font-size: large; margin-right: 5px\"></i>\n" +
            "                        <span style=\"font-size: large\">" + restaurant + "</span>\n" +
            "                    </div>\n" +
            "                    <div class=\"layui-col-md3\" style=\"margin-left: -20px; position: relative; top: 4px\">\n" +
            "                        <i class=\"layui-icon\" style=\"margin-right: 5px; font-size: large\">🍴</i>\n" +
            "                        <span>" + name + "</span>\n" +
            "                    </div>\n" +
            "                    <div class=\"layui-col-md2\" style=\"margin-left: -40px; position: relative; top: 6px\">\n" +
            "                <span class=\"layui-badge layui-bg-green\">\n" +
            "                    <span>" + style + "</span>\n" +
            "                    <i class=\"layui-icon layui-icon-note\"></i>\n" +
            "                </span>\n" +
            "                        <span class=\"layui-badge layui-bg-green\">\n" +
            "                    <span>" + meat + "</span>\n" +
            "                    <i class=\"layui-icon layui-icon-note\"></i>\n" +
            "                </span>\n" +
            "                    </div>\n" +
            "                    <div class=\"layui-col-md2\" style=\"text-align: center; margin-left: 30px; font-size: medium; font-family: '仓耳渔阳体 W04',serif; color: gray; position: relative; top: 7px\">月售" + sales + "份</div>\n" +
            "                    <div class=\"layui-col-md2\" style=\"color: red; text-align: center\">\n" +
            "                        <i class=\"layui-icon layui-icon-rmb\">\n" +
            "                            <span style=\"font-family: '仓耳渔阳体 W04',serif; font-size: x-large\"> " + price + "</span>\n" +
            "                        </i>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "            </div>\n" +
            "        </a>\n" +
            "    </div>";
    }
    document.getElementById("content").innerHTML = content;
}

function searchRestaurant() {
    searchWhat = "restaurant";
    document.getElementById("whole-search-btn").classList.remove("layui-btn-disabled");
    search();
}

function searchDish() {
    searchWhat = "dish";
    document.getElementById("whole-search-btn").classList.add("layui-btn-disabled");
    search();
}

function changeSearchBtn() {
    if (searchWhat === "restaurant") {
        if (searchMode === "distance") {
            document.getElementById("search-mode-btn").innerHTML = "按评分";
            searchMode = "rate";
        }
        else {
            document.getElementById("search-mode-btn").innerHTML = "按距离";
            searchMode = "distance";
        }
    }
    search();
}

function jump(html) {
    const uid = getQueryVariable("uid");
    if (html.innerHTML === "个人空间") {
        window.open("space.html?uid=" + uid, '_blank');
    }
    else if (html.innerHTML === "我的订单") {
        window.open("order.html?uid=" + uid, '_blank');
    }
}
