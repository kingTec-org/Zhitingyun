package com.xiyoukeji.zhitingyun.widget;

import com.xiyoukeji.zhitingyun.data.entity.MessageEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import cn.earhear.hadevicelib.DeviceInfo;
import cn.earhear.hadevicelib.callback.DetectCallback;

/**
 * Created by dasiy on 2018/7/20.
 */

public class StaticDetectCallback {
    private static StaticDetectCallback s = null;
    private static Callback detectCallback;

    public static DetectCallback getInStanceBlock() {
        if (detectCallback == null)
            synchronized (StaticDetectCallback.class) {
                if (detectCallback == null)
                    detectCallback = new Callback();

            }

        return detectCallback;

    }

//    public StaticConnectCallback() {
//
//    }


    private final static class Callback extends DetectCallback {


//        @Override
//        public void onConnectSuccess() {
//            EventBus.getDefault().post(new MessageEvent(0, null));
//
//        }
//
//        @Override
//        public void onDisconnect(boolean b) {
//
//            EventBus.getDefault().post(new MessageEvent(1, b));
//        }

        @Override
        public void onDetecting(DeviceInfo deviceInfo) {
            EventBus.getDefault().post(new MessageEvent(3, deviceInfo));

        }

        @Override
        public void onDetectFinish(List<DeviceInfo> list) {
            EventBus.getDefault().post(new MessageEvent(4, list));

        }

        @Override
        public void onFail(int i) {
            EventBus.getDefault().post(new MessageEvent(5, i));
        }
    }

}
