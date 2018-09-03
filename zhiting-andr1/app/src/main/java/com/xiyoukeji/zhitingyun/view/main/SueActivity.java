package com.xiyoukeji.zhitingyun.view.main;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.xiyoukeji.zhitingyun.R;
import com.xiyoukeji.zhitingyun.ViewModelFactory;
import com.xiyoukeji.zhitingyun.base.BaseActivity;
import com.xiyoukeji.zhitingyun.data.entity.AdviceBackEntity;
import com.xiyoukeji.zhitingyun.data.entity.Model0;
import com.xiyoukeji.zhitingyun.data.entity.OtherEntity;
import com.xiyoukeji.zhitingyun.data.entity.RecordEntity;
import com.xiyoukeji.zhitingyun.data.remote.BaseObserver;
import com.xiyoukeji.zhitingyun.databinding.ActivityComplaintsBinding;
import com.xiyoukeji.zhitingyun.util.ToastUtils;
import com.xiyoukeji.zhitingyun.viewmodel.main.SueViewModel;
import com.xiyoukeji.zhitingyun.viewmodel.mine.AdviceBackViewModel;
import com.xiyoukeji.zhitingyun.widget.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class SueActivity extends BaseActivity {
    @BindView(R.id.sue_et)
    EditText mSueEt;
    @BindView( R.id.submit )
    Button btnSub;
    @BindView(R.id.doctor_name  )
    TextView docName;
    @BindView( R.id.call )
    TextView mCall;

    private SueViewModel mViewModel;
    private ActivityComplaintsBinding mBinding;
    private Integer mProId;
    private String mContent;
    RecordEntity record;
    private String mPhone;




    public SueActivity() {
        super( R.layout.activity_complaints );
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

    @OnClick({R.id.back_layout,R.id.submit,R.id.call})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_layout:
                finish();
                break;
            case R.id.submit:
                record=(RecordEntity) getIntent().getSerializableExtra( "data" );

                if(record!=null) {
                    mProId = record.getProId();
                    mViewModel.sueExpert( mProId, mContent, new BaseObserver<Model0<RecordEntity>>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            addDisposable( d );
                        }

                        @Override
                        public void onNext(Model0<RecordEntity> sueEntityModel0) {
                            mViewModel.setLoadingState( false );
                            ToastUtils.showShort( "投诉成功" );
                            finish();
                        }

                        @Override
                        public void onError(Throwable e) {
                            super.onError( e );
                            mViewModel.setLoadingState( false );
                        }
                    } );
                }else{
                    Intent intent=getIntent();
                    mProId=intent.getIntExtra( "evaluateId",1 );
                    mViewModel.sueExpert( mProId, mContent, new BaseObserver<Model0<RecordEntity>>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            addDisposable( d );
                        }

                        @Override
                        public void onNext(Model0<RecordEntity> sueEntityModel0) {
                            mViewModel.setLoadingState( false );
                            ToastUtils.showShort( "投诉成功" );
                            finish();
                        }

                        @Override
                        public void onError(Throwable e) {
                            super.onError( e );
                            mViewModel.setLoadingState( false );
                        }
                    } );
                }
                break;
            case R.id.call:
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

    @Override
    protected void initData() {
        super.initData();
        obtainViewModel();
        mBinding.setViewModel(mViewModel);
        mViewModel.addSizeCallBack();
        record=(RecordEntity) getIntent().getSerializableExtra( "data" );
        if(record!=null) {
            docName.setText( record.getProfessorName() );
        }else {
            Intent intent = getIntent();
            docName.setText( intent.getStringExtra( "evaluateDocName" ) );
        }

    }

    public void diallPhone(String phoneNum) {
        Intent intent = new Intent( Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
    }

    public void obtainViewModel() {
        ViewModelFactory factory = ViewModelFactory.getInstance(mActivity.getApplication());
        mViewModel = ViewModelProviders.of(this, factory).get(SueViewModel.class);
    }

}
