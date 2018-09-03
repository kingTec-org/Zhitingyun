package com.xiyoukeji.zhitingyun.data.entity;

public class AdviceBackEntity {


    /**
     * id : 8
     * personId : 356
     * adminId : null
     * adminAccNum : null
     * status : 2
     * content : wangbangqishabi
     * reply : null
     * classify : 1
     * submitTime : 1532444507301
     * del : false
     */

    private Integer id;
    private Integer personId;
    private Object adminId;
    private Object adminAccNum;
    private Integer status;
    private String content;
    private Object reply;
    private Integer classify;
    private long submitTime;
    private boolean del;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Object getAdminId() {
        return adminId;
    }

    public void setAdminId(Object adminId) {
        this.adminId = adminId;
    }

    public Object getAdminAccNum() {
        return adminAccNum;
    }

    public void setAdminAccNum(Object adminAccNum) {
        this.adminAccNum = adminAccNum;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Object getReply() {
        return reply;
    }

    public void setReply(Object reply) {
        this.reply = reply;
    }

    public int getClassify() {
        return classify;
    }

    public void setClassify(int classify) {
        this.classify = classify;
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
}
