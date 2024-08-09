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
                                                                <button type="button" class="btn btn-primary" style="width: 100%;">借阅</button>
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
</div>

<!-- 全局js -->
<script src="/js/jquery.min.js?v=2.1.4"></script>
<script src="/js/bootstrap.min.js?v=3.3.7"></script>

<!-- 自定义js -->
<script src="/js/content.js?v=1.0.0"></script>

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

</script>

</body>

</html>
