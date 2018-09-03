package com.xiyoukeji.zhitingyun.data.entity;

import java.io.Serializable;


public class UserEntity implements Serializable {


    /**
     * id : 350
     * name : 知鱼之乐
     * phone : 13173638162
     * headPic : images/user/1530261928947a9e376.jpeg
     * age : 20
     * age_1 : 48
     * cureCount : 0
     * wearTimeEnum : 2
     * equipmentAddress : null
     * sex : 1
     * able : true
     * records : 66
     * token : null
     * refreshToken : null
     * imToken : null
     * accid : null
     */

    private Integer id;
    private String name;
    private String phone;
    private String headPic;
    private Integer age;
    private Integer age_1;
    private Integer cureCount;
    private Integer wearTimeEnum;
    private Object equipmentAddress;
    private Integer sex;
    private boolean able;
    private String records;
    private String token;
    private String refreshToken;
    private String imToken;
    private String  accid;

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

    public Integer getAge_1() {
        return age_1;
    }

    public void setAge_1(Integer age_1) {
        this.age_1 = age_1;
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

    public Object getEquipmentAddress() {
        return equipmentAddress;
    }

    public void setEquipmentAddress(Object equipmentAddress) {
        this.equipmentAddress = equipmentAddress;
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

    public String getImToken() {
        return imToken;
    }

    public void setImToken(String imToken) {
        this.imToken = imToken;
    }

    public String getAccid() {
        return accid;
    }

    public void setAccid(String accid) {
        this.accid = accid;
    }
}
