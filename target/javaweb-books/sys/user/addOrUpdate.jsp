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


    <title>校园图书管理系统 - 用户管理</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    <link rel="shortcut icon" href="favicon.ico"> <link href="/css/bootstrap.min.css?v=3.3.7" rel="stylesheet">
    <link href="/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="/css/animate.css" rel="stylesheet">
    <!-- 百度WebUpload插件 -->
    <link rel="stylesheet" type="text/css" href="/css/plugins/webuploader/webuploader.css">
<%--    <link rel="stylesheet" type="text/css" href="/css/demo/webuploader-demo.css">--%>

    <link href="/css/style.css?v=4.1.0" rel="stylesheet">
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>用户管理</h5>
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
                    <form class="form-horizontal m-t" id="signupForm" action="/sys/userServlet?action=saveOrUpdate" method="post">
                        <input type="hidden" name="id" value="${requestScope.entity.id}">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">用户名：</label>
                            <div class="col-sm-8">
                                <input id="username" name="username" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error" value="${requestScope.entity.username}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">昵称：</label>
                            <div class="col-sm-8">
                                <input id="nickname" name="nickname" class="form-control" type="text" aria-required="true" aria-invalid="false" class="valid" value="${requestScope.entity.nickname}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">密码：</label>
                            <div class="col-sm-8">
                                <input id="password" name="password" class="form-control" type="password" value="${requestScope.entity.password}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">确认密码：</label>
                            <div class="col-sm-8">
                                <input id="confirm_password" name="confirm_password" class="form-control" type="password" value="${requestScope.entity.password}">
                                <span class="help-block m-b-none"><i class="fa fa-info-circle"></i> 请再次输入您的密码</span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">分配角色：</label>
                            <div class="col-sm-8">
                                <select class="form-control m-b" name="roleId">
                                    <c:forEach items="${requestScope.roles}" var="role">
                                        <option value="${role.id}" ${role.id == requestScope.entity.roleId ? 'selected' : ''}>${role.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">头像：</label>
                            <input type="hidden" name="img" id="imgName" value="${requestScope.entity.img}">
                            <div class="col-sm-8">
                                <!--dom结构部分-->
                                <div id="uploader-demo">
                                    <!--用来存放item-->
                                    <span id="img">${requestScope.entity.img}</span>
                                    <div id="fileList" class="uploader-list" value="${requestScope.entity.img}"></div>
                                    <div id="filePicker">选择图片</div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-8 col-sm-offset-3">
                                <button class="btn btn-primary" type="submit">提交</button>
                                <a class="btn btn-default" href="/sys/userServlet?action=list">取消</a>
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

<!-- Web Uploader -->
<script type="text/javascript">
    // 添加全局站点信息
    const BASE_URL = '/js/plugins/webuploader';
</script>
<script src="/js/plugins/webuploader/webuploader.min.js"></script>

<%--<script src="/js/demo/webuploader-demo.js"></script>--%>

<!-- jQuery Validation plugin javascript-->
<script src="/js/plugins/validate/jquery.validate.min.js"></script>
<script src="/js/plugins/validate/messages_zh.min.js"></script>

<script type="text/javascript" src="/js/demo/form-validate-demo.js"></script>

<script>

    // 初始化Web Uploader
    var uploader = WebUploader.create({

        // 选完文件后，是否自动上传。
        auto: true,

        // swf文件路径
        swf: BASE_URL + '/Uploader.swf',

        // 文件接收服务端。
        server: '/sys/uploadServlet',

        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '#filePicker',

        // 只允许选择图片文件。
        accept: {
            title: 'Images',
            extensions: 'gif,jpg,jpeg,bmp,png',
            mimeTypes: 'image/*'
        }
    });

    // 文件上传成功，给item添加成功class, 用样式标记上传成功。
    uploader.on( 'uploadSuccess', function( file, data ) {
        //_raw
        console.log('uploadSuccess', file, data);
        $( '#'+file.id ).addClass('upload-state-done');
        $('#imgName').val(data._raw);
        //$('#fileList').text(data._raw);
        $('#img').text(data._raw);
    });


</script>

</body>

</html>

