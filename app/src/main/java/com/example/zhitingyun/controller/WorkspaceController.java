package com.example.zhitingyun.controller;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhitingyun.R;
import com.example.zhitingyun.activity.TreattingActivity;
import com.example.zhitingyun.fragment.MonitorEquipmentFragment;
import com.example.zhitingyun.fragment.NewsListFragment;
import com.example.zhitingyun.fragment.OrderDetailFragment;
import com.example.zhitingyun.fragment.OrderManagerFragment;
import com.example.zhitingyun.fragment.TreatmentRecordFragment;
import com.example.zhitingyun.listener.SendMessageListener;
import com.example.zhitingyun.mvp.Presenter;
import com.example.zhitingyun.mvp.ViewsContainer;
import com.netease.nimlib.sdk.avchat.model.AVChatCommonEvent;
import com.netease.nimlib.sdk.avchat.model.AVChatData;
import com.qmuiteam.qmui.layout.QMUILinearLayout;
import com.qmuiteam.qmui.util.QMUIResHelper;
import com.qmuiteam.qmui.widget.QMUITabSegment;

import java.io.Serializable;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.qmuiteam.qmui.layout.IQMUILayout.HIDE_RADIUS_SIDE_BOTTOM;

/**
 * Created by dasiy on 2018/7/7.
 */

public class WorkspaceController extends MainController implements ViewsContainer.WorkspaceView {
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.imgAlarm)
    ImageView imgAlarm;
    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.viewRed)
    View viewRed;
    @BindView(R.id.pager)
    ViewPager mViewPager;
    @BindView(R.id.tabs)
    QMUITabSegment mTabSegment;
    @BindView(R.id.linearlayout)
    QMUILinearLayout linearlayout;
    @BindView(R.id.linearlayout0)
    QMUILinearLayout linearlayout0;
    private HashMap<Pager, MainController> mPages;
    MainController quickController, todoController;
    Context context;
    Presenter presenter;
    Integer pageIndex = 0;


//    public SendMessageListener sendMessageListener = new SendMessageListener() {
//        @Override
//        public void onSend(Object object, int flag) {
//            ((QuickController) quickController).sendMessageListener.onSend(object, flag);
//        }
//    };

    public WorkspaceController(@NonNull Context context) {
        super(context);
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.controller_workspace, this);
        ButterKnife.bind(this, view);
        presenter = new Presenter(this);
        tvTitle.setText("智听云专家端");
        imgAlarm.setVisibility(View.VISIBLE);
        imgBack.setVisibility(View.GONE);
        linearlayout.setRadius(10);
        linearlayout0.setRadius(10, HIDE_RADIUS_SIDE_BOTTOM);
        initTabs();
        initPagers();

    }

    public void getRed() {
        presenter.haveNewMsg(new HashMap());


    }

    @Override
    protected String getTitle() {
        return null;
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
//            if (position == 4) {
//                ((GuidController) guidController).getBmapView().onDestroy();
//            }
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
        int normalColor = QMUIResHelper.getAttrColor(context, R.attr.qmui_config_color_gray_6);
        int selectColor = QMUIResHelper.getAttrColor(context, R.attr.qmui_config_color_blue);
        mTabSegment.setDefaultNormalColor(normalColor);
        mTabSegment.setDefaultSelectedColor(selectColor);
        mTabSegment.notifyDataChanged();
        mTabSegment.addTab(new QMUITabSegment.Tab("快速诊疗"))
                .addTab(new QMUITabSegment.Tab("待办事项"));

    }

    private void initPagers() {

        mPages = new HashMap<>();
        quickController = new QuickController(context);
        quickController.setListener(listener);
        mPages.put(Pager.QUICK, quickController);

        todoController = new TodoController(context);
        todoController.setListener(listener);
        mPages.put(Pager.TODO, todoController);


        mViewPager.setAdapter(mPagerAdapter);
        mTabSegment.setupWithViewPager(mViewPager, false);
        mViewPager.setFocusable( false );
    }



    @OnClick({R.id.imgAlarm, R.id.llOrder, R.id.llOnline, R.id.llList, R.id.llRecord})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgAlarm:
                listener.startFragment(new NewsListFragment());
                break;
            case R.id.llOrder:
                listener.startFragment(new OrderManagerFragment());
                break;
            case R.id.llOnline:


//                context.startActivity(new Intent(context, TreattingActivity.class).putExtra("avChatData", (Serializable) null).putExtra("receiverId", "5572874d4bf7c9e6"));
                listener.startFragment(new OrderDetailFragment(0, 2));
                break;
            case R.id.llList:
                listener.startFragment(new MySchedualFragment());
                break;
            case R.id.llRecord:
                listener.startFragment(new TreatmentRecordFragment());
                break;
        }
    }

    @Override
    public void haveNewMsg(Integer integer) {
        if (integer > 0)
            viewRed.setVisibility(View.VISIBLE);
        else
            viewRed.setVisibility(View.GONE);


    }

    enum Pager {
        QUICK, TODO;

        public static Pager getPagerFromPositon(int position) {
            switch (position) {
                case 0:
                    return QUICK;
                case 1:
                    return TODO;
                default:
                    return QUICK;
            }
        }
    }

}
