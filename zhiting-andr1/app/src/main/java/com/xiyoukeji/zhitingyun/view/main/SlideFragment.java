package com.xiyoukeji.zhitingyun.view.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidkun.xtablayout.XTabLayout;
import com.xiyoukeji.zhitingyun.R;
import com.xiyoukeji.zhitingyun.base.BaseFragment;
import com.xiyoukeji.zhitingyun.base.BaseTabAdapter;
import com.xiyoukeji.zhitingyun.data.remote.BaseObserver;
import com.xiyoukeji.zhitingyun.databinding.FragmentExpertDateBinding;
import com.xiyoukeji.zhitingyun.databinding.FragmentSlidetestLeftBinding;
import com.xiyoukeji.zhitingyun.util.ToastUtils;
import com.xiyoukeji.zhitingyun.viewmodel.main.MainViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.disposables.Disposable;

public class SlideFragment extends BaseFragment {
    @BindView(R.id.tab2)
    XTabLayout tab;
    @BindView(R.id.vp2)
    ViewPager vp;

    LeftEarFragment leftEarFragment;
    RightEarFragment rightEarFragment;
    String hertz = "125,250,500,750,1000,1500,2000,3000,4000,6000,8000";
    List<Integer> left = new ArrayList<>();
    List<Integer> right = new ArrayList<>();
    String leftStr = "";
    String rightStr = "";
    private MainViewModel mViewModel;
    private FragmentSlidetestLeftBinding mBinding;
    private


    OnFeedBackListener onFeedBackListener = new OnFeedBackListener() {
        @Override
        public void onFeedback(int flag, List<Integer> list) {
            switch (flag) {
                case 0:
                    left = list;
                    break;
                case 1:
                    right = list;
                    break;
            }


        }
    };


    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_ears;
    }

    public static Fragment newInstance() {
        return new SlideFragment();
    }




    @Override
    protected void initView(View view) {
        super.initView(view);
        mViewModel = MainActivity.obtainViewModel(getActivity());
        leftEarFragment = new LeftEarFragment( onFeedBackListener );
        rightEarFragment = new RightEarFragment( onFeedBackListener );

        fragmentList.add(leftEarFragment);
        fragmentList.add(rightEarFragment);

        titleList.add("左耳");
        titleList.add("右耳");

        BaseTabAdapter adapter = new BaseTabAdapter(getChildFragmentManager(), titleList, fragmentList);
        vp.setAdapter(adapter);
        vp.setCurrentItem(0);
        tab.setupWithViewPager(vp);

    }



    @OnClick({R.id.auto_layoutt, R.id.save_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.auto_layoutt:
                for (int i = 0; i < left.size(); i++) {
                    leftStr += left.get(i) + "";
                    if (i != left.size() - 1)
                        leftStr += ",";
                }
                for (int i = 0; i < right.size(); i++) {
                    rightStr += right.get(i) + "";
                    if (i != right.size() - 1)
                        rightStr += ",";
                }
                ToastUtils.showLong( leftStr+","+rightStr );
                break;
            case R.id.save_layout:
                for (int i = 0; i < left.size(); i++) {
                    leftStr += left.get(i) + "";
                    if (i != left.size() - 1)
                        leftStr += ",";
                }
                for (int i = 0; i < right.size(); i++) {
                    rightStr += right.get(i) + "";
                    if (i != right.size() - 1)
                        rightStr += ",";
                }
                mViewModel.submitRecord(hertz, hertz, leftStr, rightStr, "1", new BaseObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(Object object) {
                        leftStr="";
                        rightStr="";
                        ToastUtils.showShort("上传成功");

                    }
                });

                break;
        }
    }

    interface OnFeedBackListener {
        void onFeedback(int flag, List<Integer> list);
    }
}
