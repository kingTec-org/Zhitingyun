package com.xiyoukeji.zhitingyun.data.entity;

public class RecieveDeviceEntity {

    /**
     * id : 21
     * userId : 350
     * classify : 2
     * equipmentType : null
     * hertz : 250,780,1800,2900,4500
     * del : false
     * createTime : 1531407409528
     * chg_DATA : 0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30
     * r_P_BASS_VALUE : 3
     * r_P_MID_VALUE : null
     * r_P_TRB_VALUE : 2
     * l_P_BASS_VALUE : 2
     * l_P_MID_VALUE : null
     * l_P_TRB_VALUE : 4
     * l_CR_SELECT : false
     * r_CR_SELECT : false
     * r_P_CHSW : 6,10,3,5,8
     * l_SWITCH_P_NRLVL : true
     * l_SWITCH_P_AFSLVL : true
     * l_SWITCH_P_CHEXP : true
     * r_SWITCH_P_NRLVL : true
     * r_SWITCH_P_AFSLVL : true
     * r_SWITCH_P_CHEXP : true
     * l_P_CHNG : 2,2,3,5,7
     * r_P_CHNG : 6,10,3,5,8
     * l_P_MID : null
     * r_P_MID : null
     * p_S_DL : 10
     * l_P_VC : 0,6,12,14,16,18,20,22
     * l_P_BASS : 1,2,3,4,5,6
     * l_P_TRB : 1,2,3,4,5,6
     * l_P_NRLVL : 2
     * l_P_AFSLVL : 1
     * l_P_EQ : null
     * l_P_CHMPO : 0,750,1750,2750,4250
     * l_P_CHSW : 2,10,2,6,2
     * l_P_CHSG : 4,3,7,5,10
     * l_P_CHLG : 1,8,7,6,4
     * l_P_CHEXP : 1
     * l_P_CHETH : 10,25,30,20,30
     * l_P_CHER : 10,15,10,5,20
     * r_P_VC : 0,6,12,14,16,18,20,22
     * r_P_BASS : 1,2,3,4,5,6
     * r_P_TRB : 1,2,3,4,5,6
     * r_P_NRLVL : 2
     * r_P_AFSLVL : 1
     * r_P_EQ : null
     * r_P_CHMPO : 0,750,1750,2750,4250
     * r_P_CHSG : 2,7,1,5,8
     * r_P_CHLG : 3,2,2,5,9
     * r_P_CHEXP : 1
     * r_P_CHETH : 1
     * r_P_CHER : 1
     * l_P_VC_VALUE : 4
     * r_P_VC_VALUE : 6
     */

    private int id;
    private int userId;
    private int classify;
    private String equipmentType;
    private String hertz;
    private boolean del;
    private long createTime;
    private String chg_DATA;
    private int r_P_BASS_VALUE;
    private String r_P_MID_VALUE;
    private int r_P_TRB_VALUE;
    private int l_P_BASS_VALUE;
    private String l_P_MID_VALUE;
    private int l_P_TRB_VALUE;
    private boolean l_CR_SELECT;
    private boolean r_CR_SELECT;
    private String r_P_CHSW;
    private boolean l_SWITCH_P_NRLVL;
    private boolean l_SWITCH_P_AFSLVL;
    private boolean l_SWITCH_P_CHEXP;
    private boolean r_SWITCH_P_NRLVL;
    private boolean r_SWITCH_P_AFSLVL;
    private boolean r_SWITCH_P_CHEXP;
    private String l_P_CHNG;
    private String r_P_CHNG;
    private String l_P_MID;
    private String r_P_MID;
    private String p_S_DL;
    private String l_P_VC;
    private String l_P_BASS;
    private String l_P_TRB;
    private String l_P_NRLVL;
    private String l_P_AFSLVL;
    private String l_P_EQ;
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
    private String r_P_NRLVL;
    private String r_P_AFSLVL;
    private String r_P_EQ;
    private String r_P_CHMPO;
    private String r_P_CHSG;
    private String r_P_CHLG;
    private String r_P_CHEXP;
    private String r_P_CHETH;
    private String r_P_CHER;
    private int l_P_VC_VALUE;
    private int r_P_VC_VALUE;

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

    public int getR_P_BASS_VALUE() {
        return r_P_BASS_VALUE;
    }

