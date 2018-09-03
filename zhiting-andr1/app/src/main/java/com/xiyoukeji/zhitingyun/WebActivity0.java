package com.xiyoukeji.zhitingyun;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.xiyoukeji.zhitingyun.base.BaseActivity;
import com.xiyoukeji.zhitingyun.util.L;
import com.xiyoukeji.zhitingyun.widget.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class WebActivity0 extends BaseActivity {

    @BindView(R.id.pb3)
    ProgressBar mPb;
    @BindView(R.id.webView)
    WebView mWebView;
//    @BindView(R.id.title)
//    TextView mTitle;
//    @BindView(R.id.tool_bar)
//    Toolbar mToolBar;

    public WebActivity0() {
        super(R.layout.activity_websecond);
    }

    public final static String URL = "url";
    public final static String TITLE = "title";
    public final static String DATA = "data";

    public static void runActivity(Context context, String title, String url) {
        Intent intent = new Intent(context, WebActivity0.class);
        intent.putExtra(URL, url);
        intent.putExtra(TITLE, title);
        context.startActivity(intent);
    }

    @OnClick({R.id.back_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_layout:
                finish();
                break;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String url = getIntent().getStringExtra(URL);
        final String title = getIntent().getStringExtra(TITLE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            StatusBarUtil.setStatusBarColor(this, R.color.white); }
//        mTitle.setText(title);
//        mToolBar.setTitle("");
//        setSupportActionBar(mToolBar);
//        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                goBack();
//            }
//        });
        mPb.setMax(100);

//
        //屏幕自适应
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        //对网页某些标签不支持,显示空白页的解决方案
        webSettings.setDomStorageEnabled(true);
        //支持js
        webSettings.setJavaScriptEnabled(true);

        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
//        mWebView.addJavascriptInterface(this, "baotu");
//        webSettings.setSupportZoom(true);
//        webSettings.setBuiltInZoomControls(true);
//        mWebView.setDefaultHandler(new DefaultHandler());
//        mWebView.registerHandler("sendMsg", new BridgeHandler() {
//
//            @Override
//            public void handler(String data, CallBackFunction function) {
//                Log.e("hahaha----------", "handler = sendMsg, data from web = " + data);
//                if (!"null".equals(data)) {
//                    ToastUtil.toastShortShow(data);
//                }
//                finish();
//                startActivity(MainActivity.class, new SelectedTab(1,0));
//            }
//        });
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (mPb != null) {
                    if (newProgress >= 100) {
                        mPb.setVisibility(View.GONE);
                    } else {
                        mPb.setVisibility(View.VISIBLE);
                        mPb.setProgress(newProgress);
                    }
                }
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                L.e("onJsAlert:" + message);
//                ToastUtil.toastShortShow("警告:" + message + ",返回上一页");
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        goBack();
//                    }
//                }, 1000L);
                result.confirm();
                return super.onJsConfirm(view, url, message, result); //alert 弹出
//                return true;
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
//                mTitle.setTitle(title);
            }
        });
//
        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                //域名拦截
                L.e(url);
                String[] str = url.split("/");
                L.e(str[2]);
                String place = str[2];
                return super.shouldInterceptRequest(view, url);
//                if ("101.37.34.55".equals(place) || "101.37.34.55:8082".equals(place) ||
//                        "192.168.191.1:8082".equals(place)) {
//                    return super.shouldInterceptRequest(view, url);
//                } else {
//                    return new WebResourceResponse(null, null, null);
//                }
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                L.e(error.toString());
                super.onReceivedError(view, request, error);
            }

//            @Override
//            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
//                L.e(error.toString());
//                handler.proceed();
////                super.onReceivedSslError(view, handler, error);
//            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });


        mWebView.loadUrl(url);

//        mWebView.send("hello");
//        mWebView.callHandler("functionInJs", new Gson().toJson(mEntity), new CallBackFunction() {
//            @Override
//            public void onCallBack(String data) {
//                Log.e("hahahaha", data);
//                ToastUtil.toastShortShow("data from web = " + data);
//            }
//        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (goBack()) return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private boolean goBack() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();//返回上一浏览页面
            return true;
        } else {
            finish();//关闭Activity
//            L.e("" + ActivityManager.activityList.size());
//            if (ActivityManager.activityList.size() == 1) {
//                startActivity(MainActivity.class);
//            }
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        mWebView.clearCache(true);
        mWebView.clearHistory();
        mContext.deleteDatabase("webview.db");
        mContext.deleteDatabase("webviewCache.db");
        super.onDestroy();
    }
}
