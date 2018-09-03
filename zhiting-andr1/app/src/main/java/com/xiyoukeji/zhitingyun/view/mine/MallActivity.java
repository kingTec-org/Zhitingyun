package com.xiyoukeji.zhitingyun.view.mine;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xiyoukeji.zhitingyun.R;
import com.xiyoukeji.zhitingyun.ViewModelFactory;
import com.xiyoukeji.zhitingyun.WebActivity0;
import com.xiyoukeji.zhitingyun.adapter.DoctorAdapter;
import com.xiyoukeji.zhitingyun.adapter.MallAdapter;
import com.xiyoukeji.zhitingyun.base.BaseActivity;
import com.xiyoukeji.zhitingyun.data.entity.AppointmentEntity;
import com.xiyoukeji.zhitingyun.data.entity.ListModel;
import com.xiyoukeji.zhitingyun.data.entity.MallEntity;
import com.xiyoukeji.zhitingyun.data.entity.Model0;
import com.xiyoukeji.zhitingyun.data.entity.Professor;
import com.xiyoukeji.zhitingyun.data.remote.BaseObserver;
import com.xiyoukeji.zhitingyun.databinding.ActivityMallBinding;

import com.xiyoukeji.zhitingyun.viewmodel.main.MainViewModel;
import com.xiyoukeji.zhitingyun.widget.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class MallActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener{

    @BindView( R.id.mall_rv )
    RecyclerView mRv;
    @BindView( R.id.mall_swipe )
    SwipeRefreshLayout mSwipeRefreshLayout;


    private MainViewModel mViewModel;
    private ActivityMallBinding mBinding;
    private MallAdapter mAdapter;
    private int pageNo = 1;
    private final int pageSize = 10;
    List<MallEntity> list = new ArrayList<>();






    public MallActivity() {
        super( R.layout.activity_mall );
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        int from = getIntent().getIntExtra( "from", 8 );

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR );
            StatusBarUtil.setStatusBarColor( this, R.color.white );
        }
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
    protected void bindingData() {
        super.bindingData();
        mBinding = DataBindingUtil.setContentView(mActivity, layoutId);
    }

    @Override
    protected void initView() {
        super.initView();
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mRv.setLayoutManager( new LinearLayoutManager( this ) );
//        List<MallEntity> list = new ArrayList<>();
        mAdapter = new MallAdapter( list );
        mAdapter.bindToRecyclerView( mRv );
        mAdapter.setOnItemClickListener( new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MallEntity mall = (MallEntity) adapter.getItem(position);
                WebActivity0.runActivity(mContext, "又听商城","http://"+mall.getLink());
                mAdapter.notifyItemChanged(position);
            }
        } );
    }

    private void getData(){

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
        getData();


    }

    public void obtainViewModel() {
        ViewModelFactory factory = ViewModelFactory.getInstance(mActivity.getApplication());
        mViewModel = ViewModelProviders.of(this, factory).get(MainViewModel.class);
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing( true );
        mViewModel.getMall( pageNo,10,true, new BaseObserver<ListModel<List<MallEntity>>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable( d );
            }

            @Override
            public void onNext(ListModel<List<MallEntity>> listModel0) {
                mSwipeRefreshLayout.setRefreshing(false);
                mViewModel.mMall.clear();
                if (listModel0.getComeback() != null) {
                    mViewModel.mMall.addAll( listModel0.getComeback() );
                    if (listModel0.getComeback().size() == 0) {
                        View emptyView = LayoutInflater.from( mContext ).inflate( R.layout.empty_view, null );
                        mAdapter.setEmptyView( emptyView );
                    }
                }
                else { View emptyView = LayoutInflater.from( mContext ).inflate( R.layout.empty_view, null );
                    mAdapter.setEmptyView( emptyView );
                }
            }
            @Override
            public void onError(Throwable e) {
                super.onError(e);
                mSwipeRefreshLayout.setRefreshing(false);
            }

        } );

    }

}
