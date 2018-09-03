package com.xiyoukeji.zhitingyun.viewmodel.main;

import android.app.Application;
import android.content.Context;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.xiyoukeji.zhitingyun.data.Repository;
import com.xiyoukeji.zhitingyun.data.entity.Model0;
import com.xiyoukeji.zhitingyun.data.entity.OtherEntity;
import com.xiyoukeji.zhitingyun.data.remote.BaseObserver;
import com.xiyoukeji.zhitingyun.viewmodel.BaseViewModel;

public class OthersViewModel extends BaseViewModel {

    private final Context mContext;
    private final Repository mRepository;

    public final ObservableField<Model0<OtherEntity>> mEntity = new ObservableField<Model0<OtherEntity>>();


    public OthersViewModel(@NonNull Application application, Repository repository) {
        super(application);
        mContext = application.getApplicationContext();
        mRepository = repository;
    }

    public void getOthers(Integer id,BaseObserver<Model0<OtherEntity>> observer){
        mRepository.getOthers( id,observer );
    }
}
