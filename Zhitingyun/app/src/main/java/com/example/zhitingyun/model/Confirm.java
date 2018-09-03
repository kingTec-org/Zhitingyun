package com.example.zhitingyun.model;

/**
 * Created by dasiy on 2018/7/7.
 */

public class Confirm {
    private Integer id;
    private String userName;
    private String professorName;
    private String proHeadPic;
    private Integer proServiceCount;
    private Integer workMonth;
    private Long orderTimeStart;
    private Long orderTimeEnd;
    private Long cureTimeStart;
    private Long cureTimeEnd;
    private Integer status;
    private Boolean returnVisit;
    private Long currentMillion;
    private Integer treatmentType;
    private String userHeadPic;


    private Integer userId;
    private Integer proId;
    private String proName;


//
//    快速诊疗
//
//    "id": 49,
//            "userId": 350,
//            "proId": 9,
//            "userName": "知鱼之乐",
//            "proName": "至于会",
//            "cureTimeStart": 1531474263000,
//            "cureTimeEnd": 1531475463000,
//            "status": 3
//


    public String getUserHeadPic() {
        return userHeadPic;
    }

    public void setUserHeadPic(String userHeadPic) {
        this.userHeadPic = userHeadPic;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }


    public Integer getTreatmentType() {
        return treatmentType;
    }

    public void setTreatmentType(Integer treatmentType) {
        this.treatmentType = treatmentType;
    }

    public Long getCurrentMillion() {
        return currentMillion;
    }

    public void setCurrentMillion(Long currentMillion) {
        this.currentMillion = currentMillion;
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

    public Long getOrderTimeStart() {
        return orderTimeStart;
    }

    public void setOrderTimeStart(Long orderTimeStart) {
        this.orderTimeStart = orderTimeStart;
    }

    public Long getOrderTimeEnd() {
        return orderTimeEnd;
    }

    public void setOrderTimeEnd(Long orderTimeEnd) {
        this.orderTimeEnd = orderTimeEnd;
    }

    public Long getCureTimeStart() {
        return cureTimeStart;
    }

    public void setCureTimeStart(Long cureTimeStart) {
        this.cureTimeStart = cureTimeStart;
    }

    public Long getCureTimeEnd() {
        return cureTimeEnd;
    }

    public void setCureTimeEnd(Long cureTimeEnd) {
        this.cureTimeEnd = cureTimeEnd;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getReturnVisit() {
        return returnVisit;
    }

    public void setReturnVisit(Boolean returnVisit) {
        this.returnVisit = returnVisit;
    }
}
