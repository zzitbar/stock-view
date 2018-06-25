package cn.coderme.stockview.dto.hchart;

import java.io.Serializable;

/**
 * Created By coderme
 */
public class CountDto implements Serializable {

    private String item;
    private Integer count;

    public CountDto() {
    }

    public CountDto(String item, Integer count) {
        this.item = item;
        this.count = count;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
