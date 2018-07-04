//package cn.coderme.stockview.base.schedule;
//
//import cn.coderme.stockview.dto.job.ScheduledInfoDto;
//import cn.coderme.stockview.utils.BeanUtils;
//import org.springframework.scheduling.SchedulingException;
//import org.springframework.scheduling.TaskScheduler;
//import org.springframework.scheduling.annotation.SchedulingConfigurer;
//import org.springframework.scheduling.config.*;
//import org.springframework.scheduling.support.ScheduledMethodRunnable;
//import org.springframework.stereotype.Component;
//
//import java.lang.reflect.Method;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.ScheduledFuture;
//
///**
// * @Scheduled 定时任务动态修改
// * Created By Administrator
// * Date:2018/7/4
// * Time:10:56
// */
//@Component
//public class ScheduleConfigurerOld implements SchedulingConfigurer {
//
//    private final String FIELD_SCHEDULED_TASKS = "scheduledTasks";
//    private final String FIELD_FUTURE = "future";
//    private final String RUNNABLE_METHOD = "run";//ReschedulingRunnable.run()方法名
//    public static ScheduledTaskRegistrar taskRegistrar;
//    private Set<ScheduledTask> scheduledTasks = null;
//    private Map<String, ScheduledFuture<?>> taskFutures = new ConcurrentHashMap<String, ScheduledFuture<?>>();
//
//    @Override
//    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
//        this.taskRegistrar = taskRegistrar;
//        this.taskRegistrar.afterPropertiesSet();
////        getScheduledFutures();
//        if (null != taskRegistrar.getCronTaskList()) {
//            for (CronTask ct : taskRegistrar.getCronTaskList()) {
//                ScheduledMethodRunnable smr = (ScheduledMethodRunnable) ct.getRunnable();
//                System.out.println(smr.getMethod().toString());
//                ScheduleData.JOB_RUNNING_MAP.put(ScheduledInfoDto.TYPE_CRON + smr.getMethod().toString(), ct);
//                List<CronTask> cronTasks = new ArrayList<>();
//                cronTasks.addAll(taskRegistrar.getCronTaskList());
//                ScheduleData.JOB_METHOD_STRING_TASK_LIST_MAP.put(ScheduledInfoDto.TYPE_CRON + smr.getMethod().toString(), cronTasks);
//            }
//        }
//        if (null != taskRegistrar.getFixedRateTaskList()) {
//            for (IntervalTask ct : taskRegistrar.getFixedRateTaskList()) {
//                ScheduledMethodRunnable smr = (ScheduledMethodRunnable) ct.getRunnable();
//                System.out.println(smr.getMethod().toString());
//                ScheduleData.JOB_RUNNING_MAP.put(ScheduledInfoDto.TYPE_FIXEDRATE + smr.getMethod().toString(), ct);
//                List<IntervalTask> cronTasks = new ArrayList<>();
//                cronTasks.addAll(taskRegistrar.getFixedRateTaskList());
//                ScheduleData.JOB_METHOD_STRING_TASK_LIST_MAP.put(ScheduledInfoDto.TYPE_FIXEDRATE + smr.getMethod().toString(), cronTasks);
//            }
//        }
//        if (null != taskRegistrar.getFixedDelayTaskList()) {
//            for (IntervalTask ct : taskRegistrar.getFixedDelayTaskList()) {
//                ScheduledMethodRunnable smr = (ScheduledMethodRunnable) ct.getRunnable();
//                System.out.println(smr.getMethod().toString());
//                ScheduleData.JOB_RUNNING_MAP.put(ScheduledInfoDto.TYPE_FIXEDDELAY + smr.getMethod().toString(), ct);
//                List<IntervalTask> cronTasks = new ArrayList<>();
//                cronTasks.addAll(taskRegistrar.getFixedDelayTaskList());
//                ScheduleData.JOB_METHOD_STRING_TASK_LIST_MAP.put(ScheduledInfoDto.TYPE_FIXEDDELAY + smr.getMethod().toString(), cronTasks);
//            }
//        }
//    }
//
//    public Set<ScheduledTask> getScheduledFutures() {
//        if (scheduledTasks == null || scheduledTasks.size() == 0) {
//            try {
//                scheduledTasks = (Set<ScheduledTask>) BeanUtils.getProperty(taskRegistrar, FIELD_SCHEDULED_TASKS);
//            } catch (NoSuchFieldException e) {
//                throw new SchedulingException("not found scheduledTasks field.");
//            }
//        }
//        return scheduledTasks;
//    }
//
//    public void map() {
//        getScheduledFutures();
//        try {
//            for (ScheduledTask st : scheduledTasks) {
//                ScheduledFuture sf = (ScheduledFuture) BeanUtils.getProperty(st, FIELD_FUTURE);
//                if (sf.getClass().getSimpleName().equals("ReschedulingRunnable")) {
//                    ScheduledMethodRunnable runnable = (ScheduledMethodRunnable) BeanUtils.getProperty(sf, "delegate");
//                    ScheduleData.JOB_RUNNING_MAP.put(ScheduledInfoDto.TYPE_CRON + runnable.getMethod().toString(), sf);
//                }
//            }
//        } catch (NoSuchFieldException e) {
//            throw new SchedulingException("not found scheduledTasks field.");
//        }
//    }
//
//    public void pause(String method) {
//        if (null == ScheduleData.JOB_RUNNING_MAP || ScheduleData.JOB_RUNNING_MAP.size() == 0) {
//            map();
//        }
//        ScheduledFuture sf = ScheduleData.JOB_RUNNING_MAP.get(method);
//        if (null != sf) {
//            sf.cancel(true);
//        }
//    }
//
//    public void resume(String method) {
//        if (null == ScheduleData.JOB_RUNNING_MAP || ScheduleData.JOB_RUNNING_MAP.size() == 0) {
//            map();
//        }
//        ScheduledFuture sf = ScheduleData.JOB_RUNNING_MAP.get(method);
//        if (null != sf) {
//            try {
//                Method m = sf.getClass().getDeclaredMethod(RUNNABLE_METHOD);
//                m.invoke(sf);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    /**
//     * 添加任务
//     *
//     * @param taskId
//     * @param triggerTask
//     */
//    public void addTriggerTask(String taskId, TriggerTask triggerTask) {
//        if (taskFutures.containsKey(taskId)) {
//            throw new SchedulingException("the taskId[" + taskId + "] was added.");
//        }
//        TaskScheduler scheduler = taskRegistrar.getScheduler();
//        ScheduledFuture<?> future = scheduler.schedule(triggerTask.getRunnable(), triggerTask.getTrigger());
////        getScheduledFutures().add(future);
//        taskFutures.put(taskId, future);
//    }
//
//    /**
//     * 取消任务
//     *
//     * @param taskId
//     */
//    public void cancelTriggerTask(String taskId) {
//        ScheduledFuture<?> future = taskFutures.get(taskId);
//        if (future != null) {
//            future.cancel(true);
//        }
//        taskFutures.remove(taskId);
//        getScheduledFutures().remove(future);
//    }
//
//    /**
//     * 重置任务
//     *
//     * @param taskId
//     * @param triggerTask
//     */
//    public void resetTriggerTask(String taskId, TriggerTask triggerTask) {
//        cancelTriggerTask(taskId);
//        addTriggerTask(taskId, triggerTask);
//    }
//
//    /**
//     * 任务编号
//     *
//     * @return
//     */
//    public Set<String> taskIds() {
//        return taskFutures.keySet();
//    }
//
//    /**
//     * 是否存在任务
//     *
//     * @param taskId
//     * @return
//     */
//    public boolean hasTask(String taskId) {
//        return this.taskFutures.containsKey(taskId);
//    }
//
//    /**
//     * 任务调度是否已经初始化完成
//     *
//     * @return
//     */
//    public boolean inited() {
//        return this.taskRegistrar != null && this.taskRegistrar.getScheduler() != null;
//    }
//}