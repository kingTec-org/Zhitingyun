package com.xiyoukeji.zhitingyun.data.remote;


import android.databinding.ObservableField;

import com.xiyoukeji.zhitingyun.data.entity.AccidEntity;
import com.xiyoukeji.zhitingyun.data.entity.AdviceBackEntity;
import com.xiyoukeji.zhitingyun.data.entity.AppointmentEntity;
import com.xiyoukeji.zhitingyun.data.entity.BaseModel;
import com.xiyoukeji.zhitingyun.data.entity.BaseModel0;
import com.xiyoukeji.zhitingyun.data.entity.DeviceEntity;
import com.xiyoukeji.zhitingyun.data.entity.EvaluationEntity;
import com.xiyoukeji.zhitingyun.data.entity.ExpertSchEntity;
import com.xiyoukeji.zhitingyun.data.entity.ListModel;
import com.xiyoukeji.zhitingyun.data.entity.LoginEntity;
import com.xiyoukeji.zhitingyun.data.entity.LunBoEntity;
import com.xiyoukeji.zhitingyun.data.entity.MallEntity;
import com.xiyoukeji.zhitingyun.data.entity.MessageEntity;
import com.xiyoukeji.zhitingyun.data.entity.Model0;
import com.xiyoukeji.zhitingyun.data.entity.Model1;
import com.xiyoukeji.zhitingyun.data.entity.OssMessageEntity;
import com.xiyoukeji.zhitingyun.data.entity.OtherEntity;
import com.xiyoukeji.zhitingyun.data.entity.Professor;
import com.xiyoukeji.zhitingyun.data.entity.Quick;
import com.xiyoukeji.zhitingyun.data.entity.RecordEntity;
import com.xiyoukeji.zhitingyun.data.entity.ReportEntity;
import com.xiyoukeji.zhitingyun.data.entity.SubmitEntity;
import com.xiyoukeji.zhitingyun.data.entity.SueEntity;
import com.xiyoukeji.zhitingyun.data.entity.TestRecordEntity;
import com.xiyoukeji.zhitingyun.data.entity.UploadEntity;
import com.xiyoukeji.zhitingyun.data.entity.UserEntity;
import com.xiyoukeji.zhitingyun.util.L;

import java.io.File;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Url;


public interface ApiServer {

    @POST("user/sign_up")
    @FormUrlEncoded
    Observable<BaseModel<LoginEntity>> register(@Field("phone") String phone, @Field("password") String password,
                                                @Field("nickname") String nickname, @Field("code") String code);


    @POST("/user/professor/getList")
    @FormUrlEncoded
    Observable<BaseModel0<Model0<List<Professor>>>> getProfessorList(@Field("name") String name, @Field("classify") Integer classify);

    @POST("/login/user")
    @FormUrlEncoded
    Observable<BaseModel0<Model0<LoginEntity>>> login(@Field("phone") String phone, @Field("password") String password);

    @POST("/verifyCode/send")
    @FormUrlEncoded
    Observable<BaseModel0> sendCode(@Field("phone") String phone);

    @POST("user/checkcode")
    @FormUrlEncoded
    Observable<BaseModel<Boolean>> checkCode(@Field("phone") String phone, @Field("code") String code);

    @POST("/register/user")
    @FormUrlEncoded
    Observable<BaseModel0<Model0<LoginEntity>>> register(@Field("phone") String phone, @Field("code") String code, @Field("password") String password);

    @POST("/user/logout")
    Observable<BaseModel0> logout();

    @POST("/resetUserPassword")
    @FormUrlEncoded
    Observable<BaseModel0<Model0<LoginEntity>>> updatePwd(@Field("phone") String phone, @Field("code") String code, @Field("password") String password);

    @Multipart
    Observable<BaseModel<OssMessageEntity>> getOssMessage(@PartMap() Map<String, RequestBody> partMap);


