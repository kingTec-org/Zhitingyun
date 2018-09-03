package com.xiyoukeji.zhitingyun.view.main;

import android.annotation.SuppressLint;
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
import com.xiyoukeji.zhitingyun.data.entity.MessageEvent;
import com.xiyoukeji.zhitingyun.widget.StaticConfigCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.earhear.hadevicelib.DeviceInfo;
import cn.earhear.hadevicelib.HAParameterList;
import cn.earhear.hadevicelib.constant.OperationType;

@SuppressLint("ValidFragment")

public class EnvironmentJTFragment extends BaseFragment {
    @BindView(R.id.tab2 )
    XTabLayout tab;
    @BindView( R.id.vp2 )
    ViewPager vp;

    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();
    private AutoRegulationActivity.OngetInfo ongetInfo;
    List<Integer> envir1 = new ArrayList<>();
    List<Integer> envir2 = new ArrayList<>();

    InsideJTFragment insideFragment;
    HzJTFragment hzFragment;
    private List<HAParameterList> sysParameterList;
    private List<HAParameterList> memParameterList;
    private int currentMemory;
    List<DeviceInfo> deviceInfoList;

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_volume_adjustment;
    }


    public EnvironmentJTFragment(AutoRegulationActivity.OngetInfo ongetInfo) {
        this.ongetInfo = ongetInfo;
    }

public void setCurrent(){
    StaticConfigCallback.getInStanceBlock().setActivityFlag(1);
    ZhitingyunApplication.deviceManager.setCurrentSelect( 2 );
}

    @Override
    protected void initView(View view) {
        super.initView(view);
        EventBus.getDefault().register(this);
        ongetInfo.ongetInfo( 9,null );


        insideFragment=new InsideJTFragment(ongetInfo);
        hzFragment=new HzJTFragment(ongetInfo);

        fragmentList.add(insideFragment);
        fragmentList.add(hzFragment);

        titleList.add("音量");
        titleList.add("频段");
//        BaseTabAdapter adapter = new BaseTabAdapter(getSupportFragmentManager(),
//                titleList, fragmentList);
        BaseTabAdapter adapter = new BaseTabAdapter(getChildFragmentManager(), titleList, fragmentList);
        vp.setAdapter( adapter );
        vp.setCurrentItem( 0 );
        tab.setupWithViewPager( vp );



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
                if (messageEvent.getActivityFlag() == 1){
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
                            insideFragment.fillAllParameterList(sysParameterList,memParameterList,currentMemory);
                            hzFragment.fillAllParameterList(sysParameterList,memParameterList,currentMemory);
                            break;
                        default:
                            break;
                    }
                }

                break;
            case 7://configOnFail
                if (messageEvent.getActivityFlag() == 1){
                    switch ((Integer) messageEvent.getObject()) {
                        case OperationType.OP_AUTO_CHECK:
                            if (deviceInfoList!=null)
                            ZhitingyunApplication.deviceManager.disconnectDevice(deviceInfoList.get( 0 ));
                            break;
                        case OperationType.OP_READ_CONFIG:
                            Log.d( "vvv","fail1" );
                            ZhitingyunApplication.deviceManager.readDevice();
                            break;
                        default:
                            break;
                    }
                }

                break;
        }
    }



}
