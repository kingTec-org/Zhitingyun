package com.xiyoukeji.zhitingyun.data.entity;

import java.io.Serializable;


public class LoginEntity implements Serializable {
    /**
     * total : null
     * comeback : {"id":350,"name":"知鱼之乐","phone":"13173638162","headPic":"images/user/1530261928947a9e376.jpeg","age":20,"cureCount":0,"wearTimeEnum":2,"sex":1,"able":true,"records":"[Ljava.lang.Integer;@74a6efc8","token":"eyJhbGciOiJIUzI1NiJ9.eyJUT0tFTl9PUl9SRUZSRVNIX1RPS0VOIjoidG9rZW4iLCJuYW1lIjoi55-l6bG85LmL5LmQIiwiaXNzIjoiWElZT1VLRUpJIiwiZXhwIjoxNTMzMjY1NDMzMjE5LCJ1c2VySWQiOjM1MH0.rJpQIBza8ohz10TpVKnsYAJB4OFKFoxepJDmjQ52li0","refreshToken":"eyJhbGciOiJIUzI1NiJ9.eyJUT0tFTl9PUl9SRUZSRVNIX1RPS0VOIjoicmVmcmVzaFRva2VuIiwibmFtZSI6IuefpemxvOS5i-S5kCIsImlzcyI6IlhJWU9VS0VKSSIsImV4cCI6MTU2MTc3NzQzMzIxOSwidXNlcklkIjozNTAsInJlZnJlc2hUb2tlblR5cGUiOiJ1c2VyIn0.IBnEcE4ZM70Je4Z0StLQi91pzmsVrusnXZvy_20WuGE"}
     */

    /**
     * id : 350
     * name : 知鱼之乐
     * phone : 13173638162
     * headPic : images/user/1530261928947a9e376.jpeg
     * age : 20
     * cureCount : 0
     * wearTimeEnum : 2
     * sex : 1
     * able : true
     * records : [Ljava.lang.Integer;@74a6efc8
     * token : eyJhbGciOiJIUzI1NiJ9.eyJUT0tFTl9PUl9SRUZSRVNIX1RPS0VOIjoidG9rZW4iLCJuYW1lIjoi55-l6bG85LmL5LmQIiwiaXNzIjoiWElZT1VLRUpJIiwiZXhwIjoxNTMzMjY1NDMzMjE5LCJ1c2VySWQiOjM1MH0.rJpQIBza8ohz10TpVKnsYAJB4OFKFoxepJDmjQ52li0
     * refreshToken : eyJhbGciOiJIUzI1NiJ9.eyJUT0tFTl9PUl9SRUZSRVNIX1RPS0VOIjoicmVmcmVzaFRva2VuIiwibmFtZSI6IuefpemxvOS5i-S5kCIsImlzcyI6IlhJWU9VS0VKSSIsImV4cCI6MTU2MTc3NzQzMzIxOSwidXNlcklkIjozNTAsInJlZnJlc2hUb2tlblR5cGUiOiJ1c2VyIn0.IBnEcE4ZM70Je4Z0StLQi91pzmsVrusnXZvy_20WuGE
     */

    private Integer id;
    private String name;
    private String phone;
    private String headPic;
    private Integer age;
    private Integer cureCount;
    private Integer wearTimeEnum;
    private Integer sex;
    private boolean able;
    private String records;
    private String token;
    private String refreshToken;
    private UserEntity user;
    private String imToken;
    private String accid;

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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}


//    /**
//     * id : 350
//     * name : 知鱼之乐
//     * phone : 13173638162
//     * headPic : images/user/1530261928947a9e376.jpeg
//     * age : 20
//     * cureCount : null
//     * wearTimeEnum : 2
//     * sex : 1
//     * able : true
//     * records : null
//     * token : eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoi55-l6bG85LmL5LmQIiwiaXNzIjoiWElZT1VLRUpJIiwiZXhwIjoxNTMzMTE2MDcxMDc1LCJ1c2VySWQiOjM1MH0.DpxbLHW0NiF8vjc7OX_Yb_y_6w5ZVyRXJImWbWv5c98
//     */
//
//    private int id;
//    private String name;
//    private String phone;
//    private String headPic;
//    private int age;
//    private Object cureCount;
//    private int wearTimeEnum;
//    private int sex;
//    private boolean able;
//    private Object records;
//    private String token;
////    private UserEntity user;
//    /**
//     * cureCount : 0
//     * records : [Ljava.lang.Integer;@63e81566
//     * refreshToken : eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoi55-l6bG85LmL5LmQIiwiaXNzIjoiWElZT1VLRUpJIiwiZXhwIjoxNTYxNzIxMjU1NzEwLCJ1c2VySWQiOjM1MH0.bJQVAwSoLBSUI4QQBO4SUbepD3qPxdVkJ6RFNyQ28dg
//     */
//
//    private int cureCountX;
//    private String recordsX;
//    private String refreshToken;
//
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public String getHeadPic() {
//        return headPic;
//    }
//
//    public void setHeadPic(String headPic) {
//        this.headPic = headPic;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//
//    public Object getCureCount() {
//        return cureCount;
//    }
//
//    public void setCureCount(Object cureCount) {
//        this.cureCount = cureCount;
//    }
//
//    public int getWearTimeEnum() {
//        return wearTimeEnum;
//    }
//
//    public void setWearTimeEnum(int wearTimeEnum) {
//        this.wearTimeEnum = wearTimeEnum;
//    }
//
//    public int getSex() {
//        return sex;
//    }
//
//    public void setSex(int sex) {
//        this.sex = sex;
//    }
//
//    public boolean isAble() {
//        return able;
//    }
//
//    public void setAble(boolean able) {
//        this.able = able;
//    }
//
//    public Object getRecords() {
//        return records;
//    }
//
//    public void setRecords(Object records) {
//        this.records = records;
//    }
//
//    public String getToken() {
//        return token;
//    }
//
//    public void setToken(String token) {
//        this.token = token;
//    }
//
////    public UserEntity getUser() {
////        return user;
////    }
//
//    public int getCureCountX() {
//        return cureCountX;
//    }
//
//    public void setCureCountX(int cureCountX) {
//        this.cureCountX = cureCountX;
//    }
//
//    public String getRecordsX() {
//        return recordsX;
//    }
//
//    public void setRecordsX(String recordsX) {
//        this.recordsX = recordsX;
//    }
//
//    public String getRefreshToken() {
//        return refreshToken;
//    }
//
//    public void setRefreshToken(String refreshToken) {
//        this.refreshToken = refreshToken;
//    }

