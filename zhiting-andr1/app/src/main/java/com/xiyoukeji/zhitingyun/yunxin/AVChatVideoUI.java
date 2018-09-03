package com.xiyoukeji.zhitingyun.yunxin;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.netease.nimlib.sdk.avchat.AVChatCallback;
import com.netease.nimlib.sdk.avchat.AVChatManager;
import com.netease.nimlib.sdk.avchat.constant.AVChatControlCommand;
import com.netease.nimlib.sdk.avchat.constant.AVChatType;
import com.netease.nimlib.sdk.avchat.constant.AVChatVideoScalingType;
import com.netease.nimlib.sdk.avchat.model.AVChatData;
import com.netease.nimlib.sdk.avchat.model.AVChatSurfaceViewRenderer;
import com.xiyoukeji.zhitingyun.R;
import com.xiyoukeji.zhitingyun.base.BaseActivity;
import com.xiyoukeji.zhitingyun.constant.Urls;
import com.xiyoukeji.zhitingyun.widget.RoundImageView;

import org.greenrobot.eventbus.EventBus;
import org.reactivestreams.Subscriber;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;


/**
 * 视频界面变化及点击事件
 * Created by winnie on 2017/12/11.
 */

public class AVChatVideoUI implements View.OnClickListener {

    // constant
    private static final int PEER_CLOSE_CAMERA = 0;
    private static final int LOCAL_CLOSE_CAMERA = 1;
    private static final int AUDIO_TO_VIDEO_WAIT = 2;
    private static final int TOUCH_SLOP = 10;
    private static final String TAG = AVChatVideoUI.class.getSimpleName();

    private final String[] BASIC_PERMISSIONS = new String[]{Manifest.permission.CAMERA,};

    /**
     * surface view
     */
    private LinearLayout largeSizePreviewLayout;
    private FrameLayout smallSizePreviewFrameLayout;
    private LinearLayout smallSizePreviewLayout;
    private ImageView smallSizePreviewCoverImg;//stands for peer or local close camera
    private View largeSizePreviewCoverLayout;//stands for peer or local close camera
    private View touchLayout;
    //    Subscription subscription;
    View buttonView, videoRoot, audioRoot, waittingView, videoingRoot;

    /**
     * video view
     */
    //顶部控制按钮
//    private View topRoot;
//    private View switchAudio;
//    private Chronometer time;
//    private TextView netUnstableTV;
//    //中间控制按钮
//    private View middleRoot;
//    private HeadImageView headImg;
//    private TextView nickNameTV;
//    private TextView notifyTV;
//    private View refuse_receive;
//    private TextView refuseTV;
//    private TextView receiveTV;
//    //底部控制按钮
//    private View bottomRoot;
//    ToggleView switchCameraToggle;
//    ToggleView closeCameraToggle;
//    ToggleView muteToggle;
//    ImageView recordToggle;
//    ImageView hangUpImg;
    //face unity
//    private View faceUnityRoot;
    //摄像头权限提示显示
//    private View permissionRoot;
    //record
//    private View recordView;
//    private View recordTip;
//    private View recordWarning;

    //render
    private AVChatSurfaceViewRenderer smallRender;
    private AVChatSurfaceViewRenderer largeRender;

    // state
    private boolean surfaceInit = false;
    private boolean videoInit = false;
    //    private boolean shouldEnableToggle = false;
//    public boolean canSwitchCamera = false;
//    private boolean isInSwitch = false;
//    private boolean isPeerVideoOff = false;
//    private boolean isLocalVideoOff = false;
    private boolean localPreviewInSmallSize = true;
//    private boolean isRecordWarning = false;

    // data
    private TouchZoneCallback touchZoneCallback;
    private AVChatData avChatData;
    private String account;
    private String displayName;

//    private int topRootHeight = 0;
//    private int bottomRootHeight = 0;

    private String largeAccount; // 显示在大图像的用户id
    private String smallAccount; // 显示在小图像的用户id

    // move
    private int lastX, lastY;
    private int inX, inY;
    private Rect paddingRect;

    private Context context;
    private View root;
    private AVChatController avChatController;
    private AVSwitchListener avSwitchListener;
    OnFunctionClickListener onFunctionClickListener;
    boolean mIsInComingCall;

    // touch zone
    public interface TouchZoneCallback {
        void onTouch();
    }

