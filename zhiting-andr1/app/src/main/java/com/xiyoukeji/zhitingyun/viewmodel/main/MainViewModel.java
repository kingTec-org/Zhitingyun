package com.xiyoukeji.zhitingyun.viewmodel.main;

import android.app.Application;
import android.content.Context;
import android.databinding.Observable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.xiyoukeji.zhitingyun.SingleLiveEvent;
import com.xiyoukeji.zhitingyun.data.Repository;
import com.xiyoukeji.zhitingyun.data.entity.AccidEntity;
import com.xiyoukeji.zhitingyun.data.entity.AppointmentEntity;
import com.xiyoukeji.zhitingyun.data.entity.DeviceEntity;
import com.xiyoukeji.zhitingyun.data.entity.ExpertSchEntity;
import com.xiyoukeji.zhitingyun.data.entity.ListModel;
import com.xiyoukeji.zhitingyun.data.entity.LoginEntity;
import com.xiyoukeji.zhitingyun.data.entity.LunBoEntity;
import com.xiyoukeji.zhitingyun.data.entity.MallEntity;
import com.xiyoukeji.zhitingyun.data.entity.MessageEntity;
import com.xiyoukeji.zhitingyun.data.entity.Model0;
import com.xiyoukeji.zhitingyun.data.entity.Model1;
import com.xiyoukeji.zhitingyun.data.entity.Professor;
import com.xiyoukeji.zhitingyun.data.entity.Quick;
import com.xiyoukeji.zhitingyun.data.entity.RecordEntity;
import com.xiyoukeji.zhitingyun.data.entity.SubmitEntity;
import com.xiyoukeji.zhitingyun.data.entity.TestRecordEntity;
import com.xiyoukeji.zhitingyun.data.remote.BaseObserver;
import com.xiyoukeji.zhitingyun.viewmodel.BaseViewModel;

import java.util.List;


public class MainViewModel extends BaseViewModel {

    private final Context mContext;
    private final Repository mRepository;

    public final ObservableField<String> mPageNo = new ObservableField<>("");
    public final ObservableField<String> mPageSize = new ObservableField<>("");

    public final ObservableList<AppointmentEntity> mAppointment = new ObservableArrayList<>();
    public final ObservableField<LoginEntity> mUserMsg = new ObservableField<>();
    private final SingleLiveEvent<Long> mExperience = new SingleLiveEvent<>();
    public final ObservableList<RecordEntity>mRecord=new ObservableArrayList<>(  );
    public final ObservableList<RecordEntity>mQuickRecord=new ObservableArrayList<>(  );
    public final ObservableList<MallEntity>mMall=new ObservableArrayList<>(  );
    public final ObservableList<LunBoEntity>mLunBo=new ObservableArrayList<>(  );
    public final ObservableList<Professor> mProfessor = new ObservableArrayList<>();
    public final ObservableField<Model0<RecordEntity>> mRecent = new ObservableField<Model0<RecordEntity>>();
    public final ObservableField<Model0<RecordEntity>> mOrderStatus = new ObservableField<Model0<RecordEntity>>();







    public MainViewModel(@NonNull Application application, Repository repository) {
        super(application);
        mContext = application.getApplicationContext();
        mRepository = repository;
        mExperience.setValue(0L);
    }


    public void onAppointmentRefresh(String name, String classify, BaseObserver<ListModel<List<AppointmentEntity>>> observer) {
        mRepository.getAppointment(name, classify, observer);
    }

    public void getProfessorList(String name, Integer classify, BaseObserver<Model0<List<Professor>>> observer) {
        mRepository.getProfessorList(name, classify, observer);
    }

    public void getRecordList(BaseObserver<Model0<List<TestRecordEntity>>> observer) {
        mRepository.getRecordList(observer);
    }

    public void getMall(Integer pageNo,Integer pageSize,boolean sort,BaseObserver<ListModel<List<MallEntity>>> observer) {
        mRepository.getMall(pageNo,pageSize,sort,observer);
    }

    public void submit(Integer workId, String desc, String equipmentId, BaseObserver observer) {
        mRepository.submit(workId, desc, equipmentId, observer);
    }

    public void submitParamater(DeviceEntity deviceEntity, BaseObserver<Model0<DeviceEntity>> observer) {
        mRepository.submitParamater(deviceEntity, observer);
    }

    public void submitRecord(String left_hertz, String right_hertz, String left_data, String right_data, String equipmentHolder, BaseObserver observer) {
        mRepository.submitRecord(left_hertz, right_hertz, left_data, right_data, equipmentHolder, observer);
    }

    public void getPersonId(String accid, BaseObserver<Model0<AccidEntity>> observer) {
        mRepository.getPersonId(accid, observer);
    }

    public void addQuickOrder(String equParamId, BaseObserver<Model0<Quick>> observer) {
        mRepository.addQuickOrder(equParamId, observer);
    }

    public void getLunBo(String pageNo, String pageSize, BaseObserver<ListModel<List<LunBoEntity>>> observer) {
        mRepository.lunBo(mPageNo.get(), mPageSize.get(), observer);
    }

    public void getRecent(String name, BaseObserver<Model0<RecordEntity>>observer){

        mRepository.getRecent( name,observer);
    }

    public void onRecordRefresh(Integer id, BaseObserver<ListModel<List<RecordEntity>>> observer) {
        mRepository.getRecord(id, observer);
    }

    public void onQuickRecordRefresh(Integer id, BaseObserver<ListModel<List<RecordEntity>>> observer) {
        mRepository.getQuickRecord(id, observer);
    }

    public void cancelOrder(Integer classify,Integer orderId,BaseObserver<Model0<RecordEntity>>observer){
        mRepository.cancelOrder( classify,orderId,observer );
    }

    public void cancelDate(Integer orderId,Integer classify,BaseObserver<Model0<RecordEntity>>observer){
        mRepository.cancelDate( orderId,classify,observer );
    }

    public void checkMsg(String name,BaseObserver<Model1<MessageEntity>>observer){
        mRepository.checkMsg(name,observer );
    }

    public void orderStatus(Integer orderId,Integer classify,BaseObserver<Model0<RecordEntity>>observer){
        mRepository.orderStatus( orderId,classify,observer );
    }
}
