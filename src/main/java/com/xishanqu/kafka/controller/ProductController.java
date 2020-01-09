package com.xishanqu.kafka.controller;

import com.alibaba.fastjson.JSON;
import com.xishanqu.kafka.common.base.ResultEntity;
import com.xishanqu.kafka.entity.Product;
import com.xishanqu.kafka.service.KafkaService;
import com.xishanqu.kafka.service.ProductService;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author BaoNing 2019/6/12
 */
@RestController
@RequestMapping("/api/admin/product")
@Slf4j
public class ProductController {

    @Autowired
    private KafkaService kafkaService;

    @Autowired
    private ProductService productService;

    @Value("${kafka.topic.product-topic}")
    private String topicName;


    /**
     * ip
     * @Param
     * @Return
     * @Author BaoNing
     * @Time 2019/12/18 21:59
     */
    @GetMapping("/request")
    public ResultEntity initSearchLog(HttpServletRequest request){
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        log.info("userAgent============,{}", userAgent);

        Browser browser = userAgent.getBrowser();
        log.info("browser============,{}",  browser);

        String remoteAddr = request.getRemoteAddr();
        log.info("remoteAddr============,{}",  remoteAddr);

        String method = request.getMethod();
        log.info("method==============,{}", method);

        String servletPath = request.getServletPath();
        log.info("servletPath=============,{}", servletPath);

        String serverName = request.getServerName();
        log.info("serverName============,{}", serverName);


        return new ResultEntity(userAgent);

    }


    /**
     * get
     * @Param
     * @Return
     * @Author BaoNing
     * @Time 2019/12/18 17:33
     */
    @GetMapping("/get")
    public ResultEntity getProduct(@RequestParam("sid") String sid){
        if (StringUtils.isEmpty(sid)){
            return new ResultEntity("params lack");
        }
        Product product = productService.getBySid(sid);
        return new ResultEntity(product);
    }


    /**
     * save
     * @param product
     * @return
     */
    @PostMapping("/save")
    public ResultEntity saveProduct(@RequestBody Product product){
        if (Objects.isNull(product)){
            return new ResultEntity("product is null");
        }
        //发送到kafka
        kafkaService.sendMessage(topicName, JSON.toJSONString(product));

       // product = productService.saveProduct(product);
        return new ResultEntity(product);
    }


    /**
     * save
     * @param product
     * @return
     */
    @PostMapping("/saveMysql")
    public ResultEntity saveProductToMysql(@RequestBody Product product){
        if (Objects.isNull(product)){
            return new ResultEntity("product is null");
        }
        product = productService.saveProduct(product);
        return new ResultEntity(product);
    }



    /**
     * saveToMongo
     * @param product
     * @return
     */
    @PostMapping("/saveMongo")
    public ResultEntity saveProductToMongo(@RequestBody Product product){
        if (Objects.isNull(product)){
            return new ResultEntity("product is null");
        }
        product = productService.saveProductToMongo(product);
        return new ResultEntity(product);
    }


    /**
     * 更新所有门店
     * @Param
     * @Return
     * @Author BaoNing
     * @Time 2019/12/25 17:58
     */
    @GetMapping("/updateAllProduct")
    public ResultEntity updateAllProduct(){
        AtomicInteger atomicInteger = productService.updateAllProduct();
        return new ResultEntity("修改了:" + atomicInteger + "条记录" );
    }


}
