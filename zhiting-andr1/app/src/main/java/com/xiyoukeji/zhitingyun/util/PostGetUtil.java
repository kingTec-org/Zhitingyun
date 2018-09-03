package com.xiyoukeji.zhitingyun.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by xiaowen on 2017/12/30.
 */

public class PostGetUtil {

    private static Context sContext;

    /**
     * 使用post方式与服务器通讯
     * @param content
     * @return
     */
    public static String SendPostRequest(String content){
        HttpURLConnection conn=null;
        try {
            final SharedPreferences sharedPreferences =sContext.getSharedPreferences("mySP",MODE_PRIVATE);
            int address=sharedPreferences.getInt("id",1);
            String Strurl="ws://47.96.229.223:443/webSocket/news/1,"+String.valueOf(address);
            URL url = new URL(Strurl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("ser-Agent", "Fiddler");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setConnectTimeout(5 * 1000);
            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(URLEncoder.encode(content.toString(), "UTF-8").getBytes());
            outputStream.flush();
            outputStream.close();
            if(HttpURLConnection.HTTP_OK==conn.getResponseCode()){
                Log.i("PostGetUtil","post请求成功");
                InputStream in=conn.getInputStream();
//                String backcontent=IOUtils.readString(in);
//                backcontent= URLDecoder.decode(backcontent,"UTF-8");
//                Log.i("PostGetUtil",backcontent);
                in.close();
            }else {
                Log.i("PostGetUtil","post请求失败");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
//            conn.disconnect();
        }
        return null;
    }

    /**
     * 使用get方式与服务器通信
     * @param content
     * @return
     */
    public static String SendGetRequest(String content){

        HttpURLConnection conn=null;
        try {

            String Strurl="http://服务器IP:8080/ServletDemo/ServletDemo?"+content;
            URL url = new URL(Strurl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            if(HttpURLConnection.HTTP_OK==conn.getResponseCode()){
                Log.i("PostGetUtil","get请求成功");
                InputStream in=conn.getInputStream();
//                String backcontent=IOUtils.readString(in);
//                backcontent= URLDecoder.decode(backcontent,"UTF-8");
//                Log.i("PostGetUtil",backcontent);
                in.close();
            }
            else {
                Log.i("PostGetUtil","get请求失败");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            conn.disconnect();
        }
        return null;
    }
}