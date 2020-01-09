package com.xishanqu.kafka.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;


/**
 * @Author BaoNing 2019/6/12
 */
@Service
@Slf4j
public class KafkaService {

    private final KafkaTemplate<Integer, String> kafkaTemplate;

    /**
     * 注入KafkaTemplate
     * @param kafkaTemplate kafka模版类
     */
    @Autowired
    public KafkaService(KafkaTemplate kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * 发送消息
     * @param topic
     * @param data
     */
    public void sendMessage(String topic, String data){
        log.info("kafka sendMessage start");
        ListenableFuture<SendResult<Integer, String>> future = kafkaTemplate.send(topic, data);
        future.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                log.error("kafka sendMessage error, throwable = {}, topic = {}, data = {} >>>>>>>>>>>>>>>>>>", throwable, topic, data );
            }
            @Override
            public void onSuccess(SendResult<Integer, String> result) {
                /**
                 * result表示发送消息成功，返回的信息。
                 *
                 *  ProducerRecord（构建消息对象，包含：topic、partition、timestamp、k、v、headers）
                 *  RecordMetadata（topicPartition、offset）
                 *
                 */
                log.info("kafka sendMessage success, result = {}, topic = {}, data = {} >>>>>>>>>>>>>>>>>>", result, topic, data );
            }
        });
        log.info("kafka sendMessage end");
    }


}
