package com.xiyoukeji.zhitingyun.view.main;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xiyoukeji.zhitingyun.R;
import com.xiyoukeji.zhitingyun.ViewModelFactory;
import com.xiyoukeji.zhitingyun.adapter.TestRecordAdapter;
import com.xiyoukeji.zhitingyun.base.BaseActivity;
import com.xiyoukeji.zhitingyun.data.entity.Model0;
import com.xiyoukeji.zhitingyun.data.entity.TestRecordEntity;
import com.xiyoukeji.zhitingyun.data.remote.BaseObserver;
import com.xiyoukeji.zhitingyun.viewmodel.login.LoginViewModel;
import com.xiyoukeji.zhitingyun.viewmodel.main.MainViewModel;
import com.xiyoukeji.zhitingyun.widget.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class TestRecordActivity extends BaseActivity {
    @BindView(R.id.test_record_rv)
    RecyclerView rv;
    private MainViewModel mViewModel;
    TestRecordAdapter adapter;
    List<TestRecordEntity> list = new ArrayList<>();

    public TestRecordActivity() {
        super(R.layout.activity_test_record);
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            StatusBarUtil.setStatusBarColor(this, R.color.white); }
    }

    @Override
    protected void initView() {
        super.initView();
        ViewModelFactory factory = ViewModelFactory.getInstance(mActivity.getApplication());
        mViewModel = ViewModelProviders.of(this, factory).get(MainViewModel.class);
//        mViewModel = MainActivity.obtainViewModel(TestRecordActivity.this);
        rv.setLayoutManager(new LinearLayoutManager(TestRecordActivity.this));
        adapter = new TestRecordAdapter(list);
        adapter.bindToRecyclerView(rv);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(TestRecordActivity.this, HearingActivity.class
                ).putExtra("data", list.get(position)));
            }
        });
        getData();

    }

    private void getData() {
        mViewModel.getRecordList(new BaseObserver<Model0<List<TestRecordEntity>>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(Model0<List<TestRecordEntity>> listModel0) {
                list.clear();
                list.addAll(listModel0.getComeback());
                adapter.notifyDataSetChanged();
            }
        });


    }
}


