package com.xiyoukeji.zhitingyun.viewmodel.mine;

import android.app.Application;
import android.content.Context;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.xiyoukeji.zhitingyun.data.Repository;
import com.xiyoukeji.zhitingyun.data.User;
import com.xiyoukeji.zhitingyun.data.entity.LoginEntity;
import com.xiyoukeji.zhitingyun.data.entity.Model0;
import com.xiyoukeji.zhitingyun.data.entity.UserEntity;
import com.xiyoukeji.zhitingyun.data.remote.BaseObserver;
import com.xiyoukeji.zhitingyun.viewmodel.BaseViewModel;

import java.io.File;

public class MineViewModel extends BaseViewModel {
    private final Context mContext;
    private final Repository mRepository;
    public final ObservableField<Model0<UserEntity>> mUserEntity = new ObservableField<Model0<UserEntity>>();
    public final ObservableField<String> mUrl = new ObservableField<>("");

    public MineViewModel(@NonNull Application application, Repository repository) {
        super( application );
        mContext = application.getApplicationContext();
        mRepository = repository;
    }



    public void getUserInfo(Integer id,BaseObserver<Model0<UserEntity>> observer){
        mRepository.getUser(id,observer );
    }


}
