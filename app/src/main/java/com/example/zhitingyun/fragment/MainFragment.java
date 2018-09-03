package com.example.zhitingyun.fragment;

import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhitingyun.R;
import com.example.zhitingyun.base.BaseFragment;
import com.example.zhitingyun.controller.MainController;
import com.example.zhitingyun.controller.PersoninfoController;
import com.example.zhitingyun.controller.WorkspaceController;
import com.example.zhitingyun.listener.ControlListener;
import com.example.zhitingyun.model.MessageEvent;
import com.example.zhitingyun.mvp.Presenter;
import com.example.zhitingyun.mvp.ViewsContainer;
import com.example.zhitingyun.yunxin.AVChatExitCode;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.avchat.AVChatManager;
import com.netease.nimlib.sdk.avchat.constant.AVChatControlCommand;
import com.netease.nimlib.sdk.avchat.model.AVChatCommonEvent;
import com.netease.nimlib.sdk.avchat.model.AVChatData;
import com.qmuiteam.qmui.util.QMUIResHelper;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUITabSegment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import coder.mylibrary.manager.ActivityManager;
import coder.mylibrary.util.SPUtil;

import static com.qmuiteam.qmui.widget.QMUITabSegment.ICON_POSITION_LEFT;

/**
 * Created by dasiy on 2018/7/7.
 */

public class MainFragment extends BaseFragment implements ViewsContainer.MainView {

    @BindView(R.id.pager)
    ViewPager mViewPager;
    @BindView(R.id.tabs)
    QMUITabSegment mTabSegment;
    private HashMap<Pager, MainController> mPages;
    MainController workspaceController, personinfoController;
    Presenter presenter;
    Integer orderId;
    Integer pageIndex = 0;
    Integer a=0;

    @Override
    protected View onCreateView() {
        super.onCreateView();
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.activity_main, null);
        QMUIStatusBarHelper.setStatusBarLightMode(ActivityManager.getInstance().currentActivity());
        ButterKnife.bind(this, view);
        presenter = new Presenter(this);
        initTabs();
        initPagers();
        initChat();
        return view;

    }

    private void initChat() {
        AVChatManager.getInstance().observeIncomingCall(inComingCallObserver, true);
        AVChatManager.getInstance().observeHangUpNotification(callHangupObserver, true);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            getBaseFragmentActivity().startFragment(new WriteReportFragment(0,orderId, 1));

        }
    };


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
        if (messageEvent.getFlag() == 5) {
            orderId = (Integer) messageEvent.getObject();
            if (orderId != null) {
                Map map = new HashMap();
                map.put("orderId", orderId);
                map.put("classify", 1);
                presenter.finishCure(map, 1);
            }

//            Map map = new HashMap();
//            map.put("orderId", orderId);
//            presenter.finishCure(map, 0);
        }

//            ((WorkspaceController) workspaceController).sendMessageListener.onSend(messageEvent.getObject(), 2);

    }

    // 通话过程中，收到对方挂断电话
    private Observer<AVChatCommonEvent> callHangupObserver = new Observer<AVChatCommonEvent>() {
        @Override
        public void onEvent(AVChatCommonEvent avChatHangUpInfo) {
//            ((WorkspaceController) workspaceController).sendMessageListener.onSend(avChatHangUpInfo, 1);

        }
    };

    private Observer<AVChatData> inComingCallObserver = new Observer<AVChatData>() {
        @Override
        public void onEvent(final AVChatData data) {
//            String extra = data.getExtra();
//            ((WorkspaceController) workspaceController).sendMessageListener.onSend(data, 0);
//            Log.e("Extra", "Extra Message->" + extra);
        }
    };

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
        int normalColor = QMUIResHelper.getAttrColor(getActivity(), R.attr.qmui_config_color_gray_6);
        int selectColor = QMUIResHelper.getAttrColor(getActivity(), R.attr.qmui_config_color_blue);
        mTabSegment.setDefaultNormalColor(normalColor);
        mTabSegment.setDefaultSelectedColor(selectColor);
        mTabSegment.notifyDataChanged();
        QMUITabSegment.Tab workspace = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(getContext(), R.mipmap.workspace_gray),
                ContextCompat.getDrawable(getContext(), R.mipmap.workspace_blue),
                "工作台", false
        );

        QMUITabSegment.Tab personinfo = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(getContext(), R.mipmap.personinfo_gray),
                ContextCompat.getDrawable(getContext(), R.mipmap.personinfo_blue),
                "个人中心", false
        );
        mTabSegment.setDefaultTabIconPosition(ICON_POSITION_LEFT);

        mTabSegment.addTab(workspace)
                .addTab(personinfo);

    }

    private void initPagers() {
        ControlListener listener = new ControlListener() {
            @Override
            public void startFragment(BaseFragment fragment) {
                MainFragment.this.startFragment(fragment);
            }
        };

        mPages = new HashMap<>();
        workspaceController = new WorkspaceController(getActivity());
        workspaceController.setListener(listener);
        mPages.put(Pager.WORKSPACE, workspaceController);

        personinfoController = new PersoninfoController(getActivity(),1);
        personinfoController.setListener(listener);
        mPages.put(Pager.PERSONINFO, personinfoController);


        mViewPager.setAdapter(mPagerAdapter);
        mTabSegment.setupWithViewPager(mViewPager, false);
//        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                Log.d( "sss",position+"" );
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
        mViewPager.setFocusable( false );

//        mViewPager.setCurrentItem(0);

    }

    @Override
    public void onPause() {
        super.onPause();
        pageIndex=mViewPager.getCurrentItem();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((WorkspaceController) workspaceController).getRed();
        Log.d( "sss","resume" );
        mViewPager.setCurrentItem(pageIndex);

//        if (SPUtil.get(getContext(), "needRefresh", "1").toString().equals("1")) {
//            ((PersoninfoController) personinfoController).getInfo();
//            SPUtil.put(getContext(), "needRefresh", "0");
//        }

    }

    @Override
    public void finishCure() {
        handler.sendMessageDelayed(new Message(), 100);

//        startFragment(new WriteReportFragment(orderId));
    }

    enum Pager {
        WORKSPACE, PERSONINFO;

        public static Pager getPagerFromPositon(int position) {
            switch (position) {
                case 0:
                    return WORKSPACE;
                case 1:
                    return PERSONINFO;
                default:
                    return WORKSPACE;
            }
        }
    }

    interface OngetInfo {
        void ongetInfo(int flag);
    }

}
