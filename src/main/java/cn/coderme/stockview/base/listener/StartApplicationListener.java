package cn.coderme.stockview.base.listener;

import cn.coderme.stockview.base.annotation.Job;
import cn.coderme.stockview.base.annotation.JobInfo;
import cn.coderme.stockview.base.schedule.ScheduleData;
import cn.coderme.stockview.dto.job.ScheduledInfoDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.*;

@Component
public class StartApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    protected Logger log = LoggerFactory.getLogger(StartApplicationListener.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // root application context
        System.out.println(event.getApplicationContext());
        if(null == event.getApplicationContext().getParent()) {
            log.info("加载 ROOT 系统配置...");
            Map<String, Object> jobs = event.getApplicationContext().getBeansWithAnnotation(Job.class);
            for (Map.Entry<String, Object> entry : jobs.entrySet()) {
                Object service = entry.getValue();
                log.debug(">>>>> {}.initMapper()", service.getClass().getName());
                try {
                    for (Method method : service.getClass().getMethods()) {
                        Scheduled annotation = method.getAnnotation(Scheduled.class);
                        JobInfo jobInfo = method.getAnnotation(JobInfo.class);
                        if (null != annotation) {
                            String cron = annotation.cron();
                            long fixedDelay = annotation.fixedDelay();
                            long fixedRate = annotation.fixedRate();
                            ScheduledInfoDto infoDto = new ScheduledInfoDto();
                            infoDto.setClzName(service.getClass().getSimpleName());
                            infoDto.setMethod(method.getName());
                            infoDto.setCron(cron);
                            infoDto.setFixedDelay(fixedDelay);
                            infoDto.setFixedRate(fixedRate);
                            infoDto.setBeanName(entry.getKey());
                            infoDto.setStatus("10");
                            if (StringUtils.hasText(cron)) {
                                infoDto.setType(ScheduledInfoDto.TYPE_CRON);
                            } else if (-1 != fixedRate) {
                                infoDto.setType(ScheduledInfoDto.TYPE_FIXEDRATE);
                            } else if (-1 != fixedDelay) {
                                infoDto.setType(ScheduledInfoDto.TYPE_FIXEDDELAY);
                            }
                            infoDto.setMethodString(method.toString());
                            if (null != jobInfo) {
                                infoDto.setJobInfo(jobInfo.value());
                            }
                            ScheduleData.SCHEDULED_INFOS.add(infoDto);
                            ScheduleData.SCHEDULE_MAP.put(infoDto.getBeanName()+"."+infoDto.getMethod(), infoDto);
                        }
                    }
                } catch (Exception e) {
                    log.error("初始化 @Job 注解异常", e);
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
//        List<CronTask> cronTasks
        List<String> stringList = new ArrayList<>();
        stringList.add("1");
        stringList.add("2");
        stringList.add("3");
        stringList.add("4");
        System.out.println("before");
        for (String s : stringList) {
            System.out.println(s);
        }
        stringList.remove("2");
        System.out.println("after");
        for (String s : stringList) {
            System.out.println(s);
        }
    }
}