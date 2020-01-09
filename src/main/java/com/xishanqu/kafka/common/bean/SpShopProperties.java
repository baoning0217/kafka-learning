package com.xishanqu.kafka.common.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 中文类名:
 * 中文描述:
 *
 * @Author BaoNing
 * @Time 2019/12/18
 */
@Data
@Component
@ConfigurationProperties(value = "sp")
public class SpShopProperties {

    public Map<String, Map<String, String>> shop;

}
