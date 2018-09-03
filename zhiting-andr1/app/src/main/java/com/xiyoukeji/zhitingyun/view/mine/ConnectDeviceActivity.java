package com.xiyoukeji.zhitingyun.view.mine;

import android.app.Activity;
import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.xiyoukeji.zhitingyun.R;
import com.xiyoukeji.zhitingyun.ViewModelFactory;
import com.xiyoukeji.zhitingyun.ZhitingyunApplication;
import com.xiyoukeji.zhitingyun.base.BaseActivity;
import com.xiyoukeji.zhitingyun.base.BaseCom;
import com.xiyoukeji.zhitingyun.constant.AppConstant;
import com.xiyoukeji.zhitingyun.data.entity.DeviceEntity;
import com.xiyoukeji.zhitingyun.data.entity.MessageEvent;
import com.xiyoukeji.zhitingyun.data.entity.Model0;
import com.xiyoukeji.zhitingyun.data.remote.BaseObserver;
import com.xiyoukeji.zhitingyun.hadeviceapi.AllParameter;
import com.xiyoukeji.zhitingyun.hadeviceapi.DeviceInfoAdapter;
import com.xiyoukeji.zhitingyun.hadeviceapi.ParamInfoAdapter;
import com.xiyoukeji.zhitingyun.hadeviceapi.PureToneManager;
import com.xiyoukeji.zhitingyun.util.ActivityManager;
import com.xiyoukeji.zhitingyun.util.ToastUtils;
import com.xiyoukeji.zhitingyun.viewmodel.login.LoginViewModel;
import com.xiyoukeji.zhitingyun.viewmodel.main.SendDeviceViewModel;

import com.xiyoukeji.zhitingyun.widget.StatusBarUtil;
import com.xiyoukeji.zhitingyun.yunxin.SPUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;
import cn.earhear.hadevicelib.DeviceInfo;
import cn.earhear.hadevicelib.DeviceState;
import cn.earhear.hadevicelib.HADeviceManager;
import cn.earhear.hadevicelib.HAParameter;
import cn.earhear.hadevicelib.HAParameterList;
import cn.earhear.hadevicelib.callback.ConfigCallback;
import cn.earhear.hadevicelib.callback.ConnectCallback;
import cn.earhear.hadevicelib.callback.DetectCallback;
import cn.earhear.hadevicelib.constant.ErrorType;
import cn.earhear.hadevicelib.constant.FittingAlgorithm;
import cn.earhear.hadevicelib.constant.OperationType;
import cn.earhear.hadevicelib.constant.ParameterString;
import cn.earhear.hadevicelib.constant.SideSelect;
import io.reactivex.disposables.Disposable;
import io.realm.Realm;

public class ConnectDeviceActivity extends BaseActivity {
    private static final String TYPE_SYS = "sys";
    private static final String TYPE_LEFT = "left";
    private static final String TYPE_RIGHT = "right";
    @BindView(R.id.btn_connect)
    Button btn_connect;
    @BindView(R.id.list_param)
    ListView allParamListView;
    @BindView(R.id.list_device)
    ListView deviceInfoListView;
    @BindView(R.id.mscaddress)
    TextView mscTv;

    private SendDeviceViewModel mViewModel;
    private DeviceInfoAdapter deviceInfoAdapter;
    DeviceEntity entity;
    private int connPos;

    private TableLayout operation_table_layout;

    private List<AllParameter> allParameterList;
    private ParamInfoAdapter paramInfoAdapter;
    private CountDownTimer mTimer;

    private int currentMemory;
    private List<HAParameterList> sysParameterList;
    private List<HAParameterList> memParameterList;
    private List<AllParameter> allParamList;


