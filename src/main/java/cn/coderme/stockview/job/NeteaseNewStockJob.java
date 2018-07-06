package cn.coderme.stockview.job;

import cn.coderme.stockview.dataobtain.netease.handler.NeteaseStockHistoryHandler;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 调用网易股票接口 抓取新股信息
 * Created By Administrator
 * Date:2018/6/25
 * Time:10:32
 */
@Service
public class NeteaseNewStockJob implements BaseJob {

    @Autowired
    private NeteaseStockHistoryHandler neteaseStockHistoryHandler;


    /**
     * 抓取新股信息
     * 每周一至周五上午8点 0 0 8 ? * 1/5
     */
    public void handleNewStock() {
        try {
            neteaseStockHistoryHandler.handleNewStock();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        handleNewStock();
    }
}