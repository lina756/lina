/**
 * Created by 磷啊 on 2017/12/3.
 */
(function() {
    function initStyleMessage(data) {
        $('#styleCode').val(data.styleCode);
        $('#styleName').val(data.styleName);
    }

    function initValuationList(data) {
        var resultSet = data.data.valuationVos;
        for (var i in resultSet) {
            var result = new Array();
            if (i%2 === 0) {
                result.push("<tr id='"+resultSet[i].valuationId+"' class='even'>");
            }else {
                result.push("<tr id='"+resultSet[i].valuationId+"' class='odd'>");
            }
            result.push("<td>");
            result.push("<input type='checkbox' class='checkbox' name='id' value='"+resultSet[i].valuationId+"' />");
            result.push("</td>");

            result.push("<td>");
            result.push(resultSet[i].Id);
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

            result.push("<td class='last'>");
            result.push("<a text='"+resultSet[i].valuationId+"' class='editValuation' href='javascript:void(0)'>编辑</a> | <a text='"+resultSet[i].valuationId+"' class='delValuation' href='javascript:void(0)'>删除</a>");
            result.push("</td>");

            result.push("</tr>");

            $('#valuationTable tbody').append(result.join(""));
        }
    }

    function initStyleDetail(data) {
        initStyleMessage(data.data);
        initValuationList(data);
    }

    function initBox() {
        $('#priceType').val("0");
        $('#price').val("");
        $('#increasePrice').val("");
        $('#edit').val("")
    }

    function initHtml() {
        var styleId = localStorage.getItem("styleId");
        ajax("http://106.15.200.24:8080/SM/statistics/v1/styleDetail?styleId="+styleId,"get",false,null,initStyleDetail,error);
    }

    function changeValuationForm() {
        initBox();
        var display = $('#box').css("display");
        if(display == "none") {
            $('#box').show();
        }else {
            $('#box').hide();
        }
    }

    initHtml();

    $('#submitEdit').on("click",function () {
        function returnSuccess() {
            window.location.href="http://localhost:8080/SM/index/style.html";
        }

        var styleCode = $('#styleCode').val();
        var styleName = $('#styleName').val();
        var styleId = localStorage.getItem("styleId");

        var data = {
            styleId:styleId,
            styleCode:styleCode,
            styleName:styleName
        };

        ajax("http://localhost:8080/SM/statistics/v1/style","put",false,data,returnSuccess,error);
    });

    $('#cancelEdit').on("click",function () {
        window.location.href="http://localhost:8080/SM/index/style.html";
    });

    $('#createValuation').on("click",function() {
        changeValuationForm();
    });

    $('#createValuationButton').on("click",function() {
        var priceType = $('#priceType').val();
        var price = $('#price').val();
        var increasePrice = $('#increasePrice').val();
        var styleId = localStorage.getItem("styleId");
        var edit = $('#edit').val();


        var data = {
            valuationId:"",
            valuationType:priceType,
            price:price,
            increasePrice:increasePrice,
            styleId:styleId
        };

        function addTr(data) {
            var result = [];
            var size = $('#valuationTable tbody').children('tr').length;
            if (size%2 === 0) {
                result.push("<tr id='"+data.valuationId+"' class='even'>");
            }else {
                result.push("<tr id='"+data.valuationId+"' class='odd'>");
            }
            result.push("<td>");
            result.push("<input type='checkbox' class='checkbox' name='id' value='"+data.valuationId+"' />");
            result.push("</td>");

            result.push("<td>");
            result.push(data.id);
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

            result.push("<td class='last'>");
            result.push("<a text='"+data.valuationId+"' class='editValuation' href='javascript:void(0)'>编辑</a> | <a text='"+data.valuationId+"' class='delValuation' href='javascript:void(0)'>删除</a>");
            result.push("</td>");

            result.push("</tr>");

            $('#valuationTable tbody').append(result.join(""));
        }

        function returnSuccess(data) {
            changeValuationForm();
            addTr(data.data);
        }

        function editSuccess(data) {
            changeValuationForm();
            $('tr#'+data.data).children().eq(2).text(priceType ==="0"? "计时" : "计件");
            $('tr#'+data.data).children().eq(3).text(price);
            $('tr#'+data.data).children().eq(4).text(increasePrice);
        }

        if (edit !== null &&edit !== undefined && edit !== "") {
            data.valuationId = edit;
            ajax("http://localhost:8080/SM/statistics/v1/valuation", "put", false, data, editSuccess, error);
        }else {
            ajax("http://localhost:8080/SM/statistics/v1/valuation", "post", false, data, returnSuccess, error);
        }
    });

    $(document).on("click",".delValuation",function () {
        var r=confirm("是否真的要删除？")
        if (r === false) {
            return;
        }

        var valuationId = $(this).attr("text");

        function returnSuccess(data) {
            $("tr#"+data.data).remove();
        }
       ajax("http://localhost:8080/SM/statistics/v1/valuation?valuationId="+valuationId,"delete",false,null,returnSuccess,error);
    });

    $(document).on("click",".editValuation",function() {
        $('#box').toggle();
        var valuationId = $(this).attr("text");
        var priceType = $('tr#'+valuationId).children().eq(2).text();
        var price = $('tr#'+valuationId).children().eq(3).text();
        var increasePrice = $('tr#'+valuationId).children().eq(4).text();
        $('#priceType').val(priceType == "计时"?0:1);
        $('#price').val(price);
        $('#increasePrice').val(increasePrice);
        $("#edit").val(valuationId);
    });

})();