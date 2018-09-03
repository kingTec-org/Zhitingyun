package com.xiyoukeji.zhitingyun.widget;

import android.content.Context;
import android.content.SharedPreferences;

import com.xiyoukeji.zhitingyun.ZhitingyunApplication;
import com.xiyoukeji.zhitingyun.data.entity.MessageEvent;
import com.xiyoukeji.zhitingyun.util.ActivityManager;
import com.xiyoukeji.zhitingyun.yunxin.SPUtil;

import org.greenrobot.eventbus.EventBus;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.ByteString;

/**
 * Created by dasiy on 2018/7/20.
 */

public class WS {
    private static WS s = null;
    private EchoWebSocketListener listener;
    private Request request;
    private OkHttpClient okHttpClient;
    private okhttp3.WebSocket webSocket;

    public static WS getInStanceBlock() {
        if (s == null)
            synchronized (WS.class) {
                if (s == null)
                    s = new WS();

            }

        return s;

    }

    public WS() {
        SharedPreferences sharedPreferences = ZhitingyunApplication.sInstance.getSharedPreferences("mySP", Context.MODE_PRIVATE);
        int address = sharedPreferences.getInt("id", 1);
        if (listener == null)
            listener = new EchoWebSocketListener();
        if (request == null)
            request = new Request.Builder()
                    .url("wss://uhear.com.cn/webSocket/news/1," + address)
                    .build();
        if (okHttpClient == null)
            okHttpClient = new OkHttpClient();

    }

    public void connect() {
        if (okHttpClient != null) {

            ActivityManager.getCurrentActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    okHttpClient.newWebSocket(request, listener);

                    //此时已在主线程中，可以更新UI了
                }
            });
        }

    }

    public void disConnect() {
        if (okHttpClient != null)
            okHttpClient.dispatcher().executorService().shutdown();
    }

    public void send(String message) {
        if (webSocket != null)
            webSocket.send(message);

    }

    private final class EchoWebSocketListener extends okhttp3.WebSocketListener {

        @Override
        public void onOpen(okhttp3.WebSocket webSocket, Response response) {
            WS.this.webSocket = webSocket;
        }

        @Override
        public void onMessage(okhttp3.WebSocket webSocket, String text) {
            EventBus.getDefault().post(new MessageEvent(100, text));
        }

        @Override
        public void onMessage(okhttp3.WebSocket webSocket, ByteString bytes) {
            EventBus.getDefault().post(new MessageEvent(100, bytes));
        }

        @Override
        public void onClosing(okhttp3.WebSocket webSocket, int code, String reason) {
            if (!SPUtil.get(ActivityManager.getCurrentActivity(), "token", "").toString().equals(""))
                connect();
        }

        @Override
        public void onClosed(okhttp3.WebSocket webSocket, int code, String reason) {
            webSocket.close(1000, null);
        }

        @Override
        public void onFailure(okhttp3.WebSocket webSocket, Throwable t, Response response) {
            connect();
        }
    }

}
