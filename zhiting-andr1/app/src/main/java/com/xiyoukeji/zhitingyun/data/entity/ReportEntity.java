package com.xiyoukeji.zhitingyun.data.entity;

import java.io.Serializable;

public class ReportEntity implements Serializable{

    /**
     * id : 24
     * userDto : {"id":350,"name":"","phone":"13173638162","headPic":"images/user/1530261928947a9e376.jpeg","age":20,"age_1":48,"cureCount":0,"wearTimeEnum":2,"equipmentAddress":null,"sex":1,"able":true,"records":"66","token":null,"refreshToken":null,"imToken":null,"accid":null}
     * professorDto : {"id":3,"age":538761600000,"age_1":31,"serviceCount":0,"name":"我是大法师","phone":"18868128676","headPic":"images/admin/15305974233052fadc9.jpg","sex":1,"able":true,"rate":0,"workTime":2,"canBeenOrder":false,"token":null,"refreshToken":null,"imToken":null,"accid":null}
     * proAdjustListenRecord : null
     * orderTimeStart : 1530933300000
     * orderTimeEnd : 1530936900000
     * cureTimeStart : null
     * cureTimeEnd : null
     * reportTime : null
     * status : 3
     * problemDesc : 我的肚子疼
     * professorReport : null
     * evaluation : null
     * returnVisit : true
     * historyOrderId : null
     * historyOrderType : null
     * userAccid : 261473901c0f6dac
     * proAccid : 11155025a5d7d21c
     * classify : 2
     */

