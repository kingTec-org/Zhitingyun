package com.example.zhitingyun.listener;

import com.netease.nimlib.sdk.avchat.model.AVChatCommonEvent;
import com.netease.nimlib.sdk.avchat.model.AVChatData;

/**
 * Created by dasiy on 2018/7/13.
 */

public interface SendMessageListener {
    void onSend(Object object, int flag);
}
