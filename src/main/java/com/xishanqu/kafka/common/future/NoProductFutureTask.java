package com.xishanqu.kafka.common.future;

import com.xishanqu.kafka.entity.vo.ProductCountDto;
import com.xishanqu.kafka.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 中文类名: 未使用线程
 * 中文描述:
 *
 * @Author BaoNing
 * @Time 2019/12/31
 */
@Log4j2
@Component
public class NoProductFutureTask {

    @Autowired
    private ProductService productService;


    /**
     * 商品数据聚合集
     * @Param
     * @Return
     * @Author BaoNing
     * @Time 2019/12/31 15:12
     */
    public ProductCountDto getProductAggregatedResult(final String productId){
        System.out.println("ProductFutureTask的线程:" + Thread.currentThread());

        long fansCount = 0, msgCount = 0, collectCount = 0, followCount = 0, redBagCount = 0, couponCount = 0;

        fansCount = productService.countFansCountByProductId(productId);
        msgCount = productService.countMsgCountByProductId(productId);
        collectCount = productService.countCollectCountByProductId(productId);
        followCount = productService.countFollowCountByProductId(productId);
        redBagCount = productService.countRedBagCountByProductId(productId);
        couponCount = productService.countCouponCountByProductId(productId);


        ProductCountDto productData = ProductCountDto.builder()
                .fansCount(fansCount)
                .msgCount(msgCount)
                .collectCount(collectCount)
                .followCount(followCount)
                .redBagCount(redBagCount)
                .couponCount(couponCount).build();

        return productData;
    }

}
