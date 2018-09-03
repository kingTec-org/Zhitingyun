package com.xiyoukeji.zhitingyun.view.login;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xiyoukeji.zhitingyun.R;
import com.xiyoukeji.zhitingyun.ViewModelFactory;
import com.xiyoukeji.zhitingyun.base.BaseActivity;
import com.xiyoukeji.zhitingyun.constant.AppConstant;
import com.xiyoukeji.zhitingyun.data.entity.BaseModel;
import com.xiyoukeji.zhitingyun.data.entity.BaseModel0;
import com.xiyoukeji.zhitingyun.data.entity.LoginEntity;
import com.xiyoukeji.zhitingyun.data.entity.Model0;
import com.xiyoukeji.zhitingyun.data.remote.BaseObserver;
import com.xiyoukeji.zhitingyun.databinding.ActivityForgetPasswordCodeBinding;
import com.xiyoukeji.zhitingyun.util.ToastUtils;
import com.xiyoukeji.zhitingyun.view.main.MainActivity;
import com.xiyoukeji.zhitingyun.viewmodel.login.ForgetPwdViewModel;
import com.xiyoukeji.zhitingyun.widget.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;



public class ForgetPwdActivity extends BaseActivity {


    @BindView(R.id.send_code_tv)
    TextView mSendCodeTv;
    @BindView( R.id.btn_confirm )
    Button confirmBtn;
    private ActivityForgetPasswordCodeBinding mBinding;
    private ForgetPwdViewModel mViewModel;
    private CountDownTimer mTimer;
    private String mPhone;
    private String mPassword;
    private String mCode;

    public ForgetPwdActivity() {
        super(R.layout.activity_forget_password_code);
    }

    @Override
    protected void bindingData() {
        super.bindingData();
        mBinding = DataBindingUtil.setContentView(mActivity, layoutId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            StatusBarUtil.setStatusBarColor(this, R.color.white); }
    }

    @Override
    protected void initView() {
        super.initView();
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
        mViewModel = ViewModelProviders.of(this, factory).get(ForgetPwdViewModel.class);
    }

    @OnClick({R.id.back_layout, R.id.send_code_tv,R.id.register_textview,R.id.btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_layout:
                finish();
                break;
            case R.id.register_textview:
                startActivity( LoginActivity.class );
                break;
            case R.id.send_code_tv:
                mViewModel.mTip.set("发送验证码中...");
                mViewModel.sendCode(new BaseObserver<BaseModel0>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(BaseModel0 baseModel) {
                        mViewModel.getLoadingState().setValue(false);
                        ToastUtils.showShort("发送验证码成功");
                        mTimer = new CountDownTimer(60000L, 1000L) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                mSendCodeTv.setText(millisUntilFinished / 1000 + "s");
                            }

                            @Override
                            public void onFinish() {
                                mSendCodeTv.setClickable(true);
                                mSendCodeTv.setTextColor(getResources().getColor(R.color.text_CC1801));
                                mSendCodeTv.setText("发送验证码");
                            }
                        }.start();
                        mSendCodeTv.setClickable(false);
                        mSendCodeTv.setTextColor(getResources().getColor(R.color.text_CCCCCC));
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mViewModel.getLoadingState().setValue(false);
                    }
                });
                break;
            case R.id.btn_confirm:
                mViewModel.updatePwd(mPhone, mCode,mPassword, new BaseObserver<Model0<LoginEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(Model0<LoginEntity> loginEntity) {
                        mViewModel.setLoadingState(false);
                        finish();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTimer != null) {
            mTimer.cancel();
        }
    }
}
