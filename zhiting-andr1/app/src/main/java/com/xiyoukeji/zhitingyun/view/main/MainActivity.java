package com.xiyoukeji.zhitingyun.view.main;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jude.rollviewpager.OnItemClickListener;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.lzy.imagepicker.view.SystemBarTintManager;

import com.netease.nimlib.sdk.avchat.AVChatManager;
import com.netease.nimlib.sdk.avchat.model.AVChatCommonEvent;
import com.netease.nimlib.sdk.avchat.model.AVChatData;
import com.xiyoukeji.zhitingyun.R;
import com.xiyoukeji.zhitingyun.ViewModelFactory;
import com.xiyoukeji.zhitingyun.WebActivity0;
import com.xiyoukeji.zhitingyun.ZhitingyunApplication;
import com.xiyoukeji.zhitingyun.adapter.LunBoAdapter;
import com.xiyoukeji.zhitingyun.base.BaseActivity;

import com.xiyoukeji.zhitingyun.data.entity.DeviceEntity;
import com.xiyoukeji.zhitingyun.data.entity.ListModel;
import com.xiyoukeji.zhitingyun.data.entity.LunBoEntity;
import com.xiyoukeji.zhitingyun.data.entity.Model0;
import com.xiyoukeji.zhitingyun.data.entity.Quick;
import com.xiyoukeji.zhitingyun.data.remote.BaseObserver;
import com.xiyoukeji.zhitingyun.databinding.ActivityMainBinding;
import com.xiyoukeji.zhitingyun.util.ActivityManager;
import com.xiyoukeji.zhitingyun.util.CameraCanUseUtils;
import com.xiyoukeji.zhitingyun.util.Constant;
import com.xiyoukeji.zhitingyun.util.ToastUtils;
import com.xiyoukeji.zhitingyun.view.mine.MineActivity;
import com.xiyoukeji.zhitingyun.viewmodel.main.MainViewModel;
import com.xiyoukeji.zhitingyun.widget.StatusBarUtil;
import com.xiyoukeji.zhitingyun.widget.WS;
import com.xiyoukeji.zhitingyun.yunxin.ChatMainActivity;
import com.xiyoukeji.zhitingyun.yunxin.SPUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.RuntimePermissions;
import static com.xiyoukeji.zhitingyun.ZhitingyunApplication.deviceManager;


@RuntimePermissions
public class MainActivity extends BaseActivity {

    @BindView(R.id.home_rv)
    RollPagerView mRollViewPager;


    @BindView(R.id.viewRed)
    View viewRed;


    private BottomSheetDialog mDialog;
    private MainViewModel mViewModel;
    private ActivityMainBinding mBinding;
    private long lastTime;
    private long duration = 2000L;
    private BluetoothAdapter adapter;


    DeviceEntity entity;

    private LunBoAdapter mAdapter;

    private String ids = "";
    private int index = 0;


    private String mac="";


    List<LunBoEntity>list=new ArrayList<>(  );


