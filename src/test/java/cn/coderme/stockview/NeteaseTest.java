package cn.coderme.stockview;

import cn.coderme.stockview.dataobtain.netease.jobs.NeteaseJob;
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
    private NeteaseJob neteaseJob;

    /**
     * 下载csv
     */
    @Test
    public void downloadCsvFromNetease() {
        neteaseJob.downloadCsvFromNetease();
    }

    /**
     * csv 导入数据库
     */
    @Test
    public void importCsv() {
        neteaseJob.importCsv();
    }
}