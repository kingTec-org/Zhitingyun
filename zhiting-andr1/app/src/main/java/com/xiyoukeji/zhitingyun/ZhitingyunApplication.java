package com.xiyoukeji.zhitingyun;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.Toast;

import com.lzy.imagepicker.ImagePicker;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.SDKOptions;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.uinfo.UserInfoProvider;
import com.netease.nimlib.sdk.uinfo.model.UserInfo;
import com.shuyu.gsyvideoplayer.utils.GSYVideoType;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.xiyoukeji.zhitingyun.task.TaskQueue;
import com.xiyoukeji.zhitingyun.util.GlideImageLoader;
import com.xiyoukeji.zhitingyun.util.Utils;
import com.xiyoukeji.zhitingyun.view.main.MainActivity;
import com.xiyoukeji.zhitingyun.widget.StaticConfigCallback;
import com.xiyoukeji.zhitingyun.widget.StaticConnectCallback;
import com.xiyoukeji.zhitingyun.widget.StaticDetectCallback;
import com.xiyoukeji.zhitingyun.yunxin.SPUtil;
import com.xiyoukeji.zhitingyun.yunxin.ScreenUtil;

import cn.earhear.hadevicelib.DeviceState;
import cn.earhear.hadevicelib.HADeviceManager;
import cn.earhear.hadevicelib.constant.ErrorType;


public class ZhitingyunApplication extends Application {
//    public static ZhitingyunApplication baseApplication;

    public static ZhitingyunApplication sInstance;
    public static TaskQueue sTaskQueue;
    public static Context context;

    public static HADeviceManager deviceManager;

    public static DeviceState curState;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
//        baseApplication = this;
        deviceManager = HADeviceManager.getInstance();
        int result = deviceManager.init(this, StaticDetectCallback.getInStanceBlock(), StaticConnectCallback.getInStanceBlock(), StaticConfigCallback.getInStanceBlock());
        if (result != ErrorType.SUCCESS) {
            Toast.makeText(this, getErrorString(result), Toast.LENGTH_LONG).show();
            return;
        }
        Utils.init(this);
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());
        GSYVideoType.setShowType(GSYVideoType.SCREEN_TYPE_FULL);
        sTaskQueue = new TaskQueue(1);
        sTaskQueue.start();
        Utils.init(this);
        Beta.canShowUpgradeActs.add(MainActivity.class);
        NIMClient.init(this, new LoginInfo(SPUtil.get(sInstance, "accid", "").toString(), SPUtil.get(sInstance, "imToken", "").toString()), options());

//        LocationUtil.getInstance(this).getLocation();

//        Stetho.initializeWithDefaults(this);
//        LeakCanary.install(this);
    }

    private String getErrorString(int error) {
        switch (error) {
            case ErrorType.SUCCESS:
                return " Success";

            case ErrorType.E_UNINITIALIZED:
                return " X API uninitialized";
            case ErrorType.E_INVALID_INPUT_PARAMS:
                return " X Invalid input parameters";
            case ErrorType.E_NO_MORE_DEVICE:
                return " X No more can connect";
            case ErrorType.E_REPEAT_INITIALIZED:
                return " X Repeat initialized";

            case ErrorType.E_BLUETOOTH_UNSUPPORTED:
                return " X BT unsupported";
            case ErrorType.E_BLUETOOTH_DISABLED:
                return " X BT disable";
            case ErrorType.E_LOCATION_DISABLED:
                return " X Location disabled";

            case ErrorType.E_BLE_START_SCAN_FAILED:
                return " X BLE scan failed";
            case ErrorType.E_BLE_NO_DEVICE_SCANNED:
                return " X BLE not scanned";
            case ErrorType.E_BLE_NO_DEVICE_CONNECTED:
                return " X BLE not connected";
            case ErrorType.E_BLE_SERVICE_UNSUPPORTED:
                return " X BLE service unrecognized";
            case ErrorType.E_BLE_COMMUNICATION:
                return " X BLE communication";

            case ErrorType.E_CONFIG_IN_PROGRESS:
                return " X Config in progress";
            case ErrorType.E_PARAMETER_NULL:
                return " X No parameter";
            default:
                break;
        }
        return " Unknown";
    }

    private SDKOptions options() {
        SDKOptions options = new SDKOptions();

        // 如果将新消息通知提醒托管给 SDK 完成，需要添加以下配置。否则无需设置。
//        StatusBarNotificationConfig config = new StatusBarNotificationConfig();
//        config.notificationEntrance = MainActivity.class; // 点击通知栏跳转到该Activity
//        config.notificationSmallIconId = R.mipmap.ic_launcher;
//        // 呼吸灯配置
//        config.ledARGB = Color.GREEN;
//        config.ledOnMs = 1000;
//        config.ledOffMs = 1500;
//        // 通知铃声的uri字符串
//        config.notificationSound = "android.resource://com.netease.nim.demo/raw/msg";
//        options.statusBarNotificationConfig = config;

        // 配置保存图片，文件，log 等数据的目录
        // 如果 options 中没有设置这个值，SDK 会使用采用默认路径作为 SDK 的数据目录。
        // 该目录目前包含 log, file, image, audio, video, thumb 这6个目录。
//        String sdkPath = getAppCacheDir(context) + "/nim"; // 可以不设置，那么将采用默认路径
        // 如果第三方 APP 需要缓存清理功能， 清理这个目录下面个子目录的内容即可。
//        options.sdkStorageRootPath = sdkPath;

        // 配置是否需要预下载附件缩略图，默认为 true
        options.preloadAttach = true;

        // 配置附件缩略图的尺寸大小。表示向服务器请求缩略图文件的大小
        // 该值一般应根据屏幕尺寸来确定， 默认值为 Screen.width / 2
        options.thumbnailSize = (int) (165.0 / 320.0 * ScreenUtil.getScreenWidth(sInstance));

        // 用户资料提供者, 目前主要用于提供用户资料，用于新消息通知栏中显示消息来源的头像和昵称
        options.userInfoProvider = new UserInfoProvider() {

            @Override
            public UserInfo getUserInfo(String account) {
                return null;
            }

            @Override
            public String getDisplayNameForMessageNotifier(String account, String sessionId, SessionTypeEnum sessionType) {
                return null;
            }

            @Override
            public Bitmap getAvatarForMessageNotifier(SessionTypeEnum sessionType, String sessionId) {
                return null;
            }
        };


        return options;
    }


    public static Context getContext() {
        return context;
    }


}