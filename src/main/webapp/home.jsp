<%--
  Created by IntelliJ IDEA.
  User: AlbertHPW
  Date: 2024/8/3
  Time: 13:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />

  <!--360浏览器优先以webkit内核解析-->

  <title>H+ 后台主题UI框架 - 主页示例</title>

  <link rel="shortcut icon" href="favicon.ico" />
  <link href="/css/bootstrap.min.css?v=3.3.7" rel="stylesheet" />
  <link href="/css/font-awesome.css?v=4.4.0" rel="stylesheet" />

  <link href="/css/animate.css" rel="stylesheet" />
  <link href="/css/style.css?v=4.1.0" rel="stylesheet" />
</head>

<body class="gray-bg">
<div class="row border-bottom white-bg dashboard-header">
  <div class="col-sm-12">
    <blockquote class="text-warning" style="font-size: 14px;">
      基于Servlet+JSP实现的一个校园图书管理系统…
      <br />Servlet+JSP从0开始实现的项目…
      <br />数据库使用的是MySQl…
      <br />通过Apache的DBUtils来实现数据库的操作…
    </blockquote>
  </div>
  <div class="col-sm-3">
    <h2>Hello,Guest</h2>
  </div>
  <div class="col-sm-5">
    <h2>
      H+ 后台主题UI框架
    </h2>
    <p>
      H+是一个完全响应式，基于Bootstrap3.3.7最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术，她提供了诸多的强大的可以重新组合的UI组件，并集成了最新的jQuery版本(v2.1.4)，当然，也集成了很多功能强大，用途广泛的jQuery插件，她可以用于所有的Web应用程序，如<b>网站管理后台</b>，<b>网站会员中心</b>，<b>CMS</b>，<b>CRM</b>，<b>OA</b>等等，当然，您也可以对她进行深度定制，以做出更强系统。
    </p>
  </div>
  <div class="col-sm-4">
    <h4>H+具有以下特点：</h4>
    <ol>
      <li>完全响应式布局（支持电脑、平板、手机等所有主流设备）</li>
      <li>基于最新版本的Bootstrap 3.3.7</li>
      <li>提供3套不同风格的皮肤</li>
      <li>支持多种布局方式</li>
      <li>使用最流行的的扁平化设计</li>
      <li>提供了诸多的UI组件</li>
      <li>集成多款国内优秀插件，诚意奉献</li>
      <li>提供盒型、全宽、响应式视图模式</li>
      <li>采用HTML5 & CSS3</li>
      <li>拥有良好的代码结构</li>
      <li>更多……</li>
    </ol>
  </div>
</div>

<!-- 全局js -->
<script src="/js/jquery.min.js?v=2.1.4"></script>
<script src="/js/bootstrap.min.js?v=3.3.7"></script>
<script src="/js/plugins/layer/layer.min.js"></script>

<!-- 自定义js -->
<script src="/js/content.js"></script>

<!-- 欢迎信息 -->
<script src="/js/welcome.js"></script>
</body>
</html>
