package com.xishanqu.kafka.entity.vo;

import lombok.*;

import java.io.Serializable;

/**
 * 中文类名:
 * 中文描述:
 *
 * @Author BaoNing
 * @Time 2019/12/31
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductCountDto implements Serializable {

    private Long fansCount;

    private Long msgCount;

    private Long collectCount;

    private Long followCount;

    private Long redBagCount;

    private Long couponCount;

    private String time;

}
