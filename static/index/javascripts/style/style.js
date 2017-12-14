/**
 * Created by 磷啊 on 2017/12/2.
 */
(function() {
    function initTable() {
        localStorage.clear();
        var page = 1;
        var pageSize = 10;
        ajax("http://localhost:8080/SM/statistics/v1/styles?page="+page+"&pageSize="+pageSize,"get",false,null,render,error);
    }
    function init() {
        $('#styleCode').val("");
        $('#styleName').val("");
        $('#ascription').val("");
        $('#priceType').val("0");
        $('#price').val("");
        $('#increasePrice').val("");
        $('#styleTable tbody').html("");
    }

    function createSuccess() {
        $('#box').hide();
        initTable();
    }
    
    function initHead() {
        var html = [];
        html.push("<li class='first'><a id='createStyle' href='javascript:void(0)'>款号</a></li>");
        $('#main-navigation .wat-cf').append(html.join(""));
    }

    function deleteSuccess(data) {
        init();
        initTable();
    }

    function render(data) {
        var resultSet = data.data;
        for (var i in resultSet) {
            var result = new Array();
            if (i%2 === 0) {
                result.push("<tr class='even'>");
            }else {
                result.push("<tr class='odd'>");
            }
            result.push("<td>");
            result.push("<input type='checkbox' class='checkbox' name='id' value='"+resultSet[i].styleId+"' />");
            result.push("</td>");

            result.push("<td>");
            result.push(resultSet[i].id);
            result.push("</td>");

            result.push("<td>");
            result.push(resultSet[i].styleCode);
            result.push("</td>");

            result.push("<td>");
            result.push(resultSet[i].styleName);
            result.push("</td>");

            result.push("<td class='last'>");
            result.push("<a text='"+resultSet[i].styleId+"' class='editStyle' href='javascript:void(0)'>编辑</a> | <a text='"+resultSet[i].styleId+"' class='delStyle' href='javascript:void(0)'>删除</a>");
            result.push("</td>");

            result.push("</tr>");

            $('#styleTable tbody').append(result.join(""));
        }
    };

    $(document).on("click","#createStyle",function() {
        var status = $('#box').css("display");
        if (status == "none") {
            $('#box').css("display","block");
        }else {
            $('#box').css("display","none");
        }

    });

    $('#createStyleButton').on("click",function() {
        var styleCode = $('#styleCode').val();
        var styleName = $('#styleName').val();
        var priceType = $('#priceType').val();
        var price = $('#price').val();
        var increasePrice = $('#increasePrice').val();
        var data = {
            styleCode:styleCode,
            styleName:styleName,
            priceType:priceType,
            price:price,
            increasePrice:increasePrice
        };

        function addTr(data) {

        }

        function createSuccess(data) {
            $('#box').hide();
            addTr(data.data);
        }

        ajax("http://localhost:8080/SM/statistics/v1/style","post",false,data,createSuccess,error);
    });

    $(document).on("click",'.editStyle',function () {
        var styleId = $(this).attr("text");
        console.log(styleId);
        localStorage.setItem("styleId",styleId);
        window.location.href="http://localhost:8080/SM/index/styleDetail.html";
    });

    $(document).on("click",".delStyle",function () {
        var r=confirm("是否真的要删除？")
        if (r === false) {
            return;
        }
        var styleId = $(this).attr("text");
        console.log(styleId)

        ajax(
            "http://localhost:8080/SM/statistics/v1/style?styleId="+styleId,
            "delete",
            false,
            null,
            deleteSuccess,
            error);
    });

    initHead();
    initTable();
})();