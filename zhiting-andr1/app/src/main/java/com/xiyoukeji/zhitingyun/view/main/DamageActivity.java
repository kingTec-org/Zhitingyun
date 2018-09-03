package com.xiyoukeji.zhitingyun.view.main;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.androidkun.xtablayout.XTabLayout;
import com.xiyoukeji.zhitingyun.R;
import com.xiyoukeji.zhitingyun.base.BaseActivity;
import com.xiyoukeji.zhitingyun.base.BaseTabAdapter;
import com.xiyoukeji.zhitingyun.util.ToastUtils;
import com.xiyoukeji.zhitingyun.widget.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.xiyoukeji.zhitingyun.ZhitingyunApplication.deviceManager;

public class DamageActivity extends BaseActivity {
    @BindView( R.id.tab )
    XTabLayout tab;
    @BindView( R.id.vp )
    ViewPager vp;

    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();

    public DamageActivity() {
        super( R.layout.activity_damage );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            StatusBarUtil.setStatusBarColor(this, R.color.white); }
    }

    @OnClick({R.id.back,R.id.change})
    public void onViewClicked(View view){
        switch (view.getId()){
            case R.id.back:
                ToastUtils.showLong( "提示：已开启助听功能" );

                finish();
                deviceManager.setHaDeviceSwitch(true);

                break;
            case R.id.change:
                startActivity( TestRecordActivity.class );
                break;

        }
    }

    @Override
    protected void initView(){
        super.initView();


        fragmentList.add( SlideFragment.newInstance() );
        fragmentList.add( InputFragment.newInstance() );

        titleList.add("耳机测听");
        titleList.add( "输入听力图" );
        BaseTabAdapter adapter = new BaseTabAdapter(getSupportFragmentManager(),
                titleList, fragmentList);
        vp.setAdapter( adapter );
        vp.setCurrentItem( 0 );
        tab.setupWithViewPager( vp );
    }

    @Override
    public void onBackPressed() {
        ToastUtils.showLong( "提示：已开启助听功能" );
        deviceManager.setHaDeviceSwitch(true);

        finish();
        Runtime.getRuntime();
    }
}
