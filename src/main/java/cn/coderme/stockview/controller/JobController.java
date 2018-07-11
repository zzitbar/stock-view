package cn.coderme.stockview.controller;

import cn.coderme.stockview.base.PageDataDto;
import cn.coderme.stockview.base.PageReqDto;
import cn.coderme.stockview.dto.Result;
import cn.coderme.stockview.entity.JobAndTrigger;
import cn.coderme.stockview.job.BaseJob;
import cn.coderme.stockview.service.JobAndTriggerService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* Quartz 定时任务管理
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping(value = "/job")
public class JobController {

    @Autowired
    private JobAndTriggerService jobAndTriggerService;

    @Autowired
    @Qualifier("Scheduler")
    private Scheduler scheduler;

    private static Logger log = LoggerFactory.getLogger(JobController.class);

    @GetMapping(value = {"", "/index"})
    public String index() {
        return "jobs/quartz";
    }

    @PostMapping(value = "/page")
    @ResponseBody
    public PageDataDto<JobAndTrigger> queryjob(PageReqDto dto) {
        PageDataDto<JobAndTrigger> jobAndTrigger = jobAndTriggerService.getJobAndTriggerDetails(dto);
        return jobAndTrigger;
    }

    @PostMapping(value = "/add")
    @ResponseBody
    public Result addjob(@RequestParam(value = "jobClassName") String jobClassName,
                       @RequestParam(value = "jobGroupName") String jobGroupName,
                       @RequestParam(value = "cronExpression") String cronExpression, String jobDescription) {
        Result result = new Result();
        try {
            addJob(jobClassName, jobGroupName, cronExpression, jobDescription);
        } catch (Exception e) {
            log.error("新增任务出错", e);
            result.setMsg(e.getMessage());
            result.setCode(Result.FAIL);
        }
        return result;
    }

    public void addJob(String jobClassName, String jobGroupName, String cronExpression, String jobDescription) throws Exception {
        // 启动调度器
        scheduler.start();
        //构建job信息
        JobKey jobKey = JobKey.jobKey(jobClassName, jobGroupName);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        //表达式调度构建器(即任务执行的时间)
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
//        SimpleScheduleBuilder.simpleSchedule().
        //按新的cronExpression表达式构建一个新的trigger
        TriggerKey triggerKey = TriggerKey.triggerKey(jobClassName, jobGroupName);
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        if (null != trigger && null != jobDetail) {
            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            jobDetail = jobDetail.getJobBuilder().newJob(getClass(jobClassName).getClass()).withDescription(jobDescription).withIdentity(jobClassName, jobGroupName).build();
            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        } else {
            trigger = TriggerBuilder.newTrigger().withIdentity(jobClassName, jobGroupName)
                    .withSchedule(scheduleBuilder).build();
            jobDetail = JobBuilder.newJob(getClass(jobClassName).getClass()).withDescription(jobDescription).withIdentity(jobClassName, jobGroupName).build();
            scheduler.scheduleJob(jobDetail, trigger);
        }
    }

    @PostMapping(value = "/exe")
    @ResponseBody
    public Result exe(String jobName, String groupName) {
        Result result = new Result();
        try {
            scheduler.triggerJob(JobKey.jobKey(jobName, groupName));
        } catch (Exception e) {
            log.error("删除任务出错", e);
            result.setMsg(e.getMessage());
            result.setCode(Result.FAIL);
        }
        return result;
    }

    @PostMapping(value = "/pause")
    @ResponseBody
    public Result pausejob(String jobName, String groupName) {
        Result result = new Result();
        try {
            scheduler.pauseJob(JobKey.jobKey(jobName, groupName));
        } catch (Exception e) {
            log.error("暂停任务出错", e);
            result.setMsg(e.getMessage());
            result.setCode(Result.FAIL);
        }
        return result;
    }

    @PostMapping(value = "/resume")
    @ResponseBody
    public Result resumejob(String jobName, String groupName) throws Exception {
        Result result = new Result();
        try {
            scheduler.resumeJob(JobKey.jobKey(jobName, groupName));
        } catch (Exception e) {
            log.error("恢复任务出错", e);
            result.setMsg(e.getMessage());
            result.setCode(Result.FAIL);
        }
        return result;
    }

    @PostMapping(value = "/reschedule")
    @ResponseBody
    public void rescheduleJob(@RequestParam(value = "jobClassName") String jobClassName,
                              @RequestParam(value = "jobGroupName") String jobGroupName,
                              @RequestParam(value = "cronExpression") String cronExpression) throws Exception {
        jobreschedule(jobClassName, jobGroupName, cronExpression);
    }

    public void jobreschedule(String jobClassName, String jobGroupName, String cronExpression) throws Exception {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobClassName, jobGroupName);
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            System.out.println("更新定时任务失败" + e);
            throw new Exception("更新定时任务失败");
        }
    }


    @PostMapping(value = "/delete")
    @ResponseBody
    public Result deletejob(String jobName, String groupName) {
        Result result = new Result();
        try {
            scheduler.pauseTrigger(TriggerKey.triggerKey(jobName, groupName));
            scheduler.unscheduleJob(TriggerKey.triggerKey(jobName, groupName));
            scheduler.deleteJob(JobKey.jobKey(jobName, groupName));
        } catch (Exception e) {
            log.error("删除任务出错", e);
            result.setMsg(e.getMessage());
            result.setCode(Result.FAIL);
        }
        return result;
    }

    public static BaseJob getClass(String classname) throws Exception {
        Class<?> class1 = Class.forName(classname);
        return (BaseJob) class1.newInstance();
    }

}