    public MainActivity() {
        super(R.layout.activity_main);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WS.getInStanceBlock().connect();
        initView();
        initChat();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            StatusBarUtil.setStatusBarColor(this, R.color.white); }

    }

    private void initChat() {
        AVChatManager.getInstance().observeIncomingCall(inComingCallObserver, true);
        AVChatManager.getInstance().observeHangUpNotification(callHangupObserver, true);
    }

    //     通话过程中，收到对方挂断电话
    private com.netease.nimlib.sdk.Observer<AVChatCommonEvent> callHangupObserver = new com.netease.nimlib.sdk.Observer<AVChatCommonEvent>() {
        @Override
        public void onEvent(AVChatCommonEvent avChatHangUpInfo) {

        }
    };

    private com.netease.nimlib.sdk.Observer<AVChatData> inComingCallObserver = new com.netease.nimlib.sdk.Observer<AVChatData>() {
        @Override
        public void onEvent(final AVChatData data) {

            if (CameraCanUseUtils.isCameraCanUse()) {
                Log.i("", "相机");

                if( Constant.START==0){
                    for (Activity activity:ActivityManager.activityList)
                        Log.d( "ttt",activity.toString() );
                    Constant.START=1;
                    startActivity(new Intent( ActivityManager.getCurrentActivity(), ChatMainActivity.class).putExtra("avChatData", data));


                }

            } else {
                ToastUtils.showLong("没相机权限，请到应用程序权限管理开启权限");
                //跳转至app设置
                getAppDetailSettingIntent();
                return;
            }


        }
    };


    @Override
    protected void bindingData() {
        super.bindingData();
        mBinding = DataBindingUtil.setContentView(mActivity, layoutId);
    }

    @Override
    protected void initView() {
        super.initView();


        adapter = BluetoothAdapter.getDefaultAdapter();
        int a2dp = adapter.getProfileConnectionState(BluetoothProfile.A2DP);
        int headset = adapter.getProfileConnectionState(BluetoothProfile.HEADSET);
        int health = adapter.getProfileConnectionState(BluetoothProfile.HEALTH);


        int flag = -1;
        if (a2dp == BluetoothProfile.STATE_CONNECTED) {
//            Log.i(TAG, "onCreate:1 "+a2dp);
            flag = a2dp;
        } else if (headset == BluetoothProfile.STATE_CONNECTED) {
//            Log.i(TAG, "onCreate:2 "+headset);
            flag = headset;
        } else if (health == BluetoothProfile.STATE_CONNECTED) {
//            Log.i(TAG, "onCreate:3 "+health);
            flag = health;
        }



        if (flag != -1) {
            adapter.getProfileProxy(MainActivity.this, new BluetoothProfile.ServiceListener() {

                @Override
                public void onServiceDisconnected(int profile) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void onServiceConnected(int profile, BluetoothProfile proxy) {
                    // TODO Auto-generated method stub
                    List<BluetoothDevice> mDevices = proxy.getConnectedDevices();
                    if (mDevices != null && mDevices.size() > 0) {
                        for (BluetoothDevice device : mDevices) {
                            Log.i("W", "device name: " + device.getName() + ";" + device.getAddress());
//                            mAddress.setText( device.getAddress() );

                            mac=device.getAddress();
                            SharedPreferences sharedPreferences = getSharedPreferences("mySP", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("address", device.getAddress());
                            editor.commit();
                        }
                    } else {
                        Log.i("W", "mDevices is null");
                    }
                }
            }, flag);
        }

        mRollViewPager.setPlayDelay(2000);//时间间隔
        mRollViewPager.setAnimationDurtion(500);//透明度


        mAdapter = new LunBoAdapter(MainActivity.this, list);
//        mRollViewPager.setAdapter(new TestNormalAdapter());
        mRollViewPager.setAdapter( mAdapter );
        mRollViewPager.setOnItemClickListener( new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                LunBoEntity entity=list.get( position );
//                entity.getLink();
                WebActivity0.runActivity(mContext, "资讯","http://"+ entity.getLink());

            }
        } );

    }

    private void getData() {
        mViewModel.getLunBo( "", "", new BaseObserver<ListModel<List<LunBoEntity>>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable( d );
            }

            @Override
            public void onNext(ListModel<List<LunBoEntity>> listListModel) {
                list.clear();
                list.addAll( listListModel.getComeback() );
                mAdapter.notifyDataSetChanged();

            }
        } );



    }

    @Override
    protected void initData() {
        super.initData();
        mViewModel = obtainViewModel(this);

        mViewModel.getLoadingState().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean) {
                    showLoading(mViewModel.mTip.get());
                } else {
                    dismissLoading();
                }
            }
        });
        getData();
    }

    public static MainViewModel obtainViewModel(FragmentActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        MainViewModel viewModel = ViewModelProviders.of(activity, factory).get(MainViewModel.class);
        return viewModel;
    }

    @Override
    protected void onResume() {
        super.onResume();
        //获取个人信息
        if (!isLoginSimple()) {
            return;
        }

    }


    private class TestNormalAdapter extends StaticPagerAdapter {
        private int[] imgs = {
                R.mipmap.witcher,
                R.mipmap.w1,
                R.mipmap.w3,
                R.mipmap.w5,
        };


        @Override
        public View getView(ViewGroup container, int position) {
            ImageView view = new ImageView(container.getContext());
            view.setImageResource(imgs[position]);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return view;
        }


        @Override
        public int getCount() {
            return imgs.length;
        }
    }


    @OnClick({R.id.mine, R.id.hearing_identify,R.id.information, R.id.quick_check, R.id.expert, R.id.diagnosis,R.id.leftimg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mine:
                startActivity( MineActivity.class );
                break;
            case R.id.hearing_identify:
                if(ZhitingyunApplication.curState!=null){
                    if(ZhitingyunApplication.deviceManager.isConnected( ZhitingyunApplication.curState.getDevMac())==true){
                        if (mDialog == null) {
                            initDialog();
                        }
                        mDialog.show();
                    }
                    else {
                        ToastUtils.showLong( "请连接设备" );
                    }
                }
                else{
                    ToastUtils.showLong( "请连接设备" );
                }

                break;
            case R.id.quick_check:
                if(ZhitingyunApplication.curState!=null){
                if(ZhitingyunApplication.deviceManager.isConnected( ZhitingyunApplication.curState.getDevMac())==true){

                startActivity( AutoRegulationActivity.class );
                }
                else {
                    ToastUtils.showLong( "请连接设备" );
                }
                }
                else{
                    ToastUtils.showLong( "请连接设备" );
                }

                break;
            case R.id.expert:
                startActivity(AppointmentActivity.class);
                break;
            case R.id.information:
                startActivity(MessageActivity.class);
                break;
            case R.id.diagnosis:

                if(ZhitingyunApplication.curState!=null){
                    if(ZhitingyunApplication.deviceManager.isConnected( ZhitingyunApplication.curState.getDevMac())==true){
                        MainActivityPermissionsDispatcher.showChoiceWithCheck(this);
                final ProgressDialog proDialog = android.app.ProgressDialog.show(this, "请稍等", "正在进入等待页面");
                Thread thread = new Thread()
                {
                    public void run()
                    {
                        try
                        {
                            sleep(2000);
                            startActivity( WaitingActivity.class );
                        } catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                        proDialog.dismiss();
                    }
                };
                thread.start();



                 }
                    else {
                        ToastUtils.showLong( "请连接设备" );
                    }
                }
                else{
                    ToastUtils.showLong( "请连接设备" );
                }


                break;
            case R.id.leftimg:

                break;

        }
    }

    private void initDialog() {
        mDialog = new BottomSheetDialog(mContext);
        mDialog.setCancelable(true);
        mDialog.setCanceledOnTouchOutside(true);
        final View dialogView = LayoutInflater.from(mContext).inflate(R.layout.dialog_hearing_damaged, null);
        TextView isOwner = dialogView.findViewById(R.id.owner);
        TextView isOthers = dialogView.findViewById(R.id.others);
        TextView cancelTv = dialogView.findViewById(R.id.cancel);

        isOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDialog != null) {
//                    deviceManager.
                    ToastUtils.showLong( "提示：已关闭助听功能" );
                    deviceManager.setHaDeviceSwitch(false);
                    startActivity(DamageActivity.class);
                    mDialog.dismiss();

                }
            }
        });

        isOthers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDialog != null) {
                    ToastUtils.showLong( "提示：已关闭助听功能" );

                    deviceManager.setHaDeviceSwitch(false);

                    startActivity(Damage0Activity.class);

                    mDialog.dismiss();
                }
            }
        });

        cancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDialog != null) {
                    mDialog.dismiss();
                }
            }
        });

        mDialog.setContentView(dialogView);
    }

    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - lastTime) > duration) {
            ToastUtils.showShort("再按一次退出");
            lastTime = currentTime;
        } else {
            finish();
            Process.killProcess(Process.myPid());
            Runtime.getRuntime();
        }
    }

    private void getAppDetailSettingIntent() {
        Intent localIntent = new Intent();
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", mContext.getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", mContext.getPackageName());
        }
        startActivity(localIntent);
    }

    @NeedsPermission({Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.READ_PHONE_STATE, Manifest.permission.RECORD_AUDIO})
    void showChoice() {
        Gson gson = new Gson();
        final List<DeviceEntity> list = new ArrayList<>();
        String session1 = SPUtil.get(MainActivity.this, "session1", "").toString();
        if (session1.equals(""))
            ToastUtils.showShort("请连接设备");
        else {
            ids = "";
            index = 0;
            DeviceEntity entity1 = gson.fromJson(session1, DeviceEntity.class);
            list.add(entity1);
            String session2 = SPUtil.get(MainActivity.this, "session2", "").toString();
            if (session2.equals("")) {
                DeviceEntity deviceEntity = entity1.createDeviceEntity();
                deviceEntity.setClassify(2);
                list.add(deviceEntity);
            } else {
                DeviceEntity entity2 = gson.fromJson(session2, DeviceEntity.class);
                list.add(entity2);
            }
            String session3 = SPUtil.get(MainActivity.this, "session3", "").toString();
            if (session3.equals("")) {
                DeviceEntity deviceEntity = entity1.createDeviceEntity();
                deviceEntity.setClassify(3);
                list.add(deviceEntity);
            } else {
                DeviceEntity entity3 = gson.fromJson(session3, DeviceEntity.class);
                list.add(entity3);
            }
            String session4 = SPUtil.get(MainActivity.this, "session4", "").toString();
            if (session4.equals("")) {
                DeviceEntity deviceEntity = entity1.createDeviceEntity();
                deviceEntity.setClassify(4);
                list.add(deviceEntity);
            } else {
                DeviceEntity entity4 = gson.fromJson(session4, DeviceEntity.class);
                list.add(entity4);
            }

            for (int i = 0; i < list.size(); i++) {
                mViewModel.submitParamater(list.get(i), new BaseObserver<Model0<DeviceEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(Model0<DeviceEntity> model0) {
                        index++;
                        ids += model0.getComeback().getId();

                        if (index != list.size()) {
                            ids += ",";

                        }
                        if (index == list.size()) {
                            mViewModel.addQuickOrder(ids, new BaseObserver<Model0<Quick>>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(Model0<Quick> quickModel0) {
                                    SharedPreferences sharedPreferences = getSharedPreferences("mySP", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putInt("orderId",quickModel0.getComeback().getId() );
                                    editor.putLong( "createTime",quickModel0.getComeback().getCreateTime() );
                                    editor.commit();

                                    ids = "";
                                    index = 0;
                                    ToastUtils.showShort("正在发起快速诊疗，请稍后");
                                }
                            });

                        }
                    }
                });
            }
        }


    }

    @OnPermissionDenied({Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.READ_PHONE_STATE, Manifest.permission.RECORD_AUDIO})
    void denied() {
    }

    @OnNeverAskAgain({Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.READ_PHONE_STATE, Manifest.permission.RECORD_AUDIO})
    void nerverAskAgain() {
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

}
