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


    <title>校园图书管理系统 - 图书管理</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    <link rel="shortcut icon" href="favicon.ico"> <link href="/css/bootstrap.min.css?v=3.3.7" rel="stylesheet">
    <link href="/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="/css/animate.css" rel="stylesheet">

    <!-- 百度WebUpload插件 -->
    <link rel="stylesheet" type="text/css" href="/css/plugins/webuploader/webuploader.css">

    <link href="/css/style.css?v=4.1.0" rel="stylesheet">
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>图书管理</h5>
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
                    <form class="form-horizontal m-t" id="signupForm" action="/book/bookServlet?action=saveOrUpdate" method="post">
                        <input type="hidden" name="id" value="${requestScope.entity.id}">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">名称：</label>
                            <div class="col-sm-8">
                                <input id="name" name="name" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error" value="${requestScope.entity.name}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">作者：</label>
                            <div class="col-sm-8">
                                <input id="author" name="author" class="form-control" type="text" aria-required="true" aria-invalid="false" class="valid" value="${requestScope.entity.author}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">出版社：</label>
                            <div class="col-sm-8">
                                <%--<input id="publish" name="publish" class="form-control" type="text" aria-required="true" aria-invalid="false" class="valid" value="${requestScope.entity.notes}">--%>
                                <select class="form-control m-b" name="publish">
                                    <option value="人民文学出版社" ${requestScope.entity.publish == '人民文学出版社' ? 'selected' : ''}>人民文学出版社</option>
                                    <option value="商务印书馆" ${requestScope.entity.publish == '商务印书馆' ? 'selected' : ''}>商务印书馆</option>
                                    <option value="人民出版社" ${requestScope.entity.publish == '人民出版社' ? 'selected' : ''}>人民出版社</option>
                                    <option value="中华书局" ${requestScope.entity.publish == '中华书局' ? 'selected' : ''}>中华书局</option>
                                    <option value="上海译文出版社" ${requestScope.entity.publish == '上海译文出版社' ? 'selected' : ''}>上海译文出版社</option>
                                    <option value="三联书店" ${requestScope.entity.publish == '三联书店' ? 'selected' : ''}>三联书店</option>
                                    <option value="机械工业出版社" ${requestScope.entity.publish == '机械工业出版社' ? 'selected' : ''}>机械工业出版社</option>
                                    <option value="人民邮电出版社" ${requestScope.entity.publish == '人民邮电出版社' ? 'selected' : ''}>人民邮电出版社</option>
                                    <option value="上海译文出版社" ${requestScope.entity.publish == '上海人民出版社' ? 'selected' : ''}>上海人民出版社</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">ISBN：</label>
                            <div class="col-sm-8">
                                <input id="isbn" name="isbn" class="form-control" type="text" aria-required="true" aria-invalid="false" class="valid" value="${requestScope.entity.isbn}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">价格：</label>
                            <div class="col-sm-8">
                                <input id="price" name="price" class="form-control" type="number" aria-required="true" aria-invalid="false" class="valid" value="${requestScope.entity.price}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">类型：</label>
                            <div class="col-sm-8">
                                <select class="form-control m-b" name="typeId">
                                    <c:forEach items="${requestScope.types}" var="type">
                                        <option value="${type.id}" ${type.id == requestScope.entity.typeId ? 'selected' : ''}>${type.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">描述：</label>
                            <div class="col-sm-8">
                                <textarea id="notes" name="notes" class="form-control" aria-required="true" aria-invalid="false" class="valid">${requestScope.entity.notes}</textarea>
                                <%--<input id="notes" name="notes" class="form-control" type="text" aria-required="true" aria-invalid="false" class="valid" value="${requestScope.entity.notes}">--%>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">图片：</label>
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
                                <a class="btn btn-default" href="/book/bookServlet?action=list">取消</a>
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

