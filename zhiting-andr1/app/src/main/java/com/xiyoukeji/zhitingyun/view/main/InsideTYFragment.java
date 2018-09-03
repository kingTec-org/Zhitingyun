package com.xiyoukeji.zhitingyun.view.main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.xiyoukeji.zhitingyun.R;
import com.xiyoukeji.zhitingyun.ZhitingyunApplication;
import com.xiyoukeji.zhitingyun.base.BaseCom;
import com.xiyoukeji.zhitingyun.base.BaseFragment;
import com.xiyoukeji.zhitingyun.data.entity.DeviceEntity;
import com.xiyoukeji.zhitingyun.data.entity.MessageEvent;
import com.xiyoukeji.zhitingyun.hadeviceapi.AllParameter;

import com.xiyoukeji.zhitingyun.widget.StaticConfigCallback;
import com.xiyoukeji.zhitingyun.widget.VerticalSeekBar;
import com.xiyoukeji.zhitingyun.yunxin.SPUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

import cn.earhear.hadevicelib.HAParameter;
import cn.earhear.hadevicelib.HAParameterList;
import cn.earhear.hadevicelib.callback.ConfigCallback;
import cn.earhear.hadevicelib.callback.ConnectCallback;
import cn.earhear.hadevicelib.callback.DetectCallback;
import cn.earhear.hadevicelib.constant.ErrorType;
import cn.earhear.hadevicelib.constant.OperationType;
import cn.earhear.hadevicelib.constant.SideSelect;
import static com.xiyoukeji.zhitingyun.ZhitingyunApplication.deviceManager;
@SuppressLint("ValidFragment")

public class InsideTYFragment extends BaseFragment {
    @BindView(R.id.left)
    SeekBar mLeftSb;
    @BindView(R.id.right)
    SeekBar mRightSb;
    @BindView(R.id.show1)
    TextView show1;


    private List<HAParameterList> sysParameterList;
    private List<HAParameterList> memParameterList;
    private static final String TYPE_SYS = "sys";
    private static final String TYPE_LEFT = "left";
    private static final String TYPE_RIGHT = "right";
    private List<AllParameter> allParamList;
    private List<AllParameter> allParameterList;
    private AutoRegulationActivity autoRegulationActivity;

    public List<AllParameter> getAllParameterList() {
        return allParamList;
    }

    private Integer enviro;
    private int a1 = 0, a2 = 0, a3 = 0, a4 = 0, a5 = 0, b1 = 0, b2 = 0, b3 = 0, b4 = 0, b5 = 0, c1 = 0, c2 = 0, c3 = 0, c4 = 0, c5 = 0,
            d1 = 0, d2 = 0, d3 = 0, d4 = 0, d5 = 0, e1 = 0, e2 = 0, e3 = 0, e4 = 0, e5 = 0, f1 = 0, f2 = 0, f3 = 0, f4 = 0, f5 = 0, g1 = 0, g2 = 0, g3 = 0, g4 = 0, g5 = 0, h1 = 0, h2 = 0, h3 = 0, h4 = 0, h5 = 0;
    private int i1 = 0, i2 = 0, i3 = 0, i4 = 0, i5 = 0, j1 = 0, j2 = 0, j3 = 0, j4 = 0, j5 = 0, k1 = 0, k2 = 0, k3 = 0, k4 = 0, k5 = 0,
            l1 = 0, l2 = 0, l3 = 0, l4 = 0, l5 = 0, m1 = 0, m2 = 0, m3 = 0, m4 = 0, m5 = 0, n1 = 0, n2 = 0, n3 = 0, n4 = 0, n5 = 0, o1 = 0, o2 = 0, o3 = 0, o4 = 0, o5 = 0, p1 = 0, p2 = 0, p3 = 0, p4 = 0, p5 = 0;
    DeviceEntity entity;

    private AutoRegulationActivity.OngetInfo ongetInfo;
    List<Integer> list=new ArrayList<>(  );
    public void setCurrent(){
        Log.d( "vvv","current3" );
        StaticConfigCallback.getInStanceBlock().setActivityFlag(3);
        ZhitingyunApplication.deviceManager.setCurrentSelect( 4 );

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_volumbarhw;
    }



