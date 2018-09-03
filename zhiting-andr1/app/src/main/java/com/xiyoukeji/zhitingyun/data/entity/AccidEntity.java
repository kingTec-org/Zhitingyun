package com.xiyoukeji.zhitingyun.data.entity;

import java.io.Serializable;

public class AccidEntity implements Serializable {


    /**
     * personName : 至于会
     * personId : 9
     * personType : professor
     * personHeadPic : images/admin/153111795753696e243.jpg
     */

    private String personName;
    private Integer personId;
    private String personType;
    private String personHeadPic;

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

    public String getPersonHeadPic() {
        return personHeadPic;
    }

    public void setPersonHeadPic(String personHeadPic) {
        this.personHeadPic = personHeadPic;
    }
}
