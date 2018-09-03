package com.example.zhitingyun.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;


import com.example.zhitingyun.R;

import java.io.ByteArrayOutputStream;

/**
 * Created by dasiy on 2017/9/9.
 */

public class Util {
    private static long lastClickTime;

    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }

        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static SpannableString getSpan(String text, String key, int color) {
        int index = text.indexOf(key);
        if (index != -1) {
            SpannableString spannableString = new SpannableString(text);
            ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(color);
            spannableString.setSpan(foregroundColorSpan, index, index + key.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return spannableString;
        } else
            return null;


    }
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

//    public static void showDialog(Context context, String title, String message, String negative, String positive, DialogInterface.OnClickListener negativeOnClick, DialogInterface.OnClickListener positiveOnClick) {
//  /*
//  这里使用了 android.support.v7.app.AlertDialog.Builder
//  可以直接在头部写 import android.support.v7.app.AlertDialog
//  那么下面就可以写成 AlertDialog.Builder
//  */
//        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context);
//        if (!title.equals(""))
//            builder.setTitle(title);
//        if (!message.equals(""))
//            builder.setMessage(message);
//
//        builder.setNegativeButton(negative, negativeOnClick);
//        builder.setPositiveButton(positive, positiveOnClick);
//
//        builder.show();
//        builder.
//    }


    public static void changeAvatar(Context context, final View.OnClickListener onClickListener) {
        final Dialog dialog = new Dialog(context, R.style.Theme_Light_Dialog);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.inflate_dialog_click_avatar, null);
        //获得dialog的window窗口
        Window window = dialog.getWindow();
        //设置dialog在屏幕底部
        window.setGravity(Gravity.BOTTOM);
        //设置dialog弹出时的动画效果，从屏幕底部向上弹出
        //window.setWindowAnimations(R.style.dialogStyle);
        window.getDecorView().setPadding(0, 0, 0, 0);
        //获得window窗口的属性
        WindowManager.LayoutParams lp = window.getAttributes();
        //设置窗口宽度为充满全屏
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        //设置窗口高度为包裹内容
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        //将设置好的属性set回去
        window.setAttributes(lp);
        //将自定义布局加载到dialog上
        dialog.setContentView(dialogView);

        dialog.show();

        Button btnCamera = (Button) dialogView.findViewById(R.id.btn_camera_avatar_dialog);
        Button btnAlbum = (Button) dialogView.findViewById(R.id.btn_album_avatar_dialog);
        Button btnCancel = (Button) dialogView.findViewById(R.id.btn_cancel_avatar_dialog);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                onClickListener.onClick(v);
            }
        });

        btnAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                onClickListener.onClick(v);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }



    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
}
