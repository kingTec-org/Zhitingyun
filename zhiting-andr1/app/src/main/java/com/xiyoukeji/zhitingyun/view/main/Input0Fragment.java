package com.xiyoukeji.zhitingyun.view.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.androidkun.xtablayout.XTabLayout;
import com.xiyoukeji.zhitingyun.R;
import com.xiyoukeji.zhitingyun.base.BaseFragment;
import com.xiyoukeji.zhitingyun.base.BaseTabAdapter;
import com.xiyoukeji.zhitingyun.data.remote.BaseObserver;
import com.xiyoukeji.zhitingyun.util.ToastUtils;
import com.xiyoukeji.zhitingyun.viewmodel.main.MainViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class Input0Fragment extends BaseFragment {
    @BindView(R.id.tab2)
    XTabLayout tab;
    @BindView(R.id.vp2)
    ViewPager vp;

    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();
    private MainViewModel mViewModel;

    String hertz = "125,250,500,750,1000,1500,2000,3000,4000,6000,8000";
    List<Integer> left = new ArrayList<>();
    List<Integer> right = new ArrayList<>();
    String leftStr = "";
    String rightStr = "";
    InputLeftear0Fragment leftEarFragment;
    InputRightear0Fragment rightEarFragment;
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
                case 2:
                    right=left;
                    break;
            }


        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_ears;
    }

    public static Fragment newInstance() {
        return new Input0Fragment();
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mViewModel = MainActivity.obtainViewModel(getActivity());
        leftEarFragment = (InputLeftear0Fragment) InputLeftear0Fragment.newInstance();
        rightEarFragment = (InputRightear0Fragment) InputRightear0Fragment.newInstance();

        fragmentList.add(leftEarFragment);
        fragmentList.add(rightEarFragment);

        titleList.add("左耳");
        titleList.add("右耳");

        BaseTabAdapter adapter = new BaseTabAdapter(getChildFragmentManager(), titleList, fragmentList);
        vp.setAdapter(adapter);
        vp.setCurrentItem(0);
        tab.setupWithViewPager(vp);
        leftEarFragment.setOnFeedBackListener(onFeedBackListener);
        rightEarFragment.setOnFeedBackListener(onFeedBackListener);


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
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                /*设置主题、内容、按钮*/
                builder.setTitle("参数修改确认").setMessage("您非设备持有者，此操作将更改 设备参数,您确定继续吗")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0, int arg1) {
                                arg0.dismiss();
                                /*关闭对话框*/
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
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
                mViewModel.submitRecord(hertz, hertz, leftStr, rightStr, "2", new BaseObserver() {
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
                            }
                        });
                builder.create().show();
                break;
        }
    }

    interface OnFeedBackListener {
        void onFeedback(int flag, List<Integer> list);
    }
}
