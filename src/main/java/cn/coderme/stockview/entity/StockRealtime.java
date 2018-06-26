package cn.coderme.stockview.entity;

import cn.coderme.stockview.Constants;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import java.io.Serializable;

/**
 * <p>
 * 实时数据
 * </p>
 *
 * @author Coderme
 * @since 2018-06-13
 */
@TableName(value = "stock_realtime")
public class StockRealtime implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 股票ID
     */
    @TableField("stockId")
    private Integer stockId;
    /**
     * 代码
     */
    @TableField("stockCode")
    private String stockCode;
    /**
     * 日期
     */
    @TableField("realDate")
    private LocalDate realDate;
    /**
     * 时间
     */
    @TableField("realTime")
    private LocalTime realTime;
    /**
     * 今日开盘价
     */
    private Double open;
    /**
     * 昨日收盘价
     */
    @TableField("lastClose")
    private Double lastClose;
    /**
     * 当前价格
     */
    @TableField("currentPrice")
    private Double currentPrice;
    /**
     * 今日最高价
     */
    private Double high;
    /**
     * 今日最低价
     */
    private Double low;
    /**
     * 竞买价，即“买一”报价
     */
    @TableField("bidPrice")
    private Double bidPrice;
    /**
     * 竞卖价，即“卖一”报价
     */
    @TableField("auctionPrice")
    private Double auctionPrice;
    /**
     * 成交的股票数（单位为“股”）
     */
    @TableField("dealQty")
    private Double dealQty;
    /**
     * 成交金额（单位为“元”）
     */
    @TableField("dealMoney")
    private Double dealMoney;
    /**
     * “买一”申请
     */
    @TableField("buy1Qty")
    private Double buy1Qty;
    /**
     * “买一”报价
     */
    @TableField("buy1Price")
    private Double buy1Price;
    /**
     * “买二”申请
     */
    @TableField("buy2Qty")
    private Double buy2Qty;
    /**
     * “买二”报价
     */
    @TableField("buy2Price")
    private Double buy2Price;
    /**
     * “买三”申请
     */
    @TableField("buy3Qty")
    private Double buy3Qty;
    /**
     * “买三”报价
     */
    @TableField("buy3Price")
    private Double buy3Price;
    /**
     * “买四”申请
     */
    @TableField("buy4Qty")
    private Double buy4Qty;
    /**
     * “买四”报价
     */
    @TableField("buy4Price")
    private Double buy4Price;
    /**
     * “买五”申请
     */
    @TableField("buy5Qty")
    private Double buy5Qty;
    /**
     * “买五”报价
     */
    @TableField("buy5Price")
    private Double buy5Price;
    /**
     * “卖一”申请
     */
    @TableField("sell1Qty")
    private Double sell1Qty;
    /**
     * “卖一”报价
     */
    @TableField("sell1Price")
    private Double sell1Price;
    @TableField("sell2Qty")
    private Double sell2Qty;
    @TableField("sell2Price")
    private Double sell2Price;
    @TableField("sell3Qty")
    private Double sell3Qty;
    @TableField("sell3Price")
    private Double sell3Price;
    @TableField("sell4Qty")
    private Double sell4Qty;
    @TableField("sell4Price")
    private Double sell4Price;
    @TableField("sell5Qty")
    private Double sell5Qty;
    @TableField("sell5Price")
    private Double sell5Price;
    /**
     * 更新时间
     */
    @TableField("updateTime")
    private Date updateTime;
    @TableField("increaseRate")
    private Double increaseRate;//涨幅

    private Integer status = Constants.STOCK_STATUS.TRADE.getValue(); //状态 1:交易，2:停牌

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStockId() {
        return stockId;
    }

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public LocalDate getRealDate() {
        return realDate;
    }

    public void setRealDate(LocalDate realDate) {
        this.realDate = realDate;
    }

    public LocalTime getRealTime() {
        return realTime;
    }

    public void setRealTime(LocalTime realTime) {
        this.realTime = realTime;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Double getLastClose() {
        return lastClose;
    }

    public void setLastClose(Double lastClose) {
        this.lastClose = lastClose;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(Double bidPrice) {
        this.bidPrice = bidPrice;
    }

    public Double getAuctionPrice() {
        return auctionPrice;
    }

    public void setAuctionPrice(Double auctionPrice) {
        this.auctionPrice = auctionPrice;
    }

    public Double getDealQty() {
        return dealQty;
    }

    public void setDealQty(Double dealQty) {
        this.dealQty = dealQty;
    }

    public Double getDealMoney() {
        return dealMoney;
    }

    public void setDealMoney(Double dealMoney) {
        this.dealMoney = dealMoney;
    }

    public Double getBuy1Qty() {
        return buy1Qty;
    }

    public void setBuy1Qty(Double buy1Qty) {
        this.buy1Qty = buy1Qty;
    }

    public Double getBuy1Price() {
        return buy1Price;
    }

    public void setBuy1Price(Double buy1Price) {
        this.buy1Price = buy1Price;
    }

    public Double getBuy2Qty() {
        return buy2Qty;
    }

    public void setBuy2Qty(Double buy2Qty) {
        this.buy2Qty = buy2Qty;
    }

    public Double getBuy2Price() {
        return buy2Price;
    }

    public void setBuy2Price(Double buy2Price) {
        this.buy2Price = buy2Price;
    }

    public Double getBuy3Qty() {
        return buy3Qty;
    }

    public void setBuy3Qty(Double buy3Qty) {
        this.buy3Qty = buy3Qty;
    }

    public Double getBuy3Price() {
        return buy3Price;
    }

    public void setBuy3Price(Double buy3Price) {
        this.buy3Price = buy3Price;
    }

    public Double getBuy4Qty() {
        return buy4Qty;
    }

    public void setBuy4Qty(Double buy4Qty) {
        this.buy4Qty = buy4Qty;
    }

    public Double getBuy4Price() {
        return buy4Price;
    }

    public void setBuy4Price(Double buy4Price) {
        this.buy4Price = buy4Price;
    }

    public Double getBuy5Qty() {
        return buy5Qty;
    }

    public void setBuy5Qty(Double buy5Qty) {
        this.buy5Qty = buy5Qty;
    }

    public Double getBuy5Price() {
        return buy5Price;
    }

    public void setBuy5Price(Double buy5Price) {
        this.buy5Price = buy5Price;
    }

    public Double getSell1Qty() {
        return sell1Qty;
    }

    public void setSell1Qty(Double sell1Qty) {
        this.sell1Qty = sell1Qty;
    }

    public Double getSell1Price() {
        return sell1Price;
    }

    public void setSell1Price(Double sell1Price) {
        this.sell1Price = sell1Price;
    }

    public Double getSell2Qty() {
        return sell2Qty;
    }

    public void setSell2Qty(Double sell2Qty) {
        this.sell2Qty = sell2Qty;
    }

    public Double getSell2Price() {
        return sell2Price;
    }

    public void setSell2Price(Double sell2Price) {
        this.sell2Price = sell2Price;
    }

    public Double getSell3Qty() {
        return sell3Qty;
    }

    public void setSell3Qty(Double sell3Qty) {
        this.sell3Qty = sell3Qty;
    }

    public Double getSell3Price() {
        return sell3Price;
    }

    public void setSell3Price(Double sell3Price) {
        this.sell3Price = sell3Price;
    }

    public Double getSell4Qty() {
        return sell4Qty;
    }

    public void setSell4Qty(Double sell4Qty) {
        this.sell4Qty = sell4Qty;
    }

    public Double getSell4Price() {
        return sell4Price;
    }

    public void setSell4Price(Double sell4Price) {
        this.sell4Price = sell4Price;
    }

    public Double getSell5Qty() {
        return sell5Qty;
    }

    public void setSell5Qty(Double sell5Qty) {
        this.sell5Qty = sell5Qty;
    }

    public Double getSell5Price() {
        return sell5Price;
    }

    public void setSell5Price(Double sell5Price) {
        this.sell5Price = sell5Price;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Double getIncreaseRate() {
        return increaseRate;
    }

    public void setIncreaseRate(Double increaseRate) {
        this.increaseRate = increaseRate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "StockRealtime{" +
        ", id=" + id +
        ", stockId=" + stockId +
        ", stockCode=" + stockCode +
        ", realDate=" + realDate +
        ", realTime=" + realTime +
        ", open=" + open +
        ", lastClose=" + lastClose +
        ", high=" + high +
        ", low=" + low +
        ", bidPrice=" + bidPrice +
        ", auctionPrice=" + auctionPrice +
        ", dealQty=" + dealQty +
        ", dealMoney=" + dealMoney +
        ", buy1Qty=" + buy1Qty +
        ", buy1Price=" + buy1Price +
        ", buy2Qty=" + buy2Qty +
        ", buy2Price=" + buy2Price +
        ", buy3Qty=" + buy3Qty +
        ", buy3Price=" + buy3Price +
        ", buy4Qty=" + buy4Qty +
        ", buy4Price=" + buy4Price +
        ", buy5Qty=" + buy5Qty +
        ", buy5Price=" + buy5Price +
        ", sell1Qty=" + sell1Qty +
        ", sell1Price=" + sell1Price +
        ", sell2Qty=" + sell2Qty +
        ", sell2Price=" + sell2Price +
        ", sell3Qty=" + sell3Qty +
        ", sell3Price=" + sell3Price +
        ", sell4Qty=" + sell4Qty +
        ", sell4Price=" + sell4Price +
        ", sell5Qty=" + sell5Qty +
        ", sell5Price=" + sell5Price +
        ", updateTime=" + updateTime +
        ", increaseRate=" + increaseRate +
        "}";
    }
}
