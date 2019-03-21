package cn.coderme.stockview;

import cn.coderme.stockview.entity.LiveRoom;
import cn.coderme.stockview.job.LiveMessageHourJob;
import cn.coderme.stockview.job.LiveMessageJob;
import cn.coderme.stockview.job.LiveRoomJob;
import cn.coderme.stockview.service.LiveRoomService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * Created By Administrator
 * Date:2018/7/6
 * Time:16:45
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LiveRoomJobTest {

    @Autowired
    private LiveRoomJob liveRoomJob;
    @Autowired
    private LiveMessageJob liveMessageJob;
    @Autowired
    private LiveRoomService liveRoomService;
    @Autowired
    private LiveMessageHourJob liveMessageHourJob;

    private static final LocalDate DATE = LocalDate.now().withDayOfYear(1);

    @Test
    public void test(){
        LocalDate ld = LocalDate.parse("2018-07-07");
        while (!ld.isAfter(LocalDate.now())) {
            liveRoomJob.crawl(ld);
            ld = ld.plusDays(1);
        }
    }

    @Test
    public void messageHistory() {
        List<LiveRoom> rooms = liveRoomService.selectList(new EntityWrapper<LiveRoom>().ge("liveDate", LocalDate.parse("2018-07-09")));
        for (LiveRoom lr : rooms) {
            try {
                liveMessageHourJob.getMessageByHour(lr, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    public void message() {
//        liveMessageHourJob.getMessageRest("184831", 9);
    }

}