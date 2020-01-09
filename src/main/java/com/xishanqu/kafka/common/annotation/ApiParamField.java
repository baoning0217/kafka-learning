package com.xishanqu.kafka.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 中文类名:
 * 中文描述:
 *
 * @Author BaoNing
 * @Time 2020/1/6
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiParamField {

    public String desc();

    int length();

}
