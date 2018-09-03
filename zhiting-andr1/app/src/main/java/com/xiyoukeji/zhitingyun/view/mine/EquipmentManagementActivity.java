package com.xiyoukeji.zhitingyun.view.mine;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xiyoukeji.zhitingyun.R;
import com.xiyoukeji.zhitingyun.ZhitingyunApplication;
import com.xiyoukeji.zhitingyun.base.BaseActivity;
import com.xiyoukeji.zhitingyun.data.remote.BaseObserver;
import com.xiyoukeji.zhitingyun.util.ActivityManager;
import com.xiyoukeji.zhitingyun.util.ToastUtils;
import com.xiyoukeji.zhitingyun.view.login.LoginActivity;
import com.xiyoukeji.zhitingyun.widget.StatusBarUtil;

import java.lang.reflect.Method;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

import static com.xiyoukeji.zhitingyun.ZhitingyunApplication.deviceManager;

public class EquipmentManagementActivity extends BaseActivity {
    @BindView(R.id.type)
    TextView type;
    @BindView( R.id.connect )
    Button connect;




    public EquipmentManagementActivity() {
        super( R.layout.activity_equipment );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        int from = getIntent().getIntExtra( "from", 10 );

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR );
            StatusBarUtil.setStatusBarColor( this, R.color.white );
        }
    }

    @OnClick({R.id.back_layout, R.id.connect,R.id.restart,R.id.recover})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_layout:
                finish();
                break;
            case R.id.connect:
                startActivity( ConnectStepOneActivity.class );
                break;
            case R.id.restart:
                if(ZhitingyunApplication.curState!=null){
                    if(ZhitingyunApplication.deviceManager.isConnected( ZhitingyunApplication.curState.getDevMac())==true){
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        /*设置主题、内容、按钮*/
                        builder.setTitle("").setMessage("是否确定重启设备？")
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface arg0, int arg1) {
                                        arg0.dismiss();
                                        /*关闭对话框*/
                                    }
                                })
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1) {
                                        deviceManager.resetHaDevice();
                                    }
                                });
                        builder.create().show();
                    }
                    else {
                        ToastUtils.showLong( "请连接设备" );
                    }
                }
                else{
                    ToastUtils.showLong( "请连接设备" );
                }
//                deviceManager.resetHaDevice();
                break;
            case R.id.recover:
                if(ZhitingyunApplication.curState!=null){
                    if(ZhitingyunApplication.deviceManager.isConnected( ZhitingyunApplication.curState.getDevMac())==true){
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        /*设置主题、内容、按钮*/
                        builder.setTitle("").setMessage("是否确定重启设备？")
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface arg0, int arg1) {
                                        arg0.dismiss();
                                        /*关闭对话框*/
                                    }
                                })
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1) {

                                    }
                                });
                        builder.create().show();
                    }
                    else {
                        ToastUtils.showLong( "请连接设备" );
                    }
                }
                else{
                    ToastUtils.showLong( "请连接设备" );
                }
                break;
        }
    }







    @Override
    protected void initView() {
        super.initView();


    }

}
