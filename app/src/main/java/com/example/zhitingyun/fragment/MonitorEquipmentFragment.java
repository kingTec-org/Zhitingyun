package com.example.zhitingyun.fragment;

import android.annotation.SuppressLint;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhitingyun.R;
import com.example.zhitingyun.base.BaseFragment;
import com.example.zhitingyun.controller.CommonController;
import com.example.zhitingyun.controller.IndoorController;
import com.example.zhitingyun.controller.MainController;
import com.example.zhitingyun.controller.OutdoorController;
import com.example.zhitingyun.controller.TrafficController;
import com.example.zhitingyun.listener.ControlListener;
import com.example.zhitingyun.model.EquipmentParamater;
import com.example.zhitingyun.model.OrderDetail;
import com.qmuiteam.qmui.util.QMUIResHelper;
import com.qmuiteam.qmui.widget.QMUITabSegment;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dasiy on 2018/7/9.
 */

@SuppressLint("ValidFragment")
public class MonitorEquipmentFragment extends BaseFragment {
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tabs)
    QMUITabSegment mTabSegment;
    @BindView(R.id.pager)
    ViewPager mViewPager;
    private HashMap<Pager, MainController> mPages;
    MainController indoorController, trafficController, outdoorController, commonController;
    OrderDetail orderDetail;
    EquipmentParamater indoorParamater, trafficParamater, outdoorParamater, commonParamater;
    List<EquipmentParamater> list;

    public MonitorEquipmentFragment(OrderDetail orderDetail, List<EquipmentParamater> list) {
        this.orderDetail = orderDetail;
        this.list = list;


    }


    @Override
    protected View onCreateView() {
        View view = View.inflate(getActivity(), R.layout.fragment_detail_equipment, null);
        ButterKnife.bind(this, view);
        tvTitle.setText("设备参数");
        initTabs();
        initPagers();
        return view;
    }


    @OnClick(R.id.imgBack)
    public void onViewClicked() {
        popBackStack();
    }


    private void initTabs() {
        int normalColor = QMUIResHelper.getAttrColor(getContext(), R.attr.qmui_config_color_gray_6);
        int selectColor = QMUIResHelper.getAttrColor(getContext(), R.attr.qmui_config_color_blue);
        mTabSegment.setDefaultNormalColor(normalColor);
        mTabSegment.setDefaultSelectedColor(selectColor);
        mTabSegment.notifyDataChanged();
        mTabSegment.addTab(new QMUITabSegment.Tab("室内"))
                .addTab(new QMUITabSegment.Tab("交通"))
                .addTab(new QMUITabSegment.Tab("户外"))
                .addTab(new QMUITabSegment.Tab("通用"));

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

    private void initPagers() {
        ControlListener listener = new ControlListener() {
            @Override
            public void startFragment(BaseFragment fragment) {
                MonitorEquipmentFragment.this.startFragment(fragment);
            }
        };

        mPages = new HashMap<>();
        indoorController = new TrafficController(getContext(), 0, list,orderDetail.getUserDto().getId());
        indoorController.setListener(listener);
        mPages.put(Pager.INDOOR, indoorController);

        trafficController = new TrafficController(getContext(), 1, list,orderDetail.getUserDto().getId());
        trafficController.setListener(listener);
        mPages.put(Pager.TRAFFIC, trafficController);

        outdoorController = new TrafficController(getContext(), 2, list,orderDetail.getUserDto().getId());
        outdoorController.setListener(listener);
        mPages.put(Pager.OUTDOOR, outdoorController);

        commonController = new TrafficController(getContext(), 3, list,orderDetail.getUserDto().getId());
        commonController.setListener(listener);
        mPages.put(Pager.COMMON, commonController);


        mViewPager.setAdapter(mPagerAdapter);
        mTabSegment.setupWithViewPager(mViewPager, false);
        mViewPager.setCurrentItem(0);

    }

    enum Pager {
        INDOOR, TRAFFIC, OUTDOOR, COMMON;

        public static Pager getPagerFromPositon(int position) {
            switch (position) {
                case 0:
                    return INDOOR;
                case 1:
                    return TRAFFIC;
                case 2:
                    return OUTDOOR;
                case 3:
                    return COMMON;
                default:
                    return INDOOR;
            }
        }
    }
}
