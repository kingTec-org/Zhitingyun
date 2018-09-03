package coder.mylibrary.base;

import android.app.Application;

/**
 * Created by dasiy on 2017/8/22.
 */

public class BaseApplication extends Application {
    public static BaseApplication baseApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
    }
}
