package com.example.zhitingyun.activity;

import android.os.Bundle;

import com.example.zhitingyun.R;
import com.example.zhitingyun.base.BaseFragment;
import com.example.zhitingyun.base.BaseFragmentActivity;
import com.example.zhitingyun.fragment.LoginFragment;
import com.example.zhitingyun.fragment.SplashFragment;

import coder.mylibrary.manager.ActivityManager;

/**
 * Created by dasiy on 2018/7/15.
 */

public class SplashActivity extends BaseFragmentActivity {
    @Override
    protected int getContextViewId() {
        return R.id.main_container;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().addActivity(this);
        if (savedInstanceState == null) {
            BaseFragment fragment = new SplashFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(getContextViewId(), fragment, fragment.getClass().getSimpleName())
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .commit();
        }
    }
}
