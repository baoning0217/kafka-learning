package com.xishanqu.kafka.service;

import cn.hutool.core.util.IdUtil;
import com.xishanqu.kafka.common.bean.SpShopProperties;
import com.xishanqu.kafka.dao.ProductDao;
import com.xishanqu.kafka.entity.Product;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author BaoNing 2019/6/12
 */
@Service
@Log4j2
public class ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private SpShopProperties spShopProperties;


    /**
     * getBySid
     * @Param
     * @Return
     * @Author BaoNing
     * @Time 2019/12/18 17:39
     */
//    public Product getBySid(String sid){
//        log.info("sid ============> {}", sid);
//        Product product = productDao.getBySid(sid);
//        if (ObjectUtil.isNotEmpty(product)){
//            Map<String, Map<String, String>> shopMap = spShopProperties.getShop();
//            log.info("shop===========,{}", shopMap);
//            shopMap.forEach((key, value) ->{
//                String shopId = value.get("shopId");
//                if (shopId.equals(sid)){
//                    ProductVo productVo = new ProductVo();
//                    Integer type = Integer.valueOf(value.get("type"));
//                    String name = value.get("name");
//                    productVo.setName(name);
//                    productVo.setType(type);
//                    productVo.setShopId(shopId);
//                    product.setProductVo(productVo);
//                }
//            });
//        }
//        return product;
//    }

    /**
     * 测试apiCount
     * @Param
     * @Return
     * @Author BaoNing
     * @Time 2019/12/20 17:56
     */
    public Product getBySid(String sid){
        Product product = productDao.getBySid(sid);
        return product;
    }


    /**
     * save
     * @param product
     * @return
     */
    public Product saveProduct(Product product){
        int res = productDao.saveProduct(product);
        if (res > 0){
            return product;
        }
        return null;
    }


    /**
     * 保存到mongo
     * @Param
     * @Return
     * @Author BaoNing
     * @Time 2019/12/25 16:41
     */
    @Transactional(rollbackFor = {Exception.class})
    public Product saveProductToMongo(Product product){
        String randomUUID = IdUtil.randomUUID();
        product.setSid(randomUUID);
        product.setDescription("为发烧而生，相信美好的事情即将发生!");
        product.setPrice(1999.00);
        product.setStatus(1);
        product.setContent("因为米粉，所以小米!");
        product.setShowStyle(1);

        try {
            //save to mongo
            product = productDao.saveProductToMongo(product);
            //save to mysql
            this.saveProduct(product);
        }catch (Exception ex){
            log.error("异常:" + ex);
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return product;
    }


    /**
     * 更新所有数据
     * @Param
     * @Return
     * @Author BaoNing
     * @Time 2019/12/25 17:44
     */
    public AtomicInteger updateAllProduct(){
        AtomicInteger atomicInteger = new AtomicInteger();
        List<Product> productList = productDao.allProduct();
        productList.stream().forEach(product -> {
            if ("5e0328536793062384a924d4".equals(product.getId())){
                productDao.updateAllProduct(product.getId(), 3);
                atomicInteger.getAndIncrement();
            }else {
                productDao.updateAllProduct(product.getId(),1);
                atomicInteger.getAndIncrement();
            }
        });
        return atomicInteger;
    }


/**********************************************测试 Future Task*********************************************************/

    public long countFansCountByProductId(String productId) {
        try {
            Thread.sleep(1000);
            log.info("获取FansCount===睡眠:" + 1 + "s");
        } catch (InterruptedException e) {
            log.error("获取FansCount异常===" + e);
        }
        log.info("UserService获取FansCount的线程  " + Thread.currentThread().getName());
        return 520;
    }

    public long countMsgCountByProductId(String productId) {
        log.info("UserService获取MsgCount的线程  " + Thread.currentThread().getName());
        try {
            Thread.sleep(800);
            log.info("获取MsgCount===睡眠:" + 0.8 + "s");
        } catch (InterruptedException e) {
            log.error("获取MsgCount异常===" + e);
        }
        return 618;
    }

    public long countCollectCountByProductId(String productId) {
        log.info("UserService获取CollectCount的线程  " + Thread.currentThread().getName());
        try {
            Thread.sleep(500);
            log.info("获取CollectCount==睡眠:" + 0.5 + "s");
        } catch (InterruptedException e) {
            log.error("获取CollectCount异常===" + e);
        }
        return 6664;
    }

    public long countFollowCountByProductId(String productId) {
        log.info("UserService获取FollowCount的线程  " + Thread.currentThread().getName());
        try {
            Thread.sleep(900);
            log.info("获取FollowCount===睡眠:" + 0.9 + "s");
        } catch (InterruptedException e) {
            log.error("获取FollowCount异常===" + e);
        }
        return 888;
    }

    public long countRedBagCountByProductId(String productId) {
        log.info("UserService获取RedBagCount的线程  " + Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(1);
            log.info("获取RedBagCount===睡眠:" + 1 + "s");
        } catch (InterruptedException e) {
            log.error("获取RedBagCount异常===" + e);
        }
        return 99;
    }

    public long countCouponCountByProductId(String productId) {
        log.info("UserService获取CouponCount的线程  " + Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(1);
            log.info("获取CouponCount===睡眠:" + 1 + "s");
        } catch (InterruptedException e) {
            log.error("获取CouponCount异常===" + e);
        }
        return 66;
    }


/**********************************************测试 Future Task*********************************************************/



}
