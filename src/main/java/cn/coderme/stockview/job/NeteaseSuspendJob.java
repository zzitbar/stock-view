package cn.coderme.stockview.job;

import cn.coderme.stockview.dataobtain.netease.handler.NeteaseStockHistoryHandler;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 调用网易股票停复牌接口 获取停复牌
 * Created By Administrator
 * Date:2018/6/25
 * Time:10:32
 */
@Service
public class NeteaseSuspendJob implements BaseJob {

    @Autowired
    private NeteaseStockHistoryHandler neteaseStockHistoryHandler;

    /**
     * 停复牌
     * 每周一至周五上午9点和下午1点 0 0 9,13 ? * 1/5
     */
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        neteaseStockHistoryHandler.handleSuspend(0);
    }
}