package com.xiyoukeji.zhitingyun.view.main;

import android.app.AlertDialog;
import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.androidkun.xtablayout.XTabLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xiyoukeji.zhitingyun.R;
import com.xiyoukeji.zhitingyun.ViewModelFactory;
import com.xiyoukeji.zhitingyun.adapter.ProfessorListAdapter;
import com.xiyoukeji.zhitingyun.base.BaseActivity;
import com.xiyoukeji.zhitingyun.base.BaseTabAdapter;
import com.xiyoukeji.zhitingyun.data.entity.AppointmentEntity;
import com.xiyoukeji.zhitingyun.data.entity.Model0;
import com.xiyoukeji.zhitingyun.data.entity.Professor;
import com.xiyoukeji.zhitingyun.data.remote.BaseObserver;
import com.xiyoukeji.zhitingyun.databinding.ActivityAppointmentBinding;
import com.xiyoukeji.zhitingyun.viewmodel.main.MainViewModel;
import com.xiyoukeji.zhitingyun.widget.StatusBarUtil;
import com.xiyoukeji.zhitingyun.yunxin.ChatMainActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class AppointmentActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.tab)
    XTabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.select_textview)
    TextView select;
    @BindView(R.id.showoff)
    TextView showoff;
    @BindView(R.id.searchEdit)
    SearchView searchEdit;
    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView( R.id.imgSelect )
    ImageView imgSelect;
    @BindView(R.id.message_swipe)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView( R.id.et_homeFragment )
    EditText mSearch;
    @BindView( R.id.img_clear_homeFragment )
    ImageView mClear;
//    List<Professor> professors = new ArrayList<>();

    private ActivityAppointmentBinding mBinding;
    private MainViewModel mViewModel;

    private PopupMenu popupMenu;
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();
    private AppointmentEntity entity;
    private ProfessorListAdapter professorListAdapter;
    final List<Professor> professors = new ArrayList<>();

    private Dialog mDialog;
    private Integer mClassify=2;
    private String keywords="";

    public AppointmentActivity() {
        super(R.layout.activity_appointment);
    }

    @OnClick({R.id.back_layout,R.id.imgSelect,R.id.img_clear_homeFragment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_layout:
                finish();
                break;
            case R.id.imgSelect:

                break;
            case R.id.img_clear_homeFragment:
                mSearch.setText( "" );
                onRefresh();
                break;
        }
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

    @Override
    protected void initView() {
        super.initView();

        fragmentList.add(ExpertDateFragment.newInstance(""));
        fragmentList.add(ExpertDateRecordFragment.newInstance());

        titleList.add("专家预约");
        titleList.add("预约记录");

        mSwipeRefreshLayout.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        professorListAdapter = new ProfessorListAdapter(professors);
        professorListAdapter.bindToRecyclerView(recyclerView);
        professorListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Professor professor = (Professor) adapter.getItem( position );
                startActivity( ExpertDateActivity.class, professor );

            }
        });



        mSearch.setOnEditorActionListener( new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    onRefresh();
                }
                return false;
            }
        } );


        imgSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(mContext, imgSelect);
                popup.getMenuInflater()
                        .inflate(R.menu.resource_select, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.item_content_one:
                                mClassify=1;
                                onRefresh();
                                break;
                            case R.id.item_content_two:
                                mClassify=2;
                                onRefresh();
                                break;
                        }
                        return true;
                    }
                });

                popup.show(); //showing popup menu
            }
        });
    }

    private void getData() {
    }

    public void select(View v) {
        startActivity( ExpertRecordActivity.class );
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

    protected void onResume() {

        super.onResume();
    }

    @Override
    public void onRefresh() {
        keywords=mSearch.getText().toString();

        mSearch.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
        if(keywords.equals( "" )){
            mClear.setVisibility( View.GONE );
            onRefresh();
        }else {
            mClear.setVisibility( View.VISIBLE );
        }
//        onRefresh();
            }
        } );


        mSwipeRefreshLayout.setRefreshing( true );
        mViewModel.getProfessorList(keywords, mClassify, new BaseObserver<Model0<List<Professor>>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(Model0<List<Professor>> listModel0) {

                mSwipeRefreshLayout.setRefreshing(false);
                mViewModel.mProfessor.clear();
                if (listModel0.getComeback() != null) {
                    mViewModel.mProfessor.addAll( listModel0.getComeback() );
                    if (listModel0.getComeback().size() == 0) {
                        View emptyView = LayoutInflater.from( mContext ).inflate( R.layout.empty_view, null );
                        professorListAdapter.setEmptyView( emptyView );
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
