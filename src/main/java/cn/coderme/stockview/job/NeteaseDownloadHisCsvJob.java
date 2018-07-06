package cn.coderme.stockview.job;

import cn.coderme.stockview.dataobtain.netease.handler.NeteaseStockHistoryHandler;
import cn.coderme.stockview.entity.StockInfo;
import cn.coderme.stockview.service.StockInfoService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

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
//        ew.eq("stockCode", "300746");
        long start = System.currentTimeMillis();
        System.out.println("下载记录交易csv start");
        List<StockInfo> stockInfoList = stockInfoService.selectList(ew);
        try {
            neteaseStockHistoryHandler.handle(stockInfoList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("下载记录交易csv end,  cost "+(end-start)+" milliseconds ");
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        downloadCsvFromNetease();
    }
}