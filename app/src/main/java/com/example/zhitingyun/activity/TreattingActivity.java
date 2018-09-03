package com.example.zhitingyun.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.example.zhitingyun.R;
import com.example.zhitingyun.base.BaseFragment;
import com.example.zhitingyun.base.BaseFragmentActivity;
import com.example.zhitingyun.fragment.MainFragment;
import com.example.zhitingyun.fragment.TreattingFragment;
import com.example.zhitingyun.model.OrderDetail;
import com.netease.nimlib.sdk.avchat.model.AVChatData;

import coder.mylibrary.manager.ActivityManager;

public class TreattingActivity extends BaseFragmentActivity {

    @Override
    protected int getContextViewId() {
        return R.id.main_container;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityManager.getInstance().addActivity(this);
        if (savedInstanceState == null) {
            BaseFragment fragment = new TreattingFragment((OrderDetail) getIntent().getSerializableExtra("orderDetail"), getIntent().getStringExtra("receiverId"), getIntent().getIntExtra("from", 2));
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(getContextViewId(), fragment, fragment.getClass().getSimpleName())
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .commit();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            return true;
        } else
            return false;
//        if (TreattingActivity.this.getCurrentFragment() instanceof TreattingFragment) {
//            ((TreattingFragment) TreattingActivity.this.getCurrentFragment()).onKeyDown(keyCode, event);
//            return true;
//        } else
//            return false;
    }
}
