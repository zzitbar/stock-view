package cn.coderme.stockview.dataobtain.baseinfo;

import cn.coderme.stockview.base.StockApiProperties;
import cn.coderme.stockview.entity.StockInfo;
import cn.coderme.stockview.service.StockInfoService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 爬取上交所股票基本信息
 * http://www.sse.com.cn/assortment/stock/list/info/company/index.shtml?COMPANY_CODE=600008
 * Created By Administrator
 * Date:2018/6/15
 * Time:10:41
 */
@Service
public class StockInfoCrawl {

    @Autowired
    private StockApiProperties stockApiProperties;
    @Autowired
    private StockInfoService stockInfoService;

    public void crawlInfo(String stockCode) {
        List<StockInfo> stockInfos = new ArrayList<>();
        if (StringUtils.hasText(stockCode)) {
            StockInfo stockInfo = stockInfoService.selectOne(new EntityWrapper().eq("stockCode", stockCode));
            stockInfos.add(stockInfo);
        } else {
            EntityWrapper<StockInfo> ew = new EntityWrapper<>();
            ew.setSqlSelect("id, stockCode");//只查询2个字段
            ew.eq("market", "sh").isNull("companyNameCn");
            stockInfos = stockInfoService.selectList(ew);
        }
        for (StockInfo stockInfo : stockInfos) {
            try {
                Document doc = Jsoup.connect(stockApiProperties.getSseStockInfoApi()+stockInfo.getStockCode())
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:59.0) Gecko/20100101 Firefox/59.0")
                        .timeout(60000)
                        .get();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("Referer", "http://www.sse.com.cn/assortment/stock/list/info/company/index.shtml?COMPANY_CODE=600008");
        headers.add("Cookie", "yfx_c_g_u_id_10000042=_ck18061309564411034493341303617; yfx_f_l_v_t_10000042=f_t_1528855004104__r_t_1529029897551__v_t_1529045047517__r_c_1; VISITED_MENU=%5B%229062%22%2C%228470%22%2C%228482%22%2C%228765%22%2C%228763%22%2C%228525%22%2C%229057%22%2C%228528%22%2C%229056%22%2C%229055%22%5D; VISITED_COMPANY_CODE=%5B%22600000%22%2C%22600008%22%5D; VISITED_STOCK_CODE=%5B%22600000%22%2C%22600008%22%5D; seecookie=%5B600000%5D%3A%u6D66%u53D1%u94F6%u884C%2C%5B600008%5D%3A%u9996%u521B%u80A1%u4EFD; yfx_mr_10000042=%3A%3Amarket_type_free_search%3A%3A%3A%3Abaidu%3A%3A%3A%3A%3A%3A%3A%3Awww.baidu.com%3A%3A%3A%3Apmf_from_free_search; yfx_mr_f_10000042=%3A%3Amarket_type_free_search%3A%3A%3A%3Abaidu%3A%3A%3A%3A%3A%3A%3A%3Awww.baidu.com%3A%3A%3A%3Apmf_from_free_search; yfx_key_10000042=");
        //http://hq.sinajs.cn/?format=text&list=sh601688
//            ResponseEntity<String> response = restTemplate.getForEntity(new URI(stockApiProperties.getSinajsApi()), String.class);
        String url = "http://query.sse.com.cn/commonQuery.do?jsonCallBack=jsonpCallback97182&isPagination=false&sqlId=COMMON_SSE_ZQPZ_GP_GPLB_C&productid=600008&_=1529045057994";
        HttpEntity<String> requestEntity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

        System.out.println(response);
        String result = response.getBody();
        System.out.println(result.split("\n"));
    }
}