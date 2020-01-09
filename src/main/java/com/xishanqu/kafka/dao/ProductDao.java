package com.xishanqu.kafka.dao;

import com.xishanqu.kafka.entity.Product;
import com.xishanqu.kafka.mapper.ProductMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author BaoNing 2019/6/12
 */
@Log4j2
@Component
public class ProductDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ProductMapper productMapper;

    /**
     * getBySid
     * @Param
     * @Return
     * @Author BaoNing
     * @Time 2019/12/18 17:38
     */
    public Product getBySid(String sid){
        return productMapper.getBySid(sid);
    }


    /**
     * save
     * @param product
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int saveProduct(Product product){
        return productMapper.add(product);
    }


    /**
     * 保存到Mongo
     * @Param
     * @Return
     * @Author BaoNing
     * @Time 2019/12/25 16:05
     */
    public Product saveProductToMongo(Product product){
        product = mongoTemplate.save(product);
        log.info("product======" + product);
        return product;
    }


    /**
     * 所有数据
     * @Param
     * @Return
     * @Author BaoNing
     * @Time 2019/12/25 17:18
     */
    public List<Product> allProduct(){
        List<Product> productList = mongoTemplate.findAll(Product.class);
        return productList;
    }


    /**
     * 更新数据
     * @Param
     * @Return
     * @Author BaoNing
     * @Time 2019/12/25 17:45
     */
    public void updateAllProduct(String id, Integer showStyle){
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update().set("show_style", showStyle);
        mongoTemplate.findAndModify(query, update, Product.class);
    }


    /**
     * 更新特殊门店
     * @Param
     * @Return
     * @Author BaoNing
     * @Time 2019/12/25 17:24
     */
    public int updateSpecialShop(String shopId){
        return productMapper.updateSpecialShop(shopId);
    }

}
