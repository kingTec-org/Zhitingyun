package com.example.zhitingyun.model;

import java.io.Serializable;

/**
 * Created by dasiy on 2018/7/17.
 */

public class UserInfo implements Serializable {
    private Integer id;
    private String name;
    private String phone;
    private String headPic;
    private Integer age;
    private Integer cureCount;
    private Integer wearTimeEnum;
    private String equipmentAddress;
    private Integer sex;
    private Boolean able;
    private String records;
    private String token;
    private String refreshToken;
    private String accid;

    public String getAccid() {
        return accid;
    }

    public void setAccid(String accid) {
        this.accid = accid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getCureCount() {
        return cureCount;
    }

    public void setCureCount(Integer cureCount) {
        this.cureCount = cureCount;
    }

    public Integer getWearTimeEnum() {
        return wearTimeEnum;
    }

    public void setWearTimeEnum(Integer wearTimeEnum) {
        this.wearTimeEnum = wearTimeEnum;
    }

    public String getEquipmentAddress() {
        return equipmentAddress;
    }

    public void setEquipmentAddress(String equipmentAddress) {
        this.equipmentAddress = equipmentAddress;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Boolean getAble() {
        return able;
    }

    public void setAble(Boolean able) {
        this.able = able;
    }

    public String getRecords() {
        return records;
    }

    public void setRecords(String records) {
        this.records = records;
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
