package com.example.zhitingyun.model;

/**
 * Created by dasiy on 2018/7/9.
 */

public class Order {
    private String time;
    private String name;
    private String status;

    public Order(String time, String name, String status) {
        this.time = time;
        this.name = name;
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
