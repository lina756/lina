/**
 * Created by 磷啊 on 2017/12/2.
 */
(function () {
    var commonHtml = [];
    commonHtml.push("<li class='active'><a id='addNewOrder' href='javascript:void(0)'>新增订单</a></li>");
    commonHtml.push("<li><a id='toOrderList' href='javascript:void(0)'>订单列表</a></li>");
    commonHtml.push("<li><a id='toStyleList' href='javascript:void(0)'>款式列表</a></li>");
    $('#main-navigation .wat-cf').html(commonHtml.join(""));
})();


$(document).on("click","#addNewOrder",function() {
    window.location.href="http://localhost:8080/SM/index/newOrder.html";
});

$(document).on("click","#toOrderList",function() {
    window.location.href="http://localhost:8080/SM/index/order.html";
});

$(document).on("click","#toStyleList",function() {
    window.location.href="http://localhost:8080/SM/index/style.html";
});

var error = function() {
    console.log("处理失败");
};
commonHtml.push("<li class='first'><a id='createStyle' href='javascript:void(0)'>新增款式</a></li>");
function ajax(url,method,async,data,success,error){
    var aj = $.ajax({
        url:url,
        contentType : "application/json ; charset=utf-8",
        data:JSON.stringify(data),
        type:method,
        async:async,
        dataType:'json',
        success:function (data) {
            if (200 !== data.code) {
                alert(data.message);
            }else {
                if (null !== success) {
                    success(data);
                }
            }
        },
        error :error
    });

}