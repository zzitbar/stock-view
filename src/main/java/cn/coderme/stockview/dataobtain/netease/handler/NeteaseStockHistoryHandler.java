package cn.coderme.stockview.dataobtain.netease.handler;

import cn.coderme.stockview.Constants;
import cn.coderme.stockview.base.StockApiProperties;
import cn.coderme.stockview.dataobtain.amap.AmapExpService;
import cn.coderme.stockview.dataobtain.netease.dto.SuspendStockDto;
import cn.coderme.stockview.dto.amap.AddressGeoDto;
import cn.coderme.stockview.entity.*;
import cn.coderme.stockview.service.*;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
    private StockRealtimeService stockRealtimeService;
    @Autowired
    private StockInfoService stockInfoService;
    @Autowired
    private StockSuspendService stockSuspendService;
    @Autowired
    private StockIssuingService stockIssuingService;
    @Autowired
    private AmapExpService amapExpService;

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

    /**
     * 处理停复牌
     * http://quotes.money.163.com/old/#query=TFPTS&DataType=tpts&sort=NTRAD4&order=desc&count=25&page=0
     * @param page
     */
    @Transactional
    public void handleSuspend(Integer page) {
        page = null==page?0:page;
        Integer count = 100;
        String date = LocalDate.now().toString();
//        String url = "http://quotes.money.163.com/hs/marketdata/service/tpts.php?host=/hs/marketdata/service/tpts.php&page={page}&query=date:{date};&sort=NTRAD4&order=desc&count={count}&type=query";
        String url = stockApiProperties.getNeteaseStockSuspendApi();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class, page, date, count);
        SuspendStockDto suspendStockDto = JSON.parseObject(response.getBody(), SuspendStockDto.class);
        if (null != suspendStockDto && suspendStockDto.getPagecount() > 1) {
            if (null != suspendStockDto.getList() && suspendStockDto.getList().size()>0 ) {
                for (SuspendStockDto.ListBean lb : suspendStockDto.getList()) {
                    StockInfo si = stockInfoService.selectOne(new EntityWrapper<StockInfo>().
                            eq("stockCode", lb.getSYMBOL()).
                            eq("market", "CNSESZ".equals(lb.getEXCHANGE())?Constants.STOCK_EXCHANGE.SZ.getValue():Constants.STOCK_EXCHANGE.SH.getValue()));
                    if (null != si) {
                        StockRealtime sr = stockRealtimeService.selectOne(new EntityWrapper<StockRealtime>().eq("stockId", si.getId()));
                        if (null != sr) {
                            sr.setStatus("今日停牌".equals(lb.getTYPE())?Constants.STOCK_STATUS.SUSPEND.getValue():
                                    Constants.STOCK_STATUS.TRADE.getValue());
                            sr.setUpdateTime(new Date());
                            stockRealtimeService.updateById(sr);
                        }
                        StockSuspend ss = stockSuspendService.selectOne(new EntityWrapper<StockSuspend>()
                                .eq("stockId", si.getId())
                                .eq("suspendDate", LocalDate.parse(lb.getNTRAD4())));
                        if (null == ss) {
                            ss = new StockSuspend();
                        }
                        ss.setStockId(si.getId());
                        ss.setStockCode(si.getStockCode());
                        ss.setStockName(si.getStockName());
                        ss.setSuspendDate(LocalDate.parse(lb.getNTRAD4()));
                        ss.setReopenDate(LocalDate.parse(lb.getNTRAD5()));
                        ss.setSuspendTitle(lb.getNTRAD1());
                        ss.setSuspendDesc(lb.getNTRAD6());
                        ss.setType(lb.getTYPE());
                        ss.setUpdateTime(LocalDateTime.now());
                        stockSuspendService.insertOrUpdate(ss);
                    }
                }
                handleSuspend(page+1);
            }

        }
    }

    /**
     * 处理新股信息
     */
    @Transactional
    public void handleNewStock() throws Exception {
        //http://quotes.money.163.com/data/ipo/shengou.html
        Document doc = Jsoup.connect("http://quotes.money.163.com/data/ipo/shengou.html")
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:59.0) Gecko/20100101 Firefox/59.0")
                .timeout(60000)
                .get();
        Elements trDatas = doc.select("table#plate_performance tbody tr");
        if (null != trDatas) {
            for (Element tr : trDatas) {
                Elements tdDatas = tr.select("td");
                String stockCode = tdDatas.get(2).html();
                String td5 = tdDatas.get(5).html();
                if (StringUtils.hasText(td5) && !"--".equals(td5)) {
                    LocalDate ipoDate = LocalDate.parse(td5);
                    StockIssuing issuing = stockIssuingService.selectOne(new EntityWrapper<StockIssuing>().eq("stockCode", stockCode));
//                    StockIssuing issuing = null;
                    if (null == issuing) {
                        Element link = tdDatas.get(3).child(0);
                        link.attr("href").split("/")[1].startsWith("1");
                        issuing = new StockIssuing(null,
                                stockCode, link.html(),
                                LocalDate.parse(tdDatas.get(4).html()), ipoDate,
                                Double.valueOf(tdDatas.get(6).html().replace(",", "")), Double.valueOf(tdDatas.get(7).html().replace(",", "")),
                                Double.valueOf(tdDatas.get(8).html()), Double.valueOf(tdDatas.get(9).html()),
                                Double.valueOf(tdDatas.get(10).html()), tdDatas.get(12).html(),
                                tdDatas.get(13).child(0).attr("onclick").replaceAll("showMsgBox\\('[\\u4e00-\\u9fa5]*：(\\S*)'\\)", "$1").replace("\\n", ","));
                        stockIssuingService.insert(issuing);

                        StockInfo stockInfo = stockInfoService.selectOne(new EntityWrapper<StockInfo>().eq("stockCode", issuing.getStockCode()));
                        if (null == stockInfo) {
                            stockInfo = new StockInfo();
                            stockInfo.setStockCode(issuing.getStockCode());
                            stockInfo.setStockName(issuing.getStockName());
                            stockInfo.setIpoDate(issuing.getIpoDate());
                            if (link.attr("href").split("/")[1].startsWith("1")) {
                                stockInfo.setMarket(Constants.STOCK_EXCHANGE.SZ.getValue());
                            } else {
                                stockInfo.setMarket(Constants.STOCK_EXCHANGE.SH.getValue());
                            }
                            crawlStockInfo(stockInfo);
                            AddressGeoDto dto = amapExpService.adderssResolve(stockInfo.getAddress(), "");
                            if (null != dto.getGeocodes() && dto.getGeocodes().size()>0) {
                                AddressGeoDto.GeocodesBean geocodesBean = dto.getGeocodes().get(0);
                                if (null != geocodesBean.getProvince()) {
                                    stockInfo.setProvince(geocodesBean.getProvince());
                                }
                                if (null != geocodesBean.getCity()) {
                                    stockInfo.setCity(geocodesBean.getCity());
                                }
                                if (null != geocodesBean.getDistrict()) {
                                    stockInfo.setDistrict(geocodesBean.getDistrict());
                                }
                                if (null != geocodesBean.getLocation()) {
                                    stockInfo.setLocation(geocodesBean.getLocation());
                                }
                            }
                            stockInfoService.insert(stockInfo);
                        }
                    }
                }

            }
        }
    }

    /**
     * 爬取公司资料详情页
     * @param stockInfo
     * @throws Exception
     */
    private void crawlStockInfo(StockInfo stockInfo) throws Exception {
        //公司资料
        Document doc = Jsoup.connect("http://quotes.money.163.com/f10/gszl_"+stockInfo.getStockCode()+".html")
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:59.0) Gecko/20100101 Firefox/59.0")
                .timeout(60000)
                .get();
        Elements trDatas = doc.select("table.table_details>tbody>tr");
        stockInfo.setAddress(trDatas.get(1).select("td").get(3).html());
        stockInfo.setCompanyNameCn(trDatas.get(2).select("td").get(1).html());
        stockInfo.setCompanyNameEn(trDatas.get(3).select("td").get(1).html());
        stockInfo.setEmail(trDatas.get(3).select("td").get(3).html());
        stockInfo.setLegalRepresentative(trDatas.get(6).select("td").get(1).html());
        stockInfo.setSecretary(trDatas.get(5).select("td").get(3).html());
        stockInfo.setWebsite(trDatas.get(8).select("td").get(1).html());
        //股东分析
        Document docGd = Jsoup.connect("http://quotes.money.163.com/f10/gdfx_"+stockInfo.getStockCode()+".html")
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:59.0) Gecko/20100101 Firefox/59.0")
                .timeout(60000)
                .get();
        Elements qtyTrDatas = docGd.select("table.table_bg001").get(1).select("tbody>tr");
        String totalQty = qtyTrDatas.get(0).select("td").get(1).html();
        String negotiableQty = qtyTrDatas.get(1).select("td").get(1).html();
        stockInfo.setTotalQty(Double.valueOf(totalQty)*100000000);
        stockInfo.setNegotiableQty(Double.valueOf(negotiableQty)*100000000);
    }

    public static void main(String[] args) {
        try {
            new NeteaseStockHistoryHandler().handleNewStock();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}