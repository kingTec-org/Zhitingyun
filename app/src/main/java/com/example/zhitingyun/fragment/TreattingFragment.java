package com.example.zhitingyun.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.zhitingyun.R;
import com.example.zhitingyun.activity.Main2Activity;
import com.example.zhitingyun.base.BaseFragment;
import com.example.zhitingyun.model.EquipmentParamater;
import com.example.zhitingyun.model.MessageEvent;
import com.example.zhitingyun.model.OrderDetail;
import com.example.zhitingyun.model.PostParamater;
import com.example.zhitingyun.mvp.Presenter;
import com.example.zhitingyun.mvp.ViewsContainer;
import com.example.zhitingyun.yunxin.AVChatController;
import com.example.zhitingyun.yunxin.AVChatExitCode;
import com.example.zhitingyun.yunxin.AVChatSoundPlayer;
import com.example.zhitingyun.yunxin.AVChatTimeoutObserver;
import com.example.zhitingyun.yunxin.AVChatVideoUI;
import com.example.zhitingyun.yunxin.AVSwitchListener;
import com.example.zhitingyun.yunxin.OnFunctionClickListener;
import com.example.zhitingyun.yunxin.PhoneCallStateObserver;
import com.example.zhitingyun.yunxin.SimpleAVChatStateObserver;
import com.google.gson.Gson;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.StatusCode;
import com.netease.nimlib.sdk.auth.AuthServiceObserver;
import com.netease.nimlib.sdk.auth.ClientType;
import com.netease.nimlib.sdk.avchat.AVChatManager;
import com.netease.nimlib.sdk.avchat.constant.AVChatControlCommand;
import com.netease.nimlib.sdk.avchat.constant.AVChatEventType;
import com.netease.nimlib.sdk.avchat.constant.AVChatType;
import com.netease.nimlib.sdk.avchat.model.AVChatAudioFrame;
import com.netease.nimlib.sdk.avchat.model.AVChatCalleeAckEvent;
import com.netease.nimlib.sdk.avchat.model.AVChatCommonEvent;
import com.netease.nimlib.sdk.avchat.model.AVChatControlEvent;
import com.netease.nimlib.sdk.avchat.model.AVChatData;
import com.netease.nimlib.sdk.avchat.model.AVChatOnlineAckEvent;
import com.netease.nimlib.sdk.avchat.model.AVChatVideoFrame;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;
import coder.mylibrary.util.LogUtil;
import coder.mylibrary.util.SPUtil;
import coder.mylibrary.util.ToastUtil;

/**
 * Created by dasiy on 2018/7/10.
 */

@SuppressLint("ValidFragment")
public class TreattingFragment extends BaseFragment implements AVChatVideoUI.TouchZoneCallback, AVSwitchListener, OnFunctionClickListener, ViewsContainer.OrderTreattingView {

    private boolean mIsInComingCall = false;// is incoming call or outgoing call
    private String receiverId = "13606531232"; // 对方的account
    private String displayName = "陈顶顶"; // 对方的显示昵称
    private int state = 2; // calltype 音频或视频
    private AVChatData avChatData = null; // config for connect video server
    AVChatController avChatController;
    View view;
    AVChatVideoUI avChatVideoUI;
    OrderDetail orderDetail;
    List<EquipmentParamater> list;
    int from;
    Presenter presenter;

    private CountDownTimer mTimer;


    public TreattingFragment(OrderDetail orderDetail, String receiverId, int from) {
        this.orderDetail = orderDetail;
        this.receiverId = receiverId;
        this.from = from;
        if (avChatData != null)
            mIsInComingCall = true;

    }

