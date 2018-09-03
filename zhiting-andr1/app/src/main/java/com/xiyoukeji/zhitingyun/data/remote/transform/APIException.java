package com.xiyoukeji.zhitingyun.data.remote.transform;


import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.xiyoukeji.zhitingyun.ZhitingyunApplication;
import com.xiyoukeji.zhitingyun.base.BaseApplication;
import com.xiyoukeji.zhitingyun.data.entity.BaseModel0;
import com.xiyoukeji.zhitingyun.util.ActivityManager;
import com.xiyoukeji.zhitingyun.util.ActivityManager0;
import com.xiyoukeji.zhitingyun.util.Constant;
import com.xiyoukeji.zhitingyun.view.login.LoginActivity;

public class APIException extends RuntimeException {
    private int code;


    public APIException(String message) {
        super(message);
    }

    public APIException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }



}
