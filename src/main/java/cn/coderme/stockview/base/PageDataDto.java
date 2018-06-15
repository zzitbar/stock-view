package cn.coderme.stockview.base;

import com.baomidou.mybatisplus.plugins.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * bootstrap-table 分页查询结果DTO
 * Created By Administrator
 * Date:2018/6/14
 * Time:11:26
 */
public class PageDataDto<T> {

    private int total = 0;

    private List<T> rows = new ArrayList<T>();

    public PageDataDto() {
    }

    public PageDataDto(Page<T> page) {
        this.rows = page.getRecords();
        this.total = page.getTotal();
    }


    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

}