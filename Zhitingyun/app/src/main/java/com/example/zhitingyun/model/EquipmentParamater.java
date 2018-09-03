package com.example.zhitingyun.model;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by dasiy on 2018/7/17.
 */

public class EquipmentParamater extends RealmObject implements Serializable {
    private Integer id;
    private Integer userId;
    private Integer classify;
    private String equipmentType;
    private String hertz;
    private Boolean del;
    private Long createTime;
    private Integer l_P_NRLVL_VALUE;//降噪等级下标
    private Integer r_P_NRLVL_VALUE;
    private Integer l_P_AFSLVL_VALUE;//反馈抑制等级下标
    private Integer r_P_AFSLVL_VALUE;
    private Integer l_P_BASS_VALUE;//低频增益下标
    private Integer r_P_BASS_VALUE;
    private Integer l_P_TRB_VALUE;//高频增益下标
    private Integer r_P_TRB_VALUE;
    private Integer l_P_VC_VALUE;//音量下标
    private Integer r_P_VC_VALUE;


    private Boolean l_SWITCH_P_CH;//压缩开关
    private Boolean r_SWITCH_P_CH;
    private Boolean l_SWITCH_P_CHEXP;//扩展开关
    private Boolean r_SWITCH_P_CHEXP;


    private String chg_DATA;//通道压缩小中大声增益数值List
    private String p_S_DL;//开机延迟时间


    private Boolean l_CR_SELECT;//小中大声 or 压缩比
    private Boolean r_CR_SELECT;

    private String l_P_CHSW;//通道压缩开启开关列表
    private String r_P_CHSW;
    private String l_P_CHNG;//中声增益下标数组
    private String r_P_CHNG;
    private String l_P_CHSG;//小声增益下标数组
    private String r_P_CHSG;
    private String l_P_CHLG;//大声增益下标数组
    private String r_P_CHLG;

    private String l_P_VC;//音量数组
    private String r_P_VC;
    private String l_P_BASS;//低频增益数组
    private String r_P_BASS;
    private String l_P_TRB;//高频增益数组
    private String r_P_TRB;
    private String l_P_NRLVL;//降噪等级数组
    private String r_P_NRLVL;
    private String l_P_AFSLVL;//声反馈抑制等级数组
    private String r_P_AFSLVL;
    private String l_P_CHMPO;//MPO
    private String r_P_CHMPO;
    private String l_P_CHEXP;//通道压缩扩展数组
    private String r_P_CHEXP;
    private String l_P_CHETH;//通道压缩扩展拐点数组
    private String r_P_CHETH;
    private String l_P_CHER;//通道压缩扩展比数组
    private String r_P_CHER;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getClassify() {
        return classify;
    }

