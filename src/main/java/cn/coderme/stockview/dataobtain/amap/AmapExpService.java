package cn.coderme.stockview.dataobtain.amap;

import cn.coderme.stockview.base.StockApiProperties;
import cn.coderme.stockview.dto.amap.AddressGeoDto;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.text.MessageFormat;

/**
 * 调用高德地图接口
 * Created By Administrator
 * Date:2018/6/19
 * Time:10:37
 */
@Service
public class AmapExpService {

    private static final Logger logger = LoggerFactory.getLogger(AmapExpService.class);

    @Autowired
    private StockApiProperties stockApiProperties;

    /**
     * 根据给定地图解析得到相关省市区和坐标
     * @param address
     * @param city
     * @return
     */
    public AddressGeoDto adderssResolve(String address, String city) {
        RestTemplate restTemplate = new RestTemplate();
        String url = stockApiProperties.getAmapAddressResolveApi();
        MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("address", address);
        requestEntity.add("city", city);
        requestEntity.add("key", stockApiProperties.getAmapKey());
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);
        System.out.println(responseEntity);
        if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            AddressGeoDto geoDto = JSON.parseObject(responseEntity.getBody(), AddressGeoDto.class);
            return geoDto;
        } else {
            logger.error("调用地址解析出错", responseEntity);
            return null;
        }
    }
}