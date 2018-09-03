package com.example.zhitingyun.yunxin;

import android.app.Activity;
import android.content.Context;

import com.netease.nimlib.sdk.AbortableFuture;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.LoginInfo;


import java.util.Set;

/**
 * 云信UI组件接口/定制化入口
 * Created by huangjun on 2017/9/29.
 */

public class NimUIKit {

    public static void startP2PSession(Context context, String account) {
        NimUIKitImpl.startP2PSession(context, account);
    }

    /**
     * 手动登陆，由于手动登陆完成之后，UIKit 需要设置账号、构建缓存等，使用此方法登陆 UIKit 会将这部分逻辑处理好，开发者只需要处理自己的逻辑即可
     *
     * @param loginInfo 登陆账号信息
     * @param callback  登陆结果回调
     */
    public static AbortableFuture<LoginInfo> login(LoginInfo loginInfo, final RequestCallback<LoginInfo> callback) {
        return NimUIKitImpl.login(loginInfo, callback);
    }
}
