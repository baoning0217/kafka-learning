package com.xishanqu.kafka.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * @Author BaoNing 2019/6/12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "t_product")
public class Product {

    private String id;

    @Id
    @Field(value = "sid")
    private String sid;

    @Field(value = "name")
    private String name;

    @Field(value = "description")
    private String description;

    @Field(value = "price")
    private Double price;

    @Field(value = "status")
    private Integer status;

    @Field(value = "content")
    private String content;

    @Field(value = "shop_id")
    private String shopId;

    @Field(value = "show_style")
    private Integer showStyle;

    @Field(value = "create_time")
    private Date createTime;


    /** 特殊对象 */
    //private ProductVo productVo;

}
