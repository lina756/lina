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
            result.push("<a text='"+resultSet[i].id+"' class='editOrderStyle' href='javascript:void(0)'>编辑</a> ");
            if (resultSet[i].check === 0) {
                result.push("|<a text='"+resultSet[i].id+"' class='checkOrderStyle' href='javascript:void(0)'>验收</a>");
            }
            result.push("| <a text='"+resultSet[i].id+"' class='delOrderStyle' href='javascript:void(0)'>删除</a>");
            result.push("</td>");

            result.push("</tr>");
            $('#orderStyleTable tbody')
            .append(result.join(""));
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
        ajax("http://localhost:8080/SM/statistics/v1/valuation?styleId="+styleId, "get", false, null, initValuationList, error);
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

    function initUpdateBox() {
        $('#updateWorkDate').val("");
        $('#updateStyleCode').val("");
        $("#updateAscription").val("");
        $('#updateStyleName').val("");
        $('#updatePersons').val("");
        $('#updateCount').val("");
        $('#updateValuationType').val("0");
        $('#price').val("");
        $('#increasePrice').val("");
        $('#updateRemark').val("")
        $('#updateEdit').val("")
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
            var size = $('#orderStyleTable tbody').children('tr').length;
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
            result.push("<a text='"+data.id+"' class='editOrderStyle' href='javascript:void(0)'>编辑</a> ");
            if (data.check === 0) {
                result.push("|<a text='"+data.id+"' class='checkOrderStyle' href='javascript:void(0)'>验收</a>");
            }
            result.push("| <a text='"+data.id+"' class='delOrderStyle' href='javascript:void(0)'>删除</a>");
            result.push("</td>");

            result.push("</tr>");

            $('#orderStyleTable tbody').append(result.join(""));
        }

        function returnSuccess(data) {
            initBox();
            $('#box').toggle();
            addTr(data.data);
        }

        ajax("http://localhost:8080/SM/statistics/v1/orderStyle","post",false,data,returnSuccess,error);
    });

    function initHtml() {
        var orderId = localStorage.getItem("orderId");
        if (orderId !== null && orderId !== undefined && orderId !== "") {
            ajax("http://localhost:8080/SM/statistics/v1/orderDetail?orderId=" + orderId, "get", false, null, initOrderDetail, error);
            ajax("http://localhost:8080/SM/statistics/v1/styles", "get", false, null, initStyleList, error);
        }
    }

    $(document).on("click",".editOrderStyle",function () {
        var orderStyleId = $(this).attr("text");
        initUpdateBox();
       $('#updateBox').toggle();

       function returnSuccess(data) {
           var result = data.data;
           $('#updateWorkDate').val(result.workDate);
           $('#updateStyleCode').val(result.styleCode);
           $("#updateAscription").val(result.ascription);
           $('#updateStyleName').val(result.styleName);
           $('#updatePersons').val(result.persons);
           $('#updateCount').val(result.count);
           $('#updateValuationType').val(result.valuationType);
           $('#price').val(result.price);
           $('#increasePrice').val(result.increasePrice);
           $('#updateRemark').val(result.remark);
           $('#updateEdit').val(orderStyleId);
       }

       ajax("http://localhost:8080/SM/statistics/v1/orderStyle?orderStyleId="+orderStyleId, "get", false, null, returnSuccess, error);
    });

    $('#cancelUpdate').on("click",function () {
       initUpdateBox();
        $('#updateBox').toggle();
    });

    $('#updateOrderStyle').on("click",function() {
        var orderId = localStorage.getItem("orderId");
        var workDate = $('#updateWorkDate').val();
        var styleCode = $('#updateStyleCode').val();
        var ascription = $("#updateAscription").val();
        var styleName = $('#updateStyleName').val();
        var persons = $('#updatePersons').val();
        var count = $('#updateCount').val();
        var valuationType = $('#updateValuationType').val();
        var price = $('#price').val();
        var increasePrice = $('#increasePrice').val();
        var remark = $('#updateRemark').val();
        var orderStyleId = $('#updateEdit').val();

        var data = {
            workDate:workDate,
            styleCode:styleCode,
            styleName:styleName,
            ascription:ascription,
            persons:persons,
            count:count,
            valuationType:valuationType,
            price:price,
            increasePrice:increasePrice,
            remark:remark,
            orderId:orderId,
            orderStyleId:orderStyleId
        };

        function returnSuccess(data) {
            $('#updateBox').toggle();
            $('#orderStyleTable tbody').empty();
            ajax("http://localhost:8080/SM/statistics/v1/orderDetail?orderId=" + orderId, "get", false, null, initOrderDetail, error);
        }

        ajax("http://localhost:8080/SM/statistics/v1/orderStyle?orderStyleId="+orderStyleId, "put", false, data, returnSuccess, error)
    });

    $(document).on("click",".delOrderStyle",function() {
        var r=confirm("是否真的要删除？");
        if (r === false) {
            return;
        }
        var id = $(this).attr("text");
        var orderId = localStorage.getItem("orderId");
        function returnSuccess(data) {
            var isDeleteOrder = data.data.isDeleteOrder;
            if (isDeleteOrder) {
                window.location.href = "http://localhost:8080/SM/index/order.html";
            }else {
                var id = data.data.id;
                $("#" + id).remove();
            }
        }
        ajax("http://localhost:8080/SM/statistics/v1/orderStyle?orderId="+orderId+"&id="+id,"delete",false,null,returnSuccess,error);
    });

    $('#addOrderStyle').on("click",function () {
        initBox();
       $('#box').toggle();
    });

    $('#cancelEdit').on("click",function() {
       window.location.href="http://localhost:8080/SM/index/order.html";
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
            window.location.href="http://localhost:8080/SM/index/order.html";
        }

        ajax("http://localhost:8080/SM/statistics/v1/order","put",false,data,returnSuccess,error);
    });

    $(document).on("click",".checkOrderStyle",function() {
        var id = $(this).attr("text");
        $('#Ids').val(id);
        $('#checkBox').toggle();
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
            $('#checkBox').toggle();
            $('#orderStyleTable tbody').empty();
            initHtml();
        }

        ajax("http://localhost:8080/SM/statistics/v1/check","PUT",false,data,returnSuccess,error);
    });

    $('#cancelCheck').on("click",function() {
        initBox();
        $('#checkBox').toggle();
    });

    $('#batchDelete').on("click",function() {
        var r=confirm("是否真的要删除？");
        if (r === false) {
            return;
        }

        var result = [];
        $('#orderStyleTable tbody .checkbox').each(function() {
            if($(this).attr("checked") === "checked") {
                var value = $(this).attr("value");
                result.push(value);
            }
        });
        var orderStyleIds = result.join(",");
        var orderId = localStorage.getItem("orderId");

        function returnSuccess(data) {
            var isDeleteOrder = data.data.isDeleteOrder;
            if (isDeleteOrder) {
                window.location.href = "http://localhost:8080/SM/index/order.html";
            }else {
                var orderStyleIds = data.data.orderStyleIds;
                for (var i in orderStyleIds) {
                    $('#' + orderStyleIds[i]).remove();
                }
            }
        }

        var data = {
            orderStyleIds:orderStyleIds,
            orderId:orderId
        };

        ajax("http://localhost:8080/SM/statistics/v1/order/batchDelete","POST",false,data,returnSuccess,error);
    });

    initHtml();
})();