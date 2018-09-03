package com.xiyoukeji.zhitingyun.viewmodel.mine;

import android.app.Application;
import android.content.Context;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.xiyoukeji.zhitingyun.data.entity.AdviceBackEntity;
import com.xiyoukeji.zhitingyun.data.entity.BaseModel;
import com.xiyoukeji.zhitingyun.data.entity.BaseModel0;
import com.xiyoukeji.zhitingyun.data.entity.Model0;
import com.xiyoukeji.zhitingyun.data.entity.OtherEntity;
import com.xiyoukeji.zhitingyun.data.remote.BaseObserver;
import com.xiyoukeji.zhitingyun.util.ToastUtils;
import com.xiyoukeji.zhitingyun.viewmodel.BaseViewModel;
import com.xiyoukeji.zhitingyun.data.Repository;
import com.xiyoukeji.zhitingyun.viewmodel.BaseViewModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class AdviceBackViewModel extends BaseViewModel {

    private final Context mContext;
    private final Repository mRepository;
    public final ObservableField<String> mContent = new ObservableField<>("");
    public final ObservableField<String> mPhone = new ObservableField<>("");
    public final ObservableInt mSize = new ObservableInt();
    public final ObservableField<Model0<OtherEntity>> mEntity = new ObservableField<Model0<OtherEntity>>();


    public AdviceBackViewModel(@NonNull Application application, Repository repository) {
        super(application);
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

    public void adviceBack(String content, BaseObserver<Model0<AdviceBackEntity>> observer) {
        if (TextUtils.isEmpty(mContent.get())) {
            ToastUtils.showShort("反馈内容不能为空");
            return;
        }

        setLoadingState(true);
        mRepository.adviceBack(mContent.get(), observer);
    }

    public void getOthers(Integer id,BaseObserver<Model0<OtherEntity>> observer){
        mRepository.getOthers( id,observer );
    }

}
