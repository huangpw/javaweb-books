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
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>中国地图</h5>
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
                <div style="height:600px" id="echarts-map-chart"></div>
            </div>
        </div>
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

<!-- ECharts demo data -->
<%--<script src="/js/demo/echarts-demo.js"></script>--%>

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
        initChart3()
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


    function initChart3() {
        // 中国地图
        var mapChart = echarts.init(document.getElementById("echarts-map-chart"));
        var mapoption = {
            title : {
                text: 'iphone销量',
                subtext: '纯属虚构',
                x:'center'
            },
            tooltip : {
                trigger: 'item'
            },
            legend: {
                orient: 'vertical',
                x:'left',
                data:['iphone3','iphone4','iphone5']
            },
            dataRange: {
                min: 0,
                max: 2500,
                x: 'left',
                y: 'bottom',
                text:['高','低'],           // 文本，默认为数值文本
                calculable : true
            },
            toolbox: {
                show: true,
                orient : 'vertical',
                x: 'right',
                y: 'center',
                feature : {
                    mark : {show: true},
                    dataView : {show: true, readOnly: false},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            roamController: {
                show: true,
                x: 'right',
                mapTypeControl: {
                    'china': true
                }
            },
            series : [
                {
                    name: 'iphone3',
                    type: 'map',
                    mapType: 'china',
                    roam: false,
                    itemStyle:{
                        normal:{label:{show:true}},
                        emphasis:{label:{show:true}}
                    },
                    data:[
                        {name: '北京',value: Math.round(Math.random()*1000)},
                        {name: '天津',value: Math.round(Math.random()*1000)},
                        {name: '上海',value: Math.round(Math.random()*1000)},
                        {name: '重庆',value: Math.round(Math.random()*1000)},
                        {name: '河北',value: Math.round(Math.random()*1000)},
                        {name: '河南',value: Math.round(Math.random()*1000)},
                        {name: '云南',value: Math.round(Math.random()*1000)},
                        {name: '辽宁',value: Math.round(Math.random()*1000)},
                        {name: '黑龙江',value: Math.round(Math.random()*1000)},
                        {name: '湖南',value: Math.round(Math.random()*1000)},
                        {name: '安徽',value: Math.round(Math.random()*1000)},
                        {name: '山东',value: Math.round(Math.random()*1000)},
                        {name: '新疆',value: Math.round(Math.random()*1000)},
                        {name: '江苏',value: Math.round(Math.random()*1000)},
                        {name: '浙江',value: Math.round(Math.random()*1000)},
                        {name: '江西',value: Math.round(Math.random()*1000)},
                        {name: '湖北',value: Math.round(Math.random()*1000)},
                        {name: '广西',value: Math.round(Math.random()*1000)},
                        {name: '甘肃',value: Math.round(Math.random()*1000)},
                        {name: '山西',value: Math.round(Math.random()*1000)},
                        {name: '内蒙古',value: Math.round(Math.random()*1000)},
                        {name: '陕西',value: Math.round(Math.random()*1000)},
                        {name: '吉林',value: Math.round(Math.random()*1000)},
                        {name: '福建',value: Math.round(Math.random()*1000)},
                        {name: '贵州',value: Math.round(Math.random()*1000)},
                        {name: '广东',value: Math.round(Math.random()*1000)},
                        {name: '青海',value: Math.round(Math.random()*1000)},
                        {name: '西藏',value: Math.round(Math.random()*1000)},
                        {name: '四川',value: Math.round(Math.random()*1000)},
                        {name: '宁夏',value: Math.round(Math.random()*1000)},
                        {name: '海南',value: Math.round(Math.random()*1000)},
                        {name: '台湾',value: Math.round(Math.random()*1000)},
                        {name: '香港',value: Math.round(Math.random()*1000)},
                        {name: '澳门',value: Math.round(Math.random()*1000)}
                    ]
                },
                {
                    name: 'iphone4',
                    type: 'map',
                    mapType: 'china',
                    itemStyle:{
                        normal:{label:{show:true}},
                        emphasis:{label:{show:true}}
                    },
                    data:[
                        {name: '北京',value: Math.round(Math.random()*1000)},
                        {name: '天津',value: Math.round(Math.random()*1000)},
                        {name: '上海',value: Math.round(Math.random()*1000)},
                        {name: '重庆',value: Math.round(Math.random()*1000)},
                        {name: '河北',value: Math.round(Math.random()*1000)},
                        {name: '安徽',value: Math.round(Math.random()*1000)},
                        {name: '新疆',value: Math.round(Math.random()*1000)},
                        {name: '浙江',value: Math.round(Math.random()*1000)},
                        {name: '江西',value: Math.round(Math.random()*1000)},
                        {name: '山西',value: Math.round(Math.random()*1000)},
                        {name: '内蒙古',value: Math.round(Math.random()*1000)},
                        {name: '吉林',value: Math.round(Math.random()*1000)},
                        {name: '福建',value: Math.round(Math.random()*1000)},
                        {name: '广东',value: Math.round(Math.random()*1000)},
                        {name: '西藏',value: Math.round(Math.random()*1000)},
                        {name: '四川',value: Math.round(Math.random()*1000)},
                        {name: '宁夏',value: Math.round(Math.random()*1000)},
                        {name: '香港',value: Math.round(Math.random()*1000)},
                        {name: '澳门',value: Math.round(Math.random()*1000)}
                    ]
                },
                {
                    name: 'iphone5',
                    type: 'map',
                    mapType: 'china',
                    itemStyle:{
                        normal:{label:{show:true}},
                        emphasis:{label:{show:true}}
                    },
                    data:[
                        {name: '北京',value: Math.round(Math.random()*1000)},
                        {name: '天津',value: Math.round(Math.random()*1000)},
                        {name: '上海',value: Math.round(Math.random()*1000)},
                        {name: '广东',value: Math.round(Math.random()*1000)},
                        {name: '台湾',value: Math.round(Math.random()*1000)},
                        {name: '香港',value: Math.round(Math.random()*1000)},
                        {name: '澳门',value: Math.round(Math.random()*1000)}
                    ]
                }
            ]
        };
        mapChart.setOption(mapoption);
        $(window).resize(mapChart.resize);
    }

</script>

</body>
</html>
