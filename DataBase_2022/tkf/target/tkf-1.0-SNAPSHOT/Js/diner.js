let searchWhat = "restaurant";
let searchMode = "distance";

function initPage() {
    searchWhat = "restaurant";
    searchMode = "distance";
}

function search() {
    console.log("test");
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

layui.use(['rate'], function(){
    const rate = layui.rate;
    rate.render({
        elem: '#rate2'
        ,value: 4
        ,readonly: true
    });
});
