package com.xiyoukeji.zhitingyun.viewmodel.main;

import android.app.Application;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.Observable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import com.xiyoukeji.zhitingyun.ViewModelFactory;
import com.xiyoukeji.zhitingyun.data.Repository;
import com.xiyoukeji.zhitingyun.data.entity.BaseModel0;
import com.xiyoukeji.zhitingyun.data.entity.ExpertSchEntity;
import com.xiyoukeji.zhitingyun.data.entity.ListModel;
import com.xiyoukeji.zhitingyun.data.entity.MessageEntity;
import com.xiyoukeji.zhitingyun.data.entity.Model0;
import com.xiyoukeji.zhitingyun.data.entity.Professor;
import com.xiyoukeji.zhitingyun.data.remote.BaseObserver;
import com.xiyoukeji.zhitingyun.viewmodel.BaseViewModel;

import java.util.List;

public class MessageViewModel extends BaseViewModel {

    private final Context mContext;
    private final Repository mRepository;
    public final ObservableList<MessageEntity> mMessage=new ObservableArrayList<>();
    public final ObservableField<String> mTitle = new ObservableField<>("");
    public final ObservableField<Integer> newsType=new ObservableField<>( );
    public final ObservableField<String> mContent = new ObservableField<>("");
    public final ObservableField<Long> mCreateTime = new ObservableField<>();
    public final ObservableField<Long> mLastTime = new ObservableField<>();
    public final ObservableField<MessageEntity>mEntity=new ObservableField<>(  );




    public MessageViewModel(@NonNull Application application, Repository repository) {
        super(application);
        mContext = application.getApplicationContext();
        mRepository = repository;
    }



    public static MessageViewModel obtainViewModel(FragmentActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        MessageViewModel viewModel = ViewModelProviders.of(activity, factory).get(MessageViewModel.class);
        return viewModel;
    }

        public void onMessageRefresh(long lastTime,BaseObserver<ListModel<List<MessageEntity>>>observer){

            mRepository.getMessage(  System.currentTimeMillis(),observer );
    }

    public void readAll(String id,BaseObserver<BaseModel0> observer) {
        setLoadingState(true);
        mRepository.readAll(id,observer);
    }

}
