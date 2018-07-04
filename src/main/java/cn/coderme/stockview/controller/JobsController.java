package cn.coderme.stockview.controller;

import cn.coderme.stockview.base.schedule.ScheduleConfigurer;
import cn.coderme.stockview.base.schedule.ScheduleData;
import cn.coderme.stockview.dto.Result;
import cn.coderme.stockview.dto.job.ScheduledInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.scheduling.support.ScheduledMethodRunnable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Set;

/**
 * 定时任务管理
 * Created By Administrator
 * Date:2018/7/4
 * Time:9:35
 */
@Controller
@RequestMapping("/jobs")
public class JobsController {

    @Autowired
    private ScheduleConfigurer scheduleConfigurer;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {
        return "jobs/index";
    }

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    @ResponseBody
    public Set<ScheduledInfoDto> page() {
//        PageDataDto<ScheduledInfoDto> result = new PageDataDto<ScheduledInfoDto>();
//        result.setTotal(StartApplicationListener.ScheduledInfoDto.size());
//        result.setRows(StartApplicationListener.ScheduledInfoDto);
        return ScheduleData.SCHEDULED_INFOS;
    }

    @RequestMapping(value = "/exe", method = RequestMethod.POST)
    @ResponseBody
    public Result exe(String methodName, String beanName) {
        Result result = new Result();
        try {
            ScheduledInfoDto infoDto = ScheduleData.SCHEDULE_MAP.get(beanName+"."+methodName);
            if (null == infoDto) {
                throw new Exception("任务不存在");
            }
            scheduleConfigurer.exe(infoDto.getMethodString());
//            Object object = SpringContext.getBean(beanName);
//            Method method = object.getClass().getDeclaredMethod(methodName);
//            method.invoke(object);
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(Result.FAIL);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/pause", method = RequestMethod.POST)
    @ResponseBody
    public Result pause(String methodName, String beanName) {
        Result result = new Result();
        try {
            ScheduledInfoDto infoDto = ScheduleData.SCHEDULE_MAP.get(beanName+"."+methodName);
            if (null == infoDto) {
                throw new Exception("任务不存在");
            }
            scheduleConfigurer.pause(infoDto.getMethodString());
            infoDto.setStatus("20");
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(Result.FAIL);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/resume", method = RequestMethod.POST)
    @ResponseBody
    public Result resume(String methodName, String beanName) {
        Result result = new Result();
        try {
            ScheduledInfoDto infoDto = ScheduleData.SCHEDULE_MAP.get(beanName+"."+methodName);
            if (null == infoDto) {
                throw new Exception("任务不存在");
            }
            scheduleConfigurer.resume(infoDto.getMethodString());
            infoDto.setStatus("10");
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(Result.FAIL);
            result.setMsg(e.getMessage());
        }
        return result;
    }
}