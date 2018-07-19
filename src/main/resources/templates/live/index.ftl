<link rel="stylesheet" href="${request.contextPath}/static/css/animate.css">
<script src="../../static/js/stomp.js"></script>
<script src="https://cdn.bootcss.com/sockjs-client/1.1.4/sockjs.min.js"></script>
<script>
    var stompClient = null;
    var nextPage = 0;
    var total = 0;
    $(function () {
        loadData();
        $("#loadmore-btn").on("click", function (e) {
            loadData(nextPage);
        })
        // 建立连接对象（还未发起连接）
        var socket = new SockJS(Stock.baseUrl+"/ws");
        // 获取 STOMP 子协议的客户端对象
        stompClient = Stomp.over(socket);
        stompClient.debug = function(str) {
        };
        // 向服务器发起websocket连接并发送CONNECT帧
        stompClient.connect(
                {},
                function connectCallback(frame) {
                    // 连接成功时（服务器响应 CONNECTED 帧）的回调方法
                    var subscription = stompClient.subscribe('/topic/liveMessage', function (response) {
                        var data = $.parseJSON(response.body);
                        if (data && data.length>0) {
                            var html = "";
                            $.each(data, function (i, val) {
                                html = html + '<li class="gl_item animated zoomIn"><span class="msg_dot"></span><span class="animate_dot"></span>'
                                        + '<div class="msg_time">' + val.messageTime + '</div><div class="msg_info fold"><p>' + val.content + '</p></div>';
                            });
                            $("#msg-ul").prepend(html);
                        }
                    });
                },
                function errorCallBack(error) {
                    Stock.showError("连接失败");
                }
        );
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

    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
        stompClient.disconnect();
    }

    //关闭连接
    function closeWebSocket() {
        stompClient.disconnect();
    }
</script>
<div class="container-fluid" id="tasks" style="overflow-y: auto; background: #ffffff">
    <h3>${room.title}</h3>
    <h4>${room.liveDate}</h4>
    <ul class="gl_list" id="msg-ul">
    </ul>
    <button type="button" class="btn btn-link btn-sm btn-block" id="loadmore-btn" style="margin: 20px 0">加载更多</button>
</div>