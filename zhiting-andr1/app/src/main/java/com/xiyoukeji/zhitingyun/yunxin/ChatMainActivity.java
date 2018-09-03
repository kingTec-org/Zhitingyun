package com.xiyoukeji.zhitingyun.yunxin;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.gson.Gson;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.StatusCode;
import com.netease.nimlib.sdk.auth.AuthServiceObserver;
import com.netease.nimlib.sdk.avchat.AVChatManager;
import com.netease.nimlib.sdk.avchat.constant.AVChatControlCommand;
import com.netease.nimlib.sdk.avchat.constant.AVChatEventType;
import com.netease.nimlib.sdk.avchat.constant.AVChatType;
import com.netease.nimlib.sdk.avchat.model.AVChatAudioFrame;
import com.netease.nimlib.sdk.avchat.model.AVChatCalleeAckEvent;
import com.netease.nimlib.sdk.avchat.model.AVChatCommonEvent;
import com.netease.nimlib.sdk.avchat.model.AVChatControlEvent;
import com.netease.nimlib.sdk.avchat.model.AVChatData;
import com.netease.nimlib.sdk.avchat.model.AVChatVideoFrame;
import com.xiyoukeji.zhitingyun.R;
import com.xiyoukeji.zhitingyun.ViewModelFactory;
import com.xiyoukeji.zhitingyun.ZhitingyunApplication;
import com.xiyoukeji.zhitingyun.base.BaseCom;
import com.xiyoukeji.zhitingyun.data.entity.AccidEntity;
import com.xiyoukeji.zhitingyun.data.entity.DeviceEntity;
import com.xiyoukeji.zhitingyun.data.entity.MessageEvent;
import com.xiyoukeji.zhitingyun.data.entity.Model0;
import com.xiyoukeji.zhitingyun.data.entity.RecordEntity;
import com.xiyoukeji.zhitingyun.data.entity.ReturnMessage;
import com.xiyoukeji.zhitingyun.data.entity.SocketMessage;
import com.xiyoukeji.zhitingyun.data.remote.BaseObserver;
import com.xiyoukeji.zhitingyun.hadeviceapi.AllParameter;
import com.xiyoukeji.zhitingyun.util.Constant;
import com.xiyoukeji.zhitingyun.util.ToastUtils;
import com.xiyoukeji.zhitingyun.view.main.EvaluationActivity;
import com.xiyoukeji.zhitingyun.view.main.MainActivity;
import com.xiyoukeji.zhitingyun.view.mine.ConnectDeviceActivity;
import com.xiyoukeji.zhitingyun.viewmodel.main.MainViewModel;
import com.xiyoukeji.zhitingyun.widget.StaticConfigCallback;
import com.xiyoukeji.zhitingyun.widget.StatusBarUtil;
import com.xiyoukeji.zhitingyun.widget.WS;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.earhear.hadevicelib.DeviceInfo;
import cn.earhear.hadevicelib.DeviceState;
import cn.earhear.hadevicelib.HADeviceManager;
import cn.earhear.hadevicelib.HAParameter;
import cn.earhear.hadevicelib.HAParameterList;
import cn.earhear.hadevicelib.constant.OperationType;
import cn.earhear.hadevicelib.constant.SideSelect;
import io.reactivex.disposables.Disposable;

import static com.xiyoukeji.zhitingyun.ZhitingyunApplication.context;
import static com.xiyoukeji.zhitingyun.ZhitingyunApplication.deviceManager;

public class ChatMainActivity extends AppCompatActivity implements AVChatVideoUI.TouchZoneCallback, AVSwitchListener, OnFunctionClickListener {
    private boolean mIsInComingCall = false;// is incoming call or outgoing call
    private String receiverId = "13606531232"; // 对方的account
    private String displayName = "陈顶顶"; // 对方的显示昵称
    private int state = 2; // calltype 音频或视频
    private AVChatData avChatData = null; // config for connect video server
    AVChatController avChatController;
    View view;
    AVChatVideoUI avChatVideoUI;

    DeviceEntity entity;
    RecordEntity record;
    List<DeviceEntity> deviceEntities = new ArrayList<>();
    private static final String TYPE_SYS = "sys";
    private static final String TYPE_LEFT = "left";
    private static final String TYPE_RIGHT = "right";

    private int currentMemory;
    private List<HAParameterList> sysParameterList;
    private List<HAParameterList> memParameterList;
    private List<AllParameter> allParameterList;
    int index = 1;
    int proId=1;
    private Integer mOrderId;
    private Integer mClassify;
    private Integer mStatus;
    int professorId=0;
    private CountDownTimer mTimer;

    private Integer classify0;
    private Integer status0;


    MainViewModel mViewModel;
    List<DeviceInfo> deviceInfoList0,deviceInfoList1,deviceInfoList2,deviceInfoList3;
    DeviceInfo deviceInfo0,deviceInfo1,deviceInfo2,deviceInfo3;


