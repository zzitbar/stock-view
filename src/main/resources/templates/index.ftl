<#assign ctx=request.contextPath>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>

    <title>首页</title>

    <meta name="HandheldFriendly" content="True"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link rel="stylesheet" type="text/css"
    <#--href="https://fonts.cat.net/css?family=Merriweather:300,700,700italic,300italic|Open+Sans:700,400"/>-->
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/font-awesome-4.5.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/js/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${request.contextPath}/static/js/bootstrap-table-master/dist/bootstrap-table.min.css">


    <meta name="description" content="股票，股票走势，股票API，股票实时数据"/>
    <link rel="shortcut icon" href="/favicon.png" type="image/png"/>
    <meta name="referrer" content="no-referrer-when-downgrade"/>
    <style>
        .increase{
            color: #FF0000;
        }
        .decrease{
            color: #008000;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Stock View</a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">首页</a></li>
                <li><a href="#">关于</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-lg-2">
            <div class="panel panel-default">
                <div class="panel-heading">导航</div>
                <ul class="list-group">
                    <li class="list-group-item"><a href="#" onclick="show('${ctx}/stockInfo/index')">股票列表</a></li>
                    <li class="list-group-item btn btn-link" onclick="">实时数据</li>
                    <li class="list-group-item btn btn-link" onclick="">统计</li>
                </ul>
            </div>
        </div>
        <div class="col-sm-9 col-lg-10" id="content">

        </div>
    </div>
</div>

<script src="${ctx}/static/js/jquery-2.2.3.min.js"></script>
<script src="${request.contextPath}/static/js/bootstrap/js/bootstrap.min.js"></script>
<script src="${request.contextPath}/static/js/bootstrap-table-master/src/bootstrap-table.js"></script>
<script src="${request.contextPath}/static/js/bootstrap-table-master/src/locale/bootstrap-table-zh-CN.js"></script>
<script src="${request.contextPath}/static/js/jquery.serializeJSON/jquery.serializejson.js"></script>

<script>
    function show(url) {
        console.log(url);
        $.get(url, function (result) {
            $("#content").html(result);
        });
    }
</script>
</body>
</html>