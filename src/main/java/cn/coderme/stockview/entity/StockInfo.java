package cn.coderme.stockview.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Coderme
 * @since 2018-06-13
 */
@TableName(value = "stock_info")
public class StockInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 代码
     */
    @TableField("stockCode")
    private String stockCode;
    /**
     * 股票名称
     */
    @TableField("stockName")
    private String stockName;
    /**
     * 市场
     */
    private String market;
    /**
     * 公司中文名
     */
    @TableField("companyNameCn")
    private String companyNameCn;
    /**
     * 公司英文名
     */
    @TableField("companyNameEn")
    private String companyNameEn;
    /**
     * 注册地址
     */
    private String address;
    /**
     * A股上市日期
     */
    @TableField("ipoDate")
    private Date ipoDate;
    /**
     * A股总股本
     */
    @TableField("totalQty")
    private Double totalQty;
    /**
     * A股流通股本
     */
    @TableField("negotiableQty")
    private Double negotiableQty;
    /**
     * 地区
     */
    private String area;
    /**
     * 省份
     */
    private String province;
    /**
     * 城市
     */
    private String city;
    /**
     * 所属行业
     */
    private String industry;
    /**
     * 公司网址
     */
    private String website;

    private String legalRepresentative;//法定代表人
    private String secretary;//董事会秘书
    private String email;//E-mail
    private String contact;//联系电话
    private String csrcIndustry;//CSRC行业(门类)
    private String csrcGreat;//CSRC行业(大类)
    private String csrcMiddle;//CSRC行业(中类)
    private String officeAddress;//通讯地址
    private String officeZip;//通讯地址邮编

    private StockRealtime stockRealtime;// 实时数据

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getCompanyNameCn() {
        return companyNameCn;
    }

    public void setCompanyNameCn(String companyNameCn) {
        this.companyNameCn = companyNameCn;
    }

    public String getCompanyNameEn() {
        return companyNameEn;
    }

    public void setCompanyNameEn(String companyNameEn) {
        this.companyNameEn = companyNameEn;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getIpoDate() {
        return ipoDate;
    }

    public void setIpoDate(Date ipoDate) {
        this.ipoDate = ipoDate;
    }

    public Double getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(Double totalQty) {
        this.totalQty = totalQty;
    }

    public Double getNegotiableQty() {
        return negotiableQty;
    }

    public void setNegotiableQty(Double negotiableQty) {
        this.negotiableQty = negotiableQty;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public StockRealtime getStockRealtime() {
        return stockRealtime;
    }

    public void setStockRealtime(StockRealtime stockRealtime) {
        this.stockRealtime = stockRealtime;
    }

    public String getLegalRepresentative() {
        return legalRepresentative;
    }

    public void setLegalRepresentative(String legalRepresentative) {
        this.legalRepresentative = legalRepresentative;
    }

    public String getSecretary() {
        return secretary;
    }

    public void setSecretary(String secretary) {
        this.secretary = secretary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCsrcIndustry() {
        return csrcIndustry;
    }

    public void setCsrcIndustry(String csrcIndustry) {
        this.csrcIndustry = csrcIndustry;
    }

    public String getCsrcGreat() {
        return csrcGreat;
    }

    public void setCsrcGreat(String csrcGreat) {
        this.csrcGreat = csrcGreat;
    }

    public String getCsrcMiddle() {
        return csrcMiddle;
    }

    public void setCsrcMiddle(String csrcMiddle) {
        this.csrcMiddle = csrcMiddle;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    public String getOfficeZip() {
        return officeZip;
    }

    public void setOfficeZip(String officeZip) {
        this.officeZip = officeZip;
    }

    @Override
    public String toString() {
        return "StockInfo{" +
        ", id=" + id +
        ", stockCode=" + stockCode +
        ", stockName=" + stockName +
        ", market=" + market +
        ", companyNameCn=" + companyNameCn +
        ", companyNameEn=" + companyNameEn +
        ", address=" + address +
        ", ipoDate=" + ipoDate +
        ", totalQty=" + totalQty +
        ", negotiableQty=" + negotiableQty +
        ", area=" + area +
        ", province=" + province +
        ", city=" + city +
        ", industry=" + industry +
        ", website=" + website +
        "}";
    }
}
