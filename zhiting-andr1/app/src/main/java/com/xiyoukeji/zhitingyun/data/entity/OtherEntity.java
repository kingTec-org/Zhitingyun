package com.xiyoukeji.zhitingyun.data.entity;

import java.io.Serializable;

public class OtherEntity implements Serializable {

    /**
     * id : 1
     * serviceTel : 1746829114
     * userAgreement : asgaggqe
     * guides : images/admin/153130070084994b4f9.jpeg,images/admin/1531300734833adad8b.jpeg
     * launcher : images/admin/1531300734833adad8b.jpeg
     */

    private Integer id;
    private String serviceTel;
    private String userAgreement;
    private String guides;
    private String launcher;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServiceTel() {
        return serviceTel;
    }

    public void setServiceTel(String serviceTel) {
        this.serviceTel = serviceTel;
    }

    public String getUserAgreement() {
        return userAgreement;
    }

    public void setUserAgreement(String userAgreement) {
        this.userAgreement = userAgreement;
    }

    public String getGuides() {
        return guides;
    }

    public void setGuides(String guides) {
        this.guides = guides;
    }

    public String getLauncher() {
        return launcher;
    }

    public void setLauncher(String launcher) {
        this.launcher = launcher;
    }
}
