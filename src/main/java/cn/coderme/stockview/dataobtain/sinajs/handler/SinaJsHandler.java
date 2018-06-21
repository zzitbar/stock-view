package cn.coderme.stockview.dataobtain.sinajs.handler;

import cn.coderme.stockview.base.StockApiProperties;
import cn.coderme.stockview.entity.StockInfo;
import cn.coderme.stockview.entity.StockRealtime;
import cn.coderme.stockview.service.StockRealtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

/**
 * Created By Administrator
 * Date:2018/6/14
 * Time:13:48
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SinaJsHandler {

    private static final Integer REQUEST_BATCH_CNT = 5;

    @Autowired
    private StockApiProperties stockApiProperties;
    @Autowired
    private StockRealtimeService stockRealtimeService;

    @Async
    public void handle(Map.Entry<String, List<StockInfo>> entry) {
        long start = System.currentTimeMillis();
        System.out.println(entry.getKey()+ " start");
        List<StockInfo> entryValue = entry.getValue();
        int size = entryValue.size();
        List<StockInfo> stockInfos = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int remainder = i % REQUEST_BATCH_CNT;
            if (remainder < REQUEST_BATCH_CNT) {
                stockInfos.add(entryValue.get(i));
            }
            if (remainder == REQUEST_BATCH_CNT-1 || i == size-1) {
                handle(stockInfos);
                stockInfos = new ArrayList<>();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(entry.getKey()+ " end, deal "+size+"， cost "+(end-start)+" milliseconds ");
    }

    @Async
    @Transactional
    public void handle(List<StockInfo> stockInfos) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        //http://hq.sinajs.cn/?format=text&list=sh601688
//            ResponseEntity<String> response = restTemplate.getForEntity(new URI(stockApiProperties.getSinajsApi()), String.class);
        StringBuilder sb = new StringBuilder();
        Map<String, StockInfo> stockCodeMap = new HashMap<>();
        for (StockInfo si : stockInfos) {
            stockCodeMap.put(si.getMarket()+si.getStockCode(), si);
            sb.append(si.getMarket()).append(si.getStockCode()).append(",");
        }
        if (sb.length() > 0) {
            ResponseEntity<String> response = restTemplate.getForEntity(
                    stockApiProperties.getSinajsApi(), String.class, sb.toString());
            if (response.getStatusCode().equals(HttpStatus.OK)) {
                //sh601688=华泰证券,16.470,16.530,16.560,16.810,16.430,16.560,16.570,28541742,474466874.000,2400,16.560,89100,16.550,50400,16.540,138801,16.530,41582,16.520,25900,16.570,21100,16.580,23400,16.590,25600,16.600,37200,16.610,2018-06-14,15:00:00,00
                for (String str : response.getBody().split("\n")) {
                    String[] result = str.split("=");
                    if (result.length == 2) {
                        StockInfo stockInfo = stockCodeMap.get(result[0]);
                        String[] data = result[1].split(",");

                        StockRealtime stockRealtime = stockRealtimeService.findByStockId(stockInfo.getId());
                        if (null == stockRealtime) {
                            stockRealtime = new StockRealtime();
                            stockRealtime.setStockId(stockInfo.getId());
                            stockRealtime.setStockCode(stockInfo.getStockCode());
                        }
                        stockRealtime.setUpdateTime(new Date());
                        // 最后更新时间
                        LocalTime lastUpdateTime = stockRealtime.getRealTime();
                        LocalDate lastUpdateDate = stockRealtime.getRealDate();
                        if (data.length >= 33) {
                            stockRealtime.setOpen(Double.valueOf(data[1]));
                            stockRealtime.setLastClose(Double.valueOf(data[2]));
                            stockRealtime.setCurrentPrice(Double.valueOf(data[3]));
                            stockRealtime.setHigh(Double.valueOf(data[4]));
                            stockRealtime.setLow(Double.valueOf(data[5]));
                            stockRealtime.setBidPrice(Double.valueOf(data[6]));
                            stockRealtime.setAuctionPrice(Double.valueOf(data[7]));
                            stockRealtime.setDealQty(Double.valueOf(data[8]));
                            stockRealtime.setDealMoney(Double.valueOf(data[9]));
                            stockRealtime.setBuy1Qty(Double.valueOf(data[10]));
                            stockRealtime.setBuy1Price(Double.valueOf(data[11]));
                            stockRealtime.setBuy2Qty(Double.valueOf(data[12]));
                            stockRealtime.setBuy2Price(Double.valueOf(data[13]));
                            stockRealtime.setBuy3Qty(Double.valueOf(data[14]));
                            stockRealtime.setBuy3Price(Double.valueOf(data[15]));
                            stockRealtime.setBuy4Qty(Double.valueOf(data[16]));
                            stockRealtime.setBuy4Price(Double.valueOf(data[17]));
                            stockRealtime.setBuy5Qty(Double.valueOf(data[18]));
                            stockRealtime.setBuy5Price(Double.valueOf(data[19]));
                            stockRealtime.setSell1Qty(Double.valueOf(data[20]));
                            stockRealtime.setSell1Price(Double.valueOf(data[21]));
                            stockRealtime.setSell2Qty(Double.valueOf(data[22]));
                            stockRealtime.setSell2Price(Double.valueOf(data[23]));
                            stockRealtime.setSell3Qty(Double.valueOf(data[24]));
                            stockRealtime.setSell3Price(Double.valueOf(data[25]));
                            stockRealtime.setSell4Qty(Double.valueOf(data[26]));
                            stockRealtime.setSell4Price(Double.valueOf(data[27]));
                            stockRealtime.setSell5Qty(Double.valueOf(data[28]));
                            stockRealtime.setSell5Price(Double.valueOf(data[29]));
                            stockRealtime.setRealDate(LocalDate.parse(data[30]));
                            stockRealtime.setRealTime(LocalTime.parse(data[31]));
                            if (null != stockRealtime.getLastClose() && null != stockRealtime.getCurrentPrice()
                                    && !Double.valueOf(0).equals(stockRealtime.getLastClose())) {
                                stockRealtime.setIncreaseRate(splitAndRound((stockRealtime.getCurrentPrice() - stockRealtime.getLastClose()) / stockRealtime.getLastClose(), 4));
                            }
                        }
                        if (null != stockRealtime.getId()) {
                            // 更新时，判断更新时间是否小于之前的最后更新时间
                            LocalDateTime ldt = LocalDateTime.of(lastUpdateDate, lastUpdateTime);
                            if (!ldt.isBefore(LocalDateTime.of(stockRealtime.getRealDate(), stockRealtime.getRealTime()))) {
                                continue;
                            }
                        }
                        stockRealtimeService.insertOrUpdate(stockRealtime);
                    }
                }
            }
        }

    }

    /**
     * 保留 n 位小数
     * @param a
     * @param n
     * @return
     */
    public double splitAndRound(double a, int n) {
        a = a * Math.pow(10, n);
        return (Math.round(a)) / (Math.pow(10, n));
    }

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        //http://hq.sinajs.cn/?format=text&list=sh601688
//            ResponseEntity<String> response = restTemplate.getForEntity(new URI(stockApiProperties.getSinajsApi()), String.class);
        ResponseEntity<String> response = restTemplate.getForEntity("http://hq.sinajs.cn/?format=text&list=sh601688,sh601003,sh601001", String.class);

        System.out.println(response);
        String result = response.getBody();
        System.out.println(result.split("\n"));
    }
}