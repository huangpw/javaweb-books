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
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <!--360浏览器优先以webkit内核解析-->

    <title>H+ 后台主题UI框架 - 主页示例</title>

    <link rel="shortcut icon" href="favicon.ico"/>
    <link href="/css/bootstrap.min.css?v=3.3.7" rel="stylesheet"/>
    <link href="/css/font-awesome.css?v=4.4.0" rel="stylesheet"/>

    <link href="/css/animate.css" rel="stylesheet"/>
    <link href="/css/style.css?v=4.1.0" rel="stylesheet"/>
</head>

<body class="gray-bg">
<div class="row border-bottom white-bg dashboard-header">
    <div class="col-sm-12">
        <blockquote class="text-warning" style="font-size: 14px;">
            基于Servlet+JSP实现的一个校园图书管理系统…
            <br/>Servlet+JSP从0开始实现的项目…
            <br/>数据库使用的是MySQl…
            <br/>通过Apache的DBUtils来实现数据库的操作…
        </blockquote>
    </div>
    <div class="col-sm-6">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>统计书籍信息</h5>
                <div class="ibox-tools">
                    <a class="collapse-link">
                        <i class="fa fa-chevron-up"></i>
                    </a>
                    <a class="dropdown-toggle" data-toggle="dropdown" href="graph_flot.html#">
                        <i class="fa fa-wrench"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="graph_flot.html#">选项1</a>
                        </li>
                        <li><a href="graph_flot.html#">选项2</a>
                        </li>
                    </ul>
                    <a class="close-link">
                        <i class="fa fa-times"></i>
                    </a>
                </div>
            </div>
            <div class="ibox-content">
                <div class="echarts" id="echarts-book-chart" style="height: 400px;"></div>
            </div>
        </div>
    </div>
    <div class="col-sm-6">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>统计书籍信息</h5>
                <div class="ibox-tools">
                    <a class="collapse-link">
                        <i class="fa fa-chevron-up"></i>
                    </a>
                    <a class="dropdown-toggle" data-toggle="dropdown" href="graph_flot.html#">
                        <i class="fa fa-wrench"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="graph_flot.html#">选项1</a>
                        </li>
                        <li><a href="graph_flot.html#">选项2</a>
                        </li>
                    </ul>
                    <a class="close-link">
                        <i class="fa fa-times"></i>
                    </a>
                </div>
            </div>
            <div class="ibox-content">
                <div class="echarts" id="echarts-book-chart2" style="height: 400px;"></div>
            </div>
        </div>
    </div>
    <div class="col-sm-12">
<%--        <jsp:include page="map.jsp" />--%>
<%--        <%@include file="map.jsp"%>--%>
        <iframe src="/map.jsp" width="100%" height="100%" frameborder="0"></iframe>
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
<!-- ECharts -->
<script src="/js/echarts.min.js"></script>


<script type="text/javascript">

    $(document).ready(function () {
        let xAxisChart1 = null;
        let dataChart1 = null;
        let dataChart2 = null;
        // 需要去后台加载相关数据
        $.get("/homeServlet", function (data) {
            // 更新option中的信息
            xAxisChart1 = data.typeNames
            dataChart1 = data.bookNums
            dataChart2 = data.chart2
            initChart1(xAxisChart1, dataChart1)
            initChart2(dataChart2)
        })
    })
    // 第一个统计图
    function initChart1(xAxisChart1, dataChart1) {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('echarts-book-chart'));

        // 指定图表的配置项和数据
        var option = {
            tooltip: {},
            legend: {
                data: ['数量']
            },
            xAxis: {
                data: ['衬衫', '羊毛衫', '雪纺衫', '裤子', '高跟鞋', '袜子']
            },
            yAxis: {},
            series: [
                {
                    name: '数量',
                    type: 'line',
                    data: [5, 20, 36, 10, 10, 20],
                    smooth: true
                }
            ]
        };
        option.xAxis.data = xAxisChart1
        option.series[0].data = dataChart1

        // 使用刚指定的配置项和数据显示图表
        myChart.setOption(option);

    }
    // 第二个统计图
    function initChart2(dataChart2) {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('echarts-book-chart2'));

        // 指定图表的配置项和数据
        var option = {
            tooltip: {
                trigger: 'item'
            },
            legend: {
                orient: 'vertical',
                left: 'left'
            },
            series: [
                {
                    type: 'pie',
                    data: [
                        {
                            value: 335,
                            name: '直接访问'
                        },
                        {
                            value: 234,
                            name: '联盟广告'
                        },
                        {
                            value: 1548,
                            name: '搜索引擎'
                        }
                    ]
                }
            ]
        };
        option.series[0].data = dataChart2
        // 使用刚指定的配置项和数据显示图表
        myChart.setOption(option);
    }

</script>

</body>
</html>
