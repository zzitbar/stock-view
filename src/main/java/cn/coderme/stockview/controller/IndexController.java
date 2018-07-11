package cn.coderme.stockview.controller;

import cn.coderme.stockview.entity.LiveRoom;
import cn.coderme.stockview.service.LiveRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;

/**
 * Created By Administrator
 * Date:2018/6/13
 * Time:17:35
 */
@Controller
@RequestMapping()
public class IndexController {

    @Autowired(required = false)
    private ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    private LiveRoomService liveRoomService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {
        LiveRoom liveRoom = liveRoomService.findByLiveDate(LocalDate.now());
        model.addAttribute("room", liveRoom);
        return "index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String main(Model model) {
        LiveRoom liveRoom = liveRoomService.findByLiveDate(LocalDate.now());
        model.addAttribute("room", liveRoom);
        return "main";
    }

    @RequestMapping(value = "/stock", method = RequestMethod.GET)
    public String stock() {
        return "stockIndex";
    }

    @RequestMapping(value = "/taskActiveCount")
    @ResponseBody
    public Integer taskActiveCount() {
        String a = "300750";
        return taskExecutor.getActiveCount();
    }
}