    private int a1 = 0, a2 = 0, a3 = 0, a4 = 0, a5 = 0, b1 = 0, b2 = 0, b3 = 0, b4 = 0, b5 = 0, c1 = 0, c2 = 0, c3 = 0, c4 = 0, c5 = 0,
            d1 = 0, d2 = 0, d3 = 0, d4 = 0, d5 = 0, e1 = 0, e2 = 0, e3 = 0, e4 = 0, e5 = 0, f1 = 0, f2 = 0, f3 = 0, f4 = 0, f5 = 0, g1 = 0, g2 = 0, g3 = 0, g4 = 0, g5 = 0, h1 = 0, h2 = 0, h3 = 0, h4 = 0, h5 = 0;
    private int i1 = 0, i2 = 0, i3 = 0, i4 = 0, i5 = 0, j1 = 0, j2 = 0, j3 = 0, j4 = 0, j5 = 0, k1 = 0, k2 = 0, k3 = 0, k4 = 0, k5 = 0,
            l1 = 0, l2 = 0, l3 = 0, l4 = 0, l5 = 0, m1 = 0, m2 = 0, m3 = 0, m4 = 0, m5 = 0, n1 = 0, n2 = 0, n3 = 0, n4 = 0, n5 = 0, o1 = 0, o2 = 0, o3 = 0, o4 = 0, o5 = 0, p1 = 0, p2 = 0, p3 = 0, p4 = 0, p5 = 0;
    //    private DeviceEntity entity;
    private int m;

