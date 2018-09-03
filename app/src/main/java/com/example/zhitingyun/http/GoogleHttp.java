package com.example.zhitingyun.http;



import java.util.Map;

import coder.mylibrary.base.ProjectConfig;
import coder.mylibrary.base.RetrofitAsyncTask;
import rx.Subscriber;

/**
 * Created by dasiy on 2017/8/21.
 */

public class GoogleHttp {
    private GoogleInterface googleInterface;

    public GoogleHttp() {
        googleInterface = RetrofitAsyncTask.getInstance().getGoogleRetrofit(ProjectConfig.GEO_URL).create(GoogleInterface.class);

    }

    public void getGeo(Map map, Subscriber subscriber) {
//        map1.put("origins", "40.6655101%2C-73.89188969999998");
//        map.put("destinations", "30.3340162%2C120.1025627");
//        map.put("destinations", "place_id:ChIJnZP4HtxRqEcRw0epcRGQ-_k");
        map.put("key", "AIzaSyBqt1aCnZY_QScAETqak0-QQyFcUTV-GcU");

//        RxUtils.toSubscribe(googleInterface.getGeo(map)).subscribe(subscriber);
    }


}
