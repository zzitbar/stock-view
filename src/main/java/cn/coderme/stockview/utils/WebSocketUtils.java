package cn.coderme.stockview.utils;

import cn.coderme.stockview.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 * Created By Administrator
 * Date:2018/7/12
 * Time:14:02
 */
@Component
public class WebSocketUtils {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    /**
     * 广播财经消息
     * @param content
     */
    public void liveMessage(String content) {
        messagingTemplate.convertAndSend(Constants.LIVE_MSG_TOPIC, content);
    }
}