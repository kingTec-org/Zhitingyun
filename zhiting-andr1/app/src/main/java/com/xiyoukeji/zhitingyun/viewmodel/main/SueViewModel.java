package com.xiyoukeji.zhitingyun.viewmodel.main;

import android.app.Application;
import android.content.Context;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.xiyoukeji.zhitingyun.data.Repository;
import com.xiyoukeji.zhitingyun.data.entity.Model0;
import com.xiyoukeji.zhitingyun.data.entity.OtherEntity;
import com.xiyoukeji.zhitingyun.data.entity.RecordEntity;
import com.xiyoukeji.zhitingyun.data.entity.SueEntity;
import com.xiyoukeji.zhitingyun.data.remote.BaseObserver;
import com.xiyoukeji.zhitingyun.util.ToastUtils;
import com.xiyoukeji.zhitingyun.viewmodel.BaseViewModel;

public class SueViewModel extends BaseViewModel{
    private final Context mContext;
    private final Repository mRepository;
    public final ObservableField<String> mContent = new ObservableField<>("");
    public final ObservableField<String> mProId = new ObservableField<>("");
    public final ObservableInt mSize = new ObservableInt();
    public final ObservableField<Model0<OtherEntity>> mEntity = new ObservableField<Model0<OtherEntity>>();


    public SueViewModel(@NonNull Application application, Repository repository) {
        super( application );
        mContext = application.getApplicationContext();
        mRepository = repository;
    }

    public void addSizeCallBack() {
        mContent.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                mSize.set(mContent.get().length());
            }
        });
    }
    public void sueExpert(Integer professorId, String content, BaseObserver<Model0<RecordEntity>>observer){
        if (TextUtils.isEmpty(mContent.get())) {
            ToastUtils.showShort( "反馈内容不能为空" );
            return;
        }
        setLoadingState(true);
        mRepository.sueExpert(professorId,mContent.get(),observer );

    }

    public void getOthers(Integer id,BaseObserver<Model0<OtherEntity>> observer){
        mRepository.getOthers( id,observer );
    }

}
