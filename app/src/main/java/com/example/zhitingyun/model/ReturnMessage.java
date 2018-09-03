package com.example.zhitingyun.model;

/**
 * Created by dasiy on 2018/7/25.
 */

public class ReturnMessage {
    private Integer classify;
    private String key;
    private Object value;

    public Integer getClassify() {
        return classify;
    }

    public void setClassify(Integer classify) {
        this.classify = classify;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
