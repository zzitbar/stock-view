package cn.coderme.stockview.dataobtain.eastmoney.dto;

import java.util.List;

/**
 * Created By Administrator
 * Date:2018/7/3
 * Time:16:55
 */
public class StockSuspendDto {


    /**
     * pages : 4
     * data : ["300198,纳川股份,2018-02-02 09:30,2018-08-02 15:00,连续停牌,拟筹划重大资产重组,创业板,2018-02-02,2018-08-03"]
     */

    private Integer pages;
    private List<String> data;

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}