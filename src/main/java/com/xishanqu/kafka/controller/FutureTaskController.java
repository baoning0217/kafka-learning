package com.xishanqu.kafka.controller;

import com.xishanqu.kafka.entity.vo.ProductCountDto;
import com.xishanqu.kafka.common.future.NoProductFutureTask;
import com.xishanqu.kafka.common.future.ProductFutureTask;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 中文类名:
 * 中文描述:
 *
 * @Author BaoNing
 * @Time 2019/12/31
 */
@RestController
@RequestMapping("/api/admin/product/future")
@Log4j2
public class FutureTaskController {

    @Autowired
    private ProductFutureTask productFutureTask;

    @Autowired
    private NoProductFutureTask noProductFutureTask;


    /**
     * 使用线程
     * @Param
     * @Return
     * @Author BaoNing
     * @Time 2019/12/31 16:35
     */
    @GetMapping("/dataWithFuture")
    public ProductCountDto getProductDataWithFuture(@RequestParam("productId") String productId){
        log.info("使用线程>>>>>>>>>>>>>>>>>>>>>入参:" + productId);
        long begin = System.currentTimeMillis();
        ProductCountDto data = productFutureTask.getProductAggregatedResult(productId);
        long end = System.currentTimeMillis();
        double time = (end - begin) / 1000.0000;
        log.info(">>>>>>>>>>>>>>>>>>>>>此接口总耗时:" + time + "秒");
        data.setTime("此接口总耗时:" + time);
        return data;
    }


    /**
     * 不使用线程
     * @Param
     * @Return
     * @Author BaoNing
     * @Time 2019/12/31 16:35
     */
    @GetMapping("/dataNoFuture")
    public ProductCountDto getProductDataNoFuture(@RequestParam("productId") String productId){
        log.info("不使用线程>>>>>>>>>>>>>>>>>>>>>入参:" + productId);
        long beginTime = System.currentTimeMillis();
        ProductCountDto data = noProductFutureTask.getProductAggregatedResult(productId);
        long endTime = System.currentTimeMillis();
        double time = (endTime - beginTime) / 1000.0000;
        log.info(">>>>>>>>>>>>>>>>>>>>>此接口总耗时:" + time + "秒");
        data.setTime("此接口总耗时:" + time);
        return data;
    }


}
