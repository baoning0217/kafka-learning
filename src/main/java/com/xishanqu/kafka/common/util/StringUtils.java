package com.xishanqu.kafka.common.util;

/**
 * @Author BaoNing 2019/6/12
 */
public class StringUtils {

    public static boolean isEmpty(Object o) {
        return org.springframework.util.StringUtils.isEmpty(o);
    }

    public static boolean isNotEmpty(Object o) {
        return !isEmpty(o);
    }

}
