package com.xiyoukeji.zhitingyun.data.entity;

import com.xiyoukeji.zhitingyun.util.L;

import java.util.List;

import io.realm.RealmObject;

public class DeviceEntity {
    private Integer id;
    private Integer classify;   //设备参数场景
    private String equipmentType;//设备类别
    private String hertz;   //通道赫兹数组
    private String chg_DATA;    //通道压缩小中大声增益数值List
    private Integer p_S_DL;//开机延迟时间
    //左耳
    private Boolean l_CR_SELECT;   //使用方式是否是压缩比方式
    private String l_P_VC;    //音量 VOL 数组list
    private Integer l_P_VC_VALUE;   //音量值，音量list的下标。
    private String l_P_BASS;    //低频增益 BASS数组
    private Integer l_P_BASS_VALUE;//低频增益 BASS下标
    private String l_P_MID; //中频增益 MID数组
    private Integer l_P_MID_VALUE;//中频增益 BASS下标
    private String l_P_TRB;    //高频增益数组
    private Integer l_P_TRB_VALUE;//高频增益 BASS下标
    private Integer l_P_NRLVL_VALUE; //降噪等级下标
    private String l_P_NRLVL;//降噪等级 CH_NR_LVL数组
    private Integer l_P_AFSLVL_VALUE; //声反馈抑制下标
    private String l_P_AFSLVL;  //声反馈抑制等级数组list
    private String l_P_EQ; //EQ前缀
    private Boolean l_SWITCH_P_CH;  //压缩开关
    private String l_P_CHMPO;    //通道MPO
    private String l_P_CHSW;    //通道压缩开启数组
    private String l_P_CHSG;    //通道压缩小声增益下标数组
    private String l_P_CHNG;    //通道压缩中声增益下标数组
    private String l_P_CHLG;    //通道压缩大声增益下标数组
    private Boolean l_SWITCH_P_CHEXP;   //通道压缩扩展开启开关
    private String l_P_CHEXP;    //通道压缩扩展前缀
    private String l_P_CHETH;    //通道压缩扩展拐点
    private String l_P_CHER;    //通道压缩扩展比
    //右耳
    private Boolean r_CR_SELECT;   //使用方式是否是压缩比方式
    private String r_P_VC;    //音量 VOL 数组list
    private Integer r_P_VC_VALUE;   //音量值，音量list的下标。
    private String r_P_BASS;    //低频增益 BASS数组
    private Integer r_P_BASS_VALUE;//低频增益 BASS下标
    private String r_P_MID; //中频增益 MID数组
    private Integer r_P_MID_VALUE;//中频增益 BASS下标
    private String r_P_TRB;    //高频增益数组
    private Integer r_P_TRB_VALUE;//高频增益 BASS下标
    private Integer r_P_NRLVL_VALUE; //降噪等级下标
    private String r_P_NRLVL;//降噪等级 CH_NR_LVL数组
    private Integer r_P_AFSLVL_VALUE; //声反馈抑制下标
    private String r_P_AFSLVL;  //声反馈抑制等级数组list
    private String r_P_EQ; //EQ前缀
    private Boolean r_SWITCH_P_CH;  //压缩开关
    private String r_P_CHMPO;    //通道MPO前缀
    private String r_P_CHSG;    //通道压缩小声增益下标数组
    private String r_P_CHNG;    //通道压缩中声增益下标数组
    private String r_P_CHLG;    //通道压缩大声增益下标数组
    private String r_P_CHSW;    //通道压缩开启数组
    private Boolean r_SWITCH_P_CHEXP;   //通道压缩扩展开启开关
    private String r_P_CHEXP;    //通道压缩扩展前缀
    private String r_P_CHETH;    //通道压缩扩展拐点
    private String r_P_CHER;    //通道压缩扩展比