    public AVChatVideoUI(boolean mIsInComingCall, Context context, View root, AVChatData avChatData, String displayName,
                         AVChatController avChatController, TouchZoneCallback touchZoneCallback,
                         AVSwitchListener avSwitchListener, OnFunctionClickListener onFunctionClickListener) {
        this.mIsInComingCall = mIsInComingCall;
        this.context = context;
        this.root = root;
        this.avChatData = avChatData;
        this.displayName = displayName;
        this.avChatController = avChatController;
        this.touchZoneCallback = touchZoneCallback;
        this.avSwitchListener = avSwitchListener;
        this.smallRender = new AVChatSurfaceViewRenderer(context);
        this.largeRender = new AVChatSurfaceViewRenderer(context);
        this.onFunctionClickListener = onFunctionClickListener;
    }

    /**
     * ********************** surface 初始化 **********************
     */

    private void findSurfaceView() {
        if (surfaceInit)
            return;
        View surfaceView = root.findViewById(R.id.avchat_surface_layout);
        surfaceView.setVisibility(View.VISIBLE);
        if (surfaceView != null) {
            touchLayout = surfaceView.findViewById(R.id.touch_zone);
            touchLayout.setOnTouchListener(touchListener);

            smallSizePreviewFrameLayout = (FrameLayout) surfaceView.findViewById(R.id.small_size_preview_layout);
            smallSizePreviewLayout = (LinearLayout) surfaceView.findViewById(R.id.small_size_preview);
            smallSizePreviewCoverImg = (ImageView) surfaceView.findViewById(R.id.smallSizePreviewCoverImg);
            smallSizePreviewFrameLayout.setOnTouchListener(smallPreviewTouchListener);

            largeSizePreviewLayout = (LinearLayout) surfaceView.findViewById(R.id.large_size_preview);
            largeSizePreviewCoverLayout = surfaceView.findViewById(R.id.notificationLayout);

            surfaceInit = true;
        }
    }


