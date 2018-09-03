package com.xiyoukeji.zhitingyun.view.main;

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
import com.xiyoukeji.zhitingyun.data.entity.RecordEntity;
import com.xiyoukeji.zhitingyun.data.remote.BaseObserver;
import com.xiyoukeji.zhitingyun.databinding.ActivityReport0Binding;
import com.xiyoukeji.zhitingyun.util.TimeUtils;
import com.xiyoukeji.zhitingyun.viewmodel.main.ReportViewModel;
import com.xiyoukeji.zhitingyun.widget.StatusBarUtil;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class TreatmentQuickReportActivity extends BaseActivity {
    @BindView( R.id.treat_start )
    TextView mStart;
    @BindView( R.id.treat_end )
    TextView mEnd;
    @BindView( R.id.doctor_name )
    TextView mDoc;
    @BindView( R.id.patient_name )
    TextView mPatient;
    @BindView( R.id.problem )
    TextView mPro;
    @BindView( R.id.reason )
    TextView mReason;

    private ReportViewModel mViewModel;
    private ActivityReport0Binding mBinding;
    private Integer id;
    RecordEntity record;

    public TreatmentQuickReportActivity() {
        super( R.layout.activity_report0 );
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

    @OnClick({R.id.back_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_layout:
                finish();
                break;
        }
    }

    @Override
    protected void initView() {
        super.initView();


    }

    @Override
    protected void initData() {
        super.initData();
        obtainViewModel();
        mBinding.setViewModel( mViewModel );
        mViewModel.getLoadingState().observe( this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean) {
                    showLoading(mViewModel.mTip.get());
                } else {
                    dismissLoading();
                }
            }
        } );

        record=(RecordEntity) getIntent().getSerializableExtra( "data" );
        if (record != null) {

            id = record.getId();
            mStart.setText( TimeUtils.millis2String( record.getCureTimeStart(), new SimpleDateFormat( "yyyy-MM-dd HH:mm" ) ) );
            mEnd.setText( TimeUtils.millis2String( record.getCureTimeEnd(), new SimpleDateFormat( "HH:mm" ) ) );

            mPatient.setText( record.getUserName() );
            mDoc.setText( record.getProName() );

            mViewModel.getQuickReport( id, new BaseObserver<Model0<RecordEntity>>() {
                @Override
                public void onSubscribe(Disposable d) {
                    addDisposable( d );
                }

                @Override
                public void onNext(Model0<RecordEntity> recordEntityModel0) {
                    mViewModel.mEntity.set(recordEntityModel0);

                    mPro.setText( recordEntityModel0.getComeback().getProblemDesc() );
                    mReason.setText( recordEntityModel0.getComeback().getProfessorReport() );

                }

            } );
        }

    }



    public void obtainViewModel() {
        ViewModelFactory factory = ViewModelFactory.getInstance(mActivity.getApplication());
        mViewModel = ViewModelProviders.of(this, factory).get(ReportViewModel.class);
    }
}
