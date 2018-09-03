package com.example.zhitingyun.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import com.example.zhitingyun.R;
import com.example.zhitingyun.base.BaseFragment;
import com.example.zhitingyun.base.BaseFragmentActivity;
import com.example.zhitingyun.base.Constant;
import com.example.zhitingyun.fragment.LoginFragment;
import com.example.zhitingyun.fragment.TreattingFragment;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.StatusCode;

import coder.mylibrary.manager.ActivityManager;

public class LoginActivity extends BaseFragmentActivity {

    @Override
    protected int getContextViewId() {
        return R.id.zhitingyun;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().addActivity(this);
        int from = getIntent().getIntExtra("from", 0);
        if (from == 1) {
            ActivityManager.getInstance().finishAllActivityBesidesCurrent();
        }
        if (savedInstanceState == null) {
            BaseFragment fragment = new LoginFragment(from);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(getContextViewId(), fragment, fragment.getClass().getSimpleName())
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .commit();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Constant.INDEX = 0;
    }

    //    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (LoginActivity.this.getCurrentFragment() instanceof LoginFragment) {
//            ((LoginFragment) LoginActivity.this.getCurrentFragment()).onKeyDown(keyCode, event);
////            return true;
//        }
//        return true;
////        else
////            return false;
//    }
}
