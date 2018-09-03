package com.xiyoukeji.zhitingyun.widget;

import android.os.Environment;

public interface SPConstant {
    public static final String PHOTO_DIR = Environment.getExternalStorageDirectory() + "/Givememoney/min/photo/";
    public static final String IMAGE_DIR = Environment.getExternalStorageDirectory().getPath() + "/Givememoney/min/image/";
    public static final String APK_DIR = Environment.getExternalStorageDirectory() + "/Givememoney/min/apk/";

    public static final String ACCOUNT = "ACCOUNT";
    public static final String PASSWD = "PASSWD";

    public static final String IS_FIRST_OPEN = "IS_FIRST_OPEN";
    public static final String IS_NOTIFY_UPDATE = "IS_NOTIFY_UPDATE";

    public static final String COOKIE = "JSESSIONID";
    public static final String SESSION = "SESSION";
    public static final String DOMAIN = "DOMAIN";
}