    private int id;
    private UserDtoBean userDto;
    private ProfessorDtoBean professorDto;
    private long orderTimeStart;
    private long orderTimeEnd;
    private long cureTimeStart;
    private long cureTimeEnd;
    private long reportTime;
    private int status;
    private String problemDesc;
    private String professorReport;
    private String evaluation;
    private boolean returnVisit;
    private Integer historyOrderId;
    private Integer historyOrderType;
    private int classify;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserDtoBean getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDtoBean userDto) {
        this.userDto = userDto;
    }

    public ProfessorDtoBean getProfessorDto() {
        return professorDto;
    }

    public void setProfessorDto(ProfessorDtoBean professorDto) {
        this.professorDto = professorDto;
    }

    public long getOrderTimeStart() {
        return orderTimeStart;
    }

    public void setOrderTimeStart(long orderTimeStart) {
        this.orderTimeStart = orderTimeStart;
    }

    public long getOrderTimeEnd() {
        return orderTimeEnd;
    }

    public void setOrderTimeEnd(long orderTimeEnd) {
        this.orderTimeEnd = orderTimeEnd;
    }

    public Object getCureTimeStart() {
        return cureTimeStart;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getProblemDesc() {
        return problemDesc;
    }

    public void setProblemDesc(String problemDesc) {
        this.problemDesc = problemDesc;
    }

    public void setCureTimeStart(long cureTimeStart) {
        this.cureTimeStart = cureTimeStart;
    }

    public long getCureTimeEnd() {
        return cureTimeEnd;
    }

    public void setCureTimeEnd(long cureTimeEnd) {
        this.cureTimeEnd = cureTimeEnd;
    }

    public long getReportTime() {
        return reportTime;
    }

    public void setReportTime(long reportTime) {
        this.reportTime = reportTime;
    }

    public String getProfessorReport() {
        return professorReport;
    }

    public void setProfessorReport(String professorReport) {
        this.professorReport = professorReport;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public void setHistoryOrderId(Integer historyOrderId) {
        this.historyOrderId = historyOrderId;
    }

    public Integer getHistoryOrderType() {
        return historyOrderType;
    }

    public void setHistoryOrderType(Integer historyOrderType) {
        this.historyOrderType = historyOrderType;
    }

    public void setClassify(int classify) {
        this.classify = classify;
    }

    public boolean isReturnVisit() {
        return returnVisit;
    }

    public void setReturnVisit(boolean returnVisit) {
        this.returnVisit = returnVisit;
    }

    public Object getHistoryOrderId() {
        return historyOrderId;
    }

    public Integer getClassify() {
        return classify;
    }

    public void setClassify(Integer classify) {
        this.classify = classify;
    }

    public static class UserDtoBean {
        /**
         * id : 350
         * name :
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

        private int id;
        private String name;
        private String phone;
        private String headPic;
        private int age;
        private int age_1;
        private int cureCount;
        private int wearTimeEnum;
        private Object equipmentAddress;
        private int sex;
        private boolean able;
        private String records;
        private Object token;
        private Object refreshToken;
        private Object imToken;
        private Object accid;

        public int getId() {
            return id;
        }

        public void setId(int id) {
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

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getAge_1() {
            return age_1;
        }

        public void setAge_1(int age_1) {
            this.age_1 = age_1;
        }

        public int getCureCount() {
            return cureCount;
        }

        public void setCureCount(int cureCount) {
            this.cureCount = cureCount;
        }

        public int getWearTimeEnum() {
            return wearTimeEnum;
        }

        public void setWearTimeEnum(int wearTimeEnum) {
            this.wearTimeEnum = wearTimeEnum;
        }

        public Object getEquipmentAddress() {
            return equipmentAddress;
        }

        public void setEquipmentAddress(Object equipmentAddress) {
            this.equipmentAddress = equipmentAddress;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
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

        public Object getToken() {
            return token;
        }

        public void setToken(Object token) {
            this.token = token;
        }

        public Object getRefreshToken() {
            return refreshToken;
        }

        public void setRefreshToken(Object refreshToken) {
            this.refreshToken = refreshToken;
        }

        public Object getImToken() {
            return imToken;
        }

        public void setImToken(Object imToken) {
            this.imToken = imToken;
        }

        public Object getAccid() {
            return accid;
        }

        public void setAccid(Object accid) {
            this.accid = accid;
        }


    }

    public static class ProfessorDtoBean {
        /**
         * id : 3
         * age : 538761600000
         * age_1 : 31
         * serviceCount : 0
         * name : 我是大法师
         * phone : 18868128676
         * headPic : images/admin/15305974233052fadc9.jpg
         * sex : 1
         * able : true
         * rate : 0
         * workTime : 2
         * canBeenOrder : false
         * token : null
         * refreshToken : null
         * imToken : null
         * accid : null
         */

        private int id;
        private long age;
        private int age_1;
        private int serviceCount;
        private String name;
        private String phone;
        private String headPic;
        private int sex;
        private boolean able;
        private int rate;
        private int workTime;
        private boolean canBeenOrder;
        private Object token;
        private Object refreshToken;
        private Object imToken;
        private Object accid;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public long getAge() {
            return age;
        }

        public void setAge(long age) {
            this.age = age;
        }

        public int getAge_1() {
            return age_1;
        }

        public void setAge_1(int age_1) {
            this.age_1 = age_1;
        }

        public int getServiceCount() {
            return serviceCount;
        }

        public void setServiceCount(int serviceCount) {
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

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public boolean isAble() {
            return able;
        }

        public void setAble(boolean able) {
            this.able = able;
        }

        public int getRate() {
            return rate;
        }

        public void setRate(int rate) {
            this.rate = rate;
        }

        public int getWorkTime() {
            return workTime;
        }

        public void setWorkTime(int workTime) {
            this.workTime = workTime;
        }

        public boolean isCanBeenOrder() {
            return canBeenOrder;
        }

        public void setCanBeenOrder(boolean canBeenOrder) {
            this.canBeenOrder = canBeenOrder;
        }

        public Object getToken() {
            return token;
        }

        public void setToken(Object token) {
            this.token = token;
        }

        public Object getRefreshToken() {
            return refreshToken;
        }

        public void setRefreshToken(Object refreshToken) {
            this.refreshToken = refreshToken;
        }

        public Object getImToken() {
            return imToken;
        }

        public void setImToken(Object imToken) {
            this.imToken = imToken;
        }

        public Object getAccid() {
            return accid;
        }

        public void setAccid(Object accid) {
            this.accid = accid;
        }
    }
}