    public DeviceEntity createDeviceEntity() {
        DeviceEntity deviceEntity = new DeviceEntity();
        deviceEntity.setClassify(this.getClassify());
        deviceEntity.setChg_DATA(this.getChg_DATA());
        deviceEntity.setHertz(this.getHertz());
        deviceEntity.setEquipmentType(this.getEquipmentType());
        deviceEntity.setId(this.getId());
        deviceEntity.setL_CR_SELECT(this.getL_CR_SELECT());
        deviceEntity.setL_P_AFSLVL(this.getL_P_AFSLVL());
        deviceEntity.setL_P_AFSLVL_VALUE(this.getL_P_AFSLVL_VALUE());
        deviceEntity.setL_P_BASS(this.getL_P_BASS());
        deviceEntity.setL_P_BASS_VALUE(this.getL_P_BASS_VALUE());
        deviceEntity.setL_P_CHER(this.getL_P_CHER());
        deviceEntity.setL_P_CHETH(this.getL_P_CHETH());
        deviceEntity.setL_P_CHEXP(this.getL_P_CHEXP());
        deviceEntity.setL_P_CHLG(this.getL_P_CHLG());
        deviceEntity.setL_P_CHMPO(this.getL_P_CHMPO());
        deviceEntity.setL_P_CHNG(this.getL_P_CHNG());
        deviceEntity.setL_P_CHSG(this.getL_P_CHSG());
        deviceEntity.setL_P_CHSW(this.getL_P_CHSW());
        deviceEntity.setL_P_EQ(this.getL_P_EQ());
        deviceEntity.setL_P_MID(this.getL_P_MID());
        deviceEntity.setL_P_MID_VALUE(this.getL_P_MID_VALUE());
        deviceEntity.setL_P_NRLVL(this.getL_P_NRLVL());
        deviceEntity.setL_P_NRLVL_VALUE(this.getL_P_NRLVL_VALUE());
        deviceEntity.setL_P_TRB(this.getL_P_TRB());
        deviceEntity.setL_P_TRB_VALUE(this.getL_P_TRB_VALUE());
        deviceEntity.setL_P_VC(this.getL_P_VC());
        deviceEntity.setL_P_VC_VALUE(this.getL_P_VC_VALUE());
        deviceEntity.setL_SWITCH_P_CH(this.getL_SWITCH_P_CH());
        deviceEntity.setL_SWITCH_P_CHEXP(this.getL_SWITCH_P_CHEXP());
        deviceEntity.setP_S_DL(this.getP_S_DL());
        deviceEntity.setR_CR_SELECT(this.getR_CR_SELECT());
        deviceEntity.setR_P_AFSLVL(this.getR_P_AFSLVL());
        deviceEntity.setR_P_AFSLVL_VALUE(this.getR_P_AFSLVL_VALUE());
        deviceEntity.setR_P_BASS(this.getR_P_BASS());
        deviceEntity.setR_P_BASS_VALUE(this.getR_P_BASS_VALUE());
        deviceEntity.setR_P_CHER(this.getR_P_CHER());
        deviceEntity.setR_P_CHETH(this.getR_P_CHETH());
        deviceEntity.setR_P_CHEXP(this.getR_P_CHEXP());
        deviceEntity.setR_P_CHLG(this.getR_P_CHLG());
        deviceEntity.setR_P_CHMPO(this.getR_P_CHMPO());
        deviceEntity.setR_P_CHNG(this.getR_P_CHNG());
        deviceEntity.setR_P_CHSG(this.getR_P_CHSG());
        deviceEntity.setR_P_CHSW(this.getR_P_CHSW());
        deviceEntity.setR_P_EQ(this.getR_P_EQ());
        deviceEntity.setR_P_MID(this.getR_P_MID());
        deviceEntity.setR_P_MID_VALUE(this.getR_P_MID_VALUE());
        deviceEntity.setR_P_NRLVL(this.getR_P_NRLVL());
        deviceEntity.setR_P_NRLVL_VALUE(this.getR_P_NRLVL_VALUE());
        deviceEntity.setR_P_TRB(this.getR_P_TRB());
        deviceEntity.setR_P_TRB_VALUE(this.getR_P_TRB_VALUE());
        deviceEntity.setR_P_VC(this.getR_P_VC());
        deviceEntity.setR_P_VC_VALUE(this.getR_P_VC_VALUE());
        deviceEntity.setR_SWITCH_P_CH(this.getR_SWITCH_P_CH());
        deviceEntity.setR_SWITCH_P_CHEXP(this.getR_SWITCH_P_CHEXP());
        return deviceEntity;


    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getChg_DATA() {
        return chg_DATA;
    }

    public void setChg_DATA(String chg_DATA) {
        this.chg_DATA = chg_DATA;
    }

    public Integer getP_S_DL() {
        return p_S_DL;
    }

    public void setP_S_DL(Integer p_S_DL) {
        this.p_S_DL = p_S_DL;
    }

    public Boolean getL_CR_SELECT() {
        return l_CR_SELECT;
    }

    public void setL_CR_SELECT(Boolean l_CR_SELECT) {
        this.l_CR_SELECT = l_CR_SELECT;
    }

    public String getL_P_VC() {
        return l_P_VC;
    }

    public void setL_P_VC(String l_P_VC) {
        this.l_P_VC = l_P_VC;
    }

    public Integer getL_P_VC_VALUE() {
        return l_P_VC_VALUE;
    }

    public void setL_P_VC_VALUE(Integer l_P_VC_VALUE) {
        this.l_P_VC_VALUE = l_P_VC_VALUE;
    }

    public String getL_P_BASS() {
        return l_P_BASS;
    }

    public void setL_P_BASS(String l_P_BASS) {
        this.l_P_BASS = l_P_BASS;
    }

    public Integer getL_P_BASS_VALUE() {
        return l_P_BASS_VALUE;
    }

    public void setL_P_BASS_VALUE(Integer l_P_BASS_VALUE) {
        this.l_P_BASS_VALUE = l_P_BASS_VALUE;
    }

    public String getL_P_MID() {
        return l_P_MID;
    }

    public void setL_P_MID(String l_P_MID) {
        this.l_P_MID = l_P_MID;
    }

    public Integer getL_P_MID_VALUE() {
        return l_P_MID_VALUE;
    }

    public void setL_P_MID_VALUE(Integer l_P_MID_VALUE) {
        this.l_P_MID_VALUE = l_P_MID_VALUE;
    }

    public String getL_P_TRB() {
        return l_P_TRB;
    }

    public void setL_P_TRB(String l_P_TRB) {
        this.l_P_TRB = l_P_TRB;
    }

    public Integer getL_P_TRB_VALUE() {
        return l_P_TRB_VALUE;
    }

    public void setL_P_TRB_VALUE(Integer l_P_TRB_VALUE) {
        this.l_P_TRB_VALUE = l_P_TRB_VALUE;
    }

    public Integer getL_P_NRLVL_VALUE() {
        return l_P_NRLVL_VALUE;
    }

    public void setL_P_NRLVL_VALUE(Integer l_P_NRLVL_VALUE) {
        this.l_P_NRLVL_VALUE = l_P_NRLVL_VALUE;
    }

    public String getL_P_NRLVL() {
        return l_P_NRLVL;
    }

    public void setL_P_NRLVL(String l_P_NRLVL) {
        this.l_P_NRLVL = l_P_NRLVL;
    }

    public Integer getL_P_AFSLVL_VALUE() {
        return l_P_AFSLVL_VALUE;
    }

    public void setL_P_AFSLVL_VALUE(Integer l_P_AFSLVL_VALUE) {
        this.l_P_AFSLVL_VALUE = l_P_AFSLVL_VALUE;
    }

    public String getL_P_AFSLVL() {
        return l_P_AFSLVL;
    }

    public void setL_P_AFSLVL(String l_P_AFSLVL) {
        this.l_P_AFSLVL = l_P_AFSLVL;
    }

    public String getL_P_EQ() {
        return l_P_EQ;
    }

    public void setL_P_EQ(String l_P_EQ) {
        this.l_P_EQ = l_P_EQ;
    }

    public Boolean getL_SWITCH_P_CH() {
        return l_SWITCH_P_CH;
    }

    public void setL_SWITCH_P_CH(Boolean l_SWITCH_P_CH) {
        this.l_SWITCH_P_CH = l_SWITCH_P_CH;
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

    public String getL_P_CHNG() {
        return l_P_CHNG;
    }

    public void setL_P_CHNG(String l_P_CHNG) {
        this.l_P_CHNG = l_P_CHNG;
    }

    public String getL_P_CHLG() {
        return l_P_CHLG;
    }

    public void setL_P_CHLG(String l_P_CHLG) {
        this.l_P_CHLG = l_P_CHLG;
    }

    public Boolean getL_SWITCH_P_CHEXP() {
        return l_SWITCH_P_CHEXP;
    }

    public void setL_SWITCH_P_CHEXP(Boolean l_SWITCH_P_CHEXP) {
        this.l_SWITCH_P_CHEXP = l_SWITCH_P_CHEXP;
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

    public Boolean getR_CR_SELECT() {
        return r_CR_SELECT;
    }

    public void setR_CR_SELECT(Boolean r_CR_SELECT) {
        this.r_CR_SELECT = r_CR_SELECT;
    }

    public String getR_P_VC() {
        return r_P_VC;
    }

    public void setR_P_VC(String r_P_VC) {
        this.r_P_VC = r_P_VC;
    }

    public Integer getR_P_VC_VALUE() {
        return r_P_VC_VALUE;
    }

    public void setR_P_VC_VALUE(Integer r_P_VC_VALUE) {
        this.r_P_VC_VALUE = r_P_VC_VALUE;
    }

    public String getR_P_BASS() {
        return r_P_BASS;
    }

    public void setR_P_BASS(String r_P_BASS) {
        this.r_P_BASS = r_P_BASS;
    }

    public Integer getR_P_BASS_VALUE() {
        return r_P_BASS_VALUE;
    }

    public void setR_P_BASS_VALUE(Integer r_P_BASS_VALUE) {
        this.r_P_BASS_VALUE = r_P_BASS_VALUE;
    }

    public String getR_P_MID() {
        return r_P_MID;
    }

    public void setR_P_MID(String r_P_MID) {
        this.r_P_MID = r_P_MID;
    }

    public Integer getR_P_MID_VALUE() {
        return r_P_MID_VALUE;
    }

    public void setR_P_MID_VALUE(Integer r_P_MID_VALUE) {
        this.r_P_MID_VALUE = r_P_MID_VALUE;
    }

    public String getR_P_TRB() {
        return r_P_TRB;
    }

    public void setR_P_TRB(String r_P_TRB) {
        this.r_P_TRB = r_P_TRB;
    }

    public Integer getR_P_TRB_VALUE() {
        return r_P_TRB_VALUE;
    }

    public void setR_P_TRB_VALUE(Integer r_P_TRB_VALUE) {
        this.r_P_TRB_VALUE = r_P_TRB_VALUE;
    }

    public Integer getR_P_NRLVL_VALUE() {
        return r_P_NRLVL_VALUE;
    }

    public void setR_P_NRLVL_VALUE(Integer r_P_NRLVL_VALUE) {
        this.r_P_NRLVL_VALUE = r_P_NRLVL_VALUE;
    }

    public String getR_P_NRLVL() {
        return r_P_NRLVL;
    }

    public void setR_P_NRLVL(String r_P_NRLVL) {
        this.r_P_NRLVL = r_P_NRLVL;
    }

    public Integer getR_P_AFSLVL_VALUE() {
        return r_P_AFSLVL_VALUE;
    }

    public void setR_P_AFSLVL_VALUE(Integer r_P_AFSLVL_VALUE) {
        this.r_P_AFSLVL_VALUE = r_P_AFSLVL_VALUE;
    }

    public String getR_P_AFSLVL() {
        return r_P_AFSLVL;
    }

    public void setR_P_AFSLVL(String r_P_AFSLVL) {
        this.r_P_AFSLVL = r_P_AFSLVL;
    }

    public String getR_P_EQ() {
        return r_P_EQ;
    }

    public void setR_P_EQ(String r_P_EQ) {
        this.r_P_EQ = r_P_EQ;
    }

    public Boolean getR_SWITCH_P_CH() {
        return r_SWITCH_P_CH;
    }

    public void setR_SWITCH_P_CH(Boolean r_SWITCH_P_CH) {
        this.r_SWITCH_P_CH = r_SWITCH_P_CH;
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

    public String getR_P_CHNG() {
        return r_P_CHNG;
    }

    public void setR_P_CHNG(String r_P_CHNG) {
        this.r_P_CHNG = r_P_CHNG;
    }

    public String getR_P_CHLG() {
        return r_P_CHLG;
    }

    public void setR_P_CHLG(String r_P_CHLG) {
        this.r_P_CHLG = r_P_CHLG;
    }

    public String getR_P_CHSW() {
        return r_P_CHSW;
    }

    public void setR_P_CHSW(String r_P_CHSW) {
        this.r_P_CHSW = r_P_CHSW;
    }

    public Boolean getR_SWITCH_P_CHEXP() {
        return r_SWITCH_P_CHEXP;
    }

    public void setR_SWITCH_P_CHEXP(Boolean r_SWITCH_P_CHEXP) {
        this.r_SWITCH_P_CHEXP = r_SWITCH_P_CHEXP;
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
