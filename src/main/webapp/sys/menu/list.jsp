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


    <title>校园图书管理系统 - 菜单管理</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    <link rel="shortcut icon" href="favicon.ico">
    <link href="/css/bootstrap.min.css?v=3.3.7" rel="stylesheet">
    <link href="/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="/css/animate.css" rel="stylesheet">
    <!-- Sweet Alert -->
    <link href="/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    <link href="/css/style.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>菜单管理</h5>
                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                        <a class="dropdown-toggle" data-toggle="dropdown" href="table_basic.html#">
                            <i class="fa fa-wrench"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-user">
                            <li><a href="table_basic.html#">选项1</a>
                            </li>
                            <li><a href="table_basic.html#">选项2</a>
                            </li>
                        </ul>
                        <a class="close-link">
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <div class="row">
                        <div class="col-sm-9 m-b-xs">
                            <a class="btn btn-success " type="button" href="/sys/menuServlet?action=saveOrUpdatePage">
                                <i class="fa fa-plus"></i>&nbsp;添加
                            </a>
                        </div>
                        <div class="col-sm-3">
                            <div class="input-group">
                                <form action="/sys/menuServlet" id="myForm" method="get">
                                    <div class="input-group">
                                        <input type="text" name="key" value="${requestScope.pageUtils.key}" placeholder="请输入关键词" class="input-sm form-control">
                                        <span class="input-group-btn">
                                            <button type="button" class="btn btn-sm btn-primary" onclick="onSearch()">搜索</button>
                                        </span>
                                        <input type="hidden" name="action" value="list">
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                            <tr>

                                <th></th>
                                <th>编号</th>
                                <th>名称</th>
                                <th>地址</th>
<%--                                <th>父菜单</th>--%>
                                <th>序号</th>
                                <th>日期</th>
                                <th style="width: 200px">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${requestScope.list}" var="entity">
                                <%-- 取出所有的父菜单 --%>
                                <c:if test="${ entity.parentId == -1 }">
                                    <tr>
                                        <td>
                                            <input type="checkbox" class="i-checks" name="input[]">
                                        </td>
                                        <td>${entity.id}</td>
                                        <td>${entity.name}</td>
                                        <td>${entity.url}</td>
<%--                                        <td>${entity.parentId}</td>--%>
                                        <td>${entity.seq}</td>
                                        <td>${entity.createTime}</td>
                                        <td>
                                            <a class="btn btn-warning " type="button"
                                               href="/sys/menuServlet?action=saveOrUpdatePage&id=${entity.id}">
                                                <i class="fa fa-edit"></i>更新
                                            </a>
                                            <button class="btn btn-danger " type="button"
                                                    onclick="removeData(${ entity.id })">
                                                <i class="fa fa-remove"></i>&nbsp;删除
                                            </button>
                                        </td>
                                    </tr>
                                    <%-- 根据当前循环获取的父菜单编号来获取对应的子菜单 --%>
                                    <c:forEach items="${requestScope.list}" var="child">
                                        <c:if test="${ child.parentId == entity.id }">
                                            <tr>
                                                <td>
                                                    <input type="checkbox" class="i-checks" name="input[]">
                                                </td>
                                                <td style="text-indent: 32px;">${child.id}</td>
                                                <td style="text-indent: 32px;">${child.name}</td>
                                                <td>${child.url}</td>
<%--                                                <td>${child.parentId}</td>--%>
                                                <td>${child.seq}</td>
                                                <td>${child.createTime}</td>
                                                <td>
                                                    <a class="btn btn-warning " type="button"
                                                       href="/sys/menuServlet?action=saveOrUpdatePage&id=${child.id}">
                                                        <i class="fa fa-edit"></i>更新
                                                    </a>
                                                    <button class="btn btn-danger " type="button"
                                                            onclick="removeData(${ child.id })">
                                                        <i class="fa fa-remove"></i>&nbsp;删除
                                                    </button>
                                                </td>
                                            </tr>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>

<!-- 全局js -->
<script src="/js/jquery.min.js?v=2.1.4"></script>
<script src="/js/bootstrap.min.js?v=3.3.7"></script>

<!-- Peity -->
<script src="/js/plugins/peity/jquery.peity.min.js"></script>

<!-- 自定义js -->
<script src="/js/content.js?v=1.0.0"></script>

<!-- iCheck -->
<script src="/js/plugins/iCheck/icheck.min.js"></script>

<!-- Peity -->
<script src="/js/demo/peity-demo.js"></script>

<!-- Sweet alert -->
<script src="/js/plugins/sweetalert/sweetalert.min.js"></script>

<script>
    $(document).ready(function () {
        $('.i-checks').iCheck({
            checkboxClass: 'icheckbox_square-green',
            radioClass: 'iradio_square-green',
        });
    });

    // 删除
    function removeData(id) {
        swal({
            title: "您确定要删除这条信息吗",
            text: "删除后将无法恢复，请谨慎操作！",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "删除",
            cancelButtonText: "取消",
            closeOnConfirm: false
        }, function () {
            // swal("删除成功！", "您已经永久删除了这条信息。", "success");
            $.get("/sys/menuServlet?action=remove&id=" + id, function (msg) {
                if(msg === 'ok') {
                    // 再发起一个查询的操作
                    window.location.href = "/sys/menuServlet?action=list"
                } else {
                    // 表示不能被删除
                    swal("删除失败！", "该菜单不能删除!!!。", "warning");
                }

            })
        });
    }

    // 搜索
    function onSearch() {
        $("#pageNum").val(1);
        $("#myForm").submit();
    }

</script>
</body>

</html>
