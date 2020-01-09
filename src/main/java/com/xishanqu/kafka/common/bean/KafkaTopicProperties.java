package com.xishanqu.kafka.common.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author BaoNing 2019/6/11
 */
@ConfigurationProperties(value = "kafka.topic")
@Data
public class KafkaTopicProperties {

    private String groupId;

    private String[] topicName;

}
