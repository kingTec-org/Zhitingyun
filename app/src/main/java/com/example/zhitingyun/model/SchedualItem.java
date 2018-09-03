package com.example.zhitingyun.model;

/**
 * Created by dasiy on 2018/7/12.
 */

public class SchedualItem {
    private Integer id;
    private Long start;
    private Long end;

    private String item;
    private Integer status;

    public SchedualItem(String item, Integer status) {
        this.item = item;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getEnd() {
        return end;
    }

    public void setEnd(Long end) {
        this.end = end;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