    @Multipart
    @POST("user/uploadImg")
    Observable<BaseModel0<Model0<String>>> uploadFile(@Part("path") RequestBody name, @Part MultipartBody.Part file);

    @POST("user/updatepwd")
    @FormUrlEncoded
    Observable<BaseModel<LoginEntity>> changePwd(@Field("new_password") String newPassword, @Field("old_password") String oldPassword);

    @POST("/user/info/updatePassword")
    @FormUrlEncoded
    Observable<BaseModel0<Model0<LoginEntity>>> resetPwd(@Field("newPassword") String newPassword, @Field("oldPassword") String oldPassword);

    @POST("/user/info/updatePhone")
    @FormUrlEncoded
    Observable<BaseModel0<Model0<LoginEntity>>> changePhone(@Field("phone") String phone, @Field("code") String code);

    @POST("/user/professor/getList")
    @FormUrlEncoded
    Observable<BaseModel0<ListModel<List<AppointmentEntity>>>> getAppointment(@Field("name") String name, @Field("classify") String classify);

    @POST("/user/professor/get")
    @FormUrlEncoded
    Observable<BaseModel<AppointmentEntity>> getDoctor(@Field("id") int id);

    @POST("/user/info/get")
    Observable<BaseModel0<Model0<LoginEntity>>> getUserMsg();

    @POST("/user/listenRecord/submit")
    @FormUrlEncoded
    Observable<BaseModel<TestRecordEntity>> getTestRecordData(@Field("id") int id);

    @POST("/user/professor/get")
    @FormUrlEncoded
    Observable<BaseModel0<Model0<ExpertSchEntity>>> getSchedual(@Field("id") Integer id);

    @POST("/user/equipmentParam/submit")
    @FormUrlEncoded
    Observable<BaseModel0<Model0<DeviceEntity>>> sendDevice(@Field("classify") Integer classify, @Field("equipmentType") String equipmentType, @Field("hertz") List<String> hertz, @Field("CHG_DATA") String CHG_DATA, @Field("P_S_DL") Integer P_S_DL, @Field("L_P_VC") String L_P_VC, @Field("L_P_VC_VALUE") Integer L_P_VC_VALUE, @Field("L_P_BASS") String L_P_BASS, @Field("L_P_BASS_VALUE") Integer L_P_BASS_VALUE,
                                                            @Field("L_P_MID") String L_P_MID, @Field("L_P_MID_VALUE") Integer L_P_MID_VALUE, @Field("L_P_TRB") String L_P_TRB, @Field("L_P_TRB_VALUE") Integer L_P_TRB_VALUE, @Field("L_P_NRLVL_VALUE") Integer L_P_NRLVL_VALUE, @Field("L_P_NRLVL") String L_P_NRLVL, @Field("L_P_AFSLVL_VALUE") Integer L_P_AFSLVL_VALUE, @Field("L_P_AFSLVL") String L_P_AFSLVL, @Field("L_P_EQ") String L_P_EQ, @Field("L_SWITCH_P_CH") Boolean L_SWITCH_P_CH, @Field("L_P_CHMPO") List<String> L_P_CHMPO, @Field("L_P_CHSW") List<String> L_P_CHSW,
                                                            @Field("L_P_CHSG") List<String> L_P_CHSG, @Field("L_P_CHNG") List<String> L_P_CHNG, @Field("L_P_CHLG") List<String> L_P_CHLG, @Field("L_SWITCH_P_CHEXP") Boolean L_SWITCH_P_CHEXP, @Field("L_P_CHEXP") List<String> L_P_CHEXP, @Field("L_P_CHETH") List<String> L_P_CHETH, @Field("L_P_CHER") List<String> L_P_CHER, @Field("R_P_VC") String R_P_VC, @Field("R_P_VC_VALUE") Integer R_P_VC_VALUE, @Field("R_P_BASS") String R_P_BASS, @Field("R_P_BASS_VALUE") Integer R_P_BASS_VALUE, @Field("R_P_MID") String R_P_MID,
                                                            @Field("R_P_MID_VALUE") Integer R_P_MID_VALUE, @Field("R_P_TRB") String R_P_TRB, @Field("R_P_TRB_VALUE") Integer R_P_TRB_VALUE, @Field("R_P_NRLVL_VALUE") Integer R_P_NRLVL_VALUE, @Field("R_P_NRLVL") String R_P_NRLVL, @Field("R_P_AFSLVL_VALUE") Integer R_P_AFSLVL_VALUE, @Field("R_P_AFSLVL") String R_P_AFSLVL, @Field("R_P_EQ") String R_P_EQ, @Field("R_SWITCH_P_CH") Boolean R_SWITCH_P_CH, @Field("R_P_CHMPO") List<String> R_P_CHMPO, @Field("R_P_CHSG") List<String> R_P_CHSG, @Field("R_P_CHNG") List<String> R_P_CHNG,
                                                            @Field("R_P_CHLG") List<String> R_P_CHLG, @Field("R_P_CHSW") List<String> R_P_CHSW, @Field("R_SWITCH_P_CHEXP") Boolean R_SWITCH_P_CHEXP, @Field("R_P_CHEXP") List<String> R_P_CHEXP, @Field("R_P_CHETH") List<String> R_P_CHETH, @Field("R_P_CHER") List<String> R_P_CHER);

