package com.xiyoukeji.zhitingyun.viewmodel.mine;

import android.app.Application;
import android.content.Context;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.xiyoukeji.zhitingyun.data.Repository;
import com.xiyoukeji.zhitingyun.data.entity.LoginEntity;
import com.xiyoukeji.zhitingyun.data.entity.Model0;
import com.xiyoukeji.zhitingyun.data.entity.UserEntity;
import com.xiyoukeji.zhitingyun.data.remote.BaseObserver;
import com.xiyoukeji.zhitingyun.util.ToastUtils;
import com.xiyoukeji.zhitingyun.view.mine.BasicInformationActivity;
import com.xiyoukeji.zhitingyun.viewmodel.BaseViewModel;

import java.io.File;

public class BasicInfoViewModel extends BaseViewModel {
    private final Context mContext;
    private final Repository mRepository;
    public final ObservableField<String> mName = new ObservableField<>("");
    public final ObservableField<String> mSex = new ObservableField<>("");
    public final ObservableField<String> mAge = new ObservableField<>("");
    public final ObservableField<String> mWearTimeEnum = new ObservableField<>("");
    public final ObservableField<Model0<UserEntity>> mUserEntity = new ObservableField<Model0<UserEntity>>();

    public final ObservableField<String> mUrl = new ObservableField<>("");


    public BasicInfoViewModel(@NonNull Application application, Repository repository) {
        super( application );
        mContext = application.getApplicationContext();
        mRepository = repository;
    }



    public void completeInfo(String name, String sex, String age, String WearTimeEnum,String headPic, BaseObserver<Model0<UserEntity>>observer){
        if (TextUtils.isEmpty(mName.get())) {
            ToastUtils.showShort("请输入姓名");
            return;
        }
        if (TextUtils.isEmpty(mSex.get())) {
            ToastUtils.showShort("请输入性别");
            return;
        }
        if (TextUtils.isEmpty(mAge.get() + "")) {
            ToastUtils.showShort("请输入出生日期");
            return;
        }
        if (TextUtils.isEmpty(mWearTimeEnum.get())) {
            ToastUtils.showShort("选择佩戴年龄");
            return;
        }
        mTip.set("保存中");
        setLoadingState( true );

        String timeStamp = BasicInformationActivity.timeStamp();
        Log.d("aaa", "timeStamp=" + timeStamp);
        Log.d("aaa", "current=" + System.currentTimeMillis());

        String date = BasicInformationActivity.timeStamp2Date(timeStamp, "yyyy-MM-dd HH:mm:ss");
        Log.d("aaa", "data" + date);

        String timeStamp2 = BasicInformationActivity.date2TimeStamp(date, "yyyy-MM-dd HH:mm:ss");
        Log.d("aaa", "timeStamp2" + timeStamp2);
        mRepository.basicInfo( mName.get(),mSex.get(),mAge.get(),mWearTimeEnum.get(),headPic,observer );
    }

    public void uploadAvatar(String path, BaseObserver<Model0<String>> observer) {
        mTip.set("上传图片中...");
        setLoadingState(true);
        mRepository.uploadAvatar(path, observer);
    }

    public void getUserInfo(Integer id,BaseObserver<Model0<UserEntity>> observer){
        mRepository.getUser(id,observer );
    }
}
