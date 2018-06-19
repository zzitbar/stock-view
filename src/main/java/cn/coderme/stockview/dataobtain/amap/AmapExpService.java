package cn.coderme.stockview.dataobtain.amap;

import cn.coderme.stockview.base.StockApiProperties;
import cn.coderme.stockview.dto.amap.AddressGeoDto;
import cn.coderme.stockview.dto.amap.ProvinceDto;
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

    /**
     * 获取行政区划
     * @see <a href="http://lbs.amap.com/api/webservice/guide/api/district">行政区域查询</a>
     * @param keywords 只支持单个关键词语搜索关键词支持：行政区名称、citycode、adcode，例如，在subdistrict=2，搜索省份（例如山东），能够显示市（例如济南），区（例如历下区）
     * @param subdistrict 规则：设置显示下级行政区级数（行政区级别包括：国家、省/直辖市、市、区/县4个级别）可选值：0、1、2、30：不返回下级行政区；1：返回下一级行政区；2：返回下两级行政区；3：返回下三级行政区；
     * @param extensions
     * @return
     */
    public ProvinceDto provinceList(String keywords, String subdistrict, String extensions) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://restapi.amap.com/v3/config/district";
        MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("keywords", keywords);
        requestEntity.add("subdistrict", subdistrict);
        requestEntity.add("extensions", extensions);
        requestEntity.add("key", stockApiProperties.getAmapKey());
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);
        System.out.println(responseEntity);
        if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            ProvinceDto provinceDto = JSON.parseObject(responseEntity.getBody(), ProvinceDto.class);
            return provinceDto;
        } else {
            logger.error("获取行政区划出错", responseEntity);
            return null;
        }
    }
}