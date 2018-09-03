package com.xiyoukeji.zhitingyun.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xiyoukeji.zhitingyun.R;
import com.xiyoukeji.zhitingyun.adapter.RecordAdapter;
import com.xiyoukeji.zhitingyun.base.BaseActivity;
import com.xiyoukeji.zhitingyun.base.BaseFragment;
import com.xiyoukeji.zhitingyun.data.entity.ListModel;
import com.xiyoukeji.zhitingyun.data.entity.Model0;
import com.xiyoukeji.zhitingyun.data.entity.RecordEntity;
import com.xiyoukeji.zhitingyun.data.remote.BaseObserver;
import com.xiyoukeji.zhitingyun.databinding.FragmentRecordBinding;
import com.xiyoukeji.zhitingyun.util.ToastUtils;
import com.xiyoukeji.zhitingyun.viewmodel.main.MainViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.disposables.Disposable;

public class RecordFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.record_rv)
    RecyclerView mRecordRv;
    @BindView(R.id.record_swipe)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private MainViewModel mViewModel;
    private FragmentRecordBinding mBinding;
    private RecordAdapter mAdapter;
    private Integer mId = 0;
    private Integer classify;
    private Integer orderId;
    private Integer proId;
    private String name;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_record;
    }

    public static Fragment newInstance(String title) {
        RecordFragment fragment = new RecordFragment();
        Bundle bundle = new Bundle();
        bundle.putString("data", title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected View bindingData(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentRecordBinding.inflate(inflater, container, false);
        mViewModel = MainActivity.obtainViewModel(getActivity());
        mBinding.setViewModel(mViewModel);
        return mBinding.getRoot();
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecordRv.setLayoutManager(new LinearLayoutManager(mContext));
        List<RecordEntity> list = new ArrayList<>();
        mAdapter = new RecordAdapter(list);
        mAdapter.bindToRecyclerView(mRecordRv);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.btn_reason:
                        RecordEntity entity4=(RecordEntity)adapter.getItem( position );
                        ((BaseActivity) getActivity()).startActivity( TreatmentReportActivity.class, entity4 );
                        break;
                    case R.id.btn_check_report:
                        RecordEntity entity=(RecordEntity)adapter.getItem( position );
                        ((BaseActivity) getActivity()).startActivity( TreatmentReportActivity.class, entity );
                        break;
                    case R.id.btn_retreat:
                        RecordEntity entity3=(RecordEntity)adapter.getItem( position );
                        Intent intent=new Intent(mContext,ExpertDateActivity.class);
                        intent.putExtra( "2", entity3.getProId());
                        intent.putExtra( "3",entity3.getProHeadPic() );
                        intent.putExtra( "4",entity3.getProfessorName() );
                        intent.putExtra( "5",entity3.getWorkMonth() );
                        ((BaseActivity) getActivity()).startActivity( intent );
                        break;
                    case R.id.btn_finish:
                        RecordEntity entity0=(RecordEntity)adapter.getItem( position );
                        orderId=entity0.getId();
                        classify=entity0.getClassify();
                        mViewModel.cancelOrder( classify, orderId, new BaseObserver<Model0<RecordEntity>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                addDisposable( d );
                            }

                            @Override
                            public void onNext(Model0<RecordEntity> recordEntityModel0) {
                                mViewModel.setLoadingState(false);
                                ToastUtils.showShort("取消成功");
                                onRefresh();
                            }
                        } );
                        break;
                    case R.id.btn_cancel:
                        RecordEntity entity1=(RecordEntity)adapter.getItem( position );
                        orderId=entity1.getId();
                        mViewModel.cancelDate( orderId, 2,new BaseObserver<Model0<RecordEntity>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                addDisposable( d );
                            }

                            @Override
                            public void onNext(Model0<RecordEntity> recordEntityModel0) {
                                mViewModel.setLoadingState(false);
                                ToastUtils.showShort("取消成功");
                                onRefresh();
                            }
                        } );
                        break;
                    case R.id.btn_sue:
                        RecordEntity entity2=(RecordEntity)adapter.getItem( position );
                        proId=entity2.getProId();

                        ((BaseActivity) getActivity()).startActivity( SueActivity.class, entity2 );
                        break;

                }
            }
        });

    }

    @Override
    protected void initData() {
        super.initData();
        onRefresh();
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        mViewModel.onRecordRefresh(mId, new BaseObserver<ListModel<List<RecordEntity>>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(ListModel<List<RecordEntity>> listListModel) {
                mSwipeRefreshLayout.setRefreshing(false);
                mViewModel.mRecord.clear();
                if (listListModel.getComeback() != null) {
                    mViewModel.mRecord.addAll(listListModel.getComeback());
                    if (listListModel.getComeback().size() == 0) {
                        View emptyView = LayoutInflater.from(mContext).inflate(R.layout.empty_view, null);
                        mAdapter.setEmptyView(emptyView);
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
