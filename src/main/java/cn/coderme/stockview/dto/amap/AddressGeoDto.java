package cn.coderme.stockview.dto.amap;

import cn.coderme.stockview.dto.amap.base.AmapBaseDto;

import java.util.List;

/**
 * 给定地址信息，解析所在省市区和坐标
 * Created By Administrator
 * Date:2018/6/19
 * Time:10:32
 */
public class AddressGeoDto extends AmapBaseDto {

    /**
     * status : 1
     * info : OK
     * infocode : 10000
     * count : 1
     * geocodes : [{"formatted_address":"河南省郑州市金水区民生路|1号","province":"河南省","citycode":"0371","city":"郑州市","district":"金水区","adcode":"410105","street":"民生路","number":"1号","location":"113.748497,34.770522","level":"门牌号"}]
     */
    private String count;
    private List<GeocodesBean> geocodes;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<GeocodesBean> getGeocodes() {
        return geocodes;
    }

    public void setGeocodes(List<GeocodesBean> geocodes) {
        this.geocodes = geocodes;
    }

    @Override
    public String toString() {
        return "AddressGeoDto{" +
                "count='" + count + '\'' +
                ", geocodes=" + geocodes +
                '}';
    }

    public static class GeocodesBean {
        /**
         * formatted_address : 河南省郑州市金水区民生路|1号
         * province : 河南省
         * citycode : 0371
         * city : 郑州市
         * district : 金水区
         * adcode : 410105
         * street : 民生路
         * number : 1号
         * location : 113.748497,34.770522
         * level : 门牌号
         */

        private String formatted_address;
        private String province;
        private String citycode;
        private String city;
        private String district;
        private String adcode;
        private String street;
        private String number;
        private String location;
        private String level;

        public String getFormatted_address() {
            return formatted_address;
        }

        public void setFormatted_address(String formatted_address) {
            this.formatted_address = formatted_address;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCitycode() {
            return citycode;
        }

        public void setCitycode(String citycode) {
            this.citycode = citycode;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getAdcode() {
            return adcode;
        }

        public void setAdcode(String adcode) {
            this.adcode = adcode;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        @Override
        public String toString() {
            return "GeocodesBean{" +
                    "formatted_address='" + formatted_address + '\'' +
                    ", province='" + province + '\'' +
                    ", citycode='" + citycode + '\'' +
                    ", city='" + city + '\'' +
                    ", district='" + district + '\'' +
                    ", adcode='" + adcode + '\'' +
                    ", street='" + street + '\'' +
                    ", number='" + number + '\'' +
                    ", location='" + location + '\'' +
                    ", level='" + level + '\'' +
                    '}';
        }
    }
}