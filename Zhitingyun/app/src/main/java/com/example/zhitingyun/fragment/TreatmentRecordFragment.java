package com.example.zhitingyun.fragment;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhitingyun.R;
import com.example.zhitingyun.base.BaseFragment;
import com.example.zhitingyun.controller.TreatmentController;
import com.example.zhitingyun.controller.MainController;
import com.example.zhitingyun.controller.QuickTreatmentController;
import com.example.zhitingyun.listener.ControlListener;
import com.qmuiteam.qmui.util.QMUIResHelper;
import com.qmuiteam.qmui.widget.QMUITabSegment;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dasiy on 2018/7/9.
 */

public class TreatmentRecordFragment extends BaseFragment {
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.pager)
    ViewPager mViewPager;
    @BindView(R.id.tabs)
    QMUITabSegment mTabSegment;
    private HashMap<Pager, MainController> mPages;
    MainController commonTreatmentController, quickTreatmentController;

    @Override
    protected View onCreateView() {
        View view = View.inflate(getActivity(), R.layout.fragment_treatment_record, null);
        ButterKnife.bind(this, view);
        tvTitle.setText("诊疗记录");
        initTabs();
        initPagers();
        return view;
    }

    @OnClick({R.id.imgBack})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                popBackStack();
                break;

        }
    }

    private PagerAdapter mPagerAdapter = new PagerAdapter() {

        private int mChildCount = 0;

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public int getCount() {
            return mPages.size();
        }

        @Override
        public Object instantiateItem(final ViewGroup container, int position) {
            MainController page = mPages.get(Pager.getPagerFromPositon(position));
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            container.addView(page, params);
            return page;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getItemPosition(Object object) {
            if (mChildCount == 0) {
                return POSITION_NONE;
            }
            return super.getItemPosition(object);
        }

        @Override
        public void notifyDataSetChanged() {
            mChildCount = getCount();
            super.notifyDataSetChanged();
        }
    };

    private void initTabs() {
        int normalColor = QMUIResHelper.getAttrColor(getContext(), R.attr.qmui_config_color_gray_6);
        int selectColor = QMUIResHelper.getAttrColor(getContext(), R.attr.qmui_config_color_blue);
        mTabSegment.setDefaultNormalColor(normalColor);
        mTabSegment.setDefaultSelectedColor(selectColor);
        mTabSegment.notifyDataChanged();
        mTabSegment.addTab(new QMUITabSegment.Tab("普通诊疗"))
                .addTab(new QMUITabSegment.Tab("快速诊疗"));


    }

    private void initPagers() {

        ControlListener listener = new ControlListener() {
            @Override
            public void startFragment(BaseFragment fragment) {
                TreatmentRecordFragment.this.startFragment(fragment);
            }
        };


        mPages = new HashMap<>();
        commonTreatmentController = new TreatmentController(getContext(), 0);
        commonTreatmentController.setListener(listener);
        mPages.put(Pager.COMMON, commonTreatmentController);


        quickTreatmentController = new TreatmentController(getContext(), 1);
        quickTreatmentController.setListener(listener);
        mPages.put(Pager.QUICK, quickTreatmentController);


        mViewPager.setAdapter(mPagerAdapter);
        mTabSegment.setupWithViewPager(mViewPager, false);
        mViewPager.setCurrentItem(0);

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("vvv", "onResume");
    }

    enum Pager {
        COMMON, QUICK;

        public static Pager getPagerFromPositon(int position) {
            switch (position) {
                case 0:
                    return COMMON;
                case 1:
                    return QUICK;
                default:
                    return COMMON;
            }
        }
    }
}