    private View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_UP && touchZoneCallback != null) {
                touchZoneCallback.onTouch();
            }

            return true;
        }
    };

    private View.OnTouchListener smallPreviewTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(final View v, MotionEvent event) {
            int x = (int) event.getRawX();
            int y = (int) event.getRawY();

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    lastX = x;
                    lastY = y;
                    int[] p = new int[2];
                    smallSizePreviewFrameLayout.getLocationOnScreen(p);
                    inX = x - p[0];
                    inY = y - p[1];
                    inY = y - p[1];

                    break;
                case MotionEvent.ACTION_MOVE:
                    final int diff = Math.max(Math.abs(lastX - x), Math.abs(lastY - y));
                    if (diff < TOUCH_SLOP)
                        break;

                    if (paddingRect == null) {
                        paddingRect = new Rect(ScreenUtil.dip2px(10), ScreenUtil.dip2px(20), ScreenUtil.dip2px(10),
                                ScreenUtil.dip2px(70));
                    }

                    int destX, destY;
                    if (x - inX <= paddingRect.left) {
                        destX = paddingRect.left;
                    } else if (x - inX + v.getWidth() >= ScreenUtil.screenWidth - paddingRect.right) {
                        destX = ScreenUtil.screenWidth - v.getWidth() - paddingRect.right;
                    } else {
                        destX = x - inX;
                    }

                    if (y - inY <= paddingRect.top) {
                        destY = paddingRect.top;
                    } else if (y - inY + v.getHeight() >= ScreenUtil.screenHeight - paddingRect.bottom) {
                        destY = ScreenUtil.screenHeight - v.getHeight() - paddingRect.bottom;
                    } else {
                        destY = y - inY;
                    }

                    FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) v.getLayoutParams();
                    params.gravity = Gravity.NO_GRAVITY;
                    params.leftMargin = destX;
                    params.topMargin = destY;
                    v.setLayoutParams(params);

                    break;
                case MotionEvent.ACTION_UP:
                    if (Math.max(Math.abs(lastX - x), Math.abs(lastY - y)) <= 5) {
                        if (largeAccount == null || smallAccount == null) {
                            return true;
                        }
                        String temp;
                        switchRender(smallAccount, largeAccount);
                        temp = largeAccount;
                        largeAccount = smallAccount;
                        smallAccount = temp;
                        switchAndSetLayout();
                    }

                    break;
            }

            return true;
        }
    };

    // 大小图像显示切换
    private void switchRender(String user1, String user2) {

        //先取消用户的画布
        if (SPUtil.get(context, "phone", "").toString().equals(user1)) {
            AVChatManager.getInstance().setupLocalVideoRender(null, false, 0);
        } else {
            AVChatManager.getInstance().setupRemoteVideoRender(user1, null, false, 0);
        }
        if (SPUtil.get(context, "phone", "").toString().equals(user2)) {
            AVChatManager.getInstance().setupLocalVideoRender(null, false, 0);
        } else {
            AVChatManager.getInstance().setupRemoteVideoRender(user2, null, false, 0);
        }
        //交换画布
        //如果存在多个用户,建议用Map维护account,render关系.
        //目前只有两个用户,并且认为这两个account肯定是对的
        AVChatSurfaceViewRenderer render1;
        AVChatSurfaceViewRenderer render2;
        if (user1.equals(smallAccount)) {
            render1 = largeRender;
            render2 = smallRender;
        } else {
            render1 = smallRender;
            render2 = largeRender;
        }

        //重新设置上画布
        if (user1.equals(SPUtil.get(context, "phone", "").toString())) {
            AVChatManager.getInstance().setupLocalVideoRender(render1, false, AVChatVideoScalingType.SCALE_ASPECT_BALANCED);
        } else {
            AVChatManager.getInstance().setupRemoteVideoRender(user1, render1, false, AVChatVideoScalingType.SCALE_ASPECT_BALANCED);
        }
        if (user2.equals(SPUtil.get(context, "phone", "").toString())) {
            AVChatManager.getInstance().setupLocalVideoRender(render2, false, AVChatVideoScalingType.SCALE_ASPECT_BALANCED);
        } else {
            AVChatManager.getInstance().setupRemoteVideoRender(user2, render2, false, AVChatVideoScalingType.SCALE_ASPECT_BALANCED);
        }
    }

    /**
     * ************************** video 初始化 ***********************
     */

    private void findAudioViews() {
        audioRoot = root.findViewById(R.id.avchat_audio_layout);
        audioRoot.setVisibility(View.GONE);
        ImageView imgSpeech = audioRoot.findViewById(R.id.imgSpeech);
        ImageView imgOver = audioRoot.findViewById(R.id.imgOver);
        TextView tvTime = audioRoot.findViewById(R.id.tvTime);
//        QMUIRoundButton btOver = audioRoot.findViewById(R.id.btAudioOver);
        imgSpeech.setOnClickListener(this);
        imgOver.setOnClickListener(this);
    }

    private void findVideoingViews() {
        videoingRoot = root.findViewById(R.id.avchat_wait_layout);
        videoingRoot.setVisibility(View.GONE);
        ImageView imgVideo = videoingRoot.findViewById(R.id.imgVideo);
        ImageView imgVideoOver = videoingRoot.findViewById(R.id.imgVideoOver);
        imgVideo.setOnClickListener(this);
        imgVideoOver.setOnClickListener(this);
    }

    private void findVideoViews() {
        if (videoInit)
            return;
        videoRoot = root.findViewById(R.id.avchat_video_layout);
        videoRoot.setVisibility(View.GONE);
        ImageView imgVideoOver = videoRoot.findViewById(R.id.imgVideoOver);
        ImageView imgStart = videoRoot.findViewById(R.id.imgStart);

        RoundImageView imgAvatar = videoRoot.findViewById(R.id.imgAvatar);
        TextView tvName = videoRoot.findViewById(R.id.tvName);

        SharedPreferences sharedPreferences = context.getSharedPreferences("mySP", Context.MODE_PRIVATE);
        String headpic = sharedPreferences.getString("videoheadpic", "");
        Glide.with(context).load( Urls.BASE_URL + "/"+headpic).into(imgAvatar);
        tvName.setText( sharedPreferences.getString("videoname", ""));


        imgVideoOver.setOnClickListener(this);
        imgStart.setOnClickListener(this);

        videoInit = true;
    }

    public void onResume() {
        surfaceViewFixBefore43(smallSizePreviewLayout, largeSizePreviewLayout);
    }

