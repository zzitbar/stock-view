package cn.coderme.stockview.job;

import cn.coderme.stockview.Constants;
import cn.coderme.stockview.base.StockApiProperties;
import cn.coderme.stockview.service.StockHistoryService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * 调用网易股票接口 导入记录交易csv
 * Created By Administrator
 * Date:2018/6/25
 * Time:10:32
 */
@Service
public class NeteaseImportHisCsvJob implements BaseJob {

    @Autowired
    private StockApiProperties stockApiProperties;
    @Autowired
    private StockHistoryService stockHistoryService;

    /**
     * 导入记录交易csv
     * 每周一至周五凌晨2点 0 0 2 ? * MON-FRI
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

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        importCsv();
    }
}