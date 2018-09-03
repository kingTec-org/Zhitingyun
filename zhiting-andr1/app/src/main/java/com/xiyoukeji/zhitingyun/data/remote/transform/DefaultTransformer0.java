package com.xiyoukeji.zhitingyun.data.remote.transform;

import com.xiyoukeji.zhitingyun.data.entity.BaseModel;
import com.xiyoukeji.zhitingyun.data.entity.BaseModel0;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;


public class DefaultTransformer0<T extends BaseModel0<R>, R>
        implements ObservableTransformer<T, R> {

    @Override
    public ObservableSource<R> apply(Observable<T> upstream) {
        return upstream.compose(SchedulerTransformer.<T>create())
                .compose(new ErrorCheckerTransformer0<T, R>());
    }
}