package cn.coderme.stockview.base.annotation;

import java.lang.annotation.*;

/**
 * 注解定时任务方法描述
 * Created By Administrator
 * Date:2018/7/4
 * Time:9:17
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JobInfo {

    String value() default "";
}