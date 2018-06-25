package cn.coderme.stockview.dataobtain.netease.jobs;

import cn.coderme.stockview.Constants;
import cn.coderme.stockview.base.StockApiProperties;
import cn.coderme.stockview.dataobtain.netease.handler.NeteaseStockHistoryHandler;
import cn.coderme.stockview.entity.StockInfo;
import cn.coderme.stockview.service.StockHistoryService;
import cn.coderme.stockview.service.StockInfoService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created By Administrator
 * Date:2018/6/25
 * Time:10:32
 */
@Service
public class NeteaseJob {

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
     */
//    @Scheduled(fixedRate = 300000)
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
     */
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
}