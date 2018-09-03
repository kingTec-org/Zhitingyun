package com.xiyoukeji.zhitingyun.view.mine;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.xiyoukeji.zhitingyun.R;
import com.xiyoukeji.zhitingyun.ViewModelFactory;
import com.xiyoukeji.zhitingyun.base.BaseActivity;
import com.xiyoukeji.zhitingyun.data.entity.LoginEntity;
import com.xiyoukeji.zhitingyun.data.entity.Model0;
import com.xiyoukeji.zhitingyun.data.remote.BaseObserver;
import com.xiyoukeji.zhitingyun.databinding.ActivityChangePasswordBinding;
import com.xiyoukeji.zhitingyun.util.ToastUtils;
import com.xiyoukeji.zhitingyun.viewmodel.mine.ChangePwdViewModel;
import com.xiyoukeji.zhitingyun.widget.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;



public class ChangePwdActivity extends BaseActivity {


    @BindView( R.id.btn_confirm)
    Button btnConfirm;
    private ActivityChangePasswordBinding mBinding;
    private ChangePwdViewModel mViewModel;

    public ChangePwdActivity() {
        super(R.layout.activity_change_password);
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

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.changePwd(new BaseObserver<Model0<LoginEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(Model0<LoginEntity> loginEntityModel0) {
                        mViewModel.setLoadingState(false);
                        ToastUtils.showShort("修改密码成功");
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mViewModel.setLoadingState(false);
                    }
                });
            }
        });
    }

    @OnClick({R.id.back_layout})
    public void onViewClicked(View view){
        switch (view.getId()){
            case R.id.back_layout:
                finish();
                break;
        }
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
        mViewModel = ViewModelProviders.of(this, factory).get(ChangePwdViewModel.class);
    }
}