    @POST("/user/equipmentParam/send")
    @FormUrlEncoded
    Observable<BaseModel0<Model0<SubmitEntity>>> sendExpert(@Field("proId") Integer proId, @Field("equipmentParamIds") String equipmentParamIds);

    @POST("/user/order/submit")
    @FormUrlEncoded
    Observable<BaseModel0<Model0<SubmitEntity>>> submit(@Field("workId") Integer workId, @Field("desc") String desc, @Field("equipmentId") String equipmentId);

    @POST("/user/equipmentParam/submit")
    @FormUrlEncoded
    Observable<BaseModel0<Model0<DeviceEntity>>> submitParamater(@Field("classify") Integer classify, @Field("equipmentType") String equipmentType, @Field("hertz") String hertz, @Field("chg_DATA") String CHG_DATA, @Field("P_S_DL") Integer P_S_DL, @Field("L_P_VC") String L_P_VC, @Field("L_P_VC_VALUE") Integer L_P_VC_VALUE, @Field("L_P_BASS") String L_P_BASS, @Field("L_P_BASS_VALUE") Integer L_P_BASS_VALUE,
                                                                 @Field("L_P_MID") String L_P_MID, @Field("L_P_MID_VALUE") Integer L_P_MID_VALUE, @Field("L_P_TRB") String L_P_TRB, @Field("L_P_TRB_VALUE") Integer L_P_TRB_VALUE, @Field("L_P_NRLVL_VALUE") Integer L_P_NRLVL_VALUE, @Field("L_P_NRLVL") String L_P_NRLVL, @Field("L_P_AFSLVL_VALUE") Integer L_P_AFSLVL_VALUE, @Field("L_P_AFSLVL") String L_P_AFSLVL, @Field("L_P_EQ") String L_P_EQ, @Field("L_SWITCH_P_CH") Boolean L_SWITCH_P_CH, @Field("L_P_CHMPO") String L_P_CHMPO, @Field("L_P_CHSW") String L_P_CHSW,
                                                                 @Field("L_P_CHSG") String L_P_CHSG, @Field("L_P_CHNG") String L_P_CHNG, @Field("L_P_CHLG") String L_P_CHLG, @Field("L_SWITCH_P_CHEXP") Boolean L_SWITCH_P_CHEXP, @Field("L_P_CHEXP") String L_P_CHEXP, @Field("L_P_CHETH") String L_P_CHETH, @Field("L_P_CHER") String L_P_CHER, @Field("R_P_VC") String R_P_VC, @Field("R_P_VC_VALUE") Integer R_P_VC_VALUE, @Field("R_P_BASS") String R_P_BASS, @Field("R_P_BASS_VALUE") Integer R_P_BASS_VALUE, @Field("R_P_MID") String R_P_MID,
                                                                 @Field("R_P_MID_VALUE") Integer R_P_MID_VALUE, @Field("R_P_TRB") String R_P_TRB, @Field("R_P_TRB_VALUE") Integer R_P_TRB_VALUE, @Field("R_P_NRLVL_VALUE") Integer R_P_NRLVL_VALUE, @Field("R_P_NRLVL") String R_P_NRLVL, @Field("R_P_AFSLVL_VALUE") Integer R_P_AFSLVL_VALUE, @Field("R_P_AFSLVL") String R_P_AFSLVL, @Field("R_P_EQ") String R_P_EQ, @Field("R_SWITCH_P_CH") Boolean R_SWITCH_P_CH, @Field("R_P_CHMPO") String R_P_CHMPO, @Field("R_P_CHSG") String R_P_CHSG, @Field("R_P_CHNG") String R_P_CHNG,
                                                                 @Field("R_P_CHLG") String R_P_CHLG, @Field("R_P_CHSW") String R_P_CHSW, @Field("R_SWITCH_P_CHEXP") Boolean R_SWITCH_P_CHEXP, @Field("R_P_CHEXP") String R_P_CHEXP, @Field("R_P_CHETH") String R_P_CHETH, @Field("R_P_CHER") String R_P_CHER);

