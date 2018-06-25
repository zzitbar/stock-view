package cn.coderme.stockview;

import cn.coderme.stockview.dataobtain.amap.AmapExpService;
import cn.coderme.stockview.dto.amap.AddressGeoDto;
import cn.coderme.stockview.dto.amap.ProvinceDto;
import cn.coderme.stockview.dto.amap.base.AmapBaseDto;
import cn.coderme.stockview.entity.MdProvince;
import cn.coderme.stockview.entity.StockInfo;
import cn.coderme.stockview.service.MdProvinceService;
import cn.coderme.stockview.service.StockInfoService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * 根据地址解析省市区和坐标
 * Created By Administrator
 * Date:2018/6/19
 * Time:10:54
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AmapTest {

    @Autowired
    private AmapExpService amapExpService;
    @Autowired
    private StockInfoService stockInfoService;
    @Autowired
    private MdProvinceService mdProvinceService;

    @Test
    public void adderssResolve() {
        EntityWrapper<StockInfo> ew = new EntityWrapper<>();
        ew.setSqlSelect("id, address");//只查询2个字段
//        ew.eq("stockCode", "600000");
        ew.isNull("location");
        List<StockInfo> stockInfos = stockInfoService.selectList(ew);
        for (StockInfo si : stockInfos) {
            AddressGeoDto dto = amapExpService.adderssResolve(si.getAddress(), "");
            if (null != dto.getGeocodes() && dto.getGeocodes().size()>0) {
                AddressGeoDto.GeocodesBean geocodesBean = dto.getGeocodes().get(0);
                if (null != geocodesBean.getProvince()) {
                    si.setProvince(geocodesBean.getProvince());
                }
                if (null != geocodesBean.getCity()) {
                    si.setCity(geocodesBean.getCity());
                }
                if (null != geocodesBean.getDistrict()) {
                    si.setDistrict(geocodesBean.getDistrict());
                }
                if (null != geocodesBean.getLocation()) {
                    si.setLocation(geocodesBean.getLocation());
                }
                stockInfoService.updateById(si);
            }
        }
    }

    @Test
    public void provinceGenerate() {
        ProvinceDto dto = amapExpService.provinceList("", "1", "");
        if (AmapBaseDto.STATUS_SUCCESS.equals(dto.getStatus()) && null != dto.getDistricts() && dto.getDistricts().size()>0) {
            for (ProvinceDto.DistrictsBeanX districtsBeanX : dto.getDistricts().get(0).getDistricts()) {
                MdProvince province = new MdProvince();
                province.setName(districtsBeanX.getName());
                province.setAdcode(districtsBeanX.getAdcode());
                province.setCenter(districtsBeanX.getCenter());
                mdProvinceService.insert(province);
            }
        }
    }

}