    public ConnectDeviceActivity() {
        super(R.layout.activity_connect_device);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        entity = new DeviceEntity();
        EventBus.getDefault().register(this);
        initView();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR );
            StatusBarUtil.setStatusBarColor( this, R.color.white );
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent messageEvent) {
        switch (messageEvent.getFlag()) {
            case 0://onConnectSuccess
                deviceInfoAdapter.setConnectPosition(connPos);
                allParamListView.setVisibility(View.GONE);
                SPUtil.put(ConnectDeviceActivity.this, "device", new Gson().toJson(deviceInfoAdapter.getItem(connPos)));
                ZhitingyunApplication.curState = ZhitingyunApplication.deviceManager.getDeviceState(deviceInfoAdapter.getItem(connPos));
                Toast.makeText(ConnectDeviceActivity.this, ZhitingyunApplication.curState.getDevName(), Toast.LENGTH_LONG).show();
                final ProgressDialog proDialog = android.app.ProgressDialog.show(ConnectDeviceActivity.this, "设备连接成功 ", "正在为您跳转。。。");
                Thread thread = new Thread()
                {
                    public void run()
                    {
                        try
                        {
                            sleep(5000);
                            finish();
                        } catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                        proDialog.dismiss();
                    }
                };
                thread.start();
                ConnectStepOneActivity.instance.finish();
                ConnectStepTwoActivity.instance.finish();
                break;
            case 1://onDisconnect
                disconnectProcess();
                Toast.makeText(ConnectDeviceActivity.this, (Boolean) messageEvent.getObject() ? R.string.active_disconnected :
                        R.string.passive_disconnected, Toast.LENGTH_LONG).show();
                break;
            case 2://onFail
                Toast.makeText(ConnectDeviceActivity.this, "connect fail:" + getErrorString((Integer) messageEvent.getObject()), Toast.LENGTH_SHORT).show();
                break;
            case 3://onDetecting
                DeviceInfo deviceInfo = (DeviceInfo) messageEvent.getObject();
                deviceInfoAdapter.addDeviceInfo(deviceInfo);
                break;
            case 4://onDetectFinish
                List<DeviceInfo> deviceInfoList = (List<DeviceInfo>) messageEvent.getObject();
                if (btn_connect.getText().equals(getString(R.string.scanning)))
                    btn_connect.setText(getString(R.string.start_scan));
                deviceInfoAdapter.setDeviceInfoList(deviceInfoList);
                break;
            case 5://onFail
                if (btn_connect.getText().equals(getString(R.string.scanning)))
                    btn_connect.setText(getString(R.string.start_scan));
                Toast.makeText(ConnectDeviceActivity.this, "detect fail:" + getErrorString((Integer) messageEvent.getObject()), Toast.LENGTH_LONG).show();
                break;

            case 6://configOnSuccess
                switch ((Integer) messageEvent.getObject()) {
                    case OperationType.OP_AUTO_CHECK:
                    case OperationType.OP_SELECT_MEMORY:
                        ZhitingyunApplication.deviceManager.readDevice();
                        break;
                    case OperationType.OP_READ_CONFIG:
                        currentMemory = ZhitingyunApplication.deviceManager.getCurrentMemory();
                        sysParameterList = ZhitingyunApplication.deviceManager.getSysParameterList();
                        memParameterList = ZhitingyunApplication.deviceManager.getMemBinParameterList();
                        fillAllParameterList();
                        break;
                    default:
                        break;
                }
                break;
            case 7://configOnFail
                switch ((Integer) messageEvent.getObject()) {
                    case OperationType.OP_AUTO_CHECK:
                        ZhitingyunApplication.deviceManager.disconnectDevice(deviceInfoAdapter.getItem(connPos));
                        break;
                    case OperationType.OP_READ_CONFIG:
                        break;
                    default:
                        break;
                }
                break;
        }

    }

    public void setAllParameterList(List<AllParameter> parameter) {
        this.allParamList = parameter;
        notify();
    }

    public AllParameter getItem(int position) {
        if (allParamList == null || position > allParamList.size())
            return null;
        return allParamList.get(position);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        deviceManager.destroy();
        EventBus.getDefault().unregister(this);
        deviceInfoAdapter.deviceListClear();
        paramInfoAdapter.parameterListClear();
        if (mTimer != null) {
            mTimer.cancel();
        }
    }


    public List<AllParameter> getAllParameterList() {
        return allParamList;
    }


    @Override
    protected void initView() {
        super.initView();

        paramInfoAdapter = new ParamInfoAdapter(this);
        paramInfoAdapter.setOnValueChangeListener(new ParamInfoAdapter.OnValueChangeListener() {
            @Override
            public void onValueChanging(int pos, int value) {

            }

            @Override
            public void onValueChanged(int pos, int value) {
                AllParameter parameter = paramInfoAdapter.getItem(pos);
                switch (parameter.getType()) {
                    case TYPE_SYS:
                        sysParameterList.get(0).getHAParameterById(parameter.getParameter().getId()).setValue(value);
                        break;
                    case TYPE_LEFT:
                        memParameterList.get(SideSelect.S_UNILATERAL_LEFT).getHAParameterById(parameter.getParameter().getId()).setValue(value);
                        break;
                    case TYPE_RIGHT:
                        memParameterList.get(SideSelect.S_UNILATERAL_RIGHT).getHAParameterById(parameter.getParameter().getId()).setValue(value);
                        break;
                    default:
                        break;
                }
            }
        });


        allParamListView.setAdapter(paramInfoAdapter);
        allParamListView.setVisibility(View.GONE);
        deviceInfoAdapter = new DeviceInfoAdapter(this);
        deviceInfoAdapter.setConnectPosition(DeviceInfoAdapter.INVALID_POSITION);
        deviceInfoAdapter.setOnDeviceClickListener(new DeviceInfoAdapter.OnDeviceClickListener() {
            @Override
            public void onChoice(int position) {
                DeviceInfo deviceInfo = deviceInfoAdapter.getItem(position);
                if (ZhitingyunApplication.deviceManager.getDeviceState(deviceInfo) == null) {
                    connPos = position;
                    currentMemory = -1;
                    ZhitingyunApplication.deviceManager.connectDevice(deviceInfo);
                    Toast.makeText(ConnectDeviceActivity.this, getString(R.string.connecting), Toast.LENGTH_SHORT).show();
                } else {
                    if (ZhitingyunApplication.deviceManager.disconnectDevice(deviceInfo) == ErrorType.SUCCESS)
                        Toast.makeText(ConnectDeviceActivity.this, getString(R.string.disconnecting), Toast.LENGTH_SHORT).show();
                    else {
                        disconnectProcess();
                    }
                }
            }
        });
        ListView deviceInfoListView = findViewById(R.id.list_device);
        deviceInfoListView.setAdapter(deviceInfoAdapter);

    }

    @Override
    protected void initData() {
        super.initData();
        obtainViewModel();

    }

    public void obtainViewModel() {
        ViewModelFactory factory = ViewModelFactory.getInstance(mActivity.getApplication());
        mViewModel = ViewModelProviders.of(this, factory).get(SendDeviceViewModel.class);
    }


    private void disconnectProcess() {
        deviceInfoAdapter.setConnectPosition(DeviceInfoAdapter.INVALID_POSITION);
        operation_table_layout.setVisibility(View.GONE);
        allParamListView.setVisibility(View.GONE);
        paramInfoAdapter.parameterListClear();
        deviceInfoAdapter.deviceListClear();


        currentMemory = -1;
        sysParameterList = null;
        memParameterList = null;
    }



    @OnClick({R.id.back_layout, R.id.btn_connect})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_layout:
                finish();
                break;
            case R.id.btn_connect:

                deviceInfoAdapter.deviceListClear();
                btn_connect.setText(getString(R.string.scanning));

                ZhitingyunApplication.deviceManager.detectDevice();
                Toast.makeText(ConnectDeviceActivity.this, getString(R.string.start_scan), Toast.LENGTH_SHORT).show();

                break;
        }
    }

    private void fillAllParameterList() {

        if (allParameterList == null)
            allParameterList = new ArrayList<>();
        else
            allParameterList.clear();


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

            for (HAParameter parameter : memParameterList.get(SideSelect.S_UNILATERAL_LEFT).getParameterList()) {
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
                } else if (parameter.getId().equals("CH_NORM_GAIN_1")) {
                    e2 = parameter.getValue();
                } else if (parameter.getId().equals("CH_NORM_GAIN_2")) {
                    e3 = parameter.getValue();
                } else if (parameter.getId().equals("CH_NORM_GAIN_3")) {
                    e4 = parameter.getValue();
                } else if (parameter.getId().equals("CH_NORM_GAIN_4")) {
                    e5 = parameter.getValue();
                } else if (parameter.getId().equals("CH_EXPS_1")) {
                    f1 = parameter.getValue();
                } else if (parameter.getId().equals("CH_EXPS_2")) {
                    f2 = parameter.getValue();
                } else if (parameter.getId().equals("CH_EXPS_3")) {
                    f3 = parameter.getValue();
                } else if (parameter.getId().equals("CH_EXPS_4")) {
                    f4 = parameter.getValue();
                } else if (parameter.getId().equals("CH_EXPS_5")) {
                    f5 = parameter.getValue();
                } else if (parameter.getId().equals("CH_EXPS_TH_1")) {
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
                    entity.setL_SWITCH_P_CH(false);
                }
                if (f1 != 0 && g1 != 0 && h1 != 0) {
                    entity.setL_SWITCH_P_CHEXP(true);
                } else {
                    entity.setL_SWITCH_P_CHEXP(false);
                }
                entity.setL_P_CHMPO(a1 + "," + a2 + "," + a3 + "," + a4 + "," + a5);

                entity.setL_P_CHSW(b1 + "," + b2 + "," + b3 + "," + b4 + "," + b5);

                entity.setL_P_CHSG(c1 + "," + c2 + "," + c3 + "," + c4 + "," + c5);

                entity.setL_P_CHNG(d1 + "," + d2 + "," + d3 + "," + d4 + "," + d5);

                entity.setL_P_CHLG(e1 + "," + e2 + "," + e3 + "," + e4 + "," + e5);

                entity.setL_P_CHEXP(f1 + "," + f2 + "," + f3 + "," + f4 + "," + f5);

                entity.setL_P_CHETH(g1 + "," + g2 + "," + g3 + "," + g4 + "," + g5);

                entity.setL_P_CHER(h1 + "," + h2 + "," + h3 + "," + h4 + "," + h5);
            }

            for (HAParameter parameter : memParameterList.get(SideSelect.S_UNILATERAL_RIGHT).getParameterList()) {
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
                } else if (parameter.getId().equals("CH_NORM_GAIN_1")) {
                    m2 = parameter.getValue();
                } else if (parameter.getId().equals("CH_NORM_GAIN_2")) {
                    m3 = parameter.getValue();
                } else if (parameter.getId().equals("CH_NORM_GAIN_3")) {
                    m4 = parameter.getValue();
                } else if (parameter.getId().equals("CH_NORM_GAIN_4")) {
                    m5 = parameter.getValue();
                } else if (parameter.getId().equals("CH_EXPS_1")) {
                    n1 = parameter.getValue();
                } else if (parameter.getId().equals("CH_EXPS_2")) {
                    n2 = parameter.getValue();
                } else if (parameter.getId().equals("CH_EXPS_3")) {
                    n3 = parameter.getValue();
                } else if (parameter.getId().equals("CH_EXPS_4")) {
                    n4 = parameter.getValue();
                } else if (parameter.getId().equals("CH_EXPS_5")) {
                    n5 = parameter.getValue();
                } else if (parameter.getId().equals("CH_EXPS_TH_1")) {
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
                    entity.setR_SWITCH_P_CH(false);
                }
                if (n1 != 0 && o1 != 0 && p1 != 0) {
                    entity.setR_SWITCH_P_CHEXP(true);
                } else {
                    entity.setR_SWITCH_P_CHEXP(false);
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
            entity.setClassify(1);
            entity.setHertz("125,250,500,750,1000,1500,2000,3000,4000,6000,8000");
            Gson gson = new Gson();
            String session1 = gson.toJson(entity);
            SPUtil.put(ConnectDeviceActivity.this, "session1", session1);

            DeviceEntity entity2 = entity.createDeviceEntity();
            entity2.setClassify(2);
            String session2 = gson.toJson(entity2);
            SPUtil.put(ConnectDeviceActivity.this, "session2", session2);

            DeviceEntity entity3 = entity.createDeviceEntity();
            entity3.setClassify(3);
            String session3 = gson.toJson(entity3);
            SPUtil.put(ConnectDeviceActivity.this, "session3", session3);

            DeviceEntity entity4 = entity.createDeviceEntity();
            entity4.setClassify(4);
            String session4 = gson.toJson(entity4);
            SPUtil.put(ConnectDeviceActivity.this, "session4", session4);

        }
        paramInfoAdapter.setAllParameterList(allParameterList);

        for (int i = 0; i < allParameterList.size(); i++) {
            final HAParameter haParameter = allParameterList.get(i).getParameter();
        }
    }

    private String getErrorString(int error) {
        switch (error) {
            case ErrorType.SUCCESS:
                return " Success";

            case ErrorType.E_UNINITIALIZED:
                return " X API uninitialized";
            case ErrorType.E_INVALID_INPUT_PARAMS:
                return " X Invalid input parameters";
            case ErrorType.E_NO_MORE_DEVICE:
                return " X No more can connect";
            case ErrorType.E_REPEAT_INITIALIZED:
                return " X Repeat initialized";

            case ErrorType.E_BLUETOOTH_UNSUPPORTED:
                return " X BT unsupported";
            case ErrorType.E_BLUETOOTH_DISABLED:
                return " X BT disable";
            case ErrorType.E_LOCATION_DISABLED:
                return " X Location disabled";

            case ErrorType.E_BLE_START_SCAN_FAILED:
                return " X BLE scan failed";
            case ErrorType.E_BLE_NO_DEVICE_SCANNED:
                return " X BLE not scanned";
            case ErrorType.E_BLE_NO_DEVICE_CONNECTED:
                return " X BLE not connected";
            case ErrorType.E_BLE_SERVICE_UNSUPPORTED:
                return " X BLE service unrecognized";
            case ErrorType.E_BLE_COMMUNICATION:
                return " X BLE communication";

            case ErrorType.E_CONFIG_IN_PROGRESS:
                return " X Config in progress";
            case ErrorType.E_PARAMETER_NULL:
                return " X No parameter";
            default:
                break;
        }
        return " Unknown";
    }


}
