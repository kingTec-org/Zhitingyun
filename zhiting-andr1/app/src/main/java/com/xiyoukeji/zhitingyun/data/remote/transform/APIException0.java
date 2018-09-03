package com.xiyoukeji.zhitingyun.data.remote.transform;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.xiyoukeji.zhitingyun.ZhitingyunApplication;
import com.xiyoukeji.zhitingyun.base.BaseApplication;
import com.xiyoukeji.zhitingyun.data.entity.BaseModel0;
import com.xiyoukeji.zhitingyun.data.entity.MessageEvent;
import com.xiyoukeji.zhitingyun.util.ActivityManager;
import com.xiyoukeji.zhitingyun.util.Constant;
import com.xiyoukeji.zhitingyun.util.ToastUtils;
import com.xiyoukeji.zhitingyun.view.login.LoginActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class APIException0 extends RuntimeException {
    private int code;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (Constant.INDEX == 0) {
                Constant.INDEX++;
//                SharedPreferences sharedPreferences =.getSharedPreferences("mySP", Context.MODE_PRIVATE);
                ToastUtils.showShort("登录失效");
                ActivityManager.getCurrentActivity().startActivity( new Intent( ActivityManager.getCurrentActivity(),LoginActivity.class ).putExtra("from", 1));
            }
        }
    };

    public APIException0(BaseModel0 baseModel,int code,String message ) {
        super(message);
        this.code = code;
        ToastUtils.showShort(baseModel.getMessage());

        if (baseModel.getCode() != 110 && baseModel.getCode() != 113){
        Toast.makeText( ZhitingyunApplication.sInstance.getApplicationContext(), baseModel.getMessage(), Toast.LENGTH_SHORT).show();}
        else if (ActivityManager.getCurrentActivity() instanceof LoginActivity && baseModel.getCode() == 113){
            Toast.makeText( ZhitingyunApplication.sInstance.getApplicationContext(), baseModel.getMessage(), Toast.LENGTH_SHORT).show();}
        else {
            handler.sendMessageDelayed(new Message(), 200);
        }
    }

}
