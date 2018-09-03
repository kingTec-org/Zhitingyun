package com.xiyoukeji.zhitingyun.data;

import android.support.annotation.NonNull;

import com.xiyoukeji.zhitingyun.constant.AppConstant;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.disposables.Disposable;


public class Repository implements DataSource {

    private volatile static Repository INSTANCE = null;

    private final DataSource mRemoteDataSource;
    private final DataSource mLocalDataSource;
    Map<String, Object> mCacheDataSource = new LinkedHashMap<>();

    private Repository(@NonNull DataSource remoteDataSource,
                       @NonNull DataSource localDataSource) {
        mRemoteDataSource = remoteDataSource;
        mLocalDataSource = localDataSource;
    }

    public static Repository getInstance(DataSource tasksRemoteDataSource,
                                         DataSource tasksLocalDataSource) {
        if (INSTANCE == null) {
            synchronized (DataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Repository(tasksRemoteDataSource, tasksLocalDataSource);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void login(String phone, String password, BaseObserver<Model0<LoginEntity>> observer) {
        mRemoteDataSource.login(phone, password, observer);
    }

    @Override
    public void sendCode(String phone, BaseObserver<BaseModel0> observer) {
        mRemoteDataSource.sendCode(phone, observer);
    }

    @Override
    public void checkCode(String phone, String code, BaseObserver<Boolean> observer) {
        mRemoteDataSource.checkCode(phone, code, observer);
    }

    @Override
    public void register(String phone, String code, String password, BaseObserver<Model0<LoginEntity>> observer) {
        mRemoteDataSource.register(phone, code, password, observer);
    }


    @Override
    public void logout(BaseObserver<BaseModel0> observer) {
        mRemoteDataSource.logout(observer);
    }

    @Override
    public void readAll(String id,BaseObserver<BaseModel0> observer) {
        mRemoteDataSource.readAll(id,observer);
    }

    @Override
    public void updatePwd(String phone, String code, String password, BaseObserver<Model0<LoginEntity>> observer) {
        mRemoteDataSource.updatePwd(phone, code, password, observer);
    }

    @Override
    public void getUserMsg(final CallBack<Model0<LoginEntity>> callBack) {
        mRemoteDataSource.getUserMsg(new CallBack<Model0<LoginEntity>>() {
            @Override
            public void addDis(Disposable d) {
                callBack.addDis(d);
            }

            @Override
            public void onSuccess(Model0<LoginEntity> loginEntityModel0) {
                callBack.onSuccess(loginEntityModel0);
                mCacheDataSource.put(AppConstant.USER_MSG, loginEntityModel0);
            }


            @Override
            public void onError() {
                LoginEntity entity = (LoginEntity) mCacheDataSource.get(AppConstant.USER_MSG);
                if (entity == null) {
                    mLocalDataSource.getUserMsg(new CallBack<Model0<LoginEntity>>() {
                        @Override
                        public void addDis(Disposable d) {

                        }

                        @Override
                        public void onSuccess(Model0<LoginEntity> loginEntityModel0) {
                            callBack.onSuccess(loginEntityModel0);
                        }

                        @Override
                        public void onError() {
                            callBack.onError();
                        }

                    });
                }
            }
        });
    }



    @Override
    public void uploadAvatar(String path, BaseObserver<Model0<String>> observer) {
        mRemoteDataSource.uploadAvatar(path, observer);
    }

    @Override
    public void changePwd(String newPwd, String oldPwd, BaseObserver<LoginEntity> observer) {
        mRemoteDataSource.changePwd(newPwd, oldPwd, observer);
    }

    @Override
    public void resetPwd(String newPassword, String oldPassword, BaseObserver<Model0<LoginEntity>> observer) {
        mRemoteDataSource.resetPwd(newPassword, oldPassword, observer);
    }

    @Override
    public void changePhone(String phone, String code, BaseObserver<Model0<LoginEntity>> observer) {
        mRemoteDataSource.changePhone(phone, code, observer);
    }


    @Override
    public void getAppointment(String name, String classify, BaseObserver<ListModel<List<AppointmentEntity>>> observer) {
        mRemoteDataSource.getAppointment(name, classify, observer);
    }


    @Override
    public void getDoctor(int id, BaseObserver<AppointmentEntity> observer) {
        mRemoteDataSource.getDoctor(id, observer);
    }

    @Override
    public void getRecordList(BaseObserver<Model0<List<TestRecordEntity>>> observer) {
        mRemoteDataSource.getRecordList(observer);
    }

    @Override
    public void getSchedual(Integer id, BaseObserver<Model0<ExpertSchEntity>> observer) {
        mRemoteDataSource.getSchedual(id, observer);
    }

    @Override
    public void getPersonId(String accid, BaseObserver<Model0<AccidEntity>> observer) {
        mRemoteDataSource.getPersonId(accid, observer);
    }

    @Override
    public void addQuickOrder(String equParamId, BaseObserver<Model0<Quick>> observer) {
        mRemoteDataSource.addQuickOrder(equParamId, observer);
    }

    @Override
    public void submitRecord(String left_hertz, String right_hertz, String left_data, String right_data, String equipmentHolder, BaseObserver observer) {
        mRemoteDataSource.submitRecord(left_hertz, right_hertz, left_data, right_data, equipmentHolder, observer);
    }

    @Override
    public void getTestRecordData(int id, BaseObserver<TestRecordEntity> observer) {
        mRemoteDataSource.getTestRecordData(id, observer);
    }

    @Override
    public void sendAdvice(String professorId, String content, BaseObserver<Model0<AdviceBackEntity>> observer) {
        mRemoteDataSource.sendAdvice(professorId, content, observer);
    }

    @Override
    public void adviceBack(String content, BaseObserver<Model0<AdviceBackEntity>> observer) {
        mRemoteDataSource.adviceBack(content, observer);
    }

    @Override
    public void lunBo(String pageNo, String pageSize, BaseObserver<ListModel<List<LunBoEntity>>> observer) {
        mRemoteDataSource.lunBo(pageNo, pageSize, observer);
    }

    @Override
    public void getUser(Integer id,BaseObserver<Model0<UserEntity>> observer) {
        mRemoteDataSource.getUser(id,observer);
    }

    @Override
    public void basicInfo(String name, String sex, String age, String wearTimeEnum,String headPic, BaseObserver<Model0<UserEntity>> observer) {
        mRemoteDataSource.basicInfo(name, sex, age, wearTimeEnum,headPic, observer);
    }

    @Override
    public void sueExpert(Integer professorId, String content, BaseObserver<Model0<RecordEntity>> observer) {
        mRemoteDataSource.sueExpert(professorId, content, observer);
    }

    @Override
    public void getRecord(Integer id, BaseObserver<ListModel<List<RecordEntity>>> observer) {
        mRemoteDataSource.getRecord(id, observer);
    }

    @Override
    public void erroCancel(Integer classify, Integer orderId, BaseObserver<BaseModel0> observer) {
        mRemoteDataSource.erroCancel(classify, orderId, observer);
    }

    @Override
    public void checkMsg(String name,BaseObserver<Model1<MessageEntity>> observer) {
        mRemoteDataSource.checkMsg(name,observer);
    }

    @Override
    public void getUserInfo(String name, BaseObserver<Model0<UserEntity>> observer) {
        mRemoteDataSource.getUserInfo(name, observer);
    }


    @Override
    public void sendDevice(Integer classify, String equipmentType, List<String> hertz, String chg_DATA, Integer P_S_DL, List<Double> L_P_VC, Integer L_P_VC_VALUE, List<Double> L_P_BASS, Integer L_P_BASS_VALUE, List<Double> L_P_MID, Integer L_P_MID_VALUE, List<Double> L_P_TRB, Integer L_P_TRB_VALUE, Integer L_P_NRLVL_VALUE, List<Double> L_P_NRLVL, Integer L_P_AFSLVL_VALUE, List<Double> L_P_AFSLVL, String L_P_EQ, Boolean L_SWITCH_P_CH, List<String> L_P_CHMPO, List<String> L_P_CHSW, List<String> L_P_CHSG, List<String> L_P_CHNG, List<String> L_P_CHLG, Boolean L_SWITCH_P_CHEXP, List<String> L_P_CHEXP, List<String> L_P_CHETH, List<String> L_P_CHER, List<Double> R_P_VC, Integer R_P_VC_VALUE, List<Double> R_P_BASS, Integer R_P_BASS_VALUE, List<Double> R_P_MID, Integer R_P_MID_VALUE, List<Double> R_P_TRB, Integer R_P_TRB_VALUE, Integer R_P_NRLVL_VALUE, List<Double> R_P_NRLVL, Integer R_P_AFSLVL_VALUE, List<Double> R_P_AFSLVL, String R_P_EQ, Boolean R_SWITCH_P_CH, List<String> R_P_CHMPO, List<String> R_P_CHSG, List<String> R_P_CHNG, List<String> R_P_CHLG, List<String> R_P_CHSW, Boolean R_SWITCH_P_CHEXP, List<String> R_P_CHEXP, List<String> R_P_CHETH, List<String> R_P_CHER, BaseObserver<Model0<DeviceEntity>> observer) {
        mRemoteDataSource.sendDevice(classify, equipmentType, hertz, chg_DATA, P_S_DL, L_P_VC, L_P_VC_VALUE, L_P_BASS, L_P_BASS_VALUE, L_P_MID, L_P_MID_VALUE, L_P_TRB, L_P_TRB_VALUE, L_P_NRLVL_VALUE,
                L_P_NRLVL, L_P_AFSLVL_VALUE, L_P_AFSLVL, L_P_EQ, L_SWITCH_P_CH, L_P_CHMPO, L_P_CHSW, L_P_CHSG, L_P_CHNG, L_P_CHLG, L_SWITCH_P_CHEXP, L_P_CHEXP, L_P_CHETH, L_P_CHER, R_P_VC, R_P_BASS_VALUE, R_P_BASS,
                R_P_BASS_VALUE, R_P_MID, R_P_MID_VALUE, R_P_TRB, R_P_TRB_VALUE, R_P_NRLVL_VALUE, R_P_NRLVL, R_P_AFSLVL_VALUE, R_P_AFSLVL, R_P_EQ, R_SWITCH_P_CH, R_P_CHMPO, R_P_CHSG, R_P_CHNG, R_P_CHLG, R_P_CHSW,
                R_SWITCH_P_CHEXP, R_P_CHEXP, R_P_CHETH, R_P_CHER, observer);
    }

    @Override
    public void sendExpert(Integer proId, String equipmentParamIds, BaseObserver<BaseModel0> observer) {
        mRemoteDataSource.sendExpert(proId, equipmentParamIds, observer);
    }

    @Override
    public void submit(Integer workId, String desc, String equipmentId, BaseObserver<Model0<SubmitEntity>> observer) {
        mRemoteDataSource.submit(workId, desc, equipmentId, observer);
    }

    @Override
    public void submitParamater(DeviceEntity deviceEntity, BaseObserver<Model0<DeviceEntity>> observer) {
        mRemoteDataSource.submitParamater(deviceEntity, observer);
    }

    @Override
    public void getProfessorList(String name, Integer classify, BaseObserver<Model0<List<Professor>>> observer) {
        mRemoteDataSource.getProfessorList(name, classify, observer);
    }


    @Override
    public void getQuickRecord(Integer id, BaseObserver<ListModel<List<RecordEntity>>> observer) {
        mRemoteDataSource.getQuickRecord(id, observer );

    }

    @Override
    public void sendEvaluation(Integer orderId, Integer starts, String tags, String evaluation, BaseObserver<Model0<EvaluationEntity>> observer) {
        mRemoteDataSource.sendEvaluation( orderId,starts,tags,evaluation,observer );
    }

    @Override
    public void sendNormalEvaluation(Integer orderId, Integer starts, String tags, String evaluation, BaseObserver<Model0<EvaluationEntity>> observer) {
        mRemoteDataSource.sendNormalEvaluation( orderId,starts,tags,evaluation,observer );
    }

    @Override
    public void getMall(Integer pageNo, Integer pageSize, boolean sort, BaseObserver<ListModel<List<MallEntity>>> observer) {
        mRemoteDataSource.getMall( pageNo,pageSize,sort,observer );
    }

    @Override
    public void getReport(Integer orderId, BaseObserver<Model0<RecordEntity>> observer) {
        mRemoteDataSource.getReport( orderId,observer );
    }

    @Override
    public void getRecent(String name, BaseObserver<Model0<RecordEntity>> observer) {
        mRemoteDataSource.getRecent( name,observer );
    }

    @Override
    public void orderStatus(Integer orderId,Integer classify, BaseObserver<Model0<RecordEntity>> observer) {
        mRemoteDataSource.orderStatus(orderId, classify,observer );
    }

    @Override
    public void getOthers(Integer id, BaseObserver<Model0<OtherEntity>> observer) {
        mRemoteDataSource.getOthers( id,observer );
    }

    @Override
    public void readOrnot(String name, BaseObserver<Model0<MessageEntity>> observer) {
        mRemoteDataSource.readOrnot( name,observer );
    }

    @Override
    public void getQuickReport(Integer quickOrderId, BaseObserver<Model0<RecordEntity>> observer) {
        mRemoteDataSource.getQuickReport( quickOrderId,observer );
    }

    @Override
    public void cancelOrder(Integer classify, Integer orderId, BaseObserver<Model0<RecordEntity>> observer) {
        mRemoteDataSource.cancelOrder( classify,orderId,observer );
    }

    @Override
    public void cancelDate(Integer orderId,Integer classify, BaseObserver<Model0<RecordEntity>> observer) {
        mRemoteDataSource.cancelDate( orderId,classify,observer );
    }


    @Override
    public void getMessage(long lastTime, BaseObserver<ListModel<List<MessageEntity>>> observer) {
        mRemoteDataSource.getMessage(lastTime, observer);
    }

    @Override
    public void getMsgDetail(Integer newsId, Integer newsType, BaseObserver<Model0<MessageEntity>> observer) {
        mRemoteDataSource.getMsgDetail(newsId, newsType, observer);
    }

}
