package com.xiyoukeji.zhitingyun.view.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xiyoukeji.zhitingyun.R;
import com.xiyoukeji.zhitingyun.ViewModelFactory;
import com.xiyoukeji.zhitingyun.adapter.SchedualAdapter;
import com.xiyoukeji.zhitingyun.base.BaseActivity;
import com.xiyoukeji.zhitingyun.constant.AppConstant;
import com.xiyoukeji.zhitingyun.constant.Urls;
import com.xiyoukeji.zhitingyun.data.entity.ExpertSchEntity;
import com.xiyoukeji.zhitingyun.data.entity.Model0;
import com.xiyoukeji.zhitingyun.data.entity.Professor;
import com.xiyoukeji.zhitingyun.data.remote.BaseObserver;
import com.xiyoukeji.zhitingyun.viewmodel.main.AppointmentViewModel;
import com.xiyoukeji.zhitingyun.databinding.ActivityExpertSchedualBinding;
import com.xiyoukeji.zhitingyun.widget.RoundImageView;
import com.xiyoukeji.zhitingyun.widget.StatusBarUtil;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class ExpertDateActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.appointments_swipe)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.appointment_Rv)
    RecyclerView mRv;
    @BindView(R.id.expertName)
    TextView tvName;
    @BindView(R.id.experience_time)
    TextView mExperirnce;
    @BindView(R.id.portrait_imageview)
    RoundImageView roundImageView;


    private AppointmentViewModel mViewModel;
    private ActivityExpertSchedualBinding mBinding;
    private SchedualAdapter mAdapter;


    private Integer id;
    Professor professor;


    public ExpertDateActivity() {
        super(R.layout.activity_expert_schedual);
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

    @Override
    protected void initView() {
        super.initView();
        professor = (Professor) getIntent().getSerializableExtra("data");
        if(professor!=null) {
            id = professor.getId();
            mSwipeRefreshLayout.setOnRefreshListener( this );
            mRv.setLayoutManager( new LinearLayoutManager( this ) );
            List<ExpertSchEntity.WorkScheduleDtosBean> list = new ArrayList<>();
            mAdapter = new SchedualAdapter( list );
            mRv.setAdapter( mAdapter );
            Glide.with( mContext ).load( Urls.BASE_URL + "/" + professor.getHeadPic() ).into( roundImageView );
            tvName.setText( professor.getName() );
            mExperirnce.setText( professor.getWorkTime() + "个月" );

            mAdapter.setOnItemClickListener( new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    ExpertSchEntity.WorkScheduleDtosBean entity = (ExpertSchEntity.WorkScheduleDtosBean) adapter.getItem( position );
                    startActivity( new Intent( ExpertDateActivity.this, DateMainActivity.class ).putExtra( "data", entity ) );
                }
            } );
        }
        else {
            Intent intent = getIntent();
            id = intent.getIntExtra("2",1);
            mSwipeRefreshLayout.setOnRefreshListener( this );
            mRv.setLayoutManager( new LinearLayoutManager( this ) );
            List<ExpertSchEntity.WorkScheduleDtosBean> list = new ArrayList<>();
            mAdapter = new SchedualAdapter( list );
            mRv.setAdapter( mAdapter );

            Glide.with( mContext ).load( Urls.BASE_URL + "/" + intent.getStringExtra( "3" ) ).into( roundImageView );
            tvName.setText( intent.getStringExtra( "4" ) );
            mExperirnce.setText( intent.getIntExtra( "5" ,1) + "个月" );

            mAdapter.setOnItemClickListener( new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    ExpertSchEntity.WorkScheduleDtosBean entity = (ExpertSchEntity.WorkScheduleDtosBean) adapter.getItem( position );
                    startActivity( new Intent( ExpertDateActivity.this, DateMainActivity.class ).putExtra( "data", entity ) );
                }
            } );
        }

    }

    @Override
    protected void initData() {
        super.initData();
        obtainViewModel();
        onRefresh();
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
        mViewModel = ViewModelProviders.of(this, factory).get(AppointmentViewModel.class);
    }

    @OnClick({R.id.back_layout, R.id.appointment_date})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_layout:
                finish();
                break;
            case R.id.appointment_date:
//                startActivity( AppointmentListActivity.class );
                break;
        }
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
//        id=mViewModel
        mViewModel.onSchedualRefresh(id, new BaseObserver<Model0<ExpertSchEntity>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(Model0<ExpertSchEntity> workScheduleDtosBeanModel0) {
                mSwipeRefreshLayout.setRefreshing(false);
                mViewModel.mSchedual.clear();
                if (workScheduleDtosBeanModel0.getComeback().getWorkScheduleDtos() != null)
                    mViewModel.mSchedual.addAll(workScheduleDtosBeanModel0.getComeback().getWorkScheduleDtos());
                if (mViewModel.mSchedual.size() == 0) {
                    View emptyView = LayoutInflater.from(mContext).inflate(R.layout.empty_view, null);
                    mAdapter.setEmptyView(emptyView);
                }

            }

//            @Override

        });

    }
}
