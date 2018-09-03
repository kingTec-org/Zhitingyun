package coder.mylibrary.base;

import android.content.Context;
import android.util.Log;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import coder.mylibrary.manager.ActivityManager;
import coder.mylibrary.util.SPUtil;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dasiy on 2017/8/21.
 */

public class RetrofitAsyncTask {
    private static RetrofitAsyncTask instance;
    private Retrofit aggregationRetrofit;
    private Retrofit googleRetrofit;

    public static synchronized RetrofitAsyncTask getInstance() {
        if (instance == null) {
            instance = new RetrofitAsyncTask();
        }
        return instance;
    }

    public Retrofit getRetrofit() {
        if (aggregationRetrofit == null) {
            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl(ProjectConfig.BASE_URL);
            builder.client(getTokenOkHttpClient());
            builder.addConverterFactory(GsonConverterFactory.create());
            builder.addCallAdapterFactory(RxJavaCallAdapterFactory.create());
            aggregationRetrofit = builder.build();
        }
        return aggregationRetrofit;
    }

    public Retrofit getGoogleRetrofit(String url) {
//        if (googleRetrofit == null) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(url);
        builder.client(getOkHttpClient());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        googleRetrofit = builder.build();
//        }
        return googleRetrofit;
    }
    //.addHeader("pb_token", SPUtil.get(BaseApplication.baseApplication.getApplicationContext(), "token", "").toString())

    public static OkHttpClient getTokenOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(8, TimeUnit.SECONDS);

        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request()
                        .newBuilder()
                        .addHeader("Authorization", SPUtil.get(ActivityManager.getInstance().currentActivity(), "token", "").toString())
                        .build();

                return chain.proceed(request);
            }
        });
        builder.addInterceptor(new AddCookiesInterceptor(BaseApplication.baseApplication.getApplicationContext(), ""));
        builder.addInterceptor(new ReceivedCookiesInterceptor(BaseApplication.baseApplication.getApplicationContext()));
        builder.addInterceptor(loggingInterceptor);


//        ClearableCookieJar cookieJar =
//                new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(BaseApplication.baseApplication.getApplicationContext()));
//        builder.cookieJar(cookieJar);
        OkHttpClient client = builder.build();
        return client;
    }

    private static String checkValue(String value) {
        StringBuffer stringBuffer = new StringBuffer();

        if (value == null) throw new NullPointerException("value == null");
        for (int i = 0, length = value.length(); i < length; i++) {
            char c = value.charAt(i);
            if (c <= '\u001f' || c >= '\u007f') {
                stringBuffer.append(String.format("\\u%04x", (int) c));
            } else {
                stringBuffer.append(c);
            }

        }
        return stringBuffer.toString();
    }

    public static OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(8, TimeUnit.SECONDS);
        builder.addInterceptor(new AddCookiesInterceptor(BaseApplication.baseApplication.getApplicationContext(), ""));
        builder.addInterceptor(new ReceivedCookiesInterceptor(BaseApplication.baseApplication.getApplicationContext()));

        builder.addInterceptor(loggingInterceptor);
        OkHttpClient client = builder.build();
        return client;
    }

    private static HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(
            new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Log.d("test", "收到响应: " + message);
                }
            }).setLevel(HttpLoggingInterceptor.Level.BODY);
}
