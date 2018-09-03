package com.xiyoukeji.zhitingyun.view.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xiyoukeji.zhitingyun.R;
import com.xiyoukeji.zhitingyun.adapter.DoctorAdapter;
import com.xiyoukeji.zhitingyun.base.BaseActivity;
import com.xiyoukeji.zhitingyun.base.BaseFragment;
import com.xiyoukeji.zhitingyun.data.entity.AppointmentEntity;
import com.xiyoukeji.zhitingyun.data.entity.ListModel;
import com.xiyoukeji.zhitingyun.data.remote.BaseObserver;
import com.xiyoukeji.zhitingyun.viewmodel.main.MainViewModel;
import com.xiyoukeji.zhitingyun.databinding.FragmentExpertDateBinding;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.disposables.Disposable;

public class ExpertDateFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{


    @BindView(R.id.expert_rv)
    RecyclerView mExpertRv;
    @BindView(R.id.appointments_swipe)
    SwipeRefreshLayout mSwipeRefreshLayout;



    private MainViewModel mViewModel;
    private FragmentExpertDateBinding mBinding;
    private DoctorAdapter mAdapter;

    private String name="";
    private String classify="1";



    @Override
    protected int getLayoutId() {
        return R.layout.fragment_expert_date;
    }

    public static Fragment newInstance(String title) {
        ExpertDateFragment fragment = new ExpertDateFragment();
        Bundle bundle = new Bundle();
        bundle.putString( "data", title );
        fragment.setArguments( bundle );
        return fragment;
    }

    @Override
    protected View bindingData(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentExpertDateBinding.inflate( inflater, container, false );
        mViewModel = MainActivity.obtainViewModel( getActivity() );
        mBinding.setViewModel( mViewModel );
        return mBinding.getRoot();
    }


    @Override
    protected void initView(View view) {
        super.initView( view );
        mSwipeRefreshLayout.setOnRefreshListener( this );
        mExpertRv.setLayoutManager( new LinearLayoutManager( mContext ) );
        List<AppointmentEntity> list = new ArrayList<>();
        mAdapter = new DoctorAdapter( list );
        mAdapter.bindToRecyclerView( mExpertRv );
        mAdapter.setOnItemClickListener( new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public int hashCode() {
//                return super.hashCode();
//            }

            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                AppointmentEntity entity = (AppointmentEntity) adapter.getItem( position );
                ((BaseActivity) getActivity()).startActivity( ExpertDateActivity.class, entity );
            }
        } );

    }




    @Override
    protected void initData() {
        super.initData();
        onRefresh();
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing( true );
        name="";
        mViewModel.onAppointmentRefresh( name, classify, new BaseObserver<ListModel<List<AppointmentEntity>>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable( d );
            }

            @Override
            public void onNext(ListModel<List<AppointmentEntity>> listListModel) {
                mSwipeRefreshLayout.setRefreshing( false );
                mViewModel.mAppointment.clear();
                mViewModel.mAppointment.addAll( listListModel.getComeback() );
                if(listListModel.getComeback().size()==0){
                    View emptyView=LayoutInflater.from( mContext ).inflate( R.layout.empty_view,null );
                    mAdapter.setEmptyView( emptyView );
                }
            }
            @Override
            public void onError(Throwable e){
                super.onError( e );
                mSwipeRefreshLayout.setRefreshing( false );
            }
        } );
    }
}
