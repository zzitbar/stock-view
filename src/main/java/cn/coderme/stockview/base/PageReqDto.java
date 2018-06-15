package cn.coderme.stockview.base;

import com.baomidou.mybatisplus.plugins.Page;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created By zhangtengfei
 * Date:2018/4/19
 * Time:15:58
 */
public class PageReqDto {
    private Integer offset = 0;//偏移
    private Integer limit = 10;//页 行数

    private String sort;//排序字段名
    private String order = ASC;//排序字段顺序

    public static final String ASC = "asc";
    public static final String DESC = "desc";

    //排序map
    private Map<String, String> orderByMap = new LinkedHashMap<String, String>(4);

    private Page page = new Page();

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public PageReqDto pageable() {
        if (getOrderByMap().size()>0) {
            List<String> ascs = new ArrayList<>();
            List<String> descs = new ArrayList<>();
            for (Map.Entry<String, String> entry : getOrderByMap().entrySet()) {
                if (ASC.equalsIgnoreCase(entry.getValue())) {
                    ascs.add(entry.getKey());
                } else if (DESC.equalsIgnoreCase(entry.getValue())) {
                    descs.add(entry.getKey());
                }
            }
            page.setAscs(ascs);
            page.setDescs(descs);
        }
        page.setSize(limit).setCurrent(offset/limit+1);
        return this;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Map<String, String> getOrderByMap() {
        if (null != sort && "" != sort) {
            String[] sorts = sort.split(",");
            String[] orders = new String[sorts.length];
            if (null != order && "" != order) {
                orders = order.split(",");
            }
            for (int i = 0; i<sorts.length; i++) {
                orderByMap.put(sorts[i], orders[i]);
            }
            sort = null;
        }
        return orderByMap;
    }

    public void setOrderByMap(Map<String, String> orderByMap) {
        this.orderByMap = orderByMap;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}