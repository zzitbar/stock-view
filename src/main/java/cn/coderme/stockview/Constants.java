package cn.coderme.stockview;

/**
 * Created By Administrator
 * Date:2018/6/20
 * Time:11:17
 */
public class Constants {

    public static final String LIVE_MSG_TOPIC = "/topic/liveMessage";

    public static final Integer THREAD_DEAL_SIZE = 500;

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

    /**
     * 状态
     * 1:交易
     * 2:停牌
     */
    public enum STOCK_STATUS {
        TRADE(1), SUSPEND(2);

        private Integer value;

        STOCK_STATUS(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }
    }

    /**
     * 交易市场
     */
    public enum STOCK_EXCHANGE {
        SH("sh"), SZ("sz");
        private String value;

        STOCK_EXCHANGE(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}