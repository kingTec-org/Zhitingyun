package com.xiyoukeji.zhitingyun.view.login;

import android.Manifest;
import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.LinkMovementMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.xiyoukeji.zhitingyun.R;
import com.xiyoukeji.zhitingyun.ViewModelFactory;
import com.xiyoukeji.zhitingyun.base.BaseActivity;
import com.xiyoukeji.zhitingyun.constant.AppConstant;
import com.xiyoukeji.zhitingyun.constant.Urls;
import com.xiyoukeji.zhitingyun.data.entity.BaseModel0;
import com.xiyoukeji.zhitingyun.data.entity.LoginEntity;
import com.xiyoukeji.zhitingyun.data.entity.MessageEvent;
import com.xiyoukeji.zhitingyun.data.entity.Model0;
import com.xiyoukeji.zhitingyun.data.entity.UserEntity;
import com.xiyoukeji.zhitingyun.data.remote.BaseObserver;
import com.xiyoukeji.zhitingyun.databinding.ActivityLoginBinding;
import com.xiyoukeji.zhitingyun.util.ActivityManager;
import com.xiyoukeji.zhitingyun.util.Constant;
import com.xiyoukeji.zhitingyun.util.SPUtil;
import com.xiyoukeji.zhitingyun.util.ToastUtils;
import com.xiyoukeji.zhitingyun.view.main.MainActivity;
import com.xiyoukeji.zhitingyun.viewmodel.login.LoginViewModel;
import com.xiyoukeji.zhitingyun.viewmodel.main.MainViewModel;

import com.xiyoukeji.zhitingyun.widget.StatusBarUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;



public class LoginActivity extends BaseActivity {


    @BindView(R.id.passwd_edittext)
    EditText mPwdEt;
    @BindView(R.id.telephone_edittext)
    EditText mTelEt;
    @BindView(R.id.pwd_cb)
    CheckBox mPwdCb;

    private LoginViewModel mViewModel;
    private ActivityLoginBinding mBinding;

    public LoginActivity() {
        super(R.layout.activity_login);
    }

    @Override
    protected void bindingData() {
        super.bindingData();
        mBinding = DataBindingUtil.setContentView(mActivity, layoutId);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        int from = getIntent().getIntExtra( "from", 0 );


        if (from == 1) {
            AlertDialog.Builder builder = new AlertDialog.Builder( this );
            builder.setTitle( "" ).setMessage( "该账号已在另一台设备上登录，您的账号密码可能已被盗用" )
                    .setNegativeButton( "确定", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface arg0, int arg1) {
                            arg0.dismiss();
                        }
                    } );
            builder.create().show();
        }



            ActivityManager.finishExcept( LoginActivity.class);
            SharedPreferences sharedPreferences = getSharedPreferences("mySP", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("token","");
            editor.commit();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            StatusBarUtil.setStatusBarColor(this, R.color.white); }
    }

    @Override
    protected void initView() {
        super.initView();

        mPwdCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mPwdEt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    if (!TextUtils.isEmpty(mPwdEt.getText().toString())) {
                        mPwdEt.setSelection(mPwdEt.getText().toString().length());
                    }
                } else {
                    mPwdEt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    if (!TextUtils.isEmpty(mPwdEt.getText().toString())) {
                        mPwdEt.setSelection(mPwdEt.getText().toString().length());
                    }
                }
            }
        });

    }

    @Override
    protected void initData() {
        super.initData();
        obtainViewModel();
        mBinding.setViewModel(mViewModel);
        mViewModel.getLoadingState().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean) {
                    showLoading(mViewModel.mTip.get());
                } else {
                    dismissLoading();
                }
            }
        });
    }



    public void obtainViewModel() {
        ViewModelFactory factory = ViewModelFactory.getInstance(mActivity.getApplication());
        mViewModel = ViewModelProviders.of(this, factory).get(LoginViewModel.class);
    }

    @OnClick({R.id.btn_register, R.id.forget_passwd_textview, R.id.btn_log_in})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                startActivity(RegisterActivity.class);
                break;
            case R.id.forget_passwd_textview:
                startActivity(ForgetPwdActivity.class);
                break;
            case R.id.btn_log_in:
                mViewModel.login(new BaseObserver<Model0<LoginEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(Model0<LoginEntity> entity) {
                        mViewModel.setLoadingState(false);
                        com.xiyoukeji.zhitingyun.yunxin.SPUtil.put(LoginActivity.this, "imToken", entity.getComeback().getImToken());
                        com.xiyoukeji.zhitingyun.yunxin.SPUtil.put(LoginActivity.this, "accid", entity.getComeback().getAccid());


                        SharedPreferences sharedPreferences = getSharedPreferences("mySP", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt("id", entity.getComeback().getId());
                        editor.putString("token", entity.getComeback().getToken());
                        editor.putString( "usernames",entity.getComeback().getName() );
                        editor.putString( "telphone",mTelEt.getText().toString() );
                        editor.putString( "password",mPwdEt.getText().toString() );
                        editor.commit();
                        SPUtil.saveObjectToShare(AppConstant.USER_MSG, entity);

                        mViewModel.setLoadingState(false);

                        LoginInfo info = new LoginInfo(entity.getComeback().getAccid(), entity.getComeback().getImToken()); // config...

                        NIMClient.getService(AuthService.class).login(info)
                                .setCallback(callback);

                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mViewModel.setLoadingState(false);
                    }
                });
                break;
        }
    }

    RequestCallback<LoginInfo> callback =
            new RequestCallback<LoginInfo>() {
                // 可以在此保存LoginInfo到本地，下次启动APP做自动登录用

                @Override
                public void onSuccess(LoginInfo param) {
                    finish();
                    startActivity(MainActivity.class);

                }

                @Override
                public void onFailed(int code) {

                }

                @Override
                public void onException(Throwable exception) {

                }
            };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Constant.INDEX = 0;
        EventBus.getDefault().unregister(this);
    }

}
