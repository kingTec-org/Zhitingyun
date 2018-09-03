package com.xiyoukeji.zhitingyun.data;

import com.xiyoukeji.zhitingyun.data.entity.AccidEntity;
import com.xiyoukeji.zhitingyun.data.entity.AdviceBackEntity;
import com.xiyoukeji.zhitingyun.data.entity.AppointmentEntity;
import com.xiyoukeji.zhitingyun.data.entity.BaseModel;
import com.xiyoukeji.zhitingyun.data.entity.BaseModel0;
import com.xiyoukeji.zhitingyun.data.entity.DeviceEntity;
import com.xiyoukeji.zhitingyun.data.entity.EvaluationEntity;
import com.xiyoukeji.zhitingyun.data.entity.ExpertSchEntity;
import com.xiyoukeji.zhitingyun.data.entity.ListModel;
import com.xiyoukeji.zhitingyun.data.entity.ListModel0;
import com.xiyoukeji.zhitingyun.data.entity.LoginEntity;
import com.xiyoukeji.zhitingyun.data.entity.LunBoEntity;
import com.xiyoukeji.zhitingyun.data.entity.MallEntity;
import com.xiyoukeji.zhitingyun.data.entity.MessageEntity;
import com.xiyoukeji.zhitingyun.data.entity.Model0;
import com.xiyoukeji.zhitingyun.data.entity.Model1;
import com.xiyoukeji.zhitingyun.data.entity.OtherEntity;
import com.xiyoukeji.zhitingyun.data.entity.Professor;
import com.xiyoukeji.zhitingyun.data.entity.Quick;
import com.xiyoukeji.zhitingyun.data.entity.RecordEntity;
import com.xiyoukeji.zhitingyun.data.entity.ReportEntity;
import com.xiyoukeji.zhitingyun.data.entity.SubmitEntity;
import com.xiyoukeji.zhitingyun.data.entity.SueEntity;
import com.xiyoukeji.zhitingyun.data.entity.TestRecordEntity;
import com.xiyoukeji.zhitingyun.data.entity.UserEntity;
import com.xiyoukeji.zhitingyun.data.remote.BaseObserver;

import java.io.File;
import java.util.List;

import io.reactivex.disposables.Disposable;


public interface DataSource {

    void getProfessorList(String name, Integer classify, BaseObserver<Model0<List<Professor>>> observer);


    void login(String phone, String password, BaseObserver<Model0<LoginEntity>> observer);

    void sendCode(String phone, BaseObserver<BaseModel0> observer);

    void checkCode(String phone, String code, BaseObserver<Boolean> observer);

    void register(String phone, String code, String password, BaseObserver<Model0<LoginEntity>> observer);

    void logout(BaseObserver<BaseModel0> observer);

    void updatePwd(String phone, String code, String password, BaseObserver<Model0<LoginEntity>> observer);

    void getUserMsg(CallBack<Model0<LoginEntity>> callBack);

    void readAll(String id,BaseObserver<BaseModel0> observer);


    void uploadAvatar(String path, BaseObserver<Model0<String>> observer);

    void changePwd(String newPwd, String oldPwd, BaseObserver<LoginEntity> observer);

    void resetPwd(String newPassword, String oldPassword, BaseObserver<Model0<LoginEntity>> observer);

    void changePhone(String phone, String code, BaseObserver<Model0<LoginEntity>> observer);

    void getTestRecordData(int id, BaseObserver<TestRecordEntity> observer);

    void sendDevice(Integer classify, String equipmentType, List<String> hertz, String chg_DATA, Integer P_S_DL, List<Double> L_P_VC,
                    Integer L_P_VC_VALUE, List<Double> L_P_BASS, Integer L_P_BASS_VALUE, List<Double> L_P_MID, Integer L_P_MID_VALUE, List<Double> L_P_TRB,
                    Integer L_P_TRB_VALUE, Integer L_P_NRLVL_VALUE, List<Double> L_P_NRLVL, Integer L_P_AFSLVL_VALUE, List<Double> L_P_AFSLVL,
                    String L_P_EQ, Boolean L_SWITCH_P_CH, List<String> L_P_CHMPO, List<String> L_P_CHSW, List<String> L_P_CHSG, List<String> L_P_CHNG, List<String> L_P_CHLG,
                    Boolean L_SWITCH_P_CHEXP, List<String> L_P_CHEXP, List<String> L_P_CHETH, List<String> L_P_CHER, List<Double> R_P_VC,
                    Integer R_P_VC_VALUE, List<Double> R_P_BASS, Integer R_P_BASS_VALUE, List<Double> R_P_MID, Integer R_P_MID_VALUE, List<Double> R_P_TRB,
                    Integer R_P_TRB_VALUE, Integer R_P_NRLVL_VALUE, List<Double> R_P_NRLVL, Integer R_P_AFSLVL_VALUE, List<Double> R_P_AFSLVL, String R_P_EQ,
                    Boolean R_SWITCH_P_CH, List<String> R_P_CHMPO, List<String> R_P_CHSG, List<String> R_P_CHNG, List<String> R_P_CHLG, List<String> R_P_CHSW, Boolean R_SWITCH_P_CHEXP,
                    List<String> R_P_CHEXP, List<String> R_P_CHETH, List<String> R_P_CHER, BaseObserver<Model0<DeviceEntity>> observer);

