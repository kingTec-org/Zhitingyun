package com.xiyoukeji.zhitingyun.view.main;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xiyoukeji.zhitingyun.R;
import com.xiyoukeji.zhitingyun.ViewModelFactory;
import com.xiyoukeji.zhitingyun.base.BaseActivity;
import com.xiyoukeji.zhitingyun.constant.Urls;
import com.xiyoukeji.zhitingyun.data.entity.EvaluationEntity;
import com.xiyoukeji.zhitingyun.data.entity.Model0;
import com.xiyoukeji.zhitingyun.data.remote.BaseObserver;
import com.xiyoukeji.zhitingyun.databinding.ActivityEvaluationBinding;
import com.xiyoukeji.zhitingyun.util.ToastUtils;
import com.xiyoukeji.zhitingyun.viewmodel.main.EvaluationViewModel;
import com.xiyoukeji.zhitingyun.viewmodel.mine.AdviceBackViewModel;
import com.xiyoukeji.zhitingyun.widget.RoundImageView;
import com.xiyoukeji.zhitingyun.widget.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class EvaluationActivity  extends BaseActivity {
    @BindView( R.id.ratingbar )
    RatingBar mRatingBar;
    @BindView( R.id.imgRound )
    RoundImageView mHeadpic;
    @BindView( R.id.nameTv )
    TextView mNameTv;


    private EvaluationViewModel mViewModel;
    private ActivityEvaluationBinding mBinding;
    private int mStarts;
    private int mId;
    private String mTags;
    private String mEvaluation;
    private Integer mClassify;
    private Integer mStatus;
    private Integer mProId;


    public EvaluationActivity() {
        super( R.layout.activity_evaluation );
    }

    @Override
    public void bindingData(){
        super.bindingData();
        mBinding= DataBindingUtil.setContentView( mActivity,layoutId );
    }


    @Override
    protected void initView() {
        super.initView();
//        mStarts=Math.round( mRatingBar.getRating());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            StatusBarUtil.setStatusBarColor(this, R.color.white); }

        Intent intent=getIntent();
        mClassify=intent.getIntExtra( "evaluateClassify",1);
        mStatus=intent.getIntExtra( "evaluateStatus",1 );
        if(mClassify==1) {
            mId = intent.getIntExtra( "evaluateId", 1 );
            mNameTv.setText( intent.getStringExtra( "evaluateName" ) );
            Glide.with( mContext ).load( Urls.BASE_URL + "/" + intent.getStringExtra( "evaluateHeadpic" ) ).into( mHeadpic );
        }
        if(mClassify==2) {
            mId = intent.getIntExtra( "evaluateId", 1 );
            mNameTv.setText( intent.getStringExtra( "evaluateName" ) );
            Glide.with( mContext ).load( Urls.BASE_URL + "/" + intent.getStringExtra( "evaluateHeadpic" ) ).into( mHeadpic );
        }
    }

    @OnClick({R.id.finished,R.id.sue_tv})
    public void onViewClicked(View view){
        switch (view.getId()){
            case R.id.finished:
                Intent intent=getIntent();
                if(mClassify==1) {
                    mId = intent.getIntExtra( "evaluateId", 1 );

                    mStarts = Math.round( mRatingBar.getRating() );
                    mViewModel.evaluation( mId, mStarts, mTags, mEvaluation, new BaseObserver<Model0<EvaluationEntity>>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            addDisposable( d );
                        }

                        @Override
                        public void onNext(Model0<EvaluationEntity> evaluationEntityModel0) {
                            mViewModel.setLoadingState( false );
                            ToastUtils.showShort( "提交成功" );
                            finish();
                        }

                        @Override
                        public void onError(Throwable e) {
                            super.onError( e );
                            mViewModel.setLoadingState( false );
                        }
                    } );
                }

                else if(mClassify==2) {
                    mId = intent.getIntExtra( "evaluateId", 1 );

                    mStarts = Math.round( mRatingBar.getRating() );
                    mViewModel.normalEvaluation( mId, mStarts, mTags, mEvaluation, new BaseObserver<Model0<EvaluationEntity>>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            addDisposable( d );
                        }

                        @Override
                        public void onNext(Model0<EvaluationEntity> evaluationEntityModel0) {
                            mViewModel.setLoadingState( false );
                            ToastUtils.showShort( "提交成功" );
                            finish();
                        }

                        @Override
                        public void onError(Throwable e) {
                            super.onError( e );
                            mViewModel.setLoadingState( false );
                        }
                    } );
                }


                break;
            case R.id.sue_tv:
                Intent intent2=getIntent();
                mProId=intent2.getIntExtra( "evaluateProId",1 );
                String mName=intent2.getStringExtra( "evaluateName" );

                Intent intent3=new Intent(EvaluationActivity.this,SueActivity.class);
                intent3.putExtra( "evaluateId",mProId );
                intent3.putExtra( "evaluateDocName",mName );
                startActivity( intent3);
                break;
        }
    }

    @Override
    protected void initData() {
        super.initData();
        obtainViewModel();
        mBinding.setViewModel(mViewModel);
    }

    public void obtainViewModel() {
        ViewModelFactory factory = ViewModelFactory.getInstance(mActivity.getApplication());
        mViewModel = ViewModelProviders.of(this, factory).get(EvaluationViewModel.class);
    }

}
