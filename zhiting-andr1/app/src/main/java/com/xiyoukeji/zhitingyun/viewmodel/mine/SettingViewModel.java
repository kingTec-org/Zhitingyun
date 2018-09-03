package com.xiyoukeji.zhitingyun.viewmodel.mine;

import android.app.Application;
import android.content.Context;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.xiyoukeji.zhitingyun.data.Repository;
import com.xiyoukeji.zhitingyun.data.entity.BaseModel;
import com.xiyoukeji.zhitingyun.data.entity.BaseModel0;
import com.xiyoukeji.zhitingyun.data.remote.BaseObserver;
import com.xiyoukeji.zhitingyun.viewmodel.BaseViewModel;

import io.reactivex.internal.operators.observable.ObservableError;



public class SettingViewModel extends BaseViewModel {

    private final Context mContext;
    private final Repository mRepository;
    public final ObservableField<String> mName = new ObservableField<>("");
    public final ObservableField<String> mUrl = new ObservableField<>("");
    public final ObservableField<String> phone=new ObservableField<>( "" );


    public SettingViewModel(@NonNull Application application, Repository repository) {
        super(application);
        mContext = application.getApplicationContext();
        mRepository = repository;
    }

    public void logout(BaseObserver<BaseModel0> observer) {
        mTip.set("退出中...");
        setLoadingState(true);
        mRepository.logout(observer);
    }
}
