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


    <title>校园图书管理系统 - 角色管理</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    <link rel="shortcut icon" href="favicon.ico"> <link href="/css/bootstrap.min.css?v=3.3.7" rel="stylesheet">
    <link href="/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="/css/animate.css" rel="stylesheet">
    <link href="/css/style.css?v=4.1.0" rel="stylesheet">
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>角色管理</h5>
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
                    <form class="form-horizontal m-t" id="signupForm" action="/sys/roleServlet?action=saveOrUpdate" method="post">
                        <input type="hidden" name="id" value="${requestScope.entity.id}">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">名称：</label>
                            <div class="col-sm-8">
                                <input id="name" name="name" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error" value="${requestScope.entity.name}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">描述：</label>
                            <div class="col-sm-8">
                                <input id="notes" name="notes" class="form-control" type="text" aria-required="true" aria-invalid="false" class="valid" value="${requestScope.entity.notes}">
                            </div>
                        </div>
                        <c:if test="${not empty requestScope.entity}">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">分配菜单：</label>
                                <div class="col-sm-8">
                                    <c:forEach items="${requestScope.menus}" var="parent">
                                        <c:if test="${ parent.parentId == -1}">
                                            <div class="checkbox i-checks">
                                                <label>
                                                    <input type="checkbox" name="menuIds" ${ parent.check ? 'checked' : '' } value="${ parent.id }"> <i></i> ${ parent.name }
                                                </label>
                                            </div>
                                            <c:forEach items="${requestScope.menus}" var="child">
                                                <c:if test="${ parent.id == child.parentId }">
                                                    <div class="checkbox i-checks" style="text-indent: 16px;">
                                                        <label>
                                                            <input type="checkbox" name="menuIds" ${ parent.check ? 'checked' : '' } value="${ child.id }"> <i></i> ${ child.name }
                                                        </label>
                                                    </div>
                                                </c:if>
                                            </c:forEach>
                                        </c:if>
                                    </c:forEach>
                                </div>
                            </div>
                        </c:if>

                        <div class="form-group">
                            <div class="col-sm-8 col-sm-offset-3">
                                <button class="btn btn-primary" type="submit">提交</button>
                                <a class="btn btn-default" href="/sys/roleServlet?action=list">取消</a>
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

<!-- iCheck -->
<script src="/js/plugins/iCheck/icheck.min.js"></script>
<script>
    $(document).ready(function () {
        $('.i-checks').iCheck({
            checkboxClass: 'icheckbox_square-green',
            radioClass: 'iradio_square-green',
        });
    });
</script>

</body>

</html>