//    public void onDestroy() {
//        if (time != null) {
//            time.stop();
//        }
//    }

    /**
     * ********************** 视频流程 **********************
     */

    public void startCamera() {
//        if (subscription != null)
//            subscription.unsubscribe();

        buttonView.setVisibility(View.GONE);
        videoRoot.setVisibility(View.VISIBLE);
        initLargeSurfaceView(SPUtil.get(context, "phone", "").toString());


    }

    public void changeVideoToAudio() {
        videoingRoot.setVisibility(View.VISIBLE);
        audioRoot.setVisibility(View.GONE);
        smallSizePreviewFrameLayout.setVisibility(View.GONE);
        smallSizePreviewCoverImg.setVisibility(View.GONE);
        largeSizePreviewCoverLayout.setVisibility(View.VISIBLE);
        largeSizePreviewLayout.setVisibility(View.GONE);
    }

    public void changeAudioToVideo() {
        audioRoot.setVisibility(View.GONE);
        videoRoot.setVisibility(View.VISIBLE);
//        smallSizePreviewFrameLayout.setVisibility(View.VISIBLE);
//        largeSizePreviewCoverLayout.setVisibility(View.GONE);


        smallSizePreviewFrameLayout.setVisibility(View.VISIBLE);
        smallSizePreviewCoverImg.setVisibility(View.GONE);
        largeSizePreviewCoverLayout.setVisibility(View.GONE);
        largeSizePreviewLayout.setVisibility(View.VISIBLE);
    }


    public void showIncomingCall(AVChatData avChatData) {
        this.avChatData = avChatData;
        this.account = avChatData.getAccount();


        findSurfaceView();
        findVideoViews();
        findAudioViews();
        findVideoingViews();
        initBeWaittingView();
        videoRoot.setVisibility(View.VISIBLE);


//        avChatController.receive(AVChatType.VIDEO, new AVChatControllerCallback<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//                videoRoot.setVisibility(View.VISIBLE);
//
//            }
//
//            @Override
//            public void onFailed(int code, String errorMsg) {
//                closeSession();
//            }
//        });
    }

    public void receiveCall() {
        avChatController.receive(AVChatType.VIDEO, new AVChatControllerCallback<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                videoRoot.setVisibility(View.GONE);
                audioRoot.setVisibility(View.VISIBLE);
                onFunctionClickListener.onImgPatientClick();

            }

            @Override
            public void onFailed(int code, String errorMsg) {
                closeSession();
            }
        });
    }


    public void doOutgoingCall(String account) {
        this.account = account;

        findSurfaceView();
        findVideoViews();
        findAudioViews();
        findVideoingViews();
        initBeWaittingView();


        avChatController.doCalling(account, AVChatType.VIDEO, new AVChatControllerCallback<AVChatData>() {
            @Override
            public void onSuccess(AVChatData data) {
                avChatData = data;
                avChatController.setAvChatData(data);

//                initButtonView();


//                initLargeSurfaceView("18258139327");
            }

            @Override
            public void onFailed(int code, String errorMsg) {
                closeSession();
            }
        });
    }


    // 大图像surface view 初始化
    public void initLargeSurfaceView(String account) {
        // 设置画布，加入到自己的布局中，用于呈现视频图像
        // account 要显示视频的用户帐号
        largeAccount = account;
        if (SPUtil.get(context, "phone", "").toString().equals(account)) {
            AVChatManager.getInstance().setupLocalVideoRender(largeRender, false, AVChatVideoScalingType.SCALE_ASPECT_BALANCED);
        } else {
            AVChatManager.getInstance().setupRemoteVideoRender(account, largeRender, false, AVChatVideoScalingType.SCALE_ASPECT_BALANCED);
        }
        addIntoLargeSizePreviewLayout(largeRender);
    }

    private void addIntoLargeSizePreviewLayout(SurfaceView surfaceView) {
        if (surfaceView.getParent() != null) {
            ((ViewGroup) surfaceView.getParent()).removeView(surfaceView);
        }
        largeSizePreviewLayout.addView(surfaceView);
        surfaceView.setZOrderMediaOverlay(false);
        largeSizePreviewCoverLayout.setVisibility(View.GONE);
    }


