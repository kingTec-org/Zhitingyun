package com.xiyoukeji.zhitingyun.data.remote;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.xiyoukeji.zhitingyun.ZhitingyunApplication;
import com.xiyoukeji.zhitingyun.data.remote.transform.APIException;
import com.xiyoukeji.zhitingyun.util.ActivityManager;
import com.xiyoukeji.zhitingyun.util.ToastUtils;
import com.xiyoukeji.zhitingyun.util.Utils;
import com.xiyoukeji.zhitingyun.view.login.LoginActivity;

import org.apache.http.conn.ConnectTimeoutException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;


public abstract class BaseObserver<T> implements Observer<T> {

    @Override
    public void onError(Throwable e) {
        if (e instanceof SocketTimeoutException) {
            ToastUtils.showShort("网络中断，请检查您的网络状态");
        } else if (e instanceof ConnectException) {
            ToastUtils.showShort("网络中断，请检查您的网络状态");
        } else if (e instanceof ConnectTimeoutException) {
            ToastUtils.showShort("网络中断，请检查您的网络状态");
        } else if (e instanceof UnknownHostException) {

            Toast.makeText(Utils.getContext(), "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
        } else if (e instanceof IllegalStateException) {
            ToastUtils.showShort("解析失败");
        } else if (e instanceof APIException){

        } else {
            ToastUtils.showShort(e.getMessage());
        }
    }

    @Override
    public void onComplete() {

    }
}
