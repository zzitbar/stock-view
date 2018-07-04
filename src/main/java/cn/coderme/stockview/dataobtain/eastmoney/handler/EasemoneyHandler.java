package cn.coderme.stockview.dataobtain.eastmoney.handler;

import cn.coderme.stockview.Constants;
import cn.coderme.stockview.dataobtain.eastmoney.dto.StockSuspendDto;
import cn.coderme.stockview.dataobtain.netease.dto.SuspendStockDto;
import cn.coderme.stockview.entity.StockInfo;
import cn.coderme.stockview.entity.StockRealtime;
import cn.coderme.stockview.entity.StockSuspend;
import cn.coderme.stockview.service.StockInfoService;
import cn.coderme.stockview.service.StockRealtimeService;
import cn.coderme.stockview.service.StockSuspendService;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

/**
 * Created By Administrator
 * Date:2018/7/3
 * Time:16:47
 */
@Service
public class EasemoneyHandler {

    private static final String TFP_URL = "http://datainterface.eastmoney.com/EM_DataCenter/JS.aspx?type=FD&sty=SRB&st=0&p=3&ps=50&js={pages:(pc),data:[(x)]}&mkt=1&fd=2018-07-03";

    @Autowired
    private StockRealtimeService stockRealtimeService;
    @Autowired
    private StockInfoService stockInfoService;
    @Autowired
    private StockSuspendService stockSuspendService;

    @Transactional
    public void handleSuspend(Integer page) {
        page = null==page?0:page;
        Integer count = 100;
        String date = LocalDate.now().toString();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(TFP_URL, String.class, page, date, count);
        StockSuspendDto suspendStockDto = JSON.parseObject(response.getBody(), StockSuspendDto.class);
        if (null != suspendStockDto && null!= suspendStockDto.getData() && suspendStockDto.getData().size()>0) {
            for (String str : suspendStockDto.getData()) {
                String[] data = str.split(",");
                if (data.length >= 9) {
                    LocalDate openDate = StringUtils.hasText(data[8])?LocalDate.parse(data[8]):null;
                    StockInfo si = stockInfoService.selectOne(new EntityWrapper<StockInfo>()
                            .eq("stockCode", data[0])
                            .ne("id", "1"));
                    if (null != si) {
                        StockRealtime sr = stockRealtimeService.selectOne(new EntityWrapper<StockRealtime>().eq("stockId", si.getId()));
                        if (null != sr) {
                            sr.setStatus((null != openDate && openDate.equals(LocalDate.now()))?Constants.STOCK_STATUS.TRADE.getValue():
                                    Constants.STOCK_STATUS.SUSPEND.getValue());
                            stockRealtimeService.insertOrUpdate(sr);
                        }
                        StockSuspend ss = stockSuspendService.selectOne(new EntityWrapper<StockSuspend>()
                                .eq("stockId", si.getId())
                                .eq("suspendDate", LocalDate.parse(data[7])));
                        if (null == ss) {
                            ss = new StockSuspend();
                        }
                        ss.setStockId(si.getId());
                        ss.setStockCode(si.getStockCode());
                        ss.setStockName(si.getStockName());
                        ss.setSuspendDate(LocalDate.parse(data[7]));
                        ss.setReopenDate(LocalDate.parse(data[8]));
//                        ss.setSuspendTitle(lb.getNTRAD1());
//                        ss.setSuspendDesc(lb.getNTRAD6());
//                        ss.setType(lb.getTYPE());
                        stockSuspendService.insertOrUpdate(ss);
                    }
                }
            }
        }
    }
}