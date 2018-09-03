package com.example.zhitingyun.http;


import com.example.zhitingyun.base.BaseModel;
import com.example.zhitingyun.model.Confirm;
import com.example.zhitingyun.model.EquipmentParamater;
import com.example.zhitingyun.model.HomePage;
import com.example.zhitingyun.model.News;
import com.example.zhitingyun.model.NewsDetail;
import com.example.zhitingyun.model.OrderDetail;
import com.example.zhitingyun.model.ProfessorInfo;
import com.example.zhitingyun.model.Quick;
import com.example.zhitingyun.model.SchedualItem;

import java.io.File;
import java.util.List;
import java.util.Map;

import coder.mylibrary.base.RetrofitAsyncTask;
import coder.mylibrary.util.RxUtils;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Subscriber;

/**
 * Created by dasiy on 2017/8/21.
 */

public class MainHttp {
    private MainInterface mainInterface;

    public MainHttp() {
        mainInterface = RetrofitAsyncTask.getInstance().getRetrofit().create(MainInterface.class);
    }

    public void login(Map map, Subscriber subscriber) {

        RxUtils.toSubscribe(mainInterface.login(map)).compose(new ErrorCheckerTransformer<BaseModel<ProfessorInfo>, ProfessorInfo>()).subscribe(subscriber);
    }

    public void verifyCodeSend(Map map, Subscriber subscriber) {

        RxUtils.toSubscribe(mainInterface.verifyCodeSend(map)).compose(new ErrorCheckerTransformer()).subscribe(subscriber);
    }

    public void resetPassword(Map map, Subscriber subscriber) {

        RxUtils.toSubscribe(mainInterface.resetPassword(map)).compose(new ErrorCheckerTransformer()).subscribe(subscriber);
    }

    public void getInfo(Map map, Subscriber subscriber) {

        RxUtils.toSubscribe(mainInterface.getInfo(map)).compose(new ErrorCheckerTransformer<BaseModel<ProfessorInfo>, ProfessorInfo>()).subscribe(subscriber);
    }

