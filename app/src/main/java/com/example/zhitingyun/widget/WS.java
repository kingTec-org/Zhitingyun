package com.example.zhitingyun.widget;

import android.util.Log;

import com.example.zhitingyun.activity.MainActivity;
import com.example.zhitingyun.model.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.java_websocket.WebSocket;
import org.java_websocket.WebSocketListener;
import org.java_websocket.drafts.Draft;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.Handshakedata;
import org.java_websocket.handshake.ServerHandshake;
import org.java_websocket.handshake.ServerHandshakeBuilder;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

import coder.mylibrary.manager.ActivityManager;
import coder.mylibrary.util.SPUtil;
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
        if (listener == null)
            listener = new EchoWebSocketListener();
        if (request == null)
            request = new Request.Builder()
                    .url("wss://uhear.com.cn/webSocket/news/2," + SPUtil.get(ActivityManager.getInstance().currentActivity(), "userId", "").toString())
                    .build();
        if (okHttpClient == null)
            okHttpClient = new OkHttpClient();

    }

    public void connect() {
        Log.d( "ggg","prepare" );
        if (okHttpClient != null) {
            Log.d( "ggg","ready" );
            ActivityManager.getInstance().currentActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    okHttpClient.newWebSocket(request, listener);

                    //此时已在主线程中，可以更新UI了
                }
            });
        }

    }

    public void disConnect() {
        if (webSocket != null)
            webSocket.close(1000, "close");

//            okHttpClient.dispatcher().executorService().shutdown();
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
            if (!SPUtil.get(ActivityManager.getInstance().currentActivity(), "token", "").toString().equals(""))
                connect();
        }

        @Override
        public void onClosed(okhttp3.WebSocket webSocket, int code, String reason) {
            if (!SPUtil.get(ActivityManager.getInstance().currentActivity(), "token", "").toString().equals(""))
                connect();
//            webSocket.close(1000, null);

        }

        @Override
        public void onFailure(okhttp3.WebSocket webSocket, Throwable t, Response response) {
            connect();
        }
    }

}
