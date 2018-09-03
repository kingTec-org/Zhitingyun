package com.xiyoukeji.zhitingyun.viewmodel.main;

import android.app.Application;
import android.content.Context;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.xiyoukeji.zhitingyun.data.Repository;
import com.xiyoukeji.zhitingyun.data.entity.Model0;
import com.xiyoukeji.zhitingyun.data.entity.RecordEntity;
import com.xiyoukeji.zhitingyun.data.remote.BaseObserver;
import com.xiyoukeji.zhitingyun.viewmodel.BaseViewModel;

public class ReportViewModel extends BaseViewModel {
    private final Context mContext;
    private final Repository mRepository;
    public final ObservableField<String> mPeName = new ObservableField<>("");
    public final ObservableField<String> mStart = new ObservableField<>("");
    public final ObservableField<String> mEnd = new ObservableField<>("");
    public final ObservableField<String> mProblem = new ObservableField<>("");
    public final ObservableField<String> mPoName = new ObservableField<>("");
    public final ObservableField<String> mResult = new ObservableField<>("");
    public final ObservableField<Model0<RecordEntity>> mEntity = new ObservableField<Model0<RecordEntity>>();


    public ReportViewModel(@NonNull Application application, Repository repository) {
        super(application);
        mContext = application.getApplicationContext();
        mRepository = repository;
    }


    public void getReport(Integer orderId, BaseObserver<Model0<RecordEntity>>observer){

        mRepository.getReport( orderId,observer);
    }


    public void getQuickReport(Integer quickOrderId, BaseObserver<Model0<RecordEntity>>observer){

        mRepository.getQuickReport( quickOrderId,observer);
    }
}
