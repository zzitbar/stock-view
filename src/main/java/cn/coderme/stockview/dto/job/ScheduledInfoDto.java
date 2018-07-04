package cn.coderme.stockview.dto.job;

/**
 * Created By Administrator
 * Date:2018/7/4
 * Time:9:26
 */
public class ScheduledInfoDto {

    public static final String TYPE_CRON = "10";
    public static final String TYPE_FIXEDRATE = "20";
    public static final String TYPE_FIXEDDELAY = "30";

    private String clzName;//类名
    private String method;//方法名
    private String cron;//cron 表达式
    private long fixedDelay;//延迟
    private long fixedRate;//频率
    private String beanName;//bean名称
    private String jobInfo;//任务描述
    private String status;
    private String methodString;//全量方法名
    private String type;//类型 10：cron，20：fixedRate，30：fixedDelay

    public String getClzName() {
        return clzName;
    }

    public void setClzName(String clzName) {
        this.clzName = clzName;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public long getFixedDelay() {
        return fixedDelay;
    }

    public void setFixedDelay(long fixedDelay) {
        this.fixedDelay = fixedDelay;
    }

    public long getFixedRate() {
        return fixedRate;
    }

    public void setFixedRate(long fixedRate) {
        this.fixedRate = fixedRate;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getJobInfo() {
        return jobInfo;
    }

    public void setJobInfo(String jobInfo) {
        this.jobInfo = jobInfo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMethodString() {
        return methodString;
    }

    public void setMethodString(String methodString) {
        this.methodString = methodString;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}