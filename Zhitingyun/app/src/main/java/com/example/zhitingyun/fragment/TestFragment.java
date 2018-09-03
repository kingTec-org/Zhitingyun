package com.example.zhitingyun.fragment;

import android.view.View;
import android.view.WindowManager;

import com.example.zhitingyun.R;
import com.example.zhitingyun.base.BaseFragment;
import com.example.zhitingyun.yunxin.AVChatController;
import com.example.zhitingyun.yunxin.AVChatSoundPlayer;
import com.example.zhitingyun.yunxin.AVChatVideoUI;
import com.netease.nimlib.sdk.avchat.model.AVChatData;

import butterknife.ButterKnife;

/**
 * Created by dasiy on 2018/7/11.
 */

public class TestFragment extends BaseFragment {
    View view;
    AVChatController avChatController;
    AVChatVideoUI avChatVideoUI;
    private boolean mIsInComingCall = false;// is incoming call or outgoing call
    private String receiverId = "13606531232"; // 对方的account
    private String displayName = "陈顶顶"; // 对方的显示昵称
    private int state = 0; // calltype 音频或视频
    private AVChatData avChatData = null;


    @Override
    protected View onCreateView() {
        view = View.inflate(getActivity(), R.layout.fragment_treatting, null);
        ButterKnife.bind(this, view);
        initData();
        showUI();
        return view;
    }

    private void showUI() {
        AVChatSoundPlayer.instance().play(AVChatSoundPlayer.RingerTypeEnum.CONNECTING);
        avChatVideoUI.doOutgoingCall(receiverId);
    }

    private void initData() {
        avChatController = new AVChatController(getActivity(), avChatData);
//        avChatVideoUI = new AVChatVideoUI(getContext(), view, avChatData, displayName, avChatController, null, null);
    }


    private void dismissKeyguard() {
        getActivity().getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                        WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
                        WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
                        WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
        );
    }

}
