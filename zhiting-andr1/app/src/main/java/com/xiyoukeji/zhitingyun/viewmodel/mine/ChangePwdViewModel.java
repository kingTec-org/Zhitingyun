package com.xiyoukeji.zhitingyun.viewmodel.mine;

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



public class ChangePwdViewModel extends BaseViewModel {

    private final Context mContext;
    private final Repository mRepository;
    public final ObservableField<String> mOldPwd = new ObservableField<>("");
    public final ObservableField<String> mNewPwd = new ObservableField<>("");
    public final ObservableField<String> mNewPwdCopy = new ObservableField<>("");


    public ChangePwdViewModel(@NonNull Application application, Repository repository) {
        super(application);
        mContext = application.getApplicationContext();
        mRepository = repository;
    }

    public void changePwd(BaseObserver<Model0<LoginEntity>> observer) {
        if (TextUtils.isEmpty(mOldPwd.get())) {
            ToastUtils.showShort("请输入旧密码");
            return;
        }
        if (TextUtils.isEmpty(mNewPwd.get())) {
            ToastUtils.showShort("请输入新密码");
            return;
        }
        if (TextUtils.isEmpty(mNewPwdCopy.get())) {
            ToastUtils.showShort("请再次输入新密码");
            return;
        }
        if (!checkPwd(mNewPwd.get())) {
            ToastUtils.showShort("请输入8位及以上数字和字母组成的密码");
            return;
        }
        if (!mNewPwd.get().equals(mNewPwdCopy.get())) {
            ToastUtils.showShort("请保持两次输入的密码一致");
            return;
        }
        setLoadingState(true);
        mRepository.resetPwd( mNewPwd.get(),mOldPwd.get(),observer );
    }
}
