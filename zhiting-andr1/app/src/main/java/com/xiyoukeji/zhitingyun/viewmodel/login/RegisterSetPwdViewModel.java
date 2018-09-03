package com.xiyoukeji.zhitingyun.viewmodel.login;

import android.app.Application;
import android.content.Context;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.xiyoukeji.zhitingyun.data.Repository;
import com.xiyoukeji.zhitingyun.data.entity.LoginEntity;
import com.xiyoukeji.zhitingyun.data.entity.Model0;
import com.xiyoukeji.zhitingyun.data.remote.BaseObserver;
import com.xiyoukeji.zhitingyun.util.ToastUtils;
import com.xiyoukeji.zhitingyun.viewmodel.BaseViewModel;



public class RegisterSetPwdViewModel extends BaseViewModel {

    private final Context mContext;
    private final Repository mRepository;
    public final ObservableField<String> mName = new ObservableField<>("");
    public final ObservableField<String> mPwd = new ObservableField<>("");
    public final ObservableField<String> mPwdCopy = new ObservableField<>("");


    public RegisterSetPwdViewModel(@NonNull Application application, Repository repository) {
        super(application);
        mContext = application.getApplicationContext();
        mRepository = repository;
    }

}
