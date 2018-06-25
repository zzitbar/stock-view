package cn.coderme.stockview.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

/**
 * <p>
 * 历史交易数据
 * </p>
 *
 * @author Coderme
 * @since 2018-06-21
 */
@TableName(value = "stock_history")
public class StockHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 股票ID
     */
    @TableField("stockId")
    private Integer stockId;
    /**
     * 日期
     */
    @TableField("realDate")
    private LocalDate realDate;
    /**
     * 代码
     */
    @TableField("stockCode")
    private String stockCode;
    /**
     * 名称
     */
    @TableField("stockName")
    private String stockName;
    /**
     * 收盘价
     */
    private Double close;
    /**
     * 最高价
     */
    private Double high;
    /**
     * 最低价
     */
    private Double low;
    /**
     * 开盘价
     */
    private Double open;
    /**
     * 前收盘
     */
    @TableField("lastClose")
    private Double lastClose;
    /**
     * 涨跌额
     */
    private Double increase;
    /**
     * 涨跌幅
     */
    @TableField("increaseRate")
    private Double increaseRate;
    /**
     * 换手率
     */
    @TableField("exchangeRate")
    private Double exchangeRate;
    /**
     * 交易量(股)
     */
    @TableField("dealQty")
    private Double dealQty;
    /**
     * 交易金额(元)
     */
    @TableField("dealMoney")
    private Double dealMoney;
    /**
     * 总市值
     */
    @TableField("totalMarketCap")
    private Double totalMarketCap;
    /**
     * 流通市值
     */
    @TableField("negotiableMarketCap")
    private Double negotiableMarketCap;
    /**
     * 时间
     */
    @TableField("realTime")
    private LocalTime realTime;
    /**
     * 更新时间
     */
    @TableField("updateTime")
    private LocalDateTime updateTime;

    /**
     * 时间戳，stockChart 使用
     */
    @TableField(exist=false)
    private Long timestamp;

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

    public LocalDate getRealDate() {
        return realDate;
    }

    public void setRealDate(LocalDate realDate) {
        this.realDate = realDate;
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

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
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

    public Double getIncrease() {
        return increase;
    }

    public void setIncrease(Double increase) {
        this.increase = increase;
    }

    public Double getIncreaseRate() {
        return increaseRate;
    }

    public void setIncreaseRate(Double increaseRate) {
        this.increaseRate = increaseRate;
    }

    public Double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(Double exchangeRate) {
        this.exchangeRate = exchangeRate;
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

    public Double getTotalMarketCap() {
        return totalMarketCap;
    }

    public void setTotalMarketCap(Double totalMarketCap) {
        this.totalMarketCap = totalMarketCap;
    }

    public Double getNegotiableMarketCap() {
        return negotiableMarketCap;
    }

    public void setNegotiableMarketCap(Double negotiableMarketCap) {
        this.negotiableMarketCap = negotiableMarketCap;
    }

    public LocalTime getRealTime() {
        return realTime;
    }

    public void setRealTime(LocalTime realTime) {
        this.realTime = realTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Long getTimestamp() {
        if (null != realDate) {
            LocalDateTime dateTime = LocalDateTime.of(realDate, LocalTime.of(0,0,0));
            return dateTime.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
        }
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "StockHistory{" +
        ", id=" + id +
        ", stockId=" + stockId +
        ", realDate=" + realDate +
        ", stockCode=" + stockCode +
        ", stockName=" + stockName +
        ", close=" + close +
        ", high=" + high +
        ", low=" + low +
        ", open=" + open +
        ", lastClose=" + lastClose +
        ", increase=" + increase +
        ", increaseRate=" + increaseRate +
        ", exchangeRate=" + exchangeRate +
        ", dealQty=" + dealQty +
        ", dealMoney=" + dealMoney +
        ", totalMarketCap=" + totalMarketCap +
        ", negotiableMarketCap=" + negotiableMarketCap +
        ", realTime=" + realTime +
        ", updateTime=" + updateTime +
        "}";
    }

    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        System.out.println(now.toEpochDay());
        System.out.println(LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")));
        System.out.println(LocalDateTime.now().toInstant(ZoneOffset.ofHours(8)).toEpochMilli());
    }
}