    public void uploadFile(String imagePath, Subscriber subscriber) {
        File file = new File(imagePath);
        RequestBody photoRequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part photo = MultipartBody.Part.createFormData("img", file.getName(), photoRequestBody);//pic为key
        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), imagePath);
        RxUtils.toSubscribe(mainInterface.uploadFile(name, photo)).compose(new ErrorCheckerTransformer<BaseModel<String>, String>()).subscribe(subscriber);
    }

    public void addSchedule(Map map, Subscriber subscriber) {

        RxUtils.toSubscribe(mainInterface.addSchedule(map)).compose(new ErrorCheckerTransformer()).subscribe(subscriber);
    }

    public void getScheduleList(Map map, Subscriber subscriber) {

        RxUtils.toSubscribe(mainInterface.getScheduleList(map)).compose(new ErrorCheckerTransformer<BaseModel<List<SchedualItem>>, List<SchedualItem>>()).subscribe(subscriber);
    }

    public void deleteSchedule(Map map, Subscriber subscriber) {

        RxUtils.toSubscribe(mainInterface.deleteSchedule(map)).compose(new ErrorCheckerTransformer<BaseModel<List<SchedualItem>>, List<SchedualItem>>()).subscribe(subscriber);
    }

    public void repeatSchedule(Map map, Subscriber subscriber) {

        RxUtils.toSubscribe(mainInterface.repeatSchedule(map)).compose(new ErrorCheckerTransformer()).subscribe(subscriber);
    }

    public void getOrderList(Map map, Subscriber subscriber) {

        RxUtils.toSubscribe(mainInterface.getOrderList(map)).compose(new ErrorCheckerTransformer<BaseModel<List<Confirm>>, List<Confirm>>()).subscribe(subscriber);
    }

    public void getQuickOrderList(Map map, Subscriber subscriber) {

        RxUtils.toSubscribe(mainInterface.getQuickOrderList(map)).compose(new ErrorCheckerTransformer<BaseModel<List<Confirm>>, List<Confirm>>()).subscribe(subscriber);
    }

    public void getRequestList(Map map, Subscriber subscriber) {

        RxUtils.toSubscribe(mainInterface.getRequestList(map)).compose(new ErrorCheckerTransformer<BaseModel<List<Quick>>, List<Quick>>()).subscribe(subscriber);
    }

    public void receiveRequest(Map map, Subscriber subscriber) {

        RxUtils.toSubscribe(mainInterface.receiveRequest(map)).compose(new ErrorCheckerTransformer<BaseModel<OrderDetail>, OrderDetail>()).subscribe(subscriber);
    }

    public void getOrder(Map map, Subscriber subscriber) {

        RxUtils.toSubscribe(mainInterface.getOrder(map)).compose(new ErrorCheckerTransformer<BaseModel<OrderDetail>, OrderDetail>()).subscribe(subscriber);
    }

    public void getQuickOrder(Map map, Subscriber subscriber) {

        RxUtils.toSubscribe(mainInterface.getQuickOrder(map)).compose(new ErrorCheckerTransformer<BaseModel<OrderDetail>, OrderDetail>()).subscribe(subscriber);
    }

    public void refuseOrder(Map map, Subscriber subscriber) {

        RxUtils.toSubscribe(mainInterface.refuseOrder(map)).compose(new ErrorCheckerTransformer()).subscribe(subscriber);
    }

    public void receiveOrder(Map map, Subscriber subscriber) {

        RxUtils.toSubscribe(mainInterface.receiveOrder(map)).compose(new ErrorCheckerTransformer<BaseModel<OrderDetail>, OrderDetail>()).subscribe(subscriber);
    }

    public void recordCure(Map map, Subscriber subscriber) {

        RxUtils.toSubscribe(mainInterface.recordCure(map)).compose(new ErrorCheckerTransformer()).subscribe(subscriber);
    }

    public void startCure(Map map, Subscriber subscriber) {

        RxUtils.toSubscribe(mainInterface.startCure(map)).compose(new ErrorCheckerTransformer()).subscribe(subscriber);
    }

    public void logout(Map map, Subscriber subscriber) {

        RxUtils.toSubscribe(mainInterface.logout(map)).compose(new ErrorCheckerTransformer()).subscribe(subscriber);
    }

    public void finishCure(Map map, Subscriber subscriber) {

        RxUtils.toSubscribe(mainInterface.finishCure(map)).compose(new ErrorCheckerTransformer()).subscribe(subscriber);
    }

    public void getParamaterList(Map map, Subscriber subscriber) {

        RxUtils.toSubscribe(mainInterface.getParamaterList(map)).compose(new ErrorCheckerTransformer<BaseModel<List<EquipmentParamater>>, List<EquipmentParamater>>()).subscribe(subscriber);
    }

    public void submitParamater(Map map, Subscriber subscriber) {

        RxUtils.toSubscribe(mainInterface.submitParamater(map)).compose(new ErrorCheckerTransformer<BaseModel<EquipmentParamater>, EquipmentParamater>()).subscribe(subscriber);
    }

    public void cureReport(Map map, Subscriber subscriber) {

        RxUtils.toSubscribe(mainInterface.cureReport(map)).compose(new ErrorCheckerTransformer()).subscribe(subscriber);
    }

    public void submitQuickOrder(Map map, Subscriber subscriber) {

        RxUtils.toSubscribe(mainInterface.submitQuickOrder(map)).compose(new ErrorCheckerTransformer()).subscribe(subscriber);
    }

    public void cancelOrder(Map map, Subscriber subscriber) {

        RxUtils.toSubscribe(mainInterface.cancelOrder(map)).compose(new ErrorCheckerTransformer()).subscribe(subscriber);
    }

    public void cancelHaveNotBegunOrder(Map map, Subscriber subscriber) {

        RxUtils.toSubscribe(mainInterface.cancelHaveNotBegunOrder(map)).compose(new ErrorCheckerTransformer()).subscribe(subscriber);
    }

    public void getRecentlyOrder(Map map, Subscriber subscriber) {

        RxUtils.toSubscribe(mainInterface.getRecentlyOrder(map)).compose(new ErrorCheckerTransformer<BaseModel<OrderDetail>, OrderDetail>()).subscribe(subscriber);
    }

    public void homePage(Map map, Subscriber subscriber) {

        RxUtils.toSubscribe(mainInterface.homePage(map)).compose(new ErrorCheckerTransformer<BaseModel<HomePage>, HomePage>()).subscribe(subscriber);
    }

    public void getNewsList(Map map, Subscriber subscriber) {

        RxUtils.toSubscribe(mainInterface.getNewsList(map)).compose(new ErrorCheckerTransformer<BaseModel<List<News>>, List<News>>()).subscribe(subscriber);
    }

    public void readAll(Map map, Subscriber subscriber) {

        RxUtils.toSubscribe(mainInterface.readAll(map)).compose(new ErrorCheckerTransformer()).subscribe(subscriber);
    }

    public void haveNewMsg(Map map, Subscriber subscriber) {

        RxUtils.toSubscribe(mainInterface.haveNewMsg(map)).compose(new ErrorCheckerTransformer<BaseModel<Integer>, Integer>()).subscribe(subscriber);
    }

    public void sendVerifyCode(Map map, Subscriber subscriber) {

        RxUtils.toSubscribe(mainInterface.sendVerifyCode(map)).compose(new ErrorCheckerTransformer()).subscribe(subscriber);
    }

    public void changePhone(Map map, Subscriber subscriber) {

        RxUtils.toSubscribe(mainInterface.changePhone(map)).compose(new ErrorCheckerTransformer()).subscribe(subscriber);
    }

    public void changePassword(Map map, Subscriber subscriber) {

        RxUtils.toSubscribe(mainInterface.changePassword(map)).compose(new ErrorCheckerTransformer()).subscribe(subscriber);
    }

    public void addFeedback(Map map, Subscriber subscriber) {

        RxUtils.toSubscribe(mainInterface.addFeedback(map)).compose(new ErrorCheckerTransformer()).subscribe(subscriber);
    }

    public void readNews(Map map, Subscriber subscriber) {

        RxUtils.toSubscribe(mainInterface.readNews(map)).compose(new ErrorCheckerTransformer<BaseModel<NewsDetail>, NewsDetail>()).subscribe(subscriber);
    }

    public void verifyCode(Map map, Subscriber subscriber) {

        RxUtils.toSubscribe(mainInterface.verifyCode(map)).compose(new ErrorCheckerTransformer()).subscribe(subscriber);
    }

}
