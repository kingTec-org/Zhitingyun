package com.xiyoukeji.zhitingyun.view.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.xiyoukeji.zhitingyun.R;
import com.xiyoukeji.zhitingyun.ViewModelFactory;
import com.xiyoukeji.zhitingyun.base.BaseActivity;
import com.xiyoukeji.zhitingyun.constant.AppConstant;
import com.xiyoukeji.zhitingyun.data.entity.SubmitEntity;
import com.xiyoukeji.zhitingyun.databinding.ActivityAppointmentListBinding;
import com.xiyoukeji.zhitingyun.viewmodel.main.SubmitViewModel;

import butterknife.BindView;
import butterknife.OnClick;

public class AppointmentListActivity extends BaseActivity {
    @BindView( R.id.time_textview )
    TextView mTime;
    @BindView( R.id.desc )
    EditText mDesc;
    @BindView( R.id.submit )
    Button mSubmit;

    private ActivityAppointmentListBinding mBinding;
    private SubmitViewModel mViewModel;


    public AppointmentListActivity() {
        super( R.layout.activity_appointment_list );
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
        mBinding.setViewModel( mViewModel );
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

        SubmitEntity entity = (SubmitEntity) getIntent().getSerializableExtra( AppConstant.SUBMIT);
        if (entity != null) {

        }

    }

    public void obtainViewModel() {
        ViewModelFactory factory = ViewModelFactory.getInstance(mActivity.getApplication());
        mViewModel = ViewModelProviders.of(this, factory).get(SubmitViewModel.class);
    }

        @OnClick({R.id.back_layout})
    public void onViewClicked(View view){
        switch (view.getId()){
            case R.id.back_layout:
                finish();
                break;
        }
    }
}
