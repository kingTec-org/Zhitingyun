package com.xiyoukeji.zhitingyun.view.mine;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xiyoukeji.zhitingyun.R;
import com.xiyoukeji.zhitingyun.ViewModelFactory;
import com.xiyoukeji.zhitingyun.base.BaseActivity;
import com.xiyoukeji.zhitingyun.constant.AppConstant;
import com.xiyoukeji.zhitingyun.constant.Urls;
import com.xiyoukeji.zhitingyun.data.entity.LoginEntity;
import com.xiyoukeji.zhitingyun.data.entity.Model0;
import com.xiyoukeji.zhitingyun.data.entity.Professor;
import com.xiyoukeji.zhitingyun.data.entity.UserEntity;
import com.xiyoukeji.zhitingyun.data.remote.BaseObserver;
import com.xiyoukeji.zhitingyun.databinding.ActivityMine0Binding;
import com.xiyoukeji.zhitingyun.databinding.ActivityMineBinding;
import com.xiyoukeji.zhitingyun.util.ActivityManager;
import com.xiyoukeji.zhitingyun.util.ToastUtils;
import com.xiyoukeji.zhitingyun.view.login.ForgetPwdActivity;
import com.xiyoukeji.zhitingyun.view.login.LoginActivity;
import com.xiyoukeji.zhitingyun.viewmodel.main.MainViewModel;
import com.xiyoukeji.zhitingyun.viewmodel.mine.MineViewModel;
import com.xiyoukeji.zhitingyun.widget.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class MineActivity extends BaseActivity {
    @BindView( R.id.name )
    TextView nameTv;
    @BindView( R.id.portrait_imageview )
    ImageView mHeadpic;
    private MineViewModel mViewModel;
    private ActivityMine0Binding mBinding;
    UserEntity entity;
    private String name;
    private Integer id;



    public MineActivity() {
        super( R.layout.activity_mine0 );
    }

    @Override
    protected void bindingData() {
        super.bindingData();
        mBinding = DataBindingUtil.setContentView(mActivity, layoutId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        int from = getIntent().getIntExtra( "from", 4);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR );
            StatusBarUtil.setStatusBarColor( this, R.color.white );
        }
    }

    @Override
    protected void initView() {
        super.initView();


    }

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

        entity=(UserEntity) getIntent().getSerializableExtra( "userdata" );

//        nameTv.setText( entity.getName() );
        mViewModel.getUserInfo(id, new BaseObserver<Model0<UserEntity>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Model0<UserEntity> userEntityModel0) {
                mViewModel.mUserEntity.set( userEntityModel0 );
                nameTv.setText( userEntityModel0.getComeback().getName());
                Glide.with(mContext).load( Urls.BASE_URL + "/"+userEntityModel0.getComeback().getHeadPic()).into(mHeadpic);

            }
        } );
    }

    public void obtainViewModel() {
        ViewModelFactory factory = ViewModelFactory.getInstance(mActivity.getApplication());
        mViewModel = ViewModelProviders.of(this, factory).get(MineViewModel.class);
    }

    @OnClick({R.id.name,R.id.back,R.id.portrait_imageview ,R.id.advice_back_layout, R.id.equipment_layout,R.id.setting_layout,R.id.mall_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.advice_back_layout:
                startActivity( AdviceBackActivity.class );
                break;
            case R.id.equipment_layout:
                startActivity( EquipmentManagementActivity.class );
                break;
            case R.id.setting_layout:
                startActivity( SettingActivity.class );
                break;
            case R.id.portrait_imageview:
                finish();
                startActivity( BasicInformationActivity.class );
                break;
            case R.id.mall_layout:
                startActivity( MallActivity.class );
                break;
            case R.id.name:
                break;
        }
    }

}
