package cn.coderme.stockview.job;

import cn.coderme.stockview.dto.live.LiveMessageDto;
import cn.coderme.stockview.entity.LiveMessages;
import cn.coderme.stockview.entity.LiveRoom;
import cn.coderme.stockview.service.LiveMessagesService;
import cn.coderme.stockview.service.LiveRoomService;
import com.alibaba.fastjson.JSON;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 抓取财经直播消息(每小时执行一次，获取上一小时所有财经消息)
 * Created By Administrator
 * Date:2018/7/6
 * Time:16:20
 */
@Service
public class LiveMessageHourJob implements BaseJob {

    // 直播消息实时接口
    private static final String NETEASE_LIVE_MESSAGE_URL = "http://data.live.126.net/live/{0}.json";
    // 直播消息历史URL
    private static final String NETEASE_LIVE_MESSAGE_ALL_URL = "http://data.live.126.net/liveAll/{0}/{1}.json";
    // 直播消息实时接口(按时间点) 0:roomId（如185422）,1:时间点（如20180709-13）
    private static final String NETEASE_LIVE_MESSAGE_TIME_LOG = "http://data.live.126.net/liveTimeLog/{0}/{1}.json";

    @Autowired
    private LiveMessagesService liveMessagesService;
    @Autowired
    private LiveRoomService liveRoomService;

    /**
     * 获取实时财经消息
     * 每小时调用一次
     * @param context
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            LiveRoom room = liveRoomService.findByLiveDate(LocalDate.now());
            if (null != room) {
                getMessageByHour(room, LocalDateTime.now().minusHours(1L));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getMessageByHour(LiveRoom room, LocalDateTime dateTime) throws IOException {
        if (null == dateTime) {
            dateTime = LocalDateTime.of(room.getLiveDate(), LocalTime.MIN);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HH");
        Document doc = Jsoup.connect(MessageFormat.format(NETEASE_LIVE_MESSAGE_TIME_LOG, room.getRoomId(), dateTime.format(formatter)))
                .ignoreContentType(true)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:59.0) Gecko/20100101 Firefox/59.0")
                .timeout(60000)
                .get();
        Element body = doc.body();
        String bodyStr = body.html();
        if (StringUtils.hasText(bodyStr)) {
            LiveMessageDto liveMessageDto = JSON.parseObject(bodyStr, LiveMessageDto.class);
            if (null != liveMessageDto.getMessages() && liveMessageDto.getMessages().size()>0) {
                liveMessagesService.insertMessage(room.getRoomId(), liveMessageDto);
            }
            if (!dateTime.isAfter(LocalDateTime.of(room.getLiveDate(), LocalTime.MAX))) {
                dateTime = dateTime.plusHours(1L);
                getMessageByHour(room, dateTime);
            }
        }
    }

    public static void main(String[] args) {
        LocalDateTime ld = LocalDateTime.parse("2018-01-01 07:36:28", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(ld);
        System.out.println(MessageFormat.format(NETEASE_LIVE_MESSAGE_ALL_URL, "asf", 1));
        System.out.println(LocalDateTime.of(LocalDate.now(), LocalTime.MIN));
    }
}