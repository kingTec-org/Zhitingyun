package com.example.zhitingyun.base;

/**
 * Created by dasiy on 2018/7/15.
 */

public class Comeback<T> {
    private Integer total;
    private T comeback;

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
