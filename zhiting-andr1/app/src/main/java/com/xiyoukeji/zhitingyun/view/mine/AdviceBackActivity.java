package com.xiyoukeji.zhitingyun.view.mine;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.xiyoukeji.zhitingyun.R;
import com.xiyoukeji.zhitingyun.ViewModelFactory;
import com.xiyoukeji.zhitingyun.base.BaseActivity;
import com.xiyoukeji.zhitingyun.data.entity.AdviceBackEntity;
import com.xiyoukeji.zhitingyun.data.entity.BaseModel;
import com.xiyoukeji.zhitingyun.data.entity.Model0;
import com.xiyoukeji.zhitingyun.data.entity.OtherEntity;
import com.xiyoukeji.zhitingyun.data.remote.BaseObserver;
import com.xiyoukeji.zhitingyun.databinding.ActivityAdviceBackBinding;
import com.xiyoukeji.zhitingyun.util.ActivityManager;
import com.xiyoukeji.zhitingyun.util.ToastUtils;
import com.xiyoukeji.zhitingyun.view.login.LoginActivity;
import com.xiyoukeji.zhitingyun.viewmodel.mine.AdviceBackViewModel;
import com.xiyoukeji.zhitingyun.widget.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;


public class AdviceBackActivity extends BaseActivity {

    @BindView(R.id.advice_back_et)
    EditText mAdviceBackEt;
    @BindView( R.id.submit )
    Button mSubmit;


    private AdviceBackViewModel mViewModel;
    private ActivityAdviceBackBinding mBinding;
    private String mContent;
    private String mPhone;



    public AdviceBackActivity() {
        super( R.layout.activity_advice_back );
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        int from = getIntent().getIntExtra( "from", 12 );

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            StatusBarUtil.setStatusBarColor(this, R.color.white); }
    }



    @Override
    protected void bindingData() {
        super.bindingData();
        mBinding = DataBindingUtil.setContentView(mActivity, layoutId);
    }

    @OnClick({R.id.back_layout,R.id.submit,R.id.callTv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_layout:
                finish();
                break;
            case R.id.submit:
                mViewModel.adviceBack(mContent,new BaseObserver<Model0<AdviceBackEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(Model0<AdviceBackEntity> adviceBackEntityModel0) {
                        mViewModel.setLoadingState(false);
                        ToastUtils.showShort("反馈成功");
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mViewModel.setLoadingState(false);
                    }
                });
                break;
            case R.id.callTv:
                mViewModel.getOthers( null, new BaseObserver<Model0<OtherEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable( d );
                    }

                    @Override
                    public void onNext(Model0<OtherEntity> otherEntityModel0) {
                        mViewModel.mEntity.set( otherEntityModel0 );
                        mPhone=otherEntityModel0.getComeback().getServiceTel();
                    }
                } );
                diallPhone( mPhone );
                break;
        }
    }

    public void diallPhone(String phoneNum) {
        Intent intent = new Intent( Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
    }

    @Override
    protected void initData() {
        super.initData();
        obtainViewModel();
        mBinding.setViewModel(mViewModel);
        mViewModel.addSizeCallBack();
    }

    public void obtainViewModel() {
        ViewModelFactory factory = ViewModelFactory.getInstance(mActivity.getApplication());
        mViewModel = ViewModelProviders.of(this, factory).get(AdviceBackViewModel.class);
    }
}