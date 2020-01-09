package com.xishanqu.kafka.controller;

import com.xishanqu.kafka.service.KafkaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 中文类名:
 * 中文描述:
 *  开启定时任务
 *  @EnableScheduling
 *
 * @author captain
 * @Time 2019/12/10
 */
@Slf4j
@EnableScheduling
@Configuration
public class ScheduledController {

    @Autowired
    private KafkaService kafkaService;

    @Value("${kafka.topic.product-topic}")
    private String topicName;


    /**
     * 每5秒执行一次
     * @Param
     * @Return
     * @Author BaoNing
     * @Time 2019/12/10 16:23
     */
//    @Scheduled(cron = "0/5 * * * * ?")
//    public void sendMessage(){
//        String randomUUID = IdUtil.randomUUID();
//        Product product = new Product();
//        product.setSid(randomUUID);
//        product.setName("小米手机");
//        product.setDescription("为发烧而生，相信美好的事情即将发生!");
//        product.setPrice(1999.00);
//        product.setStatus(1);
//        product.setContent("因为米粉，所以小米!");
//        product.setShopId("abc123");
//        product.setShowStyle(1);
//        product.setCreateTime(new Date());
//        //发送到kafka
//        log.info("发送消息：" + JSON.toJSONString(product));
//        kafkaService.sendMessage(topicName, JSON.toJSONString(product));
//    }


}
