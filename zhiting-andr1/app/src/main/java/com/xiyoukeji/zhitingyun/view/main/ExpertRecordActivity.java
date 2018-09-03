package com.xiyoukeji.zhitingyun.view.main;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.androidkun.xtablayout.XTabLayout;
import com.xiyoukeji.zhitingyun.R;
import com.xiyoukeji.zhitingyun.base.BaseActivity;
import com.xiyoukeji.zhitingyun.base.BaseTabAdapter;
import com.xiyoukeji.zhitingyun.databinding.ActivityRecordBinding;
import com.xiyoukeji.zhitingyun.util.ActivityManager;
import com.xiyoukeji.zhitingyun.view.login.LoginActivity;
import com.xiyoukeji.zhitingyun.viewmodel.main.MainViewModel;
import com.xiyoukeji.zhitingyun.widget.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ExpertRecordActivity extends BaseActivity {
    @BindView(R.id.tab )
    XTabLayout tab;
    @BindView( R.id.vp )
    ViewPager vp;

    private ActivityRecordBinding mBinding;
    private MainViewModel mViewModel;
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();

    public ExpertRecordActivity() {
        super( R.layout.activity_record );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        ActivityManager.addActivity( this );
        int from = getIntent().getIntExtra( "from", 13);

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
    protected void initView(){
        super.initView();

        fragmentList.add( RecordFragment.newInstance(""));
        fragmentList.add( QuickFragemnt.newInstance(""));

        titleList.add( "普通诊疗" );
        titleList.add( "快速诊疗" );
        BaseTabAdapter adapter=new BaseTabAdapter( getSupportFragmentManager(),titleList,fragmentList );
        vp.setAdapter( adapter );
        tab.setupWithViewPager( vp );

    }

    @OnClick({R.id.back_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_layout:
                finish();
                break;
        }
    }

}
