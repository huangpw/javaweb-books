<%--
  Created by IntelliJ IDEA.
  User: AlbertHPW
  Date: 2024/8/3
  Time: 17:15
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

    <link rel="shortcut icon" href="favicon.ico"> <link href="/css/bootstrap.min.css?v=3.3.7" rel="stylesheet">
    <link href="/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="/css/animate.css" rel="stylesheet">
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
                        <a class="dropdown-toggle" data-toggle="dropdown" href="form_basic.html#">
                            <i class="fa fa-wrench"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-user">
                            <li><a href="form_basic.html#">选项1</a>
                            </li>
                            <li><a href="form_basic.html#">选项2</a>
                            </li>
                        </ul>
                        <a class="close-link">
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <form class="form-horizontal m-t" id="signupForm" action="/sys/menuServlet?action=saveOrUpdate" method="post">
                        <input type="hidden" name="id" value="${entity.id}">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">名称：</label>
                            <div class="col-sm-8">
                                <input id="name" name="name" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error" value="${entity.name}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">访问地址：</label>
                            <div class="col-sm-8">
                                <input id="url" name="url" class="form-control" type="text" aria-required="true" aria-invalid="false" class="valid" value="${entity.url}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">父菜单：</label>
                            <div class="col-sm-8">
                                <select class="form-control m-b" name="parentId">
                                    <option value="-1">---本身就是父菜单---</option>
                                    <c:forEach items="${requestScope.parents}" var="parent">
                                        <option value="${parent.id}" ${parent.id == requestScope.entity.parentId?'selected':''}>${parent.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">序号：</label>
                            <div class="col-sm-8">
                                <input id="seq" name="seq" class="form-control" type="number" aria-required="true" aria-invalid="false" class="valid" value="${entity.seq}">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-8 col-sm-offset-3">
                                <button class="btn btn-primary" type="submit">提交</button>
                                <a class="btn btn-default" href="/sys/menuServlet?action=list">取消</a>
                            </div>
                        </div>
                    </form>
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

<!-- jQuery Validation plugin javascript-->
<script src="/js/plugins/validate/jquery.validate.min.js"></script>
<script src="/js/plugins/validate/messages_zh.min.js"></script>

<script type="text/javascript" src="/js/demo/form-validate-demo.js"></script>

<script>

</script>

</body>

</html>

