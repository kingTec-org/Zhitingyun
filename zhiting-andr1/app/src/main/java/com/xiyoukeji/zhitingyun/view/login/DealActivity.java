package com.xiyoukeji.zhitingyun.view.login;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.xiyoukeji.zhitingyun.R;
import com.xiyoukeji.zhitingyun.ViewModelFactory;
import com.xiyoukeji.zhitingyun.base.BaseActivity;
import com.xiyoukeji.zhitingyun.data.entity.Model0;
import com.xiyoukeji.zhitingyun.data.entity.OtherEntity;
import com.xiyoukeji.zhitingyun.data.remote.BaseObserver;
import com.xiyoukeji.zhitingyun.databinding.ActivityDealBinding;
import com.xiyoukeji.zhitingyun.viewmodel.main.OthersViewModel;
import com.xiyoukeji.zhitingyun.widget.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class DealActivity extends BaseActivity{
    @BindView( R.id.deal_tv )
    TextView mDeal;

    private ActivityDealBinding mBinding;
    private OthersViewModel mViewModel;

    public DealActivity() {
        super( R.layout.activity_deal );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            StatusBarUtil.setStatusBarColor(this, R.color.white); }
    }

    @Override
    protected void bindingData() {
        super.bindingData();
        mBinding = DataBindingUtil.setContentView(mActivity, layoutId);
    }

    @OnClick({R.id.back_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
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

        mViewModel.getOthers( null, new BaseObserver<Model0<OtherEntity>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable( d );
            }

            @Override
            public void onNext(Model0<OtherEntity> otherEntityModel0) {
                mViewModel.mEntity.set( otherEntityModel0 );
                mDeal.setText( otherEntityModel0.getComeback().getUserAgreement() );
            }
        } );
    }

    public void obtainViewModel() {
        ViewModelFactory factory = ViewModelFactory.getInstance(mActivity.getApplication());
        mViewModel = ViewModelProviders.of(this, factory).get(OthersViewModel.class);
    }
}
