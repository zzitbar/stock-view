package cn.coderme.stockview.base.schedule;

import cn.coderme.stockview.dto.job.ScheduledInfoDto;
import cn.coderme.stockview.utils.BeanUtils;
import cn.coderme.stockview.utils.SpringContext;
import org.springframework.scheduling.SchedulingException;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.*;
import org.springframework.scheduling.support.DelegatingErrorHandlingRunnable;
import org.springframework.scheduling.support.ScheduledMethodRunnable;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;

/**
 * @Scheduled 定时任务动态修改
 * Created By Administrator
 * Date:2018/7/4
 * Time:10:56
 */
@Component
public class ScheduleConfigurer implements SchedulingConfigurer {

    private final String FIELD_SCHEDULED_TASKS = "scheduledTasks";
    private final String FIELD_FUTURE = "future";
    private final String RUNNABLE_METHOD = "run";//ReschedulingRunnable.run()方法名
    public static ScheduledTaskRegistrar taskRegistrar;
    private Set<ScheduledTask> scheduledTasks = null;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        this.taskRegistrar = taskRegistrar;
        this.taskRegistrar.afterPropertiesSet();
        getScheduledFutures();
    }

    public Set<ScheduledTask> getScheduledFutures() {
        if (scheduledTasks == null || scheduledTasks.size() == 0) {
            try {
                scheduledTasks = (Set<ScheduledTask>) BeanUtils.getProperty(taskRegistrar, FIELD_SCHEDULED_TASKS);
            } catch (NoSuchFieldException e) {
                throw new SchedulingException("not found scheduledTasks field.");
            }
        }
        return scheduledTasks;
    }

    public void map() {
        try {
            for (ScheduledTask st : scheduledTasks) {
                ScheduledFuture sf = (ScheduledFuture) BeanUtils.getProperty(st, FIELD_FUTURE);
                if (sf.getClass().getSimpleName().equals("ReschedulingRunnable")) {
                    // cron
                    ScheduledMethodRunnable runnable = (ScheduledMethodRunnable) BeanUtils.getProperty(sf, "delegate");
                    ScheduleData.JOB_RUNNING_MAP.put(runnable.getMethod().toString(), sf);
                } else if (sf.getClass().getSimpleName().equals("ScheduledFutureTask")) {
                    // fixedRate/fixedDelay
                    DelegatingErrorHandlingRunnable der = (DelegatingErrorHandlingRunnable) BeanUtils.getProperty(BeanUtils.getProperty(sf, "callable"), "task");
                    ScheduledMethodRunnable runnable = (ScheduledMethodRunnable) BeanUtils.getProperty(der, "delegate");
                    ScheduleData.JOB_RUNNING_MAP.put(runnable.getMethod().toString(), sf);
                }
            }
        } catch (NoSuchFieldException e) {
            throw new SchedulingException("not found scheduledTasks field.");
        }
    }

    public void exe(String method) {
        if (null == ScheduleData.JOB_RUNNING_MAP || ScheduleData.JOB_RUNNING_MAP.size() == 0) {
            map();
        }
        ScheduledFuture sf = ScheduleData.JOB_RUNNING_MAP.get(method);
        if (null != sf) {
            try {
                ScheduledMethodRunnable runnable = (ScheduledMethodRunnable) BeanUtils.getProperty(sf, "delegate");
                runnable.run();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }

    public void pause(String method) {
        if (null == ScheduleData.JOB_RUNNING_MAP || ScheduleData.JOB_RUNNING_MAP.size() == 0) {
            map();
        }
        ScheduledFuture sf = ScheduleData.JOB_RUNNING_MAP.get(method);
        if (null != sf) {
            sf.cancel(true);
        }
    }

    public void resume(String method) {
        if (null == ScheduleData.JOB_RUNNING_MAP || ScheduleData.JOB_RUNNING_MAP.size() == 0) {
            map();
        }
        ScheduledFuture sf = ScheduleData.JOB_RUNNING_MAP.get(method);
        if (null != sf) {
            try {
                Method m = sf.getClass().getDeclaredMethod(RUNNABLE_METHOD);
                ReflectionUtils.makeAccessible(m);
                m.invoke(sf);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}