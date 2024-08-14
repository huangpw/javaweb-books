<%--
  Created by IntelliJ IDEA.
  User: AlbertHPW
  Date: 2024/8/3
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>校园图书管理系统 - 图书管理</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    <link rel="shortcut icon" href="favicon.ico">
    <link href="/css/bootstrap.min.css?v=3.3.7" rel="stylesheet">
    <link href="/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="/css/animate.css" rel="stylesheet">
    <!-- Sweet Alert -->
    <link href="/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    <link href="/css/style.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="tabs-container">
                <ul class="nav nav-tabs">
                    <c:forEach items="${ requestScope.list }" var="entity">
                        <li class="">
                            <a data-toggle="tab" href="#tab-${entity.id}" aria-expanded="true">${entity.name}</a>
                        </li>
                    </c:forEach>
                </ul>
                <div class="tab-content">
                    <c:forEach items="${ requestScope.list }" var="entity">
                        <div id="tab-${entity.id}" class="tab-pane">
                            <div class="panel-body">
                                <div class="row">
                                    <c:forEach items="${entity.books}" var="book">
                                        <div class="col-sm-4">
                                            <div class="contact-box">
                                                <div class="col-sm-4">
                                                    <div class="text-center">
                                                        <img alt="image" class="m-t-xs img-responsive"
                                                             src="/sys/downloadServlet?fileName=${book.img}">
                                                        <div class="m-t-xs m-b-xs font-bold"></div>
                                                        <c:if test="${book.state == 0}">
                                                            <div style="margin-top: 16px;">
                                                                <button type="button" class="btn btn-primary" style="width: 100%;" data-target="#myModal" onclick="goBorrowing(${book.id})">借阅</button>
                                                            </div>
                                                        </c:if>
                                                        <c:if test="${book.state != 0}">
                                                            <div style="margin-top: 16px;">
                                                                <button type="button" class="btn btn-default" style="width: 100%;">已借出</button>
                                                            </div>
                                                        </c:if>
                                                    </div>
                                                </div>
                                                <div class="col-sm-8">
                                                    <h3><strong>${book.name}</strong></h3>
                                                    <p>
                                                        <i class="fa fa-user"></i>
                                                        <span>${book.author}</span>
                                                    </p>
                                                    <address>
                                                        <div style="display: flex;align-items: center;">
                                                            <i class="fa fa-cny"></i>
                                                            <strong>${book.price}</strong>
                                                        </div>
                                                        <div>出版社:${book.publish}</div>
                                                        <div style="display: block;
                                                        white-space: nowrap;
                                                        overflow: hidden;
                                                        text-overflow: ellipsis;">简介:${book.notes}</div>
                                                        <div>
                                                            ISBN:${book.isbn}
                                                        </div>
                                                        <%--<abbr title="isbn">ISBN:</abbr>${book.isbn}--%>
                                                    </address>
                                                </div>
                                                <div class="clearfix"></div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
    <div class="modal inmodal" id="myModal" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content animated bounceInRight">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
                    </button>
                    <i class="fa fa-laptop modal-icon"></i>
                    <h4 class="modal-title">借阅图书</h4>
                    <small class="font-bold">请选择对应的借书卡来借阅。
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label>借书卡</label>
                        <select class="form-control m-b" id="cardid" name="cardid">

                        </select>
                        <input type="hidden" id="bookid" name="bookid">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" onclick="submitData()">保存</button>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 全局js -->
<script src="/js/jquery.min.js?v=2.1.4"></script>
<script src="/js/bootstrap.min.js?v=3.3.7"></script>
<!-- 自定义js -->
<script src="/js/content.js?v=1.0.0"></script>
<!-- Sweet alert -->
<script src="/js/plugins/sweetalert/sweetalert.min.js"></script>

<script>
    $(document).ready(function () {
        $(".tabs-container .nav-tabs li").click(function () {
            let href = $(this).children()[0].href;
            // 做字符串的截取操作
            let aId = href.substring(href.lastIndexOf('tab-'), href.length);
            // 先给所有的 class= tab-pane 的都移除掉 active 属性
            $(".tab-pane").removeClass('active')
            // 然后单独给当前点击的添加 active 属性
            $("#" + aId).addClass("active")
        })
        initTab()
    })

    function initTab() {
        let li = $(".tabs-container .nav-tabs").children()[0];
        $(li).addClass('active')
        //var href = $($(".tabs-container .nav-tabs").children()[0]).children()[0].href
        var href = $(li).children()[0].href
        // 做字符串的截取操作
        var aId = href.substring(href.lastIndexOf('tab-'), href.length);
        // 先给所有的 class= tab-pane 的都移除掉 active 属性
        $(".tab-pane").removeClass('active')
        // 然后单独给当前点击的添加 active 属性
        $("#" + aId).addClass("active")
    }
    // 借阅
    function goBorrowing(bookId) {
        // 判断是否有可用的借书卡
        $.get("/book/borrowCardServlet?action=checkHaveCard", function (data) {
            console.log(data)
            if(data.msg == "ok") {
                // 可以借阅
                // 绑定数据
                $("#bookid").val(bookId)
                // 下拉菜单的信息
                let option = ""
                for (let i = 0; i < data.cards.length; i++) {
                    let card = data.cards[i]
                    option += "<option value='" + card.id + "'>" + card.id + "[" + timestampToDate(card.starttime) + "至" + timestampToDate(card.endtime) + "]" + "</option>"
                }
                $("#cardid").html(option)
                // 打开模态窗口
                $("#myModal").modal('show')

            } else {
                //不可以借阅
                swal("借阅失败！", "可以使用的借书卡不够，请申请后借阅。", "warning");
            }
        })
    }
    function timestampToDate(timestamp) {
        let date = new Date(timestamp)
        let year = date.getFullYear();
        let month = date.getMonth() + 1;
        let day = date.getDate();
        let hour = date.getHours();
        let minute = date.getMinutes();
        let second = date.getSeconds();
        return  year + "-" + (month < 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day) + " " + (hour < 10 ? "0" + hour : hour) + ":" + (minute < 10 ? "0" + minute : minute) + ":" + (second < 10 ? "0" + second : second);
    }
    // 提交借阅信息
    function submitData() {
        // 获取bookid 和 cardid
        let bookid = $("#bookid").val()
        let cardid = $("#cardid").val()
        // 发送请求
        $.get("/book/borrowRecoderServlet?action=saveOrUpdate&bookid="+ bookid +"&cardid=" + cardid, function (data) {
            if(data == "ok") {
                swal("借阅成功！", "请按时归还。", "success");
                // 刷新页面
                //window.location.reload()
                location.href = "/book/showBookServlet"
            } else {
                swal("借阅失败！", "请稍后再试。", "error");
            }
        })
    }
</script>

</body>

</html>
