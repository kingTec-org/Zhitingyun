package com.xiyoukeji.zhitingyun.viewmodel.main;

import android.app.Application;
import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.xiyoukeji.zhitingyun.data.Repository;
import com.xiyoukeji.zhitingyun.data.entity.ExpertSchEntity;
import com.xiyoukeji.zhitingyun.data.entity.Model0;
import com.xiyoukeji.zhitingyun.data.remote.BaseObserver;
import com.xiyoukeji.zhitingyun.viewmodel.BaseViewModel;

public class AppointmentViewModel extends BaseViewModel {

    private final Context mContext;
    private final Repository mRepository;
    public final ObservableList<ExpertSchEntity.WorkScheduleDtosBean> mSchedual=new ObservableArrayList<>();

    public final ObservableField<Integer> mId=new ObservableField<>( );
    public final ObservableField<String> mName = new ObservableField<>("");
    public final ObservableField<String> mExperience = new ObservableField<>("");
    public final ObservableField<Model0<ExpertSchEntity>> mExpertSchEntity = new ObservableField<Model0<ExpertSchEntity>>();



    public AppointmentViewModel(@NonNull Application application,Repository repository) {
        super(application);
        mContext = application.getApplicationContext();
        mRepository = repository;
    }

    public void onSchedualRefresh(Integer id, BaseObserver<Model0<ExpertSchEntity>> observer){
        mRepository.getSchedual( id,observer );
    }

    public void getExpertData(Integer id,BaseObserver<Model0<ExpertSchEntity>>observer){
        setLoadingState( true );
        mRepository.getSchedual( mId.get(),observer );
    }
}
