package com.example.zhitingyun.model;

/**
 * Created by dasiy on 2018/8/1.
 */

public class NewsDetail {
    private Integer id;
    private String title;
    private String coverPic;
    private String richText;
    private Long lastPushTime;
    private Boolean pushUser;
    private Boolean pushProfessor;
    private Boolean hadPublish;
    private Boolean del;


    private Integer personId;
    private Integer adminId;
    private String adminAccNum;
    private Integer status;
    private String content;
    private String reply;
    private Integer classify;
    private Long submitTime;


    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAdminAccNum() {
        return adminAccNum;
    }

    public void setAdminAccNum(String adminAccNum) {
        this.adminAccNum = adminAccNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public Integer getClassify() {
        return classify;
    }

    public void setClassify(Integer classify) {
        this.classify = classify;
    }

    public Long getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Long submitTime) {
        this.submitTime = submitTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoverPic() {
        return coverPic;
    }

    public void setCoverPic(String coverPic) {
        this.coverPic = coverPic;
    }

    public String getRichText() {
        return richText;
    }

    public void setRichText(String richText) {
        this.richText = richText;
    }

    public Long getLastPushTime() {
        return lastPushTime;
    }

    public void setLastPushTime(Long lastPushTime) {
        this.lastPushTime = lastPushTime;
    }

    public Boolean getPushUser() {
        return pushUser;
    }

    public void setPushUser(Boolean pushUser) {
        this.pushUser = pushUser;
    }

    public Boolean getPushProfessor() {
        return pushProfessor;
    }

    public void setPushProfessor(Boolean pushProfessor) {
        this.pushProfessor = pushProfessor;
    }

    public Boolean getHadPublish() {
        return hadPublish;
    }

    public void setHadPublish(Boolean hadPublish) {
        this.hadPublish = hadPublish;
    }

    public Boolean getDel() {
        return del;
    }

    public void setDel(Boolean del) {
        this.del = del;
    }
}
