package com.xiyoukeji.zhitingyun.data.entity;

import java.io.Serializable;

public class AppointmentEntity implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * id : 3
     * age : 766166400
     * serviceCount : 0
     * name : 我是大法师
     * phone : 18256677567
     * headPic : images/admin/15305974233052fadc9.jpg
     * sex : 1
     * able : true
     * rate : 0
     * workTime : 2
     * canBeenOrder : false
     * token : null
     * refreshToken : null
     */

    private Integer id;
    private Long age;
    private Integer serviceCount;
    private String name;
    private String phone;
    private String headPic;
    private Integer sex;
    private boolean able;
    private Integer rate;
    private Integer workTime;
    private boolean canBeenOrder;
    private String token;
    private String refreshToken;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public Integer getServiceCount() {
        return serviceCount;
    }

    public void setServiceCount(Integer serviceCount) {
        this.serviceCount = serviceCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public boolean isAble() {
        return able;
    }

    public void setAble(boolean able) {
        this.able = able;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Integer getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Integer workTime) {
        this.workTime = workTime;
    }

    public boolean isCanBeenOrder() {
        return canBeenOrder;
    }

    public void setCanBeenOrder(boolean canBeenOrder) {
        this.canBeenOrder = canBeenOrder;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
