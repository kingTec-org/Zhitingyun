package com.example.zhitingyun.activity;

import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.zhitingyun.R;
import com.example.zhitingyun.base.BaseFragment;
import com.example.zhitingyun.base.BaseFragmentActivity;
import com.example.zhitingyun.fragment.LoginFragment;
import com.example.zhitingyun.fragment.PatientInfoFragment;
import com.just.agentweb.AgentWeb;

import coder.mylibrary.manager.ActivityManager;

public class Main2Activity extends BaseFragmentActivity {
    WebView webView;

    @Override
    protected int getContextViewId() {
        return R.id.zhitingyun;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().addActivity(this);
        int from = getIntent().getIntExtra("from", 0);

//        if (savedInstanceState == null) {
//            BaseFragment fragment = new PatientInfoFragment();
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .add(getContextViewId(), fragment, fragment.getClass().getSimpleName())
//                    .addToBackStack(fragment.getClass().getSimpleName())
//                    .commit();
//        }
    }


}
