package com.xiyoukeji.zhitingyun.data.entity;

import java.io.Serializable;
import java.util.List;

public class ExpertSchEntity implements Serializable {
//    private static final long serialVersionUID =1L;

    /**
     * id : 9
     * age : 770745600000
     * age_1 : 24
     * serviceCount : 0
     * name : 至于会
     * phone : 13173638162
     * headPic : images/admin/153111795753696e243.jpg
     * sex : 1
     * workTime : 2
     * canBeenOrder : null
     * workScheduleDtos : [{"id":212,"start":1532147400000,"end":1532151000000,"status":1}]
     */

    private Integer id;
    private long age;
    private Integer age_1;
    private Integer serviceCount;
    private String name;
    private String phone;
    private String headPic;
    private Integer sex;
    private Integer workTime;
    private Object canBeenOrder;
    private List<WorkScheduleDtosBean> workScheduleDtos;

//    public static long getSerialVersionUID() {
//        return serialVersionUID;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public Integer getAge_1() {
        return age_1;
    }

    public void setAge_1(Integer age_1) {
        this.age_1 = age_1;
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

    public Integer getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Integer workTime) {
        this.workTime = workTime;
    }

    public Object getCanBeenOrder() {
        return canBeenOrder;
    }

    public void setCanBeenOrder(Object canBeenOrder) {
        this.canBeenOrder = canBeenOrder;
    }

    public List<WorkScheduleDtosBean> getWorkScheduleDtos() {
        return workScheduleDtos;
    }

    public void setWorkScheduleDtos(List<WorkScheduleDtosBean> workScheduleDtos) {
        this.workScheduleDtos = workScheduleDtos;
    }

    public static class WorkScheduleDtosBean implements Serializable {
        /**
         * id : 212
         * start : 1532147400000
         * end : 1532151000000
         * status : 1
         */

        private Integer id;
        private long start;
        private long end;
        private Integer status;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public long getStart() {
            return start;
        }

        public void setStart(long start) {
            this.start = start;
        }

        public long getEnd() {
            return end;
        }

        public void setEnd(long end) {
            this.end = end;
        }

    }
}
