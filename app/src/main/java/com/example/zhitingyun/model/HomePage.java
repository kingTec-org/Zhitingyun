package com.example.zhitingyun.model;

import java.util.List;

/**
 * Created by dasiy on 2018/7/21.
 */

public class HomePage {
    private Integer id;
    private List<Confirm> orderListDtos;
    private String remind;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Confirm> getOrderListDtos() {
        return orderListDtos;
    }

    public void setOrderListDtos(List<Confirm> orderListDtos) {
        this.orderListDtos = orderListDtos;
    }

    public String getRemind() {
        return remind;
    }

    public void setRemind(String remind) {
        this.remind = remind;
    }
}
