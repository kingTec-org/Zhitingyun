package com.example.zhitingyun.fragment;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhitingyun.R;
import com.example.zhitingyun.base.BaseFragment;
import com.example.zhitingyun.controller.ConfirmController;
import com.example.zhitingyun.controller.MainController;
import com.example.zhitingyun.controller.WorkspaceController;
import com.example.zhitingyun.listener.ControlListener;
import com.example.zhitingyun.model.MessageEvent;
import com.qmuiteam.qmui.util.QMUIResHelper;
import com.qmuiteam.qmui.widget.QMUITabSegment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dasiy on 2018/7/7.
 */

public class OrderManagerFragment extends BaseFragment {
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.pager)
    ViewPager mViewPager;
    @BindView(R.id.tabs)
    QMUITabSegment mTabSegment;
    private HashMap<Pager, MainController> mPages;
    MainController forConfirmController, hasConfirmController;

    @Override
    protected View onCreateView() {
        super.onCreateView();
        View view = View.inflate(getActivity(), R.layout.fragment_order_manager, null);
        ButterKnife.bind(this, view);
        tvTitle.setText("预约管理");
        initTabs();
        initPagers();
        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
        if (messageEvent.getFlag() == 2) {
            ((ConfirmController) forConfirmController).initData();
            ((ConfirmController) hasConfirmController).initData();
        }

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
        mTabSegment.addTab(new QMUITabSegment.Tab("未确认预约"))
                .addTab(new QMUITabSegment.Tab("已确认预约"));


    }

    private void initPagers() {

        ControlListener listener = new ControlListener() {
            @Override
            public void startFragment(BaseFragment fragment) {
                OrderManagerFragment.this.startFragment(fragment);
            }
        };


        mPages = new HashMap<>();
        forConfirmController = new ConfirmController(getContext(), 0);
        forConfirmController.setListener(listener);
        mPages.put(Pager.FORCONFIRM, forConfirmController);


        hasConfirmController = new ConfirmController(getContext(), 1);
        hasConfirmController.setListener(listener);
        mPages.put(Pager.HASCONFIRM, hasConfirmController);


        mViewPager.setAdapter(mPagerAdapter);
        mTabSegment.setupWithViewPager(mViewPager, false);
        mViewPager.setCurrentItem(0);

    }


    enum Pager {
        FORCONFIRM, HASCONFIRM;

        public static Pager getPagerFromPositon(int position) {
            switch (position) {
                case 0:
                    return FORCONFIRM;
                case 1:
                    return HASCONFIRM;
                default:
                    return FORCONFIRM;
            }
        }
    }
}
