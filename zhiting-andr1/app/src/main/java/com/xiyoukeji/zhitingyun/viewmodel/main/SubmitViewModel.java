package com.xiyoukeji.zhitingyun.viewmodel.main;

import android.app.Application;
import android.content.Context;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.xiyoukeji.zhitingyun.data.Repository;
import com.xiyoukeji.zhitingyun.data.entity.Model0;
import com.xiyoukeji.zhitingyun.data.entity.SubmitEntity;
import com.xiyoukeji.zhitingyun.data.remote.BaseObserver;
import com.xiyoukeji.zhitingyun.viewmodel.BaseViewModel;

public class SubmitViewModel extends BaseViewModel {

    private final Context mContext;
    private final Repository mRepository;
    public final ObservableField<SubmitEntity>mEntity=new ObservableField<>(  );

    public SubmitViewModel(@NonNull Application application, Repository repository) {
        super( application );
        mContext = application.getApplicationContext();
        mRepository = repository;
    }

    public void submit(Integer workId, String desc, String equipmentId, BaseObserver<Model0<SubmitEntity>>observer){
        mRepository.submit( workId,desc,equipmentId,observer );
    }

}