    @Override
    protected View onCreateView() {
        super.onCreateView();
        view = View.inflate(getActivity(), R.layout.fragment_treatting, null);
        ButterKnife.bind(this, view);
        presenter = new Presenter( this );
        dismissKeyguard();
        initData();
        showUI();
        registerObserves(true);
        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
        if (messageEvent.getFlag() == 0) {
        }
        else if (messageEvent.getFlag() == 100) {
            if (!messageEvent.getObject().toString().equals("pong")) {
                PostParamater postParamater = new Gson().fromJson(messageEvent.getObject().toString() + "", PostParamater.class);
                list = postParamater.getMessage();
                SPUtil.put(getContext(), "equipmentParamaters", new Gson().toJson(list));
                ToastUtil.showLong( getContext(), "获取设备数据成功，已可进行设备调节" );
            }

//            EventBus.getDefault().post(new MessageEvent(1, orderDetail.getId()));
//        else if (messageEvent.getFlag() == 100) {
//            if(messageEvent.getObject().toString().equals("pong")){
//
//            }else if(messageEvent.getObject().toString().equals("abcd"))
//                ToastUtil.showShort( "" );
//            else {
//                PostParamater postParamater = new Gson().fromJson(messageEvent.getObject().toString() + "", PostParamater.class);
//                list = postParamater.getMessage();
//                SPUtil.put(getContext(), "equipmentParamaters", new Gson().toJson(list));
//            }

//            if (!messageEvent.getObject().toString().equals("pong")) {
//
//
//            }

//            ToastUtil.showShort(messageEvent.getObject().toString());
//            Map map0 = new HashMap();
//            map0.put("equipmentIds", messageEvent.getObject().toString());
//            presenter.getParamaterList(map0, 1);
        }

    }

//
//    @Override
//    public void onResume() {
//        super.onResume();
//        avChatVideoUI.onResume();
//        avChatController.resumeVideo();
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        avChatController.pauseVideo();
//    }

//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == event.KEYCODE_BACK
//                && event.getAction() == KeyEvent.ACTION_DOWN) {
//
//
//            return true;
//        }
//        return false;
//    }
//
//    @OnClick({R.id.imgPatient, R.id.imgEquipment, R.id.imgSpeech, R.id.btOver})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.imgPatient:
//
//                break;
//            case R.id.imgEquipment:
//                break;
//            case R.id.imgSpeech:
//                break;
//            case R.id.btOver:
//                startFragment(new WriteReportFragment(orderDetail.getId()));
//                break;
//        }
//    }


    private void registerObserves(boolean register) {
        AVChatManager.getInstance().observeAVChatState(avchatStateObserver, register);//视频录制
        AVChatManager.getInstance().observeHangUpNotification(callHangupObserver, register);
        AVChatManager.getInstance().observeCalleeAckNotification(callAckObserver, register);
        AVChatManager.getInstance().observeControlNotification(callControlObserver, register);
        AVChatTimeoutObserver.getInstance().observeTimeoutNotification(timeoutObserver, register, mIsInComingCall);
        PhoneCallStateObserver.getInstance().observeAutoHangUpForLocalPhone(autoHangUpForLocalPhoneObserver, register);
        NIMClient.getService(AuthServiceObserver.class).observeOnlineStatus(userStatusObserver, register);
    }

    Observer<StatusCode> userStatusObserver = new Observer<StatusCode>() {

        @Override
        public void onEvent(StatusCode code) {
            if (code.wontAutoLogin()) {
                AVChatSoundPlayer.instance().stop();
                //logout  finish
            }
        }
    };
    Observer<AVChatOnlineAckEvent> onlineAckObserver = new Observer<AVChatOnlineAckEvent>() {
        @Override
        public void onEvent(AVChatOnlineAckEvent ackInfo) {
            if (state == AVChatType.AUDIO.getValue()) {
                avChatData = avChatController.getAvChatData();
            } else {
                avChatData = avChatVideoUI.getAvChatData();
            }
            if (avChatData != null && avChatData.getChatId() == ackInfo.getChatId()) {
                AVChatSoundPlayer.instance().stop();

                String client = null;
                switch (ackInfo.getClientType()) {
                    case ClientType.Web:
                        client = "Web";
                        break;
                    case ClientType.Windows:
                        client = "Windows";
                        break;
                    case ClientType.Android:
                        client = "Android";
                        break;
                    case ClientType.iOS:
                        client = "iOS";
                        break;
                    case ClientType.MAC:
                        client = "Mac";
                        break;
                    default:
                        break;
                }
                if (client != null) {
                    String option = ackInfo.getEvent() == AVChatEventType.CALLEE_ONLINE_CLIENT_ACK_AGREE ? "接听！" : "拒绝！";
                    Toast.makeText(getActivity(), "通话已在" + client + "端被" + option, Toast.LENGTH_SHORT).show();
                }
                popBackStack();
            }
        }
    };

