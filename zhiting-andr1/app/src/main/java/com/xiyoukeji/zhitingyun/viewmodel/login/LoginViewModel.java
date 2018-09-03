package com.xiyoukeji.zhitingyun.viewmodel.login;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;

import com.xiyoukeji.zhitingyun.LoadingState;
import com.xiyoukeji.zhitingyun.data.DataSource;
import com.xiyoukeji.zhitingyun.data.Repository;
import com.xiyoukeji.zhitingyun.data.entity.BaseModel;
import com.xiyoukeji.zhitingyun.data.entity.BaseModel0;
import com.xiyoukeji.zhitingyun.data.entity.LoginEntity;
import com.xiyoukeji.zhitingyun.data.entity.Model0;
import com.xiyoukeji.zhitingyun.data.remote.BaseObserver;
import com.xiyoukeji.zhitingyun.util.ToastUtils;
import com.xiyoukeji.zhitingyun.viewmodel.BaseViewModel;

import java.util.Observable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class LoginViewModel extends BaseViewModel {

    private final Context mContext;
    private final Repository mRepository;
    public final ObservableField<String> mPhone = new ObservableField<>("");
    public final ObservableField<String> mPassword = new ObservableField<>("");

    public LoginViewModel(@NonNull Application application, Repository repository) {
        super(application);
        mContext = application.getApplicationContext();
        mRepository = repository;
    }

    public void login(BaseObserver<Model0<LoginEntity>> observer) {
        if (mPhone.get().isEmpty()) {
            ToastUtils.showShort("手机号不能为空");
            return;
        }
        if (mPassword.get().isEmpty()) {
            ToastUtils.showShort("密码不能为空");
            return;
        }
        if (mPassword.get().length() < 6) {
            ToastUtils.showShort("密码不能少于6位");
            return;
        }
        if (!checkPhone(mPhone.get())) {
            ToastUtils.showShort("请输入正确手机号");
            return;
        }
        mTip.set("登录中");
        setLoadingState(true);
        mRepository.login(mPhone.get(), mPassword.get(), observer);
    }

    protected boolean checkPhone(String str) {
        String pattern = "0?(13|14|15|17|18)[0-9]{9}";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        return m.matches();
    }
}
