/**
 * 
 */
package cn.coderme.stockview.dto.hchart;

import java.io.Serializable;

/**
 * highchart 图表数据DTO
 * @author coderme
 */
public class ChartDataDto implements Serializable {

	private static final long serialVersionUID = 3544348758880772179L;

	private String name;
	private Object[] data;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object[] getData() {
		return data;
	}
	public void setData(Object[] data) {
		this.data = data;
	}
}
