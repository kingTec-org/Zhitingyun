package com.example.zhitingyun.base;

import android.view.View;

import com.google.gson.Gson;
import com.qmuiteam.qmui.arch.QMUIFragment;

import org.greenrobot.eventbus.EventBus;

import java.text.DecimalFormat;


/**
 * Created by cgspine on 2018/1/7.
 */

public abstract class BaseFragment extends QMUIFragment {
    public Gson gson;


    public BaseFragment() {
    }

    public String getDecimal(Long aLong) {
        return new DecimalFormat("0.00").format((float) aLong / 100);
    }

    public String getDecimal(Double aLong) {
        return new DecimalFormat("0.00").format(aLong);
    }


    @Override
    protected View onCreateView() {
        gson = new Gson();
        EventBus.getDefault().register(this);
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);


    }

//    @Override
//    protected int backViewInitOffset() {
//        return QMUIDisplayHelper.dp2px(getContext(), 100);
//    }

    @Override
    public void onResume() {
        super.onResume();

    }

}
