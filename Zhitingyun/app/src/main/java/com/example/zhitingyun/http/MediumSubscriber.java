package com.example.zhitingyun.http;

import rx.Subscriber;

/**
 * Created by dasiy on 2017/8/21.
 */

public class MediumSubscriber<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
//        ActivityManager.getInstance().currentActivity().sendBroadcast(new Intent().setAction("com.xiyoukeji.packagbutler.dismiss"));/*进度条消失*/
//        Log.d("ddd", e.getClass().getName());
//        if (e.getClass().getName().equals("java.net.ConnectException"))
//            ToastUtil.showShort(ActivityManager.getInstance().currentActivity().getResources().getString(R.string.connect_exception));
//        if (e.getClass().getName().equals("java.net.SocketTimeoutException"))
//            ToastUtil.showShort(ActivityManager.getInstance().currentActivity().getResources().getString(R.string.request_timeout));
    }

    @Override
    public void onNext(T t) {

    }
}