    @POST("/user/listenRecord/submit")
    @FormUrlEncoded
    Observable<BaseModel0> submitRecord(@Field("left_hertz") String left_hertz, @Field("right_hertz") String right_hertz, @Field("left_data") String left_data, @Field("right_data") String right_data, @Field("equipmentHolder") String equipmentHolder);

    @POST("/user/listenRecord/getList")
    @FormUrlEncoded
    Observable<BaseModel0<Model0<List<TestRecordEntity>>>> getRecordList(@Field("classify") Integer classify);

    @POST("/tools/getPersonId")
    @FormUrlEncoded
    Observable<BaseModel0<Model0<AccidEntity>>> getPersonId(@Field("accid") String accid);

    @POST("/user/quickOrder/add")
    @FormUrlEncoded
    Observable<BaseModel0<Model0<Quick>>> addQuickOrder(@Field("equParamId") String equParamId);


    @POST("/user/news/getList")
    @FormUrlEncoded
    Observable<BaseModel0<ListModel<List<MessageEntity>>>> getMessage(@Field("lastTime") Long lastTime);

    @POST("/user/news/read")
    @FormUrlEncoded
    Observable<BaseModel0<Model0<MessageEntity>>> getMsgDetail(@Field("newsId") Integer newsId, @Field("newsType") Integer newsType);

    @POST("/user/complaint/submit")
    @FormUrlEncoded
    Observable<BaseModel0<Model0<AdviceBackEntity>>> sendAdvice(@Field("professorId") String professorId, @Field("content") String content);

    @POST("/user/feedback/add")
    @FormUrlEncoded
    Observable<BaseModel0<Model0<AdviceBackEntity>>> adviceBack(@Field("content") String content);

    @POST("/user/carousel/getList")
    @FormUrlEncoded
    Observable<BaseModel0<ListModel<List<LunBoEntity>>>> lunBo(@Field("pageNo") String pageNo, @Field("pageSize") String pageSize);

    @POST("/user/info/get")
    @FormUrlEncoded
    Observable<BaseModel0<Model0<UserEntity>>> getUser(@Field( "id" )Integer id);

    @POST("/user/info/updateInfo")
    @FormUrlEncoded
    Observable<BaseModel0<Model0<UserEntity>>> basicInfo(@Field("name") String name, @Field("sex") String sex, @Field("age") String age, @Field("wearTimeEnum") String wearTimeEnum,@Field( "headPic" )String headPic);

