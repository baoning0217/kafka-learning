package com.xishanqu.kafka.mapper;

import com.xishanqu.kafka.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 中文类名:
 * 中文描述:
 *
 * @Author BaoNing
 * @Time 2019/12/10
 */
@Mapper
public interface ProductMapper {

    /**
     * get
     * @Param sid
     * @Return product
     * @Author BaoNing
     * @Time 2019/12/18 17:36
     */
    Product getBySid(String sid);


    /**
     * add
     * @Param product
     * @Return int
     * @Author BaoNing
     * @Time 2019/12/10 16:09
     */
    int add(Product product);


    /**
     * 更新特殊门店
     * @Param shopId
     * @Return int
     * @Author BaoNing
     * @Time 2019/12/25 17:19
     */
    int updateSpecialShop(@Param("shopId")String shopId);


}
