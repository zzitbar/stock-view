package cn.coderme.stockview.job;

import cn.coderme.stockview.entity.LiveRoom;
import cn.coderme.stockview.service.LiveRoomService;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 抓取财经直播间
 * Created By Administrator
 * Date:2018/7/6
 * Time:16:20
 */
@Service
public class LiveRoomJob implements BaseJob {

    private static final Logger logger = LoggerFactory.getLogger(LiveRoomJob.class);

    private static final String NETEASE_LIVE_ROOM_URL = "http://money.163.com/special/stock{0}";

    private static final String NETEASE_LIVE_MSG_URL = "http://data.live.126.net/live/{0}.json";

    @Autowired
    private LiveRoomService liveRoomService;

    /**
     * 抓取网易财经直播间
     * 每天每小时执行一次
     * @param context
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        crawl(null);
    }

    public void crawl(LocalDate localDate) {
        if (null == localDate) {
            localDate = LocalDate.now();
        }
        String date = localDate.format(DateTimeFormatter.BASIC_ISO_DATE);
        String url = MessageFormat.format(NETEASE_LIVE_ROOM_URL, date.substring(2));
        try {
            Connection.Response response = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:59.0) Gecko/20100101 Firefox/59.0")
                    .timeout(60000)
                    .execute();
            if (response.statusCode() == 200) {
                Document doc = response.parse();
                Element element = doc.selectFirst("#roomid");
                String roomId = element.val();
                if (StringUtils.isEmpty(roomId)) {
                    throw new Exception("直播间ID为空");
                }
                LiveRoom liveRoom = liveRoomService.findByRoomId(roomId);
                if (null == liveRoom) {
                    liveRoom = new LiveRoom();
                }
                liveRoom.setRoomId(roomId);
                liveRoom.setTitle(doc.title());
                liveRoom.setLiveDate(localDate);
                liveRoom.setLiveMsgUrl(MessageFormat.format(NETEASE_LIVE_MSG_URL, roomId));
                liveRoomService.insertOrUpdate(liveRoom);
            } else {
                logger.error("请求 " +url+" 出错，HttpCode: "+response.statusCode());
            }
        } catch (Exception e) {
            logger.error("获取直播间出错", e);
        }
    }

    public static void main(String[] args) {
        new LiveRoomJob().crawl(null);
    }
}