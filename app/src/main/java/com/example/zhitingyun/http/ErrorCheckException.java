package com.example.zhitingyun.http;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.example.zhitingyun.activity.LoginActivity;
import com.example.zhitingyun.base.BaseModel;
import com.example.zhitingyun.base.Constant;
import com.qmuiteam.qmui.arch.QMUIFragmentActivity;

import org.greenrobot.eventbus.EventBus;

import coder.mylibrary.base.BaseApplication;
import coder.mylibrary.manager.ActivityManager;
import coder.mylibrary.util.ToastUtil;

/**
 * Created by dasiy on 2017/8/21.
 */

public class ErrorCheckException extends RuntimeException {
//    Resources resources = ActivityManager.getInstance().currentActivity()
//            .getResources();

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (Constant.INDEX == 0) {
                Constant.INDEX++;
                ActivityManager.getInstance().currentActivity().startActivity(new Intent(ActivityManager.getInstance().currentActivity(), LoginActivity.class).putExtra("from", 1));
            }

        }
    };

    public ErrorCheckException(BaseModel baseModel) {
//        if (baseModel.getCode()!=1)
        ToastUtil.showShort(baseModel.getMessage());
//        EventBus.getDefault().post(new MessageEvent(100));
        if (baseModel.getCode() != 110 && baseModel.getCode() != 113)
            Toast.makeText(BaseApplication.baseApplication.getApplicationContext(), baseModel.getMessage(), Toast.LENGTH_SHORT).show();
        else if (ActivityManager.getInstance().currentActivity() instanceof LoginActivity && baseModel.getCode() == 113)
            Toast.makeText(BaseApplication.baseApplication.getApplicationContext(), baseModel.getMessage(), Toast.LENGTH_SHORT).show();
        else {
            handler.sendMessageDelayed(new Message(), 200);
        }
    }
//        if (responseData.getCode() == 0) {
//            msg = "签名错误";
//            ToastUtil.showShort(msg);
//        } else if (responseData.getCode() == 1000) {
//            msg = ActivityManager.getInstance().currentActivity().getResources().getString(R.string.system_error);
//            ToastUtil.showShort(msg);
//
//        } else if (responseData.getCode() == 800) {
////            Intent intent = new Intent(ActivityManager.getInstance().currentActivity(), LoginSendActivity.class);
////            ActivityManager.getInstance().currentActivity().startActivity(intent);
//
//            switch (BaseCom.SYSTEM_TYPE) {
//                case 0:
//                    if (ActivityManager.getInstance().currentActivity() != ProjectConfig.com.xiyoukeji.westlake.activity) {
//                        Intent intent = new Intent(ActivityManager.getInstance().currentActivity(), LoginActivity.class);
//                        intent.putExtra("tag", "lose_token");
//                        ActivityManager.getInstance().currentActivity().startActivity(intent);
//                        ProjectConfig.com.xiyoukeji.westlake.activity = ActivityManager.getInstance().currentActivity();
//
//                    }
//                    break;
//                case 1:
//                    Intent intent = new Intent(ActivityManager.getInstance().currentActivity(), LoginSendActivity.class);
//                    intent.putExtra("tag", "lose_token");
//                    ActivityManager.getInstance().currentActivity().startActivity(intent);
//                    break;
//            }
//
//        } else {
//            msg = responseData.getMessage();
//            ToastUtil.showShort(msg);
//        }
//}
}
