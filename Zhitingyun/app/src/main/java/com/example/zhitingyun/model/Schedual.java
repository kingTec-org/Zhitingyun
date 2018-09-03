package com.example.zhitingyun.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dasiy on 2018/7/12.
 */

public class Schedual {
    private int date;
    private Integer status;
    private String strDate;
    private String strWeek;
    private Integer canAdd;
    private List<SchedualItem> schedualItems = new ArrayList<>();

    public Schedual(int date, Integer status, String strDate, String strWeek, Integer canAdd) {
        this.date = date;
        this.status = status;
        this.strDate = strDate;
        this.strWeek = strWeek;
        this.canAdd = canAdd;
    }

    public Schedual(int date, Integer status, String strDate, String strWeek, Integer canAdd, List<SchedualItem> schedualItems) {
        this.date = date;
        this.status = status;
        this.strDate = strDate;
        this.strWeek = strWeek;
        this.canAdd = canAdd;
        this.schedualItems = schedualItems;
    }

    public Integer getCanAdd() {
        return canAdd;
    }

    public void setCanAdd(Integer canAdd) {
        this.canAdd = canAdd;
    }

    public Schedual(int date) {
        this.date = date;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getStrDate() {
        return strDate;
    }

    public void setStrDate(String strDate) {
        this.strDate = strDate;
    }

    public String getStrWeek() {
        return strWeek;
    }

    public void setStrWeek(String strWeek) {
        this.strWeek = strWeek;
    }

    public List<SchedualItem> getSchedualItems() {
        return schedualItems;
    }

    public void setSchedualItems(List<SchedualItem> schedualItems) {
        this.schedualItems = schedualItems;
    }
}
