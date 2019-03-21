package cn.coderme.stockview;

import cn.coderme.stockview.job.NeteaseDownloadHisCsvJob;
import cn.coderme.stockview.job.NeteaseImportHisCsvJob;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created By Administrator
 * Date:2018/6/21
 * Time:9:38
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class NeteaseTest {

    @Autowired
    private NeteaseDownloadHisCsvJob neteaseDownloadHisCsvJob;
    @Autowired
    private NeteaseImportHisCsvJob neteaseImportHisCsvJob;

    /**
     * 下载csv
     */
    @Test
    public void downloadCsvFromNetease() {
        neteaseDownloadHisCsvJob.downloadCsvFromNetease();
    }

    /**
     * csv 导入数据库
     */
    @Test
    public void importCsv() {
        neteaseImportHisCsvJob.importCsv();
    }

    /**
     * 抓取停复牌信息
     */
//    @Test
//    public void suspend() {
//        neteaseJob.suspend();
//    }
//    /**
//     * 抓取新股信息
//     */
//    @Test
//    public void handleNewStock() {
//        neteaseJob.handleNewStock();
//    }
}