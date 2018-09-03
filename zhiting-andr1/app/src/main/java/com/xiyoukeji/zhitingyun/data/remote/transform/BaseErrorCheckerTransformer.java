package com.xiyoukeji.zhitingyun.data.remote.transform;

import com.xiyoukeji.zhitingyun.data.entity.BaseModel;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

public class BaseErrorCheckerTransformer<T extends BaseModel>
        implements ObservableTransformer<T, T> {

    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream.map(new Function<T, T>() {
            @Override
            public T apply(T t) throws Exception {
                if (t != null) {
                    int code = Integer.parseInt(t.getState());
                    if (code == 0) {
                        return t;
                    } else {
                        String message = ErrorMessage.get(code);
                        if (message != null) {
                            throw new APIException(code, message);
                        } else {
                            throw new APIException(code, t.getMsg());
                        }
                    }
                } else {
                    return null;
                }
            }
        });
    }
}
