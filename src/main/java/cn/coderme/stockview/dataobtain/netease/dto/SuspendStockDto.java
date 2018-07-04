package cn.coderme.stockview.dataobtain.netease.dto;

import java.util.List;

/**
 * 停复牌股票数据DTO
 * Created By Administrator
 * Date:2018/6/26
 * Time:12:43
 */
public class SuspendStockDto {

    /**
     * page : 0
     * count : 300
     * order : -1
     * total : 222
     * pagecount : 1
     * time : 2018-06-26 12:40:31
     * key : /finance/hs/marketdata/tpts/525bccea535f1afe579157b48e3406a6.json
     * list : [{"NO":"1","SNAME":"富临运业","EXCHANGE":"CNSESZ","SYMBOL":"002357","NTRAD4":"2018-06-11","NTRAD5":"2018-06-25","NTRAD7":"2018-06-26","NTRAD1":"未刊登重要事项","NTRAD6":"    四川富临运业集团股份有限公司控股股东拟筹划公司控制权变更事项，根据本所《股票上市规则》和《中小企业板上市公司规范运作指引》的有关规定，经公司申请，公司股票（证券简称：富临运业，证券代码：002357）于2018年6月11日开市起临时停牌，待公司通过指定媒体披露相关公告后复牌，敬请投资者密切关注。","CODE":"1002357","TYPE":"今日复牌","NAME":""}]
     */

    private Integer page;//当前页，从0开始
    private Integer count;//每页条数
    private String order;//排序
    private String total;//总数
    private Integer pagecount;//总页数
    private String time;//时间
    private String key;//
    private List<ListBean> list;//停复牌股票列表

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Integer getPagecount() {
        return pagecount;
    }

    public void setPagecount(Integer pagecount) {
        this.pagecount = pagecount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * NO : 1
         * SNAME : 富临运业
         * EXCHANGE : CNSESZ
         * SYMBOL : 002357
         * NTRAD4 : 2018-06-11
         * NTRAD5 : 2018-06-25
         * NTRAD7 : 2018-06-26
         * NTRAD1 : 未刊登重要事项
         * NTRAD6 :     四川富临运业集团股份有限公司控股股东拟筹划公司控制权变更事项，根据本所《股票上市规则》和《中小企业板上市公司规范运作指引》的有关规定，经公司申请，公司股票（证券简称：富临运业，证券代码：002357）于2018年6月11日开市起临时停牌，待公司通过指定媒体披露相关公告后复牌，敬请投资者密切关注。
         * CODE : 1002357
         * TYPE : 今日复牌
         * NAME :
         */

        private String NO;//序号
        private String SNAME;//股票名
        private String EXCHANGE;//交易所 CNSESH：上海，CNSESZ：深圳
        private String SYMBOL;//股票代码
        private String NTRAD4;//停牌日期
        private String NTRAD5;//复牌日期
        private String NTRAD7;//
        private String NTRAD1;//停牌原因
        private String NTRAD6;//停牌原因描述
        private String CODE;//打码
        private String TYPE;//类型，今日复牌或今日停牌
        private String NAME;//

        public String getNO() {
            return NO;
        }

        public void setNO(String NO) {
            this.NO = NO;
        }

        public String getSNAME() {
            return SNAME;
        }

        public void setSNAME(String SNAME) {
            this.SNAME = SNAME;
        }

        public String getEXCHANGE() {
            return EXCHANGE;
        }

        public void setEXCHANGE(String EXCHANGE) {
            this.EXCHANGE = EXCHANGE;
        }

        public String getSYMBOL() {
            return SYMBOL;
        }

        public void setSYMBOL(String SYMBOL) {
            this.SYMBOL = SYMBOL;
        }

        public String getNTRAD4() {
            return NTRAD4;
        }

        public void setNTRAD4(String NTRAD4) {
            this.NTRAD4 = NTRAD4;
        }

        public String getNTRAD5() {
            return NTRAD5;
        }

        public void setNTRAD5(String NTRAD5) {
            this.NTRAD5 = NTRAD5;
        }

        public String getNTRAD7() {
            return NTRAD7;
        }

        public void setNTRAD7(String NTRAD7) {
            this.NTRAD7 = NTRAD7;
        }

        public String getNTRAD1() {
            return NTRAD1;
        }

        public void setNTRAD1(String NTRAD1) {
            this.NTRAD1 = NTRAD1;
        }

        public String getNTRAD6() {
            return NTRAD6;
        }

        public void setNTRAD6(String NTRAD6) {
            this.NTRAD6 = NTRAD6;
        }

        public String getCODE() {
            return CODE;
        }

        public void setCODE(String CODE) {
            this.CODE = CODE;
        }

        public String getTYPE() {
            return TYPE;
        }

        public void setTYPE(String TYPE) {
            this.TYPE = TYPE;
        }

        public String getNAME() {
            return NAME;
        }

        public void setNAME(String NAME) {
            this.NAME = NAME;
        }
    }
}