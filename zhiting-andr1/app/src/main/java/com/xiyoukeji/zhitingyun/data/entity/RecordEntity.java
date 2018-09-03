package com.xiyoukeji.zhitingyun.data.entity;

import java.io.Serializable;

public class RecordEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * id : 94
     * userName : 我是大王
     * professorName : 我是大法师
     * proHeadPic : images/admin/15305974233052fadc9.jpg
     * proServiceCount : 0
     * workMonth : 2
     * orderTimeStart : 1532527200000
     * orderTimeEnd : 1532530800000
     * cureTimeStart : 1532522562684
     * cureTimeEnd : null
     * status : 3
     * returnVisit : true
     * classify : 2
     */

    private Integer id;
    private String userName;
    private String professorName;
    private String proHeadPic;
    private Integer proServiceCount;
    private Integer workMonth;
    private long orderTimeStart;
    private long orderTimeEnd;
    private long cureTimeStart;
    private long cureTimeEnd;
    private int status;
    private boolean returnVisit;
    private Integer classify;
    private String proName;
    private Integer proId;
    private Integer orderId;

    private UserDtoBean userDto;
    private ProfessorDtoBean professorDto;
    private long reportTime;
    private String problemDesc;
    private String professorReport;
    private String evaluation;
    private Integer historyOrderId;
    private Integer historyOrderType;

    private Integer userId;
    private Integer professorId;
    private Integer adminId;
    private String reply;
    private String content;
    private long submitTime;
    private boolean del;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Integer professorId) {
        this.professorId = professorId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(long submitTime) {
        this.submitTime = submitTime;
    }

    public boolean isDel() {
        return del;
    }

    public void setDel(boolean del) {
        this.del = del;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public String getProHeadPic() {
        return proHeadPic;
    }

    public void setProHeadPic(String proHeadPic) {
        this.proHeadPic = proHeadPic;
    }

    public Integer getProServiceCount() {
        return proServiceCount;
    }

    public void setProServiceCount(Integer proServiceCount) {
        this.proServiceCount = proServiceCount;
    }

    public Integer getWorkMonth() {
        return workMonth;
    }

    public void setWorkMonth(Integer workMonth) {
        this.workMonth = workMonth;
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

    public long getCureTimeStart() {
        return cureTimeStart;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isReturnVisit() {
        return returnVisit;
    }

    public void setReturnVisit(boolean returnVisit) {
        this.returnVisit = returnVisit;
    }

    public Integer getClassify() {
        return classify;
    }

    public void setClassify(Integer classify) {
        this.classify = classify;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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

    public long getReportTime() {
        return reportTime;
    }

    public void setReportTime(long reportTime) {
        this.reportTime = reportTime;
    }

    public String getProblemDesc() {
        return problemDesc;
    }

    public void setProblemDesc(String problemDesc) {
        this.problemDesc = problemDesc;
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

    public Integer getHistoryOrderId() {
        return historyOrderId;
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

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
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
        private Double rate;
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

        public Double getRate() {
            return rate;
        }

        public void setRate(Double rate) {
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
