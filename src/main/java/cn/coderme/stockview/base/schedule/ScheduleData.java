package cn.coderme.stockview.base.schedule;

import cn.coderme.stockview.dto.job.ScheduledInfoDto;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.scheduling.config.Task;

import java.util.*;
import java.util.concurrent.ScheduledFuture;

/**
 * Created By Administrator
 * Date:2018/7/4
 * Time:15:25
 */
public class ScheduleData {

    public static Set<ScheduledInfoDto> SCHEDULED_INFOS = new HashSet<>();
    public static Map<String, ScheduledInfoDto> SCHEDULE_MAP = new HashMap<>(16);
    public static Map<String, ScheduledFuture> JOB_RUNNING_MAP = new HashMap<>(16);
    public static Map<String, ? super Task> JOB_REMOVED_MAP = new HashMap<>(16);

    public static Map<String, List> JOB_METHOD_STRING_TASK_LIST_MAP = new HashMap<>(16);

}