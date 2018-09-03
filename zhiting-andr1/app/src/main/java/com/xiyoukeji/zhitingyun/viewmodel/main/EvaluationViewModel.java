package com.xiyoukeji.zhitingyun.viewmodel.main;

import android.app.Application;
import android.content.Context;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.xiyoukeji.zhitingyun.data.Repository;
import com.xiyoukeji.zhitingyun.data.entity.EvaluationEntity;
import com.xiyoukeji.zhitingyun.data.entity.Model0;
import com.xiyoukeji.zhitingyun.data.remote.BaseObserver;
import com.xiyoukeji.zhitingyun.util.ToastUtils;
import com.xiyoukeji.zhitingyun.viewmodel.BaseViewModel;

public class EvaluationViewModel extends BaseViewModel {

    private final Context mContext;
    private final Repository mRepository;
    public final ObservableField<String>mName=new ObservableField<>( "" );
    public  final ObservableField<String>mEvaluation=new ObservableField<>( "" );
    public final ObservableField<Integer>mStarts=new ObservableField<>(  );
    public final ObservableField<Integer>mOrderId=new ObservableField<>(  );


    public EvaluationViewModel(@NonNull Application application, Repository repository) {
        super(application);
        mContext = application.getApplicationContext();
        mRepository = repository;
    }

    public void evaluation(Integer orderId, Integer starts, String tags, String evaluation, BaseObserver<Model0<EvaluationEntity>>observer){
        if(TextUtils.isEmpty( mEvaluation.get() )){
            ToastUtils.showShort( "评价内容不能为空" );
            return;
        }
        mTip.set( "提交中" );
        setLoadingState( true );
        mRepository.sendEvaluation( orderId,starts,"","12312", observer);
    }

    public void normalEvaluation(Integer orderId, Integer starts, String tags, String evaluation, BaseObserver<Model0<EvaluationEntity>>observer){
        if(TextUtils.isEmpty( mEvaluation.get() )){
            ToastUtils.showShort( "评价内容不能为空" );
            return;
        }
        mTip.set( "提交中" );
        setLoadingState( true );
        mRepository.sendNormalEvaluation( orderId,starts,"",mEvaluation.get(), observer);
    }


}
