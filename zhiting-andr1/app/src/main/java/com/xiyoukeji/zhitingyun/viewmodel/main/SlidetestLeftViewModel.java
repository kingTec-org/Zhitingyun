package com.xiyoukeji.zhitingyun.viewmodel.main;

import android.app.Application;
import android.content.Context;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.xiyoukeji.zhitingyun.data.Repository;
import com.xiyoukeji.zhitingyun.data.entity.TestRecordEntity;
import com.xiyoukeji.zhitingyun.viewmodel.BaseViewModel;

public class SlidetestLeftViewModel extends BaseViewModel {
    private final Repository mRepository;
    private final Context mContext;
    public final ObservableField<TestRecordEntity>mTest=new ObservableField<>(  );


    public SlidetestLeftViewModel(@NonNull Application application, Repository mRepository, Context mContext) {
        super( application );
        this.mRepository = mRepository;
        this.mContext = mContext;
    }

}
