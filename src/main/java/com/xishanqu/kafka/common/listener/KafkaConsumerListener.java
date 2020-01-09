package com.xishanqu.kafka.common.listener;

import com.xishanqu.kafka.dao.ProductDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 中文类名:
 * 中文描述:
 *
 * @Author BaoNing
 * @Time 2019/12/10
 */
@Slf4j
@Component
public class KafkaConsumerListener {

    @Autowired
    private ProductDao productDao;


    /**
     * 消费kafka消息，插入Mysql
     * @Param
     * @Return
     * @Author BaoNing
     * @Time 2019/12/10 16:36
     */
//    @KafkaListener(topics = "${kafka.topic.product-topic}", groupId = "${kafka.topic.group-id}")
//    public void kafkaConsumer(ConsumerRecord<Integer, String> record){
//        Product product = JSON.parseObject(record.value(), Product.class);
//        //保存到mysql
//        int i = productDao.saveProduct(product);
//        log.info("插入Mysql:状态 " + i);
//
//        //保存到Mongo
//        Product product_res = productDao.saveProductToMongo(product);
//        log.info("插入Mongo返回数据:" + product_res);
//
//    }



}
