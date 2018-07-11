<script type="text/javascript" src="${request.contextPath}/static/js/infinite-scroll.pkgd.js"></script>

<script>
    var nextPage = 0;
    var total = 0;
    $(function () {
        loadData();
        $('#tasks').infiniteScroll({
        <#--path: function () {-->
        <#--return "${request.contextPath}/liveRoom/page?roomId=${room.roomId!}";-->
        <#--},-->
            <#--path: function () {-->
                <#--var offset = nextPage * 10;-->
                <#--if (offset <= total) {-->
                    <#--return "${request.contextPath}/liveRoom/page?roomId=${room.roomId!}&offset=" + offset;-->
                <#--}-->
            <#--},-->
            path: "#navigation a",
        <#--path: "${request.contextPath}/liveRoom/page?offset=2&roomId=${room.roomId!}",-->
            append: false,
            responseType: 'json',
            status: '.page-load-status'
        });
        var infScroll = $('#tasks').data('infiniteScroll');
        $('#tasks').on('load.infiniteScroll', function (event, response) {
            // parse JSON
            if (response && response.rows && response.rows.length > 0) {
                var html = "";
                $.each(response.rows, function (i, val) {
                    html = html + '<li class="gl_item"><span class="msg_dot"></span><span class="animate_dot"></span>'
                            + '<div class="msg_time">' + val.messageTime + '</div><div class="msg_info fold"><p>' + val.content + '</p></div>';
                });
                $("#msg-ul").append(html);
                nextPage++;
                total = response.total;
            }
            // do something with JSON...
        });
        // var myScroll = new IScroll('#iscroll-wrapper', {
        //     mouseWheel: true,
        //     scrollbars: true
        // });
        // myScroll.on("scroll",function(e){
        //     console.log("scroll e", e);
        //     console.log(this);
        // });
        // myScroll.on("scrollEnd",function(e){
        //     console.log("scrollEnd e", e);
        //     console.log(this);
        // });
    })

    function loadData(offset) {
        $.post("${request.contextPath}/liveRoom/page", {
            offset: offset ? offset : 0,
            roomId: "${room.roomId!}"
        }, function (result) {
            if (result && result.rows && result.rows.length > 0) {
                var html = "";
                $.each(result.rows, function (i, val) {
                    html = html + '<li class="gl_item"><span class="msg_dot"></span><span class="animate_dot"></span>'
                            + '<div class="msg_time">' + val.messageTime + '</div><div class="msg_info fold"><p>' + val.content + '</p></div>';
                })
                $("#msg-ul").append(html);
                nextPage++;
                total = result.total;
            }
        }, "json");
    }
</script>
<div class="container-fluid" id="tasks" style="overflow-y: auto;">
    <h3>${room.title}</h3>
    <h4>${room.liveDate}</h4>
    <ul class="gl_list" id="msg-ul">
        <li class="gl_item"></li>
    <#--<li class="gl_item">-->
    <#--<span class="msg_dot"></span>-->
    <#--<span class="animate_dot"></span>-->
    <#--<div class="msg_time">-->
    <#--14:31:00-->
    <#--</div>-->
    <#--<div class="msg_info fold">-->
    <#--<p ne-mouseover="mouseFold(__i)" ne-mouseout="mouseFold()" ne-if="{{livemsg.msg.content}}">-->
    <#--光启科学在香港一度大涨74%，为2015年7月以来最大涨幅。</p>-->
    <#--</div>-->
    <#--</li>-->
    </ul>
    <button type="button" class="btn btn-default btn-xs" id="loadmore-btn">加载更多</button>
    <!--在最后，要加上一个导航，每次滚到底部，就会触发这个url去加载数据-->
    <div id="navigation">
        <a href="${request.contextPath}/liveRoom/page?offset=20&roomId=${room.roomId!}"></a>
    </div>
    <div class="page-load-status">
        <div class="loader-ellips infinite-scroll-request">
            <span class="loader-ellips__dot"></span>
            <span class="loader-ellips__dot"></span>
            <span class="loader-ellips__dot"></span>
            <span class="loader-ellips__dot"></span>
        </div>
        <p class="infinite-scroll-last">End of content</p>
        <p class="infinite-scroll-error">No more pages to load</p>
    </div>
</div>