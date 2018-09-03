package com.example.zhitingyun.model;

/**
 * Created by dasiy on 2018/7/7.
 */

public class Quick {
    private Integer userId;
    private String userName;
    private Integer quickOrderId;
    private Long endTime;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getQuickOrderId() {
        return quickOrderId;
    }

    public void setQuickOrderId(Integer quickOrderId) {
        this.quickOrderId = quickOrderId;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }
}
