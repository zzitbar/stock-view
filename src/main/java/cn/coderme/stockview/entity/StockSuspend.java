package cn.coderme.stockview.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author Coderme
 * @since 2018-06-26
 */
@TableName(value = "stock_suspend")
public class StockSuspend implements Serializable {

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
     * 停牌日期
     */
    @TableField("suspendDate")
    private LocalDate suspendDate;
    /**
     * 复牌日期
     */
    @TableField("reopenDate")
    private LocalDate reopenDate;
    /**
     * 停牌原因
     */
    @TableField("suspendTitle")
    private String suspendTitle;
    /**
     * 停牌原因描述
     */
    @TableField("suspendDesc")
    private String suspendDesc;
    /**
     * 状态
     */
    private String type;
    /**
     * 更新时间
     */
    @TableField("updateTime")
    private LocalDateTime updateTime = LocalDateTime.now();

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

    public LocalDate getSuspendDate() {
        return suspendDate;
    }

    public void setSuspendDate(LocalDate suspendDate) {
        this.suspendDate = suspendDate;
    }

    public LocalDate getReopenDate() {
        return reopenDate;
    }

    public void setReopenDate(LocalDate reopenDate) {
        this.reopenDate = reopenDate;
    }

    public String getSuspendTitle() {
        return suspendTitle;
    }

    public void setSuspendTitle(String suspendTitle) {
        this.suspendTitle = suspendTitle;
    }

    public String getSuspendDesc() {
        return suspendDesc;
    }

    public void setSuspendDesc(String suspendDesc) {
        this.suspendDesc = suspendDesc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "StockSuspend{" +
        ", int=" + id +
        ", stockId=" + stockId +
        ", stockCode=" + stockCode +
        ", stockName=" + stockName +
        ", suspendDate=" + suspendDate +
        ", reopenDate=" + reopenDate +
        ", suspendTitle=" + suspendTitle +
        ", suspendDesc=" + suspendDesc +
        ", type=" + type +
        ", updateTime=" + updateTime +
        "}";
    }
}
