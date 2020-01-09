package com.xishanqu.kafka.common.future;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import com.xishanqu.kafka.entity.vo.ProductCountDto;
import com.xishanqu.kafka.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * 中文类名: 使用线程查询
 * 中文描述:
 *
 * @Author BaoNing
 * @Time 2019/12/31
 */
@Log4j2
@Component
public class ProductFutureTask {

    @Autowired
    private ProductService productService;


    /**
     * 核心线程 8, 最大线程 20, 保活时间 30s, 存储队列 10, 有守护线程, 拒绝策略：将超负荷任务回退到调用者
     */
    private static ExecutorService executor = new ThreadPoolExecutor(8,60,30L, TimeUnit.SECONDS,
            //基于链表结构的阻塞队列
            new LinkedBlockingDeque<Runnable>(10),
            //设置守护线程
            new ThreadFactoryBuilder().setNamePrefix("Product_Async_FutureTask-").setDaemon(true).build(),
            //只用调用者所在线程来处理任务
            new ThreadPoolExecutor.CallerRunsPolicy());


    /**
     * 商品数据聚合集
     * @Param
     * @Return
     * @Author BaoNing
     * @Time 2019/12/31 15:12
     */
    public ProductCountDto getProductAggregatedResult(final String productId){
        log.info("ProductFutureTask的线程:" + Thread.currentThread());

        long fansCount = 0, msgCount = 0, collectCount = 0, followCount = 0, redBagCount = 0, couponCount = 0;

        try {
            //submit方法:不需要返回结果则实现Runnable,需要执行结果实现Callable,需要返回结果调用期submit方法。
            Future<Long> fansCountFT = executor.submit(new Callable<Long>() {
                //将任务runner以caller的方式进行回调,阻塞获取,最后将结果汇总。
                @Override
                public Long call() throws Exception {
                    return productService.countFansCountByProductId(productId);
                }
            });

            Future<Long> msgCountFT = executor.submit(() -> productService.countMsgCountByProductId(productId));
            Future<Long> collectCountFT = executor.submit(() -> productService.countCollectCountByProductId(productId));
            Future<Long> followCountFT = executor.submit(() -> productService.countFollowCountByProductId(productId));
            Future<Long> redBagCountFT = executor.submit(() -> productService.countRedBagCountByProductId(productId));
            Future<Long> couponCountFT = executor.submit(() -> productService.countCouponCountByProductId(productId));

            //get阻塞
            fansCount = fansCountFT.get();
            msgCount = msgCountFT.get();
            collectCount = collectCountFT.get();
            followCount = followCountFT.get();
            redBagCount = redBagCountFT.get();
            couponCount = couponCountFT.get();

        }catch (Exception ex){
            log.error(">>>>>>>>>聚合查询用户聚合信息异常:" + ex + "<<<<<<<<<");
        }

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
