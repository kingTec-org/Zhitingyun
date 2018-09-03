package com.xiyoukeji.zhitingyun.view.main;

import android.annotation.SuppressLint;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.androidkun.xtablayout.XTabLayout;
import com.xiyoukeji.zhitingyun.R;
import com.xiyoukeji.zhitingyun.ZhitingyunApplication;
import com.xiyoukeji.zhitingyun.base.BaseFragment;
import com.xiyoukeji.zhitingyun.base.BaseTabAdapter;
import com.xiyoukeji.zhitingyun.data.entity.DeviceEntity;
import com.xiyoukeji.zhitingyun.data.entity.MessageEvent;
import com.xiyoukeji.zhitingyun.hadeviceapi.AllParameter;
import com.xiyoukeji.zhitingyun.widget.StaticConfigCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.earhear.hadevicelib.DeviceInfo;
import cn.earhear.hadevicelib.HAParameter;
import cn.earhear.hadevicelib.HAParameterList;
import cn.earhear.hadevicelib.constant.OperationType;
import cn.earhear.hadevicelib.constant.SideSelect;

import static com.xiyoukeji.zhitingyun.ZhitingyunApplication.deviceManager;

@SuppressLint("ValidFragment")
public class EnvironmentFragment extends BaseFragment {
    @BindView(R.id.tab2 )
    XTabLayout tab;
    @BindView( R.id.vp2 )
    ViewPager vp;

    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();

    private AutoRegulationActivity.OngetInfo ongetInfo;
    List<Integer> envir1 = new ArrayList<>();
    List<Integer> envir2 = new ArrayList<>();

    InsideFragment insideFragment;
    HzFragment hzFragment;
    private int currentMemory;
    private List<AllParameter> allParamList;
    private List<AllParameter> allParameterList;
    private List<HAParameterList> sysParameterList;
    private List<HAParameterList> memParameterList;

    List<DeviceInfo> deviceInfoList;
    private int a1,a2,a3,a4,a5,a6,b1,b2,b3,b4,b5,b6,c1,c2,c3,c4,c5,c6,d1,d2,d3,d4;

    List<Integer> list=new ArrayList<>(  );


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_volume_adjustment;
    }

    public EnvironmentFragment(AutoRegulationActivity.OngetInfo ongetInfo) {
        this.ongetInfo = ongetInfo;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        list.add(0);
//        list.add( 0 );
//        ongetInfo.ongetInfo( 0,list );


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }


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
                DeviceInfo deviceInfo = (DeviceInfo) messageEvent.getObject();
                break;
            case 4://onDetectFinish
                deviceInfoList = (List<DeviceInfo>) messageEvent.getObject();
                break;
            case 5://onFail
            case 6://configOnSuccess
                Log.d( "ddd",messageEvent.getActivityFlag()+"" );
                 if (messageEvent.getActivityFlag() == 0){
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
                            insideFragment.fillAllParameterList(sysParameterList,memParameterList,currentMemory);
                            hzFragment.fillAllParameterList(sysParameterList,memParameterList,currentMemory);

                            insideFragment.insideList(sysParameterList);

//                        initSb();
                            break;
                        default:
                            break;
                    }
                }

                break;
            case 7://configOnFail
                if (messageEvent.getActivityFlag() == 0){
                    switch ((Integer) messageEvent.getObject()) {
                        case OperationType.OP_AUTO_CHECK:
                            if (deviceInfoList!=null)
                            ZhitingyunApplication.deviceManager.disconnectDevice(deviceInfoList.get( 0 ));
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

                break;
        }
    }




    public void setCurrent(){
        StaticConfigCallback.getInStanceBlock().setActivityFlag(0);

        ZhitingyunApplication.deviceManager.setCurrentSelect( 1 );
    }


    @Override
    protected void initView(View view) {
        super.initView(view);
        EventBus.getDefault().register(this);
        ongetInfo.ongetInfo( 8,null );

        insideFragment=new InsideFragment(ongetInfo);
        hzFragment=new HzFragment(ongetInfo);

        fragmentList.add(insideFragment);
        fragmentList.add(hzFragment);

        titleList.add("音量");
        titleList.add("频段");

        BaseTabAdapter adapter = new BaseTabAdapter(getChildFragmentManager(), titleList, fragmentList);
        vp.setAdapter( adapter );
        vp.setCurrentItem( 0 );
        tab.setupWithViewPager( vp );


    }



}
