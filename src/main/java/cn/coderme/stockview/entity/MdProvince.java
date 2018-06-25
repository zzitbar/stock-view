package cn.coderme.stockview.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * <p>
 * 省份数据
 * </p>
 *
 * @author Coderme
 * @since 2018-06-19
 */
@TableName(value = "md_province")
public class MdProvince implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 省名称
     */
    private String name;
    /**
     * 省编码
     */
    private String adcode;
    /**
     * 城市中心点
     */
    private String center;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdcode() {
        return adcode;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    @Override
    public String toString() {
        return "MdProvince{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", adcode='" + adcode + '\'' +
                ", center='" + center + '\'' +
                '}';
    }
}
