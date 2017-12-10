/**
 * Created by 磷啊 on 2017/12/3.
 */
(function () {
    function initOrderList(data) {
        var resultSet = data.data;
        for (var i in resultSet) {
            var result = [];
            if (i%2 === 0) {
                result.push("<tr class='even' id='"+resultSet[i].orderId+resultSet[i].id+"'>");
            }else {
                result.push("<tr class='odd' id='"+resultSet[i].orderId+resultSet[i].id+"'>");
            }
            result.push("<td>");
            result.push("<input type='checkbox' class='checkbox' name='id' value='"+resultSet[i].orderId+"' />");
            result.push("</td>");

            result.push("<td>");
            result.push(i);
            result.push("</td>");

            result.push("<td>");
            result.push(resultSet[i].orderCode);
            result.push("</td>");

            result.push("<td>");
            result.push(resultSet[i].createOrderPerson);
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
            result.push(resultSet[i].valuationType === 0? "计时":"计件");
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
            result.push("<a text='"+resultSet[i].orderId+"|"+resultSet[i].id+"' class='editOrder' href='javascript:void(0)'>编辑</a> |");
            if (resultSet[i].check === 0) {
                result.push(" <a text='"+resultSet[i].orderId+"|"+resultSet[i].id+"' class='checkOrder' href='javascript:void(0)'>验收</a> |");
            }
            result.push(" <a text='"+resultSet[i].orderId+"|"+resultSet[i].id+"' class='delOrder' href='javascript:void(0)'>删除</a>");
            result.push("</td>");

            result.push("</tr>");

            $('#styleTable tbody').append(result.join(""));
        }
    }

    function initHtml() {
        localStorage.clear();
        ajax("http://localhost:8080/SM/statistics/v1/orders","get",false,null,initOrderList,error);
    };

    $('#selectOrder').on("click",function() {
        var filter = $('#filter').val();
        var checkStatus = $('#checkStatus').val();
        function resultSuccess(data) {
            $('#styleTable tbody').empty();
            initOrderList(data);
        }
        ajax("http://localhost:8080/SM/statistics/v1/orders?filter="+filter+"&checkStatus="+checkStatus,"get",false,null,resultSuccess,error);
    });

    $(document).on("click",".editOrder",function() {
        var ids = $(this).attr("text").split("|");
        var orderId = ids[0];
        localStorage.setItem("orderId",orderId);
        window.location.href = "http://localhost:8080/SM/index/orderDetail.html";
    });

    $(document).on("click",".delOrder",function() {
        var r=confirm("是否真的要删除？")
        if (r === false) {
            return;
        }
        var ids = $(this).attr("text").split("|");
        var orderId = ids[0];
        var id = ids[1];
        function returnSuccess(data) {
            var realId = data.data+id;
            $('#'+realId).remove();
        }
       ajax("http://localhost:8080/SM/statistics/v1/order?orderId="+orderId+"&id="+id,"delete",false,null,returnSuccess,error);
    });

    function initBox() {
        $('#Ids').val("");
        $('#checkPersonName').val("");
    }

    $(document).on("click",".checkOrder",function() {
        var ids = $(this).attr("text").split("|");
        var orderId = ids[0];
        var id = ids[1];
        $('#Ids').val(id);
        $('#box').toggle();
    });

    $('#submitCheck').on("click",function () {
        var ids = $('#Ids').val();
        var checkPersonName = $('#checkPersonName').val();
        var data = {
            ids:ids,
            checkPersonName:checkPersonName
        };

        function returnSuccess(data) {
            initBox();
            $('#box').toggle();
            $('#styleTable tbody').empty();
            initHtml();
        }

        ajax("http://localhost:8080/SM/statistics/v1/check","PUT",false,data,returnSuccess,error);
    });

    $('#cancelCheck').on("click",function() {
        initBox();
        $('#box').toggle();
    });

    $('#statistics').on("click",function() {
        $('#statisticsResult').html("");
        var statisticsType = $('#statisticsSelect').val();
        var id = $('#statisticsFilter').val();

        if (statisticsType === "" || id === "") {
            alert("统计类型和单号/款号不能为空");
            return;
        }

        function resultSeccess(data) {
            var result = [];
            result.push( "当前单号 / 款号共有");
            result.push(data.data.count);
            result.push("条数据，统计总价为：");
            result.push(data.data.totalPrice);
            result.push("；其中，已验收：");
            result.push(data.data.checkPrice);
            result.push("；待验收：");
            result.push(data.data.unCheckPrice);
            $('#statisticsResult').html(result.join(""));
        }

        ajax("http://localhost:8080/SM/statistics/v1/statisticsResult?id="+id+"&type="+statisticsType,"GET",false,null,resultSeccess,error);
    });

    $('#exportExcelForm').submit(function () {
        console.log("发送表单");
    });

    $('#exportExcel').on("click",function() {
        var result = [];
        $('#styleTable tbody .checkbox').each(function() {
            var value = $(this).attr("value");
            result.push(value);
        });
        var orderIds = result.join(",");
        var data = {
            orderIds:orderIds
        };

        $('#exportOrderIds').val(orderIds);
        $('#exportExcelForm').attr("action","http://localhost:8080/SM/statistics/v1/exportExcel");
        $('#exportExcelForm').submit();
    });

    initHtml();
})();