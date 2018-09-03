package com.xiyoukeji.zhitingyun.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.xiyoukeji.zhitingyun.LoadingState;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class BaseViewModel extends AndroidViewModel {

    public final ObservableField<String> mTip = new ObservableField<>("提交中");
    protected final LoadingState mLoadingState = new LoadingState();

    public LoadingState getLoadingState() {
        return mLoadingState;
    }

    public void setLoadingState(Boolean state) {
        mLoadingState.setValue(state);
    }

    public BaseViewModel(@NonNull Application application) {
        super(application);
    }

    public boolean checkPwd(String str) {
        String pattern = "[a-z|A-Z|0-9]{6,}";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        return m.matches();
    }
}
