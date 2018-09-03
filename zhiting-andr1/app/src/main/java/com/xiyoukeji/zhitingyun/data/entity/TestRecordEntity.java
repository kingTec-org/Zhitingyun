package com.xiyoukeji.zhitingyun.data.entity;

import java.io.Serializable;

public class TestRecordEntity implements Serializable {

    /**
     * id : 67
     * userId : 1
     * equipmentHolder : 1
     * left_data : 10,25,30,55,50,20,70,30,40,40,20
     * right_data : 10,25,30,55,50,20,70,30,40,40,20
     * left_hertz : 125,250,500,750,1000,1500,2000,3000,4000,6000,8000
     * right_hertz : 125,250,500,750,1000,1500,2000,3000,4000,6000,8000
     * createTime : 1530688258718
     * del : false
     */

    private int id;
    private int userId;
    private int equipmentHolder;
    private String left_data;
    private String right_data;
    private String left_hertz;
    private String right_hertz;
    private long createTime;
    private boolean del;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getEquipmentHolder() {
        return equipmentHolder;
    }

    public void setEquipmentHolder(int equipmentHolder) {
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

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public boolean isDel() {
        return del;
    }

    public void setDel(boolean del) {
        this.del = del;
    }
}
