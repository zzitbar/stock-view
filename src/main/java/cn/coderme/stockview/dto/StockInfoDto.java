package cn.coderme.stockview.dto;

import cn.coderme.stockview.Constants;
import cn.coderme.stockview.base.PageReqDto;

/**
 * Created By Administrator
 * Date:2018/6/15
 * Time:10:56
 */
public class StockInfoDto extends PageReqDto {

    private String market;//市场
    private String stockCode;//代码
    private String stockName;//名称
    private Integer stockType = Constants.STOCK_TYPE.STOCK.getValue();//类型 1：股票，2：股票指数

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public Integer getStockType() {
        return stockType;
    }

    public void setStockType(Integer stockType) {
        this.stockType = stockType;
    }
}