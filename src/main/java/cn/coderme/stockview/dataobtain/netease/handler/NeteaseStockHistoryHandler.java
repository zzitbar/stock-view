package cn.coderme.stockview.dataobtain.netease.handler;

import cn.coderme.stockview.Constants;
import cn.coderme.stockview.base.StockApiProperties;
import cn.coderme.stockview.entity.StockHistory;
import cn.coderme.stockview.entity.StockInfo;
import cn.coderme.stockview.service.StockHistoryService;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 下载网易股票历史交易数据csv文件
 * Created By Administrator
 * Date:2018/6/21
 * Time:9:24
 */
@Service
public class NeteaseStockHistoryHandler {
    @Autowired
    private StockApiProperties stockApiProperties;
    @Autowired
    private StockHistoryService stockHistoryService;

    public void handle(List<StockInfo> stockInfoList) throws IOException {
        String end = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        for(StockInfo si : stockInfoList) {
            String url = MessageFormat.format(stockApiProperties.getNeteaseHistoryCsvApi(), ("sh".equals(si.getMarket())?"0":"1")+si.getStockCode(),
                    si.getLastHistoryDate().plusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMdd")), end);
//            历史交易页面
//            http://quotes.money.163.com/trade/lsjysj_600795.html#06f01
//            http://quotes.money.163.com/trade/lsjysj_zhishu_000001.html
//            http://quotes.money.163.com/service/chddata.html?code=0000001&start=19901219&end=20180622&fields=TCLOSE;HIGH;LOW;TOPEN;LCLOSE;CHG;PCHG;VOTURNOVER;VATURNOVER
//            http://quotes.money.163.com/service/chddata.html?code=1000060&start=19970110&end=20180620&fields=TCLOSE;HIGH;LOW;TOPEN;LCLOSE;CHG;PCHG;TURNOVER;VOTURNOVER;VATURNOVER;TCAP;MCAP
            //Open a URL Stream
            Connection.Response response = Jsoup.connect(url).ignoreContentType(true).execute();
            // output here
            String dirPath = stockApiProperties.getNeteaseHistoryCsvFilePath()+si.getType()+"/";
            FileOutputStream out = (new FileOutputStream(new java.io.File(dirPath + si.getStockCode()+".csv")));
            out.write(response.bodyAsBytes());
            // response.body() is where the image's contents are.
            out.close();
        }
    }

}