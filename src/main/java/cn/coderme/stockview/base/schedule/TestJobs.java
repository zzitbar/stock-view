package cn.coderme.stockview.base.schedule;

import cn.coderme.stockview.base.annotation.Job;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

/**
 * Created By Administrator
 * Date:2018/7/4
 * Time:11:26
 */
@Job
@Service
public class TestJobs {

//    @Scheduled(cron = "5 * * * * *")
    public void test1() {
        System.out.println("-----test1 cron working-----"+ LocalTime.now().toString());
    }

    @Scheduled(fixedRate = 5000)
    public void test2() {
        System.out.println(ScheduleConfigurer.taskRegistrar);
        System.out.println("-----test2 fixedRate working-----"+ LocalTime.now().toString());
    }

    @Scheduled(fixedDelay = 10000)
    public void test3() {
        System.out.println("-----test3 fixedDelay working-----"+ LocalTime.now().toString());
    }
}