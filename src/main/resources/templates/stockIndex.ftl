<script>
    $(function () {
        $(".menu-list").on("click", function (e) {
            var $this = e.target;
            $($this).siblings().removeClass("active");
            $($this).addClass("active");
            var url = $($this).attr("data-url");
            $.get(url, function (result) {
                $("#content").html(result);
            });
        })
    })
</script>
<div class="row">
    <div class="col-sm-3 col-lg-2">
        <a href="#" class="list-group-item menu-list active" data-url="${request.contextPath}/stockInfo/index">
            股票列表
        </a>
        <a href="#" class="list-group-item menu-list" data-url="${request.contextPath}/liveRoom/index">财经直播</a>
        <a href="#" class="list-group-item menu-list" data-url="${request.contextPath}/stockInfo/index">实时数据</a>
        <a href="#" class="list-group-item menu-list" data-url="${request.contextPath}/stockInfo//history/1">历史交易</a>
        <a href="#" class="list-group-item menu-list" data-url="${request.contextPath}/stockInfo/index">统计</a>
        <a href="#" class="list-group-item menu-list" data-url="${request.contextPath}/statistic/increaseRange">涨跌家数统计</a>
        <a href="#" class="list-group-item menu-list" data-url="${request.contextPath}/job">定时任务管理</a>
    </div>
    <div class="col-sm-9 col-lg-10" id="content">

    </div>
</div>