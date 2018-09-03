package com.xiyoukeji.zhitingyun.view.mine;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
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
import com.xiyoukeji.zhitingyun.data.remote.BaseObserver;
import com.xiyoukeji.zhitingyun.databinding.ActivitySettingBinding;
import com.xiyoukeji.zhitingyun.util.ActivityManager;
import com.xiyoukeji.zhitingyun.util.SPUtil;
import com.xiyoukeji.zhitingyun.view.login.LoginActivity;
import com.xiyoukeji.zhitingyun.viewmodel.mine.SettingViewModel;
import com.xiyoukeji.zhitingyun.widget.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;



public class SettingActivity extends BaseActivity {


    @BindView( R.id.btn_logout )
    Button logoutBtn;
    private ActivitySettingBinding mBinding;
    private SettingViewModel mViewModel;

    public SettingActivity() {
        super(R.layout.activity_setting);
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
    protected void bindingData() {
        super.bindingData();
        mBinding = DataBindingUtil.setContentView(mActivity, layoutId);
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
        mViewModel = ViewModelProviders.of(this, factory).get(SettingViewModel.class);
    }

    @OnClick({R.id.back_layout,R.id.change_phone_layout,R.id.change_password_layout,R.id.btn_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_layout:
                finish();
                break;
            case R.id.change_phone_layout:
                startActivity( ChangePhoneActivity.class );
                break;
            case R.id.change_password_layout:
                startActivity( ChangePwdActivity.class );
                break;
            case R.id.btn_logout:
                mViewModel.logout(new BaseObserver<BaseModel0>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(BaseModel0 baseModel) {
                        mViewModel.setLoadingState(false);
                        finishAll();
                        startActivity(LoginActivity.class);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mViewModel.setLoadingState(false);
                        finishAll();
                        startActivity(LoginActivity.class);
                    }
                });
                break;
        }
    }
}
