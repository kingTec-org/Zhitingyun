package com.xiyoukeji.zhitingyun.data.local;

import com.xiyoukeji.zhitingyun.constant.AppConstant;
import com.xiyoukeji.zhitingyun.data.DataSource;
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
import com.xiyoukeji.zhitingyun.util.SPUtil;

import java.io.File;
import java.util.List;


public class LocalDataSource implements DataSource {

    private static LocalDataSource INSTANCE;

    private LocalDataSource() {
    }

    public static LocalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LocalDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void login(String phone, String password, BaseObserver<Model0<LoginEntity>> observer) {

    }

    @Override
    public void sendCode(String phone, BaseObserver<BaseModel0> observer) {

    }

    @Override
    public void checkCode(String phone, String code, BaseObserver<Boolean> observer) {

    }

    @Override
    public void register(String phone, String code, String password, BaseObserver<Model0<LoginEntity>> observer) {

    }


    @Override
    public void logout(BaseObserver<BaseModel0> observer) {

    }

    @Override
    public void updatePwd(String phone, String code, String password, BaseObserver<Model0<LoginEntity>> observer) {

    }


    @Override
    public void getUserMsg(CallBack<Model0<LoginEntity>> callBack) {
        Model0<LoginEntity> entity = SPUtil.getObjectFromShare(AppConstant.USER_MSG);
        if (entity == null) {
            callBack.onError();
        } else {
            callBack.onSuccess( entity );
        }
    }

    @Override
    public void readAll(String id,BaseObserver<BaseModel0> observer) {

    }

    @Override
    public void uploadAvatar(String path, BaseObserver<Model0<String>> observer) {

    }


    @Override
    public void changePwd(String newPwd, String oldPwd, BaseObserver<LoginEntity> observer) {

    }

    @Override
    public void resetPwd(String newPassword, String oldPassword, BaseObserver<Model0<LoginEntity>> observer) {

    }

    @Override
    public void changePhone(String phone, String code, BaseObserver<Model0<LoginEntity>> observer) {

    }


    @Override
    public void getDoctor(int id, BaseObserver<AppointmentEntity> observer) {

    }

    @Override
    public void getRecordList(BaseObserver<Model0<List<TestRecordEntity>>> observer) {

    }

    @Override
    public void getSchedual(Integer id, BaseObserver<Model0<ExpertSchEntity>> observer) {

    }

    @Override
    public void getPersonId(String accid, BaseObserver<Model0<AccidEntity>> observer) {

    }

    @Override
    public void addQuickOrder(String equParamId, BaseObserver<Model0<Quick>> observer) {

    }

    @Override
    public void submitRecord(String left_hertz, String right_hertz, String left_data, String right_data, String equipmentHolder, BaseObserver observer) {

    }

    @Override
    public void sendAdvice(String professorId, String content, BaseObserver<Model0<AdviceBackEntity>> observer) {

    }

    @Override
    public void adviceBack(String content, BaseObserver<Model0<AdviceBackEntity>> observer) {

    }

    @Override
    public void lunBo(String pageNo, String pageSize, BaseObserver<ListModel<List<LunBoEntity>>> observer) {

    }

    @Override
    public void getUser(Integer id,BaseObserver<Model0<UserEntity>> observer) {

    }

    @Override
    public void basicInfo(String name, String sex, String age, String wearTimeEnum,String headPic, BaseObserver<Model0<UserEntity>> observer) {

    }

    @Override
    public void sueExpert(Integer professorId, String content, BaseObserver<Model0<RecordEntity>> observer) {

    }

    @Override
    public void getRecord(Integer id, BaseObserver<ListModel<List<RecordEntity>>> observer) {

    }

    @Override
    public void erroCancel(Integer classify, Integer orderId, BaseObserver<BaseModel0> observer) {

    }

    @Override
    public void checkMsg(String name,BaseObserver<Model1<MessageEntity>> observer) {

    }

    @Override
    public void getUserInfo(String name, BaseObserver<Model0<UserEntity>> observer) {

    }

    @Override
    public void getMessage(long lastTime, BaseObserver<ListModel<List<MessageEntity>>> observer) {

    }

    @Override
    public void getMsgDetail(Integer newsId, Integer newsType, BaseObserver<Model0<MessageEntity>> observer) {

    }

