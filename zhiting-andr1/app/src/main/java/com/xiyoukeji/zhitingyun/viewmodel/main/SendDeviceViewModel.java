package com.xiyoukeji.zhitingyun.viewmodel.main;

import android.app.Application;
import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;

import com.xiyoukeji.zhitingyun.data.Repository;
import com.xiyoukeji.zhitingyun.data.entity.BaseModel0;
import com.xiyoukeji.zhitingyun.data.entity.DeviceEntity;
import com.xiyoukeji.zhitingyun.data.entity.Model0;
import com.xiyoukeji.zhitingyun.data.remote.BaseObserver;
import com.xiyoukeji.zhitingyun.viewmodel.BaseViewModel;

import java.util.Observable;

import io.reactivex.disposables.Disposable;

public class SendDeviceViewModel extends BaseViewModel {
    private final Repository mRepository;
    private final Context mContext;


    public SendDeviceViewModel(@NonNull Application application, Repository mRepository) {
        super( application );

        this.mRepository = mRepository;
        this.mContext = application.getApplicationContext();
    }

    public void sendData(BaseObserver<Model0<DeviceEntity>>observer,DeviceEntity deviceEntity){


        mTip.set("上传中");
        setLoadingState( true );
    }

    public void sendExpert(Integer proId,String equipmentParamIds){
        mRepository.sendExpert( 9, "1", new BaseObserver<BaseModel0>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseModel0 baseModel0) {

            }
        } );
    }


}
