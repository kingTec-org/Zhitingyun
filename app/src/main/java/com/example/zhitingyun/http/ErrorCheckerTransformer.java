package com.example.zhitingyun.http;


import com.example.zhitingyun.base.BaseModel;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by dasiy on 2017/8/21.
 */

public class ErrorCheckerTransformer<T extends BaseModel<R>, R>
        implements Observable.Transformer<T, R> {
    @Override
    public Observable<R> call(Observable<T> tObservable) {
        return tObservable.map(new Func1<T, R>() {
            @Override
            public R call(T t) {
                if (t.getCode() == 100)
                    return t.getData().getComeback();
                else
                    throw new ErrorCheckException(t);
//                else if (t.getCode() == 0 && t.getData() == null)
//                    return null;
//                else if (t.getCode() == 0 && Utils.getMD5(new GsonBuilder()
//                        .disableHtmlEscaping()
//                        .create().toJson(t.getData().getData()) + BaseCom.APP_KEY).equals(t.getData().getSign())) {
//                    ToastUtil.showShort(t.getMessage());
//
//                    return t.getData().getData();
//
//                } else if (t.getCode() == 0 && !Utils.getMD5(new GsonBuilder()
//                        .disableHtmlEscaping()
//                        .create().toJson(t.getData().getData()) + BaseCom.APP_KEY).equals(t.getData().getSign())) {
//                    Log.d("ddd", new GsonBuilder()
//                            .disableHtmlEscaping()
//                            .create().toJson(t.getData().getData()) + BaseCom.APP_KEY);
//                    Log.d("ddd", Utils.getMD5(new GsonBuilder()
//                            .disableHtmlEscaping()
//                            .create().toJson(t.getData().getData()) + BaseCom.APP_KEY));
//                    ToastUtil.showShort("签名错误");
//                    return null;
//                } else
//                    throw new ErrorCheckException(t);


            }
        });
    }
}
