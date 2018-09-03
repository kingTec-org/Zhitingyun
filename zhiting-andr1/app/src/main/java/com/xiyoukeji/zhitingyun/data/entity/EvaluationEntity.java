package com.xiyoukeji.zhitingyun.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EvaluationEntity {
    private Integer orderId;
    private Integer starts;
    private String tags;
    private String evaluation;
    private String name;
    /**
     * personName : (¦3[▓▓]
     * personId : 350
     * personType : user
     */

    private String personName;
    private Integer personId;
    private String personType;
    /**
     * id : 154
     * userDto : {"id":350,"name":"(¦3[▓▓]","phone":"13173638162","headPic":"images/user/15331889332683c3421.jpg","age":4,"age_1":48,"cureCount":0,"wearTimeEnum":3,"equipmentAddress":null,"sex":1,"able":true,"records":"214, 201","token":null,"refreshToken":null,"imToken":null,"accid":null}
     * professorDto : {"id":19,"age":867686400000,"age_1":21,"serviceCount":1,"name":"hjr","phone":"13588189932","headPic":"images/admin/15330008208721d5af3.jpg","sex":2,"able":true,"rate":0.6,"workTime":3,"canBeenOrder":true,"token":null,"refreshToken":null,"imToken":null,"accid":null}
     * userNewestRecord : {"id":214,"userId":350,"equipmentHolder":1,"left_data":"0,0,0,0,0,0,0,0,0,0,0","right_data":"0,0,0,0,0,0,0,0,0,0,0","left_hertz":"125,250,500,750,1000,1500,2000,3000,4000,6000,8000","right_hertz":"125,250,500,750,1000,1500,2000,3000,4000,6000,8000","createTime":1533283631928,"del":false}
     * proAdjustListenRecord : null
     * orderTimeStart : 1533366000000
     * orderTimeEnd : 1533369600000
     * cureTimeStart : 1533283951379
     * cureTimeEnd : 1533284169241
     * reportTime : null
     * status : 4
     * problemDesc :
     * professorReport : markOfProfessor
     * returnVisit : false
     * historyOrderId : 186
     * historyOrderType : 2
     * userAccid : 261473901c0f6dac
     * proAccid : 0868897a5bb13ad8
     * classify : 2
     * equParamBefore : [{"id":2091,"userId":350,"classify":1,"equipmentType":null,"hertz":"125,250,500,750,1000,1500,2000,3000,4000,6000,8000","del":false,"createTime":1533283758285,"chg_DATA":"0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30","l_CR_SELECT":false,"r_CR_SELECT":false,"l_P_NRLVL_VALUE":null,"l_P_AFSLVL_VALUE":null,"r_P_NRLVL_VALUE":null,"r_P_AFSLVL_VALUE":null,"l_SWITCH_P_CH":false,"r_SWITCH_P_CH":false,"r_P_BASS_VALUE":0,"r_P_MID_VALUE":null,"r_P_TRB_VALUE":0,"l_P_BASS_VALUE":3,"l_P_MID_VALUE":null,"l_P_TRB_VALUE":4,"l_P_VC_VALUE":1,"r_P_VC_VALUE":2,"r_P_CHSW":"0,0,0,0,0","l_SWITCH_P_CHEXP":false,"r_SWITCH_P_CHEXP":false,"l_P_CHNG":"0,0,0,0,0","r_P_CHNG":"0,0,0,0,0","l_P_MID":null,"r_P_MID":null,"p_S_DL":null,"l_P_VC":"0,3,6,9,12,15,18,21,24,27,30,33,36,39,42","l_P_BASS":"0,1,2,3,4,5,6","l_P_TRB":"0,1,2,3,4,5,6","l_P_NRLVL":null,"l_P_AFSLVL":null,"l_P_EQ":null,"l_P_CHMPO":"0,0,0,0,0","l_P_CHSW":"0,0,0,0,0","l_P_CHSG":"0,0,0,0,0","l_P_CHLG":"0,0,0,0,0","l_P_CHEXP":"0,0,0,0,0","l_P_CHETH":"0,0,0,0,0","l_P_CHER":"0,0,0,0,0","r_P_VC":"0,3,6,9,12,15,18,21,24,27,30,33,36,39,42","r_P_BASS":"0,1,2,3,4,5,6","r_P_TRB":"0,1,2,3,4,5,6","r_P_NRLVL":null,"r_P_AFSLVL":null,"r_P_EQ":null,"r_P_CHMPO":"0,0,0,0,0","r_P_CHSG":"0,0,0,0,0","r_P_CHLG":"0,0,0,0,0","r_P_CHEXP":"0,0,0,0,0","r_P_CHETH":"0,0,0,0,0","r_P_CHER":"0,0,0,0,0"},{"id":2093,"userId":350,"classify":2,"equipmentType":null,"hertz":"125,250,500,750,1000,1500,2000,3000,4000,6000,8000","del":false,"createTime":1533283758348,"chg_DATA":"0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30","l_CR_SELECT":false,"r_CR_SELECT":false,"l_P_NRLVL_VALUE":null,"l_P_AFSLVL_VALUE":null,"r_P_NRLVL_VALUE":null,"r_P_AFSLVL_VALUE":null,"l_SWITCH_P_CH":false,"r_SWITCH_P_CH":false,"r_P_BASS_VALUE":0,"r_P_MID_VALUE":null,"r_P_TRB_VALUE":0,"l_P_BASS_VALUE":3,"l_P_MID_VALUE":null,"l_P_TRB_VALUE":4,"l_P_VC_VALUE":1,"r_P_VC_VALUE":2,"r_P_CHSW":"0,0,0,0,0","l_SWITCH_P_CHEXP":false,"r_SWITCH_P_CHEXP":false,"l_P_CHNG":"0,0,0,0,0","r_P_CHNG":"0,0,0,0,0","l_P_MID":null,"r_P_MID":null,"p_S_DL":null,"l_P_VC":"0,3,6,9,12,15,18,21,24,27,30,33,36,39,42","l_P_BASS":"0,1,2,3,4,5,6","l_P_TRB":"0,1,2,3,4,5,6","l_P_NRLVL":null,"l_P_AFSLVL":null,"l_P_EQ":null,"l_P_CHMPO":"0,0,0,0,0","l_P_CHSW":"0,0,0,0,0","l_P_CHSG":"0,0,0,0,0","l_P_CHLG":"0,0,0,0,0","l_P_CHEXP":"0,0,0,0,0","l_P_CHETH":"0,0,0,0,0","l_P_CHER":"0,0,0,0,0","r_P_VC":"0,3,6,9,12,15,18,21,24,27,30,33,36,39,42","r_P_BASS":"0,1,2,3,4,5,6","r_P_TRB":"0,1,2,3,4,5,6","r_P_NRLVL":null,"r_P_AFSLVL":null,"r_P_EQ":null,"r_P_CHMPO":"0,0,0,0,0","r_P_CHSG":"0,0,0,0,0","r_P_CHLG":"0,0,0,0,0","r_P_CHEXP":"0,0,0,0,0","r_P_CHETH":"0,0,0,0,0","r_P_CHER":"0,0,0,0,0"},{"id":2094,"userId":350,"classify":3,"equipmentType":null,"hertz":"125,250,500,750,1000,1500,2000,3000,4000,6000,8000","del":false,"createTime":1533283758348,"chg_DATA":"0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30","l_CR_SELECT":false,"r_CR_SELECT":false,"l_P_NRLVL_VALUE":null,"l_P_AFSLVL_VALUE":null,"r_P_NRLVL_VALUE":null,"r_P_AFSLVL_VALUE":null,"l_SWITCH_P_CH":false,"r_SWITCH_P_CH":false,"r_P_BASS_VALUE":0,"r_P_MID_VALUE":null,"r_P_TRB_VALUE":0,"l_P_BASS_VALUE":3,"l_P_MID_VALUE":null,"l_P_TRB_VALUE":4,"l_P_VC_VALUE":1,"r_P_VC_VALUE":2,"r_P_CHSW":"0,0,0,0,0","l_SWITCH_P_CHEXP":false,"r_SWITCH_P_CHEXP":false,"l_P_CHNG":"0,0,0,0,0","r_P_CHNG":"0,0,0,0,0","l_P_MID":null,"r_P_MID":null,"p_S_DL":null,"l_P_VC":"0,3,6,9,12,15,18,21,24,27,30,33,36,39,42","l_P_BASS":"0,1,2,3,4,5,6","l_P_TRB":"0,1,2,3,4,5,6","l_P_NRLVL":null,"l_P_AFSLVL":null,"l_P_EQ":null,"l_P_CHMPO":"0,0,0,0,0","l_P_CHSW":"0,0,0,0,0","l_P_CHSG":"0,0,0,0,0","l_P_CHLG":"0,0,0,0,0","l_P_CHEXP":"0,0,0,0,0","l_P_CHETH":"0,0,0,0,0","l_P_CHER":"0,0,0,0,0","r_P_VC":"0,3,6,9,12,15,18,21,24,27,30,33,36,39,42","r_P_BASS":"0,1,2,3,4,5,6","r_P_TRB":"0,1,2,3,4,5,6","r_P_NRLVL":null,"r_P_AFSLVL":null,"r_P_EQ":null,"r_P_CHMPO":"0,0,0,0,0","r_P_CHSG":"0,0,0,0,0","r_P_CHLG":"0,0,0,0,0","r_P_CHEXP":"0,0,0,0,0","r_P_CHETH":"0,0,0,0,0","r_P_CHER":"0,0,0,0,0"},{"id":2092,"userId":350,"classify":4,"equipmentType":null,"hertz":"125,250,500,750,1000,1500,2000,3000,4000,6000,8000","del":false,"createTime":1533283758345,"chg_DATA":"0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30","l_CR_SELECT":false,"r_CR_SELECT":false,"l_P_NRLVL_VALUE":null,"l_P_AFSLVL_VALUE":null,"r_P_NRLVL_VALUE":null,"r_P_AFSLVL_VALUE":null,"l_SWITCH_P_CH":false,"r_SWITCH_P_CH":false,"r_P_BASS_VALUE":0,"r_P_MID_VALUE":null,"r_P_TRB_VALUE":0,"l_P_BASS_VALUE":3,"l_P_MID_VALUE":null,"l_P_TRB_VALUE":4,"l_P_VC_VALUE":1,"r_P_VC_VALUE":2,"r_P_CHSW":"0,0,0,0,0","l_SWITCH_P_CHEXP":false,"r_SWITCH_P_CHEXP":false,"l_P_CHNG":"0,0,0,0,0","r_P_CHNG":"0,0,0,0,0","l_P_MID":null,"r_P_MID":null,"p_S_DL":null,"l_P_VC":"0,3,6,9,12,15,18,21,24,27,30,33,36,39,42","l_P_BASS":"0,1,2,3,4,5,6","l_P_TRB":"0,1,2,3,4,5,6","l_P_NRLVL":null,"l_P_AFSLVL":null,"l_P_EQ":null,"l_P_CHMPO":"0,0,0,0,0","l_P_CHSW":"0,0,0,0,0","l_P_CHSG":"0,0,0,0,0","l_P_CHLG":"0,0,0,0,0","l_P_CHEXP":"0,0,0,0,0","l_P_CHETH":"0,0,0,0,0","l_P_CHER":"0,0,0,0,0","r_P_VC":"0,3,6,9,12,15,18,21,24,27,30,33,36,39,42","r_P_BASS":"0,1,2,3,4,5,6","r_P_TRB":"0,1,2,3,4,5,6","r_P_NRLVL":null,"r_P_AFSLVL":null,"r_P_EQ":null,"r_P_CHMPO":"0,0,0,0,0","r_P_CHSG":"0,0,0,0,0","r_P_CHLG":"0,0,0,0,0","r_P_CHEXP":"0,0,0,0,0","r_P_CHETH":"0,0,0,0,0","r_P_CHER":"0,0,0,0,0"}]
     * equParamAfter : []
     */

    private int id;
    private UserDtoBean userDto;
    private ProfessorDtoBean professorDto;
    private UserNewestRecordBean userNewestRecord;
    private Object proAdjustListenRecord;
    private long orderTimeStart;
    private long orderTimeEnd;
    private long cureTimeStart;
    private long cureTimeEnd;
    private Object reportTime;
    private int status;
    private String problemDesc;
    private String professorReport;
    private boolean returnVisit;
    private int historyOrderId;
    private int historyOrderType;
    private String userAccid;
    private String proAccid;
    private int classify;
    private List<EquParamBeforeBean> equParamBefore;
    private List<?> equParamAfter;


    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getStarts() {
        return starts;
    }

    public void setStarts(Integer starts) {
        this.starts = starts;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

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

    public UserNewestRecordBean getUserNewestRecord() {
        return userNewestRecord;
    }

    public void setUserNewestRecord(UserNewestRecordBean userNewestRecord) {
        this.userNewestRecord = userNewestRecord;
    }

    public Object getProAdjustListenRecord() {
        return proAdjustListenRecord;
    }

    public void setProAdjustListenRecord(Object proAdjustListenRecord) {
        this.proAdjustListenRecord = proAdjustListenRecord;
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

    public Object getReportTime() {
        return reportTime;
    }

    public void setReportTime(Object reportTime) {
        this.reportTime = reportTime;
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

    public String getProfessorReport() {
        return professorReport;
    }

    public void setProfessorReport(String professorReport) {
        this.professorReport = professorReport;
    }

    public boolean isReturnVisit() {
        return returnVisit;
    }

    public void setReturnVisit(boolean returnVisit) {
        this.returnVisit = returnVisit;
    }

    public int getHistoryOrderId() {
        return historyOrderId;
    }

    public void setHistoryOrderId(int historyOrderId) {
        this.historyOrderId = historyOrderId;
    }

    public int getHistoryOrderType() {
        return historyOrderType;
    }

    public void setHistoryOrderType(int historyOrderType) {
        this.historyOrderType = historyOrderType;
    }

    public String getUserAccid() {
        return userAccid;
    }

    public void setUserAccid(String userAccid) {
        this.userAccid = userAccid;
    }

    public String getProAccid() {
        return proAccid;
    }

    public void setProAccid(String proAccid) {
        this.proAccid = proAccid;
    }

    public int getClassify() {
        return classify;
    }

    public void setClassify(int classify) {
        this.classify = classify;
    }

    public List<EquParamBeforeBean> getEquParamBefore() {
        return equParamBefore;
    }

    public void setEquParamBefore(List<EquParamBeforeBean> equParamBefore) {
        this.equParamBefore = equParamBefore;
    }

    public List<?> getEquParamAfter() {
        return equParamAfter;
    }

    public void setEquParamAfter(List<?> equParamAfter) {
        this.equParamAfter = equParamAfter;
    }

    public static class UserDtoBean {
        /**
         * id : 350
         * name : (¦3[▓▓]
         * phone : 13173638162
         * headPic : images/user/15331889332683c3421.jpg
         * age : 4
         * age_1 : 48
         * cureCount : 0
         * wearTimeEnum : 3
         * equipmentAddress : null
         * sex : 1
         * able : true
         * records : 214, 201
         * token : null
         * refreshToken : null
         * imToken : null
         * accid : null
         */

        private int id;
        @SerializedName("name")
        private String nameX;
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

        public String getNameX() {
            return nameX;
        }

        public void setNameX(String nameX) {
            this.nameX = nameX;
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
         * id : 19
         * age : 867686400000
         * age_1 : 21
         * serviceCount : 1
         * name : hjr
         * phone : 13588189932
         * headPic : images/admin/15330008208721d5af3.jpg
         * sex : 2
         * able : true
         * rate : 0.6
         * workTime : 3
         * canBeenOrder : true
         * token : null
         * refreshToken : null
         * imToken : null
         * accid : null
         */

        private int id;
        private long age;
        private int age_1;
        private int serviceCount;
        @SerializedName("name")
        private String nameX;
        private String phone;
        private String headPic;
        private int sex;
        private boolean able;
        private double rate;
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

        public String getNameX() {
            return nameX;
        }

        public void setNameX(String nameX) {
            this.nameX = nameX;
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

        public double getRate() {
            return rate;
        }

        public void setRate(double rate) {
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

    public static class UserNewestRecordBean {
        /**
         * id : 214
         * userId : 350
         * equipmentHolder : 1
         * left_data : 0,0,0,0,0,0,0,0,0,0,0
         * right_data : 0,0,0,0,0,0,0,0,0,0,0
         * left_hertz : 125,250,500,750,1000,1500,2000,3000,4000,6000,8000
         * right_hertz : 125,250,500,750,1000,1500,2000,3000,4000,6000,8000
         * createTime : 1533283631928
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

    public static class EquParamBeforeBean {
        /**
         * id : 2091
         * userId : 350
         * classify : 1
         * equipmentType : null
         * hertz : 125,250,500,750,1000,1500,2000,3000,4000,6000,8000
         * del : false
         * createTime : 1533283758285
         * chg_DATA : 0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30
         * l_CR_SELECT : false
         * r_CR_SELECT : false
         * l_P_NRLVL_VALUE : null
         * l_P_AFSLVL_VALUE : null
         * r_P_NRLVL_VALUE : null
         * r_P_AFSLVL_VALUE : null
         * l_SWITCH_P_CH : false
         * r_SWITCH_P_CH : false
         * r_P_BASS_VALUE : 0
         * r_P_MID_VALUE : null
         * r_P_TRB_VALUE : 0
         * l_P_BASS_VALUE : 3
         * l_P_MID_VALUE : null
         * l_P_TRB_VALUE : 4
         * l_P_VC_VALUE : 1
         * r_P_VC_VALUE : 2
         * r_P_CHSW : 0,0,0,0,0
         * l_SWITCH_P_CHEXP : false
         * r_SWITCH_P_CHEXP : false
         * l_P_CHNG : 0,0,0,0,0
         * r_P_CHNG : 0,0,0,0,0
         * l_P_MID : null
         * r_P_MID : null
         * p_S_DL : null
         * l_P_VC : 0,3,6,9,12,15,18,21,24,27,30,33,36,39,42
         * l_P_BASS : 0,1,2,3,4,5,6
         * l_P_TRB : 0,1,2,3,4,5,6
         * l_P_NRLVL : null
         * l_P_AFSLVL : null
         * l_P_EQ : null
         * l_P_CHMPO : 0,0,0,0,0
         * l_P_CHSW : 0,0,0,0,0
         * l_P_CHSG : 0,0,0,0,0
         * l_P_CHLG : 0,0,0,0,0
         * l_P_CHEXP : 0,0,0,0,0
         * l_P_CHETH : 0,0,0,0,0
         * l_P_CHER : 0,0,0,0,0
         * r_P_VC : 0,3,6,9,12,15,18,21,24,27,30,33,36,39,42
         * r_P_BASS : 0,1,2,3,4,5,6
         * r_P_TRB : 0,1,2,3,4,5,6
         * r_P_NRLVL : null
         * r_P_AFSLVL : null
         * r_P_EQ : null
         * r_P_CHMPO : 0,0,0,0,0
         * r_P_CHSG : 0,0,0,0,0
         * r_P_CHLG : 0,0,0,0,0
         * r_P_CHEXP : 0,0,0,0,0
         * r_P_CHETH : 0,0,0,0,0
         * r_P_CHER : 0,0,0,0,0
         */

        private int id;
        private int userId;
        private int classify;
        private Object equipmentType;
        private String hertz;
        private boolean del;
        private long createTime;
        private String chg_DATA;
        private boolean l_CR_SELECT;
        private boolean r_CR_SELECT;
        private Object l_P_NRLVL_VALUE;
        private Object l_P_AFSLVL_VALUE;
        private Object r_P_NRLVL_VALUE;
        private Object r_P_AFSLVL_VALUE;
        private boolean l_SWITCH_P_CH;
        private boolean r_SWITCH_P_CH;
        private int r_P_BASS_VALUE;
        private Object r_P_MID_VALUE;
        private int r_P_TRB_VALUE;
        private int l_P_BASS_VALUE;
        private Object l_P_MID_VALUE;
        private int l_P_TRB_VALUE;
        private int l_P_VC_VALUE;
        private int r_P_VC_VALUE;
        private String r_P_CHSW;
        private boolean l_SWITCH_P_CHEXP;
        private boolean r_SWITCH_P_CHEXP;
        private String l_P_CHNG;
        private String r_P_CHNG;
        private Object l_P_MID;
        private Object r_P_MID;
        private Object p_S_DL;
        private String l_P_VC;
        private String l_P_BASS;
        private String l_P_TRB;
        private Object l_P_NRLVL;
        private Object l_P_AFSLVL;
        private Object l_P_EQ;
        private String l_P_CHMPO;
        private String l_P_CHSW;
        private String l_P_CHSG;
        private String l_P_CHLG;
        private String l_P_CHEXP;
        private String l_P_CHETH;
        private String l_P_CHER;
        private String r_P_VC;
        private String r_P_BASS;
        private String r_P_TRB;
        private Object r_P_NRLVL;
        private Object r_P_AFSLVL;
        private Object r_P_EQ;
        private String r_P_CHMPO;
        private String r_P_CHSG;
        private String r_P_CHLG;
        private String r_P_CHEXP;
        private String r_P_CHETH;
        private String r_P_CHER;

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

        public int getClassify() {
            return classify;
        }

        public void setClassify(int classify) {
            this.classify = classify;
        }

        public Object getEquipmentType() {
            return equipmentType;
        }

        public void setEquipmentType(Object equipmentType) {
            this.equipmentType = equipmentType;
        }

        public String getHertz() {
            return hertz;
        }

        public void setHertz(String hertz) {
            this.hertz = hertz;
        }

        public boolean isDel() {
            return del;
        }

        public void setDel(boolean del) {
            this.del = del;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getChg_DATA() {
            return chg_DATA;
        }

        public void setChg_DATA(String chg_DATA) {
            this.chg_DATA = chg_DATA;
        }

        public boolean isL_CR_SELECT() {
            return l_CR_SELECT;
        }

        public void setL_CR_SELECT(boolean l_CR_SELECT) {
            this.l_CR_SELECT = l_CR_SELECT;
        }

        public boolean isR_CR_SELECT() {
            return r_CR_SELECT;
        }

        public void setR_CR_SELECT(boolean r_CR_SELECT) {
            this.r_CR_SELECT = r_CR_SELECT;
        }

        public Object getL_P_NRLVL_VALUE() {
            return l_P_NRLVL_VALUE;
        }

        public void setL_P_NRLVL_VALUE(Object l_P_NRLVL_VALUE) {
            this.l_P_NRLVL_VALUE = l_P_NRLVL_VALUE;
        }

        public Object getL_P_AFSLVL_VALUE() {
            return l_P_AFSLVL_VALUE;
        }

        public void setL_P_AFSLVL_VALUE(Object l_P_AFSLVL_VALUE) {
            this.l_P_AFSLVL_VALUE = l_P_AFSLVL_VALUE;
        }

        public Object getR_P_NRLVL_VALUE() {
            return r_P_NRLVL_VALUE;
        }

        public void setR_P_NRLVL_VALUE(Object r_P_NRLVL_VALUE) {
            this.r_P_NRLVL_VALUE = r_P_NRLVL_VALUE;
        }

        public Object getR_P_AFSLVL_VALUE() {
            return r_P_AFSLVL_VALUE;
        }

        public void setR_P_AFSLVL_VALUE(Object r_P_AFSLVL_VALUE) {
            this.r_P_AFSLVL_VALUE = r_P_AFSLVL_VALUE;
        }

        public boolean isL_SWITCH_P_CH() {
            return l_SWITCH_P_CH;
        }

        public void setL_SWITCH_P_CH(boolean l_SWITCH_P_CH) {
            this.l_SWITCH_P_CH = l_SWITCH_P_CH;
        }

        public boolean isR_SWITCH_P_CH() {
            return r_SWITCH_P_CH;
        }

        public void setR_SWITCH_P_CH(boolean r_SWITCH_P_CH) {
            this.r_SWITCH_P_CH = r_SWITCH_P_CH;
        }

        public int getR_P_BASS_VALUE() {
            return r_P_BASS_VALUE;
        }

        public void setR_P_BASS_VALUE(int r_P_BASS_VALUE) {
            this.r_P_BASS_VALUE = r_P_BASS_VALUE;
        }

        public Object getR_P_MID_VALUE() {
            return r_P_MID_VALUE;
        }

        public void setR_P_MID_VALUE(Object r_P_MID_VALUE) {
            this.r_P_MID_VALUE = r_P_MID_VALUE;
        }

        public int getR_P_TRB_VALUE() {
            return r_P_TRB_VALUE;
        }

        public void setR_P_TRB_VALUE(int r_P_TRB_VALUE) {
            this.r_P_TRB_VALUE = r_P_TRB_VALUE;
        }

        public int getL_P_BASS_VALUE() {
            return l_P_BASS_VALUE;
        }

        public void setL_P_BASS_VALUE(int l_P_BASS_VALUE) {
            this.l_P_BASS_VALUE = l_P_BASS_VALUE;
        }

        public Object getL_P_MID_VALUE() {
            return l_P_MID_VALUE;
        }

        public void setL_P_MID_VALUE(Object l_P_MID_VALUE) {
            this.l_P_MID_VALUE = l_P_MID_VALUE;
        }

        public int getL_P_TRB_VALUE() {
            return l_P_TRB_VALUE;
        }

        public void setL_P_TRB_VALUE(int l_P_TRB_VALUE) {
            this.l_P_TRB_VALUE = l_P_TRB_VALUE;
        }

        public int getL_P_VC_VALUE() {
            return l_P_VC_VALUE;
        }

        public void setL_P_VC_VALUE(int l_P_VC_VALUE) {
            this.l_P_VC_VALUE = l_P_VC_VALUE;
        }

        public int getR_P_VC_VALUE() {
            return r_P_VC_VALUE;
        }

        public void setR_P_VC_VALUE(int r_P_VC_VALUE) {
            this.r_P_VC_VALUE = r_P_VC_VALUE;
        }

        public String getR_P_CHSW() {
            return r_P_CHSW;
        }

        public void setR_P_CHSW(String r_P_CHSW) {
            this.r_P_CHSW = r_P_CHSW;
        }

        public boolean isL_SWITCH_P_CHEXP() {
            return l_SWITCH_P_CHEXP;
        }

        public void setL_SWITCH_P_CHEXP(boolean l_SWITCH_P_CHEXP) {
            this.l_SWITCH_P_CHEXP = l_SWITCH_P_CHEXP;
        }

        public boolean isR_SWITCH_P_CHEXP() {
            return r_SWITCH_P_CHEXP;
        }

        public void setR_SWITCH_P_CHEXP(boolean r_SWITCH_P_CHEXP) {
            this.r_SWITCH_P_CHEXP = r_SWITCH_P_CHEXP;
        }

        public String getL_P_CHNG() {
            return l_P_CHNG;
        }

        public void setL_P_CHNG(String l_P_CHNG) {
            this.l_P_CHNG = l_P_CHNG;
        }

        public String getR_P_CHNG() {
            return r_P_CHNG;
        }

        public void setR_P_CHNG(String r_P_CHNG) {
            this.r_P_CHNG = r_P_CHNG;
        }

        public Object getL_P_MID() {
            return l_P_MID;
        }

        public void setL_P_MID(Object l_P_MID) {
            this.l_P_MID = l_P_MID;
        }

        public Object getR_P_MID() {
            return r_P_MID;
        }

        public void setR_P_MID(Object r_P_MID) {
            this.r_P_MID = r_P_MID;
        }

        public Object getP_S_DL() {
            return p_S_DL;
        }

        public void setP_S_DL(Object p_S_DL) {
            this.p_S_DL = p_S_DL;
        }

        public String getL_P_VC() {
            return l_P_VC;
        }

        public void setL_P_VC(String l_P_VC) {
            this.l_P_VC = l_P_VC;
        }

        public String getL_P_BASS() {
            return l_P_BASS;
        }

        public void setL_P_BASS(String l_P_BASS) {
            this.l_P_BASS = l_P_BASS;
        }

        public String getL_P_TRB() {
            return l_P_TRB;
        }

        public void setL_P_TRB(String l_P_TRB) {
            this.l_P_TRB = l_P_TRB;
        }

        public Object getL_P_NRLVL() {
            return l_P_NRLVL;
        }

        public void setL_P_NRLVL(Object l_P_NRLVL) {
            this.l_P_NRLVL = l_P_NRLVL;
        }

        public Object getL_P_AFSLVL() {
            return l_P_AFSLVL;
        }

        public void setL_P_AFSLVL(Object l_P_AFSLVL) {
            this.l_P_AFSLVL = l_P_AFSLVL;
        }

        public Object getL_P_EQ() {
            return l_P_EQ;
        }

        public void setL_P_EQ(Object l_P_EQ) {
            this.l_P_EQ = l_P_EQ;
        }

        public String getL_P_CHMPO() {
            return l_P_CHMPO;
        }

        public void setL_P_CHMPO(String l_P_CHMPO) {
            this.l_P_CHMPO = l_P_CHMPO;
        }

        public String getL_P_CHSW() {
            return l_P_CHSW;
        }

        public void setL_P_CHSW(String l_P_CHSW) {
            this.l_P_CHSW = l_P_CHSW;
        }

        public String getL_P_CHSG() {
            return l_P_CHSG;
        }

        public void setL_P_CHSG(String l_P_CHSG) {
            this.l_P_CHSG = l_P_CHSG;
        }

        public String getL_P_CHLG() {
            return l_P_CHLG;
        }

        public void setL_P_CHLG(String l_P_CHLG) {
            this.l_P_CHLG = l_P_CHLG;
        }

        public String getL_P_CHEXP() {
            return l_P_CHEXP;
        }

        public void setL_P_CHEXP(String l_P_CHEXP) {
            this.l_P_CHEXP = l_P_CHEXP;
        }

        public String getL_P_CHETH() {
            return l_P_CHETH;
        }

        public void setL_P_CHETH(String l_P_CHETH) {
            this.l_P_CHETH = l_P_CHETH;
        }

        public String getL_P_CHER() {
            return l_P_CHER;
        }

        public void setL_P_CHER(String l_P_CHER) {
            this.l_P_CHER = l_P_CHER;
        }

        public String getR_P_VC() {
            return r_P_VC;
        }

        public void setR_P_VC(String r_P_VC) {
            this.r_P_VC = r_P_VC;
        }

        public String getR_P_BASS() {
            return r_P_BASS;
        }

        public void setR_P_BASS(String r_P_BASS) {
            this.r_P_BASS = r_P_BASS;
        }

        public String getR_P_TRB() {
            return r_P_TRB;
        }

        public void setR_P_TRB(String r_P_TRB) {
            this.r_P_TRB = r_P_TRB;
        }

        public Object getR_P_NRLVL() {
            return r_P_NRLVL;
        }

        public void setR_P_NRLVL(Object r_P_NRLVL) {
            this.r_P_NRLVL = r_P_NRLVL;
        }

        public Object getR_P_AFSLVL() {
            return r_P_AFSLVL;
        }

        public void setR_P_AFSLVL(Object r_P_AFSLVL) {
            this.r_P_AFSLVL = r_P_AFSLVL;
        }

        public Object getR_P_EQ() {
            return r_P_EQ;
        }

        public void setR_P_EQ(Object r_P_EQ) {
            this.r_P_EQ = r_P_EQ;
        }

        public String getR_P_CHMPO() {
            return r_P_CHMPO;
        }

        public void setR_P_CHMPO(String r_P_CHMPO) {
            this.r_P_CHMPO = r_P_CHMPO;
        }

        public String getR_P_CHSG() {
            return r_P_CHSG;
        }

        public void setR_P_CHSG(String r_P_CHSG) {
            this.r_P_CHSG = r_P_CHSG;
        }

        public String getR_P_CHLG() {
            return r_P_CHLG;
        }

        public void setR_P_CHLG(String r_P_CHLG) {
            this.r_P_CHLG = r_P_CHLG;
        }

        public String getR_P_CHEXP() {
            return r_P_CHEXP;
        }

        public void setR_P_CHEXP(String r_P_CHEXP) {
            this.r_P_CHEXP = r_P_CHEXP;
        }

        public String getR_P_CHETH() {
            return r_P_CHETH;
        }

        public void setR_P_CHETH(String r_P_CHETH) {
            this.r_P_CHETH = r_P_CHETH;
        }

        public String getR_P_CHER() {
            return r_P_CHER;
        }

        public void setR_P_CHER(String r_P_CHER) {
            this.r_P_CHER = r_P_CHER;
        }
    }
}
