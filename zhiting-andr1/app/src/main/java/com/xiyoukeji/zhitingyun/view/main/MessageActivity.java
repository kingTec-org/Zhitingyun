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

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xiyoukeji.zhitingyun.R;
import com.xiyoukeji.zhitingyun.ViewModelFactory;
import com.xiyoukeji.zhitingyun.adapter.MessageAdapter;
import com.xiyoukeji.zhitingyun.base.BaseActivity;
import com.xiyoukeji.zhitingyun.constant.AppConstant;
import com.xiyoukeji.zhitingyun.data.entity.BaseModel0;
import com.xiyoukeji.zhitingyun.data.entity.ListModel;
import com.xiyoukeji.zhitingyun.data.entity.MessageEntity;
import com.xiyoukeji.zhitingyun.data.entity.Model0;
import com.xiyoukeji.zhitingyun.data.remote.BaseObserver;
import com.xiyoukeji.zhitingyun.databinding.ActivityMessagelistBinding;
import com.xiyoukeji.zhitingyun.util.ActivityManager;
import com.xiyoukeji.zhitingyun.util.Constant;
import com.xiyoukeji.zhitingyun.view.login.LoginActivity;
import com.xiyoukeji.zhitingyun.viewmodel.main.AppointmentViewModel;
import com.xiyoukeji.zhitingyun.viewmodel.main.MessageViewModel;
import com.xiyoukeji.zhitingyun.widget.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class MessageActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.message_swipe)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.message_Rv)
    RecyclerView mRv;


    private MessageViewModel mViewModel;
    private ActivityMessagelistBinding mBinding;
    private MessageAdapter mAdapter;
    private long lastTime;
    private String id="";


    public MessageActivity() {
        super(R.layout.activity_messagelist);
    }

    @Override
    protected void bindingData() {
        super.bindingData();
        mBinding = DataBindingUtil.setContentView(mActivity, layoutId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        int from = getIntent().getIntExtra( "from", 5 );


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            StatusBarUtil.setStatusBarColor(this, R.color.white); }
    }

    @Override
    protected void initView() {
        super.initView();



        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        List<MessageEntity> list = new ArrayList<>();
        mAdapter = new MessageAdapter(list);

        mAdapter.bindToRecyclerView(mRv);

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.detail_layout:
                        MessageEntity entity = (MessageEntity) adapter.getItem( position );
                        entity.getNewsId();
                        entity.getNewsType();

                        startActivity( MessageDetailActivity.class, entity );
                        break;
                    case R.id.content:
                        MessageEntity entity1 = (MessageEntity) adapter.getItem( position );
                        Intent intent=new Intent(MessageActivity.this,TreatmentReportActivity.class);
                        intent.putExtra( "1", entity1.getNewsId());

                        startActivity( intent );
                        break;
                }
            }

//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                MessageEntity entity = (MessageEntity) adapter.getItem(position);
//                startActivity(MessageDetailActivity.class, entity);
//            }
        });


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
        mViewModel = ViewModelProviders.of(this, factory).get(MessageViewModel.class);
    }


    @OnClick({R.id.back_layout,R.id.readAll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_layout:
                finish();
                break;
            case R.id.readAll:
                mViewModel.readAll(id, new BaseObserver<BaseModel0>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable( d );
                    }

                    @Override
                    public void onNext(BaseModel0 baseModel0) {
                        mViewModel.setLoadingState(false);
                    }
                } );
                break;
        }
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        mViewModel.onMessageRefresh(lastTime, new BaseObserver<ListModel<List<MessageEntity>>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(ListModel<List<MessageEntity>> listListModel) {
                mSwipeRefreshLayout.setRefreshing(false);
                mViewModel.mMessage.clear();
                if (listListModel.getComeback() != null) {
                    mViewModel.mMessage.addAll( listListModel.getComeback() );
                    if (listListModel.getComeback().size() == 0) {
                        View emptyView = LayoutInflater.from( mContext ).inflate( R.layout.empty_view, null );
                        mAdapter.setEmptyView( emptyView );
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }


}
