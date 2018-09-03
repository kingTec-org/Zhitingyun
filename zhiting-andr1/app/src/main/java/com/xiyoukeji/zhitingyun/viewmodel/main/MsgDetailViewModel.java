package com.xiyoukeji.zhitingyun.viewmodel.main;

import android.app.Application;
import android.content.Context;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.xiyoukeji.zhitingyun.data.Repository;
import com.xiyoukeji.zhitingyun.data.entity.MessageEntity;
import com.xiyoukeji.zhitingyun.data.entity.Model0;
import com.xiyoukeji.zhitingyun.data.remote.BaseObserver;
import com.xiyoukeji.zhitingyun.viewmodel.BaseViewModel;

public class MsgDetailViewModel extends BaseViewModel {

        private final Context mContext;
        private final Repository mRepository;
        public final ObservableField<Model0<MessageEntity>> mMessageEntity = new ObservableField<>();
        public final ObservableField<String> mTitle = new ObservableField<>("");
        public final ObservableField<String> mLastPushTime = new ObservableField<>("");


        public MsgDetailViewModel(@NonNull Application application, Repository repository) {
            super(application);
            mContext = application.getApplicationContext();
            mRepository = repository;
        }

        public void getMsgDetail(Integer newsId, Integer newsType, BaseObserver<Model0<MessageEntity>> observer) {
            mRepository.getMsgDetail(newsId,newsType, observer);
        }

}
