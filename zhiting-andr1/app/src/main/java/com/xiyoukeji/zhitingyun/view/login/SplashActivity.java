package com.xiyoukeji.zhitingyun.view.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.xiyoukeji.zhitingyun.R;
import com.xiyoukeji.zhitingyun.base.BaseActivity;
import com.xiyoukeji.zhitingyun.constant.AppConstant;
import com.xiyoukeji.zhitingyun.util.SPUtil;
import com.xiyoukeji.zhitingyun.view.main.MainActivity;
import com.xiyoukeji.zhitingyun.viewmodel.login.LoginViewModel;


public class SplashActivity extends AppCompatActivity {
    int time=3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler.postDelayed(runnable,2000);
    }



    Handler handler=new Handler();
    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            time--;
            handler.postDelayed(this,1000);

            if(time==0){
                finish();

                SharedPreferences sharedPreferences = getSharedPreferences("mySP", Context.MODE_PRIVATE);
                int id = sharedPreferences.getInt("id", 1);
                String tel=sharedPreferences.getString( "telphone","" );
                String password=sharedPreferences.getString( "password","" );
                String token = sharedPreferences.getString("token", "");

                if (token.isEmpty()) {
                    Intent intent = new Intent( SplashActivity.this, LoginActivity.class );
                    startActivity( intent );
                }else {
                    Intent intent = new Intent( SplashActivity.this, MainActivity.class );
                    startActivity( intent );
                }
            }
        }
    };
}