    private void initData() {
        avChatController = new AVChatController(getActivity(), avChatData);
        avChatVideoUI = new AVChatVideoUI(mIsInComingCall, getContext(), view, avChatData, displayName, avChatController, this, this, this);
    }

    Observer<AVChatControlEvent> callControlObserver = new Observer<AVChatControlEvent>() {
        @Override
        public void onEvent(AVChatControlEvent netCallControlNotification) {
            handleCallControl(netCallControlNotification);
        }
    };

    //     设置窗口flag，亮屏并且解锁/覆盖在锁屏界面上
    private void dismissKeyguard() {
        getActivity().getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                        WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
                        WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
                        WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
        );
    }

    Observer<Integer> autoHangUpForLocalPhoneObserver = new Observer<Integer>() {
        @Override
        public void onEvent(Integer integer) {
            avChatController.onHangUp(AVChatExitCode.PEER_BUSY);
        }
    };

    private void showUI() {
//        AVChatSoundPlayer.instance().play(AVChatSoundPlayer.RingerTypeEnum.CONNECTING);
//        avChatVideoUI.doOutgoingCall(receiverId);
        if (mIsInComingCall) {
            // 来电
            AVChatSoundPlayer.instance().play(AVChatSoundPlayer.RingerTypeEnum.RING);
            avChatVideoUI.showIncomingCall(avChatData);
        } else {
            // 去电
            AVChatSoundPlayer.instance().play(AVChatSoundPlayer.RingerTypeEnum.CONNECTING);
            avChatVideoUI.doOutgoingCall(receiverId);
        }
    }

    private void handleCallControl(AVChatControlEvent notification) {
        if (AVChatManager.getInstance().getCurrentChatId() != notification.getChatId()) {
            return;
        }
        switch (notification.getControlCommand()) {
            case AVChatControlCommand.SWITCH_AUDIO_TO_VIDEO:
                avChatVideoUI.incomingAudioToVideo();

                break;
            case AVChatControlCommand.SWITCH_AUDIO_TO_VIDEO_AGREE:
                // 对方同意切成视频啦
                state = AVChatType.VIDEO.getValue();
                avChatVideoUI.onAudioToVideoAgree(notification.getAccount());
                break;
            case AVChatControlCommand.SWITCH_AUDIO_TO_VIDEO_REJECT:
//                rejectAudioToVideo();
                Toast.makeText(getActivity(), R.string.avchat_switch_video_reject, Toast.LENGTH_SHORT).show();
                break;
            case AVChatControlCommand.SWITCH_VIDEO_TO_AUDIO:
                onVideoToAudio();
                break;
            case AVChatControlCommand.NOTIFY_VIDEO_OFF:
                // 收到对方关闭画面通知
                if (state == AVChatType.VIDEO.getValue()) {
                    avChatVideoUI.peerVideoOff();
                }
                break;
            case AVChatControlCommand.NOTIFY_VIDEO_ON:
                // 收到对方打开画面通知
                if (state == AVChatType.VIDEO.getValue()) {
                    avChatVideoUI.peerVideoOn();
                }
                break;
            default:
//                Toast.makeText(getActivity(), "对方发来指令值：" + notification.getControlCommand(), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    // 通话过程中，收到对方挂断电话
    Observer<AVChatCommonEvent> callHangupObserver = new Observer<AVChatCommonEvent>() {
        @Override
        public void onEvent(AVChatCommonEvent avChatHangUpInfo) {
            avChatData = avChatController.getAvChatData();
            if (avChatData != null && avChatData.getChatId() == avChatHangUpInfo.getChatId()) {
                avChatController.onHangUp(AVChatExitCode.HANGUP);
                ToastUtil.showShort("用户挂断视频通话，请重新发起通话");
                EventBus.getDefault().post(new MessageEvent(6, null));
//                EventBus.getDefault().post(new MessageEvent(1, orderDetail.getId()));

//                cancelCallingNotifier();
//                // 如果是incoming call主叫方挂断，那么通知栏有通知
//                if (mIsInComingCall && !isCallEstablished) {
//                    activeMissCallNotifier();
//                }
            }

        }
    };

    // 呼叫时，被叫方的响应（接听、拒绝、忙）
    Observer<AVChatCalleeAckEvent> callAckObserver = new Observer<AVChatCalleeAckEvent>() {
        @Override
        public void onEvent(AVChatCalleeAckEvent ackInfo) {
            AVChatData info = avChatController.getAvChatData();
            if (info != null && info.getChatId() == ackInfo.getChatId()) {
                AVChatSoundPlayer.instance().stop();
                if (ackInfo.getEvent() == AVChatEventType.CALLEE_ACK_AGREE) {
                    EventBus.getDefault().post(new MessageEvent(3, null));
                    avChatVideoUI.startCamera();

                    mTimer=new CountDownTimer(25000L,1000L) {
                        @Override
                        public void onTick(long millisUntilFinished) {

                        }

                        @Override
                        public void onFinish() {
//                            ToastUtil.showShort( "" );
                            if(list==null){
                            new QMUIDialog.MessageDialogBuilder( getActivity() )
                                    .setMessage( "用户设备数据缺失，请挂断后重新发起诊疗。" )
                                    .addAction( "确认", new QMUIDialogAction.ActionListener() {
                                        @Override
                                        public void onClick(QMUIDialog dialog, int index) {
                                            dialog.dismiss();
                                        }
                                    } )
                                    .create(com.qmuiteam.qmui.R.style.QMUI_Dialog).show();
                        }}
                    }.start();



                } else if (ackInfo.getEvent() == AVChatEventType.CALLEE_ACK_BUSY) {

                    AVChatSoundPlayer.instance().play(AVChatSoundPlayer.RingerTypeEnum.PEER_BUSY);

                    avChatController.onHangUp(AVChatExitCode.PEER_BUSY);
                    EventBus.getDefault().post(new MessageEvent(6, null));

                } else if (ackInfo.getEvent() == AVChatEventType.CALLEE_ACK_REJECT) {
                    avChatController.onHangUp(AVChatExitCode.REJECT);
                    EventBus.getDefault().post(new MessageEvent(6, null));
                } else if (ackInfo.getEvent() == AVChatEventType.CALLEE_ACK_AGREE) {
                    avChatController.isCallEstablish.set(true);
                }
            }
        }
    };

    protected void handleWithConnectServerResult(int auth_result) {
        if (auth_result == 200) {
//            LogUtil.d(TAG, "onConnectServer success");
        } else if (auth_result == 101) { // 连接超时
            avChatController.showQuitToast(AVChatExitCode.PEER_NO_RESPONSE);
            EventBus.getDefault().post(new MessageEvent(4, null));
        } else if (auth_result == 401) { // 验证失败
            avChatController.showQuitToast(AVChatExitCode.CONFIG_ERROR);
            EventBus.getDefault().post(new MessageEvent(4, null));
        } else if (auth_result == 417) { // 无效的channelId
            avChatController.showQuitToast(AVChatExitCode.INVALIDE_CHANNELID);
            EventBus.getDefault().post(new MessageEvent(4, null));
        } else { // 连接服务器错误，直接退出
            avChatController.showQuitToast(AVChatExitCode.CONFIG_ERROR);
            EventBus.getDefault().post(new MessageEvent(4, null));
        }
    }

    private SimpleAVChatStateObserver avchatStateObserver = new SimpleAVChatStateObserver() {
        @Override
        public void onAVRecordingCompletion(String account, String filePath) {
            if (account != null && filePath != null && filePath.length() > 0) {
                String msg = "音视频录制已结束, " + "账号：" + account + " 录制文件已保存至：" + filePath;
            } else {
            }
//            if (state == AVChatType.VIDEO.getValue()) {
//                avChatVideoUI.resetRecordTip();
//            } else {
//                avChatAudioUI.resetRecordTip();
//            }
        }

        @Override
        public void onAudioRecordingCompletion(String filePath) {
            if (filePath != null && filePath.length() > 0) {
                String msg = "音频录制已结束, 录制文件已保存至：" + filePath;
            } else {
            }
//            if (state == AVChatType.AUDIO.getValue()) {
//                avChatAudioUI.resetRecordTip();
//            } else {
//                avChatVideoUI.resetRecordTip();
//            }
        }

        @Override
        public void onLowStorageSpaceWarning(long availableSize) {
            if (state == AVChatType.VIDEO.getValue()) {
//                avChatVideoUI.showRecordWarning();
            } else {
//                avChatAudioUI.showRecordWarning();
            }
        }

        @Override
        public void onJoinedChannel(int code, String audioFile, String videoFile, int i) {
            handleWithConnectServerResult(code);

        }

        @Override
        public void onUserJoined(String account) {
            if (state == AVChatType.VIDEO.getValue()) {
                avChatVideoUI.initLargeSurfaceView(account);
            }
        }

        @Override
        public void onUserLeave(String account, int event) {
            avChatController.hangUp(AVChatExitCode.HANGUP);
        }

        @Override
        public void onCallEstablished() {
            //移除超时监听
            AVChatTimeoutObserver.getInstance().observeTimeoutNotification(timeoutObserver, false, mIsInComingCall);
            if (avChatController.getTimeBase() == 0)
                avChatController.setTimeBase(SystemClock.elapsedRealtime());

            if (state == AVChatType.AUDIO.getValue()) {
//                avChatAudioUI.showAudioInitLayout();
            } else {
                // 接通以后，自己是小屏幕显示图像，对方是大屏幕显示图像
                avChatVideoUI.initLargeSurfaceView(SPUtil.get(getContext(), "phone", "").toString());
                avChatVideoUI.initSmallSurfaceView(SPUtil.get(getContext(), "phone", "").toString());
//                avChatVideoUI.showVideoInitLayout();
            }
//            isCallEstablished = true;
        }

        @Override
        public boolean onVideoFrameFilter(AVChatVideoFrame frame, boolean maybeDualInput) {
//            if (faceU != null) {
//                faceU.effect(frame.data, frame.width, frame.height, FaceU.VIDEO_FRAME_FORMAT.I420);
//            }

            return true;
        }

        @Override
        public boolean onAudioFrameFilter(AVChatAudioFrame frame) {
            return true;
        }

    };

    //    // 通话过程状态监听
//    private SimpleAVChatStateObserver avchatStateObserver = new SimpleAVChatStateObserver() {
//
//        @Override
//        public void onJoinedChannel(int code, String audioFile, String videoFile, int i) {
////            LogUtil.d(TAG, "audioFile -> " + audioFile+" videoFile -> " + videoFile);
//            handleWithConnectServerResult(code);
//        }
//
//
////        @Override
////        public void onCallEstablished() {
//////            LogUtil.d(TAG, "onCallEstablished");
////            //移除超时监听
////            AVChatTimeoutObserver.getInstance().observeTimeoutNotification(timeoutObserver, false, mIsInComingCall);
////            if (avChatController.getTimeBase() == 0)
////                avChatController.setTimeBase(SystemClock.elapsedRealtime());
////
////            if (state == AVChatType.AUDIO.getValue()) {
//////                avChatVideoUI.showAudioInitLayout();
////            } else {
////                // 接通以后，自己是小屏幕显示图像，对方是大屏幕显示图像
//////                avChatVideoUI.initLargeSurfaceView("18258139327");
//////                avChatVideoUI.initSmallSurfaceView("18258139327");
//////                avChatVideoUI.showVideoInitLayout();
////            }
//////            isCallEstablished = true;
////        }
//
////        @Override
////        public boolean onVideoFrameFilter(AVChatVideoFrame frame, boolean maybeDualInput) {
////            if (faceU != null) {
////                faceU.effect(frame.data, frame.width, frame.height, FaceU.VIDEO_FRAME_FORMAT.I420);
////            }
////
////            return true;
////        }
////
////        @Override
////        public boolean onAudioFrameFilter(AVChatAudioFrame frame) {
////            return true;
////        }
//
//    };
    Observer<Integer> timeoutObserver = new Observer<Integer>() {
        @Override
        public void onEvent(Integer integer) {

            avChatController.hangUp(AVChatExitCode.CANCEL);

            // 来电超时，自己未接听


            if (mIsInComingCall) {
//                activeMissCallNotifier();
            }

            getActivity().finish();
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mTimer != null) {
            mTimer.cancel();
        }
//        if (avChatAudioUI != null) {
//            avChatVideoUI.onDestroy();
//        }
//
//        if (avChatVideoUI != null) {
//            avChatVideoUI.onDestroy();
//        }

        registerObserves(false);
//        AVChatProfile.getInstance().setAVChatting(false);
//        cancelCallingNotifier();
//        destroyFaceU();
//        needFinish = true;
    }


    @Override
    public void onVideoToAudio() {
        //转音频
        avChatVideoUI.changeVideoToAudio();


    }

    @Override
    public void onAudioToVideo() {
        //转视频
        ToastUtil.showShort("正在等待对方打开摄像头...");
//        avChatVideoUI.changeAudioToVideo();

    }

    @Override
    public void onReceiveAudioToVideoAgree() {
        avChatVideoUI.onAudioToVideoAgree(receiverId);
    }

    @Override
    public void onTouch() {

    }

    @Override
    protected boolean canDragBack() {
        return false;
    }

    @Override
    public void onBtPatientClick() {
//        getActivity().startActivity(new Intent(getActivity(), Main2Activity.class));
        startFragment(new PatientInfoFragment(orderDetail));

    }

    @Override
    public void onImgPatientClick() {
        startFragment(new PatientInfoFragment(orderDetail));

    }

    @Override
    public void onImgEquipClick() {
        if (list == null) {
            ToastUtil.showShort( "用户设备未连接或网络延迟，尚未获取设备数据" );
        }
        else {
//            ToastUtil.showLong( getContext(), "获取设备数据成功，已可进行设备调节" );
            startFragment( new MonitorEquipmentFragment( orderDetail, list ) );
        }

    }

    @Override
    public void onImgPatient0Click() {
        startFragment(new PatientInfoFragment(orderDetail));

    }

    @Override
    public void onImgEquip0Click() {
        if (list == null) {
            ToastUtil.showShort( "用户设备未连接或网络延迟，尚未获取设备数据" );
        }
        else {
//            ToastUtil.showLong( getContext(), "获取设备数据成功，已可进行设备调节" );
            startFragment( new MonitorEquipmentFragment( orderDetail, list ) );
        }

    }

    @Override
    public void onCancelClick() {

        Map map2 = new HashMap();
        map2.put("classify", orderDetail.getClassify());
        map2.put("orderId", orderDetail.getId());
        if (orderDetail.getClassify() == 1) {
            presenter.cancelOrder(map2);
        } else if (orderDetail.getClassify() == 2) {
            switch (orderDetail.getStatus()) {
                case 1:
                    /*未接通状态取消诊疗*/
                    presenter.cancelHaveNotBegunOrder(map2);
                    break;
                case 3:
                    /*诊疗过程中取消诊疗*/
                    presenter.cancelOrder(map2);
                    break;
            }
        }
    }

    @Override
    public void onTreatmentOver() {
        new QMUIDialog.MessageDialogBuilder(getActivity())
                .setTitle("提示")
                .setMessage("是否结束本次预约")
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction("确定", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        Log.d("ggg", "ggg");
                        dialog.dismiss();
                        avChatVideoUI.doHangUp();
                        switch (from) {
                            case 1://mainFragment
                                EventBus.getDefault().post(new MessageEvent(5, orderDetail.getId()));

                                break;
                            case 2://orderDetailFragment
                                EventBus.getDefault().post(new MessageEvent(5, null));

                                break;
                        }

                    }
                })

                .create(com.qmuiteam.qmui.R.style.QMUI_Dialog).show();


    }


    @Override
    public void getParamaterList(List<EquipmentParamater> list) {
        this.list = list;


    }
}
