package com.xiyoukeji.zhitingyun.widget;

import com.xiyoukeji.zhitingyun.data.entity.MessageEvent;

import org.greenrobot.eventbus.EventBus;

import cn.earhear.hadevicelib.callback.ConfigCallback;

/**
 * Created by dasiy on 2018/7/20.
 */

public class StaticConfigCallback extends ConfigCallback{
    private static StaticConfigCallback s = null;
    private static StaticConfigCallback configCallback;
    private  int activityFlag;

    public static StaticConfigCallback getInStanceBlock() {
        if (configCallback == null)
            synchronized (StaticConfigCallback.class) {
                if (configCallback == null)
                    configCallback = new StaticConfigCallback();

            }

        return configCallback;

    }

//    public StaticConnectCallback() {
//
//    }

//    public interface configCB（） {}

    public  void setActivityFlag(int activityFlag){
        this.activityFlag = activityFlag;

    }

    @Override
    public void onSuccess(int i) {
        EventBus.getDefault().post(new MessageEvent(activityFlag,6, i));

    }

    @Override
    public void onFail(int i, int i1) {
        EventBus.getDefault().post(new MessageEvent(activityFlag,7, i));


    }

    public static class ActivityFlag implements ConfigCB{

        @Override
        public void setFlag(int activity) {

        }
    }
    public interface ConfigCB{
        void setFlag(int activity);
    }
//
//
//
//    private final static class Callback extends ConfigCallback {
//        @Override
//        public void onSuccess(int i) {
//
//        }
//
//        @Override
//        public void onFail(int i, int i1) {
//
//        }
//
//
////        @Override
////        public void onConnectSuccess() {
////            EventBus.getDefault().post(new MessageEvent(0, null));
////
////        }
////
////        @Override
////        public void onDisconnect(boolean b) {
////
////            EventBus.getDefault().post(new MessageEvent(1, b));
////        }
//
////        @Override
////        public void onDetecting(DeviceInfo deviceInfo) {
////            EventBus.getDefault().post(new MessageEvent(3, null));
////
////        }
////
////        @Override
////        public void onDetectFinish(List<DeviceInfo> list) {
////            EventBus.getDefault().post(new MessageEvent(4, list));
////
////        }
////
////        @Override
////        public void onFail(int i) {
////            EventBus.getDefault().post(new MessageEvent(5, i));
////        }
//    }

}
