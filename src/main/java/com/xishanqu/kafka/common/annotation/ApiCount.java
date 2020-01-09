package com.xishanqu.kafka.common.annotation;

import java.lang.annotation.*;

/**
 * 中文类名: 定义统计流量注解
 * 中文描述:
 *
 * @Author BaoNing
 * @Time 2019/12/20
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiCount {

  //  String value() default "";

    String shopId() default "";

    String userId() default "";

    String productId() default "";

}
