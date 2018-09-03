package com.xiyoukeji.zhitingyun.viewmodel.mine;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.xiyoukeji.zhitingyun.LoadingState;
import com.xiyoukeji.zhitingyun.data.Injection;
import com.xiyoukeji.zhitingyun.data.Repository;
import com.xiyoukeji.zhitingyun.data.entity.BaseModel;
import com.xiyoukeji.zhitingyun.data.entity.BaseModel0;
import com.xiyoukeji.zhitingyun.data.entity.LoginEntity;
import com.xiyoukeji.zhitingyun.data.entity.Model0;
import com.xiyoukeji.zhitingyun.data.remote.BaseObserver;
import com.xiyoukeji.zhitingyun.util.ToastUtils;
import com.xiyoukeji.zhitingyun.viewmodel.BaseViewModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class ForgetPwdViewModel extends BaseViewModel {

    private final Context mContext;
    private final Repository mRepository;
    public final ObservableField<String> mPhone = new ObservableField<>("");
    public final ObservableField<String> mCode = new ObservableField<>("");
    public final ObservableField<String> mPwd = new ObservableField<>("");



    public ForgetPwdViewModel(@NonNull Application application, Repository repository) {
        super(application);
        mContext = application.getApplicationContext();
        mRepository = repository;
    }

    public void sendCode(BaseObserver<BaseModel0> observer){
        if (TextUtils.isEmpty(mPhone.get())) {
            ToastUtils.showShort("手机号不能为空");
            return;
        }
        if (!checkPhone(mPhone.get())) {
            ToastUtils.showShort("请输入正确手机号");
            return;
        }
        setLoadingState(true);
        mRepository.sendCode(mPhone.get(), observer);
    }

    public void checkCode(BaseObserver<Boolean> observer) {
        if (TextUtils.isEmpty(mPhone.get())) {
            ToastUtils.showShort("手机号不能为空");
            return;
        }
        if (TextUtils.isEmpty(mCode.get())) {
            ToastUtils.showShort("验证码不能为空");
            return;
        }
        if (!checkPhone(mPhone.get())) {
            ToastUtils.showShort("请输入正确手机号");
            return;
        }
        setLoadingState(true);
        mRepository.checkCode(mPhone.get(), mCode.get(), observer);
    }


    public void updatePwd(String phone, String code,String password, BaseObserver<Model0<LoginEntity>> observer) {
        if (TextUtils.isEmpty(mPhone.get())) {
            ToastUtils.showShort("请输入手机号");
            return;
        }
        if (TextUtils.isEmpty(mCode.get())) {
            ToastUtils.showShort("验证码不能为空");
            return;
        }
        if (TextUtils.isEmpty(mPwd.get())) {
            ToastUtils.showShort("请输入密码");
            return;
        }
        if (!checkPwd(mPwd.get())) {
            ToastUtils.showShort("请输入6位及以上数字和字母组成的密码");
            return;
        }
        mTip.set("注册中");
        setLoadingState(true);
        mRepository.updatePwd(phone,code,mPwd.get(),observer);
    }

    protected boolean checkPhone(String str) {
        String pattern = "0?(13|14|15|17|18)[0-9]{9}";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        return m.matches();
    }
}
