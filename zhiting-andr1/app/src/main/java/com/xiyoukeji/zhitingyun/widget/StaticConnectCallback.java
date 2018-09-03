package com.xiyoukeji.zhitingyun.widget;

import com.xiyoukeji.zhitingyun.data.entity.MessageEvent;
import com.xiyoukeji.zhitingyun.util.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import cn.earhear.hadevicelib.callback.ConnectCallback;

/**
 * Created by dasiy on 2018/7/20.
 */

public class StaticConnectCallback {
    private static StaticConnectCallback s = null;
    private static Callback connectCallback;

    public static ConnectCallback getInStanceBlock() {
        if (connectCallback == null)
            synchronized (StaticConnectCallback.class) {
                if (connectCallback == null)
                    connectCallback = new Callback();

            }

        return connectCallback;

    }

//    public StaticConnectCallback() {
//
//    }


    private final static class Callback extends ConnectCallback {


        @Override
        public void onConnectSuccess() {
            EventBus.getDefault().post(new MessageEvent(0, null));
//            ToastUtils.showLong( "连接设备连接成功" );
        }

        @Override
        public void onDisconnect(boolean b) {

            EventBus.getDefault().post(new MessageEvent(1, b));
            ToastUtils.showLong( "设备连接已断开" );
        }

        @Override
        public void onFail(int i) {
            EventBus.getDefault().post(new MessageEvent(2, i));
        }
    }

}
