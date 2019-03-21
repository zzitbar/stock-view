package cn.coderme.stockview.controller;


import cn.coderme.stockview.base.PageDataDto;
import cn.coderme.stockview.base.PageReqDto;
import cn.coderme.stockview.dto.live.LiveMessageReqDto;
import cn.coderme.stockview.entity.LiveMessages;
import cn.coderme.stockview.entity.LiveRoom;
import cn.coderme.stockview.service.LiveMessagesService;
import cn.coderme.stockview.service.LiveRoomService;
import cn.coderme.stockview.utils.WebSocketUtils;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 财经直播间 前端控制器
 * </p>
 *
 * @author Coderme
 * @since 2018-07-06
 */
@Controller
@RequestMapping("/liveRoom")
public class LiveRoomController {

    @Autowired
    private LiveRoomService liveRoomService;
    @Autowired
    private LiveMessagesService liveMessagesService;
    @Autowired
    private WebSocketUtils webSocketUtils;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(String date, Model model) {
        LiveRoom liveRoom = liveRoomService.findByLiveDate(StringUtils.hasText(date)?LocalDate.parse(date):LocalDate.now());
        model.addAttribute("room", liveRoom);
        return "live/index";
    }

    @RequestMapping(value = "/page", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public PageDataDto<LiveMessages> page(LiveMessageReqDto dto) {
        dto.setOrder(PageReqDto.DESC);
        dto.setSort("msgTime");
        return liveMessagesService.getPage(dto);
    }

    @RequestMapping(value = "/testws", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String testws() {
        List<LiveMessages> liveMessagesList = liveMessagesService.selectBatchIds(Arrays.asList("7088573,7088574,7088575".split(",")));
        // websocket群发消息
//        LiveMessageWebSocket.sendAll(JSON.toJSONString(liveMessagesList));
        webSocketUtils.liveMessage(JSON.toJSONString(liveMessagesList));
        return "success";
    }
}

