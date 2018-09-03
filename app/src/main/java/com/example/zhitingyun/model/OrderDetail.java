package com.example.zhitingyun.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dasiy on 2018/7/17.
 */

public class OrderDetail implements Serializable {
    private Integer id;
    private UserInfo userDto;
    private ProfessorInfo professorDto;
    private List<EquipmentParamater> equParamBefore;
    private List<EquipmentParamater> equParamAfter;
    private NewestRecord userNewestRecord;//普通诊疗的测听记录
    private NewestRecord userNewestListenRecord;//快速诊疗的测听记录
    private NewestRecord proAdjustListenRecord;
    private Long orderTimeStart;
    private Long orderTimeEnd;
    private Long cureTimeStart;
    private Long cureTimeEnd;
    private Long reportTime;
    private Integer status;
    private String problemDesc;
    private String professorReport;
    private String evaluation;
    private Boolean returnVisit;
    private Integer historyOrderId;
    private Integer historyOrderType;
    private String userAccid;
    private Integer classify;

    public Integer getClassify() {
        return classify;
    }

    public void setClassify(Integer classify) {
        this.classify = classify;
    }

    public NewestRecord getUserNewestListenRecord() {
        return userNewestListenRecord;
    }

    public void setUserNewestListenRecord(NewestRecord userNewestListenRecord) {
        this.userNewestListenRecord = userNewestListenRecord;
    }

    public String getUserAccid() {
        return userAccid;
    }

    public void setUserAccid(String userAccid) {
        this.userAccid = userAccid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserInfo getUserDto() {
        return userDto;
    }

    public void setUserDto(UserInfo userDto) {
        this.userDto = userDto;
    }

    public ProfessorInfo getProfessorDto() {
        return professorDto;
    }

    public void setProfessorDto(ProfessorInfo professorDto) {
        this.professorDto = professorDto;
    }

    public List<EquipmentParamater> getEquParamBefore() {
        return equParamBefore;
    }

    public void setEquParamBefore(List<EquipmentParamater> equParamBefore) {
        this.equParamBefore = equParamBefore;
    }

    public List<EquipmentParamater> getEquParamAfter() {
        return equParamAfter;
    }

    public void setEquParamAfter(List<EquipmentParamater> equParamAfter) {
        this.equParamAfter = equParamAfter;
    }

    public NewestRecord getUserNewestRecord() {
        return userNewestRecord;
    }

    public void setUserNewestRecord(NewestRecord userNewestRecord) {
        this.userNewestRecord = userNewestRecord;
    }

    public NewestRecord getProAdjustListenRecord() {
        return proAdjustListenRecord;
    }

    public void setProAdjustListenRecord(NewestRecord proAdjustListenRecord) {
        this.proAdjustListenRecord = proAdjustListenRecord;
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

    public Long getReportTime() {
        return reportTime;
    }

    public void setReportTime(Long reportTime) {
        this.reportTime = reportTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public Boolean getReturnVisit() {
        return returnVisit;
    }

    public void setReturnVisit(Boolean returnVisit) {
        this.returnVisit = returnVisit;
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
}