//    private void initButtonView() {
//        buttonView = root.findViewById(R.id.avchat_video_waitting);
//        buttonView.setVisibility(View.VISIBLE);
//        final TextView tvTime = buttonView.findViewById(R.id.tvTime);
//        final QMUIRoundButton btCancel = buttonView.findViewById(R.id.btCancel);
//        final QMUIRoundButton btInfo = buttonView.findViewById(R.id.btInfo);
//        btCancel.setVisibility(View.GONE);
//
//        subscription = Observable.interval(0, 1, TimeUnit.SECONDS)
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Long>() {
//                    @Override
//                    public void accept(Long aLong) throws Exception {
//                        tvTime.setText((aLong / 60) + ":" + (aLong % 60));
//                    }
//                })
//                .subscribe(new Subscriber<Long>() {
//                    @Override
//                    public void onCompleted() {
//
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(Long aLong) {
//                        tvTime.setText((aLong / 60) + ":" + (aLong % 60));
//                        if (aLong < 900) {
//                            btCancel.setVisibility(View.GONE);
//                        } else
//                            btCancel.setVisibility(View.VISIBLE);
//
//
//                    }
//                });
//
//    }

    private void initBeWaittingView() {
        waittingView = root.findViewById(R.id.avchat_video_be_waitting);
        waittingView.setVisibility(View.GONE);
        final TextView tvAccept = waittingView.findViewById(R.id.tvAccept);
        final TextView tvRefuse = waittingView.findViewById(R.id.tvRefuse);
        tvAccept.setOnClickListener(this);
        tvRefuse.setOnClickListener(this);

    }

    // 小图像surface view 初始化
    public void initSmallSurfaceView(String account) {
        smallAccount = account;
        smallSizePreviewFrameLayout.setVisibility(View.VISIBLE);

        // 设置画布，加入到自己的布局中，用于呈现视频图像
        // account 要显示视频的用户帐号
        if (SPUtil.get(context, "phone", "").toString().equals(account)) {
            AVChatManager.getInstance().setupLocalVideoRender(null, false, AVChatVideoScalingType.SCALE_ASPECT_BALANCED);
            AVChatManager.getInstance().setupLocalVideoRender(smallRender, false, AVChatVideoScalingType.SCALE_ASPECT_BALANCED);
        } else {
            AVChatManager.getInstance().setupRemoteVideoRender(account, smallRender, false, AVChatVideoScalingType.SCALE_ASPECT_BALANCED);
        }
        addIntoSmallSizePreviewLayout(smallRender);

        smallSizePreviewFrameLayout.bringToFront();
    }

    private void addIntoSmallSizePreviewLayout(SurfaceView surfaceView) {
        smallSizePreviewCoverImg.setVisibility(View.GONE);
        if (surfaceView.getParent() != null) {
            ((ViewGroup) surfaceView.getParent()).removeView(surfaceView);
        }
        smallSizePreviewLayout.addView(surfaceView);
        surfaceView.setZOrderMediaOverlay(true);
        smallSizePreviewLayout.setVisibility(View.VISIBLE);
    }

    /**
     * ******************* 音视频切换 *******************
     */


    /********************** 界面显示 **********************************/


    // 摄像头切换时，布局显隐
    private void switchAndSetLayout() {
        localPreviewInSmallSize = !localPreviewInSmallSize;
        largeSizePreviewCoverLayout.setVisibility(View.GONE);
        smallSizePreviewCoverImg.setVisibility(View.GONE);
//        if (isPeerVideoOff) {
//            peerVideoOff();
//        }
//        if (isLocalVideoOff) {
//            localVideoOff();
//        }
    }

    /**
     * ******************** 点击事件 **********************
     */

    @Override
    public void onClick(View v) {

//        imgvideo.setOnClickListener(this);
//        imgVideoOver.setOnClickListener(this);
//        imgVideoOver.setOnClickListener(this);
//        imgStart.setOnClickListener(this);
//        tvAccept.setOnClickListener(this);
//        tvRefuse.setOnClickListener(this);


        int i = v.getId();
        if (i == R.id.imgStart) {
            onFunctionClickListener.onBtPatientClick();
//            onFunctionClickListener.onInfoClick();
        } else if (i == R.id.imgVideoOver) {
            doHangUp();
//            onFunctionClickListener.onEvalutionClick();
        } else if (i == R.id.imgSpeech) {
            avChatController.switchVideoToAudio(avSwitchListener);
        } else if (i == R.id.imgOver) {
            doHangUp();

        } else if (i == R.id.imgVideo) {
            avChatController.switchAudioToVideo(avSwitchListener);

        } else if (i == R.id.imgVideoOver) {
            doHangUp();
        } else if (i == R.id.tvRefuse) {
            onAudioToVideoAgree();
        } else if (i == R.id.tvAccept) {
            avChatController.receiveAudioToVideo(avSwitchListener);
        }
// else if (i == R.id.imgVideoOver) {
//            doHangUp();
//        } else if (i == R.id.imgSpeech) {
//            avChatController.switchVideoToAudio(avSwitchListener);
//        }
////        else if (i == R.id.imgvideo) {
////            avChatController.switchAudioToVideo(avSwitchListener);
////        }
////
//
//        else if (i == R.id.tvRefuse) {
//
//            doHangUp();
//        } else if (i == R.id.tvAccept) {
//            avChatController.receiveAudioToVideo(avSwitchListener);
//
//
////            doHangUp();
//        } else if (i == R.id.tvRefuse) {
//            onAudioToVideoAgree();
////            avChatController.toggleMute();
//        }
// else if (i == R.id.avchat_switch_camera) {
//            avChatController.switchCamera();
//        } else if (i == R.id.avchat_close_camera) {
//            closeCamera();
//        } else if (i == R.id.avchat_video_record) {
//            doToggleRecord();
//        } else if (i == R.id.avchat_video_switch_audio) {
//            if (isInSwitch) {
//                Toast.makeText(context, R.string.avchat_in_switch, Toast.LENGTH_SHORT).show();
//            } else {
//                avChatController.switchVideoToAudio(avSwitchListener);
//            }
//
//        }
    }

    public void onAudioToVideoAgree() {
        Log.d("vvv", avChatController.getAvChatData().getChatId() + "");
        AVChatManager.getInstance().sendControlCommand(avChatController.getAvChatData().getChatId(), AVChatControlCommand.SWITCH_AUDIO_TO_VIDEO_REJECT, new AVChatCallback<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                waittingView.setVisibility(View.GONE);
                audioRoot.setVisibility(View.GONE);
                videoingRoot.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailed(int code) {
//                LogUtil.i(TAG, "rejectAudioToVideo onFailed");

            }

            @Override
            public void onException(Throwable exception) {
//                LogUtil.i(TAG, "rejectAudioToVideo onException");
            }
        });

    }

    public void incomingAudioToVideo() {
        waittingView.setVisibility(View.VISIBLE);
        audioRoot.setVisibility(View.GONE);
        videoingRoot.setVisibility(View.GONE);

    }


    public void onAudioToVideoAgree(String largeAccount) {
//        showVideoInitLayout();
        audioRoot.setVisibility(View.VISIBLE);
        videoingRoot.setVisibility(View.GONE);
        waittingView.setVisibility(View.GONE);

        largeSizePreviewCoverLayout.setVisibility(View.GONE);
        largeSizePreviewLayout.setVisibility(View.VISIBLE);

        //打开视频
        AVChatManager.getInstance().enableVideo();
        AVChatManager.getInstance().startVideoPreview();


        initLargeSurfaceView(largeAccount);
        initSmallSurfaceView(SPUtil.get(context, "phone", "").toString());

    }

    // 拒绝来电
    private void doRefuseCall() {
        avChatController.hangUp(AVChatExitCode.HANGUP);
        closeSession();
    }

    public void peerVideoOff() {
//        isPeerVideoOff = true;
        if (localPreviewInSmallSize) { //local preview in small size layout, then peer preview should in large size layout
            showNotificationLayout(PEER_CLOSE_CAMERA);
        } else {  // peer preview in small size layout
            closeSmallSizePreview();
        }

    }

    public void peerVideoOn() {
//        isPeerVideoOff = false;
        if (localPreviewInSmallSize) {
            largeSizePreviewCoverLayout.setVisibility(View.GONE);
        } else {
            smallSizePreviewCoverImg.setVisibility(View.GONE);
        }
    }

//    private void doReceiveCall() {
//        showNotify(R.string.avchat_connecting);
//        shouldEnableToggle = true;
//
//        avChatController.receive(AVChatType.VIDEO, new AVChatControllerCallback<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//                canSwitchCamera = true;
//            }
//
//            @Override
//            public void onFailed(int code, String errorMsg) {
//                closeSession();
//            }
//        });
//    }

    private void doHangUp() {
        avChatController.hangUp( AVChatExitCode.HANGUP );
        closeSession();
    }

    /**
     * ********************** 开关摄像头 **********************
     */

//    private void closeCamera() {
//        if (!AVChatManager.getInstance().isLocalVideoMuted()) {
//            // 关闭摄像头
//            AVChatManager.getInstance().muteLocalVideo(true);
//            localVideoOff();
//        } else {
//            // 打开摄像头
//            AVChatManager.getInstance().muteLocalVideo(false);
//            localVideoOn();
//        }
//    }
//
//
//    // 对方打开了摄像头
//    private void localVideoOn() {
//        isLocalVideoOff = false;
//        if (localPreviewInSmallSize) {
//            smallSizePreviewCoverImg.setVisibility(View.GONE);
//        } else {
//            largeSizePreviewCoverLayout.setVisibility(View.GONE);
//        }
//    }

//    // 本地关闭了摄像头
//    private void localVideoOff() {
//        isLocalVideoOff = true;
//        if (localPreviewInSmallSize)
//            closeSmallSizePreview();
//        else
//            showNotificationLayout(LOCAL_CLOSE_CAMERA);
//    }
//
//    // 对方关闭了摄像头
//    public void peerVideoOff() {
//        isPeerVideoOff = true;
//        if (localPreviewInSmallSize) { //local preview in small size layout, then peer preview should in large size layout
//            showNotificationLayout(PEER_CLOSE_CAMERA);
//        } else {  // peer preview in small size layout
//            closeSmallSizePreview();
//        }
//    }
//
//    // 对方打开了摄像头
//    public void peerVideoOn() {
//        isPeerVideoOff = false;
//        if (localPreviewInSmallSize) {
//            largeSizePreviewCoverLayout.setVisibility(View.GONE);
//        } else {
//            smallSizePreviewCoverImg.setVisibility(View.GONE);
//        }
//    }

    // 关闭小窗口
    private void closeSmallSizePreview() {
        smallSizePreviewCoverImg.setVisibility(View.VISIBLE);
    }

    // 界面提示
    private void showNotificationLayout(int closeType) {
        if (largeSizePreviewCoverLayout == null) {
            return;
        }
        TextView textView = (TextView) largeSizePreviewCoverLayout;
        switch (closeType) {
            case PEER_CLOSE_CAMERA:
                textView.setText(R.string.avchat_peer_close_camera);
                break;
            case LOCAL_CLOSE_CAMERA:
                textView.setText(R.string.avchat_local_close_camera);
                break;
            case AUDIO_TO_VIDEO_WAIT:
                textView.setText(R.string.avchat_audio_to_video_wait);
                break;
            default:
                return;
        }
        largeSizePreviewCoverLayout.setVisibility(View.VISIBLE);
    }


    /**
     * ******************** 录制 ***************************
     */

//    private void doToggleRecord() {
//        avChatController.toggleRecord(AVChatType.VIDEO.getValue(), account, new AVChatController.RecordCallback() {
//            @Override
//            public void onRecordUpdate(boolean isRecording) {
//                showRecordView(isRecording, isRecordWarning);
//            }
//        });
//    }
//
//    public void showRecordView(boolean show, boolean warning) {
//        if (show) {
//            recordToggle.setEnabled(true);
//            recordToggle.setSelected(true);
//            recordView.setVisibility(View.VISIBLE);
//            recordTip.setVisibility(View.VISIBLE);
//            if (warning) {
//                recordWarning.setVisibility(View.VISIBLE);
//            } else {
//                recordWarning.setVisibility(View.GONE);
//            }
//        } else {
//            recordToggle.setSelected(false);
//            recordView.setVisibility(View.INVISIBLE);
//            recordTip.setVisibility(View.INVISIBLE);
//            recordWarning.setVisibility(View.GONE);
//        }
//    }

//    public void showRecordWarning() {
//        isRecordWarning = true;
//        showRecordView(avChatController.isRecording(), isRecordWarning);
//    }
//
//    public void resetRecordTip() {
//        isRecordWarning = false;
//        avChatController.setRecording(false);
//        showRecordView(false, isRecordWarning);
//    }
    private void closeSession() {
        ((Activity) context).finish();
    }

    public AVChatData getAvChatData() {
        return avChatData;
    }

    private void surfaceViewFixBefore43(ViewGroup front, ViewGroup back) {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            if (back.getChildCount() > 0) {
                View child = back.getChildAt(0);
                back.removeView(child);
                back.addView(child);
            }

            if (front.getChildCount() > 0) {
                View child = front.getChildAt(0);
                front.removeView(child);
                front.addView(child);
            }
        }
    }

}
