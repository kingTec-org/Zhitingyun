package com.xiyoukeji.zhitingyun.view.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xiyoukeji.zhitingyun.R;
import com.xiyoukeji.zhitingyun.ViewModelFactory;
import com.xiyoukeji.zhitingyun.base.BaseActivity;
import com.xiyoukeji.zhitingyun.data.entity.Model0;
import com.xiyoukeji.zhitingyun.data.entity.RecordEntity;
import com.xiyoukeji.zhitingyun.data.entity.UserEntity;
import com.xiyoukeji.zhitingyun.data.remote.BaseObserver;
import com.xiyoukeji.zhitingyun.util.CodeTimer;
import com.xiyoukeji.zhitingyun.util.ToastUtils;
import com.xiyoukeji.zhitingyun.viewmodel.main.MainViewModel;
import com.xiyoukeji.zhitingyun.databinding.ActivityWaitBinding;
import com.xiyoukeji.zhitingyun.widget.StatusBarUtil;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class WaitingActivity extends BaseActivity {


    private int orderId;
    private ActivityWaitBinding mBinding;
    private MainViewModel mViewModel;

    private CountDownTimer mTimer;

    public static int time = 15;  //超时时间设置
    public static Timer timer;
    Integer ss = 1000;
    Integer mi = ss * 60;
    Integer hh = mi * 60;
    Integer dd = hh * 24;
    private long savetime=900000L;
    private Integer mStatus;
    private long tobesaved;
    private long currentTime;
    RecordEntity entity;



    @BindView(R.id.gif)
    ImageView mGif;
    @BindView( R.id.timer )
    TextView timerTv;



    public WaitingActivity() {
        super( R.layout.activity_wait );
    }

    @Override
    protected void bindingData() {
        super.bindingData();
        mBinding = DataBindingUtil.setContentView( mActivity, layoutId );
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


        currentTime=System.currentTimeMillis();
        SharedPreferences sharedPreferences0 = getSharedPreferences( "mySP", Context.MODE_PRIVATE );
        long current2= sharedPreferences0.getLong( "createTime", 900000L );

        Long laseTime =currentTime-current2;

        savetime=savetime-laseTime;

        mTimer = new CountDownTimer( savetime, 1000L ) {
            @Override
            public void onTick(long millisUntilFinished) {
                long seconds=millisUntilFinished%60000;
                timerTv.setText("15分钟到将自动取消 "+millisUntilFinished / 60000 + ":"+ Math.round((float)seconds/1000 ) );
            }

            @Override
            public void onFinish() {

            }
        }.start();

    }

    @OnClick({R.id.back_layout, R.id.btn_log_in})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_layout:
                finish();
                break;
            case R.id.btn_log_in:
                SharedPreferences sharedPreferences = getSharedPreferences( "mySP", Context.MODE_PRIVATE );
                orderId = sharedPreferences.getInt( "orderId", 1 );


                mViewModel.cancelDate( orderId, 1, new BaseObserver<Model0<RecordEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable( d );
                    }

                    @Override
                    public void onNext(Model0<RecordEntity> recordEntityModel0) {
                        mViewModel.setLoadingState( false );
                        ToastUtils.showShort( "取消成功" );

                        finish();
                    }
                } );
                break;
        }
    }

    @Override
    protected void initData() {
        super.initData();
        obtainViewModel();
        mBinding.setViewModel( mViewModel );
        mViewModel.getLoadingState().observe( this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean) {
                    showLoading( mViewModel.mTip.get() );
                } else {
                    dismissLoading();
                }
            }
        } );


    }

    public void obtainViewModel() {
        ViewModelFactory factory = ViewModelFactory.getInstance( mActivity.getApplication() );
        mViewModel = ViewModelProviders.of( this, factory ).get( MainViewModel.class );
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTimer != null) {
            mTimer.cancel();
        }

    }
}
