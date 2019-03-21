package cn.coderme.stockview.job;

import cn.coderme.stockview.Constants;
import cn.coderme.stockview.base.annotation.Job;
import cn.coderme.stockview.base.annotation.JobInfo;
import cn.coderme.stockview.dataobtain.sinajs.handler.SinaJsHandler;
import cn.coderme.stockview.entity.StockInfo;
import cn.coderme.stockview.service.StockInfoService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 调用新浪股票接口获取数据
 * Created By Administrator
 * Date:2018/6/14
 * Time:17:18
 */
@Service
public class SinaJsJob implements BaseJob {

    @Autowired
    private SinaJsHandler sinaJsHandler;
    @Autowired
    private StockInfoService stockInfoService;

    private Map<String, List<StockInfo>> stockMap = new HashMap<>();

    /**
     * 抓取股票实时交易数据
     * 每周一至周五9点至15点，每隔5分钟 0 0/5 9-15 ? * MON-FRI
     */
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        if (null == stockMap || stockMap.size() == 0) {
            synchronized (this) {
                EntityWrapper<StockInfo> ew = new EntityWrapper<>();
                ew.setSqlSelect("id, stockCode, market");//只查询3个字段
//                ew.eq("stockCode", "300746");
                List<StockInfo> stockInfoList = stockInfoService.selectList(ew);
                int size = stockInfoList.size();
                int threadCnt = stockInfoList.size()/ Constants.THREAD_DEAL_SIZE;
                int fromIndex = 0, toIndex = 0;
                for (int i = 0; i <= threadCnt; i++) {
                    fromIndex = toIndex;
                    toIndex = i==threadCnt?size:(i+1)*Constants.THREAD_DEAL_SIZE;
                    stockMap.put("Thread-"+i, stockInfoList.subList(fromIndex, toIndex));
                }
                stockInfoList = null;
            }
        }
        if (null != stockMap && stockMap.size()>0) {
            for (Map.Entry<String, List<StockInfo>> entry : stockMap.entrySet()) {
                sinaJsHandler.handle(entry);
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(new Integer[]{1,2,3,4,5,6,7,8,9,10});
        System.out.println(list.subList(0, 5));
        System.out.println(list.subList(5, 10));

        int size = 4;
        System.out.println((6<5)?4:2-1);
    }


}