    @Override
    public void getQuickRecord(Integer id, BaseObserver<ListModel<List<RecordEntity>>> observer) {

    }

    @Override
    public void sendEvaluation(Integer orderId, Integer starts, String tags, String evaluation, BaseObserver<Model0<EvaluationEntity>> observer) {

    }

    @Override
    public void sendNormalEvaluation(Integer orderId, Integer starts, String tags, String evaluation, BaseObserver<Model0<EvaluationEntity>> observer) {

    }

    @Override
    public void getMall(Integer pageNo, Integer pageSize, boolean sort, BaseObserver<ListModel<List<MallEntity>>> observer) {

    }

    @Override
    public void getReport(Integer orderId, BaseObserver<Model0<RecordEntity>> observer) {

    }

    @Override
    public void getQuickReport(Integer quickOrderId, BaseObserver<Model0<RecordEntity>> observer) {

    }

    @Override
    public void cancelOrder(Integer classify, Integer orderId, BaseObserver<Model0<RecordEntity>> observer) {

    }

    @Override
    public void cancelDate(Integer orderId,Integer classify, BaseObserver<Model0<RecordEntity>> observer) {

    }

    @Override
    public void getRecent(String name, BaseObserver<Model0<RecordEntity>> observer) {

    }

    @Override
    public void orderStatus(Integer orderId, Integer classify, BaseObserver<Model0<RecordEntity>> observer) {

    }

    @Override
    public void getOthers(Integer id, BaseObserver<Model0<OtherEntity>> observer) {

    }

    @Override
    public void readOrnot(String name, BaseObserver<Model0<MessageEntity>> observer) {

    }


    @Override
    public void getTestRecordData(int id, BaseObserver<TestRecordEntity> observer) {

    }

    @Override
    public void sendDevice(Integer classify, String equipmentType, List<String> hertz, String chg_DATA, Integer P_S_DL, List<Double> L_P_VC, Integer L_P_VC_VALUE, List<Double> L_P_BASS, Integer L_P_BASS_VALUE, List<Double> L_P_MID, Integer L_P_MID_VALUE, List<Double> L_P_TRB, Integer L_P_TRB_VALUE, Integer L_P_NRLVL_VALUE, List<Double> L_P_NRLVL, Integer L_P_AFSLVL_VALUE, List<Double> L_P_AFSLVL, String L_P_EQ, Boolean L_SWITCH_P_CH, List<String> L_P_CHMPO, List<String> L_P_CHSW, List<String> L_P_CHSG, List<String> L_P_CHNG, List<String> L_P_CHLG, Boolean L_SWITCH_P_CHEXP, List<String> L_P_CHEXP, List<String> L_P_CHETH, List<String> L_P_CHER, List<Double> R_P_VC, Integer R_P_VC_VALUE, List<Double> R_P_BASS, Integer R_P_BASS_VALUE, List<Double> R_P_MID, Integer R_P_MID_VALUE, List<Double> R_P_TRB, Integer R_P_TRB_VALUE, Integer R_P_NRLVL_VALUE, List<Double> R_P_NRLVL, Integer R_P_AFSLVL_VALUE, List<Double> R_P_AFSLVL, String R_P_EQ, Boolean R_SWITCH_P_CH, List<String> R_P_CHMPO, List<String> R_P_CHSG, List<String> R_P_CHNG, List<String> R_P_CHLG, List<String> R_P_CHSW, Boolean R_SWITCH_P_CHEXP, List<String> R_P_CHEXP, List<String> R_P_CHETH, List<String> R_P_CHER, BaseObserver<Model0<DeviceEntity>> observer) {

    }



    @Override
    public void getAppointment(String name, String classify, BaseObserver<ListModel<List<AppointmentEntity>>> observer) {

    }

    @Override
    public void sendExpert(Integer proId, String equipmentParamIds, BaseObserver<BaseModel0> observer) {

    }

    @Override
    public void getProfessorList(String name, Integer classify, BaseObserver<Model0<List<Professor>>> observer) {

    }

    @Override
    public void submit(Integer workId, String desc, String equipmentId, BaseObserver<Model0<SubmitEntity>> observer) {

    }

    @Override
    public void submitParamater(DeviceEntity deviceEntity, BaseObserver<Model0<DeviceEntity>> observer) {

    }


}
