package com.xishanqu.kafka.entity;

import lombok.Data;

/**
 * 中文类名:
 * 中文描述:
 *
 * @Author BaoNing
 * @Time 2019/12/18
 */
@Data
public class Account {

    private Integer id;

    private String accountId;

    private String username;

    private String password;

    private String sessionId;

    private Data createDate;

}
