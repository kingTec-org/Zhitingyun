package com.example.zhitingyun.mvp;


import com.example.zhitingyun.http.MainHttp;

import java.util.Map;

import coder.mylibrary.base.BaseData;
import rx.Subscriber;

/**
 * Created by dasiy on 2017/12/13.
 */

public class MainData implements BaseData {
    private static MainData mainData = null;

    public static MainData getInstance() {
        if (mainData == null) {
            synchronized (MainData.class) {
                if (mainData == null) {
                    mainData = new MainData();
                }
            }
        }
        return mainData;
    }

    public void login(Map map, Subscriber subscriber) {
        new MainHttp().login(map, subscriber);
    }

    public void verifyCodeSend(Map map, Subscriber subscriber) {
        new MainHttp().verifyCodeSend(map, subscriber);
    }

    public void resetPassword(Map map, Subscriber subscriber) {
        new MainHttp().resetPassword(map, subscriber);
    }

    public void getInfo(Map map, Subscriber subscriber) {
        new MainHttp().getInfo(map, subscriber);
    }

    public void uploadFile(String imagePath, Subscriber subscriber) {
        new MainHttp().uploadFile(imagePath, subscriber);

    }

    public void addSchedule(Map map, Subscriber subscriber) {
        new MainHttp().addSchedule(map, subscriber);
    }

    public void getScheduleList(Map map, Subscriber subscriber) {
        new MainHttp().getScheduleList(map, subscriber);
    }

    public void deleteSchedule(Map map, Subscriber subscriber) {
        new MainHttp().deleteSchedule(map, subscriber);
    }

    public void repeatSchedule(Map map, Subscriber subscriber) {
        new MainHttp().repeatSchedule(map, subscriber);
    }

    public void getOrderList(Map map, Subscriber subscriber) {
        new MainHttp().getOrderList(map, subscriber);
    }

    public void getQuickOrderList(Map map, Subscriber subscriber) {
        new MainHttp().getQuickOrderList(map, subscriber);
    }

    public void getOrder(Map map, Subscriber subscriber) {
        new MainHttp().getOrder(map, subscriber);
    }

    public void getQuickOrder(Map map, Subscriber subscriber) {
        new MainHttp().getQuickOrder(map, subscriber);
    }

    public void refuseOrder(Map map, Subscriber subscriber) {
        new MainHttp().refuseOrder(map, subscriber);
    }

    public void receiveOrder(Map map, Subscriber subscriber) {
        new MainHttp().receiveOrder(map, subscriber);
    }

    public void recordCure(Map map, Subscriber subscriber) {
        new MainHttp().recordCure(map, subscriber);
    }

    public void startCure(Map map, Subscriber subscriber) {
        new MainHttp().startCure(map, subscriber);
    }

    public void getRequestList(Map map, Subscriber subscriber) {
        new MainHttp().getRequestList(map, subscriber);
    }

    public void receiveRequest(Map map, Subscriber subscriber) {
        new MainHttp().receiveRequest(map, subscriber);
    }

    public void logout(Map map, Subscriber subscriber) {
        new MainHttp().logout(map, subscriber);
    }

    public void finishCure(Map map, Subscriber subscriber) {
        new MainHttp().finishCure(map, subscriber);
    }

    public void getParamaterList(Map map, Subscriber subscriber) {
        new MainHttp().getParamaterList(map, subscriber);
    }

    public void submitParamater(Map map, Subscriber subscriber) {
        new MainHttp().submitParamater(map, subscriber);
    }

    public void cureReport(Map map, Subscriber subscriber) {
        new MainHttp().cureReport(map, subscriber);
    }

    public void submitQuickOrder(Map map, Subscriber subscriber) {
        new MainHttp().submitQuickOrder(map, subscriber);
    }

    public void cancelOrder(Map map, Subscriber subscriber) {
        new MainHttp().cancelOrder(map, subscriber);
    }

    public void cancelHaveNotBegunOrder(Map map, Subscriber subscriber) {
        new MainHttp().cancelHaveNotBegunOrder(map, subscriber);
    }

    public void getRecentlyOrder(Map map, Subscriber subscriber) {
        new MainHttp().getRecentlyOrder(map, subscriber);
    }

    public void homePage(Map map, Subscriber subscriber) {
        new MainHttp().homePage(map, subscriber);
    }

    public void getNewsList(Map map, Subscriber subscriber) {
        new MainHttp().getNewsList(map, subscriber);
    }

    public void readAll(Map map, Subscriber subscriber) {
        new MainHttp().readAll(map, subscriber);
    }

    public void haveNewMsg(Map map, Subscriber subscriber) {
        new MainHttp().haveNewMsg(map, subscriber);
    }

    public void sendVerifyCode(Map map, Subscriber subscriber) {
        new MainHttp().sendVerifyCode(map, subscriber);
    }

    public void changePhone(Map map, Subscriber subscriber) {
        new MainHttp().changePhone(map, subscriber);
    }

    public void changePassword(Map map, Subscriber subscriber) {
        new MainHttp().changePassword(map, subscriber);
    }

    public void addFeedback(Map map, Subscriber subscriber) {
        new MainHttp().addFeedback(map, subscriber);
    }

    public void readNews(Map map, Subscriber subscriber) {
        new MainHttp().readNews(map, subscriber);
    }

    public void verifyCode(Map map, Subscriber subscriber) {
        new MainHttp().verifyCode(map, subscriber);
    }
}
