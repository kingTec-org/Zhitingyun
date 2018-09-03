package com.example.zhitingyun.controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.FrameLayout;

import com.example.zhitingyun.listener.ControlListener;
import com.example.zhitingyun.listener.RefreshListener;


/**
 * Created by dasiy on 2018/3/29.
 */

public abstract class MainController extends FrameLayout {

    public ControlListener listener;
    public RefreshListener refreshListener;

    public void setRefreshListener(RefreshListener refreshListener) {
        this.refreshListener = refreshListener;
    }

    public void setListener(ControlListener listener) {
        this.listener = listener;
    }

    public MainController(@NonNull Context context) {
        super(context);
    }


    protected abstract String getTitle();



}
