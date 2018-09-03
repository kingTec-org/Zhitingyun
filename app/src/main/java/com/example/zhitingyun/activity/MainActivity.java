package com.example.zhitingyun.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import com.example.zhitingyun.R;
import com.example.zhitingyun.base.BaseFragment;
import com.example.zhitingyun.base.BaseFragmentActivity;
import com.example.zhitingyun.fragment.LoginFragment;
import com.example.zhitingyun.fragment.MainFragment;
import com.example.zhitingyun.widget.WS;
import com.example.zhitingyun.yunxin.Extras;
import com.example.zhitingyun.yunxin.SessionCustomization;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.StatusCode;
import com.netease.nimlib.sdk.auth.AuthServiceObserver;
import com.netease.nimlib.sdk.msg.model.IMMessage;


import org.java_websocket.client.WebSocketClient;

import java.net.URI;

import coder.mylibrary.manager.ActivityManager;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class MainActivity extends BaseFragmentActivity {

    WebSocketClient mSocketClient;
    URI uri;

    @Override
    protected int getContextViewId() {
        return R.id.main_container;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().addActivity(this);
        WS.getInStanceBlock().connect();

//        NIMClient.getService(AuthServiceObserver.class).observeOnlineStatus(userStatusObserver, true);


        if (savedInstanceState == null) {
            BaseFragment fragment = new MainFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(getContextViewId(), fragment, fragment.getClass().getSimpleName())
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .commit();
        }

    }


    Observer<StatusCode> userStatusObserver = new Observer<StatusCode>() {

        @Override
        public void onEvent(StatusCode code) {
            if (code.wontAutoLogin()) {
                ActivityManager.getInstance().currentActivity().startActivity(new Intent(ActivityManager.getInstance().currentActivity(), LoginActivity.class).putExtra("from", 1));

                Log.d("vvv", "kickOut");
//                kickOut(code)
            }
//            else {
//                if (code == StatusCode.NET_BROKEN) {
//                    notifyBar.setVisibility(View.VISIBLE);
//                    notifyBarText.setText(R.string.net_broken);
//                } else if (code == StatusCode.UNLOGIN) {
//                    notifyBar.setVisibility(View.VISIBLE);
//                    notifyBarText.setText(R.string.nim_status_unlogin);
//                } else if (code == StatusCode.CONNECTING) {
//                    notifyBar.setVisibility(View.VISIBLE);
//                    notifyBarText.setText(R.string.nim_status_connecting);
//                } else if (code == StatusCode.LOGINING) {
//                    notifyBar.setVisibility(View.VISIBLE);
//                    notifyBarText.setText(R.string.nim_status_logining);
//                } else {
//                    notifyBar.setVisibility(View.GONE);
//                }
//            }
        }
    };


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (MainActivity.this.getCurrentFragment() instanceof LoginFragment) {
            } else
                popBackStack();
        }

        return true;
    }
}
