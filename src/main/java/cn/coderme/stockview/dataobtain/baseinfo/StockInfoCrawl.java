package cn.coderme.stockview.dataobtain.baseinfo;

import cn.coderme.stockview.base.StockApiProperties;
import cn.coderme.stockview.dataobtain.baseinfo.dto.StockInfoCrawlDto;
import cn.coderme.stockview.entity.StockInfo;
import cn.coderme.stockview.service.StockInfoService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    private static final Logger logger = LoggerFactory.getLogger(StockInfoCrawl.class);

    private static final String SSE_COOKIE = "yfx_c_g_u_id_10000042=_ck18061309564411034493341303617; yfx_f_l_v_t_10000042=f_t_1528855004104__r_t_1529029897551__v_t_1529045047517__r_c_1;yfx_mr_10000042=::market_type_free_search::::baidu::::::::www.baidu.com::::pmf_from_free_search; yfx_mr_f_10000042=::market_type_free_search::::baidu::::::::www.baidu.com::::pmf_from_free_search; yfx_key_10000042=";
    private static final String SSE_REFERER = "http://www.sse.com.cn/assortment/stock/list/info/company/index.shtml?COMPANY_CODE=600008";

    @Autowired
    private StockApiProperties stockApiProperties;
    @Autowired
    private StockInfoService stockInfoService;

    /**
     * 爬取股票基本信息
     * @param stockCode
     */
    @Transactional
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
                doCrawlSseStockInfo(stockInfo);
                stockInfoService.updateById(stockInfo);
            } catch (Exception e) {
                logger.error("抓取信息出错"+stockInfo.getStockCode(), e);
            }
        }
    }

    /**
     * 上交所股票基本信息爬取
     * @param stockInfo
     */
    private void doCrawlSseStockInfo(StockInfo stockInfo) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("Referer", SSE_REFERER);
        headers.add("Cookie", SSE_COOKIE);
        String url = stockApiProperties.getSseStockInfoApi();
        HttpEntity<String> requestEntity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class, stockInfo.getStockCode());
        if (response.getStatusCode().equals(HttpStatus.OK)) {
            StockInfoCrawlDto dto = JSON.parseObject(response.getBody(), StockInfoCrawlDto.class);
            List<StockInfoCrawlDto.ResultBean> resultBeans = dto.getResult();
            if (null != resultBeans && resultBeans.size()>=1) {
                StockInfoCrawlDto.ResultBean rb = resultBeans.get(0);
                stockInfo.setCompanyNameCn(rb.getFULLNAME());
                stockInfo.setCompanyNameEn(rb.getFULL_NAME_IN_ENGLISH());
                stockInfo.setAddress(rb.getCOMPANY_ADDRESS());
                stockInfo.setProvince(rb.getAREA_NAME_DESC());
                stockInfo.setIndustry(rb.getSSE_CODE_DESC());
                stockInfo.setWebsite(rb.getWWW_ADDRESS());
                stockInfo.setLegalRepresentative(rb.getLEGAL_REPRESENTATIVE());
                stockInfo.setEmail(rb.getE_MAIL_ADDRESS());
                stockInfo.setContact(rb.getREPR_PHONE());
                stockInfo.setCsrcIndustry(rb.getCSRC_CODE_DESC());
                stockInfo.setCsrcGreat(rb.getCSRC_GREAT_CODE_DESC());
                stockInfo.setCsrcMiddle(rb.getCSRC_MIDDLE_CODE_DESC());
                stockInfo.setOfficeAddress(rb.getOFFICE_ADDRESS());
                stockInfo.setOfficeZip(rb.getOFFICE_ZIP());
            }
        }
    }

    public String parseJSONP(String jsonp){
        int startIndex = jsonp.indexOf("(");
        int endIndex = jsonp.lastIndexOf(")");
        String json = jsonp.substring(startIndex+1, endIndex);
        return json;
    }

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("Referer", SSE_REFERER);
//        headers.add("Cookie", SSE_COOKIE);
        //http://hq.sinajs.cn/?format=text&list=sh601688
//            ResponseEntity<String> response = restTemplate.getForEntity(new URI(stockApiProperties.getSinajsApi()), String.class);
        String url = "http://query.sse.com.cn/commonQuery.do?isPagination=false&sqlId=COMMON_SSE_ZQPZ_GP_GPLB_C&productid=600681&_=1529045057994";
        HttpEntity<String> requestEntity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

        System.out.println(response);
        String result = response.getBody();
        System.out.println(result);
        Object o = JSON.parse(result);
        JSONObject jo = JSON.parseObject(result);
        StockInfoCrawlDto si = JSON.parseObject(result, StockInfoCrawlDto.class);
    }
}