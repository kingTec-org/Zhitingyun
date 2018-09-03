package com.xiyoukeji.zhitingyun.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseFragment extends Fragment {

    protected View rootView;
    protected boolean isViewCreated;
    protected boolean isLoadData = false;
    protected Context mContext;
    protected Unbinder mUnbinder;
    private CompositeDisposable compositeDisposable;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = bindingData(inflater, container, savedInstanceState);
        if (rootView == null) {
            rootView = LayoutInflater.from(getActivity()).inflate(getLayoutId(), container, false);
        }
        mUnbinder = ButterKnife.bind(this, rootView);
        initView(rootView);
        initData();
        isViewCreated = true;
        return rootView;
    }

    protected View bindingData(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (isViewCreated && getUserVisibleHint() && !isLoadData) {
            lazyLoad();
        }
    }

    protected void initData() {
    }

    protected void initView(View view) {
    }

    protected abstract int getLayoutId();

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isViewCreated && isVisibleToUser && !isLoadData) {
            lazyLoad();
        }
    }

    protected void lazyLoad() {
        isLoadData = true;
    }


    public void addDisposable(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    public void dispose() {
        if (compositeDisposable != null) compositeDisposable.dispose();
    }

    @Override
    public void onDetach() {
        dispose();
        mUnbinder.unbind();
        super.onDetach();
    }

}
