/**
 * Created by 磷啊 on 2017/12/3.
 */
(function () {
    function initOrderMessage(data) {
        $('#orderCode').val(data.orderCode);
        $('#createOrderName').val(data.createOrderPerson);
    }

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

    function initOrderStyleList(data) {
        var resultSet = data.data.orderStyleVos;
        for (var i in resultSet) {
            var result = [];
            if (i%2 === 0) {
                result.push("<tr id='"+resultSet[i].id+"' class='even'>");
            }else {
                result.push("<tr id='"+resultSet[i].id+"' class='odd'>");
            }
            result.push("<td>");
            result.push("<input type='checkbox' class='checkbox' name='id' value='"+resultSet[i].id+"' />");
            result.push("</td>");

            result.push("<td>");
            result.push(resultSet[i].workDate);
            result.push("</td>");

            result.push("<td>");
            result.push(resultSet[i].styleCode);
            result.push("</td>");

            result.push("<td>");
            result.push(resultSet[i].styleName);
            result.push("</td>");

            result.push("<td>");
            result.push(resultSet[i].ascription);
            result.push("</td>");

            result.push("<td>");
            result.push(resultSet[i].persons);
            result.push("</td>");

            result.push("<td>");
            result.push(resultSet[i].count);
            result.push("</td>");

            result.push("<td>");
            result.push(resultSet[i].valuationType === 0?"计时":"计件");
            result.push("</td>");

            result.push("<td>");
            result.push(resultSet[i].price);
            result.push("</td>");

            result.push("<td>");
            result.push(resultSet[i].increasePrice);
            result.push("</td>");

            result.push("<td>");
            result.push(resultSet[i].totalPrice);
            result.push("</td>");

            result.push("<td>");
            result.push(resultSet[i].check === 0? "待验收":"已验收");
            result.push("</td>");

            result.push("<td>");
            result.push(resultSet[i].checkPerson);
            result.push("</td>");

            result.push("<td>");
            result.push(resultSet[i].remark);
            result.push("</td>");


            result.push("<td class='last'>");
            result.push("<a text='"+resultSet[i].id+"' class='editOrderStyle' href='javascript:void(0)'>编辑</a> |<a text='"+resultSet[i].id+"' class='checkOrderStyle' href='javascript:void(0)'>验收</a> | <a text='"+resultSet[i].id+"' class='delOrderStyle' href='javascript:void(0)'>删除</a>");
            result.push("</td>");

            result.push("</tr>");

            $('#valuationTable tbody').append(result.join(""));
        }
    }

    function initOrderDetail(data) {
        initOrderMessage(data.data);
        initOrderStyleList(data);
    }

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
        ajax("http://106.15.200.24:8080/SM/statistics/v1/valuation?styleId="+styleId, "get", false, null, initValuationList, error);
    });

    function initBox() {
        $('#workDate').val("");
        $('#styleCode').val("0");
        $("#ascription").val("");
        $('#styleName').val("");
        $('#persons').val("");
        $('#count').val("");
        $('#valuationType').val("0");
        $('#valuationType').empty();
        $('#remark').val("")
        $('#edit').val("")
    }

    $('#createOrderStyle').on('click',function() {
        var orderId = localStorage.getItem("orderId");
        var workDate = $('#workDate').val();
        var styleId = $('#styleCode').val();
        var ascription = $('#ascription').val();
        var persons = $('#persons').val();
        var count = $('#count').val();
        var valuationId = $('#valuationType').val();
        var remark = $('#remark').val();

        var data = {
            orderId:orderId,
            workDate:workDate,
            ascription:ascription,
            styleId:styleId,
            persons:persons,
            count:count,
            valuationId:valuationId,
            remark:remark
        };

        function addTr(data) {
            var result = [];
            var size = $('#valuationTable tbody').children('tr').length;
            if (size%2 === 0) {
                result.push("<tr id='"+data.id+"' class='even'>");
            }else {
                result.push("<tr id='"+data.id+"' class='odd'>");
            }
            result.push("<td>");
            result.push("<input type='checkbox' class='checkbox' name='id' value='"+data.id+"' />");
            result.push("</td>");

            result.push("<td>");
            result.push(data.workDate);
            result.push("</td>");

            result.push("<td>");
            result.push(data.styleCode);
            result.push("</td>");

            result.push("<td>");
            result.push(data.styleName);
            result.push("</td>");

            result.push("<td>");
            result.push(data.ascription);
            result.push("</td>");

            result.push("<td>");
            result.push(data.persons);
            result.push("</td>");

            result.push("<td>");
            result.push(data.count);
            result.push("</td>");

            result.push("<td>");
            result.push(data.valuationType === 0?"计时":"计件");
            result.push("</td>");

            result.push("<td>");
            result.push(data.price);
            result.push("</td>");

            result.push("<td>");
            result.push(data.increasePrice);
            result.push("</td>");

            result.push("<td>");
            result.push(data.totalPrice);
            result.push("</td>");

            result.push("<td>");
            result.push(data.check === 0? "待验收":"已验收");
            result.push("</td>");

            result.push("<td>");
            result.push(data.checkPerson);
            result.push("</td>");

            result.push("<td>");
            result.push(data.remark);
            result.push("</td>");

            result.push("<td class='last'>");
            result.push("<a text='"+data.id+"' class='editOrderStyle' href='javascript:void(0)'>编辑</a> |<a text='"+data.id+"' class='checkOrderStyle' href='javascript:void(0)'>验收</a> | <a text='"+data.id+"' class='delOrderStyle' href='javascript:void(0)'>删除</a>");
            result.push("</td>");

            result.push("</tr>");

            $('#valuationTable tbody').append(result.join(""));
        }

        function returnSuccess(data) {
            initBox();
            $('#box').toggle();
            addTr(data.data);
        }

        ajax("http://106.15.200.24:8080/SM/statistics/v1/orderStyle","post",false,data,returnSuccess,error);
    });

    function initHtml() {
        var orderId = localStorage.getItem("orderId");
        if (orderId !== null && orderId !== undefined && orderId !== "") {
            ajax("http://106.15.200.24:8080/SM/statistics/v1/orderDetail?orderId=" + orderId, "get", false, null, initOrderDetail, error);
            ajax("http://106.15.200.24:8080/SM/statistics/v1/styles", "get", false, null, initStyleList, error);
        }
    }

    $(document).on("click",".editOrderStyle",function () {
        initBox();
       $('#box').toggle();
    });

    $(document).on("click",".delOrderStyle",function() {
        var r=confirm("是否真的要删除？")
        if (r === false) {
            return;
        }
        var id = $(this).attr("text");
        var orderId = localStorage.getItem("orderId");
        function returnSuccess(data) {
            $("#"+data.data).remove();
        }
        ajax("http://106.15.200.24:8080/SM/statistics/v1/orderStyle?orderId="+orderId+"&id="+id,"delete",false,null,returnSuccess,error);
    });

    $('#addOrderStyle').on("click",function () {
        initBox();
       $('#box').toggle();
    });

    $('#cancelEdit').on("click",function() {
       window.location.href="http://106.15.200.24:8080/SM/index/order.html";
    });

    $('#submitEdit').on("click",function() {
        var orderCode = $('#orderCode').val();
        var createOrderPerson = $('#createOrderName').val();
        var orderId = localStorage.getItem("orderId");

        var data = {
            orderCode:orderCode,
            createOrderPerson:createOrderPerson,
            orderId:orderId
        };

        function returnSuccess(data) {
            window.location.href="http://106.15.200.24:8080/SM/index/order.html";
        }

        ajax("http://106.15.200.24:8080/SM/statistics/v1/order","put",false,data,returnSuccess,error);
    });

    initHtml();
})();