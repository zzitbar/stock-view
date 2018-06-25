package cn.coderme.stockview;

/**
 * Created By Administrator
 * Date:2018/6/20
 * Time:11:17
 */
public class Constants {

    public enum CHART_TYPE {
        LINE("line"), COLUMN("column"), PIE("pie"), BAR("bar");
        private String value;

        CHART_TYPE(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
        public void setValue(String value) {
            this.value = value;
        }
    }

    /**
     * 类型
     * 1：股票
     * 2：股票指数
     */
    public enum STOCK_TYPE {
        STOCK(1), STOCK_INDEX(2);

        private Integer value;

        STOCK_TYPE(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }
    }

}