    public InsideTYFragment(AutoRegulationActivity.OngetInfo ongetInfo) {
        this.ongetInfo = ongetInfo;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        entity = new DeviceEntity();
        list.add(0);
        list.add( 0 );
        ongetInfo.ongetInfo( 6,list );

    }


    public void setAllParameterList(List<AllParameter> parameter) {
        this.allParamList = parameter;
        notify();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }


    public AllParameter getItem(int position) {
        if (allParamList == null || position > allParamList.size())
            return null;
        return allParamList.get( position );
    }



    @Override
    protected void initView(View view) {
        super.initView( view );

    }



    public void fillAllParameterList( List<HAParameterList> sysParameterList, List<HAParameterList> memParameterList,final int currentMemory ) {
        if (allParameterList == null)
            allParameterList = new ArrayList<>();
        else
            allParameterList.clear();
        entity.setClassify(1);

        if (ZhitingyunApplication.curState.getHaSide() == SideSelect.S_BINAURAL) {
            for (HAParameter parameter : sysParameterList.get(0).getParameterList()) {
                AllParameter tmp = new AllParameter(parameter, TYPE_SYS);
                allParameterList.add(tmp);

                if (parameter.getId().equals("HA_SW")) {
                } else if (parameter.getId().equals("HA_DELAY")) {
                    entity.setP_S_DL(parameter.getValue());
                } else if (parameter.getId().equals("HA_ALRM")) {

                }
            }

            for (final HAParameter parameter : memParameterList.get(SideSelect.S_UNILATERAL_LEFT).getParameterList()) {
                AllParameter tmp = new AllParameter(parameter, TYPE_LEFT);
                allParameterList.add(tmp);

                if (parameter.getId().equals("VOL")) {
                    String string = "";
                    for (int j = 0; j < parameter.getIndexedList().size(); j++) {
                        string += parameter.getIndexedList().get(j).intValue() + "";
                        if (j != parameter.getIndexedList().size() - 1)
                            string += ",";
                    }
                    entity.setL_P_VC(string);
                    entity.setL_P_VC_VALUE(parameter.getValue());
                } else if (parameter.getId().equals("BASS")) {
                    String string = "";
                    for (int j = 0; j < parameter.getIndexedList().size(); j++) {
                        string += parameter.getIndexedList().get(j).intValue() + "";
                        if (j != parameter.getIndexedList().size() - 1)
                            string += ",";
                    }

                    entity.setL_P_BASS(string);
                    entity.setL_P_BASS_VALUE(parameter.getValue());
                } else if (parameter.getId().equals("MID")) {
                    String string = "";
                    for (int j = 0; j < parameter.getIndexedList().size(); j++) {
                        string += parameter.getIndexedList().get(j).intValue() + "";
                        if (j != parameter.getIndexedList().size() - 1)
                            string += ",";
                    }
                    entity.setL_P_BASS(string);
                    entity.setL_P_BASS_VALUE(parameter.getValue());
                } else if (parameter.getId().equals("TREBLE")) {
                    String string = "";
                    for (int j = 0; j < parameter.getIndexedList().size(); j++) {
                        string += parameter.getIndexedList().get(j).intValue() + "";
                        if (j != parameter.getIndexedList().size() - 1)
                            string += ",";
                    }
                    entity.setL_P_TRB(string);
                    entity.setL_P_TRB_VALUE(parameter.getValue());
                } else if (parameter.getId().equals("NR_LVL")) {
                    String string = "";
                    for (int j = 0; j < parameter.getIndexedList().size(); j++) {
                        string += parameter.getIndexedList().get(j).intValue() + "";
                        if (j != parameter.getIndexedList().size() - 1)
                            string += ",";
                    }
                    entity.setL_P_NRLVL(string);
                    entity.setL_P_NRLVL_VALUE(parameter.getValue());
                } else if (parameter.getId().equals("AFS_LVL")) {
                    String string = "";
                    for (int j = 0; j < parameter.getIndexedList().size(); j++) {
                        string += parameter.getIndexedList().get(j).intValue() + "";
                        if (j != parameter.getIndexedList().size() - 1)
                            string += ",";
                    }
                    entity.setL_P_AFSLVL(string);
                    entity.setL_P_AFSLVL_VALUE(parameter.getValue());
                } else if (parameter.getId().equals("CH_MPO_0")) {
                    a1 = parameter.getValue();
                } else if (parameter.getId().equals("CH_MPO_1")) {
                    a2 = parameter.getValue();
                } else if (parameter.getId().equals("CH_MPO_2")) {
                    a3 = parameter.getValue();
                } else if (parameter.getId().equals("CH_MPO_3")) {
                    a4 = parameter.getValue();
                } else if (parameter.getId().equals("CH_MPO_4")) {
                    a5 = parameter.getValue();
                } else if (parameter.getId().equals("CH_CMPO_0")) {
                    b1 = parameter.getValue();
                } else if (parameter.getId().equals("CH_CMPO_1")) {
                    b2 = parameter.getValue();
                } else if (parameter.getId().equals("CH_CMPO_2")) {
                    b3 = parameter.getValue();
                } else if (parameter.getId().equals("CH_CMPO_3")) {
                    b4 = parameter.getValue();
                } else if (parameter.getId().equals("CH_CMPO_4")) {
                    b5 = parameter.getValue();
                } else if (parameter.getId().equals("CH_SOFT_GAIN_0")) {
                    c1 = parameter.getValue();
                } else if (parameter.getId().equals("CH_SOFT_GAIN_1")) {
                    c2 = parameter.getValue();
                } else if (parameter.getId().equals("CH_SOFT_GAIN_2")) {
                    c3 = parameter.getValue();
                } else if (parameter.getId().equals("CH_SOFT_GAIN_3")) {
                    c4 = parameter.getValue();
                } else if (parameter.getId().equals("CH_SOFT_GAIN_4")) {
                    c5 = parameter.getValue();
                } else if (parameter.getId().equals("CH_NORM_GAIN_0")) {
                    d1 = parameter.getValue();
                } else if (parameter.getId().equals("CH_NORM_GAIN_1")) {
                    d2 = parameter.getValue();
                } else if (parameter.getId().equals("CH_NORM_GAIN_2")) {
                    d3 = parameter.getValue();
                } else if (parameter.getId().equals("CH_NORM_GAIN_3")) {
                    d4 = parameter.getValue();
                } else if (parameter.getId().equals("CH_NORM_GAIN_4")) {
                    d5 = parameter.getValue();
                } else if (parameter.getId().equals("CH_LOUD_GAIN_0")) {
                    e1 = parameter.getValue();
                } else if (parameter.getId().equals("CH_LOUD_GAIN_1")) {
                    e2 = parameter.getValue();
                } else if (parameter.getId().equals("CH_LOUD_GAIN_2")) {
                    e3 = parameter.getValue();
                } else if (parameter.getId().equals("CH_LOUD_GAIN_3")) {
                    e4 = parameter.getValue();
                } else if (parameter.getId().equals("CH_LOUD_GAIN_4")) {
                    e5 = parameter.getValue();
                } else if (parameter.getId().equals("CH_EXPS_0")) {
                    f1 = parameter.getValue();
                } else if (parameter.getId().equals("CH_EXPS_1")) {
                    f2 = parameter.getValue();
                } else if (parameter.getId().equals("CH_EXPS_2")) {
                    f3 = parameter.getValue();
                } else if (parameter.getId().equals("CH_EXPS_3")) {
                    f4 = parameter.getValue();
                } else if (parameter.getId().equals("CH_EXPS_4")) {
                    f5 = parameter.getValue();
                } else if (parameter.getId().equals("CH_EXPS_TH_0")) {
                    g1 =(parameter.getValue()+1)*2;
                } else if (parameter.getId().equals("CH_EXPS_TH_1")) {
                    g2 = (parameter.getValue()+1)*2;
                } else if (parameter.getId().equals("CH_EXPS_TH_2")) {
                    g3 = (parameter.getValue()+1)*2;
                } else if (parameter.getId().equals("CH_EXPS_TH_3")) {
                    g4 = (parameter.getValue()+1)*2;
                } else if (parameter.getId().equals("CH_EXPS_TH_4")) {
                    g5 = (parameter.getValue()+1)*2;
                } else if (parameter.getId().equals("CH_EXPS_R_0")) {
                    h1 =(parameter.getValue()+1)*2;
                } else if (parameter.getId().equals("CH_EXPS_R_1")) {
                    h2 =(parameter.getValue()+1)*2;
                } else if (parameter.getId().equals("CH_EXPS_R_2")) {
                    h3 = (parameter.getValue()+1)*2;
                } else if (parameter.getId().equals("CH_EXPS_R_3")) {
                    h4 = (parameter.getValue()+1)*2;
                } else if (parameter.getId().equals("CH_EXPS_R_4")) {
                    h5 = (parameter.getValue()+1)*2;
                }
                if (a1 != 0 && b1 != 0 && c1 != 0 && d1 != 0 && e1 != 0) {
                    entity.setL_SWITCH_P_CH(true);
                } else {
                    entity.setL_SWITCH_P_CH(true);
                }
                if (f1 != 0 && g1 != 0 && h1 != 0) {
                    entity.setL_SWITCH_P_CHEXP(true);
                } else {
                    entity.setL_SWITCH_P_CHEXP(true);
                }
//                    List<Double[]> mylist = new ArrayList<Double[]>();
                entity.setL_P_CHMPO(a1 + "," + a2 + "," + a3 + "," + a4 + "," + a5);

                entity.setL_P_CHSW(b1 + "," + b2 + "," + b3 + "," + b4 + "," + b5);

                entity.setL_P_CHSG(c1 + "," + c2 + "," + c3 + "," + c4 + "," + c5);

                entity.setL_P_CHNG(d1 + "," + d2 + "," + d3 + "," + d4 + "," + d5);

                entity.setL_P_CHLG(e1 + "," + e2 + "," + e3 + "," + e4 + "," + e5);

                entity.setL_P_CHEXP(f1 + "," + f2 + "," + f3 + "," + f4 + "," + f5);

                entity.setL_P_CHETH(g1 + "," + g2 + "," + g3 + "," + g4 + "," + g5);

                entity.setL_P_CHER(h1 + "," + h2 + "," + h3 + "," + h4 + "," + h5);
//                }

                if (parameter.getId().equals("VOL")) {
                    mLeftSb.setMax(parameter.getMax());
                    mLeftSb.setProgress(parameter.getValue());
//                show1.setText( String.valueOf(haParameter.getValue()) );
                    mLeftSb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                            if (valueChangeListener != null)

                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {

                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {
                            parameter.setValue(mLeftSb.getProgress());
                            ZhitingyunApplication.deviceManager.writeDevice(currentMemory);
                            list.set( 0,mLeftSb.getProgress() );
                            ongetInfo.ongetInfo( 6,list );
                        }
                    });
                }
//                }
            }

            for (final HAParameter parameter : memParameterList.get(SideSelect.S_UNILATERAL_RIGHT).getParameterList()) {
                AllParameter tmp = new AllParameter(parameter, TYPE_RIGHT);
                allParameterList.add(tmp);


                if (parameter.getId().equals("CH_SOFT_GAIN_0")) {
                    String string = "";
                    for (int j = 0; j < parameter.getIndexedList().size(); j++) {
                        string += parameter.getIndexedList().get(j).intValue() + "";
                        if (j != parameter.getIndexedList().size() - 1)
                            string += ",";
                    }
                    entity.setChg_DATA(string);
                }


                if (parameter.getId().equals("VOL")) {
                    String string = "";
                    for (int j = 0; j < parameter.getIndexedList().size(); j++) {
                        string += parameter.getIndexedList().get(j).intValue() + "";
                        if (j != parameter.getIndexedList().size() - 1)
                            string += ",";
                    }

                    entity.setR_P_VC(string);
                    entity.setR_P_VC_VALUE(parameter.getValue());
                } else if (parameter.getId().equals("BASS")) {
                    String string = "";
                    for (int j = 0; j < parameter.getIndexedList().size(); j++) {
                        string += parameter.getIndexedList().get(j).intValue() + "";
                        if (j != parameter.getIndexedList().size() - 1)
                            string += ",";
                    }
                    entity.setR_P_BASS(string);
                    entity.setR_P_BASS_VALUE(parameter.getValue());
                } else if (parameter.getId().equals("MID")) {
                    String string = "";
                    for (int j = 0; j < parameter.getIndexedList().size(); j++) {
                        string += parameter.getIndexedList().get(j).intValue() + "";
                        if (j != parameter.getIndexedList().size() - 1)
                            string += ",";
                    }
                    entity.setR_P_BASS(string);
                    entity.setR_P_BASS_VALUE(parameter.getValue());
                } else if (parameter.getId().equals("TREBLE")) {
                    String string = "";
                    for (int j = 0; j < parameter.getIndexedList().size(); j++) {
                        string += parameter.getIndexedList().get(j).intValue() + "";
                        if (j != parameter.getIndexedList().size() - 1)
                            string += ",";
                    }
                    entity.setR_P_TRB(string);
                    entity.setR_P_TRB_VALUE(parameter.getValue());
                } else if (parameter.getId().equals("NR_LVL")) {
                    String string = "";
                    for (int j = 0; j < parameter.getIndexedList().size(); j++) {
                        string += parameter.getIndexedList().get(j).intValue() + "";
                        if (j != parameter.getIndexedList().size() - 1)
                            string += ",";
                    }
                    entity.setR_P_NRLVL(string);
                    entity.setR_P_NRLVL_VALUE(parameter.getValue());
                } else if (parameter.getId().equals("AFS_LVL")) {
                    String string = "";
                    for (int j = 0; j < parameter.getIndexedList().size(); j++) {
                        string += parameter.getIndexedList().get(j).intValue() + "";
                        if (j != parameter.getIndexedList().size() - 1)
                            string += ",";
                    }
                    entity.setR_P_AFSLVL(string);
                    entity.setR_P_AFSLVL_VALUE(parameter.getValue());
                } else if (parameter.getId().equals("CH_MPO_0")) {
                    i1 = parameter.getValue();
                } else if (parameter.getId().equals("CH_MPO_1")) {
                    i2 = parameter.getValue();
                } else if (parameter.getId().equals("CH_MPO_2")) {
                    i3 = parameter.getValue();
                } else if (parameter.getId().equals("CH_MPO_3")) {
                    i4 = parameter.getValue();
                } else if (parameter.getId().equals("CH_MPO_4")) {
                    i5 = parameter.getValue();
                } else if (parameter.getId().equals("CH_CMPO_0")) {
                    j1 = parameter.getValue();
                } else if (parameter.getId().equals("CH_CMPO_1")) {
                    j2 = parameter.getValue();
                } else if (parameter.getId().equals("CH_CMPO_2")) {
                    j3 = parameter.getValue();
                } else if (parameter.getId().equals("CH_CMPO_3")) {
                    j4 = parameter.getValue();
                } else if (parameter.getId().equals("CH_CMPO_4")) {
                    j5 = parameter.getValue();
                } else if (parameter.getId().equals("CH_SOFT_GAIN_0")) {
                    k1 = parameter.getValue();
                } else if (parameter.getId().equals("CH_SOFT_GAIN_1")) {
                    k2 = parameter.getValue();
                } else if (parameter.getId().equals("CH_SOFT_GAIN_2")) {
                    k3 = parameter.getValue();
                } else if (parameter.getId().equals("CH_SOFT_GAIN_3")) {
                    k4 = parameter.getValue();
                } else if (parameter.getId().equals("CH_SOFT_GAIN_4")) {
                    k5 = parameter.getValue();
                } else if (parameter.getId().equals("CH_NORM_GAIN_0")) {
                    l1 = parameter.getValue();
                } else if (parameter.getId().equals("CH_NORM_GAIN_1")) {
                    l2 = parameter.getValue();
                } else if (parameter.getId().equals("CH_NORM_GAIN_2")) {
                    l3 = parameter.getValue();
                } else if (parameter.getId().equals("CH_NORM_GAIN_3")) {
                    l4 = parameter.getValue();
                } else if (parameter.getId().equals("CH_NORM_GAIN_4")) {
                    l5 = parameter.getValue();
                } else if (parameter.getId().equals("CH_LOUD_GAIN_0")) {
                    m1 = parameter.getValue();
                } else if (parameter.getId().equals("CH_LOUD_GAIN_1")) {
                    m2 = parameter.getValue();
                } else if (parameter.getId().equals("CH_LOUD_GAIN_2")) {
                    m3 = parameter.getValue();
                } else if (parameter.getId().equals("CH_LOUD_GAIN_3")) {
                    m4 = parameter.getValue();
                } else if (parameter.getId().equals("CH_LOUD_GAIN_4")) {
                    m5 = parameter.getValue();
                } else if (parameter.getId().equals("CH_EXPS_0")) {
                    n1 = parameter.getValue();
                } else if (parameter.getId().equals("CH_EXPS_1")) {
                    n2 = parameter.getValue();
                } else if (parameter.getId().equals("CH_EXPS_2")) {
                    n3 = parameter.getValue();
                } else if (parameter.getId().equals("CH_EXPS_3")) {
                    n4 = parameter.getValue();
                } else if (parameter.getId().equals("CH_EXPS_4")) {
                    n5 = parameter.getValue();
                } else if (parameter.getId().equals("CH_EXPS_TH_0")) {
                    o1 = (parameter.getValue()+1)*2;
                } else if (parameter.getId().equals("CH_EXPS_TH_1")) {
                    o2 =(parameter.getValue()+1)*2;
                } else if (parameter.getId().equals("CH_EXPS_TH_2")) {
                    o3 = (parameter.getValue()+1)*2;
                } else if (parameter.getId().equals("CH_EXPS_TH_3")) {
                    o4 = (parameter.getValue()+1)*2;
                } else if (parameter.getId().equals("CH_EXPS_TH_4")) {
                    o5 = (parameter.getValue()+1)*2;
                } else if (parameter.getId().equals("CH_EXPS_R_0")) {
                    p1 = (parameter.getValue()+1)*2;
                } else if (parameter.getId().equals("CH_EXPS_R_1")) {
                    p2 =(parameter.getValue()+1)*2;
                } else if (parameter.getId().equals("CH_EXPS_R_2")) {
                    p3 =(parameter.getValue()+1)*2;
                } else if (parameter.getId().equals("CH_EXPS_R_3")) {
                    p4 =(parameter.getValue()+1)*2;
                } else if (parameter.getId().equals("CH_EXPS_R_4")) {
                    p5 =(parameter.getValue()+1)*2;
                }
                if (i1 != 0 && j1 != 0 && k1 != 0 && l1 != 0 && m1 != 0) {
                    entity.setR_SWITCH_P_CH(true);
                } else {
                    entity.setR_SWITCH_P_CH(true);
                }
                if (n1 != 0 && o1 != 0 && p1 != 0) {
                    entity.setR_SWITCH_P_CHEXP(true);
                } else {
                    entity.setR_SWITCH_P_CHEXP(true);
                }
                entity.setR_P_CHMPO(i1 + "," + i2 + "," + i3 + "," + i4 + "," + i5);

                entity.setR_P_CHSW(j1 + "," + j2 + "," + j3 + "," + j4 + "," + j5);

                entity.setR_P_CHSG(k1 + "," + k2 + "," + k3 + "," + k4 + "," + k5);

                entity.setR_P_CHNG(l1 + "," + l2 + "," + l3 + "," + l4 + "," + l5);

                entity.setR_P_CHLG(m1 + "," + m2 + "," + m3 + "," + m4 + "," + m5);

                entity.setR_P_CHEXP(n1 + "," + n2 + "," + n3 + "," + n4 + "," + n5);

                entity.setR_P_CHETH(o1 + "," + o2 + "," + o3 + "," + o4 + "," + o5);

                entity.setR_P_CHER(p1 + "," + p2 + "," + p3 + "," + p4 + "," + p5);

                if (parameter.getId().equals("VOL")) {
                    mRightSb.setMax(parameter.getMax());
                    mRightSb.setProgress(parameter.getValue());
//                show1.setText( String.valueOf(haParameter.getValue()) );
                    mRightSb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {

                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {
                            parameter.setValue(mRightSb.getProgress());
                            ZhitingyunApplication.deviceManager.writeDevice(currentMemory);
                            list.set( 1,mRightSb.getProgress() );
                            ongetInfo.ongetInfo( 4,list );
                        }
                    });
                }
            }
            entity.setHertz("125,250,500,750,1000,1500,2000,3000,4000,6000,8000");
            Gson gson = new Gson();
            String session1 = gson.toJson(entity);
            SPUtil.put(getContext(), "session1", session1);
        }
    }
}