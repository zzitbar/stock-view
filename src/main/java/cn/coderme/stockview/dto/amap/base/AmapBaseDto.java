package cn.coderme.stockview.dto.amap.base;

/**
 * Created By Administrator
 * Date:2018/6/19
 * Time:10:33
 */
public class AmapBaseDto {

    public static final String STATUS_SUCCESS = "1";
    public static final String INFO_SUCCESS = "OK";

    private String status;//返回结果状态值,返回值为 0 或 1，0 表示请求失败；1 表示请求成功。
    private String info;//返回状态说明，当 status 为 0 时，info 会返回具体错误原因，否则返回“OK”。详情可以参阅info状态表
    private String infocode;//错误码,http://lbs.amap.com/api/webservice/guide/tools/info

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfocode() {
        return infocode;
    }

    public void setInfocode(String infocode) {
        this.infocode = infocode;
    }
}