    void getAppointment(String name, String classify, BaseObserver<ListModel<List<AppointmentEntity>>> observer);

    void sendExpert(Integer proId, String equipmentParamIds, BaseObserver<BaseModel0> observer);

    void submit(Integer workId, String desc, String equipmentId, BaseObserver<Model0<SubmitEntity>> observer);

    void submitParamater(DeviceEntity deviceEntity, BaseObserver<Model0<DeviceEntity>> observer);


    //    void getAppointment(int page, int pageSize, BaseObserver<ListModel<AppointmentEntity>> observer);
//    void getAppointment(String name,String classify,BaseObserver<ListModel0<AppointmentEntity>>observer);
    void getDoctor(int id, BaseObserver<AppointmentEntity> observer);

    void getRecordList(BaseObserver<Model0<List<TestRecordEntity>>> observer);

    void getSchedual(Integer id, BaseObserver<Model0<ExpertSchEntity>> observer);

    void getPersonId(String accid, BaseObserver<Model0<AccidEntity>> observer);

    void addQuickOrder(String equParamId, BaseObserver<Model0<Quick>> observer);

    void submitRecord(String left_hertz, String right_hertz, String left_data, String right_data, String equipmentHolder, BaseObserver observer);


    void sendAdvice(String professorId, String content, BaseObserver<Model0<AdviceBackEntity>> observer);

    void adviceBack(String content, BaseObserver<Model0<AdviceBackEntity>> observer);

    void lunBo(String pageNo, String pageSize, BaseObserver<ListModel<List<LunBoEntity>>> observer);

    void getUser(Integer id,BaseObserver<Model0<UserEntity>> observer);

    void basicInfo(String name, String sex, String age, String wearTimeEnum,String headPic, BaseObserver<Model0<UserEntity>> observer);

    void sueExpert(Integer professorId, String content, BaseObserver<Model0<RecordEntity>> observer);

    void getRecord(Integer id, BaseObserver<ListModel<List<RecordEntity>>> observer);

    void erroCancel(Integer classify, Integer orderId, BaseObserver<BaseModel0> observer);

    void checkMsg(String name,BaseObserver<Model1<MessageEntity>> observer);

    void getUserInfo(String name, BaseObserver<Model0<UserEntity>> observer);

    void getMessage(long lastTime, BaseObserver<ListModel<List<MessageEntity>>> observer);

    void getMsgDetail(Integer newsId, Integer newsType, BaseObserver<Model0<MessageEntity>> observer);


    void getQuickRecord(Integer id, BaseObserver<ListModel<List<RecordEntity>>> observer);

    void sendEvaluation(Integer orderId,Integer starts,String tags,String evaluation, BaseObserver<Model0<EvaluationEntity>> observer);

    void sendNormalEvaluation(Integer orderId,Integer starts,String tags,String evaluation, BaseObserver<Model0<EvaluationEntity>> observer);

    void getMall(Integer pageNo, Integer pageSize, boolean sort, BaseObserver<ListModel<List<MallEntity>>>observer);

    void getReport(Integer orderId,BaseObserver<Model0<RecordEntity>>observer);

    void getQuickReport(Integer quickOrderId,BaseObserver<Model0<RecordEntity>>observer);


    void cancelOrder(Integer classify,Integer orderId,BaseObserver<Model0<RecordEntity>>observer);

    void cancelDate(Integer orderId,Integer classify,BaseObserver<Model0<RecordEntity>>observer);

    void getRecent(String name,BaseObserver<Model0<RecordEntity>>observer);

    void orderStatus(Integer orderId,Integer classify,BaseObserver<Model0<RecordEntity>>observer);

    void getOthers(Integer id, BaseObserver<Model0<OtherEntity>>observer);

    void readOrnot(String name,BaseObserver<Model0<MessageEntity>>observer);


    interface CallBack<T> {
        void addDis(Disposable d);

        void onSuccess(T t);

        void onError();
    }
}
