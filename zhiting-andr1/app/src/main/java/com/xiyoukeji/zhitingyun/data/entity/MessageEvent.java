package com.xiyoukeji.zhitingyun.data.entity;

/**
 * Created by dasiy on 2018/7/13.
 */

public class MessageEvent<T> {
    private Integer activityFlag;
    private Integer flag;//1删除首页接听数据2刷新预约管理数据
    private T object;

    public MessageEvent(Integer flag) {
        this.flag = flag;
    }

    public MessageEvent(Integer activityFlag, Integer flag, T object) {
        this.activityFlag = activityFlag;
        this.flag = flag;
        this.object = object;
    }

    public MessageEvent(Integer flag, T object) {
        this.flag = flag;
        this.object = object;
    }

    public Integer getActivityFlag() {
        return activityFlag;
    }

    public void setActivityFlag(Integer activityFlag) {
        this.activityFlag = activityFlag;
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
