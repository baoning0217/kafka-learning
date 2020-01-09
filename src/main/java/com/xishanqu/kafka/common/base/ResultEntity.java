package com.xishanqu.kafka.common.base;

import com.xishanqu.kafka.common.util.StringUtils;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author BaoNing 2019/6/12
 */
public class ResultEntity<T> implements Serializable {

    private static final long serialVersionUID = -1372623373646155857L;
    public static final String SUCCESS_RESPONSE_CODE = "200";
    private T data;
    private String code = "200";
    private String content;
    private Map<String, Object> subData;

    public Map<String, Object> getSubData() {
        return this.subData;
    }

    private void setSubData(Map<String, Object> subData) {
        this.subData = subData;
    }

    public ResultEntity() {
    }

    public ResultEntity(ExceptionEntity exceptionEntity) {
        this.code = exceptionEntity.getCode();
        this.content = exceptionEntity.getContent();
    }

    public ResultEntity(String content) {
        this.code = "SV00000";
        this.content = content;
    }

    public ResultEntity(T data, String code, String content) {
        this.data = data;
        this.code = code;
        this.content = content;
    }

    public ResultEntity(String code, String content) {
        this.code = code;
        this.content = content;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ResultEntity(T data) {
        this.data = data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return this.data;
    }

    public void addSubData(String key, Object value) {
        if (StringUtils.isEmpty(this.getSubData())) {
            this.setSubData(new HashMap());
        }
        this.getSubData().put(key, value);
    }

    public void addSubData(Map<String, Object> map) {
        if (StringUtils.isEmpty(this.getSubData())) {
            this.setSubData(new HashMap());
        }
        if (StringUtils.isNotEmpty(map)) {
            this.getSubData().putAll(map);
        }
    }

    public boolean checkSuccess() {
        return "200".equals(this.code);
    }

    public boolean checkFailed() {
        return !this.checkSuccess();
    }
}
