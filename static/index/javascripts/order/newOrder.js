/**
 * Created by 磷啊 on 2017/12/3.
 */
(function() {
    function initStyleList(data) {
        var resultSet = data.data;
        var result = [];
        result.push("<option>");
        result.push("---请选择---");
        result.push("</option>");
        for (var i in resultSet) {
            result.push("<option value='"+resultSet[i].styleId+"'>");
            result.push(resultSet[i].styleCode);
            result.push("</option>");

            localStorage.setItem(resultSet[i].styleId,resultSet[i].styleName);
        }
        $('#styleCode').append(result.join(""));
    }

    function initHtml() {
        ajax("http://localhost:8080/SM/statistics/v1/styles", "get", false, null, initStyleList, error);
    }

    initHtml();

    $('#styleCode').on("change",function () {
        var styleId = $(this).val();
        if (null === styleId || "" === styleId || undefined === styleId) {
            return;
        }
        var styleName = localStorage.getItem(styleId);
        $('#styleName').val(styleName);
        function initValuationList(data) {
            $('#valuationType').empty();
            var resultSet = data.data;
            var result = [];
            for (var i in resultSet) {
                result.push("<option value='"+resultSet[i].valuationId+"'>");
                result.push(resultSet[i].valuationType == 0?"计时":"计件");
                var increasePrice = resultSet[i].increasePrice === ""? 0:resultSet[i].increasePrice;
                result.push("("+resultSet[i].price+"+"+increasePrice+")");
                result.push("</option>");
            }
            $('#valuationType').append(result.join(""));
        }
        ajax("http://localhost:8080/SM/statistics/v1/valuation?styleId="+styleId, "get", false, null, initValuationList, error);
    });

    $('#submitCreateOrder').on("click",function() {
        var orderCode = $('#orderCode').val();
        var createOrderName = $('#createOrderName').val();
        var workDate = $('#workDate').val();
        var styleId = $('#styleCode').val();
        var ascription = $('#ascription').val();
        var persons = $('#persons').val();
        var count = $('#count').val();
        var valuationId = $('#valuationType').val();
        var remark = $('#remark').val();

        var data = {
            orderCode:orderCode,
            createOrderPerson:createOrderName,
            workDate:workDate,
            ascription:ascription,
            styleId:styleId,
            persons:persons,
            count:count,
            valuationId:valuationId,
            remark:remark
        };

        function returnSuccess() {
            window.location.href = "http://localhost:8080/SM/index/order.html";
        }

        ajax("http://localhost:8080/SM/statistics/v1/order","post",false,data,returnSuccess,error);
    });

})();