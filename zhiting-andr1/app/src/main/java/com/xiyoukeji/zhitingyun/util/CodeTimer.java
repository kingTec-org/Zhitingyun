package com.xiyoukeji.zhitingyun.util;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;

public class CodeTimer extends CountDownTimer {

    /**
     * 计时按钮能否点击
     */
    public static final String IS_ENABLE = "is_enable";
    /**
     * 计时按钮显示的信息
     */
    public static final String MESSAGE = "message";

    private Context mContext;
    private Intent mCodeReceiverIntent;

    /**
     * @param context           上下文
     * @param millisInFuture    倒计时的时长
     * @param countDownInterval 间隔时间
     * @param action            用于区别不同广播的action，如果有多个互不影响的button点击发送验证码，就要有不同action的广播接收记时信息
     */
    public CodeTimer(Context context, long millisInFuture, long countDownInterval, String action) {
        super(millisInFuture, countDownInterval);
        this.mContext = context;
        mCodeReceiverIntent = new Intent(action);
    }

    @Override
    public void onTick(long millisUntilFinished) {
        //每过millisUntilFinished的间隔时间，就会发送广播
        mCodeReceiverIntent.putExtra(IS_ENABLE, false);
        mCodeReceiverIntent.putExtra(MESSAGE, (millisUntilFinished / 1000) + "s 后重发");
        mContext.sendBroadcast(mCodeReceiverIntent);
    }

    // 结束
    @Override
    public void onFinish() {
        mCodeReceiverIntent.putExtra(IS_ENABLE, true);
        mCodeReceiverIntent.putExtra(MESSAGE, "获取验证码");
        mContext.sendBroadcast(mCodeReceiverIntent);
    }
}
