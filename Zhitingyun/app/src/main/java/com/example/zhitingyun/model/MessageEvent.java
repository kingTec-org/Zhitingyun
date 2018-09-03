package com.example.zhitingyun.model;

/**
 * Created by dasiy on 2018/7/13.
 */

public class MessageEvent<T> {
    private Integer flag;//1删除首页接听数据2刷新预约管理数据3视频页面取消诊疗
    private T object;

    public MessageEvent(Integer flag) {
        this.flag = flag;
    }

    public MessageEvent(Integer flag, T object) {
        this.flag = flag;
        this.object = object;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }
}
