package cn.coderme.stockview.job;

import cn.coderme.stockview.Constants;
import cn.coderme.stockview.dataobtain.netease.handler.NeteaseStockHistoryHandler;
import cn.coderme.stockview.entity.StockInfo;
import cn.coderme.stockview.service.StockInfoService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 调用网易股票接口下载记录交易csv
 * Created By Administrator
 * Date:2018/6/25
 * Time:10:32
 */
@Service
public class NeteaseDownloadHisCsvJob implements BaseJob {

    @Autowired
    private NeteaseStockHistoryHandler neteaseStockHistoryHandler;
    @Autowired
    private StockInfoService stockInfoService;

    /**
     * 下载记录交易csv
     * 每周一至周五凌晨1点 0 0 1 ? * MON-FRI
     */
    public void downloadCsvFromNetease() {
        EntityWrapper<StockInfo> ew = new EntityWrapper<>();
        ew.setSqlSelect("id, stockCode, market, lastHistoryDate, type");
//        ew.eq("stockCode", "000001");
//        ew.eq("type", "2");
//        ew.lt("lastHistoryDate", LocalDate.now().minusDays(1)).or().isNull("lastHistoryDate");
        long start = System.currentTimeMillis();
        System.out.println("下载记录交易csv start");
        List<StockInfo> stockInfoList = stockInfoService.selectList(ew);

        int size = stockInfoList.size();
        int threadCnt = stockInfoList.size()/ Constants.THREAD_DEAL_SIZE;
        int fromIndex = 0, toIndex = 0;
        Map<String, List<StockInfo>> stockMap = new HashMap<>();
        for (int i = 0; i <= threadCnt; i++) {
            fromIndex = toIndex;
            toIndex = i==threadCnt?size:(i+1)*Constants.THREAD_DEAL_SIZE;
            stockMap.put("Thread-"+i, stockInfoList.subList(fromIndex, toIndex));
        }

        if (null != stockMap && stockMap.size()>0) {
            for (Map.Entry<String, List<StockInfo>> entry : stockMap.entrySet()) {
                try {
                    neteaseStockHistoryHandler.handle(entry.getValue());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("下载记录交易csv end,  cost "+(end-start)+" milliseconds ");
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        downloadCsvFromNetease();
    }
}