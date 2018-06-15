package cn.coderme.stockview.base;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created By Administrator
 * Date:2018/6/14
 * Time:13:53
 */
@Configuration
@PropertySource("classpath:stock_api.properties")
public class StockApiProperties {

    @Value("${sinajs_api}")
    private String sinajsApi;//新浪js API
    @Value("${sse_stock_info_api}")
    private String sseStockInfoApi;//上交所股票基本信息页面

    public String getSinajsApi() {
        return sinajsApi;
    }

    public void setSinajsApi(String sinajsApi) {
        this.sinajsApi = sinajsApi;
    }

    public String getSseStockInfoApi() {
        return sseStockInfoApi;
    }

    public void setSseStockInfoApi(String sseStockInfoApi) {
        this.sseStockInfoApi = sseStockInfoApi;
    }
}