    @POST("/user/complaint/submit")
    @FormUrlEncoded
    Observable<BaseModel0<Model0<RecordEntity>>> sueExpert(@Field("professorId") Integer professorId, @Field("content") String content);

//    @POST("/user/order/getList")
//    @FormUrlEncoded
//    Observable<BaseModel0<ListModel<List<RecordEntity>>>> getRecord(@Field("id") Integer id);

    @POST("/user/commonOrder/cancelOrder")
    @FormUrlEncoded
    Observable<BaseModel0> erroCancel(@Field("classify") Integer classify, @Field("orderId") Integer orderId);

    @POST("/user/news/haveNewMsg")
    @FormUrlEncoded
    Observable<BaseModel0<Model1<MessageEntity>>> checkMsg(@Field( "name" )String name);

    @POST("/user/info/get")
    Observable<BaseModel0<Model0<UserEntity>>> getUserInfo(@Field("name") String name);

    @POST("/user/order/getList")
    @FormUrlEncoded
    Observable<BaseModel0<ListModel<List<RecordEntity>>>> getRecord(@Field("id") Integer id);

    @POST("/user/quickOrder/getList")
    @FormUrlEncoded
    Observable<BaseModel0<ListModel<List<RecordEntity>>>> getQuickRecord(@Field("id") Integer id);

    @POST("/user/quickOrder/evaluate")
    @FormUrlEncoded
    Observable<BaseModel0<Model0<EvaluationEntity>>> sendEvaluation(@Field("id") Integer orderId,@Field( "starts")Integer starts,@Field( "tags" )String tags,@Field( "evaluation" )String evaluation);

    @POST("/user/order/evaluate")
    @FormUrlEncoded
    Observable<BaseModel0<Model0<EvaluationEntity>>> sendNormalEvaluation(@Field("orderId") Integer orderId,@Field( "starts")Integer starts,@Field( "tags" )String tags,@Field( "evaluation" )String evaluation);

    @POST("/user/product/getList")
    @FormUrlEncoded
    Observable<BaseModel0<ListModel<List<MallEntity>>>> getMall(@Field("pageNo") Integer pageNo,@Field( "pageSize" )Integer pageSize,@Field( "sort" )boolean sort);

    @POST("/user/order/get")
    @FormUrlEncoded
    Observable<BaseModel0<Model0<RecordEntity>>>getReport(@Field( "orderId" )Integer orderId);

    @POST("/user/quickOrder/get")
    @FormUrlEncoded
    Observable<BaseModel0<Model0<RecordEntity>>>getQuickReport(@Field( "quickOrderId" )Integer quickOrderId);

    @POST("/user/commonOrder/cancelOrder")
    @FormUrlEncoded
    Observable<BaseModel0<Model0<RecordEntity>>>cancelOrder(@Field( "classify" ) Integer classify,@Field( "orderId" )Integer orderId);

    @POST("/user/order/cancelOrder")
    @FormUrlEncoded
    Observable<BaseModel0<Model0<RecordEntity>>>cancelDate(@Field( "orderId" )Integer orderId,@Field( "classify" )Integer classify);

    @POST("/user/news/readAll")
    @FormUrlEncoded
    Observable<BaseModel0>readAll(@Field( "id" )String id);

    @POST("/user/commonOrder/getRecentlyOrder")
    @FormUrlEncoded
    Observable<BaseModel0<Model0<RecordEntity>>>getRecent(@Field( "name" )String name);

    @POST("/user/commonOrder/getOrder")
    @FormUrlEncoded
    Observable<BaseModel0<Model0<RecordEntity>>>orderStatus(@Field( "orderId" )Integer orderId,@Field( "classify" )Integer classify);

    @POST("/systemSet/get")
    @FormUrlEncoded
    Observable<BaseModel0<Model0<OtherEntity>>>getOthers(@Field( "id" )Integer id);

    @POST("/user/news/haveNewMsg")
    @FormUrlEncoded
    Observable<BaseModel0<Model0<MessageEntity>>>readOrnot(@Field( "name" )String name);
}
