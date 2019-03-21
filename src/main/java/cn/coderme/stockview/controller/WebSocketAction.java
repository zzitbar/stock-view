package cn.coderme.stockview.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebSocketAction {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

//    @MessageMapping("/sendTest")
//    @SendTo("/topic/subscribeTest")
//    public String sendDemo(String name) {
//        logger.info("接收到了信息" + name);
//        return new String("你发送的消息为:" + name);
//    }
//
//    @SubscribeMapping("/subscribeTest")
//    public String sub() {
//        logger.info("XXX用户订阅了我。。。");
//        return new String("感谢你订阅了我。。。");
//    }

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    //客户端只要订阅了/topic/subscribeTest主题，调用这个方法即可
    @RequestMapping("/templateTest")
    @ResponseBody
    public void templateTest(String topic) {
        messagingTemplate.convertAndSend(topic, new String("服务器主动推的数据"+topic+"----"+System.currentTimeMillis()));
    }
}