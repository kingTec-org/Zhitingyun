package com.xiyoukeji.zhitingyun.data.remote.transform;

import com.xiyoukeji.zhitingyun.data.entity.BaseModel;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;


public class DefaultTransformer<T extends BaseModel<R>, R>
        implements ObservableTransformer<T, R> {

    @Override
    public ObservableSource<R> apply(Observable<T> upstream) {
        return upstream.compose(SchedulerTransformer.<T>create())
                .compose(new ErrorCheckerTransformer<T, R>());
    }
}