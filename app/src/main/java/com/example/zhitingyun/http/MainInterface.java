package com.example.zhitingyun.http;


import com.example.zhitingyun.base.BaseModel;
import com.example.zhitingyun.model.Confirm;
import com.example.zhitingyun.model.EquipmentParamater;
import com.example.zhitingyun.model.HomePage;
import com.example.zhitingyun.model.News;
import com.example.zhitingyun.model.NewsDetail;
import com.example.zhitingyun.model.Order;
import com.example.zhitingyun.model.OrderDetail;
import com.example.zhitingyun.model.Quick;
import com.example.zhitingyun.model.SchedualItem;
import com.example.zhitingyun.model.ProfessorInfo;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 * Created by dasiy on 2017/8/21.
 */

public interface MainInterface {
    @FormUrlEncoded
    @POST("login/professor")
    Observable<BaseModel<ProfessorInfo>> login(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("verifyCode/send")
    Observable<BaseModel> verifyCodeSend(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("resetPassword")
    Observable<BaseModel> resetPassword(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("professor/info/get")
    Observable<BaseModel<ProfessorInfo>> getInfo(@FieldMap Map<String, Object> map);

    @Multipart
    @POST("professor/uploadImg")
    Observable<BaseModel<String>> uploadFile(@Part("path") RequestBody name, @Part MultipartBody.Part file);

    @FormUrlEncoded
    @POST("professor/workSchedule/add")
    Observable<BaseModel> addSchedule(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("professor/workSchedule/getList")
    Observable<BaseModel<List<SchedualItem>>> getScheduleList(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("professor/workSchedule/delete")
    Observable<BaseModel> deleteSchedule(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("professor/workSchedule/repetition")
    Observable<BaseModel> repeatSchedule(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("professor/order/getOrderList")
    Observable<BaseModel<List<Confirm>>> getOrderList(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("professor/quickOrder/getList")
    Observable<BaseModel<List<Confirm>>> getQuickOrderList(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("professor/quickOrder/getRequestList")
    Observable<BaseModel<List<Quick>>> getRequestList(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("professor/quickOrder/receive")
    Observable<BaseModel<OrderDetail>> receiveRequest(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("professor/order/get")
    Observable<BaseModel<OrderDetail>> getOrder(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("professor/quickOrder/get")
    Observable<BaseModel<OrderDetail>> getQuickOrder(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("professor/order/refuse")
    Observable<BaseModel> refuseOrder(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("professor/order/receiveOrder")
    Observable<BaseModel<OrderDetail>> receiveOrder(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("professor/order/recordCure")
    Observable<BaseModel<OrderDetail>> recordCure(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("professor/order/startCure")
    Observable<BaseModel<OrderDetail>> startCure(@FieldMap Map<String, Object> map);


    @FormUrlEncoded
    @POST("professor/logout")
    Observable<BaseModel> logout(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("professor/commonOrder/finishCure")
    Observable<BaseModel> finishCure(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("professor/equipmentParam/get")
    Observable<BaseModel<List<EquipmentParamater>>> getParamaterList(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("professor/equipmentParam/submit")
    Observable<BaseModel<EquipmentParamater>> submitParamater(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("professor/quickOrder/submit")
    Observable<BaseModel> submitQuickOrder(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("professor/order/cureReport")
    Observable<BaseModel> cureReport(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("professor/commonOrder/cancelOrder")
    Observable<BaseModel> cancelOrder(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("professor/commonOrder/cancelHaveNotBegunOrder")
    Observable<BaseModel> cancelHaveNotBegunOrder(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("professor/commonOrder/getRecentlyOrder")
    Observable<BaseModel<OrderDetail>> getRecentlyOrder(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("professor/homePage")
    Observable<BaseModel<HomePage>> homePage(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("professor/appNews/getList")
    Observable<BaseModel<List<News>>> getNewsList(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("professor/appNews/readAll")
    Observable<BaseModel> readAll(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("professor/appNews/haveNewMsg")
    Observable<BaseModel<Integer>> haveNewMsg(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("verifyCode/send")
    Observable<BaseModel> sendVerifyCode(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("professor/info/changePhone")
    Observable<BaseModel> changePhone(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("professor/info/changePassword")
    Observable<BaseModel> changePassword(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("professor/feedback/add")
    Observable<BaseModel> addFeedback(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("professor/appNews/read")
    Observable<BaseModel<NewsDetail>> readNews(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("verifyCode/verify")
    Observable<BaseModel> verifyCode(@FieldMap Map<String, Object> map);

}
