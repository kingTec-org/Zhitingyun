package com.xiyoukeji.zhitingyun.view.main;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupMenu;


import com.androidkun.xtablayout.XTabLayout;
import com.xiyoukeji.zhitingyun.R;
import com.xiyoukeji.zhitingyun.ZhitingyunApplication;
import com.xiyoukeji.zhitingyun.base.BaseActivity;
import com.xiyoukeji.zhitingyun.base.BaseTabAdapter;
import com.xiyoukeji.zhitingyun.data.entity.MessageEvent;

import com.xiyoukeji.zhitingyun.widget.StaticConfigCallback;
import com.xiyoukeji.zhitingyun.widget.StatusBarUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.earhear.hadevicelib.DeviceInfo;

import cn.earhear.hadevicelib.HAParameter;
import cn.earhear.hadevicelib.HAParameterList;
import cn.earhear.hadevicelib.constant.OperationType;
import cn.earhear.hadevicelib.constant.ParameterString;
import cn.earhear.hadevicelib.constant.SideSelect;

import static com.xiyoukeji.zhitingyun.ZhitingyunApplication.deviceManager;

public class AutoRegulationActivity extends BaseActivity implements PopupMenu.OnMenuItemClickListener {


    @BindView(R.id.tab)
    XTabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.change)
    LinearLayout change;



    private int currentMemory;
    private List<HAParameterList> sysParameterList;
    private List<HAParameterList> memParameterList;
    private PopupMenu popupMenu;
    private int selectPosition;
    int refreh0=0,refresh1=0,refresh2=0,refresh3=0;

    private EnvironmentFragment environmentFragment0, environmentFragment1, environmentFragment2, environmentFragment3;


    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();

    List<Integer> envir1 = new ArrayList<>();
    List<Integer> envir2 = new ArrayList<>();
    List<Integer> envir3 = new ArrayList<>();
    List<Integer> envir4 = new ArrayList<>();
    List<Integer> envir5 = new ArrayList<>();
    List<Integer> envir6 = new ArrayList<>();
    List<Integer> envir7 = new ArrayList<>();
    List<Integer> envir8 = new ArrayList<>();

    EnvironmentFragment environmentFragment;
    EnvironmentJTFragment environmentJTFragment;
    EnvironmentHWFragment environmentHWFragment;
    EnvironmentTYFragment environmentTYFragment;
    List<DeviceInfo> deviceInfos0,deviceInfos1,deviceInfos2,deviceInfos3;



    OngetInfo ongetInfo=new OngetInfo() {
        @Override
        public void ongetInfo(int flag, List<Integer> list) {
            switch (flag) {
                case 0:
                    envir1 = list;
                    break;
                case 1:
                    envir2 = list;
                    break;
                case 2:
                    envir3 = list;
                    break;
                case 3:
                    envir4 = list;
                    break;
                case 4:
                    envir5 = list;
                    break;
                case 5:
                    envir6 = list;
                    break;
                case 6:
                    envir7 = list;
                    break;
                case 7:
                    envir8 = list;
                    break;
                case 8:
                    refreh0=1;
                    environmentFragment.setCurrent();
                    break;
                case 9:
                    refresh1=1;
                    if (selectPosition == 1)
                        environmentJTFragment.setCurrent();
                    break;
                case 10:
                    refresh2=1;
                    if (selectPosition == 2)
                         environmentHWFragment.setCurrent();
                    break;
                case 11:
                    refresh3=1;
                    if (selectPosition == 3)
                        environmentTYFragment.setCurrent();
                    break;

            }
        }
    };

    public AutoRegulationActivity() {
        super(R.layout.activity_autonomous_regulation);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
//        deviceManagerInit();
        final ProgressDialog proDialog = android.app.ProgressDialog.show(AutoRegulationActivity.this, "正在读取设备数据", "预计等待3~5秒");
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
                proDialog.dismiss();
            }
        };
        thread.start();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            StatusBarUtil.setStatusBarColor(this, R.color.white); }
    }

    private Handler handler = new Handler(  ){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage( msg );
            switch (msg.what){
                case 0:
                    StaticConfigCallback.getInStanceBlock().setActivityFlag(11);
                    ZhitingyunApplication.deviceManager.setCurrentSelect( 2);
                    break;
                case 1:
                    StaticConfigCallback.getInStanceBlock().setActivityFlag(12);
                    ZhitingyunApplication.deviceManager.setCurrentSelect( 3 );
                    break;
                case 2:
                    StaticConfigCallback.getInStanceBlock().setActivityFlag(13);
                    ZhitingyunApplication.deviceManager.setCurrentSelect( 4 );
                    break;
            }
        }
    };

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent messageEvent) {
        switch (messageEvent.getFlag()) {
            case 0://onConnectSuccess
                break;
            case 1://onDisconnect

                break;
            case 2://onFail
                break;
            case 3://onDetecting
                switch ((messageEvent.getActivityFlag())) {
                    case 4:
                        DeviceInfo deviceInfo = (DeviceInfo) messageEvent.getObject();
                        break;
                    case 5:
                        DeviceInfo deviceInfo1 = (DeviceInfo) messageEvent.getObject();
                        break;
                    case 6:
                        DeviceInfo deviceInfo2 = (DeviceInfo) messageEvent.getObject();
                        break;
                    case 7:
                        DeviceInfo deviceInfo3 = (DeviceInfo) messageEvent.getObject();
                        break;
//                deviceInfoAdapter.addDeviceInfo(deviceInfo);
                }
                break;
            case 4://onDetectFinish
                switch ((messageEvent.getActivityFlag())) {
                    case 4:
                        deviceInfos0 = (List<DeviceInfo>) messageEvent.getObject();
                        break;
                    case 5:
                        deviceInfos1 = (List<DeviceInfo>) messageEvent.getObject();
                        break;
                    case 6:
                        deviceInfos2 = (List<DeviceInfo>) messageEvent.getObject();
                        break;
                    case 7:
                        deviceInfos3 = (List<DeviceInfo>) messageEvent.getObject();
                        break;
//                deviceInfoAdapter.setDeviceInfoList(deviceInfoList);
                }
                break;
            case 5://onFail
            case 6://configOnSuccess
//                switch (messageEvent.getActivityFlag()) {
//                    case 4:
                    if (messageEvent.getActivityFlag() == 10) {
                        switch ((Integer) messageEvent.getObject()) {
                            case OperationType.OP_AUTO_CHECK:
                            case OperationType.OP_SELECT_MEMORY:
                                Log.d( "vvv", "read0" );
                                ZhitingyunApplication.deviceManager.readDevice();
                                break;
                            case OperationType.OP_READ_CONFIG:
                                Log.d( "vvv", "finish0" );
                                currentMemory = ZhitingyunApplication.deviceManager.getCurrentMemory();
                                sysParameterList = ZhitingyunApplication.deviceManager.getSysParameterList();
                                memParameterList = ZhitingyunApplication.deviceManager.getMemBinParameterList();

                                SharedPreferences sharedPreferences1 = getSharedPreferences( "mySP", Context.MODE_PRIVATE );
                                Integer geta1 = sharedPreferences1.getInt( "save1", -1 );
                                Integer geta2 = sharedPreferences1.getInt( "save2", -1 );
                                Integer geta3 = sharedPreferences1.getInt( "save3", -1 );
                                Integer geta4 = sharedPreferences1.getInt( "save4", -1 );
                                Integer geta5 = sharedPreferences1.getInt( "save5", -1 );
                                Integer geta6 = sharedPreferences1.getInt( "save6", -1 );

                                HAParameter parameter0 = null;
                                if (geta1 != -1) {
                                    parameter0 = memParameterList.get( SideSelect.S_UNILATERAL_LEFT ).getHAParameterById( ParameterString.P_VC );
                                    parameter0.setValue( geta1 );
                                    deviceManager.writeDevice( currentMemory );
                                }
                                if (geta3 != -1) {
                                    parameter0 = memParameterList.get( SideSelect.S_UNILATERAL_LEFT ).getHAParameterById( ParameterString.P_TRB );
                                    parameter0.setValue( geta3 );
                                    deviceManager.writeDevice( currentMemory );
                                }
                                if (geta5 != -1) {
                                    parameter0 = memParameterList.get( SideSelect.S_UNILATERAL_LEFT ).getHAParameterById( ParameterString.P_BASS );
                                    parameter0.setValue( geta5 );
                                    deviceManager.writeDevice( currentMemory );
                                }
                                if (geta2 != -1) {
                                    parameter0 = memParameterList.get( SideSelect.S_UNILATERAL_RIGHT ).getHAParameterById( ParameterString.P_VC );
                                    parameter0.setValue( geta2 );
                                    deviceManager.writeDevice( currentMemory );
                                }
                                if (geta4 != -1) {
                                    parameter0 = memParameterList.get( SideSelect.S_UNILATERAL_RIGHT ).getHAParameterById( ParameterString.P_TRB );
                                    parameter0.setValue( geta4 );
                                    deviceManager.writeDevice( currentMemory );
                                }
                                if (geta6 != -1) {
                                    parameter0 = memParameterList.get( SideSelect.S_UNILATERAL_RIGHT ).getHAParameterById( ParameterString.P_BASS );
                                    parameter0.setValue( geta6 );
                                    deviceManager.writeDevice( currentMemory );
                                }
                                Message message = new Message();
                                message.what = 0;
                                handler.sendMessageDelayed( message, 300 );


                                break;
                            default:
                                break;
                        }
//                        break;
//                    case 5:
                    } else if (messageEvent.getActivityFlag() == 11) {
                        switch ((Integer) messageEvent.getObject()) {
                            case OperationType.OP_AUTO_CHECK:
                            case OperationType.OP_SELECT_MEMORY:
                                Log.d( "vvv", "read1" );
                                ZhitingyunApplication.deviceManager.readDevice();
                                break;
                            case OperationType.OP_READ_CONFIG:
                                Log.d( "vvv", "finish1" );
                                currentMemory = ZhitingyunApplication.deviceManager.getCurrentMemory();
                                sysParameterList = ZhitingyunApplication.deviceManager.getSysParameterList();
                                memParameterList = ZhitingyunApplication.deviceManager.getMemBinParameterList();


                                SharedPreferences sharedPreferences1 = getSharedPreferences( "mySP", Context.MODE_PRIVATE );
                                Integer getb1 = sharedPreferences1.getInt( "save7", -1 );
                                Integer getb2 = sharedPreferences1.getInt( "save8", -1 );
                                Integer getb3 = sharedPreferences1.getInt( "save9", -1 );
                                Integer getb4 = sharedPreferences1.getInt( "save10", -1 );
                                Integer getb5 = sharedPreferences1.getInt( "save11", -1 );
                                Integer getb6 = sharedPreferences1.getInt( "save12", -1 );

                                HAParameter parameter0 = null;
                                if (getb1 != -1) {
                                    parameter0 = memParameterList.get( SideSelect.S_UNILATERAL_LEFT ).getHAParameterById( ParameterString.P_VC );
                                    parameter0.setValue( getb1 );
                                    deviceManager.writeDevice( currentMemory );
                                }
                                if (getb3 != -1) {
                                    parameter0 = memParameterList.get( SideSelect.S_UNILATERAL_LEFT ).getHAParameterById( ParameterString.P_TRB );
                                    parameter0.setValue( getb3 );
                                    deviceManager.writeDevice( currentMemory );
                                }
                                if (getb5 != -1) {
                                    parameter0 = memParameterList.get( SideSelect.S_UNILATERAL_LEFT ).getHAParameterById( ParameterString.P_BASS );
                                    parameter0.setValue( getb5 );
                                    deviceManager.writeDevice( currentMemory );
                                }
                                if (getb2 != -1) {
                                    parameter0 = memParameterList.get( SideSelect.S_UNILATERAL_RIGHT ).getHAParameterById( ParameterString.P_VC );
                                    parameter0.setValue( getb2 );
                                    deviceManager.writeDevice( currentMemory );
                                }
                                if (getb4 != -1) {
                                    parameter0 = memParameterList.get( SideSelect.S_UNILATERAL_RIGHT ).getHAParameterById( ParameterString.P_TRB );
                                    parameter0.setValue( getb4 );
                                    deviceManager.writeDevice( currentMemory );
                                }
                                if (getb6 != -1) {
                                    parameter0 = memParameterList.get( SideSelect.S_UNILATERAL_RIGHT ).getHAParameterById( ParameterString.P_BASS );
                                    parameter0.setValue( getb6 );
                                    deviceManager.writeDevice( currentMemory );
                                }

                                Message message = new Message();
                                message.what = 1;
                                handler.sendMessageDelayed( message, 300 );
                                break;
                            default:
                                break;
                        }
//                        break;
//                    case 6:
                    } else if (messageEvent.getActivityFlag() == 12) {
                        switch ((Integer) messageEvent.getObject()) {
                            case OperationType.OP_AUTO_CHECK:
                            case OperationType.OP_SELECT_MEMORY:
                                Log.d( "vvv", "read2" );
                                ZhitingyunApplication.deviceManager.readDevice();
                                break;
                            case OperationType.OP_READ_CONFIG:
                                Log.d( "vvv", "finish2" );
                                currentMemory = ZhitingyunApplication.deviceManager.getCurrentMemory();
                                sysParameterList = ZhitingyunApplication.deviceManager.getSysParameterList();
                                memParameterList = ZhitingyunApplication.deviceManager.getMemBinParameterList();


                                SharedPreferences sharedPreferences1 = getSharedPreferences( "mySP", Context.MODE_PRIVATE );
                                Integer getc1 = sharedPreferences1.getInt( "save13", -1 );
                                Integer getc2 = sharedPreferences1.getInt( "save14", -1 );
                                Integer getc3 = sharedPreferences1.getInt( "save15", -1 );
                                Integer getc4 = sharedPreferences1.getInt( "save16", -1 );
                                Integer getc5 = sharedPreferences1.getInt( "save17", -1 );
                                Integer getc6 = sharedPreferences1.getInt( "save18", -1 );

                                HAParameter parameter0 = null;
                                if (getc1 != -1) {
                                    parameter0 = memParameterList.get( SideSelect.S_UNILATERAL_LEFT ).getHAParameterById( ParameterString.P_VC );
                                    parameter0.setValue( getc1 );
                                    deviceManager.writeDevice( currentMemory );
                                }
                                if (getc3 != -1) {
                                    parameter0 = memParameterList.get( SideSelect.S_UNILATERAL_LEFT ).getHAParameterById( ParameterString.P_TRB );
                                    parameter0.setValue( getc3 );
                                    deviceManager.writeDevice( currentMemory );
                                }
                                if (getc5 != -1) {
                                    parameter0 = memParameterList.get( SideSelect.S_UNILATERAL_LEFT ).getHAParameterById( ParameterString.P_BASS );
                                    parameter0.setValue( getc5 );
                                    deviceManager.writeDevice( currentMemory );
                                }
                                if (getc2 != -1) {
                                    parameter0 = memParameterList.get( SideSelect.S_UNILATERAL_RIGHT ).getHAParameterById( ParameterString.P_VC );
                                    parameter0.setValue( getc2 );
                                    deviceManager.writeDevice( currentMemory );
                                }
                                if (getc4 != -1) {
                                    parameter0 = memParameterList.get( SideSelect.S_UNILATERAL_RIGHT ).getHAParameterById( ParameterString.P_TRB );
                                    parameter0.setValue( getc4 );
                                    deviceManager.writeDevice( currentMemory );
                                }
                                if (getc6 != -1) {
                                    parameter0 = memParameterList.get( SideSelect.S_UNILATERAL_RIGHT ).getHAParameterById( ParameterString.P_BASS );
                                    parameter0.setValue( getc6 );
                                    deviceManager.writeDevice( currentMemory );
                                }
                                Message message = new Message();
                                message.what = 2;
                                handler.sendMessageDelayed( message, 300 );

                                break;
                            default:
                                break;
                        }
//                        break;
//                    case 7:
                    } else if (messageEvent.getActivityFlag() == 13) {
                        switch ((Integer) messageEvent.getObject()) {
                            case OperationType.OP_AUTO_CHECK:
                            case OperationType.OP_SELECT_MEMORY:
                                Log.d( "vvv", "read3" );
                                ZhitingyunApplication.deviceManager.readDevice();
                                break;
                            case OperationType.OP_READ_CONFIG:
                                Log.d( "vvv", "finish3" );
                                currentMemory = ZhitingyunApplication.deviceManager.getCurrentMemory();
                                sysParameterList = ZhitingyunApplication.deviceManager.getSysParameterList();
                                memParameterList = ZhitingyunApplication.deviceManager.getMemBinParameterList();


                                SharedPreferences sharedPreferences1 = getSharedPreferences( "mySP", Context.MODE_PRIVATE );
                                Integer getd1 = sharedPreferences1.getInt( "save19", -1 );
                                Integer getd2 = sharedPreferences1.getInt( "save20", -1 );
                                Integer getd3 = sharedPreferences1.getInt( "save21", -1 );
                                Integer getd4 = sharedPreferences1.getInt( "save22", -1 );
                                Integer getd5 = sharedPreferences1.getInt( "save23", -1 );
                                Integer getd6 = sharedPreferences1.getInt( "save24", -1 );

                                HAParameter parameter0 = null;
                                if (getd1 != -1) {
                                    parameter0 = memParameterList.get( SideSelect.S_UNILATERAL_LEFT ).getHAParameterById( ParameterString.P_VC );
                                    parameter0.setValue( getd1 );
                                    deviceManager.writeDevice( currentMemory );
                                }
                                if (getd3 != -1) {
                                    parameter0 = memParameterList.get( SideSelect.S_UNILATERAL_LEFT ).getHAParameterById( ParameterString.P_TRB );
                                    parameter0.setValue( getd3 );
                                    deviceManager.writeDevice( currentMemory );
                                }
                                if (getd5 != -1) {
                                    parameter0 = memParameterList.get( SideSelect.S_UNILATERAL_LEFT ).getHAParameterById( ParameterString.P_BASS );
                                    parameter0.setValue( getd5 );
                                    deviceManager.writeDevice( currentMemory );
                                }
                                if (getd2 != -1) {
                                    parameter0 = memParameterList.get( SideSelect.S_UNILATERAL_RIGHT ).getHAParameterById( ParameterString.P_VC );
                                    parameter0.setValue( getd2 );
                                    deviceManager.writeDevice( currentMemory );
                                }
                                if (getd4 != -1) {
                                    parameter0 = memParameterList.get( SideSelect.S_UNILATERAL_RIGHT ).getHAParameterById( ParameterString.P_TRB );
                                    parameter0.setValue( getd4 );
                                    deviceManager.writeDevice( currentMemory );
                                }
                                if (getd6 != -1) {
                                    parameter0 = memParameterList.get( SideSelect.S_UNILATERAL_RIGHT ).getHAParameterById( ParameterString.P_BASS );
                                    parameter0.setValue( getd6 );
                                    deviceManager.writeDevice( currentMemory );
                                }
                                Message message = new Message();
//                                message.what = 3;
//                                handler.sendMessageDelayed( message, 300 );

                                break;
                            default:
                                break;
                        }
//                        break;
                    }
                    break;

            case 7://configOnFail
                if (messageEvent.getActivityFlag() == 10){
                    switch ((Integer) messageEvent.getObject()) {
                        case OperationType.OP_AUTO_CHECK:
                            if (deviceInfos0!=null)
                                ZhitingyunApplication.deviceManager.disconnectDevice(deviceInfos0.get( 0 ));
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
                else if (messageEvent.getActivityFlag() == 11){
                    switch ((Integer) messageEvent.getObject()) {
                        case OperationType.OP_AUTO_CHECK:
                            if (deviceInfos1!=null)
                                ZhitingyunApplication.deviceManager.disconnectDevice(deviceInfos0.get( 0 ));
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
                else if (messageEvent.getActivityFlag() == 12){
                    switch ((Integer) messageEvent.getObject()) {
                        case OperationType.OP_AUTO_CHECK:
                            if (deviceInfos2!=null)
                                ZhitingyunApplication.deviceManager.disconnectDevice(deviceInfos0.get( 0 ));
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
                else if (messageEvent.getActivityFlag() == 13){
                    switch ((Integer) messageEvent.getObject()) {
                        case OperationType.OP_AUTO_CHECK:
                            if (deviceInfos3!=null)
                                ZhitingyunApplication.deviceManager.disconnectDevice(deviceInfos0.get( 0 ));
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

                break;
        }
    }



    @OnClick({R.id.back, R.id.changeview})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.changeview:
//                view.showContextMenu();
//                initDialog();
                break;
        }
    }


    @Override
    protected void initView() {
        super.initView();


//        environmentFragment0 = new EnvironmentFragment(0);
//        environmentFragment1 = (EnvironmentFragment) EnvironmentFragment.newInstance();
//        environmentFragment2 = (EnvironmentFragment) EnvironmentFragment.newInstance();
//        environmentFragment3 = (EnvironmentFragment) EnvironmentFragment.newInstance();

//        fragmentList.add(new EnvironmentFragment(0));
//        fragmentList.add(new EnvironmentFragment(1));
//        fragmentList.add(new EnvironmentFragment(2));
//        fragmentList.add(new EnvironmentFragment(3));

        environmentFragment=new EnvironmentFragment( ongetInfo );
        environmentJTFragment=new EnvironmentJTFragment( ongetInfo );
        environmentHWFragment=new EnvironmentHWFragment( ongetInfo );
        environmentTYFragment=new EnvironmentTYFragment( ongetInfo );



        fragmentList.add(environmentFragment);
        fragmentList.add(environmentJTFragment);
        fragmentList.add(environmentHWFragment);
        fragmentList.add(environmentTYFragment);

        titleList.add("室内");
        titleList.add("交通");
        titleList.add("户外");
        titleList.add("通用");
        BaseTabAdapter adapter = new BaseTabAdapter(getSupportFragmentManager(),
                titleList, fragmentList);
        vp.setAdapter(adapter);
        vp.setCurrentItem(0);
//        deviceManager.readDevice();
//        deviceManager.setCurrentSelect(1);
        tab.setupWithViewPager(vp);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                selectPosition = position;
                if (position == 0&&refreh0 == 1) {
                    environmentFragment.setCurrent();
                    final ProgressDialog proDialog = android.app.ProgressDialog.show(AutoRegulationActivity.this, "正在读取设备数据", "预计等待3~5秒");
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
//                    deviceManager.setCurrentSelect(1);
                    return;

                }
                if (position == 1&&refresh1 == 1) {
                    environmentJTFragment.setCurrent();
                    final ProgressDialog proDialog = android.app.ProgressDialog.show(AutoRegulationActivity.this, "正在读取设备数据", "预计等待3~5秒");
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
//                    environmentJTFragment
//                    deviceManager.setCurrentSelect(2);
                    return;
                }
                if (position == 2&&refresh2 == 1) {
                    environmentHWFragment.setCurrent();
                    final ProgressDialog proDialog = android.app.ProgressDialog.show(AutoRegulationActivity.this, "正在读取设备数据", "预计等待3~5秒");
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
//                    deviceManager.setCurrentSelect(3);
                    return;
                }
                if (position == 3&&refresh3 == 1) {
                    environmentTYFragment.setCurrent();
                    final ProgressDialog proDialog = android.app.ProgressDialog.show(AutoRegulationActivity.this, "正在读取设备数据", "预计等待3~5秒");
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
//                    deviceManager.setCurrentSelect(4);
                    return;
                }
//                fillAllParameterList();

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



    }



    public void show(View v) {
        popupMenu = new PopupMenu(this, v);
        popupMenu.getMenuInflater().inflate(R.menu.change, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_content_one:
//                Intent intent = getIntent();
                SharedPreferences sharedPreferences = getSharedPreferences("mySP", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();


                Integer a1=envir1.get( 0 );
                editor.putInt("save1",a1);
                Integer a2=envir1.get( 1 );
                editor.putInt("save2",a2);

                Integer a3=envir2.get( 0 );
                editor.putInt("save3",a3);
                Integer a4=envir2.get( 1 );
                editor.putInt("save4",a4);
                Integer a5=envir2.get( 2 );
                editor.putInt("save5",a5);
                Integer a6=envir2.get( 3 );
                editor.putInt("save6",a6);

                Integer b1=envir3.get( 0 );
                editor.putInt("save7",b1);
                Integer b2=envir3.get( 1 );
                editor.putInt("save8",b2);

                Integer b3=envir4.get( 0 );
                editor.putInt("save9",b3);
                Integer b4=envir4.get( 1 );
                editor.putInt("save10",b4);
                Integer b5=envir4.get( 2 );
                editor.putInt("save11",b5);
                Integer b6=envir4.get( 3 );
                editor.putInt("save12",b6);

                Integer c1=envir5.get( 0 );
                editor.putInt("save13",c1);
                Integer c2=envir5.get( 1 );
                editor.putInt("save14",c2);

                Integer c3=envir6.get( 0 );
                editor.putInt("save15",c3);
                Integer c4=envir6.get( 1 );
                editor.putInt("save16",c4);
                Integer c5=envir6.get( 2 );
                editor.putInt("save17",c5);
                Integer c6=envir6.get( 3 );
                editor.putInt("save18",c6);

                Integer d1=envir7.get( 0 );
                editor.putInt("save19",d1);
                Integer d2=envir7.get( 1 );
                editor.putInt("save20",d2);

                Integer d3=envir8.get( 0 );
                editor.putInt("save21",d3);
                Integer d4=envir8.get( 1 );
                editor.putInt("save22",d4);
                Integer d5=envir8.get( 2 );
                editor.putInt("save23",d5);
                Integer d6=envir8.get( 3 );
                editor.putInt("save24",d6);
                editor.commit();
                break;
            case R.id.item_content_two:
                SharedPreferences sharedPreferences1=getSharedPreferences( "mySP",Context.MODE_PRIVATE );
                Integer geta1=sharedPreferences1.getInt("save1",0);
                Integer geta2=sharedPreferences1.getInt("save2",0);
                Integer geta3=sharedPreferences1.getInt("save3",0);
                Integer geta4=sharedPreferences1.getInt("save4",0);
                Integer geta5=sharedPreferences1.getInt("save5",0);
                Integer geta6=sharedPreferences1.getInt("save6",0);

                Integer getb1=sharedPreferences1.getInt("save7",0);
                Integer getb2=sharedPreferences1.getInt("save8",0);
                Integer getb3=sharedPreferences1.getInt("save9",0);
                Integer getb4=sharedPreferences1.getInt("save10",0);
                Integer getb5=sharedPreferences1.getInt("save11",0);
                Integer getb6=sharedPreferences1.getInt("save12",0);

                Integer getc1=sharedPreferences1.getInt("save13",0);
                Integer getc2=sharedPreferences1.getInt("save14",0);
                Integer getc3=sharedPreferences1.getInt("save15",0);
                Integer getc4=sharedPreferences1.getInt("save16",0);
                Integer getc5=sharedPreferences1.getInt("save17",0);
                Integer getc6=sharedPreferences1.getInt("save18",0);

                Integer getd1=sharedPreferences1.getInt("save19",0);
                Integer getd2=sharedPreferences1.getInt("save20",0);
                Integer getd3=sharedPreferences1.getInt("save21",0);
                Integer getd4=sharedPreferences1.getInt("save22",0);
                Integer getd5=sharedPreferences1.getInt("save23",0);
                Integer getd6=sharedPreferences1.getInt("save24",0);


                StaticConfigCallback.getInStanceBlock().setActivityFlag(10);
                ZhitingyunApplication.deviceManager.setCurrentSelect( 1 );




                break;
        }
        return true;
    }






    interface OngetInfo {
        void ongetInfo(int flag, List<Integer> list);
    }




}
