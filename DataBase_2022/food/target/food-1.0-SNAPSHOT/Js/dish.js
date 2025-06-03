let index = 2;
let mode = "update";
let rid, did;

function initPage() {
    rid = getQueryVariable("rid");
    did = getQueryVariable("did");
    if (did === null) {
        mode = "add";
        document.getElementById("title").innerHTML = "新增菜品信息";
        document.getElementById("dish-names").removeAttribute("disabled");
        document.getElementById("dish-add").style.display = "";
        document.getElementById("material-info").style.display = "none";
        $.ajax({
            url: "dish-servlet",
            type: "post",
            dataType: "json",
            data: {"action": "getAllDishes", "rid": rid},
            success: function (data) {
                let options = "";
                for (let did in data) {
                    const name = data[did];
                    options += "<option value='" + did + "'>" + name + "</option>"
                }
                document.getElementById("dish-names").innerHTML += options;
                layui.form.render();
            }
        });
    }
    else {
        $.ajax({
            url: "dish-servlet",
            type: "post",
            dataType: "json",
            data: {"action": "initUpdatePage", "rid": rid, "did": did},
            success: function (data) {
                const name = data["name"], price = data["price"], materialList = data["material"];
                document.getElementById("dish-names").innerHTML =
                    "<option value='" + did + "' selected='selected'>" + name + "</option>";
                layui.form.render();
                document.getElementById("price-input").setAttribute("value", price);
                let list = "", i = 0;
                for (; i < materialList.length; i++) {
                    const material = materialList[i];
                    const materialName = material["name"], mid = material["mid"], weight = material["weight"];
                    list += "            <form class=\"layui-form\" mid='" + mid + "' id='form" + i + "'>\n" +
                        "                <div class=\"layui-form-item\">\n" +
                        "                    <div class=\"layui-input-inline\">\n" +
                        "                        <label>\n" +
                        "                            <select id='materialSelector'" + i + " name=\"mid\" lay-search=\"\" disabled>\n" +
                        "                                <option value='" + mid + "' selected='selected' disabled>" + materialName + "</option>\n" +
                        "                            </select>\n" +
                        "                        </label>\n" +
                        "                    </div>\n" +
                        "                    <label class=\"layui-form-label\">克数</label>\n" +
                        "                    <div class=\"layui-input-inline\">\n" +
                        "                        <label>\n" +
                        "                            <input id='weight" + i + "' class=\"layui-input\" type=\"text\" name=\"weight\" value='" + weight + "'>\n" +
                        "                        </label>\n" +
                        "                    </div>\n" +
                        "                    <div class=\"layui-btn-sm layui-btn-primary\" form-id='" + i + "' style=\"display: inline; border: 0\" onclick=\"delMaterial(this)\">\n" +
                        "                        <i class=\"layui-icon layui-icon-delete\" style=\"font-size: x-large; color: green\"></i>\n" +
                        "                    </div>\n" +
                        "                    <div class=\"layui-btn-sm layui-btn-primary\" form-id='" + i + "' style=\"display: inline; border: 0\" onclick=\"saveMaterial(this)\">\n" +
                        "                        <i class=\"layui-icon layui-icon-file-b\" style=\"font-size: large; color: green; position: relative; bottom: 1px\"></i>\n" +
                        "                    </div>\n" +
                        "                </div>\n" +
                        "            </form>";
                }
                document.getElementById("material-list").innerHTML = list;
                layui.form.render();
                index = i;
            }
        });
    }
}