    public void setClassify(Integer classify) {
        this.classify = classify;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getHertz() {
        return hertz;
    }

    public void setHertz(String hertz) {
        this.hertz = hertz;
    }

    public Boolean getDel() {
        return del;
    }

    public void setDel(Boolean del) {
        this.del = del;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Integer getL_P_NRLVL_VALUE() {
        return l_P_NRLVL_VALUE;
    }

    public void setL_P_NRLVL_VALUE(Integer l_P_NRLVL_VALUE) {
        this.l_P_NRLVL_VALUE = l_P_NRLVL_VALUE;
    }

    public Integer getR_P_NRLVL_VALUE() {
        return r_P_NRLVL_VALUE;
    }

    public void setR_P_NRLVL_VALUE(Integer r_P_NRLVL_VALUE) {
        this.r_P_NRLVL_VALUE = r_P_NRLVL_VALUE;
    }

    public Integer getL_P_AFSLVL_VALUE() {
        return l_P_AFSLVL_VALUE;
    }

    public void setL_P_AFSLVL_VALUE(Integer l_P_AFSLVL_VALUE) {
        this.l_P_AFSLVL_VALUE = l_P_AFSLVL_VALUE;
    }

    public Integer getR_P_AFSLVL_VALUE() {
        return r_P_AFSLVL_VALUE;
    }

    public void setR_P_AFSLVL_VALUE(Integer r_P_AFSLVL_VALUE) {
        this.r_P_AFSLVL_VALUE = r_P_AFSLVL_VALUE;
    }

    public Integer getL_P_BASS_VALUE() {
        return l_P_BASS_VALUE;
    }

    public void setL_P_BASS_VALUE(Integer l_P_BASS_VALUE) {
        this.l_P_BASS_VALUE = l_P_BASS_VALUE;
    }

    public Integer getR_P_BASS_VALUE() {
        return r_P_BASS_VALUE;
    }

    public void setR_P_BASS_VALUE(Integer r_P_BASS_VALUE) {
        this.r_P_BASS_VALUE = r_P_BASS_VALUE;
    }

    public Integer getL_P_TRB_VALUE() {
        return l_P_TRB_VALUE;
    }

    public void setL_P_TRB_VALUE(Integer l_P_TRB_VALUE) {
        this.l_P_TRB_VALUE = l_P_TRB_VALUE;
    }

    public Integer getR_P_TRB_VALUE() {
        return r_P_TRB_VALUE;
    }

    public void setR_P_TRB_VALUE(Integer r_P_TRB_VALUE) {
        this.r_P_TRB_VALUE = r_P_TRB_VALUE;
    }

    public Integer getL_P_VC_VALUE() {
        return l_P_VC_VALUE;
    }

    public void setL_P_VC_VALUE(Integer l_P_VC_VALUE) {
        this.l_P_VC_VALUE = l_P_VC_VALUE;
    }

    public Integer getR_P_VC_VALUE() {
        return r_P_VC_VALUE;
    }

    public void setR_P_VC_VALUE(Integer r_P_VC_VALUE) {
        this.r_P_VC_VALUE = r_P_VC_VALUE;
    }

    public Boolean getL_SWITCH_P_CH() {
        return l_SWITCH_P_CH;
    }

    public void setL_SWITCH_P_CH(Boolean l_SWITCH_P_CH) {
        this.l_SWITCH_P_CH = l_SWITCH_P_CH;
    }

    public Boolean getR_SWITCH_P_CH() {
        return r_SWITCH_P_CH;
    }

    public void setR_SWITCH_P_CH(Boolean r_SWITCH_P_CH) {
        this.r_SWITCH_P_CH = r_SWITCH_P_CH;
    }

    public Boolean getL_SWITCH_P_CHEXP() {
        return l_SWITCH_P_CHEXP;
    }

    public void setL_SWITCH_P_CHEXP(Boolean l_SWITCH_P_CHEXP) {
        this.l_SWITCH_P_CHEXP = l_SWITCH_P_CHEXP;
    }

    public Boolean getR_SWITCH_P_CHEXP() {
        return r_SWITCH_P_CHEXP;
    }

    public void setR_SWITCH_P_CHEXP(Boolean r_SWITCH_P_CHEXP) {
        this.r_SWITCH_P_CHEXP = r_SWITCH_P_CHEXP;
    }

    public String getChg_DATA() {
        return chg_DATA;
    }

    public void setChg_DATA(String chg_DATA) {
        this.chg_DATA = chg_DATA;
    }

    public String getP_S_DL() {
        return p_S_DL;
    }

    public void setP_S_DL(String p_S_DL) {
        this.p_S_DL = p_S_DL;
    }

    public Boolean getL_CR_SELECT() {
        return l_CR_SELECT;
    }

    public void setL_CR_SELECT(Boolean l_CR_SELECT) {
        this.l_CR_SELECT = l_CR_SELECT;
    }

    public Boolean getR_CR_SELECT() {
        return r_CR_SELECT;
    }

    public void setR_CR_SELECT(Boolean r_CR_SELECT) {
        this.r_CR_SELECT = r_CR_SELECT;
    }

    public String getL_P_CHSW() {
        return l_P_CHSW;
    }

    public void setL_P_CHSW(String l_P_CHSW) {
        this.l_P_CHSW = l_P_CHSW;
    }

    public String getR_P_CHSW() {
        return r_P_CHSW;
    }

    public void setR_P_CHSW(String r_P_CHSW) {
        this.r_P_CHSW = r_P_CHSW;
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

    public String getL_P_CHSG() {
        return l_P_CHSG;
    }

    public void setL_P_CHSG(String l_P_CHSG) {
        this.l_P_CHSG = l_P_CHSG;
    }

    public String getR_P_CHSG() {
        return r_P_CHSG;
    }

    public void setR_P_CHSG(String r_P_CHSG) {
        this.r_P_CHSG = r_P_CHSG;
    }

    public String getL_P_CHLG() {
        return l_P_CHLG;
    }

    public void setL_P_CHLG(String l_P_CHLG) {
        this.l_P_CHLG = l_P_CHLG;
    }

    public String getR_P_CHLG() {
        return r_P_CHLG;
    }

    public void setR_P_CHLG(String r_P_CHLG) {
        this.r_P_CHLG = r_P_CHLG;
    }

    public String getL_P_VC() {
        return l_P_VC;
    }

    public void setL_P_VC(String l_P_VC) {
        this.l_P_VC = l_P_VC;
    }

    public String getR_P_VC() {
        return r_P_VC;
    }

    public void setR_P_VC(String r_P_VC) {
        this.r_P_VC = r_P_VC;
    }

    public String getL_P_BASS() {
        return l_P_BASS;
    }

    public void setL_P_BASS(String l_P_BASS) {
        this.l_P_BASS = l_P_BASS;
    }

    public String getR_P_BASS() {
        return r_P_BASS;
    }

    public void setR_P_BASS(String r_P_BASS) {
        this.r_P_BASS = r_P_BASS;
    }

    public String getL_P_TRB() {
        return l_P_TRB;
    }

    public void setL_P_TRB(String l_P_TRB) {
        this.l_P_TRB = l_P_TRB;
    }

    public String getR_P_TRB() {
        return r_P_TRB;
    }

    public void setR_P_TRB(String r_P_TRB) {
        this.r_P_TRB = r_P_TRB;
    }

    public String getL_P_NRLVL() {
        return l_P_NRLVL;
    }

    public void setL_P_NRLVL(String l_P_NRLVL) {
        this.l_P_NRLVL = l_P_NRLVL;
    }

    public String getR_P_NRLVL() {
        return r_P_NRLVL;
    }

    public void setR_P_NRLVL(String r_P_NRLVL) {
        this.r_P_NRLVL = r_P_NRLVL;
    }

    public String getL_P_AFSLVL() {
        return l_P_AFSLVL;
    }

    public void setL_P_AFSLVL(String l_P_AFSLVL) {
        this.l_P_AFSLVL = l_P_AFSLVL;
    }

    public String getR_P_AFSLVL() {
        return r_P_AFSLVL;
    }

    public void setR_P_AFSLVL(String r_P_AFSLVL) {
        this.r_P_AFSLVL = r_P_AFSLVL;
    }

    public String getL_P_CHMPO() {
        return l_P_CHMPO;
    }

    public void setL_P_CHMPO(String l_P_CHMPO) {
        this.l_P_CHMPO = l_P_CHMPO;
    }

    public String getR_P_CHMPO() {
        return r_P_CHMPO;
    }

    public void setR_P_CHMPO(String r_P_CHMPO) {
        this.r_P_CHMPO = r_P_CHMPO;
    }

    public String getL_P_CHEXP() {
        return l_P_CHEXP;
    }

    public void setL_P_CHEXP(String l_P_CHEXP) {
        this.l_P_CHEXP = l_P_CHEXP;
    }

    public String getR_P_CHEXP() {
        return r_P_CHEXP;
    }

    public void setR_P_CHEXP(String r_P_CHEXP) {
        this.r_P_CHEXP = r_P_CHEXP;
    }

    public String getL_P_CHETH() {
        return l_P_CHETH;
    }

    public void setL_P_CHETH(String l_P_CHETH) {
        this.l_P_CHETH = l_P_CHETH;
    }

    public String getR_P_CHETH() {
        return r_P_CHETH;
    }

    public void setR_P_CHETH(String r_P_CHETH) {
        this.r_P_CHETH = r_P_CHETH;
    }

    public String getL_P_CHER() {
        return l_P_CHER;
    }

    public void setL_P_CHER(String l_P_CHER) {
        this.l_P_CHER = l_P_CHER;
    }

    public String getR_P_CHER() {
        return r_P_CHER;
    }

    public void setR_P_CHER(String r_P_CHER) {
        this.r_P_CHER = r_P_CHER;
    }
}
