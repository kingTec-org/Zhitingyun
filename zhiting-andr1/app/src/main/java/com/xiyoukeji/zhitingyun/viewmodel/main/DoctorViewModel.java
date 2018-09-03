package com.xiyoukeji.zhitingyun.viewmodel.main;

import android.app.Application;
import android.content.Context;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.xiyoukeji.zhitingyun.data.Repository;
import com.xiyoukeji.zhitingyun.viewmodel.BaseViewModel;


public class DoctorViewModel extends BaseViewModel {

    private final Context mContext;
    private final Repository mRepository;
    public final ObservableField<String>mName=new ObservableField<>( "" );

    public DoctorViewModel(@NonNull Application application, Repository repository) {
        super( application );
        mContext = application.getApplicationContext();
        mRepository = repository;
    }

}
