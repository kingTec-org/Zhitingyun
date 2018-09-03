package com.xiyoukeji.zhitingyun.view.mine;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xiyoukeji.zhitingyun.R;
import com.xiyoukeji.zhitingyun.ViewModelFactory;
import com.xiyoukeji.zhitingyun.base.BaseActivity;
import com.xiyoukeji.zhitingyun.constant.AppConstant;
import com.xiyoukeji.zhitingyun.data.entity.BaseModel0;
import com.xiyoukeji.zhitingyun.data.entity.LoginEntity;
import com.xiyoukeji.zhitingyun.data.entity.Model0;
import com.xiyoukeji.zhitingyun.data.remote.BaseObserver;
import com.xiyoukeji.zhitingyun.databinding.ActivityChangePhoneBinding;
import com.xiyoukeji.zhitingyun.util.ToastUtils;
import com.xiyoukeji.zhitingyun.viewmodel.mine.ChangePhoneViewModel;
import com.xiyoukeji.zhitingyun.widget.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;


public class ChangePhoneActivity extends BaseActivity {

    @BindView( R.id.btn_confirm )
    Button btnConfirm;
    @BindView(R.id.send_code_tv)
    TextView mSendCodeTv;
    private String mPhone;
    private String mCode;
    private CountDownTimer mTimer;
    private ActivityChangePhoneBinding mBinding;
    private ChangePhoneViewModel mViewModel;
    private final int CHANGE_PHONE = 0x001;

    public ChangePhoneActivity() {
        super(R.layout.activity_change_phone);
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
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR );
            StatusBarUtil.setStatusBarColor( this, R.color.white );
        }
    }

    @Override
    protected void initView() {
        super.initView();


    }

    @Override
    protected void initData() {
        super.initData();
        mBinding.setViewModel(mViewModel);
        mPhone = getIntent().getStringExtra( AppConstant.DATA);
        obtainViewModel();
        mBinding.setViewModel(mViewModel);
        mSendCodeTv.performClick();
    }

    public void obtainViewModel() {
        ViewModelFactory factory = ViewModelFactory.getInstance(mActivity.getApplication());
        mViewModel = ViewModelProviders.of(this, factory).get(ChangePhoneViewModel.class);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == CHANGE_PHONE) {
            finish();
        }
    }

    @OnClick({R.id.back_layout,R.id.send_code_tv,R.id.btn_confirm})
    public void onViewClicked(View view){
        switch (view.getId()) {
            case R.id.back_layout:
                finish();
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
//                if (mPhone != null) {
                    mViewModel.changePhone(mPhone,mCode, new BaseObserver<Model0<LoginEntity>>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            addDisposable(d);
                        }

                        @Override
                        public void onNext(Model0<LoginEntity> loginEntityModel0) {
                            mViewModel.setLoadingState(false);
                            ToastUtils.showLong( "修改成功" );
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