function openAddDishWindow() {
    const content = "<form class=\"layui-form\" id=\"form-addDish\" style='margin-right: 20px; margin-left: 20px; margin-top: 15px'>\n" +
        "    <div class=\"layui-form-item\">\n" +
        "        <label class=\"layui-form-label\">菜系</label>\n" +
        "        <div class=\"layui-input-block\">\n" +
        "            <label>\n" +
        "                <select name=\"style\" lay-search=\"\">\n" +
        "                    <option value=\"-1\" disabled>直接选择或通过输入搜索</option>\n" +
        "                    <option value=\"川菜\">川菜</option>\n" +
        "                    <option value=\"鲁菜\">鲁菜</option>\n" +
        "                    <option value=\"粤菜\">粤菜</option>\n" +
        "                    <option value=\"苏菜\">苏菜</option>\n" +
        "                    <option value=\"闽菜\">闽菜</option>\n" +
        "                    <option value=\"浙菜\">浙菜</option>\n" +
        "                    <option value=\"湘菜\">湘菜</option>\n" +
        "                    <option value=\"徽菜\">徽菜</option>\n" +
        "                </select>\n" +
        "            </label>\n" +
        "        </div>\n" +
        "    </div>\n" +
        "    <div class=\"layui-form-item\">\n" +
        "        <label class=\"layui-form-label\">菜品名</label>\n" +
        "        <div class=\"layui-input-block\">\n" +
        "            <label>\n" +
        "                <input class=\"layui-input\" type=\"text\" name=\"name\"/>\n" +
        "            </label>\n" +
        "        </div>\n" +
        "    </div>\n" +
        "    <div class=\"layui-form-item\">\n" +
        "        <label class=\"layui-form-label\">荤素</label>\n" +
        "        <div class=\"layui-input-block\">\n" +
        "            <input type=\"radio\" name=\"meat\" value=\"荤\" title=\"荤\">\n" +
        "            <input type=\"radio\" name=\"meat\" value=\"素\" title=\"素\">\n" +
        "        </div>\n" +
        "    </div>\n" +
        "</form>";
    const layer = layui.layer;
    layer.open({
        type: 1
        , title: '添加菜品信息'
        , offset: 'auto'
        , id: 'dish-msg'
        , content: content
        , btn: ['添加', '取消']
        , btnAlign: 'c'
        , shade: 0
        , yes: function () {
            const param = $.param({'action': "addGlobalDish"}) + '&' + $('#form-addDish').serialize();
            $.ajax({
                url: "dish-servlet",
                type: "post",
                dataType: "json",
                data: param,
                success: function (data) {
                    if (data["success"] === true) {
                        layer.msg("添加成功！", {icon: 1});
                        setTimeout(() => location.reload(),1000);
                    }
                }
            });
        }
        , btn2: function () {
            layer.closeAll()
        }
    });
    layui.form.render();
}

function openAddMaterialWindow() {
    const content = "<form class=\"layui-form\" id=\"form-addMaterial\" style='margin-right: 20px; margin-left: 20px; margin-top: 15px'>\n" +
        "    <div class=\"layui-form-item\">\n" +
        "        <label class=\"layui-form-label\">原料名</label>\n" +
        "        <div class=\"layui-input-block\">\n" +
        "            <label>\n" +
        "                <input class=\"layui-input\" type=\"text\" name=\"name\">\n" +
        "            </label>\n" +
        "        </div>\n" +
        "    </div>\n" +
        "    <div class=\"layui-form-item\">\n" +
        "        <label class=\"layui-form-label\">产地</label>\n" +
        "        <div class=\"layui-input-block\">\n" +
        "            <label>\n" +
        "                <input class=\"layui-input\" type=\"text\" name=\"location\">\n" +
        "            </label>\n" +
        "        </div>\n" +
        "    </div>\n" +
        "</form>";
    const layer = layui.layer;
    layer.open({
        type: 1
        , title: '添加原料信息'
        , offset: 'auto'
        , id: 'material-msg'
        , content: content
        , btn: ['添加', '取消']
        , btnAlign: 'c'
        , shade: 0
        , yes: function () {
            const param = $.param({'action': "addGlobalMaterial"}) + '&' + $('#form-addMaterial').serialize();
            $.ajax({
                url: "dish-servlet",
                type: "post",
                dataType: "json",
                data: param,
                success: function (data) {
                    if (data["success"] === true) {
                        layer.msg("添加成功！", {icon: 1});
                        setTimeout(() => location.reload(),1000);
                    }
                }
            });
        }
        , btn2: function () {
            layer.closeAll()
        }
    });
    layui.form.render();
}

function delMaterial(html) {
    const id = html.getAttribute("form-id");
    const mid = document.getElementById("form" + id).getAttribute("mid");
    const param = {'action': "removeMaterial", 'did': did, 'mid' : mid};
    $.ajax({
        url: "dish-servlet",
        type: "post",
        dataType: "json",
        data: param,
        success: function (data) {
            if (data["success"] === true) {
                layer.msg("删除成功！", {icon: 1});
                document.getElementById("form" + id).remove();
            }
        }
    });
}

