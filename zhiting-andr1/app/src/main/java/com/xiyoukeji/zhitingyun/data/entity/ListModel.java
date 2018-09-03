package com.xiyoukeji.zhitingyun.data.entity;

import java.util.List;



public class ListModel<T> {
//    private List<T> data;
    private Integer total;
    private T comeback;


//    public List<T> getData() {
//        return data;
//    }
//
//    public void setData(List<T> data) {
//        this.data = data;
//    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public T getComeback() {
        return comeback;
    }

    public void setComeback(T comeback) {
        this.comeback = comeback;
    }
}
