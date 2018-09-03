
package com.xiyoukeji.zhitingyun.util;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     author : huangyacong
 *     e-mail : 17826817008@163.com
 *     time   : 2017/3/22
 *     desc   : xx页面
 *     version: 1.0
 * </pre>
 */
public class ActivityManager {

    public static List<Activity> activityList = new ArrayList<>();

    /**
     * 添加Activity到列表中
     *
     * @param activity
     */
    public static void addActivity(Activity activity) {
        if (!activityList.contains(activity)) {
            activityList.add(activity);
        }
    }

    /**
     * 从列表移除Activity
     *
     * @param activity
     */
    public static void removeActivity(Activity activity) {
        if (activityList.contains(activity)) {
            activityList.remove(activity);
        }
    }

    public static Activity getCurrentActivity() {
        return activityList.get(activityList.size() - 1);
    }

    /**
     * 退出应用程序
     */
    public static void exitApp() {
        for (Activity activity : activityList) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        activityList.clear();
//        android.os.Process.killProcess(android.os.Process.myPid());
    }

//    public static void finishAllActivityBesidesCurrent() {
//        for (int i = 0, size = activityList.size(); i < size; i++) {
//            if (null != activityList.get(i) && activityList.get(i) != getCurrentActivity()) {
//                activityList.get(i).finish();
//            }
//        }
//
//
////        activityStack.clear();
//    }

    public static void finishExcept(Class<?> clazz) {
        Activity activity0 = null;
        for (Activity activity : activityList) {

            if (!activity.getClass().equals(clazz)) {
//                activityList.remove(activity);

                if (!activity.isFinishing()) {
                    activity.finish();
//                    activityList.remove( activity );
                }
            }
            else
                activity0=activity;
        }
        activityList.clear();
        if (activity0!=null)
            activityList.add( activity0 );
//        for (Activity activity : activityList){
//            Log.d("fff",activity.toString());
//        }
    }
}