function saveMaterial(html) {
    const id = html.getAttribute("form-id");
    const selector = $('#form' + id);
    let params = selector.serializeArray();
    let weight;
    for (let i = 0; i < params.length; i++) {
        if (params[i].name === "weight") {
            weight = params[i].value;
            break;
        }
    }
    document.getElementById("weight" + id).setAttribute("value", weight);
    let action = "updateMaterialWeight";
    if (!document.getElementById("form" + id).hasAttribute("mid")) {
        action = "saveMaterial";
        const mid = params[0].value;
        document.getElementById("form" + id).getElementsByTagName("select")[0]
            .setAttribute("disabled", "");
        const options = document.getElementById("form" + id).getElementsByTagName("option");
        for (let i in options) {
            if (options[i].value !== mid && options[i].value !== undefined) {
                options[i].setAttribute("disabled", "");
            }
        }
        document.getElementById("form" + id).setAttribute("mid", mid);
    }

    const mid = document.getElementById("form" + id).getAttribute("mid");
    params = $.param({'action': action}) + '&' + $.param({'did': did}) + '&' +
        $.param({'mid': mid}) + '&' + selector.serialize();
    $.ajax({
        url: "dish-servlet",
        type: "post",
        dataType: "json",
        data: params,
        success: function (data) {
            if (data["success"] === true) {
                layer.msg("保存成功！", {icon: 1});
            }
        }
    });
    layui.form.render();
}

function save() {
    const action = mode === "update" ? "updatePrice" : "restaurantAddDish";
    const param =  $.param({'action': action}) + '&' + $.param({'rid': rid}) + '&' +
        $('#price-form').serialize();
    const did = $('#dish-names option:selected').val();
    $.ajax({
        url: "dish-servlet",
        type: "post",
        dataType: "json",
        data: param,
        success: function (data) {
            if (data["success"] === true) {
                if (mode === "update") {
                    layer.msg("修改成功！", {icon: 1});
                }
                else {
                    layer.msg("添加成功！", {icon: 1});
                    setTimeout(() =>
                        window.open("dish.html?rid=" + rid + "&did=" + did),1000);
                }
            }
        }
    });
}

function addFormItem() {
    $.ajax({
        url: "dish-servlet",
        type: "post",
        dataType: "json",
        data: {'action': "getAllMaterials", 'did': did},
        success: function (data) {
            let options = "<option disabled>直接选择或通过输入搜索</option>";
            for (let mid in data) {
                const name = data[mid];
                options += "<option value='" + mid + "'>" + name + "</option>\n"
            }
            document.getElementById("material-list").innerHTML +=
                "            <form class=\"layui-form\" id='form" + index + "'>\n" +
                "                <div class=\"layui-form-item\">\n" +
                "                    <div class=\"layui-input-inline\">\n" +
                "                        <label>\n" +
                "                            <select id='materialSelector'" + index + " name=\"mid\" lay-search=\"\">\n" +
                                                options +
                "                            </select>\n" +
                "                        </label>\n" +
                "                    </div>\n" +
                "                    <label class=\"layui-form-label\">克数</label>\n" +
                "                    <div class=\"layui-input-inline\">\n" +
                "                        <label>\n" +
                "                            <input id='weight" + index + "' class=\"layui-input\" type=\"text\" name=\"weight\">\n" +
                "                        </label>\n" +
                "                    </div>\n" +
                "                    <div class=\"layui-btn-sm layui-btn-primary\" form-id='" + index + "' style=\"display: inline; border: 0\" onclick=\"delMaterial(this)\">\n" +
                "                        <i class=\"layui-icon layui-icon-delete\" style=\"font-size: x-large; color: green\"></i>\n" +
                "                    </div>\n" +
                "                    <div class=\"layui-btn-sm layui-btn-primary\" form-id='" + index + "' style=\"display: inline; border: 0\" onclick=\"saveMaterial(this)\">\n" +
                "                        <i class=\"layui-icon layui-icon-file-b\" style=\"font-size: large; color: green; position: relative; bottom: 1px\"></i>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </form>";
            layui.form.render();
            index++;
        }
    });
}