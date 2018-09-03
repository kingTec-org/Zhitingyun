package com.example.zhitingyun.model;

import java.io.Serializable;

/**
 * Created by dasiy on 2018/7/17.
 */

public class NewestRecord implements Serializable {
    private Integer id;
    private Integer userId;
    private Integer equipmentHolder;
    private String left_data;
    private String right_data;
    private String left_hertz;
    private String right_hertz;
    private Long createTime;
    private Boolean del;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getEquipmentHolder() {
        return equipmentHolder;
    }

    public void setEquipmentHolder(Integer equipmentHolder) {
        this.equipmentHolder = equipmentHolder;
    }

    public String getLeft_data() {
        return left_data;
    }

    public void setLeft_data(String left_data) {
        this.left_data = left_data;
    }

    public String getRight_data() {
        return right_data;
    }

    public void setRight_data(String right_data) {
        this.right_data = right_data;
    }

    public String getLeft_hertz() {
        return left_hertz;
    }

    public void setLeft_hertz(String left_hertz) {
        this.left_hertz = left_hertz;
    }

    public String getRight_hertz() {
        return right_hertz;
    }

    public void setRight_hertz(String right_hertz) {
        this.right_hertz = right_hertz;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Boolean getDel() {
        return del;
    }

    public void setDel(Boolean del) {
        this.del = del;
    }
}
