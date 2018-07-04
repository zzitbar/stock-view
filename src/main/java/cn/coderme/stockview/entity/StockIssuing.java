package cn.coderme.stockview.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import java.io.Serializable;

/**
 * <p>
 * 新股发行记录
 * </p>
 *
 * @author Coderme
 * @since 2018-06-26
 */
@TableName(value = "stock_issuing")
public class StockIssuing implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("stockId")
    private Integer stockId;
    @TableField("stockCode")
    private String stockCode;
    @TableField("stockName")
    private String stockName;
    /**
     * 网上发行日
     */
    @TableField("issueDate")
    private LocalDate issueDate;
    /**
     * 上市日
     */
    @TableField("ipoDate")
    private LocalDate ipoDate;
    /**
     * 发行量(万股)
     */
    @TableField("issueQty")
    private Double issueQty;
    /**
     * 网上发行量(万股)
     */
    @TableField("issueWebQty")
    private Double issueWebQty;
    /**
     * 申购上限(万股)
     */
    @TableField("purchaseLimit")
    private Double purchaseLimit;
    /**
     * 发行价(元)
     */
    @TableField("issuePrice")
    private Double issuePrice;
    /**
     * 发行市盈率
     */
    @TableField("peRatio")
    private Double peRatio;
    /**
     * 中签率
     */
    @TableField("signingRate")
    private String signingRate;
    /**
     * 中签号
     */
    @TableField("signingNum")
    private String signingNum;
    @TableField("updateTime")
    private LocalDateTime updateTime = LocalDateTime.now();

    public StockIssuing() {
    }

    public StockIssuing(Integer stockId, String stockCode, String stockName, LocalDate issueDate, LocalDate ipoDate,
                        Double issueQty, Double issueWebQty, Double purchaseLimit,
                        Double issuePrice, Double peRatio, String signingRate, String signingNum) {
        this.stockId = stockId;
        this.stockCode = stockCode;
        this.stockName = stockName;
        this.issueDate = issueDate;
        this.ipoDate = ipoDate;
        this.issueQty = issueQty;
        this.issueWebQty = issueWebQty;
        this.purchaseLimit = purchaseLimit;
        this.issuePrice = issuePrice;
        this.peRatio = peRatio;
        this.signingRate = signingRate;
        this.signingNum = signingNum;
    }

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

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getIpoDate() {
        return ipoDate;
    }

    public void setIpoDate(LocalDate ipoDate) {
        this.ipoDate = ipoDate;
    }

    public Double getIssueQty() {
        return issueQty;
    }

    public void setIssueQty(Double issueQty) {
        this.issueQty = issueQty;
    }

    public Double getIssueWebQty() {
        return issueWebQty;
    }

    public void setIssueWebQty(Double issueWebQty) {
        this.issueWebQty = issueWebQty;
    }

    public Double getPurchaseLimit() {
        return purchaseLimit;
    }

    public void setPurchaseLimit(Double purchaseLimit) {
        this.purchaseLimit = purchaseLimit;
    }

    public Double getIssuePrice() {
        return issuePrice;
    }

    public void setIssuePrice(Double issuePrice) {
        this.issuePrice = issuePrice;
    }

    public Double getPeRatio() {
        return peRatio;
    }

    public void setPeRatio(Double peRatio) {
        this.peRatio = peRatio;
    }

    public String getSigningRate() {
        return signingRate;
    }

    public void setSigningRate(String signingRate) {
        this.signingRate = signingRate;
    }

    public String getSigningNum() {
        return signingNum;
    }

    public void setSigningNum(String signingNum) {
        this.signingNum = signingNum;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "StockIssuing{" +
        ", id=" + id +
        ", stockId=" + stockId +
        ", stockCode=" + stockCode +
        ", stockName=" + stockName +
        ", issueDate=" + issueDate +
        ", ipoDate=" + ipoDate +
        ", issueQty=" + issueQty +
        ", issueWebQty=" + issueWebQty +
        ", purchaseLimit=" + purchaseLimit +
        ", issuePrice=" + issuePrice +
        ", peRatio=" + peRatio +
        ", signingRate=" + signingRate +
        ", signingNum=" + signingNum +
        ", updateTime=" + updateTime +
        "}";
    }
}
