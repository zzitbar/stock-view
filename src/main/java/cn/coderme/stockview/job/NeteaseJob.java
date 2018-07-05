package cn.coderme.stockview.job;

import cn.coderme.stockview.Constants;
import cn.coderme.stockview.base.StockApiProperties;
import cn.coderme.stockview.base.annotation.Job;
import cn.coderme.stockview.base.annotation.JobInfo;
import cn.coderme.stockview.dataobtain.netease.handler.NeteaseStockHistoryHandler;
import cn.coderme.stockview.entity.StockInfo;
import cn.coderme.stockview.service.StockHistoryService;
import cn.coderme.stockview.service.StockInfoService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 调用网易股票接口获取数据
 * Created By Administrator
 * Date:2018/6/25
 * Time:10:32
 */
@Service
public class NeteaseJob implements BaseJob {

    @Autowired
    private StockApiProperties stockApiProperties;
    @Autowired
    private NeteaseStockHistoryHandler neteaseStockHistoryHandler;
    @Autowired
    private StockInfoService stockInfoService;
    @Autowired
    private StockHistoryService stockHistoryService;

    /**
     * 下载记录交易csv
     * 每周一至周五凌晨1点
     */
    @Scheduled(cron = "* * 1 * * 1/5")
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

    /**
     * 导入记录交易csv
     * 每周一至周五凌晨2点
     */
    @JobInfo("导入记录交易csv")
    @Scheduled(cron = "* * 2 * * 1/5")
    public void importCsv() {
        for (Constants.STOCK_TYPE stockType : Constants.STOCK_TYPE.values()){
            File file = new File(stockApiProperties.getNeteaseHistoryCsvFilePath()+stockType.getValue());
            if (file.isDirectory()) {
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++) {
                    stockHistoryService.importCsv(file.getAbsolutePath() + "\\" + filelist[i], stockType.getValue());
                }
            }
        }
    }

    /**
     * 停复牌
     * 每周一至周五上午9点和下午1点
     */
    @JobInfo("抓取停复牌信息")
    @Scheduled(cron = "0 0 9,13 * * 1/5")
    public void suspend() {
        neteaseStockHistoryHandler.handleSuspend(0);
    }

    /**
     * 抓取新股信息
     * 每周一至周五上午8点
     */
    @JobInfo("抓取新股信息")
    @Scheduled(cron = "0 0 8 * * 1/5")
    public void handleNewStock() {
        try {
            neteaseStockHistoryHandler.handleNewStock();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 停复牌
     * 每周一至周五上午9点和下午1点
     */
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        neteaseStockHistoryHandler.handleSuspend(0);
    }
}