    private int a1 = 0, a2 = 0, a3 = 0, a4 = 0, a5 = 0, b1 = 0, b2 = 0, b3 = 0, b4 = 0, b5 = 0, c1 = 0, c2 = 0, c3 = 0, c4 = 0, c5 = 0,
            d1 = 0, d2 = 0, d3 = 0, d4 = 0, d5 = 0, e1 = 0, e2 = 0, e3 = 0, e4 = 0, e5 = 0, f1 = 0, f2 = 0, f3 = 0, f4 = 0, f5 = 0, g1 = 0, g2 = 0, g3 = 0, g4 = 0, g5 = 0, h1 = 0, h2 = 0, h3 = 0, h4 = 0, h5 = 0;
    private int i1 = 0, i2 = 0, i3 = 0, i4 = 0, i5 = 0, j1 = 0, j2 = 0, j3 = 0, j4 = 0, j5 = 0, k1 = 0, k2 = 0, k3 = 0, k4 = 0, k5 = 0,
            l1 = 0, l2 = 0, l3 = 0, l4 = 0, l5 = 0, m1 = 0, m2 = 0, m3 = 0, m4 = 0, m5 = 0, n1 = 0, n2 = 0, n3 = 0, n4 = 0, n5 = 0, o1 = 0, o2 = 0, o3 = 0, o4 = 0, o5 = 0, p1 = 0, p2 = 0, p3 = 0, p4 = 0, p5 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Constant.INDEX =0;
        ViewModelFactory factory = ViewModelFactory.getInstance(ChatMainActivity.this.getApplication());
        mViewModel = ViewModelProviders.of(this, factory).get(MainViewModel.class);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_chat_main);
        view = findViewById(R.id.framelayout);
        avChatData = (AVChatData) getIntent().getSerializableExtra("avChatData");
        receiverId = getIntent().getStringExtra("receiverId");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            StatusBarUtil.setStatusBarColor(this, R.color.white); }


        if (avChatData != null)
            mIsInComingCall = true;
        dismissKeyguard();
        initData();
        showUI();
        registerObserves(true);
        mTimer=new CountDownTimer(600000000L,3000L) {
            @Override
            public void onTick(long millisUntilFinished) {
                if(ZhitingyunApplication.curState!=null) {
                    if (ZhitingyunApplication.deviceManager.isConnected( ZhitingyunApplication.curState.getDevMac() ) == false) {
                        mTimer.cancel();
                        AlertDialog.Builder builder = new AlertDialog.Builder( ChatMainActivity.this );
                        builder.setTitle("").setMessage("设备连接中断，为保证线上诊疗正常进行，请重新连接。")
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface arg0, int arg1) {
                                        arg0.dismiss();
                                        mTimer.start();

                                    }
                                })
                                .setPositiveButton("重新连接", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1) {
                                        SharedPreferences sharedPreferences0 = getSharedPreferences( "mySP", Context.MODE_PRIVATE );
                                        String address = sharedPreferences0.getString( "address", "" );
                                        ZhitingyunApplication.deviceManager.connectDevice( address );
                                        final ProgressDialog proDialog = android.app.ProgressDialog.show(ChatMainActivity.this,"","正在重新连接设备");
                                        Thread thread = new Thread()
                                        {
                                            public void run()
                                            {
                                                try
                                                {
                                                    sleep(4000);
                                                } catch (InterruptedException e)
                                                {
                                                    // TODO 自动生成的 catch 块
                                                    e.printStackTrace();
                                                }
                                                proDialog.dismiss();//万万不可少这句，否则会程序会卡死。
                                            }
                                        };
                                        thread.start();
                                    }
                                });
                        builder.setCancelable( false );
                        builder .show();

                    }
                }else {
                    mTimer.cancel();
                    AlertDialog.Builder builder = new AlertDialog.Builder( ChatMainActivity.this);
                    builder.setTitle("").setMessage("设备连接中断，为保证线上诊疗正常进行，请重新连接。")
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface arg0, int arg1) {
                                    arg0.dismiss();
                                    mTimer.start();
                                }
                            })
                            .setPositiveButton("重新连接", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    SharedPreferences sharedPreferences0 = getSharedPreferences( "mySP", Context.MODE_PRIVATE );
                                    String address = sharedPreferences0.getString( "address", "" );
                                    ZhitingyunApplication.deviceManager.connectDevice( address );

                                    final ProgressDialog proDialog = android.app.ProgressDialog.show(ChatMainActivity.this,"","正在重新连接设备");
                                    Thread thread = new Thread()
                                    {
                                        public void run()
                                        {
                                            try
                                            {
                                                sleep(4000);
                                            } catch (InterruptedException e)
                                            {
                                                // TODO 自动生成的 catch 块
                                                e.printStackTrace();
                                            }
                                            proDialog.dismiss();//万万不可少这句，否则会程序会卡死。
                                        }
                                    };
                                    thread.start();
                                }
                            });
                    builder.setCancelable( false );
                    builder .show();
                }
            }

            @Override
            public void onFinish() {
            }
        }.start();


    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void fillAllParameterList(int classify) {
        entity = new DeviceEntity();

        if (allParameterList == null)
            allParameterList = new ArrayList<>();
        else
            allParameterList.clear();

        if (ZhitingyunApplication.curState.getHaSide() == SideSelect.S_BINAURAL) {
            for (HAParameter parameter : sysParameterList.get(0).getParameterList()) {
                AllParameter tmp = new AllParameter(parameter, TYPE_SYS);
                allParameterList.add(tmp);

                for (int i = 0; i < allParameterList.size(); i++) {
                    if (parameter.getId().equals("HA_SW")) {
                    } else if (parameter.getId().equals("HA_DELAY")) {
                        entity.setP_S_DL(parameter.getValue());
                    } else if (parameter.getId().equals("HA_ALRM")) {
                    }
                }
            }

            for (final HAParameter parameter : memParameterList.get(SideSelect.S_UNILATERAL_LEFT).getParameterList()) {
                AllParameter tmp = new AllParameter(parameter, TYPE_LEFT);
                allParameterList.add(tmp);
                entity.setClassify(classify);
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
                        f2 =parameter.getValue();
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
                }

            }

            for (final HAParameter parameter : memParameterList.get(SideSelect.S_UNILATERAL_RIGHT).getParameterList()) {
                AllParameter tmp = new AllParameter(parameter, TYPE_RIGHT);
                allParameterList.add(tmp);

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
                    n1 =parameter.getValue();
                } else if (parameter.getId().equals("CH_EXPS_1")) {
                    n2 =parameter.getValue();
                } else if (parameter.getId().equals("CH_EXPS_2")) {
                    n3 =parameter.getValue();
                } else if (parameter.getId().equals("CH_EXPS_3")) {
                    n4 =parameter.getValue();
                } else if (parameter.getId().equals("CH_EXPS_4")) {
                    n5 =parameter.getValue();
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


            }
            entity.setHertz("125,250,500,750,1000,1500,2000,3000,4000,6000,8000");

        deviceEntities.add( entity );




        Log.d("aaa", "sss");

    }


    private void initData() {
        avChatController = new AVChatController(ChatMainActivity.this, avChatData);
        avChatVideoUI = new AVChatVideoUI(mIsInComingCall, ChatMainActivity.this, view, avChatData, displayName, avChatController, this, this, this);
    }


    Observer<AVChatControlEvent> callControlObserver = new Observer<AVChatControlEvent>() {
        @Override
        public void onEvent(AVChatControlEvent netCallControlNotification) {
            handleCallControl(netCallControlNotification);
        }
    };

    private void registerObserves(boolean register) {
        AVChatManager.getInstance().observeAVChatState(avchatStateObserver, register);//视频录制
        AVChatManager.getInstance().observeHangUpNotification(callHangupObserver, register);
        AVChatManager.getInstance().observeCalleeAckNotification(callAckObserver, register);
        AVChatManager.getInstance().observeControlNotification(callControlObserver, register);
        AVChatTimeoutObserver.getInstance().observeTimeoutNotification(timeoutObserver, register, mIsInComingCall);
        PhoneCallStateObserver.getInstance().observeAutoHangUpForLocalPhone(autoHangUpForLocalPhoneObserver, register);
        NIMClient.getService(AuthServiceObserver.class).observeOnlineStatus(userStatusObserver, register);
    }

    Observer<StatusCode> userStatusObserver = new Observer<StatusCode>() {

        @Override
        public void onEvent(StatusCode code) {
            if (code.wontAutoLogin()) {
                AVChatSoundPlayer.instance().stop();
                //logout  finish
            }
        }
    };


    private void dismissKeyguard() {
        ChatMainActivity.this.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                        WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
                        WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
                        WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
        );
    }

    Observer<Integer> autoHangUpForLocalPhoneObserver = new Observer<Integer>() {
        @Override
        public void onEvent(Integer integer) {
            avChatController.onHangUp(AVChatExitCode.PEER_BUSY);
        }
    };

    private void showUI() {

        if (mIsInComingCall) {
            // 来电
            AVChatSoundPlayer.instance().play(AVChatSoundPlayer.RingerTypeEnum.RING);
            avChatVideoUI.showIncomingCall(avChatData);
            mViewModel.getPersonId(avChatData.getAccount(), new BaseObserver<Model0<AccidEntity>>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(Model0<AccidEntity> accidEntityModel0) {
                    deviceEntities.clear();
                    professorId = accidEntityModel0.getComeback().getPersonId();
                    SharedPreferences sharedPreferences = getSharedPreferences("mySP", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("videoheadpic", accidEntityModel0.getComeback().getPersonHeadPic());
                    editor.putString("videoname", accidEntityModel0.getComeback().getPersonName());
                    editor.commit();

                    mViewModel.getRecent( "", new BaseObserver<Model0<RecordEntity>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(Model0<RecordEntity> recordEntityModel0) {
                            mViewModel.mRecent.set( recordEntityModel0 );
                            mOrderId=recordEntityModel0.getComeback().getOrderId();
                            mClassify=recordEntityModel0.getComeback().getClassify();
                            mStatus=recordEntityModel0.getComeback().getStatus();
                        }
                    } );
                }
            });
        } else {
            // 去电
            AVChatSoundPlayer.instance().play(AVChatSoundPlayer.RingerTypeEnum.CONNECTING);
            avChatVideoUI.doOutgoingCall(receiverId);
        }
    }

    private void handleCallControl(AVChatControlEvent notification) {
        if (AVChatManager.getInstance().getCurrentChatId() != notification.getChatId()) {
            return;
        }
        switch (notification.getControlCommand()) {
            case AVChatControlCommand.SWITCH_AUDIO_TO_VIDEO:
                avChatVideoUI.incomingAudioToVideo();

                break;
            case AVChatControlCommand.SWITCH_AUDIO_TO_VIDEO_AGREE:
                // 对方同意切成视频啦
                state = AVChatType.VIDEO.getValue();
                avChatVideoUI.onAudioToVideoAgree(notification.getAccount());
                break;
            case AVChatControlCommand.SWITCH_AUDIO_TO_VIDEO_REJECT:
//                rejectAudioToVideo();
                Toast.makeText(ChatMainActivity.this, R.string.avchat_switch_video_reject, Toast.LENGTH_SHORT).show();
                break;
            case AVChatControlCommand.SWITCH_VIDEO_TO_AUDIO:
                onVideoToAudio();
                break;
            case AVChatControlCommand.NOTIFY_VIDEO_OFF:
                // 收到对方关闭画面通知
                if (state == AVChatType.VIDEO.getValue()) {
                    avChatVideoUI.peerVideoOff();
                }
                break;
            case AVChatControlCommand.NOTIFY_VIDEO_ON:
                // 收到对方打开画面通知
                if (state == AVChatType.VIDEO.getValue()) {
                    avChatVideoUI.peerVideoOn();
                }
                break;
            default:
                Toast.makeText(ChatMainActivity.this, "对方发来指令值：" + notification.getControlCommand(), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    // 通话过程中，收到对方挂断电话
    Observer<AVChatCommonEvent> callHangupObserver = new Observer<AVChatCommonEvent>() {
        @Override
        public void onEvent(AVChatCommonEvent avChatHangUpInfo) {
            avChatData = avChatController.getAvChatData();
            mViewModel.orderStatus( mOrderId, mClassify, new BaseObserver<Model0<RecordEntity>>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(Model0<RecordEntity> recordEntityModel0) {
                    mViewModel.mOrderStatus.set( recordEntityModel0 );
                    classify0 = recordEntityModel0.getComeback().getClassify();
                    status0 = recordEntityModel0.getComeback().getStatus();
//                        if(classify0==1&&status0==3){
                    Intent intent = new Intent( ChatMainActivity.this, EvaluationActivity.class );
                    intent.putExtra( "evaluateId", recordEntityModel0.getComeback().getOrderId() );
                    intent.putExtra( "evaluateClassify", recordEntityModel0.getComeback().getClassify() );
                    intent.putExtra( "evaluateHeadpic", recordEntityModel0.getComeback().getProHeadPic() );
                    intent.putExtra( "evaluateProId", recordEntityModel0.getComeback().getProId() );
                    intent.putExtra( "evaluateName", recordEntityModel0.getComeback().getProName() );
                    intent.putExtra( "evaluateStatus", recordEntityModel0.getComeback().getStatus() );
                    if (recordEntityModel0.getComeback().getClassify() == 1) {
                        if (recordEntityModel0.getComeback().getStatus() == 3) {
                            startActivity( intent );
                            ToastUtils.showLong( "专家正在填写报告，请您先填写诊疗评估。" );
                        }
                    } else if (recordEntityModel0.getComeback().getClassify() == 2) {
                        if (recordEntityModel0.getComeback().getStatus() == 4) {
                            startActivity( intent );
                            ToastUtils.showLong( "专家正在填写报告，请您先填写诊疗评估。" );
                        }
                    }
                }
            } );
            avChatController.onHangUp(AVChatExitCode.HANGUP);
        }
    };

    // 呼叫时，被叫方的响应（接听、拒绝、忙）
    Observer<AVChatCalleeAckEvent> callAckObserver = new Observer<AVChatCalleeAckEvent>() {
        @Override
        public void onEvent(AVChatCalleeAckEvent ackInfo) {
            AVChatData info = avChatController.getAvChatData();
            if (info != null && info.getChatId() == ackInfo.getChatId()) {
                AVChatSoundPlayer.instance().stop();
                if (ackInfo.getEvent() == AVChatEventType.CALLEE_ACK_AGREE) {
                    avChatVideoUI.startCamera();


                } else if (ackInfo.getEvent() == AVChatEventType.CALLEE_ACK_BUSY) {

                    AVChatSoundPlayer.instance().play(AVChatSoundPlayer.RingerTypeEnum.PEER_BUSY);

                    avChatController.onHangUp(AVChatExitCode.PEER_BUSY);
                } else if (ackInfo.getEvent() == AVChatEventType.CALLEE_ACK_REJECT) {
                    avChatController.onHangUp(AVChatExitCode.REJECT);
                } else if (ackInfo.getEvent() == AVChatEventType.CALLEE_ACK_AGREE) {
                    avChatController.isCallEstablish.set(true);
                }
            }

        }

    };

    protected void handleWithConnectServerResult(int auth_result) {
        if (auth_result == 200) {
//            LogUtil.d(TAG, "onConnectServer success");
        } else if (auth_result == 101) { // 连接超时
            avChatController.showQuitToast(AVChatExitCode.PEER_NO_RESPONSE);
        } else if (auth_result == 401) { // 验证失败
            avChatController.showQuitToast(AVChatExitCode.CONFIG_ERROR);
        } else if (auth_result == 417) { // 无效的channelId
            avChatController.showQuitToast(AVChatExitCode.INVALIDE_CHANNELID);
        } else { // 连接服务器错误，直接退出
            avChatController.showQuitToast(AVChatExitCode.CONFIG_ERROR);
        }
    }

    private SimpleAVChatStateObserver avchatStateObserver = new SimpleAVChatStateObserver() {
        @Override
        public void onAVRecordingCompletion(String account, String filePath) {
            if (account != null && filePath != null && filePath.length() > 0) {
                String msg = "音视频录制已结束, " + "账号：" + account + " 录制文件已保存至：" + filePath;
            } else {
            }

        }

        @Override
        public void onAudioRecordingCompletion(String filePath) {
            if (filePath != null && filePath.length() > 0) {
                String msg = "音频录制已结束, 录制文件已保存至：" + filePath;
            } else {
            }

        }

        @Override
        public void onLowStorageSpaceWarning(long availableSize) {
            if (state == AVChatType.VIDEO.getValue()) {
//                avChatVideoUI.showRecordWarning();
            } else {
//                avChatAudioUI.showRecordWarning();
            }
        }

        @Override
        public void onJoinedChannel(int code, String audioFile, String videoFile, int i) {
            handleWithConnectServerResult(code);
        }

        @Override
        public void onUserJoined(String account) {
            if (state == AVChatType.VIDEO.getValue()) {
                avChatVideoUI.initLargeSurfaceView(account);
            }
        }

        @Override
        public void onUserLeave(String account, int event) {
            avChatController.hangUp(AVChatExitCode.HANGUP);
        }

        @Override
        public void onCallEstablished() {
            //移除超时监听
            AVChatTimeoutObserver.getInstance().observeTimeoutNotification(timeoutObserver, false, mIsInComingCall);
            if (avChatController.getTimeBase() == 0)
                avChatController.setTimeBase(SystemClock.elapsedRealtime());

            if (state == AVChatType.AUDIO.getValue()) {
//                avChatAudioUI.showAudioInitLayout();
            } else {
                // 接通以后，自己是小屏幕显示图像，对方是大屏幕显示图像
                avChatVideoUI.initLargeSurfaceView(SPUtil.get(ChatMainActivity.this, "phone", "").toString());
                avChatVideoUI.initSmallSurfaceView(SPUtil.get(ChatMainActivity.this, "phone", "").toString());
//                avChatVideoUI.showVideoInitLayout();
            }
        }

        @Override
        public boolean onVideoFrameFilter(AVChatVideoFrame frame, boolean maybeDualInput) {


            return true;
        }

        @Override
        public boolean onAudioFrameFilter(AVChatAudioFrame frame) {
            return true;
        }

    };

    private Handler handler = new Handler(  ){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage( msg );
            switch (msg.what){
                case 0:
                    StaticConfigCallback.getInStanceBlock().setActivityFlag(5);
                    ZhitingyunApplication.deviceManager.setCurrentSelect( 2);
                    break;
                case 1:
                    StaticConfigCallback.getInStanceBlock().setActivityFlag(6);
                    ZhitingyunApplication.deviceManager.setCurrentSelect( 3 );
                    break;
                case 2:
                    StaticConfigCallback.getInStanceBlock().setActivityFlag(7);

                    ZhitingyunApplication.deviceManager.setCurrentSelect( 4 );
                    break;
                case 3:
                    Gson gson = new Gson();
                    String session1 = gson.toJson(deviceEntities);
                    Log.d( "vvv",session1 );

                    WS.getInStanceBlock().send("{\"classify\":" + "2" + ",\"id\":" +professorId + ",\"EquipmentParam\":" + session1 + "}");
                    break;
            }
        }
    };

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {

        switch (messageEvent.getFlag()){
            case 0://onConnectSuccess
                break;
            case 1://onDisconnect

                break;
            case 2://onFail
                break;
            case 3://onDetecting
                switch (messageEvent.getActivityFlag()){
                    case 4:
                        deviceInfo0 = (DeviceInfo) messageEvent.getObject();
                        break;
                    case 5:
                        deviceInfo1 = (DeviceInfo) messageEvent.getObject();
                        break;
                    case 6:
                        deviceInfo2 = (DeviceInfo) messageEvent.getObject();
                        break;
                    case 7:
                        deviceInfo3 = (DeviceInfo) messageEvent.getObject();
                        break;

                }
                break;
            case 4://onDetectFinish
                switch (messageEvent.getActivityFlag()){
                    case 4:
                        deviceInfoList0 = (List<DeviceInfo>) messageEvent.getObject();
                        break;
                    case 5:
                        deviceInfoList1 = (List<DeviceInfo>) messageEvent.getObject();
                        break;
                    case 6:
                        deviceInfoList2 = (List<DeviceInfo>) messageEvent.getObject();
                        break;
                    case 7:
                        deviceInfoList3 = (List<DeviceInfo>) messageEvent.getObject();
                        break;

                }
                break;
            case 5://onFail
            case 6:
                switch (messageEvent.getActivityFlag()){
                    case 4:
                        switch ((Integer) messageEvent.getObject()) {
                            case OperationType.OP_AUTO_CHECK:
                            case OperationType.OP_SELECT_MEMORY:
                                Log.d( "vvv","read0" );
                                ZhitingyunApplication.deviceManager.readDevice();
                                break;
                            case OperationType.OP_READ_CONFIG:
                                Log.d( "vvv","finish0" );
                                currentMemory = ZhitingyunApplication.deviceManager.getCurrentMemory();
                                sysParameterList = ZhitingyunApplication.deviceManager.getSysParameterList();
                                memParameterList = ZhitingyunApplication.deviceManager.getMemBinParameterList();



                                fillAllParameterList(1);
                                Message message = new Message();
                                message.what=0;
                                handler.sendMessageDelayed( message,100 );


                                break;
                            default:
                                break;
                        }
                        break;
                    case 5:
                        switch ((Integer) messageEvent.getObject()) {
                            case OperationType.OP_AUTO_CHECK:
                            case OperationType.OP_SELECT_MEMORY:
                                Log.d( "vvv","read1" );
                                ZhitingyunApplication.deviceManager.readDevice();
                                break;
                            case OperationType.OP_READ_CONFIG:
                                Log.d( "vvv","finish1" );
                                currentMemory = ZhitingyunApplication.deviceManager.getCurrentMemory();
                                sysParameterList = ZhitingyunApplication.deviceManager.getSysParameterList();
                                memParameterList = ZhitingyunApplication.deviceManager.getMemBinParameterList();
                                fillAllParameterList(2);
                                Message message = new Message();
                                message.what=1;
                                handler.sendMessageDelayed( message,100 );



                                break;
                            default:
                                break;
                        }
                        break;
                    case 6:
                        switch ((Integer) messageEvent.getObject()) {
                            case OperationType.OP_AUTO_CHECK:
                            case OperationType.OP_SELECT_MEMORY:
                                Log.d( "vvv","read2" );
                                ZhitingyunApplication.deviceManager.readDevice();
                                break;
                            case OperationType.OP_READ_CONFIG:
                                Log.d( "vvv","finish2" );
                                currentMemory = ZhitingyunApplication.deviceManager.getCurrentMemory();
                                sysParameterList = ZhitingyunApplication.deviceManager.getSysParameterList();
                                memParameterList = ZhitingyunApplication.deviceManager.getMemBinParameterList();
                                fillAllParameterList(3);
                                Message message = new Message();
                                message.what=2;
                                handler.sendMessageDelayed( message,100 );

                                break;
                            default:
                                break;
                        }
                        break;
                    case 7:
                        switch ((Integer) messageEvent.getObject()) {
                            case OperationType.OP_AUTO_CHECK:
                            case OperationType.OP_SELECT_MEMORY:
                                Log.d( "vvv","read3" );
                                ZhitingyunApplication.deviceManager.readDevice();
                                break;
                            case OperationType.OP_READ_CONFIG:
                                Log.d( "vvv","finish3" );
                                currentMemory = ZhitingyunApplication.deviceManager.getCurrentMemory();
                                sysParameterList = ZhitingyunApplication.deviceManager.getSysParameterList();
                                memParameterList = ZhitingyunApplication.deviceManager.getMemBinParameterList();
                                fillAllParameterList(4);
                                Message message = new Message();
                                message.what=3;
                                handler.sendMessageDelayed( message,100 );

                                break;
                            default:
                                break;
                        }
                        break;

                }
                break;

            case 7:
                Log.d( "ttt","failed" );
                if(messageEvent.getActivityFlag()==  4){
                    switch ((Integer) messageEvent.getObject()) {
                        case OperationType.OP_AUTO_CHECK:
                            if (deviceInfoList0!=null)
                                ZhitingyunApplication.deviceManager.disconnectDevice(deviceInfoList0.get( 0 ));
                            break;
                        case OperationType.OP_READ_CONFIG:
                            Log.d( "vvv","fail0" );
                            ZhitingyunApplication.deviceManager.readDevice();
                            break;
                        case OperationType.OP_SELECT_MEMORY:
                            break;
                        default:
                            break;
                    }
                }
                else if(messageEvent.getActivityFlag()==5){
                    switch ((Integer) messageEvent.getObject()) {
                        case OperationType.OP_AUTO_CHECK:
                            if (deviceInfoList1!=null)
                                ZhitingyunApplication.deviceManager.disconnectDevice(deviceInfoList1.get( 0 ));
                            break;
                        case OperationType.OP_READ_CONFIG:
                            Log.d( "vvv","fail1" );
                            ZhitingyunApplication.deviceManager.readDevice();
                            break;
                        case OperationType.OP_SELECT_MEMORY:
                            break;
                        default:
                            break;
                    }
                }
                else if(messageEvent.getActivityFlag()==6){
                    switch ((Integer) messageEvent.getObject()) {
                        case OperationType.OP_AUTO_CHECK:
                            if (deviceInfoList2!=null)
                                ZhitingyunApplication.deviceManager.disconnectDevice(deviceInfoList2.get( 0 ));
                            break;
                        case OperationType.OP_READ_CONFIG:
                            Log.d( "vvv","fail2" );
                            ZhitingyunApplication.deviceManager.readDevice();
                            break;
                        case OperationType.OP_SELECT_MEMORY:
                            break;
                        default:
                            break;
                    }
                }
                else if(messageEvent.getActivityFlag()==7){
                    switch ((Integer) messageEvent.getObject()) {
                        case OperationType.OP_AUTO_CHECK:
                            if (deviceInfoList3!=null)
                                ZhitingyunApplication.deviceManager.disconnectDevice(deviceInfoList3.get( 0 ));
                            break;
                        case OperationType.OP_READ_CONFIG:
                            Log.d( "vvv","fail3" );
                            ZhitingyunApplication.deviceManager.readDevice();
                            break;
                        case OperationType.OP_SELECT_MEMORY:
                            break;
                        default:
                            break;
                    }
                }


        }



        if (messageEvent.getFlag() == 100) {
            if (!messageEvent.getObject().toString().equals("pong")) {

                SocketMessage socketMessage = new Gson().fromJson(messageEvent.getObject().toString(), SocketMessage.class);
                if (socketMessage.getCode() == 10087) {
                    if (socketMessage.getMessage().getKey().startsWith("l")) {
                        for (final HAParameter parameter : memParameterList.get(SideSelect.S_UNILATERAL_LEFT).getParameterList()) {
                            if (parameter.getId().equals("VOL") && socketMessage.getMessage().getKey().equals("l_P_VC_VALUE")) {
                                parameter.setValue(((Double) socketMessage.getMessage().getValue()).intValue());
                                deviceManager.setCurrentSelect(socketMessage.getMessage().getClassify());
                                deviceManager.writeDevice(currentMemory);

                            }
                        }

                    } else if (socketMessage.getMessage().getKey().startsWith("r")) {
                        for (final HAParameter parameter : memParameterList.get(SideSelect.S_UNILATERAL_RIGHT).getParameterList()) {
                            if (parameter.getId().equals("VOL") && socketMessage.getMessage().getKey().equals("r_P_VC_VALUE")) {
                                parameter.setValue(((Double) socketMessage.getMessage().getValue()).intValue());
                                deviceManager.setCurrentSelect(socketMessage.getMessage().getClassify());
                                deviceManager.writeDevice(currentMemory);
                            }
                        }
                    }
                    else if (socketMessage.getMessage().getKey().startsWith("l")) {
                        for (final HAParameter parameter : memParameterList.get(SideSelect.S_UNILATERAL_LEFT).getParameterList()) {
                            if (parameter.getId().equals("BASS") && socketMessage.getMessage().getKey().equals("l_P_BASS_VALUE")) {
                                parameter.setValue(((Double) socketMessage.getMessage().getValue()).intValue());
                                deviceManager.setCurrentSelect(socketMessage.getMessage().getClassify());
                                deviceManager.writeDevice(currentMemory);
//                                break;
                            }
                        }
                    }
                    else if (socketMessage.getMessage().getKey().startsWith("l")) {
                        for (final HAParameter parameter : memParameterList.get(SideSelect.S_UNILATERAL_LEFT).getParameterList()) {
                            if (parameter.getId().equals("TREBLE") && socketMessage.getMessage().getKey().equals("l_P_TRB_VALUE")) {
                                parameter.setValue(((Double) socketMessage.getMessage().getValue()).intValue());
                                deviceManager.setCurrentSelect(socketMessage.getMessage().getClassify());
                                deviceManager.writeDevice(currentMemory);
                            }
                        }
                    }
                    else if (socketMessage.getMessage().getKey().startsWith("r")) {
                        for (final HAParameter parameter : memParameterList.get(SideSelect.S_UNILATERAL_RIGHT).getParameterList()) {
                            if (parameter.getId().equals("BASS") && socketMessage.getMessage().getKey().equals("r_P_BASS_VALUE")) {
                                parameter.setValue(((Double) socketMessage.getMessage().getValue()).intValue());
                                deviceManager.setCurrentSelect(socketMessage.getMessage().getClassify());
                                deviceManager.writeDevice(currentMemory);
                            }
                        }
                    }
                    else if (socketMessage.getMessage().getKey().startsWith("r")) {
                        for (final HAParameter parameter : memParameterList.get(SideSelect.S_UNILATERAL_RIGHT).getParameterList()) {
                            if (parameter.getId().equals("TREBLE") && socketMessage.getMessage().getKey().equals("r_P_TRB_VALUE")) {
                                parameter.setValue(((Double) socketMessage.getMessage().getValue()).intValue());
                                deviceManager.setCurrentSelect(socketMessage.getMessage().getClassify());
                                deviceManager.writeDevice(currentMemory);
                            }
                        }
                    }
                    else if (socketMessage.getMessage().getKey().startsWith("r")) {
                        for (final HAParameter parameter : memParameterList.get(SideSelect.S_UNILATERAL_RIGHT).getParameterList()) {
                            if (parameter.getId().equals("CH_NR_LVL") && socketMessage.getMessage().getKey().equals("r_P_NRLVL_VALUE")) {
                                parameter.setValue(((Double) socketMessage.getMessage().getValue()).intValue());
                                deviceManager.setCurrentSelect(socketMessage.getMessage().getClassify());
                                deviceManager.writeDevice(currentMemory);
                            }
                        }
                    }
                    else if (socketMessage.getMessage().getKey().startsWith("l")) {
                        for (final HAParameter parameter : memParameterList.get(SideSelect.S_UNILATERAL_LEFT).getParameterList()) {
                            if (parameter.getId().equals("CH_NR_LVL") && socketMessage.getMessage().getKey().equals("r_P_NRLVL_VALUE")) {
                                parameter.setValue(((Double) socketMessage.getMessage().getValue()).intValue());
                                deviceManager.setCurrentSelect(socketMessage.getMessage().getClassify());
                                deviceManager.writeDevice(currentMemory);
                            }
                        }
                    }
                    else if (socketMessage.getMessage().getKey().startsWith("r")) {
                        for (final HAParameter parameter : memParameterList.get(SideSelect.S_UNILATERAL_RIGHT).getParameterList()) {
                            if (parameter.getId().equals("CH_AFS_LVL") && socketMessage.getMessage().getKey().equals("r_P_AFSLVL_VALUE")) {
                                parameter.setValue(((Double) socketMessage.getMessage().getValue()).intValue());
                                deviceManager.setCurrentSelect(socketMessage.getMessage().getClassify());
                                deviceManager.writeDevice(currentMemory);
                            }
                        }
                    }
                    else if (socketMessage.getMessage().getKey().startsWith("l")) {
                        for (final HAParameter parameter : memParameterList.get(SideSelect.S_UNILATERAL_LEFT).getParameterList()) {
                            if (parameter.getId().equals("CH_AFS_LVL") && socketMessage.getMessage().getKey().equals("l_P_AFSLVL_VALUE")) {
                                parameter.setValue(((Double) socketMessage.getMessage().getValue()).intValue());
                                deviceManager.setCurrentSelect(socketMessage.getMessage().getClassify());
                                deviceManager.writeDevice(currentMemory);
                                break;
                            }
                        }
                    }


                    else if (socketMessage.getMessage().getKey().startsWith("r")) {
                        for (final HAParameter parameter : memParameterList.get(SideSelect.S_UNILATERAL_RIGHT).getParameterList()) {
                            if ( socketMessage.getMessage().getKey().equals("r_P_CHSG")) {
                                 String parm=String.valueOf( socketMessage.getMessage().getValue());
                                 String[] array= parm.split( "," );
                                 String k0=array[0];
                                 String k1=array[1];
                                 String k2=array[2];
                                 String k3=array[3];
                                 String k4=array[4];
                                 if(parameter.getId().equals( "CH_SOFT_GAIN_0" )){
                                     parameter.setValue( Integer.valueOf( k0));
                                 }
                                 if(parameter.getId().equals( "CH_SOFT_GAIN_1" )){
                                     parameter.setValue( Integer.valueOf( k1));
                                 }
                                 if(parameter.getId().equals( "CH_SOFT_GAIN_2" )){
                                     parameter.setValue( Integer.valueOf( k2));
                                 }
                                 if(parameter.getId().equals( "CH_SOFT_GAIN_3" )){
                                    parameter.setValue( Integer.valueOf( k3));
                                 }
                                 if(parameter.getId().equals( "CH_SOFT_GAIN_4" )){
                                    parameter.setValue( Integer.valueOf( k4));
                                 }
                                 deviceManager.setCurrentSelect(socketMessage.getMessage().getClassify());
                                 deviceManager.writeDevice(currentMemory);
                            }
                        }
                    }
                    else if (socketMessage.getMessage().getKey().startsWith("l")) {
                        for (final HAParameter parameter : memParameterList.get(SideSelect.S_UNILATERAL_LEFT).getParameterList()) {
                            if ( socketMessage.getMessage().getKey().equals("l_P_CHSG")) {
                                String parm=String.valueOf( socketMessage.getMessage().getValue());
                                String[] array= parm.split( "," );
                                String k0=array[0];
                                String k1=array[1];
                                String k2=array[2];
                                String k3=array[3];
                                String k4=array[4];
                                if(parameter.getId().equals( "CH_SOFT_GAIN_0" )){
                                    parameter.setValue( Integer.valueOf( k0));
                                }
                                if(parameter.getId().equals( "CH_SOFT_GAIN_1" )){
                                    parameter.setValue( Integer.valueOf( k1));
                                }
                                if(parameter.getId().equals( "CH_SOFT_GAIN_2" )){
                                    parameter.setValue( Integer.valueOf( k2));
                                }
                                if(parameter.getId().equals( "CH_SOFT_GAIN_3" )){
                                    parameter.setValue( Integer.valueOf( k3));
                                }
                                if(parameter.getId().equals( "CH_SOFT_GAIN_4" )){
                                    parameter.setValue( Integer.valueOf( k4));
                                }
                                deviceManager.setCurrentSelect(socketMessage.getMessage().getClassify());
                                deviceManager.writeDevice(currentMemory);
                            }
                        }
                    }


                    else if (socketMessage.getMessage().getKey().startsWith("r")) {
                        for (final HAParameter parameter : memParameterList.get(SideSelect.S_UNILATERAL_RIGHT).getParameterList()) {
                            if ( socketMessage.getMessage().getKey().equals("r_P_CHMPO")) {
                                String parm=String.valueOf( socketMessage.getMessage().getValue());
                                String[] array= parm.split( "," );
                                String k0=array[0];
                                String k1=array[1];
                                String k2=array[2];
                                String k3=array[3];
                                String k4=array[4];
                                if(parameter.getId().equals( "CH_MPO_0" )){
                                    parameter.setValue( Integer.valueOf( k0));
                                }
                                if(parameter.getId().equals( "CH_MPO_1" )){
                                    parameter.setValue( Integer.valueOf( k1));
                                }
                                if(parameter.getId().equals( "CH_MPO_2" )){
                                    parameter.setValue( Integer.valueOf( k2));
                                }
                                if(parameter.getId().equals( "CH_MPO_3" )){
                                    parameter.setValue( Integer.valueOf( k3));
                                }
                                if(parameter.getId().equals( "CH_MPO_4" )){
                                    parameter.setValue( Integer.valueOf( k4));
                                }
                                deviceManager.setCurrentSelect(socketMessage.getMessage().getClassify());
                                deviceManager.writeDevice(currentMemory);
                            }
                        }
                    }
                    else if (socketMessage.getMessage().getKey().startsWith("l")) {
                        for (final HAParameter parameter : memParameterList.get(SideSelect.S_UNILATERAL_LEFT).getParameterList()) {
                            if ( socketMessage.getMessage().getKey().equals("l_P_CHMPO")) {
                                String parm=String.valueOf( socketMessage.getMessage().getValue());
                                String[] array= parm.split( "," );
                                String k0=array[0];
                                String k1=array[1];
                                String k2=array[2];
                                String k3=array[3];
                                String k4=array[4];
                                if(parameter.getId().equals( "CH_MPO_0" )){
                                    parameter.setValue( Integer.valueOf( k0));
                                }
                                if(parameter.getId().equals( "CH_MPO_1" )){
                                    parameter.setValue( Integer.valueOf( k1));
                                }
                                if(parameter.getId().equals( "CH_MPO_2" )){
                                    parameter.setValue( Integer.valueOf( k2));
                                }
                                if(parameter.getId().equals( "CH_MPO_3" )){
                                    parameter.setValue( Integer.valueOf( k3));
                                }
                                if(parameter.getId().equals( "CH_MPO_4" )){
                                    parameter.setValue( Integer.valueOf( k4));
                                }
                                deviceManager.setCurrentSelect(socketMessage.getMessage().getClassify());
                                deviceManager.writeDevice(currentMemory);
                            }
                        }
                    }

                    else if (socketMessage.getMessage().getKey().startsWith("r")) {
                        for (final HAParameter parameter : memParameterList.get(SideSelect.S_UNILATERAL_RIGHT).getParameterList()) {
                            if ( socketMessage.getMessage().getKey().equals("r_P_CHSW")) {
                                String parm=String.valueOf( socketMessage.getMessage().getValue());
                                String[] array= parm.split( "," );
                                String k0=array[0];
                                String k1=array[1];
                                String k2=array[2];
                                String k3=array[3];
                                String k4=array[4];
                                if(parameter.getId().equals( "CH_CMPO_0" )){
                                    parameter.setValue( Integer.valueOf( k0));
                                }
                                if(parameter.getId().equals( "CH_CMPO_1" )){
                                    parameter.setValue( Integer.valueOf( k1));
                                }
                                if(parameter.getId().equals( "CH_CMPO_2" )){
                                    parameter.setValue( Integer.valueOf( k2));
                                }
                                if(parameter.getId().equals( "CH_CMPO_3" )){
                                    parameter.setValue( Integer.valueOf( k3));
                                }
                                if(parameter.getId().equals( "CH_CMPO_4" )){
                                    parameter.setValue( Integer.valueOf( k4));
                                }
                                deviceManager.setCurrentSelect(socketMessage.getMessage().getClassify());
                                deviceManager.writeDevice(currentMemory);
                            }
                        }
                    }
                    else if (socketMessage.getMessage().getKey().startsWith("l")) {
                        for (final HAParameter parameter : memParameterList.get(SideSelect.S_UNILATERAL_LEFT).getParameterList()) {
                            if ( socketMessage.getMessage().getKey().equals("l_P_CHSW")) {
                                String parm=String.valueOf( socketMessage.getMessage().getValue());
                                String[] array= parm.split( "," );
                                String k0=array[0];
                                String k1=array[1];
                                String k2=array[2];
                                String k3=array[3];
                                String k4=array[4];
                                if(parameter.getId().equals( "CH_CMPO_0" )){
                                    parameter.setValue( Integer.valueOf( k0));
                                }
                                if(parameter.getId().equals( "CH_CMPO_1" )){
                                    parameter.setValue( Integer.valueOf( k1));
                                }
                                if(parameter.getId().equals( "CH_CMPO_2" )){
                                    parameter.setValue( Integer.valueOf( k2));
                                }
                                if(parameter.getId().equals( "CH_CMPO_3" )){
                                    parameter.setValue( Integer.valueOf( k3));
                                }
                                if(parameter.getId().equals( "CH_CMPO_4" )){
                                    parameter.setValue( Integer.valueOf( k4));
                                }
                                deviceManager.setCurrentSelect(socketMessage.getMessage().getClassify());
                                deviceManager.writeDevice(currentMemory);
                            }
                        }
                    }

                    else if (socketMessage.getMessage().getKey().startsWith("r")) {
                        for (final HAParameter parameter : memParameterList.get(SideSelect.S_UNILATERAL_RIGHT).getParameterList()) {
                            if ( socketMessage.getMessage().getKey().equals("r_P_CHNG")) {
                                String parm=String.valueOf( socketMessage.getMessage().getValue());
                                String[] array= parm.split( "," );
                                String k0=array[0];
                                String k1=array[1];
                                String k2=array[2];
                                String k3=array[3];
                                String k4=array[4];
                                if(parameter.getId().equals( "CH_NORM_GAIN_0" )){
                                    parameter.setValue( Integer.valueOf( k0));
                                }
                                if(parameter.getId().equals( "CH_NORM_GAIN_1" )){
                                    parameter.setValue( Integer.valueOf( k1));
                                }
                                if(parameter.getId().equals( "CH_NORM_GAIN_2" )){
                                    parameter.setValue( Integer.valueOf( k2));
                                }
                                if(parameter.getId().equals( "CH_NORM_GAIN_3" )){
                                    parameter.setValue( Integer.valueOf( k3));
                                }
                                if(parameter.getId().equals( "CH_NORM_GAIN_4" )){
                                    parameter.setValue( Integer.valueOf( k4));
                                }
                                deviceManager.setCurrentSelect(socketMessage.getMessage().getClassify());
                                deviceManager.writeDevice(currentMemory);
                            }
                        }
                    }
                    else if (socketMessage.getMessage().getKey().startsWith("l")) {
                        for (final HAParameter parameter : memParameterList.get(SideSelect.S_UNILATERAL_LEFT).getParameterList()) {
                            if ( socketMessage.getMessage().getKey().equals("l_P_CHNG")) {
                                String parm=String.valueOf( socketMessage.getMessage().getValue());
                                String[] array= parm.split( "," );
                                String k0=array[0];
                                String k1=array[1];
                                String k2=array[2];
                                String k3=array[3];
                                String k4=array[4];
                                if(parameter.getId().equals( "CH_NORM_GAIN_0" )){
                                    parameter.setValue( Integer.valueOf( k0));
                                }
                                if(parameter.getId().equals( "CH_NORM_GAIN_1" )){
                                    parameter.setValue( Integer.valueOf( k1));
                                }
                                if(parameter.getId().equals( "CH_NORM_GAIN_2" )){
                                    parameter.setValue( Integer.valueOf( k2));
                                }
                                if(parameter.getId().equals( "CH_NORM_GAIN_3" )){
                                    parameter.setValue( Integer.valueOf( k3));
                                }
                                if(parameter.getId().equals( "CH_NORM_GAIN_4" )){
                                    parameter.setValue( Integer.valueOf( k4));
                                }
                                deviceManager.setCurrentSelect(socketMessage.getMessage().getClassify());
                                deviceManager.writeDevice(currentMemory);
                            }
                        }
                    }

                    else if (socketMessage.getMessage().getKey().startsWith("r")) {
                        for (final HAParameter parameter : memParameterList.get(SideSelect.S_UNILATERAL_RIGHT).getParameterList()) {
                            if ( socketMessage.getMessage().getKey().equals("r_P_CHLG")) {
                                String parm=String.valueOf( socketMessage.getMessage().getValue());
                                String[] array= parm.split( "," );
                                String k0=array[0];
                                String k1=array[1];
                                String k2=array[2];
                                String k3=array[3];
                                String k4=array[4];
                                if(parameter.getId().equals( "CH_LOUD_GAIN_0" )){
                                    parameter.setValue( Integer.valueOf( k0));
                                }
                                if(parameter.getId().equals( "CH_LOUD_GAIN_1" )){
                                    parameter.setValue( Integer.valueOf( k1));
                                }
                                if(parameter.getId().equals( "CH_LOUD_GAIN_2" )){
                                    parameter.setValue( Integer.valueOf( k2));
                                }
                                if(parameter.getId().equals( "CH_LOUD_GAIN_3" )){
                                    parameter.setValue( Integer.valueOf( k3));
                                }
                                if(parameter.getId().equals( "CH_LOUD_GAIN_4" )){
                                    parameter.setValue( Integer.valueOf( k4));
                                }
                                deviceManager.setCurrentSelect(socketMessage.getMessage().getClassify());
                                deviceManager.writeDevice(currentMemory);
                            }
                        }
                    }
                    else if (socketMessage.getMessage().getKey().startsWith("l")) {
                        for (final HAParameter parameter : memParameterList.get(SideSelect.S_UNILATERAL_LEFT).getParameterList()) {
                            if ( socketMessage.getMessage().getKey().equals("l_P_CHLG")) {
                                String parm=String.valueOf( socketMessage.getMessage().getValue());
                                String[] array= parm.split( "," );
                                String k0=array[0];
                                String k1=array[1];
                                String k2=array[2];
                                String k3=array[3];
                                String k4=array[4];
                                if(parameter.getId().equals( "CH_LOUD_GAIN_0" )){
                                    parameter.setValue( Integer.valueOf( k0));
                                }
                                if(parameter.getId().equals( "CH_LOUD_GAIN_1" )){
                                    parameter.setValue( Integer.valueOf( k1));
                                }
                                if(parameter.getId().equals( "CH_LOUD_GAIN_2" )){
                                    parameter.setValue( Integer.valueOf( k2));
                                }
                                if(parameter.getId().equals( "CH_LOUD_GAIN_3" )){
                                    parameter.setValue( Integer.valueOf( k3));
                                }
                                if(parameter.getId().equals( "CH_LOUD_GAIN_4" )){
                                    parameter.setValue( Integer.valueOf( k4));
                                }
                                deviceManager.setCurrentSelect(socketMessage.getMessage().getClassify());
                                deviceManager.writeDevice(currentMemory);
                            }
                        }
                    }

                    else if (socketMessage.getMessage().getKey().startsWith("r")) {
                        for (final HAParameter parameter : memParameterList.get(SideSelect.S_UNILATERAL_RIGHT).getParameterList()) {
                            if ( socketMessage.getMessage().getKey().equals("r_P_CHEXP")) {
                                String parm=String.valueOf( socketMessage.getMessage().getValue());
                                String[] array= parm.split( "," );
                                String k0=array[0];
                                String k1=array[1];
                                String k2=array[2];
                                String k3=array[3];
                                String k4=array[4];
                                if(parameter.getId().equals( "CH_EXPS_0" )){
                                    parameter.setValue( Integer.valueOf( k0));
                                }
                                if(parameter.getId().equals( "CH_EXPS_1" )){
                                    parameter.setValue( Integer.valueOf( k1));
                                }
                                if(parameter.getId().equals( "CH_EXPS_2" )){
                                    parameter.setValue( Integer.valueOf( k2));
                                }
                                if(parameter.getId().equals( "CH_EXPS_3" )){
                                    parameter.setValue( Integer.valueOf( k3));
                                }
                                if(parameter.getId().equals( "CH_EXPS_4" )){
                                    parameter.setValue( Integer.valueOf( k4));
                                }
                                deviceManager.setCurrentSelect(socketMessage.getMessage().getClassify());
                                deviceManager.writeDevice(currentMemory);
                            }
                        }
                    }
                    else if (socketMessage.getMessage().getKey().startsWith("l")) {
                        for (final HAParameter parameter : memParameterList.get(SideSelect.S_UNILATERAL_LEFT).getParameterList()) {
                            if ( socketMessage.getMessage().getKey().equals("l_P_CHEXP")) {
                                String parm=String.valueOf( socketMessage.getMessage().getValue());
                                String[] array= parm.split( "," );
                                String k0=array[0];
                                String k1=array[1];
                                String k2=array[2];
                                String k3=array[3];
                                String k4=array[4];
                                if(parameter.getId().equals( "CH_EXPS_0" )){
                                    parameter.setValue( Integer.valueOf( k0));
                                }
                                if(parameter.getId().equals( "CH_EXPS_1" )){
                                    parameter.setValue( Integer.valueOf( k1));
                                }
                                if(parameter.getId().equals( "CH_EXPS_2" )){
                                    parameter.setValue( Integer.valueOf( k2));
                                }
                                if(parameter.getId().equals( "CH_EXPS_3" )){
                                    parameter.setValue( Integer.valueOf( k3));
                                }
                                if(parameter.getId().equals( "CH_EXPS_4" )){
                                    parameter.setValue( Integer.valueOf( k4));
                                }
                                deviceManager.setCurrentSelect(socketMessage.getMessage().getClassify());
                                deviceManager.writeDevice(currentMemory);
                            }
                        }
                    }

                    else if (socketMessage.getMessage().getKey().startsWith("r")) {
                        for (final HAParameter parameter : memParameterList.get(SideSelect.S_UNILATERAL_RIGHT).getParameterList()) {
                            if ( socketMessage.getMessage().getKey().equals("r_P_CHETH")) {
                                String parm=String.valueOf( socketMessage.getMessage().getValue());
                                String[] array= parm.split( "," );
                                String k0=array[0];
                                String k1=array[1];
                                String k2=array[2];
                                String k3=array[3];
                                String k4=array[4];
                                if(parameter.getId().equals( "CH_EXPS_TH_0" )){
                                    parameter.setValue( Integer.valueOf( k0));
                                }
                                if(parameter.getId().equals( "CH_EXPS_TH_1" )){
                                    parameter.setValue( Integer.valueOf( k1));
                                }
                                if(parameter.getId().equals( "CH_EXPS_TH_2" )){
                                    parameter.setValue( Integer.valueOf( k2));
                                }
                                if(parameter.getId().equals( "CH_EXPS_TH_3" )){
                                    parameter.setValue( Integer.valueOf( k3));
                                }
                                if(parameter.getId().equals( "CH_EXPS_TH_4" )){
                                    parameter.setValue( Integer.valueOf( k4));
                                }
                                deviceManager.setCurrentSelect(socketMessage.getMessage().getClassify());
                                deviceManager.writeDevice(currentMemory);
                            }
                        }
                    }
                    else if (socketMessage.getMessage().getKey().startsWith("l")) {
                        for (final HAParameter parameter : memParameterList.get(SideSelect.S_UNILATERAL_LEFT).getParameterList()) {
                            if ( socketMessage.getMessage().getKey().equals("l_P_CHETH")) {
                                String parm=String.valueOf( socketMessage.getMessage().getValue());
                                String[] array= parm.split( "," );
                                String k0=array[0];
                                String k1=array[1];
                                String k2=array[2];
                                String k3=array[3];
                                String k4=array[4];
                                if(parameter.getId().equals( "CH_EXPS_TH_0" )){
                                    parameter.setValue( Integer.valueOf( k0));
                                }
                                if(parameter.getId().equals( "CH_EXPS_TH_1" )){
                                    parameter.setValue( Integer.valueOf( k1));
                                }
                                if(parameter.getId().equals( "CH_EXPS_TH_2" )){
                                    parameter.setValue( Integer.valueOf( k2));
                                }
                                if(parameter.getId().equals( "CH_EXPS_TH_3" )){
                                    parameter.setValue( Integer.valueOf( k3));
                                }
                                if(parameter.getId().equals( "CH_EXPS_TH_4" )){
                                    parameter.setValue( Integer.valueOf( k4));
                                }
                                deviceManager.setCurrentSelect(socketMessage.getMessage().getClassify());
                                deviceManager.writeDevice(currentMemory);
                            }
                        }
                    }

                    else if (socketMessage.getMessage().getKey().startsWith("r")) {
                        for (final HAParameter parameter : memParameterList.get(SideSelect.S_UNILATERAL_RIGHT).getParameterList()) {
                            if ( socketMessage.getMessage().getKey().equals("r_P_CHER")) {
                                String parm=String.valueOf( socketMessage.getMessage().getValue());
                                String[] array= parm.split( "," );
                                String k0=array[0];
                                String k1=array[1];
                                String k2=array[2];
                                String k3=array[3];
                                String k4=array[4];
                                if(parameter.getId().equals( "CH_EXPS_R_0" )){
                                    parameter.setValue( Integer.valueOf( k0));
                                }
                                if(parameter.getId().equals( "CH_EXPS_R_1" )){
                                    parameter.setValue( Integer.valueOf( k1));
                                }
                                if(parameter.getId().equals( "CH_EXPS_R_2" )){
                                    parameter.setValue( Integer.valueOf( k2));
                                }
                                if(parameter.getId().equals( "CH_EXPS_R_3" )){
                                    parameter.setValue( Integer.valueOf( k3));
                                }
                                if(parameter.getId().equals( "CH_EXPS_R_4" )){
                                    parameter.setValue( Integer.valueOf( k4));
                                }
                                deviceManager.setCurrentSelect(socketMessage.getMessage().getClassify());
                                deviceManager.writeDevice(currentMemory);
                            }
                        }
                    }
                    else if (socketMessage.getMessage().getKey().startsWith("l")) {
                        for (final HAParameter parameter : memParameterList.get(SideSelect.S_UNILATERAL_LEFT).getParameterList()) {
                            if ( socketMessage.getMessage().getKey().equals("l_P_CHER")) {
                                String parm=String.valueOf( socketMessage.getMessage().getValue());
                                String[] array= parm.split( "," );
                                String k0=array[0];
                                String k1=array[1];
                                String k2=array[2];
                                String k3=array[3];
                                String k4=array[4];
                                if(parameter.getId().equals( "CH_EXPS_R_0" )){
                                    parameter.setValue( Integer.valueOf( k0));
                                }
                                if(parameter.getId().equals( "CH_EXPS_R_1" )){
                                    parameter.setValue( Integer.valueOf( k1));
                                }
                                if(parameter.getId().equals( "CH_EXPS_R_2" )){
                                    parameter.setValue( Integer.valueOf( k2));
                                }
                                if(parameter.getId().equals( "CH_EXPS_R_3" )){
                                    parameter.setValue( Integer.valueOf( k3));
                                }
                                if(parameter.getId().equals( "CH_EXPS_R_4" )){
                                    parameter.setValue( Integer.valueOf( k4));
                                }
                                deviceManager.setCurrentSelect(socketMessage.getMessage().getClassify());
                                deviceManager.writeDevice(currentMemory);
                            }
                        }
                    }

                }
//                ToastUtils.showShort(socketMessage.getMessage().getKey() + "" + socketMessage.getMessage().getValue() + "  " + ((Double) socketMessage.getMessage().getValue()).intValue());
                ToastUtils.showShort(socketMessage.getMessage().getKey() + "" + socketMessage.getMessage().getValue() + "  " + ( socketMessage.getMessage().getValue()).toString());
            }
        }

    }

    //    // 通话过程状态监听
//    private SimpleAVChatStateObserver avchatStateObserver = new SimpleAVChatStateObserver() {
//
//        @Override
//        public void onJoinedChannel(int code, String audioFile, String videoFile, int i) {
////            LogUtil.d(TAG, "audioFile -> " + audioFile+" videoFile -> " + videoFile);
//            handleWithConnectServerResult(code);
//        }
//
//
////        @Override
////        public void onCallEstablished() {
//////            LogUtil.d(TAG, "onCallEstablished");
////            //移除超时监听
////            AVChatTimeoutObserver.getInstance().observeTimeoutNotification(timeoutObserver, false, mIsInComingCall);
////            if (avChatController.getTimeBase() == 0)
////                avChatController.setTimeBase(SystemClock.elapsedRealtime());
////
////            if (state == AVChatType.AUDIO.getValue()) {
//////                avChatVideoUI.showAudioInitLayout();
////            } else {
////                // 接通以后，自己是小屏幕显示图像，对方是大屏幕显示图像
//////                avChatVideoUI.initLargeSurfaceView("18258139327");
//////                avChatVideoUI.initSmallSurfaceView("18258139327");
//////                avChatVideoUI.showVideoInitLayout();
////            }
//////            isCallEstablished = true;
////        }
//
////        @Override
////        public boolean onVideoFrameFilter(AVChatVideoFrame frame, boolean maybeDualInput) {
////            if (faceU != null) {
////                faceU.effect(frame.data, frame.width, frame.height, FaceU.VIDEO_FRAME_FORMAT.I420);
////            }
////
////            return true;
////        }
////
////        @Override
////        public boolean onAudioFrameFilter(AVChatAudioFrame frame) {
////            return true;
////        }
//
//    };
    Observer<Integer> timeoutObserver = new Observer<Integer>() {
        @Override
        public void onEvent(Integer integer) {

            avChatController.hangUp(AVChatExitCode.CANCEL);

            // 来电超时，自己未接听


            if (mIsInComingCall) {
//                activeMissCallNotifier();
            }

            ChatMainActivity.this.finish();
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (mTimer != null) {
            mTimer.cancel();
        }
//        if (avChatAudioUI != null) {
//            avChatVideoUI.onDestroy();
//        }

//        if (avChatVideoUI != null) {
//            avChatVideoUI.onDestroy();
//        }

        registerObserves(false);
//        AVChatProfile.getInstance().setAVChatting(false);
//        cancelCallingNotifier();
//        destroyFaceU();
//        needFinish = true;
    }


    @Override
    public void onVideoToAudio() {
        //转音频
        avChatVideoUI.changeVideoToAudio();


    }

    @Override
    public void onAudioToVideo() {
        //转视频
        Toast.makeText(ChatMainActivity.this, "正在等待对方打开摄像头...", Toast.LENGTH_SHORT).show();
//        avChatVideoUI.changeAudioToVideo();

    }

    @Override
    public void onReceiveAudioToVideoAgree() {
        avChatVideoUI.onAudioToVideoAgree(receiverId);
    }

    @Override
    public void onTouch() {

    }


    @Override
    public void onBtPatientClick() {
        avChatVideoUI.receiveCall();
//        startFragment(new PatientInfoFragment());

    }

    @Override
    public void onImgPatientClick() {
            mViewModel.getPersonId(avChatData.getAccount(), new BaseObserver<Model0<AccidEntity>>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(Model0<AccidEntity> accidEntityModel0) {
//                    professorId=accidEntityModel0.getComeback().getPersonId();
                    deviceEntities.clear();
                    professorId=accidEntityModel0.getComeback().getPersonId();

                    StaticConfigCallback.getInStanceBlock().setActivityFlag(4);
                    ZhitingyunApplication.deviceManager.setCurrentSelect( 1 );

                }


            });



    }


    @Override
    public void onImgEquipClick() {

    }
}
