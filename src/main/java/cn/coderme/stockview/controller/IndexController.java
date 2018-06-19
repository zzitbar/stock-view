package cn.coderme.stockview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created By Administrator
 * Date:2018/6/13
 * Time:17:35
 */
@Controller
@RequestMapping("/index")
public class IndexController {

    @Autowired(required = false)
    private ThreadPoolTaskExecutor taskExecutor;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/taskActiveCount")
    @ResponseBody
    public Integer taskActiveCount() {
        String a = "300750";
        return taskExecutor.getActiveCount();
    }
}