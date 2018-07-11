<link rel="stylesheet" href="${request.contextPath}/static/css/animate.css">
<script>
    var websocket = null;
    var nextPage = 0;
    var total = 0;
    $(function () {
        loadData();
        $("#loadmore-btn").on("click", function (e) {
            loadData(nextPage);
        })
        //判断当前浏览器是否支持WebSocket
        if ('WebSocket' in window) {
            websocket = new WebSocket("ws://localhost:8080/live");
        } else {
            Stock.showError('Not support websocket')
        }
    })

    function loadData(offset) {
        $.post("${request.contextPath}/liveRoom/page", {
            offset: offset ? offset : 0,
            roomId: "${room.roomId!}"
        }, function (result) {
            if (result && result.rows && result.rows.length > 0) {
                var html = "";
                $.each(result.rows, function (i, val) {
                    html = html + '<li class="gl_item animated zoomIn"><span class="msg_dot"></span><span class="animate_dot"></span>'
                            + '<div class="msg_time">' + val.messageTime + '</div><div class="msg_info fold"><p>' + val.content + '</p></div>';
                })
                $("#msg-ul").append(html);
                nextPage = nextPage + 10;
                total = result.total;
                if (nextPage >= total) {
                    $("#loadmore-btn").attr("disabled", "disabled");
                    $("#loadmore-btn").html("没有更多了")
                }
            } else {
                $("#loadmore-btn").attr("disabled", "disabled");
                $("#loadmore-btn").html("没有更多了")
            }
        }, "json");
    }

    //连接发生错误的回调方法
    websocket.onerror = function () {
        Stock.showError("error");
    };
    //连接成功建立的回调方法
    websocket.onopen = function (event) {
        // Stock.showError("open success");
    }
    //接收到消息的回调方法
    websocket.onmessage = function (event) {
        var data = $.parseJSON(event.data);
        if (data && data.length>0) {
            var html = "";
            $.each(data, function (i, val) {
                html = html + '<li class="gl_item animated zoomIn"><span class="msg_dot"></span><span class="animate_dot"></span>'
                        + '<div class="msg_time">' + val.messageTime + '</div><div class="msg_info fold"><p>' + val.content + '</p></div>';
            });
            $("#msg-ul").prepend(html);
        }
    }
    //连接关闭的回调方法
    websocket.onclose = function () {
    }

    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
        websocket.close();
    }

    //关闭连接
    function closeWebSocket() {
        websocket.close();
    }

    //发送消息
    function send() {
        var message = document.getElementById('text').value;
        websocket.send(message);
    }
</script>
<div class="container-fluid" id="tasks" style="overflow-y: auto; background: #ffffff">
    <h3>${room.title}</h3>
    <h4>${room.liveDate}</h4>
    <ul class="gl_list" id="msg-ul">
    </ul>
    <button type="button" class="btn btn-link btn-sm btn-block" id="loadmore-btn" style="margin: 20px 0">加载更多</button>
</div>