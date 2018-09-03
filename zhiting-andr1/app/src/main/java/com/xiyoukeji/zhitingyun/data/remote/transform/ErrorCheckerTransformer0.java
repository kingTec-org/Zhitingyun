package com.xiyoukeji.zhitingyun.data.remote.transform;

import com.xiyoukeji.zhitingyun.data.entity.BaseModel0;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

public class ErrorCheckerTransformer0<T extends BaseModel0<R>, R>
        implements ObservableTransformer<T, R> {

    @Override
    public ObservableSource<R> apply(Observable<T> upstream) {
        return upstream.map(new Function<T, R>() {
            @Override
            public R apply(T t) throws Exception {
                if (t != null) {
                    int code =t.getCode();
                    if (code == 100) {
                        return t.getData();
                    } else {
                        String message = ErrorMessage.get(code);
                        if (message != null) {
                            throw new APIException0(t,code,message);
                        } else {
                            throw new APIException0(t,code,t.getMessage());
                        }
                    }
                } else {
                    return null;
                }
            }
        });
    }
}