    public void setR_P_BASS_VALUE(int r_P_BASS_VALUE) {
        this.r_P_BASS_VALUE = r_P_BASS_VALUE;
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


    public int getL_P_TRB_VALUE() {
        return l_P_TRB_VALUE;
    }

    public void setL_P_TRB_VALUE(int l_P_TRB_VALUE) {
        this.l_P_TRB_VALUE = l_P_TRB_VALUE;
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

    public String getR_P_CHSW() {
        return r_P_CHSW;
    }

    public void setR_P_CHSW(String r_P_CHSW) {
        this.r_P_CHSW = r_P_CHSW;
    }

    public boolean isL_SWITCH_P_NRLVL() {
        return l_SWITCH_P_NRLVL;
    }

    public void setL_SWITCH_P_NRLVL(boolean l_SWITCH_P_NRLVL) {
        this.l_SWITCH_P_NRLVL = l_SWITCH_P_NRLVL;
    }

    public boolean isL_SWITCH_P_AFSLVL() {
        return l_SWITCH_P_AFSLVL;
    }

    public void setL_SWITCH_P_AFSLVL(boolean l_SWITCH_P_AFSLVL) {
        this.l_SWITCH_P_AFSLVL = l_SWITCH_P_AFSLVL;
    }

    public boolean isL_SWITCH_P_CHEXP() {
        return l_SWITCH_P_CHEXP;
    }

    public void setL_SWITCH_P_CHEXP(boolean l_SWITCH_P_CHEXP) {
        this.l_SWITCH_P_CHEXP = l_SWITCH_P_CHEXP;
    }

    public boolean isR_SWITCH_P_NRLVL() {
        return r_SWITCH_P_NRLVL;
    }

    public void setR_SWITCH_P_NRLVL(boolean r_SWITCH_P_NRLVL) {
        this.r_SWITCH_P_NRLVL = r_SWITCH_P_NRLVL;
    }

    public boolean isR_SWITCH_P_AFSLVL() {
        return r_SWITCH_P_AFSLVL;
    }

    public void setR_SWITCH_P_AFSLVL(boolean r_SWITCH_P_AFSLVL) {
        this.r_SWITCH_P_AFSLVL = r_SWITCH_P_AFSLVL;
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


    public String getP_S_DL() {
        return p_S_DL;
    }

    public void setP_S_DL(String p_S_DL) {
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

    public String getL_P_NRLVL() {
        return l_P_NRLVL;
    }

    public void setL_P_NRLVL(String l_P_NRLVL) {
        this.l_P_NRLVL = l_P_NRLVL;
    }

    public String getL_P_AFSLVL() {
        return l_P_AFSLVL;
    }

    public void setL_P_AFSLVL(String l_P_AFSLVL) {
        this.l_P_AFSLVL = l_P_AFSLVL;
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

    public String getR_P_NRLVL() {
        return r_P_NRLVL;
    }

    public void setR_P_NRLVL(String r_P_NRLVL) {
        this.r_P_NRLVL = r_P_NRLVL;
    }

    public String getR_P_AFSLVL() {
        return r_P_AFSLVL;
    }

    public void setR_P_AFSLVL(String r_P_AFSLVL) {
        this.r_P_AFSLVL = r_P_AFSLVL;
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

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getR_P_MID_VALUE() {
        return r_P_MID_VALUE;
    }

    public void setR_P_MID_VALUE(String r_P_MID_VALUE) {
        this.r_P_MID_VALUE = r_P_MID_VALUE;
    }

    public String getL_P_MID_VALUE() {
        return l_P_MID_VALUE;
    }

    public void setL_P_MID_VALUE(String l_P_MID_VALUE) {
        this.l_P_MID_VALUE = l_P_MID_VALUE;
    }

    public String getL_P_MID() {
        return l_P_MID;
    }

    public void setL_P_MID(String l_P_MID) {
        this.l_P_MID = l_P_MID;
    }

    public String getR_P_MID() {
        return r_P_MID;
    }

    public void setR_P_MID(String r_P_MID) {
        this.r_P_MID = r_P_MID;
    }

    public String getL_P_EQ() {
        return l_P_EQ;
    }

    public void setL_P_EQ(String l_P_EQ) {
        this.l_P_EQ = l_P_EQ;
    }

    public String getR_P_EQ() {
        return r_P_EQ;
    }

    public void setR_P_EQ(String r_P_EQ) {
        this.r_P_EQ = r_P_EQ;
    }
}
